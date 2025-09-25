package org.pfw.framework.ywmk.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.ywmk.dao.ReportCheckRuleDao;
import org.pfw.framework.ywmk.domain.ReportCheckRule;
import org.pfw.framework.ywmk.domain.ReportInfo;
import org.pfw.framework.ywmk.service.ReportCheckRuleService;
import org.pfw.framework.ywmk.service.ReportInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import freemarker.template.SimpleDate;

/**
 * 功能管理Action.
 */
@Results
	({ 
		@Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl" , type="freemarker"),
		@Result(name ="layerForparent",location="/util/winopenlayerForparent.ftl", type = "freemarker"),
		@Result(name="success",location="/ywmk/report_check_rule-list.ftl",type = "freemarker"),
		@Result(name="impRuleftl",location="/ywmk/report_check_rule-impRuleftl.ftl",type = "freemarker"),
		@Result(name ="input",location="/ywmk/report_check_rule-input.ftl", type = "freemarker"),
		@Result(name="gzdrmb",type="stream",
		params={"contentType","application/octet-stream;charset=utf-8","contentDisposition","attachment;filename=${wjname}","inputName","downloadFile","bufferSize","102410"}
		)
	})
public class ReportCheckRuleAction extends CrudActionSupport<ReportCheckRule> {
	@Autowired
	protected ReportCheckRuleService entityService;
	@Autowired
	protected ReportCheckRuleDao entityDao;
	@Autowired
	protected ReportInfoService rptService;
	
	private String dzurl;
	private String wjname;

	private int errcont = 0;
	private int succont = 0;
	private File drfj;
	private List<String> errList;
	
	public int getErrcont() {
		return errcont;
	}
	public void setErrcont(int errcont) {
		this.errcont = errcont;
	}
	public int getSuccont() {
		return succont;
	}
	public void setSuccont(int succont) {
		this.succont = succont;
	}
	public File getDrfj() {
		return drfj;
	}
	public void setDrfj(File drfj) {
		this.drfj = drfj;
	}
	public List<String> getErrList() {
		return errList;
	}
	public void setErrList(List<String> errList) {
		this.errList = errList;
	}
	
	
	
	public String getDzurl() {
		return dzurl;
	}
	public void setDzurl(String dzurl) {
		this.dzurl = dzurl;
	}
	public String getWjname() {
		return wjname;
	}
	public void setWjname(String wjname) {
		this.wjname = wjname;
	}

	private List reqlist;
	public List getReqlist() {
		return reqlist;
	}
	public void setReqlist(List reqlist) {
		this.reqlist = reqlist;
	}

	@Override
	public String delete() throws Exception {
		
		if(checks!=null&&checks.size()>0){
			entityService.delete(checks);
		}
		Struts2Utils.renderText("删除成功");
		return null;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	private String fenl;
	public String getFenl() {
		return fenl;
	}
	public void setFenl(String fenl) {
		this.fenl = fenl;
	}
	@Override
	public String list() throws Exception {
		
		
		
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		//设置默认排序方式
		if(StringUtils.isNotEmpty(fenl)){
			filters.add(new PropertyFilter("EQS_fenl",fenl));
		}
		if (!page.isOrderBySetted()) {
			page.setOrderBy("jygz");
			page.setOrder(Page.ASC);
		}
		page = entityService.findPage(page, filters);
		
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (!StringUtils.isEmpty(id)) {
			entity = (ReportCheckRule) entityService.getById(id);
		} else {
			entity = new ReportCheckRule();
		}
		
	}

	@Override
	public String save() throws Exception {
		String jyjygz = Struts2Utils.getParameter("jyjygz");
		if(StringUtils.isNotEmpty(jyjygz)){
			entity.setJygz(jyjygz);
		}
		if (!StringUtils.isEmpty(id))
		{
			entityService.update(entity);
		}else{
			
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 Date now = new Date();
			 entity.setCjsj(sdf.format(now));
			
			entityService.save(entity);
		}
		Struts2Utils.setPromptInfoToReq("保存成功!!");
		
		return RELOAD;
	}
	
	/**
	 * 通过关键字查询报表
	 * select2组件使用
	 * 得到查询结果的json数据
	 */
	public void jsnbykeyword() throws Exception
	{
		String kword = Struts2Utils.getParameter("kword");
		
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		
		if(StringUtils.isNotEmpty(kword))
		{
			PropertyFilter pft = new PropertyFilter("LIKES_reportcode_OR_reportname",kword);
			filters.add(pft);
		}
		
		List<ReportInfo> retlst = rptService.find(filters);
		
		String retstr = Struts2Utils.listToJson(retlst, "id","reportcode","reportname","versionh","dm");
		
		Struts2Utils.renderText(retstr);
	}
	
	/**
	 * 校验规则
	 * 返回数学公式+校验规则数学公式对应关系字符串
	 * 格式：数学公式$校验规则数学公式对应关系
	 * @return
	 * @throws Exception
	 */
	public void valrule()
	{
		String rulestr = Struts2Utils.getParameter("rulestr");
		
		String retstr = entityService.valrule(rulestr);
		
		Struts2Utils.renderText(retstr);
	}
	
	/**
	 * 经验校验规则
	 * 返回数学公式+校验规则数学公式对应关系字符串
	 * 格式：数学公式$校验规则数学公式对应关系
	 * @return
	 * @throws Exception
	 */
	public void jyvalrule()
	{
		String rulestr = Struts2Utils.getParameter("rulestr");
		
		String retstr = entityService.jyvalrule(rulestr);
		
		Struts2Utils.renderText(retstr);
	}
	//规则导入页面
	public String impRuleftl() {
		
		

		String proStr = (String) Struts2Utils.getSession().getAttribute(
				"promptInfo");
		if (StringUtils.isNotEmpty(proStr)) {
			Struts2Utils.getRequest().setAttribute("promptInfo",
					Struts2Utils.getSession().getAttribute("promptInfo"));
		}
		Struts2Utils.getSession().removeAttribute("promptInfo");
		
		return "impRuleftl";
	}
	
	/**
	 * 规则导入模板下载
	 */
	public String gzdrmbxz() throws Exception
	{
		try {
			String url = Struts2Utils.getSession().getServletContext()
					.getRealPath("/export");

			String scdz = "\\规则导入模板.xls";
			dzurl = url + scdz;
			File file = new File(url + scdz);
			if (!file.exists()) {
				Struts2Utils.setPromptInfo("下载文件不存在，请联系管理人员！");
				return impRuleftl();
			}
			wjname=new String("校验规则导入模板.xls".getBytes(),"ISO8859-1");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return "gzdrmb";
	}
	public String intoTopic()throws Exception{
		FileInputStream in=null;
		try {
			succont=0;   //正确条数
			errcont=0;   //错误条数
			in=new FileInputStream(drfj);
			POIFSFileSystem sf=new POIFSFileSystem(in);
			HSSFWorkbook book=new HSSFWorkbook(sf);
			HSSFSheet sheet=book.getSheetAt(0);         //获取第一个表单
			errList=new ArrayList<String>();          //错误信息集合
			//row=0为标题
			for(int row=1;row<=sheet.getLastRowNum();row++){  //row行
				//行为空跳至下一个
				
				HSSFRow rowObj = sheet.getRow(row);
				if(rowObj == null || rowObj.getCell(1) == null || StringUtils.isBlank(rowObj.getCell(1).toString())){
					continue;
				}
				
				boolean ok=true;
				
				//String bbh = null;
				//String bh  = null;
				
				ReportCheckRule ent = new ReportCheckRule();
				
				for(int column=0;column<5;column++){
					String value = null;
					//验证是否为空
					HSSFCell cell = rowObj.getCell(column);
					if(cell==null){
						if(column<=2&&StringUtils.isBlank(cell.toString().trim())){
							errList.add("第" + (row + 1) + "行，第" + (column + 1)+ "列，必添项未填");
						}
					}else{
						value=rowObj.getCell(column).toString().trim();
					}
					if(ok){
						//校验类型
						if(column==0){
							if(StringUtils.isBlank(value)){
								errList.add("第" + (row + 1) + "行，第" + (column + 1) + "列为空");
								ok = false;
							}else{
								if (value.length() > 50) {
									errList.add("第" + (row + 1) + "行，第" + (column + 1) + "列，长度不超过50个字符");
									ok = false;
								}
							}
							if(ok){
								if(value.equals("逻辑校验")){
									ent.setJylx("1");
								}else if (value.equals("经验校验")) {
									ent.setJylx("2");
								}else{
									errList.add("第" + (row + 1) + "行，第" + (column + 1) + "列，未找到您填写的校验类型");
									ok = false;
								}
								
							}
						}
						//源属
						if(column==1){
							if(StringUtils.isBlank(value)){
								errList.add("第" + (row + 1) + "行，第" + (column + 1) + "列为空");
								ok = false;
							}else{
								if (value.length() > 50) {
									errList.add("第" + (row + 1) + "行，第" + (column + 1) + "列，长度不超过50个字符");
									ok = false;
								}
							}
							
							if(ok){
								if(value.equals("教育部")){
									ent.setFenl("1");
								}else if (value.equals("市教委")) {
									ent.setFenl("2");
								}else if (value.equals("区教育局")) {
									ent.setFenl("3");
								}else if (value.equals("其他")) {
									ent.setFenl("4");
								}else{
									errList.add("第" + (row + 1) + "行，第" + (column + 1) + "列，未找到您填写源属");
									ok = false;
								}
							}
						}
						//逻辑校验规则
						if(column==2){
							if(StringUtils.isBlank(value)){
								errList.add("第" + (row + 1) + "行，第" + (column + 1) + "列为空");
								ok = false;
							}else{
								if (value.length() > 480) {
									errList.add("第" + (row + 1) + "行，第" + (column + 1) + "列，长度不超过480个字符");
									ok = false;
								}
							}
							if(ok){
								String jygz = value.toString();
								
								Pattern p = Pattern.compile("\\[.*?\\]");
						        Matcher m = p.matcher(jygz);
								
						        //特殊处理陈舔汇总教育局公式
//						        String gs = jygz;
//						        
//					        	while (m.find()){
//						            
//					        		String tabdm = m.group().substring(1, m.group().length()-1);
//					        		List<String>  gsList = Struts2Utils.split(tabdm, ".");
//					        		List<String>  ywxyList = Struts2Utils.split(gsList.get(1), ",");
//					        		
//					        		String ywy = ywxyList.get(1);
//					        		if(Integer.valueOf(ywy) < 10){
//					        			ywy = ywy.replace("0", "");
//					        		}
//					        		
//					        		gs = gs.replaceFirst(","+ywxyList.get(1), ","+ywy);
//					        		
//					        	}
//								
								
								String retstr = null;
								
								retstr = entityService.jyvalrule(jygz);
								
								try {
									
									List<String> tmpList = Struts2Utils.split(retstr, "$");
									ent.setJygzsxgs(tmpList.get(0));
									ent.setJygzsxgsdygx(tmpList.get(1));
									ent.setJygzhy(tmpList.get(2));
									ent.setJwtabs(tmpList.get(3));
									
									ent.setJygz(jygz);
								} catch (Exception e) {
									// TODO: handle exception
									errList.add("第" + (row + 1) + "行，第" + (column + 1) + "列，"+retstr);
									ok = false;
									e.printStackTrace();
								}
								
							}
						}
						//校验规则含义
						if(column==3){
							if( StringUtils.isNotBlank(value)){
								if (value.length() > 500) {
									errList.add("第" + (row + 1) + "行，第" + (column + 1) + "列，长度不超过500个字符");
									ok = false;
								}
								if(ok ){
									ent.setJygzhy(value.toString());
								}
							}
						}
						//涉及报表代码
						if(column==4){
							
							if( StringUtils.isNotBlank(value)){
								if (value.length() > 500) {
									errList.add("第" + (row + 1) + "行，第" + (column + 1) + "列，长度不超过200个字符");
									ok = false;
								}
								if(ok ){
									ent.setJwtabs(value.toString());
								}
							}
							
							
						}
						//备注
						if(column==5){
							 
							if (value.length() > 500) {
								errList.add("第" + (row + 1) + "行，第" + (column + 1) + "列，长度不超过500个字符");
								ok = false;
							}
							if(ok){
								ent.setBz(value.toString());
							}
						}
					 
					}
					
					
				}
				
				if (ok) {
					ent.setSfyx("1");
					entityService.save(ent);
					succont++;
				}else{
					errcont++;
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
				in.close();
			}
		}
		return "impRuleftl";
	}
	
	/**
	 * 下载模板公用方法
	 * @return
	 * @throws Exception
	 */
	public InputStream getDownloadFile() throws Exception
    {
		 File file = new File(dzurl);
		 InputStream is =null ;
		 if(file.exists()){
			 is= new FileInputStream(file); 
		 }
		 return is;  
    }
	
	@Autowired
	private ReportInfoService reportInfoService;
	
	public static  String zm = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
	
	public void test() {
		
		String sql = "  select * from checktbl";
		
		List tmpList= entityDao.getSession().createSQLQuery(sql).list();
		
		String errid = "";
		

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		
		
		for (int i = 0; i < tmpList.size(); i++) {
			
			
			Object[] objects = (Object[]) tmpList.get(i);
			String gslx = objects[4].toString();
			 
			String gs = objects[5].toString();
			//String error_info_type = objects[6].toString();
			String errinfo = objects[7].toString();
			String sfyx = objects[1].toString();
			
		
			Integer tabcount = Integer.valueOf(objects[10].toString());
			
			
			ReportCheckRule checkRule = new ReportCheckRule();
			if(gslx.equals("经验公式")){
				checkRule.setJylx("2");
			}else if (gslx.equals("逻辑公式")) {
				checkRule.setJylx("1");
			}
			
			if(sfyx.equals("T")){
				checkRule.setSfyx("1");
			}else if (sfyx.equals("F")) {
				checkRule.setSfyx("0");
			}
			
			checkRule.setFenl("2");
			

			 gs = gs.replaceAll("\\[", "").replaceAll("\\]", "");
			
			//if(error_info_type.equals("T")){//不包含学校信息表字段
				
				Boolean flag= false;
				for (int j = 0; j < objects.length;j++) {
					
					if(j>10){
						
						
						String tab1 = null;
						if(objects[j]!=null){
							tab1 = objects[j].toString();
						}else{
							break;
						}
						j=j+1;
						
						String tab1did = null;
						if(objects[j]!=null){
							tab1did = objects[j].toString();
						}
						
						j=j+1;
						String tab1name = null;
						if(objects[j]!=null){
							tab1name = objects[j].toString();
						}
						
						
						//System.out.println(objects[0].toString()+"=="+tab1 + "==" + tab1did + "==" + tab1name);
						
						//查询出在数据库中对应的实际列
						String rptName = tab1.replaceAll("lastOther.", "").replaceAll("last.", "").replaceAll("other.", "").replaceAll("other", "");
						if(StringUtils.isNotEmpty(tab1did ) && objects[8].toString().equals("F") ){
							String x = getxrptid(rptName,tab1did);
							
							
							
							if( StringUtils.isNotEmpty(x)){//
								

								ReportInfo reportInfo = new ReportInfo();
								checkRule.setReportinfo(reportInfo);
								
								
								List<String> zm =Struts2Utils.split(ReportCheckRuleAction.zm, ","); 
								
								
								for(int s = 0;s<=zm.size()-1;s++){
									
									String ywxy = getywxy( x, String.valueOf((s+1)), rptName);
										
									String[] ywxyarr = ywxy.split(",");
									
									if(ywxyarr != null && ywxyarr.length > 1){
										
										gs = gs.replace(tab1+tab1name+"."+zm.get(s), "["+tab1+"."+ywxyarr[0]+","+ywxyarr[1]+"]");
										   
										errinfo = errinfo.replace(tab1+tab1name+"."+zm.get(s), tab1+"."+ywxyarr[0]+","+ywxyarr[1]);
										
										System.out.println(tab1+tab1name+"."+zm.get(s) + "==>" + "["+tab1+"."+ywxyarr[0]+","+ywxyarr[1]+"]");
										
									}
										
										
									
								  
								}
								
								flag = true;
								
								
							}
//							else{
//								flag = false;
//								break;
//							}
						}
						
						
						
					}
					
				}
				
				//去数据库查询是否有这张表，没有则不保存
				if(flag){   
					Pattern p = Pattern.compile("\\[.*?\\]");
			        Matcher m = p.matcher(gs);
			        
		        	while (m.find()){
			            
			            if(m.group().indexOf("schoolAllInfo") == 0 && m.group().indexOf("YJ1001") == 0 && m.group().indexOf("YJ1102") == 0){
			            	
			            	List<String> dm = Struts2Utils.split(m.group().replaceAll("other.", ""), ".");
			        		String dmstr = dm.get(0).replaceAll("\\[", "").replaceAll("\\]", "");
			        		List aa = entityDao.getSession().createSQLQuery("  select * from t_config_reportinfo  where dm = '"+dmstr+"' ").list();
				            if(aa!=null && aa.size() > 0){
				            	
				            }else{
				            	flag = false;
				            	break;
				            }
			            	
			            }
			            
			        }
				}
				
				if(flag ){
					
					//gs = gs.replaceAll("\\[", "").replaceAll("\\]", "");
					//gs = gs.replaceAll("(?i)abs","math.abs");
					gs = gs.replaceAll("Other.", "");
					gs = gs.replaceAll("other.", "");
					//gs = gs.replaceAll("<>", "!=");
					checkRule.setJygz(gs);
					checkRule.setReportinfo(null);;
					checkRule.setJygzhy(errinfo);
					
					//校验公式
					String  ywhystr = entityService.jyvalrule(gs);
					if(StringUtils.isNotEmpty(ywhystr) && gs.indexOf("YJ4354") == -1){
						
						
						try {
							List<String> tmpList2 = Struts2Utils.split(ywhystr, "$");
							
							
							checkRule.setJygzsxgs(tmpList2.get(0));
							checkRule.setJygzsxgsdygx(tmpList2.get(1));
							//checkRule.setJygzhy(tmpList2.get(2));
							checkRule.setJwtabs(tmpList2.get(3));
							
							checkRule.setCjsj(sdf.format(now));
							
							entityService.save(checkRule);
							
						} catch (Exception e) {
							// TODO: handle exception
							errid += "'"+objects[0].toString() + "',";
						}
						
					}else{
						
						errid += "'"+objects[0].toString() + "',";
						
					}
					
					
				}else{
					
					errid += "'"+objects[0].toString() + "',";
				}
				
				
			//} 
 			
			
		}
		
		System.out.println(errid);
		
	}
	
	public String getxrptid(String rptName,String tab1did) {
		
		String x = null;
		List<ReportInfo> rptList = reportInfoService.findbyhql(" from ReportInfo a where a.dm = ?",rptName);
		if(rptList != null && rptList.size() > 0){
			if(rptList.get(0).getReptTable() != null){
				Document document = Jsoup.parse(rptList.get(0).getReptTable());
				
				if(document.select("td[ifjzcell=1]") != null && document.select("td[ifjzcell=1]").size() > 0){
					Element jzelmtlst = document.select("td[ifjzcell=1]").get(0);
					String jzx = jzelmtlst.attr("x");
					
					x = String.valueOf(Integer.valueOf(tab1did) + Integer.valueOf(jzx));
				}
				
			}
			
		}
		
		return x;
	}
	//根据x,y得到 ywx，ywy
	public String getywxy(String x,String y,String tabdm) {
		
		String xy = "";
		
		List<ReportInfo> rptList = reportInfoService.findbyhql(" from ReportInfo a where a.dm = ?",tabdm);
		
		if(rptList != null && rptList.size() > 0){
			if(rptList.get(0).getReptTable() != null){
				Document document = Jsoup.parse(rptList.get(0).getReptTable());
				
				if(document.select("td[ifjzcell=1]") != null && document.select("td[ifjzcell=1]").size() > 0){
					Elements jzelmtlst = document.select("td[id=td_"+x+"_"+y+"]");
					if(jzelmtlst != null && jzelmtlst.size() > 0){
						
						String ywx = jzelmtlst.get(0).attr("ywx");
						String ywy = jzelmtlst.get(0).attr("ywy");
						xy = ywx + "," + ywy;
						
					}
					
				}
				
			}
			
		}
		
		
		return xy;
		
	}
	
	public static void main(String[] args) {
			String a="Z";
		    char c = a.charAt(0);
		   System.out.println(c  - 'A'+ 1);
	}
	public void setsfyx() {
		String sfyx = Struts2Utils.getParameter("sfyx");
		String idstr = Struts2Utils.getParameter("idstr");
		String mesg = "设置成功！";
		if(StringUtils.isNotEmpty(sfyx) && StringUtils.isNotEmpty(idstr)){
			
			try {
				List<PropertyFilter> filters = new ArrayList<>();
				filters.add(new PropertyFilter("INC_id",Struts2Utils.split(idstr, ",")));
				
				List<ReportCheckRule> rpt = entityService.find(filters);
				for (ReportCheckRule rptrule : rpt) {
					
					ReportCheckRule tmpRule = rptrule;
					rptrule.setSfyx(sfyx);
					entityService.update(tmpRule);
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
				mesg = "设置失败！";
				e.printStackTrace();
			}
			
			
		}else{
			mesg = "参数错误！";
		}
		Struts2Utils.renderText(mesg);
		
	}
	
	
}
