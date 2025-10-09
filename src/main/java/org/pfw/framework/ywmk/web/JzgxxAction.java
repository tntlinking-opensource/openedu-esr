package org.pfw.framework.ywmk.web;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.domain.security.User;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.xtgl.domain.Dict;
import org.pfw.framework.xtgl.service.DictService;
import org.pfw.framework.ywmk.domain.Jzgxx;
import org.pfw.framework.ywmk.domain.Xsxx;
import org.pfw.framework.ywmk.domain.Xxxq;
import org.pfw.framework.ywmk.domain.Xxxx;
import org.pfw.framework.ywmk.service.JzgxxService;
import org.pfw.framework.ywmk.service.XxxqService;
import org.pfw.framework.ywmk.service.XxxxService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 功能管理Action.
 */
@Results
	({ 
		@Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl" , type="freemarker"),
		@Result(name ="layerForparent",location="/util/winopenlayerForparent.ftl", type = "freemarker"),
		@Result(name="success",location="/ywmk/jzgxx-list.ftl",type = "freemarker"),
		@Result(name="tmimp",location="/ywmk/jzgxx-daor.ftl",type = "freemarker"),
		@Result(name ="input",location="/ywmk/commonpage.ftl", type = "freemarker"),
		@Result(name = "download", type = "stream", params = { "contentType",
				"application/octet-stream;charset=utf-8", "contentDisposition",
				"attachment;filename=${wjname}", "inputName", "downloadFile",
				"bufferSize", "1002410" })
	})
public class JzgxxAction extends CrudActionSupport<Jzgxx> {
	@Autowired
	protected JzgxxService entityService;
	@Autowired
	protected DictService dictService;
	@Autowired
	protected XxxqService xxxqService;
	@Autowired
	protected XxxxService xxxxService;
	private String bxlxstr; 

	/*导入*/
	private String drfjFileName;
	private File drfj;
	private String dzurl;
	private String wjname;
	private int errcont = 0;
	private int succont = 0;
	private List<String> errList = new ArrayList<String>();
	/*导入*/

	private Xxxq xxxq;
	private String ymlx ="jzgxx";

	
	private List reqlist;
	private List<Dict> minzList;
	private List<Dict> zzmmList;
	private List<Dict> renylxList;
	private List<Dict> zhijList;
	private List<Dict> xuelList;
	private List<Dict> xuewList;
	private List<Dict> ywjyList;
	private List<Dict> zyjyList;
	private List<Dict> gzjyList;
	private List<Dict> dqztList;
	private List<Dict> xjlxList;
	private List<Dict> xuedList;
	private List xxxqList;
	private String ljdz;
	private List<Dict> jigList;

	private List<String> teacherList;

	private String xxlxdm;//学校类型代码
	private Xxxx xxxx;

	//统一教职工导入
	private List<Xxxq> xqxxList;

	//判断是否为数字
	private String reg = "^-?[0-9]+(.[0-9]+)?$";
	//判断日期是否正确格式YYYY-MM
	private String yyyymm = "^\\d{4}-(0[1-9]|1[0-2])$";
	//判断日期是否正确格式YYYY
	private String yyyy = "^(19|20)\\d\\d$";
	//判断日期是否正确格式YYYY-MM-dd
	private String yyyymmmdd = "^((\\d{2}(([02468][048])|([13579][26]))"
			+ "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
			+ "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
			+ "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
			+ "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
			+ "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";


	public List<Xxxq> getXqxxList() {
		return xqxxList;
	}

	public void setXqxxList(List<Xxxq> xqxxList) {
		this.xqxxList = xqxxList;
	}

	public Xxxx getXxxx() {
		return xxxx;
	}

	public void setXxxx(Xxxx xxxx) {
		this.xxxx = xxxx;
	}

	public String getYmlx() {
		return ymlx;
	}

	public void setYmlx(String ymlx) {
		this.ymlx = ymlx;
	}

	public String getBxlxstr() {
		return bxlxstr;
	}

	public void setBxlxstr(String bxlxstr) {
		this.bxlxstr = bxlxstr;
	}

	public String getXxlxdm() {
		return xxlxdm;
	}

	public void setXxlxdm(String xxlxdm) {
		this.xxlxdm = xxlxdm;
	}

	public List<Dict> getJigList() {
		return jigList;
	}

	public void setJigList(List<Dict> jigList) {
		this.jigList = jigList;
	}

	public String getLjdz() {
		return ljdz;
	}

	public void setLjdz(String ljdz) {
		this.ljdz = ljdz;
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

	public List<Dict> getMinzList() {
		return minzList;
	}

	public void setMinzList(List<Dict> minzList) {
		this.minzList = minzList;
	}

	public List<Dict> getZzmmList() {
		return zzmmList;
	}

	public void setZzmmList(List<Dict> zzmmList) {
		this.zzmmList = zzmmList;
	}

	public List<Dict> getRenylxList() {
		return renylxList;
	}

	public void setRenylxList(List<Dict> renylxList) {
		this.renylxList = renylxList;
	}

	public List<Dict> getZhijList() {
		return zhijList;
	}

	public void setZhijList(List<Dict> zhijList) {
		this.zhijList = zhijList;
	}

	public List<Dict> getXuelList() {
		return xuelList;
	}

	public void setXuelList(List<Dict> xuelList) {
		this.xuelList = xuelList;
	}

	public List<Dict> getXuewList() {
		return xuewList;
	}

	public void setXuewList(List<Dict> xuewList) {
		this.xuewList = xuewList;
	}

	public List<Dict> getYwjyList() {
		return ywjyList;
	}

	public void setYwjyList(List<Dict> ywjyList) {
		this.ywjyList = ywjyList;
	}

	public List<Dict> getZyjyList() {
		return zyjyList;
	}

	public void setZyjyList(List<Dict> zyjyList) {
		this.zyjyList = zyjyList;
	}

	public List<Dict> getGzjyList() {
		return gzjyList;
	}

	public void setGzjyList(List<Dict> gzjyList) {
		this.gzjyList = gzjyList;
	}

	public List<Dict> getDqztList() {
		return dqztList;
	}

	public void setDqztList(List<Dict> dqztList) {
		this.dqztList = dqztList;
	}

	public List<Dict> getXjlxList() {
		return xjlxList;
	}

	public void setXjlxList(List<Dict> xjlxList) {
		this.xjlxList = xjlxList;
	}

	public List<Dict> getXuedList() {
		return xuedList;
	}

	public void setXuedList(List<Dict> xuedList) {
		this.xuedList = xuedList;
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
		minzList = dictService.findAllByDM("minz");
		zzmmList = dictService.findAllByDM("zzmm");
		renylxList = dictService.findAllByDM("renylb");
		zhijList = dictService.findAllByDM("zhij");
		xuelList = dictService.findAllByDM("xuel");
		xuewList = dictService.findAllByDM("xuew");
		ywjyList = dictService.findAllByDM("ywjy");
		gzjyList = dictService.findAllByDM("gzjy");
		zyjyList = dictService.findAllByDM("rjdl");
		dqztList = dictService.findAllByDM("dqzt");
		xjlxList = dictService.findAllByDM("xjlx");
		xuedList = dictService.findAllByDM("xued");
		jigList  = dictService.findAllByDM("jig");
		xxxqList = xxxqService.findAll();
		
		bxlxstr = xxxxService.findAll().get(0).getBxlxid();
		List<Xxxx> xxxxAll = xxxxService.findAll();
		if (xxxxAll != null && xxxxAll.size() >0){
			 xxxx = xxxxAll.get(0);
			xxlxdm =  xxxx.getBxlxid();
		}
		return INPUT;
	}

	@Override
	public String list() throws Exception {

		String nf = Struts2Utils.getParameter("nf");
		 ljdz = Struts2Utils.getParameter("ljdz");
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
		minzList = dictService.findAllByDM("minz");
		zzmmList = dictService.findAllByDM("zzmm");
		xuelList = dictService.findAllByDM("xuel");
		
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (!StringUtils.isEmpty(id)) {
			entity = (Jzgxx) entityService.getById(id);
		} else {
			entity = new Jzgxx();
		}
		
	}

	@Override
	public String save() throws Exception {

		Set<Dict> rjkcSet = new HashSet<Dict>();
		String[] rjkc = Struts2Utils.getRequest().getParameterValues("teacherList");
		if (rjkc != null && rjkc.length > 0) {
			byte b;
			int i;
			String[] arrayOfString;
			for (i = (arrayOfString = rjkc).length, b = 0; b < i;) {
				String s = arrayOfString[b];
				Dict tmpO = new Dict();
				tmpO.setId(s);
				rjkcSet.add(tmpO);
				b++;
			}
		}
		entity.setRjkcSet(rjkcSet);
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

			String  mbname	=""+xxlxdm+"-教职工信息Excel模板.xls";
			String scdz = "/jzgxxpt.xls";
			if (StringUtils.isNotEmpty(xxlxdm)){
				if (xxlxdm.equals("九年一贯制学校")){
					scdz ="/jzgxxjnygz.xls";
				}else if (xxlxdm.equals("十二年一贯制学校")){
					scdz ="/jzgxxsenygz.xls";
				}else if (xxlxdm.equals("完全中学")){
					scdz ="/jzgxxwqzx.xls";
				}else if (xxlxdm.equals("幼儿园")){
					scdz ="/jzgxxyey.xls";
				}else if (xxlxdm.equals("调整后中等职业学校") || xxlxdm.equals("中等技术学校") || xxlxdm.equals("职业高中学校")){
					scdz ="/jzgxxzyxx.xls";
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
	public String daorxxs() throws Exception{
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
			minzList = dictService.findAllByDM("minz");
			zzmmList = dictService.findAllByDM("zzmm");
			renylxList = dictService.findAllByDM("renylb");
			zhijList = dictService.findAllByDM("zhij");
			xuelList = dictService.findAllByDM("xuel");
			xuewList = dictService.findAllByDM("xuew");
			ywjyList = dictService.findAllByDM("ywjy");
			gzjyList = dictService.findAllByDM("gzjy");
			zyjyList = dictService.findAllByDM("rjdl");
			dqztList = dictService.findAllByDM("dqzt");
			xjlxList = dictService.findAllByDM("xjlx");
			xuedList = dictService.findAllByDM("xued");
			jigList  = dictService.findAllByDM("jig");
			xxxqList = xxxqService.findAll();
			//普通学校导入模板的长度
			int[] colIndex = new int[]{1,2,4,5,6,7,8,9,10,11,12,13,14,16,17,18,19,20,25,26,27,28,29,30,31,32,33,34,35,36};
			if (StringUtils.isNotEmpty(xxbxlx)){
				if (xxbxlx.equals("小学") || xxbxlx.equals("初级中学") || xxbxlx.equals("高级中学")){
					colIndex = new int[]{1,2,4,5,6,7,8,9,10,11,12,13,14,16,17,18,19,20,21,24,25,26,27,28,29,30,31,32,33,34,35,36};
				}else if (xxbxlx.equals("调整后中等职业学校") || xxbxlx.equals("中等技术学校") || xxbxlx.equals("职业高中学校")){
					colIndex = new int[]{1,2,4,5,6,7,8,9,10,11,12,13,14,16,17,18,19,20,22,23,25,26,27,28,29,30,31,32,33,34,35,36};
				}else if (xxbxlx.equals("九年一贯制学校") || xxbxlx.equals("完全中学") || xxbxlx.equals("十二年一贯制学校")){
					colIndex = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,16,17,18,19,20,21,24,25,26,27,28,29,30,31,32,33,34,35,36};
				}else if (xxbxlx.equals("幼儿园")){
					colIndex = new int[]{1,2,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,25,26,27,28,29,30,31,32,33,34,35,36};
				}
			}
			for(int row = 1; row < sheet.getLastRowNum()+1; row++){
				Jzgxx xm = new Jzgxx();
				//row为空则跳到下个row
				HSSFRow rowObj = sheet.getRow(row);
				//拿第一行的标题名称
				HSSFRow rowObjs = sheet.getRow(0);
				if(rowObj == null || rowObj.getCell(0) == null || StringUtils.isBlank(rowObj.getCell(0).toString())){
					continue;
				}else{
					rowindex++;
				}
				//是否出现错误
				boolean ok = true;
				for (int col = 0; col < colIndex.length; col++) {
					//当前格
					String value = null;
					String values = null;
					//获取当前格的标题
					if(rowObjs.getCell(col).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
						values = String.valueOf(rowObj.getCell(col).getNumericCellValue()).trim();
					}else if(rowObjs.getCell(col).getCellType() == HSSFCell.CELL_TYPE_STRING){
						values = rowObjs.getCell(col).getStringCellValue().trim();
					}else{
						values = rowObjs.getCell(col).toString().trim();
					}
					HSSFCell cell = rowObj.getCell(col);
					if(cell == null){
						if(col == 0){
							erradd(values,1,row,col);
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
					}
					int colindex =   colIndex[col];

					if (colindex == 1){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else{
							boolean sfzs = value.matches(yyyy);
							if (sfzs){
								xm.setSjnf(value);
							}else{
								erradd(values,2,row,col);
								ok = false;
							}
						}
					}
					//所属校区
					if(colindex == 2){
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
					//学段（由九年一贯制填写“小学，初中”，完全中学填写“初中，高中”，十二年一贯制填写“小学，初中，高中”）
					if(colindex == 3){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else{
							if (xxbxlx.equals("九年一贯制学校")){
								if (value.equals("小学") || value.equals("初中")){
									if (value.equals("小学")){
										xm.setXued("1");
									}else if (value.equals("初中")){
										xm.setXued("2");
									}
								}else{
									erradd(values,4,row,col);
									ok = false;
								}
							}
							if (xxbxlx.equals("完全中学")){
								if (value.equals("初中") || value.equals("高中")){
									if (value.equals("初中")){
										xm.setXued("2");
									}else if (value.equals("高中")){
										xm.setXued("3");
									}
								}else{
									erradd(values,4,row,col);
									ok = false;
								}
							}
							if (xxbxlx.equals("十二年一贯制学校")){
								if (value.equals("小学") || value.equals("初中") || value.equals("高中")){
									if (value.equals("小学")){
										xm.setXued("1");
									}else if (value.equals("初中")){
										xm.setXued("2");
									}else if (value.equals("高中")){
										xm.setXued("3");
									}
								}else{
									erradd(values,4,row,col);
									ok = false;
								}
							}
							if (StringUtils.isEmpty(xm.getXued())){
								erradd(values,1,row,col);
								ok = false;
							}
						}
					}
					//教工号
					if(colindex == 4){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else if (value.length() > 50){
							erradd(values,3,row,col);
						}else{
							xm.setJgh(value);
						}

					}
					//姓名
					if(colindex == 5){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else if (value.length() > 50){
							erradd(values,3,row,col);
						}else{
							xm.setXm(value);
						}

					}
					//出生年月
					if(colindex == 6){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}if (value.length() > 20){
							erradd(values,3,row,col);
						}else{
							boolean sfzs = value.matches(yyyymmmdd);
							if (sfzs){
								xm.setCsny(value);
							}else{
								erradd(values,2,row,col);
								ok = false;
							}
						}
					}
					//性别
					if(colindex == 7){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else{
							if (value.equals("男") || value.equals("女")){
								if (value.equals("男")){
									xm.setXb("1");
								}else if (value.equals("女")){
									xm.setXb("0");
								}
							}else{
								erradd(values,4,row,col);
								ok = false;
							}
						}
					}
					//民族
					if(colindex == 8){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else{
							for (Dict dict :minzList) {
								if (dict.getName().equals(value)){
									xm.setMz(dict);
									break;
								}
							}
							if (xm.getMz() == null){
								erradd(values,1,row,col);
								ok = false;
							}
						}
					}
					//政治面貌
					if(colindex == 9){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else{
							for (Dict dict :zzmmList) {
								if (dict.getName().equals(value)){
									xm.setZzmm(dict);
									break;
								}
							}
							if (xm.getZzmm() == null){
								erradd(values,1,row,col);
								ok = false;
							}
						}
					}
					//从教年份
					if(colindex == 10){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else{
							boolean sfzs = value.matches(yyyymm);
							if (sfzs){
								xm.setCjny(value);
							}else{
								erradd(values,2,row,col);
								ok = false;
							}
						}
					}
					//入校年月
					if(colindex == 11){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else{
							boolean sfzs = value.matches(yyyymm);
							if (sfzs){
								xm.setRxny(value);
							}else{
								erradd(values,2,row,col);
								ok = false;
							}
						}
					}
					//人员类别
					if (colindex == 12){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else{
							for (Dict dict :renylxList) {
								if (dict.getName().equals(value)){
									xm.setRylb(dict);
									break;
								}
							}
							if (xm.getRylb() == null){
								erradd(values,1,row,col);
								ok = false;
							}
						}
					}
					//职级
					if (colindex == 13){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else{
							for (Dict dict :zhijList) {
								if (dict.getName().equals(value)){
									xm.setZhij(dict);
									break;
								}
							}
							if (xm.getZhij() == null){
								erradd(values,1,row,col);
								ok = false;
							}
						}
					}
					//本学年是否授课
					if (colindex == 14){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else{
							if (value.equals("是") || value.equals("否")){
								if (value.equals("是")){
									xm.setBxnsfsk("1");
								}if (value.equals("否")){
									xm.setBxnsfsk("0");
								}
							}else{
								erradd(values,4,row,col);
								ok = false;
							}
						}
					}
					//是否特教专任教师(幼儿园)
					if (colindex == 15){
						if (value.equals("是") || value.equals("否")){
							if (value.equals("是")){
								xm.setSftjzrjs("1");
							}else if (value.equals("否")){
								xm.setSftjzrjs("0");
							}
						}else{
							erradd(values,4,row,col);
							ok = false;
						}
					}
					//是否华侨
					if (colindex == 16){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else{
							if (value.equals("是") || value.equals("否")){
								if (value.equals("是")){
									xm.setSftjzrjs("1");
								}else if (value.equals("否")){
									xm.setSftjzrjs("0");
								}
							}else{
								erradd(values,4,row,col);
								ok = false;
							}
						}
					}
					//学历
					if (colindex == 17){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else{
							for (Dict dict :xuelList) {
								if (dict.getName().equals(value)){
									xm.setXuel(dict);
									break;
								}
							}
							if (xm.getXuel() == null){
								erradd(values,4,row,col);
								ok = false;
							}
						}
					}
					//籍贯
					if (colindex == 18){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else{
							for (Dict dict :jigList) {
								if (dict.getName().equals(value)){
									xm.setJig(dict);
									break;
								}
							}
							if (xm.getJig() == null){
								erradd(values,4,row,col);
								ok = false;
							}
						}
					}
					//学位
					if (colindex == 19){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else{
							for (Dict dict :xuewList) {
								if (dict.getName().equals(value)){
									xm.setXuew(dict);
									break;
								}
							}
							if (xm.getXuew() == null){
								erradd(values,4,row,col);
								ok = false;
							}
						}
					}
					//当前状态
					if (colindex == 20){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else{
							for (Dict dict :dqztList) {
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
					//任教课程（小学,初级中学，高级中学,完全中学,九年一贯制学校,十二年一贯制学校）
					if (colindex == 21){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else{
							for (Dict dict :ywjyList) {
								if (dict.getName().equals(value)){
									xm.setYwjyrjkc(dict);
									xm.setYwjyrjkcid(dict.getId());
									break;
								}
							}
							if (xm.getYwjyrjkc() == null){
								erradd(values,4,row,col);
								ok = false;
							}
						}
					}
					//任教大类(调整后中等职业学校 ,中等技术学校,职业高中学校)
					if (colindex == 22){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else{
							for (Dict dict :zyjyList) {
								if (dict.getName().equals(value)){
									xm.setZyjyrjdl(dict);
									break;
								}
							}
							if (xm.getZyjyrjdl() == null){
								erradd(values,4,row,col);
								ok = false;
							}
						}
					}
					//是否实习指导课教师(调整后中等职业学校 ,中等技术学校,职业高中学校)
					if (colindex == 23){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else{
							if (value.equals("是") || value.equals("否")){
								if (value.equals("是 ")){
									xm.setSfsxzdkjs("1");
								}if (value.equals("否")){
									xm.setSfsxzdkjs("0");
								}
							}else{
								erradd(values,4,row,col);
								ok = false;
							}
						}
					}
					/**外语:1英语,2日语,3俄语
					 * 艺术:4音乐,5美术
					 * 技术:6信息技术,7通用技术
					 * */
					//课程类别（小学,初级中学，高级中学,完全中学,九年一贯制学校,十二年一贯制学校）
					if (colindex == 24){
						if(xm.getYwjyrjkc() != null && StringUtils.isNotEmpty(xm.getYwjyrjkc().getName())){
							if (xm.getYwjyrjkc().getName().equals("外语") || xm.getYwjyrjkc().getName().equals("艺术") || xm.getYwjyrjkc().getName().equals("技术")){
								//一级字段为外语
								System.out.println(xm.getYwjyrjkc().getName());
								if (xm.getYwjyrjkc().getName().equals("外语")){
									if(value.equals("英语")){
										xm.setXxkm("1");
									}else if(value.equals("日语")){
										xm.setXxkm("2");
									}else if (value.equals("俄语")){
										xm.setXxkm("3");
									}//一级字段为艺术
								}else if (xm.getYwjyrjkc().getName().equals("艺术")){
									if(value.equals("音乐")){
										xm.setXxkm("4");
									}else if(value.equals("美术")){
										xm.setXxkm("5");
									}//一级字段为技术
								}else if (xm.getYwjyrjkc().getName().equals("技术")){
									if(value.equals("信息技术")){
										xm.setXxkm("6");
									}else if(value.equals("通用技术")){
										xm.setXxkm("7");
									}
								}
								//任教课程为:外语,艺术,技术,此字段必为必填
								if (StringUtils.isEmpty(xm.getXxkm())){
									erradd(values,4,row,col);
									ok = false;
								}
							}
						}
					}
					//是否在编
					if (colindex == 25){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else{
							if (value.equals("是") || value.equals("否")){
								if (value.equals("是")){
									xm.setSfzb("1");
								}else if (value.equals("否")){
									xm.setSfzb("0");
								}
							}else{
								erradd(values,4,row,col);
								ok = false;
							}
						}
					}
					//是否接受过专业教育
					if (colindex == 26){
						if (StringUtils.isNotEmpty(value)){
							if (value.equals("是") || value.equals("否")){
								if (value.equals("是 ")){
									xm.setSfjsgzyjy("1");
								}else if (value.equals("否")){
									xm.setSfjsgzyjy("0");
								}
							}
						}
					}
					//是否心里健康教育教师
					if (colindex == 27){
						if (value.equals("是") || value.equals("否")){
							if (value.equals("是 ")){
								xm.setSfxlzkjs("1");
							}else if (value.equals("否")){
								xm.setSfxlzkjs("0");
							}
						}
					}
					//是否双师
					if (colindex == 28){
						if (value.equals("是") || value.equals("否")){
							if (value.equals("是 ")){
								xm.setSfss("1");
							}else if (value.equals("否")){
								xm.setSfss("0");
							}
						}
					}
					//是否新进
					if (colindex == 29){
						if (value.equals("是") || value.equals("否")){
							if (value.equals("是 ")){
								xm.setSfxj("1");
							}else if (value.equals("否")){
								xm.setSfxj("0");
							}
						}
					}
					//新进类型
					if(colindex == 30){
						if(StringUtils.isNotEmpty(value)){
							for (Dict dict : xjlxList) {
								if (dict.getName().equals(value)){
									xm.setXjlx(dict);
									break;
								}
							}
						}
					}
					//是否应届毕业生
					if (colindex == 31){
						if (value.equals("是") || value.equals("否")){
							if (value.equals("是 ")){
								xm.setSfyjbys("1");
							}else if (value.equals("否")){
								xm.setSfyjbys("0");
							}
						}
					}
					//是否师范生
					if (colindex == 32){
						if (value.equals("是") || value.equals("否")){
							if (value.equals("是 ")){
								xm.setSfsfs("1");
							}else if (value.equals("否")){
								xm.setSfsfs("0");
							}
						}
					}
					//是否学段调整
					if (colindex == 33){
						if (value.equals("是") || value.equals("否")){
							if (value.equals("是 ")){
								xm.setSfxdtz("1");
							}else if (value.equals("否")){
								xm.setSfxdtz("0");
							}
						}
					}
					//原学段
					if(colindex == 34){
						if(StringUtils.isNotEmpty(value)){
							for (Dict dict : xuedList) {
								if (dict.getName().equals(value)){
									xm.setYxd(dict);
									break;
								}
							}
						}
					}
					//现学段
					if(colindex == 35){
						if(StringUtils.isNotEmpty(value)){
							for (Dict dict : xuedList) {
								if (dict.getName().equals(value)){
									xm.setXxd(dict);
									break;
								}
							}
						}
					}
					//是否外校调入
					if(colindex == 36){
						if(StringUtils.isNotEmpty(value)){
							if (value.equals("是")){
								xm.setSfwxdr("1");
							}else if(value.equals("否")){
								xm.setSfwxdr("0");
							}
						}
					}
				}
				if(ok){
					List<Jzgxx> jzgList = entityService.findbyhql(" from Jzgxx r where r.jgh = ? and r.sjnf=?", xm.getJgh(),xm.getSjnf());
					if (jzgList != null && jzgList.size() > 0){
						Jzgxx jzg = jzgList.get(0);
						xm.setId(jzg.getId());
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
				errList.add("第"+(hang+1) +"行:"+mc+"出错，原因:内容未填写,(第"+(hang+1)+"行，第"+(lie+1)+"列)");
				break;
			//日期格式不对
			case 2:
				errList.add("第"+(hang+1) +"行:"+mc+"出错，原因:日期格式不正确,(第"+(hang+1)+"行，第"+(lie+1)+"列)");
				break;
			//字符超长
			case 3:
				errList.add("第"+(hang+1) +"行:"+mc+"出错，原因:字符超长,(第"+(hang+1)+"行，第"+(lie+1)+"列)");
				break;
			//数据不存在
			case 4:
				errList.add("第"+(hang+1) +"行:"+mc+"出错，原因:数据填写错误,(第"+(hang+1)+"行，第"+(lie+1)+"列)");
				break;
			//自定义错误
			case 5:
				errList.add(mc);
				break;
			default:
				errList.add("第"+(hang+1) +"行:"+mc+"出错，原因:未知错误请联系管理员, (第"+(hang+1)+"行，第"+(lie+1)+"列)");
				break;
		}
	}
	public void getrjkcjson() throws Exception {
		String kword = Struts2Utils.getParameter("kword");
		String hql = " from Dict  where   parent.dm='ywjy' ";
		if(StringUtils.isNotEmpty(kword)){
			hql+=" and  name like '%"+kword+"%' ";
		}
		List<Dict> retlst = dictService.getDictByhql(hql);

		String retstr = Struts2Utils.listToJson(retlst, "id","name");

		Struts2Utils.renderText(retstr);
	}
}

