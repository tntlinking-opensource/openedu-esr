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
import org.pfw.framework.ywmk.domain.Dwpxqk;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.ywmk.domain.Zyxx;
import org.pfw.framework.ywmk.service.DwpxqkService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 功能管理Action.
 */
@Results
	({ 
		@Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl" , type="freemarker"),
		@Result(name ="layerForparent",location="/util/winopenlayerForparent.ftl", type = "freemarker"),
		@Result(name="success",location="/ywmk/dwpxqk-list.ftl",type = "freemarker"),
		@Result(name="tmimp",location="/ywmk/dwpxdr-daor.ftl",type = "freemarker"),
		@Result(name ="input",location="/ywmk/dwpxqk-input.ftl", type = "freemarker"),
		@Result(name = "download", type = "stream", params = { "contentType",
				"application/octet-stream;charset=utf-8", "contentDisposition",
				"attachment;filename=${wjname}", "inputName", "downloadFile",
				"bufferSize", "1002410" })
	})
public class DwpxqkAction extends CrudActionSupport<Dwpxqk> {
	@Autowired
	protected DwpxqkService entityService;

	/*导入*/
	private String drfjFileName;
	private File drfj;
	private String dzurl;
	private String wjname;
	private int errcont = 0;
	private int succont = 0;
	private List<String> errList = new ArrayList<String>();
	/*导入*/
	private Dwpxqk dwpxqk;
	private List reqlist;
	public List getReqlist() {
		return reqlist;
	}
	public void setReqlist(List reqlist) {
		this.reqlist = reqlist;
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

	public Dwpxqk getDwpxqk() {
		return dwpxqk;
	}

	public void setDwpxqk(Dwpxqk dwpxqk) {
		this.dwpxqk = dwpxqk;
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
	
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
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
			entity = (Dwpxqk) entityService.getById(id);
		} else {
			entity = new Dwpxqk();
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


	/**
	 * 导入模板下载
	 */
	public String daor() throws Exception
	{


		try {
			String  mbname	="对外培训信息Excel模板.xls";
			String scdz = "/dwpxxxdr.xls";
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
			//判断是否为数字
			String reg = "^-?[0-9]+(.[0-9]+)?$";
			//判断日期是否正确格式YYYY-DD
			String pdrq = "^\\d{4}-(0[1-9]|1[0-2])$";
			//判断日期是否正确格式YYYY
			String pdrqnian = "^(19|20)\\d\\d$";
			for(int row = 1; row < sheet.getLastRowNum()+1; row++){
				Dwpxqk xm = new Dwpxqk();
				//row为空则跳到下个row
				HSSFRow rowObj = sheet.getRow(row);
				if(rowObj == null || rowObj.getCell(0) == null || StringUtils.isBlank(rowObj.getCell(0).toString())){
					continue;
				}else{
					rowindex++;
				}
				//是否出现错误
				boolean ok = true;
				//单元格
				for(int col = 0; col < 18; col++){
					String value = null;
					String values = null;
					//判断当前行的第：1，4，6，12个单元格是否为空
					HSSFCell cell = rowObj.getCell(col);
					HSSFRow rowObjs = sheet.getRow(0);

					if(cell == null){
						if(col == 0){
							ok = false;
						}else{
							value = "";
							values = "";
						}
					}else{//判断当前单元格的值是否为String类型不是的话转为String类型
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
					//数据年份
					if(col == 0){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else if (value.length() > 20){
							erradd(values,3,row,col);
						}else{
							xm.setSjnf(value);
						}
					}
					if(col == 1){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else if (value.length() > 100){
							erradd(values,3,row,col);
						}else{
							xm.setPxxmmc(value);
						}
					}
					if(col == 2){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else {
							if (value.equals("财政资金支付")){
								xm.setZyzjly("1");
							}
							if (value.equals("非财政资金支付")){
								xm.setZyzjly("2");
							}
							if (value.equals("免费公益项目")){
								xm.setZyzjly("3");
							}
						}
					}
					//到账经费
					if(col == 3){
						if (StringUtils.isNotEmpty(value)) {
							if (value.length() < 20){
								xm.setDzjf(value);
							}else{
								erradd(values,3,row,col);
							}
						}

					}
					if(col == 4){
						if (StringUtils.isNotEmpty(value)) {
							if (value.length() < 20){
								xm.setPxsj(value);
							}else{
								erradd(values,3,row,col);
							}
						}

					}
					if(col == 5){
						if (StringUtils.isNotEmpty(value)) {
							if (value.length() < 100){
								xm.setPxxs(value);
							}else{
								erradd(values,3,row,col);
							}
						}
					}
					if(col == 6){
						if (StringUtils.isNotEmpty(value)) {
							if (value.length() < 20){
								xm.setQyzg(value);
							}else{
								erradd(values,3,row,col);
							}
						}
					}
					if(col == 7){
						if (StringUtils.isNotEmpty(value)) {
							if (value.length() < 20){
								xm.setDzldgb(value);
							}else{
								erradd(values,3,row,col);
							}
						}
					}
					if(col == 8){
						if (StringUtils.isNotEmpty(value)) {
							if (value.length() < 20){
								xm.setJiaos(value);
							}else{
								erradd(values,3,row,col);
							}
						}
					}
					if(col == 9){
						if (StringUtils.isNotEmpty(value)) {
							if (value.length() < 20){
								xm.setNcldz(value);
							}else{
								erradd(values,3,row,col);
							}
						}
					}
					if(col == 10){
						if (StringUtils.isNotEmpty(value)) {
							if (value.length() < 20){
								xm.setZxxs(value);
							}else{
								erradd(values,3,row,col);
							}
						}
					}
					if(col == 11){
						if (StringUtils.isNotEmpty(value)) {
							if (value.length() < 20){
								xm.setLnr(value);
							}else{
								erradd(values,3,row,col);
							}
						}
					}
					if(col == 12){
						if (StringUtils.isNotEmpty(value)) {
							if (value.length() < 20){
								xm.setQita(value);
							}else{
								erradd(values,3,row,col);
							}
						}
					}
					if(col == 13){
						if (StringUtils.isNotEmpty(value)) {
							if (value.length() < 20){
								xm.setTyjr(value);
							}else{
								erradd(values,3,row,col);
							}
						}
					}
					if(col == 14){
						if (StringUtils.isNotEmpty(value)) {
							if (value.length() < 20){
								xm.setCjr(value);
							}else{
								erradd(values,3,row,col);
							}
						}
					}
					if(col == 15){
						if (StringUtils.isNotEmpty(value)) {
							if (value.length() < 20){
								xm.setXnjsxm(value);
							}else{
								erradd(values,3,row,col);
							}
						}

					}
					if(col == 16){
						if (StringUtils.isNotEmpty(value)) {
							if (value.length() < 20){
								xm.setWpry(value);
							}else{
								erradd(values,3,row,col);
							}
						}
					}
				}
				if(ok){
					entityService.save(xm);
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
