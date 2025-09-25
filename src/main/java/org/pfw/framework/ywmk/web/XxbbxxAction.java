package org.pfw.framework.ywmk.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pfw.framework.app.util.HttpRequestUtil;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.util.PFWSecurityUtils;
import org.pfw.framework.ywmk.dao.BbqzpzDao;
import org.pfw.framework.ywmk.dao.DwpxqkDao;
import org.pfw.framework.ywmk.dao.XxbbxxDao;
import org.pfw.framework.ywmk.domain.Sjrw;
import org.pfw.framework.ywmk.domain.Xxbbxx;
import org.pfw.framework.ywmk.domain.Xxxx;
import org.pfw.framework.ywmk.service.SjrwService;
import org.pfw.framework.ywmk.service.XxbbxxService;
import org.pfw.framework.ywmk.service.XxxxService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

/**
 * 功能管理Action.
 */
@Results
	({ 
		@Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl" , type="freemarker"),
		@Result(name ="layerForparent",location="/util/winopenlayerForparent.ftl", type = "freemarker"),
		@Result(name="success",location="/ywmk/xxbbxx-list.ftl",type = "freemarker"),
		@Result(name="bbxxlist",location="/ywmk/xxbbxx-bbxxlist.ftl",type = "freemarker"),
		@Result(name="yulan",location="/ywmk/xxbbxx-yulan.ftl",type = "freemarker"),
		@Result(name="ckmb",location="/ywmk/xxbbxx-ckmb.ftl",type = "freemarker"),
		@Result(name="vietab",location="/ywmk/xxbbxx-vietab.ftl",type = "freemarker"),
		@Result(name="sdtzbb",location="/ywmk/xxbbxx-sdtzbb.ftl",type = "freemarker"),
		@Result(name ="input",location="/ywmk/xxbbxx-input.ftl", type = "freemarker"),
		@Result(name="down",type="stream",
		params={"contentType","application/octet-stream;charset=utf-8","contentDisposition","attachment;filename=${wjname}","inputName","downloadFile","bufferSize","102410"}
		)
	})
public class XxbbxxAction extends CrudActionSupport<Xxbbxx> {
	@Autowired
	protected XxbbxxService entityService;
	@Autowired
	private XxxxService xxxxService;
	@Autowired
	private BbqzpzDao bbqzpzDao;
	@Autowired
	private XxbbxxDao xxbbxxDao;
	@Autowired
	private SjrwService sjrwService;
	@Autowired
	private DwpxqkDao dwpxqkDao;
	
	
	
	private static String gjbbServerId = "http://localhost:8080/gjbb";
	
	private List reqlist;
	private String jsonstr;
	private String sjrwid;
	
	public String getSjrwid() {
		return sjrwid;
	}
	public void setSjrwid(String sjrwid) {
		this.sjrwid = sjrwid;
	}
	public String getJsonstr() {
		return jsonstr;
	}
	public void setJsonstr(String jsonstr) {
		this.jsonstr = jsonstr;
	}
	public List getReqlist() {
		return reqlist;
	}
	public void setReqlist(List reqlist) {
		this.reqlist = reqlist;
	}
	
	public String ckmb() throws Exception {
		prepareModel();
		return "ckmb";
	}
	
	public String vietab() throws Exception {
		prepareModel();
		return "vietab";
	}
	
	public String sdtzbb() throws Exception {
		prepareModel();
		return "sdtzbb";
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
	
	@Override
	public String list() throws Exception {
		sjrwid = Struts2Utils.getParameter("sjrwid");
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		filters.add(new PropertyFilter("EQS_sjrw.id",sjrwid));
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		page = entityService.findPage(page, filters);
		
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (!StringUtils.isEmpty(id)) {
			entity = (Xxbbxx) entityService.getById(id);
		} else {
			entity = new Xxbbxx();
		}
		
	}

	@Override
	public String save() throws Exception {
		if (!StringUtils.isEmpty(id))
		{
			entityService.update(entity);
		}else{
			entityService.save(entity);
		}
		Struts2Utils.setPromptInfoToReq("保存成功!!");
		
		return RELOAD;
	}

	public static  String[] zm = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	/**
	 * 一键生成报表
	 * @throws IOException 
	 * **/
	public void yjsc() throws IOException {
		List<Xxxx> xxxList = xxxxService.findAll();
		List<Xxbbxx> xxList1 = entityService.findbyhql(" from Xxbbxx xx where xx.sjrw.id = '"+sjrwid+"'  ");
		if(xxList1 == null || xxList1.size() == 0){
			Struts2Utils.renderText("请先生成模板后生成报表！");
			return;
		}
		
		try {
			Sjrw sjrw = sjrwService.getById(sjrwid);
			String sjnf = sjrw.getNianf();
			if(xxxList != null && xxxList.size() >0 ){
				Xxxx xxxx = xxxList.get(0);
				//1.配置信息准备
//				List<Bbqzpz> bbqzpzList =  bbqzpzDao.find(" from Bbqzpz a where a.bxlx.id in ("+bxlxstr+") and a.sfyx = '1' ");
			
				String url = gjbbServerId+"/ywmk/gjbbapi!getbbpzxxByxxid.action";
				Map<String,Object> paramp = new HashMap<>();
				paramp = new HashMap<>();
				paramp.put("xxdm", xxxx.getXxdm());
				jsonstr = HttpRequestUtil.doPost(url, paramp);
				JSONObject jsonObject = JSONObject.parseObject(jsonstr);
				
				if ("200".equals(jsonObject.getString("code"))) {//请求成功
					
					String pzxxjson = jsonObject.getString("data");
					JSONArray jsonArray = new JSONArray(pzxxjson);
					for (int xx = 0; xx < jsonArray.length(); xx++) {
						org.json.JSONObject obj = jsonArray.getJSONObject(xx); 
						
				      
						org.json.JSONObject rptjsonobj = obj.getJSONObject("reportInfo");
				      
						String reportcode = rptjsonobj.getString("reportcode");//表号
						String reportname = rptjsonobj.getString("reportname");//表名
						String qzlx = obj.getString("qzlx");//取值类型
						String sqlpz = obj.getString("sqlpz").replaceAll("<br>", " ");//sql配置
						//找到对应年份数据
				        sqlpz = sqlpz.replaceAll("\\%\\[sjnf\\]\\%","%"+sjnf+"%");
				        sqlpz = sqlpz.replaceAll("\\[sjnf\\]", "'"+sjnf+"'");
				        if(sqlpz.indexOf("delete") > -1 || sqlpz.indexOf("update")  > -1  || sqlpz.indexOf("drop")  > -1 ){
				        	throw new IOException("参数异常！");  
				        }
						String hdfw = obj.getString("hdfw");//回调服务
						
						
						List<Xxbbxx> xxList = entityService.findbyhql(" from Xxbbxx xx where xx.sjrw.id = '"+sjrwid+"' and xx.reportcode = '"+reportcode+"' ");
					
						if(xxList != null && xxList.size() > 0){
							Xxbbxx xxbbxx = xxList.get(0);
							
							String tabhtml = xxbbxx.getRptTable();
							Document document = Jsoup.parse(tabhtml);
							
							if(qzlx.equals("1")){//sql取值
								System.out.println(sqlpz);
								//数据源
								List tmpList =   tmpList = xxbbxxDao.getSession().createSQLQuery(sqlpz).list();
								
								
								
								if(StringUtils.isNotEmpty(tabhtml) && tmpList != null && tmpList.size() > 0){
									if(reportcode.equals("教基3221续")){//特殊自定义报表
										
										String _tr ="";

										for (int i = 0; i < tmpList.size(); i++) {

											Object[]  obj1 = (Object[]) tmpList.get(i);
											
											_tr += "<tr class='skjhb' style='font-size:10px' tit='"+i+"'>";
													
											for (int j = 0; j < 16; j++) {
												String val = obj1[j].toString().replace(".0", "");
												_tr +="<td typ='numb' y='"+(j+1)+"' x='"+(5+i)+"' id='td_"+(5+i)+"_1'>"
													+"	<input type='text' class='inputNumcssFormula' name='jj3221xu["+i+"]."+zm[j]+"' value='"+val+"' maxlength='100'> "
													+"</td> ";
											}		
											_tr +="</tr>";
 
										}

										document.select("tbody").append(_tr);
										
									
									} else if(reportcode.equals("教基8389")){//特殊自定义报表
										String _tr ="";
										for (int i = 0; i < tmpList.size(); i++) {

											Object[]  obj1 = (Object[]) tmpList.get(i);

											_tr += "<tr class='skjhb' style='font-size:10px' tit='"+i+"'>";

											for (int j = 0; j < 18; j++) {
												String strh = "";
												String val = obj1[j].toString();

												_tr +="<td typ='numb' y='"+(j+1)+"' x='"+(4+i)+"' id='td_"+(4+i)+"_1'>"
														+"	<input type='text' class='inputNumcssFormula' name='jj8389["+i+"]."+zm[j]+"' value='"+val+"' maxlength='100'> "
														+"</td> ";
											}
											_tr +="</tr>";
										}
										document.select("tbody").append(_tr);
									
									}else{
										
										Elements jzelmtlst = document.select("td[ifjzcell=1]");
										if(jzelmtlst != null && jzelmtlst.size() > 0){
											String x = jzelmtlst.get(0).attr("x");
											String y = jzelmtlst.get(0).attr("y");
											
											Elements trlst = document.select("tr");
											int flag = 0;
											for (int i = 0; i < trlst.size(); i++) {
												String style = trlst.get(i).attr("style");
//												    if (style.contains("display: none")) {//被隐藏就跳过赋值
//												        break;
//												    }
												
												Elements tdlst = trlst.get(i).select("td");
												if(tdlst != null && tdlst.size() > 0 && !style.contains("display: none")){
													
													String flagtdx = tdlst.last().attr("x");
													Object[] objects = null;
													if(Integer.valueOf(flagtdx) >  Integer.valueOf(x)){
														objects = (Object[]) tmpList.get(flag);
														flag++;
													}
													int flag2 = 0;
													for (int j = 0; j < tdlst.size(); j++) {
														
														String tdx = tdlst.get(j).attr("x");
														String tdy = tdlst.get(j).attr("y");
														if(Integer.valueOf(tdx) >  Integer.valueOf(x)  &&  Integer.valueOf(tdy) >  Integer.valueOf(y)){
		
															String tdtype = tdlst.get(j).attr("typ");
															if(!"unwirte".equals(tdtype)){
//																Elements intelemt = tdlst.get(j).select("input");
//																if(intelemt != null && intelemt.size() > 0){
//																	intelemt.get(0).val(objects[flag2].toString().replace(".0", ""));
//																}
																tdlst.get(j).html(objects[flag2].toString().replace(".0", ""));
																
															}
															flag2 ++ ;
														}
													}
												
												}
											} 
										}
										
									}
									
								}
								
							}else if(qzlx.equals("2")){//回调服务取值
								String[] tmparr = hdfw.split(",");//0下标  服务名    1下标  方法名
										
//								List<String> tmpStr = (List<String>) Struts2Utils.Invoke(tmparr[0],	tmparr[1], new String[] { id, "2" ,null});
								List<String> servertmpList = (List<String>) Struts2Utils.Invoke(tmparr[0],	tmparr[1], new String[] {sjnf});
								
								Elements jzelmtlst = document.select("td[ifjzcell=1]");
								if(jzelmtlst != null && jzelmtlst.size() > 0){
									String x = jzelmtlst.get(0).attr("x");
									String y = jzelmtlst.get(0).attr("y");
									
									Elements trlst = document.select("tr");
									int flag = 0;
									for (int i = 0; i < trlst.size(); i++) {
										String style = trlst.get(i).attr("style"); 
										
										Elements tdlst = trlst.get(i).select("td");
										if(tdlst != null && tdlst.size() > 0 && !style.contains("display: none")){
											
											String flagtdx = tdlst.last().attr("x");
											List<String> objects = null;
											if(Integer.valueOf(flagtdx) >  Integer.valueOf(x)){
												objects =Struts2Utils.split(servertmpList.get(flag), ",") ;
												flag++;
											}
											int flag2 = 0;
											for (int j = 0; j < tdlst.size(); j++) {
												
												String tdx = tdlst.get(j).attr("x");
												String tdy = tdlst.get(j).attr("y");
												if(Integer.valueOf(tdx) >  Integer.valueOf(x)  &&  Integer.valueOf(tdy) >  Integer.valueOf(y)){

													String tdtype = tdlst.get(j).attr("typ");
													System.out.println(objects.get(flag2));
													if(!"unwirte".equals(tdtype)){
														tdlst.get(j).html(objects.get(flag2).replace(".0", ""));
													}
													flag2 ++ ;
												}
											}
										}
									}
								}
							}
								xxbbxx.setRptTableRes(document.toString());
								entityService.update(xxbbxx);
							}
							
						}
				}else{
					Struts2Utils.renderText("生成失败,请联系系统管理员！");
					return;
				}
				 
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Struts2Utils.renderText("生成失败,请联系系统管理员！");
			return;
		}
		Struts2Utils.renderText("生成成功！");
		
	}
	public String setToString(Set<String> set) {
		StringBuilder sb = new StringBuilder();
		for (String s : set) {
			sb.append(s).append(",");
		}
		return sb.toString().substring(0, sb.length() - 1);
	}
	/**
	 * 模板生成
	 * **/
	public void mbsc() {
		List<Xxxx> xxxList = xxxxService.findAll();
		try {
			entityService.deleteHql(" delete  from Xxbbxx a where a.sjrw.id ='"+sjrwid+"' ");
			
			if(xxxList != null && xxxList.size() >0 ){
				Xxxx xxxx = xxxList.get(0);
				String url = gjbbServerId+"/ywmk/gjbbapi!getReptTabByxxdm.action";
				Map<String,Object> paramp = new HashMap<>();
				paramp = new HashMap<>();
				paramp.put("xxdm", xxxx.getXxdm());
				String jsonstr = HttpRequestUtil.doPost(url, paramp);
				JSONObject jsonObject = JSONObject.parseObject(jsonstr);
				if ("200".equals(jsonObject.getString("code"))) {
					String tablhtml = jsonObject.getString("data");
					JSONArray jsonArray = new JSONArray(tablhtml);
					for (int i = 0; i < jsonArray.length(); i++) {
				      org.json.JSONObject obj = jsonArray.getJSONObject(i); 
				      Xxbbxx xxbbxx = new Xxbbxx();
				      xxbbxx.setRptTable(obj.getString("reptTable").replaceAll("<br>", ""));
				      xxbbxx.setReportcode(obj.getString("reportcode"));
				      xxbbxx.setReportname(obj.getString("reportname"));
				      if(obj.has("columnNumber")){
				    	  xxbbxx.setColumnNumber(Integer.valueOf(obj.getString("columnNumber")));
				      }
				      if(obj.has("yxh")){
					      xxbbxx.setYxh(numorder(obj.getString("yxh")));
				      }
				      Sjrw sjrw = new Sjrw();
				      sjrw.setId(sjrwid);
				      xxbbxx.setSjrw(sjrw);
				      entityService.save(xxbbxx);
				    }
					Struts2Utils.renderText("生成成功！");
				}else{
					Struts2Utils.renderText("发生错误,生成失败,请联系系统管理员！");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Struts2Utils.renderText("发生错误,生成失败,请联系系统管理员！");
		}
		
	}
	public String numorder(String input) {
		String[] elements = input.split(",");
		Arrays.sort(elements);
		StringBuilder sb = new StringBuilder();
		for (String element : elements) {
		    sb.append(element).append(",");
		}
		String output = sb.substring(0, sb.length() - 1);
		return output;
	}
	//报表信息页面
	public String bbxxlist() {
		List<Xxxx> xxxList = xxxxService.findAll();
		if(xxxList != null && xxxList.size() >0 ){
			Xxxx xxxx = xxxList.get(0);
			String url = gjbbServerId+"/ywmk/gjbbapi!getReptByxxdm.action";
			Map<String,Object> paramp = new HashMap<>();
			paramp = new HashMap<>();
			paramp.put("xxdm", xxxx.getXxdm());
			jsonstr = HttpRequestUtil.doPost(url, paramp);
		}
		
		return "bbxxlist";
	}
	//报表信息预览页面
	public String yulan() {
		String idstr = Struts2Utils.getParameter("idstr");
		if(StringUtils.isNotEmpty(idstr)){
			String url = gjbbServerId+"/ywmk/gjbbapi!getRptTabByid.action";
			Map<String,Object> paramp = new HashMap<>();
			paramp = new HashMap<>();
			paramp.put("idstr", idstr);
			jsonstr = HttpRequestUtil.doPost(url, paramp);
		}
		return "yulan";
	}
	private String dzurl;
	private String wjname;
	
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
	//导出到excel并压缩
	public String exportexcel() throws IOException {
		String sjrwid = Struts2Utils.getParameter("sjrwid");
		if(StringUtils.isNotEmpty(sjrwid)){
			
			String xxmc = "null";
			List<Xxxx> xxxList = xxxxService.findAll();
			if(xxxList != null && xxxList.size() >0 ){
				xxmc = xxxList.get(0).getXxmc();
			}
			
			List<Xxbbxx> xxbbxxList = entityService.findbyhql(" from Xxbbxx a where a.sjrw.id = '"+sjrwid+"' ");
			if(xxbbxxList != null && xxbbxxList.size() > 0){
				
				String serverPath = Struts2Utils.getRequest().getSession().getServletContext().getRealPath("/");
				//在服务器端创建文件夹  
			    File file = new File(serverPath + "/userfiles/xxbbdc"+PFWSecurityUtils.getCurrentUserName());
			    if(!file.exists())
			    {
			    	file.mkdir();  
			    }else{
			    	File[] lstfiletmp = file.listFiles();
			    	for (File fltmp : lstfiletmp){
			    		fltmp.delete();
			    	}
			    }
				
				for (Xxbbxx xxbbxx : xxbbxxList) {
					
					if(StringUtils.isNotEmpty(xxbbxx.getRptTableRes())){
						
						String rptcode = xxbbxx.getReportcode();
						OutputStream output=null;
						HSSFWorkbook wb = new HSSFWorkbook();
						
						HSSFSheet sheet = wb.createSheet(rptcode);
						
						dcbb(xxbbxx.getRptTableRes(),sheet,xxbbxx,wb,xxbbxx.getSjrw().getNianf());
						
						try {
							output=Struts2Utils.getResponse().getOutputStream();
							Struts2Utils.getResponse().reset();
							String fname = rptcode + ".xls";
							String newfPath = serverPath + "/userfiles/xxbbdc"+PFWSecurityUtils.getCurrentUserName() +"/"+  fname;
							output = new FileOutputStream(new File(newfPath)); 
							wb.setForceFormulaRecalculation(true);
				            wb.write(output);
							
						} catch (Exception e) 
						{
							e.printStackTrace();
						}finally{
							output.flush();
							output.close();
						}
					}
					
				}
				
				
				File xxbbzipFile = new File(serverPath + "/userfiles/xxbbdczip"+PFWSecurityUtils.getCurrentUserName());
				if (!xxbbzipFile.exists()) {
					xxbbzipFile.mkdirs();
				}
				
				//进行打包导出
				File zpfile = null;
				zpfile = new File(serverPath + "/userfiles/xxbbdczip"+PFWSecurityUtils.getCurrentUserName()+"/"+xxmc+".zip");
				if(zpfile.exists())
				{
					zpfile.delete();
				}
				//找到要打包的文件夹
				File file2 = new File(serverPath + "/userfiles/xxbbdc"+PFWSecurityUtils.getCurrentUserName());
			    File[] lstfile2 = file2.listFiles();
				
				ZipArchiveOutputStream zaos = null; 
				
				 try 
					{
					 	//创建zip文件
						File zipFile = new File(serverPath + "/userfiles/xxbbdczip"+PFWSecurityUtils.getCurrentUserName()+"/"+xxmc+".zip");
		                zaos = new ZipArchiveOutputStream(zipFile);  
						
		                zaos.setUseZip64(Zip64Mode.AsNeeded);
		                
		                for (File fltmp : lstfile2) 
						{
		                	if(fltmp != null)
		                	{
		                		ZipArchiveEntry zipArchiveEntry  = new ZipArchiveEntry(fltmp,fltmp.getName());  
		                		zaos.putArchiveEntry(zipArchiveEntry); 
		                		InputStream is = null;  
		                		try {  
		                            is = new BufferedInputStream(new FileInputStream(fltmp));  
		                            byte[] buffer = new byte[1024 * 5];    
		                            int len = -1;  
		                            while((len = is.read(buffer)) != -1) {  
		                                zaos.write(buffer, 0, len);  
		                            }  
		                            zaos.closeArchiveEntry();    
		                        }catch(Exception e) {  
		                            throw new RuntimeException(e);  
		                        }finally {  
		                            if(is != null)   
		                                is.close();  
		                        }  
		                	}
						}
		                
		                zaos.finish();  
		                
						System.out.println("压缩完成.");
						
						zaos.close(); 
						
					} catch (Exception e) {
						e.printStackTrace();
					}finally {  
		                try {  
		                    if(zaos != null) 
		                    {
		                    	zaos.flush();
		                        zaos.close();  
		                    }  
		                } catch (IOException e) {  
		                    throw new RuntimeException(e);  
		                }  
		            }
					
					if(zpfile.exists())
					{
						dzurl = serverPath + "/userfiles/xxbbdczip"+PFWSecurityUtils.getCurrentUserName()+"/"+xxmc+".zip";//在getDownloadFile中用到
						wjname = new String((xxmc+".zip").getBytes(),"ISO8859-1");
						
					    return "down";
					}
				
				
			}
		}
		return null;
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
	//导出方法封装
	public void dcbb(String reptTable,HSSFSheet sheet,Xxbbxx entity,HSSFWorkbook wb,String nianf) {
		
		//基准样式
		
		HSSFCellStyle titleStyle = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		font.setFontName("仿宋_GB2312");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
		font.setFontHeightInPoints((short) 16);
		titleStyle.setFont(font);//选择需要用到的字体格式
		titleStyle.setWrapText(true);//自动换行
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		
		
		//基准样式
		HSSFCellStyle basestyle = wb.createCellStyle();  
		basestyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框  
		basestyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框  
		basestyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框  
		basestyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框  
		basestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		
		
		HSSFRow titrow = sheet.createRow(0);
		titrow.setHeight((short)800);
	    HSSFCell celltmp2 = titrow.createCell(0);
	    celltmp2.setCellValue(entity.getReportname());
	    celltmp2.setCellStyle(titleStyle);
	    //合并excel第一行（标题）
    	sheet.addMergedRegion(new CellRangeAddress(0,0,0,entity.getColumnNumber()-1));
    	//全局宽度设置
    	sheet.setDefaultRowHeight((short)500);
    	
		Document document = Jsoup.parse(reptTable);
		
		Elements tdlst = document.select("td[id^=td_]");
		
		if(tdlst != null && tdlst.size() > 0)
		{	
			for(Element tdelemt : tdlst)
			{
				String tdtyp = tdelemt.attr("typ");
				
				int zb_x = Integer.parseInt(tdelemt.attr("x"))+1;//坐标x
				int zb_y = Integer.parseInt(tdelemt.attr("y"));//坐标y
				
				if(StringUtils.isNotEmpty(tdtyp))
				{
					//带typ属性的为输入单元格
					Elements intelemt = tdelemt.select("input");
					Elements selectemlt = tdelemt.select("select");
					if(intelemt != null && intelemt.size() > 0)
					{
						
						Element inputele = intelemt.get(0);
						String valtmp = inputele.val();
						
						HSSFRow rowtmp;
						if(sheet.getRow(zb_x) == null)
						{
							rowtmp = sheet.createRow(zb_x);
						}else
						{
							rowtmp = sheet.getRow(zb_x);
						}
						HSSFCell celltmp;
						if(rowtmp.getCell(zb_y-1) == null)
						{
							celltmp = rowtmp.createCell(zb_y-1);
						}else
						{
							celltmp = rowtmp.getCell(zb_y-1);
						}
						celltmp.setCellStyle(basestyle);
						celltmp.setCellValue(valtmp);
					}else if (selectemlt != null && selectemlt.size() > 0) {
						Element inputele = selectemlt.get(0);
						String valtmp = "";
						for (Element element : inputele.children().select("option")) {
							if(element.toString().indexOf("selected") > -1){
								valtmp = element.text();
							}
						}
						
						HSSFRow rowtmp;
						if(sheet.getRow(zb_x) == null)
						{
							rowtmp = sheet.createRow(zb_x);
						}else
						{
							rowtmp = sheet.getRow(zb_x);
						}
						HSSFCell celltmp;
						if(rowtmp.getCell(zb_y-1) == null)
						{
							celltmp = rowtmp.createCell(zb_y-1);
						}else
						{
							celltmp = rowtmp.getCell(zb_y-1);
						}
						celltmp.setCellStyle(basestyle);
						celltmp.setCellValue(valtmp);
					}else{//没有input说明非必填
						
						String btstr = tdelemt.html();//单元格标题
						btstr = btstr.replaceAll("&nbsp;", " ");
						

						String style = tdelemt.parent().attr("style");
						
						HSSFRow rowtmp;
						if(sheet.getRow(zb_x) == null)
						{
							rowtmp = sheet.createRow(zb_x);
						}else
						{
							rowtmp = sheet.getRow(zb_x);
						}
						HSSFCell celltmp;
						if(rowtmp.getCell(zb_y-1) == null)
						{
							celltmp = rowtmp.createCell(zb_y-1);
						}else
						{
							celltmp = rowtmp.getCell(zb_y-1);
						}
						celltmp.setCellStyle(basestyle);
						if (!style.contains("display: none")) {//被隐藏就跳过赋值
							celltmp.setCellValue(btstr);
					    }else{
					    	rowtmp.setZeroHeight(true); 
					    }
						
					}
				}else
				{
					//没有带typ属性的为标题单元格
					String btstr = "";//单元格标题
					
					if(tdelemt.children() != null && tdelemt.children().size() > 0)
					{
						btstr = tdelemt.child(0).html();
					}else{
						btstr = tdelemt.html();//单元格标题
					}
					String style = tdelemt.parent().attr("style");
					btstr = btstr.replaceAll("&nbsp;", " ");
					
					HSSFRow rowtmp;
					if(sheet.getRow(zb_x) == null)
					{
						rowtmp = sheet.createRow(zb_x);
					}else
					{
						rowtmp = sheet.getRow(zb_x);
					}
					HSSFCell celltmp;
					if(rowtmp.getCell(zb_y-1) == null)
					{
						celltmp = rowtmp.createCell(zb_y-1);
					}else
					{
						celltmp = rowtmp.getCell(zb_y-1);
					}
					celltmp.setCellStyle(basestyle);
					if (!style.contains("display: none")) {//被隐藏就跳过赋值
						celltmp.setCellValue(btstr);
				    }else{
				    	rowtmp.setZeroHeight(true); 
				    }
					
				}
				
 
			}
		}
		
		Elements rowtdlst = document.select("td[rowspan]");
		if(rowtdlst != null && rowtdlst.size() > 0)
		{
			//考虑有行合并的情况
			for(Element elttmp : rowtdlst)
			{
				int zb_x = Integer.parseInt(elttmp.attr("x"))+1;//坐标x
				int zb_y = Integer.parseInt(elttmp.attr("y"));//坐标y
				String rowspanstr = elttmp.attr("rowspan");
				if(StringUtils.isNotEmpty(rowspanstr))
				{
					int rowspanint = Integer.parseInt(rowspanstr);
					int endrow = zb_x + rowspanint - 1;
					
					CellRangeAddress cra = new CellRangeAddress(zb_x,endrow,zb_y-1,zb_y-1);//合并区域
					HSSFCell celltmp = sheet.getRow(zb_x).getCell(zb_y-1);
					celltmp.setCellStyle(basestyle);
					RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cra, sheet,wb); // 下边框
					RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, cra, sheet,wb); // 左边框
					RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, cra, sheet,wb); // 有边框
					RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, cra, sheet,wb); // 上边框
					sheet.addMergedRegion(cra);
				}
			}
		}
		
		Elements coltdlst = document.select("td[colspan]");
		if(coltdlst != null && coltdlst.size() > 0)
		{
			//考虑有列合并的情况
			for(Element elttmp : coltdlst)
			{
				int zb_x = Integer.parseInt(elttmp.attr("x"))+1;//坐标x
				int zb_y = Integer.parseInt(elttmp.attr("y"));//坐标y
				String colspanstr = elttmp.attr("colspan");
				if(StringUtils.isNotEmpty(colspanstr))
				{
					int colspanint = Integer.parseInt(colspanstr);
					int endcol = (zb_y - 1) + colspanint - 1;
					
					CellRangeAddress cra = new CellRangeAddress(zb_x,zb_x,zb_y-1,endcol); //合并区域
					HSSFCell celltmp = sheet.getRow(zb_x).getCell(zb_y-1);
					celltmp.setCellStyle(basestyle);
					RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cra, sheet,wb); // 下边框
					RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, cra, sheet,wb); // 左边框
					RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, cra, sheet,wb); // 有边框
					RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, cra, sheet,wb); // 上边框
					sheet.addMergedRegion(cra);
					
				}
			}
		}
		//HSSFRow rowObj = sheet.getRow(20);
		//sheet.removeRow(rowObj);
		 //遍历所有列  隐藏空白行
		/**
		Integer sum = sheet.getLastRowNum();
		for(int row=0;row<=sum;row++){  //row行
			
		    HSSFRow rowObj = sheet.getRow(row);
			
			if (rowObj == null || rowObj.equals("")  || rowObj.getCell(0) == null) {
				rowObj = sheet.createRow(row);
				rowObj.setZeroHeight(true); 
			}
		}
		
		//从第二行复制下移一行  然后把表号加上 
		Integer sum1 = sheet.getLastRowNum();
		sheet.shiftRows(1, sum1, +1);
		**/
		HSSFRow row1 = sheet.createRow(1);
		
	    HSSFCell celltmp3 = row1.createCell(0);
	    celltmp3.setCellValue("表号："+entity.getReportcode());
	    HSSFCellStyle titlestyle1 = wb.createCellStyle(); 
	    titlestyle1.setWrapText(true);//自动换行
	    titlestyle1.setAlignment(HSSFCellStyle.ALIGN_LEFT); 
	    celltmp3.setCellStyle(titlestyle1);
	    
	    HSSFCell celltmp4 = row1.createCell(entity.getColumnNumber()-1);
	    celltmp4.setCellValue("统计时点："+nianf+"学年");
	    celltmp4.setCellStyle(titlestyle1);
	}
	
	
	
}
