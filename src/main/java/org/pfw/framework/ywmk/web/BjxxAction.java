package org.pfw.framework.ywmk.web;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.xtgl.domain.Dict;
import org.pfw.framework.xtgl.service.DictService;
import org.pfw.framework.ywmk.domain.Bjxx;
import org.pfw.framework.ywmk.domain.Xxxq;
import org.pfw.framework.ywmk.domain.Xxxx;
import org.pfw.framework.ywmk.domain.Zyxx;
import org.pfw.framework.ywmk.service.BjxxService;
import org.pfw.framework.ywmk.service.XxxqService;
import org.pfw.framework.ywmk.service.XxxxService;
import org.pfw.framework.ywmk.service.ZyxxService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能管理Action.
 */
@Results
		({
			@Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl" , type="freemarker"),
			@Result(name ="layerForparent",location="/util/winopenlayerForparent.ftl", type = "freemarker"),
			@Result(name="success",location="/ywmk/bjxx-list.ftl",type = "freemarker"),
			@Result(name="tmimp",location="/ywmk/bjxx-daor.ftl",type = "freemarker"),
			@Result(name ="input",location= "/ywmk/commonpage.ftl", type = "freemarker"),
			@Result(name = "download", type = "stream", params = { "contentType",
					"application/octet-stream;charset=utf-8", "contentDisposition",
					"attachment;filename=${wjname}", "inputName", "downloadFile",
					"bufferSize", "1002410" })
		})
public class BjxxAction extends CrudActionSupport<Bjxx> {
	@Autowired
	protected BjxxService entityService;
	@Autowired
	protected DictService dictService;
	@Autowired
	protected XxxqService xxxqService;
	@Autowired
	private XxxxService xxxxService;
	@Autowired
	private ZyxxService zyxxService;

	private List reqlist;
	private List canjilxList;
	//幼教开班类别
	private List yjkblxList;
	private List dqztList;
	private List xxxqList;
	private List zjsszyList;
	private Xxxq xxxq;
	/*导入*/
	private String drfjFileName;
	private File drfj;
	private String dzurl;
	private String wjname;
	private int errcont = 0;
	private int succont = 0;
	private List<String> errList = new ArrayList<String>();
	/*导入*/

	private String  ljdz;
	private String bxlxstr;
	private Xxxx xxxx;
	private String ymlx = "bjxx";
	private String xxlxdm ;

	public String getXxlxdm() {
		return xxlxdm;
	}

	public void setXxlxdm(String xxlxdm) {
		this.xxlxdm = xxlxdm;
	}

	public String getYmlx() {
		return ymlx;
	}

	public void setYmlx(String ymlx) {
		this.ymlx = ymlx;
	}

	public Xxxx getXxxx() {
		return xxxx;
	}

	public void setXxxx(Xxxx xxxx) {
		this.xxxx = xxxx;
	}

	public String getBxlxstr() {
		return bxlxstr;
	}

	public void setBxlxstr(String bxlxstr) {
		this.bxlxstr = bxlxstr;
	}

	public String getLjdz() {
		return ljdz;
	}

	public void setLjdz(String ljdz) {
		this.ljdz = ljdz;
	}

	public List getZjsszyList() {
		return zjsszyList;
	}

	public void setZjsszyList(List zjsszyList) {
		this.zjsszyList = zjsszyList;
	}

	public Xxxq getXxxq() {
		return xxxq;
	}

	public void setXxxq(Xxxq xxxq) {
		this.xxxq = xxxq;
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

	public List getXxxqList() {
		return xxxqList;
	}

	public void setXxxqList(List xxxqList) {
		this.xxxqList = xxxqList;
	}

	public List getReqlist() {
		return reqlist;
	}
	public void setReqlist(List reqlist) {
		this.reqlist = reqlist;
	}

	public List getCanjilxList() {
		return canjilxList;
	}

	public void setCanjilxList(List canjilxList) {
		this.canjilxList = canjilxList;
	}

	public List getYjkblxList() {
		return yjkblxList;
	}

	public void setYjkblxList(List yjkblxList) {
		this.yjkblxList = yjkblxList;
	}

	public List getDqztList() {
		return dqztList;
	}

	public void setDqztList(List dqztList) {
		this.dqztList = dqztList;
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

		yjkblxList =	dictService.findAllByDM("yjkblx");
		canjilxList =	dictService.findAllByDM("canjlx");
		dqztList =	dictService.findAllByDM("bjxxdqzt");
		zjsszyList =	zyxxService.findAll();
		xxxqList = xxxqService.findAll();
		bxlxstr = xxxxService.findAll().get(0).getBxlxid();

		List<Xxxx> allList = xxxxService.findAll();
		if(allList != null && allList.size() > 0){
			xxxx = allList.get(0);
		}else{
			xxxx = new Xxxx();
		}
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		 ljdz = Struts2Utils.getParameter("ljdz");
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
			entity = (Bjxx) entityService.getById(id);
		} else {
			entity = new Bjxx();
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
			List<Xxxx> allList = xxxxService.findAll();
			if(allList != null && allList.size() > 0){
				xxlxdm = allList.get(0).getXxlxmc();
			}else{
				Struts2Utils.setPromptInfo("参数错误:未获取到办学类型,请联系管理人员！");
				return tmimp();
			}
			String scdz = "/bjxxptxx.xls";
			String  mbname	= ""+xxlxdm+"-班级信息Excel模板.xls";
			if (StringUtils.isNotEmpty(xxlxdm)){
				mbname = "班级信息Excel模板-"+xxlxdm+".xls";
				if (xxlxdm.equals("小学")){
					scdz ="/bjxxxiaox.xls";
				}else if (xxlxdm.equals("九年一贯制学校")){
					scdz ="/bjxxjnygz.xls";
				}else if (xxlxdm.equals("十二年一贯制学校")){
					scdz ="/bjxxsenygz.xls";
				}else if (xxlxdm.equals("完全中学")){
					scdz ="/bjxxwqzx.xls";
				}else if (xxlxdm.equals("幼儿园")){
					scdz ="/bjxxyey.xls";
				}else if (xxlxdm.equals("调整后中等职业学校") || xxlxdm.equals("中等技术学校") || xxlxdm.equals("职业高中学校")){
					scdz ="/bjxxzyxx.xls";
				}

			}
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
			String	xxbxlx ="";
			List<Xxxx> allList = xxxxService.findAll();
			if(allList != null && allList.size() > 0){
				xxbxlx = allList.get(0).getXxlxmc();
			}else{
				errList.add("参数错误:未获取到该学校的办学类型！！！");
				return "tmimp";
			}
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
			List<Dict> dictall = dictService.findAllByDM("yjkblx");
			List<Dict> canjlxall = dictService.findAllByDM("canjlx");
			List<Dict> bjxxdqztall = dictService.findAllByDM("bjxxdqzt");
			List<Dict> sszyall = dictService.findAllByDM("zjzydm");
			List<Dict> xuedList = dictService.findAllByDM("xued");
			List<Zyxx> zyxxList = zyxxService.findAll();

			//普通学校导入模板的长度
			int[] colIndex = new int[]{1,3,4,5,11,12};
			if (StringUtils.isNotEmpty(xxbxlx)){
				if (xxbxlx.equals("小学")){
					colIndex = new int[]{1,3,4,5,7,10,12};
				}else if (xxbxlx.equals("调整后中等职业学校") || xxbxlx.equals("中等技术学校") || xxbxlx.equals("职业高中学校")){
					colIndex = new int[]{1,3,4,5,8,9,10,11,12};
				}else if (xxbxlx.equals("九年一贯制学校") ||  xxbxlx.equals("十二年一贯制学校")){
					colIndex = new int[]{1,2,3,4,5,7,10,12};
				}else if (xxbxlx.equals("幼儿园")){
					colIndex = new int[]{1,3,4,5,6,10,12};
				}else if (xxbxlx.equals("完全中学")){
					colIndex = new int[]{1,2,3,4,5,10,12};
				}
			}
			//判断是否为数字
			String reg = "^-?[0-9]+(.[0-9]+)?$";
			//判断日期是否正确格式YYYY-DD
			String pdrq = "^\\d{4}-(0[1-9]|1[0-2])$";
			//判断日期是否正确格式YYYY
			String pdrqnian = "^(19|20)\\d\\d$";
			for(int row = 1; row < sheet.getLastRowNum()+1; row++){
				Bjxx xm = new Bjxx();
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
				//单元格:列
				for(int col = 0; col < colIndex.length; col++){
					String value = null;
					String values = null;
					//判断当前行的第：1，4，6，12个单元格是否为空
					HSSFCell cell = rowObj.getCell(col);

					if(rowObjs.getCell(col).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
						values = String.valueOf(rowObj.getCell(col).getNumericCellValue()).trim();
					}else if(rowObjs.getCell(col).getCellType() == HSSFCell.CELL_TYPE_STRING){
						values = rowObjs.getCell(col).getStringCellValue().trim();
					}else{
						values = rowObjs.getCell(col).toString().trim();
					}
					if(cell == null){
						if(col == 0){
							ok = false;
						}else{
							value = "";
						}
					}else{//判断当前单元格的值是否为String类型不是的话转为String类型
						if(rowObj.getCell(col).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
							value = String.valueOf(rowObj.getCell(col).getNumericCellValue()).trim();
						}else if(rowObj.getCell(col).getCellType() == HSSFCell.CELL_TYPE_STRING){
							value = rowObj.getCell(col).getStringCellValue().trim();
						}else{
							value = rowObj.getCell(col).toString().trim();
						}

					}
					int colindex =   colIndex[col];
					//所属校区
					if(colindex == 1){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else{
							 xxxq = xxxqService.getByxqmc(value);
							 if (xxxq!=null){
								 xm.setSsxq(xxxq);
							 }else{
								 erradd(values,4,row,col);
								 ok = false;
							 }
						}
					}
					//学段
					if(colindex == 2){
						if(StringUtils.isNotEmpty(value)){
							if (value.equals("小学")){
								xm.setXued("1");
							}
							if (value.equals("2")){
								xm.setXued(value);
							}
							if (value.equals("3")){
								xm.setXued(value);
							}
							if (StringUtils.isEmpty(xm.getXued())){
								erradd(values,4,row,col);
							}
						}
					}
					//班级代码
					if(colindex == 3){
						if (value.length() > 50){
							erradd(values,3,row,col);
						}else{
							xm.setBjdm(value);
						}
					}
					//班级名称
					if(colindex == 4){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else if (value.length() > 50){
							erradd(values,3,row,col);
						}else{
							xm.setBjmc(value);
						}

					}
					//年级
					if(colindex == 5){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else if (value.length() > 10){
							erradd(values,3,row,col);
							ok = false;
						}else{
							xm.setNianj(value);
						}
					}
					//开班类型（幼儿园）
					if(colindex == 6){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else{
							for (Dict dict : dictall) {
								if (dict.getName().equals(value)){
									xm.setYjkblx(dict);
									break;
								}
							}
						}
					}
					//是否复式班（小学，九年一贯制，十二年一贯制）
					if(colindex == 7){
						if (StringUtils.isNotEmpty(value)){
							if (value.equals("是") || value.equals("否")){
								if (value.equals("是")){
									xm.setXjsffsb("1");
								}else if (value.equals("否")){
									xm.setXjsffsb("0");
								}
							}else{
								erradd(values,4,row,col);
								ok = false;
							}
						}
					}
					//残疾类型（调整后中等职业学校,中等技术学校,职业高中学校）
					if(colindex == 8){
						if(StringUtils.isNotEmpty(value)){
							for (Dict dict : canjlxall) {
								if (dict.getName().equals(value)){
									xm.setTjcjlx(dict);
									break;
								}
							}
						}
					}
					//所属专业（调整后中等职业学校,中等技术学校,职业高中学校）
					if(colindex == 9){
						if(StringUtils.isNotEmpty(value)){
							for (Zyxx zyxx : zyxxList) {
								if (zyxx.getZymc().equals(value)){
									xm.setSszy(zyxx);
									break;
								}
							}
						}
					}
					//当前状态
					if(colindex == 10){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else{
							for (Dict dict : bjxxdqztall) {
								if (dict.getName().equals(value)){
									xm.setDqzt(dict);
									break;
								}
							}
							if (xm.getDqzt() == null){
								erradd(values,4,row,col);
								ok = false;
							}
						}
					}
					//是否现代学徒制（调整后中等职业学校,中等技术学校,职业高中学校）
					if(colindex == 11){
						if (StringUtils.isNotEmpty(value)){
							if (value.equals("是") || value.equals("否")){
								if (value.equals("是")){
									xm.setSfxzxtz("1");
								}else if (value.equals("否")){
									xm.setSfxzxtz("0");
								}
							}
						}
					}
					if(colindex == 12){
						if (StringUtils.isNotEmpty(value) ){
							boolean sfzs = value.matches(pdrq);
							if(sfzs == true){
								//当前班级的状态，不能为空不能填毕业时间
								if (xm.getDqzt() ==null){
									errList.add("第"+(row+1) +"行:"+values+"出错，原因:该数据的当前状态未填写,(第"+(row+1)+"行，第"+(col+1)+"列)");
								}else if (StringUtils.isNotEmpty(xm.getDqzt().getName()) && xm.getDqzt().getName().equals("毕业")){
									xm.setByny(value);
								}else {
									errList.add("第"+(row+1) +"行:"+values+"出错，原因:该数据的当前状态不是毕业,不能填写时间(第"+(row+1)+"行，第"+(col+1)+"列)");
								}
							}else{
								erradd(values,2,row,col);
								ok = false;
							}
						}
					}
				}
				if(ok){
					List<Bjxx> bjxxList = entityService.findbyhql(" from Bjxx r where r.nianj = ? and r.bjmc=?", xm.getNianj(),xm.getBjmc());
					if (bjxxList != null && bjxxList.size() > 0){
						Bjxx bjxx = bjxxList.get(0);
						xm.setId(bjxx.getId());
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
