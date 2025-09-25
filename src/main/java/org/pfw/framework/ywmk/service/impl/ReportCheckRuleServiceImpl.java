package org.pfw.framework.ywmk.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.ywmk.dao.ReportCheckRuleDao;
import org.pfw.framework.ywmk.domain.ReportCheckRule;
import org.pfw.framework.ywmk.domain.ReportInfo;
import org.pfw.framework.ywmk.service.ReportCheckRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ReportCheckRuleServiceImpl implements ReportCheckRuleService {

	@Autowired
	private ReportCheckRuleDao entityDao;

	@Transactional(readOnly=true)
	public ReportCheckRule getById(String id) {
		return entityDao.get(id);
	}
	
	@Transactional(readOnly=true)
	public Page findPage(Page page, List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}	
	
	@Transactional(readOnly=true)
	public List<ReportCheckRule> find(List<PropertyFilter> filters)
	{
		return entityDao.find(filters);
	}
	
	@Transactional(readOnly=true)
	public List<ReportCheckRule> find(List<PropertyFilter> filters, LinkedHashMap orderbyMap) {
		return entityDao.find(filters, orderbyMap);
	}
	
	public void save(ReportCheckRule entity)
	{
		entityDao.save(entity);
	}
	
	public void update(ReportCheckRule entity)
	{
		entityDao.update(entity);
	}

	public void deleteById(String id)
	{
		entityDao.delete(id);
	}
	
	public void delete(List<String> checks)
	{
		if(checks!=null&&checks.size()>0){
			for (String id : checks) {
				entityDao.delete(id);
			}
		}
	}

	@Override
	public List<ReportCheckRule> findAll()
	{
		return entityDao.find(" from ReportCheckRule r ");
	}
	
	@Override
	public List<ReportCheckRule> findbyhql(String hql, Object... val) 
	{
		return entityDao.find(hql, val);
	}
	
	@Override
	public List<ReportCheckRule> findBy(String propertyName, Object value,
			MatchType matchType) {
		return entityDao.findBy(propertyName, value, matchType);
	}
	
	@Override
	public void deleteHql(String hql, Object... val) {
		entityDao.createQuery(hql, val).executeUpdate();
	}

	/**
	 * 
	 * 公式拆分
	 * 
	 */
	@Override
	public List ruleSplit(String orgrule) 
	{
		//exp = "a1+a2*a3+56.3-43A.789-(a4/a5)=a6+a7-a8*a9+(a10/a11)";
		//去掉公式中的括号
//		orgrule = orgrule.replaceAll("\\(|\\)", "");
		//对公式进行分割，去掉符号
		String[] expArray = orgrule.split("[-+*/^]|(>=)|(>)|(<=)|(<)|(==)|(=)|(!=)|[&|]{2}");
		List<String> stringList = new ArrayList<String>();
		for(int i = 0;i<expArray.length;i++){
			String s = expArray[i];
			//过滤掉判断的纯数字值的，仅保留下测点
			if(!s.matches("^(\\-|\\+)?\\d+(\\.\\d+)?$")){
				if(!stringList.contains(s)){
					stringList.add(s);
				}
			}
		}
		return stringList;
	}

	/**
	 * 校验规则
	 * 返回数学公式+校验规则数学公式对应关系字符串
	 * 格式：数学公式$校验规则数学公式对应关系
	 * @return
	 */
	@Override
	public String valrule(String rulestr) 
	{
		
		String retstr = "";
		
		try{
			if(StringUtils.isNotEmpty(rulestr))
			{
				rulestr = rulestr.replaceAll(" ", "");//先去空格
				//原始校验公式元素必须为:表号.行号,列号
				List<String> chfruleArr = ruleSplit(rulestr);
				if(chfruleArr != null && chfruleArr.size() > 0)
				{
					boolean flg = true;
					
					HashMap<String, String> bmhtmlmap = new HashMap<String,String>();//公式涉及到表名map及配置html
					
					for(String elemet : chfruleArr)
					{
						if(StringUtils.isNotEmpty(elemet) && elemet.indexOf(".")>=0 && elemet.indexOf(",")>=0)
						{
							//生成公式涉及到表名map及配置html用于后面计算
							String bmtmp = elemet.substring(0, elemet.indexOf("."));
							if(StringUtils.isNotEmpty(bmtmp))
							{
								if(!bmhtmlmap.containsKey(bmtmp))
								{
									List<ReportInfo> tmplst = entityDao.createQuery(" from ReportInfo r where r.reportcode = ?", bmtmp).list();
									if(tmplst != null && tmplst.size() > 0)
									{
										bmhtmlmap.put(bmtmp, tmplst.get(0).getReptTable());
									}else{
										retstr = "表名："+bmtmp+"不存在";
										flg = false;
										break;
									}
								}
							}
							
						}else{
							retstr = "校验规则非法";//原始公式错误
							flg = false;
							break;
						}
					}
					if(!flg)
					{
						//retstr = "校验规则非法";//原始公式错误
					}else{
						//公式基本格式验证成后，进行数学公式,原始公式与数学公式对应关系处理,规则含义处理
						//---------将原始公式转化为数学公式
						LinkedHashMap<String, String> gxmap = new LinkedHashMap<String,String>();//原始元素与数学公式变量对应map
						int i = 1;
						for(String elemet : chfruleArr)
						{
							//同一个原始变量只能有一个对应数学变量
							if(!gxmap.containsKey(elemet))
							{
								
								//以tq+数字开头生成一个变量
								String tmpbl = "tq"+i;
								gxmap.put(elemet, tmpbl);
								i++;
							}
						}
						//---------原始公式变量和数学公式变量的对应关系
						if(gxmap != null && gxmap.size() > 0)
						{
							String sxrulestr = rulestr;
							String dygxstr = "";//对应关系字符串
							String gzhystr = rulestr;
							for (String key : gxmap.keySet()) {
								String valu = gxmap.get(key);
								//数学公式生成
								sxrulestr = sxrulestr.replaceAll(key, valu);
								//原始公式变量和数学公式变量的对应关系生成
								dygxstr = dygxstr + key + ":" + valu + ";";
								
								//单元素校验规则含义生成
								String bmtmp = key.substring(0, key.indexOf("."));
								String tmpstr = this.getGzhy(key, bmhtmlmap.get(bmtmp));
								/*
								if(!tmpstr.equals(""))
								{
								}else{
									//如果找不到对应的规则含义
								}
								*/
								gzhystr = gzhystr.replaceAll(key, tmpstr);
							}
							retstr = sxrulestr+"$"+dygxstr+"$"+gzhystr;
						}
					}
				}else{
					retstr = "校验规则非法";//原始公式错误
				}
				
			}else{
				retstr = "校验规则不能为空";//空值错误代码
			}
		}catch(Exception e)
		{
			retstr = "内部发生错误,请联系管理员";//内部发生Exception报错
		}
		
		return retstr;
		
	}
	
	/**
	 * 
	 * 经验校验公式拆分
	 * 
	 */
	@Override
	public List jyruleSplit(String orgrule) 
	{
		//exp = "a1+a2*a3+56.3-43A.789-(a4/a5)=a6+a7-a8*a9+(a10/a11)";
		//去掉公式中的括号
		orgrule = orgrule.replaceAll("\\(|\\)", "");
		//对公式进行分割，去掉符号
		String[] expArray = orgrule.split("[-+*/^]|(>=)|(>)|(<=)|(<)|(==)|(=)|(!=)|[&|]{2}");
		List<String> stringList = new ArrayList<String>();
		for(int i = 0;i<expArray.length;i++){
			String s = expArray[i];
			//过滤掉判断的纯数字值的，仅保留下测点
			if(!s.matches("^(\\-|\\+)?\\d+(\\.\\d+)?$")){
				if(!stringList.contains(s)){
					stringList.add(s);
				}
			}
		}
		return stringList;
	}

    /**
	 * 方法说明 :将首字母和带 _ 后第一个字母 转换成大写
	 * 
	 * @return :String
	 * @author :HFanss
	 * @date :2018年5月31日下午9:52:19
	 */
	public String upperTable(String str)
	{
		// 字符串缓冲区
		StringBuffer sbf = new StringBuffer();
		// 如果字符串包含 下划线
		if (str.contains("_"))
		{
			// 按下划线来切割字符串为数组
			String[] split = str.split("_");
			// 循环数组操作其中的字符串
			for (int i = 0, index = split.length; i < index; i++)
			{
				// 递归调用本方法
				String upperTable = upperTable(split[i]);
				// 添加到字符串缓冲区
				sbf.append(upperTable);
			}
		} else
		{// 字符串不包含下划线
			// 转换成字符数组
			char[] ch = str.toCharArray();
			// 判断首字母是否是字母
			if (ch[0] >= 'a' && ch[0] <= 'z')
			{
				// 利用ASCII码实现大写
				ch[0] = (char) (ch[0] - 32);
			}
			// 添加进字符串缓存区
			sbf.append(ch);
		}
		// 返回
		return sbf.toString();
	}
	/**
	 * 经验校验规则
	 * 返回数学公式+校验规则数学公式对应关系字符串
	 * 格式：数学公式$校验规则数学公式对应关系
	 * @return
	 */
	@Override
	public String jyvalrule(String rulestr) 
	{
		
		String retstr = "";
		
		String sxgs = rulestr;
		String jygzsxgs = "";
		String gzhy = rulestr;
		
		Set<String> sjbbdmSet = new HashSet<>();
 		
		try {
			
			// TODO: handle exception
			if(StringUtils.isNotEmpty(rulestr)){
					
				
				Pattern p = Pattern.compile("\\[.*?\\]");
		        Matcher m = p.matcher(rulestr);
				
				int i = 0;
	        	while (m.find()){
		            
	        		String tabdm = m.group().substring(1, m.group().length()-1);
	        		
	        		if(tabdm.indexOf(",") != -1){
	        		
		        		List<String>  gsList = Struts2Utils.split(tabdm, ".");
		        		List<String>  ywxyList = Struts2Utils.split(gsList.get(1), ",");
		        		
		        		String dm = gsList.get(0).replaceAll("last", "");
		        		String ywx = ywxyList.get(0);
		        		String ywy = ywxyList.get(1);
		        		
		        		
		        		List<ReportInfo> tmplst = entityDao.createQuery(" from ReportInfo r where r.dm = ? and r.sfyx='1' ", dm).list();
		        		
		        		if(tmplst != null && tmplst.size() > 0)
						{
		        			Document document = Jsoup.parse(tmplst.get(0).getReptTable());
		    				//通过业务x,y找到这个td元素
		    				Element curTD = document.select("td[ywx="+ywx+"][ywy="+ywy+"]").get(0);
		    				gzhy = gzhy.replace(m.group(), curTD.attr("ywhyxy"));
		        			
						}else{
							retstr = "代码为："+dm+"报表不存在";
							break;
						}
		        		i ++;
		        		sxgs = sxgs.replaceFirst(gsList.get(0)+"."+ywx+","+ywy, "tq"+i);
		        		jygzsxgs += m.group() + ":"+"tq"+i+";";
		        		sjbbdmSet.add(dm);
	        		}else{
	        			List<String>  gsList = Struts2Utils.split(tabdm, ".");
	        			String dm = gsList.get(0);
		        		String zdx = gsList.get(1);
		        		List<ReportInfo> tmplst = entityDao.createQuery(" from ReportInfo r where r.dm = ? and r.sfyx='1' ", dm).list();
		        		if(tmplst != null && tmplst.size() > 0)
						{
		        			String detailEntName = upperTable(tmplst.get(0).getJgbmc2().replace("t_ywmk_", "").replace("T_YWMK_", ""));
							
							//得到实体类名
							Class cls = Class.forName("org.pfw.framework.ywmk.domain."+detailEntName);
							Field[] fieldarr = cls.getDeclaredFields();
							
							boolean flag = false;
							for (Field field : fieldarr) {
								field.setAccessible(true);
								String fieldName = field.getName();
								if(zdx.equals(fieldName)){
									flag = true;
									break;
								}
							}
		        			
							if(!flag){
								return retstr = "代码为："+dm+"的报表未找到:"+zdx+"属性";
							}
							
						}else{
							retstr = "代码为："+dm+"报表不存在";
							break;
						}
		        		i ++;
		        		sxgs = sxgs.replaceFirst(gsList.get(0)+"."+zdx, "tq"+i);
		        		jygzsxgs += m.group() + ":"+"tq"+i+";";
		        		sjbbdmSet.add(dm);
	        		}
	        		
	        		
	        		
	        	}
	        	

	    		Object[] array = sjbbdmSet.toArray();
	            String sjbb =  StringUtils.join(array, ",");
	    		
	    		
	    		retstr = sxgs+"$"+jygzsxgs+"$"+gzhy+"$"+sjbb;
				
			}else{
				
				retstr = "校验规则不能为空";//空值错误代码
			}
		} catch (Exception e) {
			retstr = "检验时发生错误";
			e.printStackTrace();
		}
		
		return retstr;
	
		
		
	}
	
	
	
	/**
	 * 根据原始规则字符串 从表存储hmtl里搜到到他的含义
	 * @param gzstr 规则字符串
	 * @param bccHtml 表存储hmtl
	 * @return
	 */
	public String getGzhy(String gzstr,String bccHtml){
		String retstr = "";
		
		
		try{
			if(StringUtils.isNotEmpty(gzstr) && StringUtils.isNotEmpty(bccHtml))
			{
				String hangl = gzstr.substring(gzstr.indexOf(".")+1);
				String xStr = hangl.split(",")[0];
				String ywStr = hangl.split(",")[1];
				
				Document document = Jsoup.parse(bccHtml);
				int rowx = Integer.parseInt(document.select("td[ifjzcell=1]").get(0).attr("x"));//得到基准单元格x
				
				//通过业务x,y找到这个td元素
				Element curTD = document.select("td[ywx="+xStr+"][ywy="+ywStr+"]").get(0);
				
				retstr = curTD.attr("ywhyxy");
				
			}
		}catch(Exception e)
		{
			retstr = "";
		}
//		try{
//			if(StringUtils.isNotEmpty(gzstr) && StringUtils.isNotEmpty(bccHtml))
//			{
//				String hangl = gzstr.substring(gzstr.indexOf(".")+1);
//				String xStr = hangl.split(",")[0];
//				String ywStr = hangl.split(",")[1];
//				
//				Document document = Jsoup.parse(bccHtml);
//				int rowx = Integer.parseInt(document.select("td[ifjzcell=1]").get(0).attr("x"));//得到基准单元格x
//				
//				//通过业务x,y找到这个td元素
//				Element curTD = document.select("td[ywx="+xStr+"][ywy="+ywStr+"]").get(0);
//				int yStr = Integer.parseInt(curTD.attr("y"));
//				
//				if(curTD != null)
//				{
//					String ywystr = "";
//					for(int i=0;i < rowx-1 ; i++)
//					{
//						String dqjzdygid = "td_"+(rowx-i-1)+"_"+yStr;
//						
//						String ywyval = document.getElementById(dqjzdygid).html();
//						
//						if(StringUtils.isNotEmpty(ywyval))
//						{
//							ywystr = ywyval +"|"+ ywystr;
//						}else{
//							for(int j=0;j < yStr; j++)
//							{
//								dqjzdygid = "td_"+(rowx-i-1)+"_"+(yStr-j);
//								ywyval = document.getElementById(dqjzdygid).html();
//								if(StringUtils.isNotEmpty(ywyval))
//								{
//									String nodecolspan = document.getElementById(dqjzdygid).attr("colspan");
//									String noderowspan = document.getElementById(dqjzdygid).attr("rowspan");
//									if(StringUtils.isNotEmpty(nodecolspan) && Integer.parseInt(nodecolspan) > 0)
//									{
//										ywystr = ywyval +"|"+ ywystr;
//									}else if(StringUtils.isNotEmpty(noderowspan) && Integer.parseInt(noderowspan) > 0)
//									{
//										ywystr = ywyval +"|"+ ywystr;
//									}
//								}
//							}
//						}
//					}
//					
//					if((ywystr.charAt(ywystr.length()-1)) == '|'){
//						ywystr = ywystr.substring(0,ywystr.length()-1);
//					}
//					retstr = ywystr;
//				}
//				
//			}
//			
//		}catch(Exception e)
//		{
//			retstr = "";
//		}
		return retstr;
	}
}
