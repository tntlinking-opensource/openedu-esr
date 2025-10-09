package org.pfw.framework.ywmk.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.xtgl.domain.Dict;
import org.pfw.framework.xtgl.service.DictService;
import org.pfw.framework.ywmk.domain.Jsskflqk;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.ywmk.domain.Zrjspxqk;
import org.pfw.framework.ywmk.service.JsskflqkService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 功能管理Action.
 */
@Results
	({ 
		@Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl" , type="freemarker"),
		@Result(name ="layerForparent",location="/util/winopenlayerForparent.ftl", type = "freemarker"),
		@Result(name="success",location="/ywmk/jsskflqk-list.ftl",type = "freemarker"),
		@Result(name ="input",location="/ywmk/jsskflqk-input.ftl", type = "freemarker"),
		@Result(name="tmimp",location="/ywmk/jsskflqk-daor.ftl",type = "freemarker"),
		@Result(name = "download", type = "stream", params = { "contentType",
				"application/octet-stream;charset=utf-8", "contentDisposition",
				"attachment;filename=${wjname}", "inputName", "downloadFile",
				"bufferSize", "1002410" })
	})
public class JsskflqkAction extends CrudActionSupport<Jsskflqk> {
	@Autowired
	protected JsskflqkService entityService;
	@Autowired
	protected DictService dictService;

	private List reqlist;
	/*导入*/
	private String drfjFileName;
	private File drfj;
	private String dzurl;
	private String wjname;
	private int errcont = 0;
	private int succont = 0;
	private List<String> errList = new ArrayList<String>();
	/*导入*/
	private List<Dict> sklbList;
	private String ljdz;



	public String getLjdz() {
		return ljdz;
	}

	public void setLjdz(String ljdz) {
		this.ljdz = ljdz;
	}

	public String getDrfjFileName() {
		return drfjFileName;
	}

	public void setDrfjFileName(String drfjFileName) {
		this.drfjFileName = drfjFileName;
	}

	public File getDrfj() {
		return drfj;
	}

	public void setDrfj(File drfj) {
		this.drfj = drfj;
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

	public List<String> getErrList() {
		return errList;
	}

	public void setErrList(List<String> errList) {
		this.errList = errList;
	}

	public List getSklbList() {
		return sklbList;
	}

	public void setSklbList(List sklbList) {
		this.sklbList = sklbList;
	}

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
		sklbList =	dictService.findAllByDM("sklb");
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		ljdz = Struts2Utils.getParameter("ljdz");
		String nf = Struts2Utils.getParameter("nf");
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		if (StringUtils.isNotEmpty(nf)){
			filters.add(new PropertyFilter("EQS_sjnf",nf));
		}
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
			entity = (Jsskflqk) entityService.getById(id);
		} else {
			entity = new Jsskflqk();
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

	public String tmimp() {
		String proStr = (String) Struts2Utils.getSession().getAttribute(
				"promptInfo");
		if (StringUtils.isNotEmpty(proStr)) {
			Struts2Utils.getRequest().setAttribute("promptInfo",
					Struts2Utils.getSession().getAttribute("promptInfo"));
		}
		Struts2Utils.getSession().removeAttribute("promptInfo");
		//zbid =  Struts2Utils.getParameter("zbid");

		return "tmimp";
	}
	/**
	 * 导入模板下载
	 */
	public String daor() throws Exception
	{
		try {
			String  mbname	="教师授课分类情况Excel模板.xls";
			String scdz = "/jsskflqk.xls";
			String url = Struts2Utils.getSession().getServletContext()
					.getRealPath("/export");
			dzurl = url + scdz;
			File file = new File(url + scdz);
			if (!file.exists()) {
				Struts2Utils.setPromptInfo("下载文件不存在，请联系管理人员！");
				return tmimp();
			}

			wjname = new String(mbname.getBytes(), "ISO8859-1");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "download";
	}
	public InputStream getDownloadFile() throws Exception {
		File file = new File(dzurl);
		InputStream is = null;
		if (file.exists()) {
			is = new FileInputStream(file);
		}
		return is;
	}

	/**
	 * 导入功能
	 * */
	public String daorxx() throws Exception{
		//初始化读取本地文件
		FileInputStream in =null;
		try {
			//正确数
			succont = 0;
			//错误数
			errcont = 0;
			//初始化错误信息集合
			errList = new ArrayList<String>();
			in = new FileInputStream(drfj);
			POIFSFileSystem sf = new POIFSFileSystem(in);
			HSSFWorkbook book = new HSSFWorkbook(sf);
			//获取表的第一个页面
			HSSFSheet sheet = book.getSheetAt(0);
			//当前表的行数
			int rowindex = 0;
			sklbList =	dictService.findAllByDM("sklb");
			//判断是否为数字
			String reg = "^-?[0-9]+(.[0-9]+)?$";
			//判断日期是否正确格式YYYY-DD
			String pdrq = "^\\d{4}-(0[1-9]|1[0-2])$";
			//判断日期是否正确格式YYYY
			String pdrqnian = "^(19|20)\\d\\d$";
			for(int row = 1; row < sheet.getLastRowNum()+1; row++){
				Jsskflqk xm = new Jsskflqk();
				//row为空则跳到下个row
				HSSFRow rowObj = sheet.getRow(row);
				HSSFRow rowObjs = sheet.getRow(0);
				if(rowObj == null || rowObj.getCell(0) == null || StringUtils.isBlank(rowObj.getCell(0).toString())){
					continue;
				}else{
					rowindex++;
				}
				//是否出现错误
				boolean ok = true;
				//单元格
				for(int col = 0; col < 7; col++){

					String value = null;
					String values = null;
					//判断当前行的第：1，4，6，12个单元格是否为空
					HSSFCell cell = rowObj.getCell(col);

					if(cell == null){
						if(col == 0){
							errList.add("Sheet1表单中第"+(row)+"行,第"+(col+1)+"列,未填.");
							ok = false;
						}else{
							value = "";
						}
					}else{//判断当前单元格的值是否为String类型是的话转为String类型
						if(rowObj.getCell(col).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
							value = String.valueOf(rowObj.getCell(col).getNumericCellValue()).trim();
						}else if(rowObj.getCell(col).getCellType() == HSSFCell.CELL_TYPE_STRING){
							value = rowObj.getCell(col).getStringCellValue().trim();
						}else{
							value = rowObj.getCell(col).toString().trim();
						}
						if(rowObjs.getCell(col).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
							values = String.valueOf(rowObj.getCell(col).getNumericCellValue()).trim();
						}else if(rowObjs.getCell(col).getCellType() == HSSFCell.CELL_TYPE_STRING){
							values = rowObjs.getCell(col).getStringCellValue().trim();
						}else{
							values = rowObjs.getCell(col).toString().trim();
						}
					}
					if(col == 0){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}if (value.length() > 10){
							erradd(values,3,row,col);
							ok = false;
						}else{
							boolean sfzs = value.matches(pdrqnian);
							if (sfzs){
								xm.setSjnf(value);
							}else{
								erradd(values,2,row,col);
								ok = false;
							}
						}
					}
					if(col == 1){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}if (value.length() > 50){
							erradd(values,3,row,col);
						}else{
//							boolean sfzs = value.matches(reg);
//							if (sfzs){
//								xm.setJgh(value);
//							}else{
//								erradd(values,2,row,col);
//								ok = false;
//							}
							xm.setJgh(value);
						}
					}
					//姓名
					if(col == 2){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}if (value.length() > 50){
							erradd(values,3,row,col);
							ok = false;
						}else{
							xm.setXm(value);
						}
					}
					if(col == 3){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}if (value.length() > 2){
							erradd(values,3,row,col);
						}else{
							if (value.equals("是")){
								xm.setSfsk("1");
							}else if(value.equals("否")){
								xm.setSfsk("0");
							}
						}
					}
					if(col == 4){
						for (Dict dict : sklbList) {
							if (dict.getName().equals(value)){
								xm.setSklb(dict);
								break;
							}
						}
					}
					//是否思政课
					if(col == 5){
						if (value.equals("是")){
							xm.setSfszk("1");
						}else if(value.equals("否")){
							xm.setSfszk("0");
						}
					}
					//不授课原因
					if(col == 6){
						if (value.length()>50){
							erradd(values,3,row,col);
						}else{
							xm.setBskyy(value);
						}

					}
				}
				if(ok){
					List<Jsskflqk> JsskflqkList = entityService.findbyhql(" from Jsskflqk r where r.jgh = ? and r.sjnf=?", xm.getJgh(),xm.getSjnf());
					if (JsskflqkList != null && JsskflqkList.size() > 0){
						Jsskflqk jsskflqk = JsskflqkList.get(0);
						xm.setId(jsskflqk.getId());
						entityService.update(xm);
					}else{
						entityService.save(xm);
					}

					succont++;
				}else{
					errcont++;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(in != null){
				in.close();
			}
		}
		return "tmimp";
	}
	public  void erradd(String mc,int cwdm,int hang,int lie){
		switch(cwdm) {
			//空
			case 1:
				errList.add("第"+(hang+1) +"行:"+mc+"出错，原因:内容未填写，(第"+(hang+1)+"行，第"+(lie+1)+"列)");
				break;
			//日期格式不对
			case 2:
				errList.add("第"+(hang+1) +"行:"+mc+"出错，原因:日期格式不正确，(第"+(hang+1)+"行，第"+(lie+1)+"列)");
				break;
			//字符超长
			case 3:
				errList.add("第"+(hang+1) +"行:"+mc+"出错，原因:字符超长，(第"+(hang+1)+"行，第"+(lie+1)+"列)");
				break;
			//数据不存在
			case 4:
				errList.add("第"+(hang+1) +"行:"+mc+"出错，原因:数据在系统里不存在，(第"+(hang+1)+"行，第"+(lie+1)+"列)");
				break;
			default:
				errList.add("第"+(hang+1) +"行:"+mc+"出错，原因:未知错误请联系管理员  (第"+(hang+1)+"行，第"+(lie+1)+"列)");
				break;
		}
	}
}
