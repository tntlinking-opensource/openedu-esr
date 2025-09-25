package org.pfw.framework.ywmk.web;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.annotations.common.reflection.XMember;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.xtgl.domain.Dict;
import org.pfw.framework.xtgl.service.DictService;
import org.pfw.framework.ywmk.domain.Bjxx;
import org.pfw.framework.ywmk.domain.Xsxx;
import org.pfw.framework.ywmk.domain.Xxxq;
import org.pfw.framework.ywmk.domain.Xxxx;
import org.pfw.framework.ywmk.service.BjxxService;
import org.pfw.framework.ywmk.service.XsxxService;
import org.pfw.framework.ywmk.service.XxxqService;
import org.pfw.framework.ywmk.service.XxxxService;
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
		@Result(name="success",location="/ywmk/xsxx-list.ftl",type = "freemarker"),
		@Result(name="tmimp",location="/ywmk/xsxx-daor.ftl",type = "freemarker"),
		@Result(name ="input",location="/ywmk/commonpage.ftl", type = "freemarker"),
		@Result(name = "download", type = "stream", params = { "contentType",
					"application/octet-stream;charset=utf-8", "contentDisposition",
					"attachment;filename=${wjname}", "inputName", "downloadFile",
					"bufferSize", "1002410" })
	})
public class XsxxAction extends CrudActionSupport<Xsxx> {
	@Autowired
	protected XsxxService entityService;
	@Autowired
	protected DictService dictService;
	@Autowired
	protected XxxqService xxxqService;
	@Autowired
	protected BjxxService bjxxService;
	@Autowired
	protected XxxxService xxxxService;
	
	private List reqlist;
	private List<Dict> canjlxList;
	private List<Dict> dqztList;
	private List<Dict> minzList;
	//进城务工随迁子女类型
	private List<Dict> jcsqzntList;
	//随迁子女类型
	private List<Dict> sqznList;
	private List<Dict> zzmmList;
	private List<Dict> jigList;
	private List<Dict> rxlxList;
	private List<Dict> swlxList;
	private List xxxqList;
	private List<Bjxx> BjxxList;
	/*导入*/
	private String drfjFileName;
	private File drfj;
	private String dzurl;
	private String wjname;
	private int errcont = 0;
	private int succont = 0;
	private List<String> errList = new ArrayList<String>();
	/*导入*/
	private Bjxx bjxx;
	private List<Bjxx> BanjiList;
	private String ljdz;
	private List<Xxxx> xxxxList;
	private Xxxx xxxx;

	
	private List<Dict> txyyList;
	private List<Dict> zszqtqkList;
	private String xxlxstr;

	//导入用到的数据,用来区分学校类型
	private String ymlx = "xsxx";//跳转页面类型
	private String xxbxlx;//学校办学类型
	private List<Dict> swddList;
	//判断是否为数字
	private String reg = "^-?[0-9]+(.[0-9]+)?$";
	//判断日期是否正确格式YYYY-MM
	private String pdrq = "^\\d{4}-(0[1-9]|1[0-2])$";
	//判断日期是否正确格式YYYY
	private 	String pdrqnian = "^(19|20)\\d\\d$";
	//判断日期是否正确格式YYYY-MM-dd
	private String ymd = "^((\\d{2}(([02468][048])|([13579][26]))"
			+ "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
			+ "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
			+ "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
			+ "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
			+ "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
	public List<Dict> getSwddList() {
		return swddList;
	}

	public void setSwddList(List<Dict> swddList) {
		this.swddList = swddList;
	}

	public String getYmlx() {
		return ymlx;
	}

	public void setYmlx(String ymlx) {
		this.ymlx = ymlx;
	}

	public String getXxlxstr() {
		return xxlxstr;
	}

	public void setXxlxstr(String xxlxstr) {
		this.xxlxstr = xxlxstr;
	}

	public List getZszqtqkList() {
		return zszqtqkList;
	}

	public void setZszqtqkList(List zszqtqkList) {
		this.zszqtqkList = zszqtqkList;
	}

	public List getTxyyList() {
		return txyyList;
	}

	public void setTxyyList(List txyyList) {
		this.txyyList = txyyList;
	}

	public Xxxx getXxxx() {
		return xxxx;
	}

	public void setXxxx(Xxxx xxxx) {
		this.xxxx = xxxx;
	}

	public List<Xxxx> getXxxxList() {
		return xxxxList;
	}

	public void setXxxxList(List<Xxxx> xxxxList) {
		this.xxxxList = xxxxList;
	}

	public List<Dict> getSwlxList() {
		return swlxList;
	}

	public void setSwlxList(List<Dict> swlxList) {
		this.swlxList = swlxList;
	}

	public List<Dict> getRxlxList() {
		return rxlxList;
	}

	public void setRxlxList(List<Dict> rxlxList) {
		this.rxlxList = rxlxList;
	}

	public List<Bjxx> getBanjiList() {
		return BanjiList;
	}

	public void setBanjiList(List<Bjxx> banjiList) {
		BanjiList = banjiList;
	}

	public String getLjdz() {
		return ljdz;
	}

	public void setLjdz(String ljdz) {
		this.ljdz = ljdz;
	}

	public Bjxx getBjxx() {
		return bjxx;
	}

	public void setBjxx(Bjxx bjxx) {
		this.bjxx = bjxx;
	}

	public List getReqlist() {
		return reqlist;
	}

	public void setReqlist(List reqlist) {
		this.reqlist = reqlist;
	}

	public List<Dict> getCanjlxList() {
		return canjlxList;
	}

	public void setCanjlxList(List<Dict> canjlxList) {
		this.canjlxList = canjlxList;
	}

	public List<Dict> getDqztList() {
		return dqztList;
	}

	public void setDqztList(List<Dict> dqztList) {
		this.dqztList = dqztList;
	}

	public List<Dict> getMinzList() {
		return minzList;
	}

	public void setMinzList(List<Dict> minzList) {
		this.minzList = minzList;
	}

	public List getJcsqzntList() {
		return jcsqzntList;
	}

	public void setJcsqzntList(List jcsqzntList) {
		this.jcsqzntList = jcsqzntList;
	}

	public List<Dict> getSqznList() {
		return sqznList;
	}

	public void setSqznList(List<Dict> sqznList) {
		this.sqznList = sqznList;
	}

	public List<Dict> getZzmmList() {
		return zzmmList;
	}

	public void setZzmmList(List<Dict> zzmmList) {
		this.zzmmList = zzmmList;
	}

	public List<Dict> getJigList() {
		return jigList;
	}

	public void setJigList(List<Dict> jigList) {
		this.jigList = jigList;
	}

	public List<Dict> getXxxqList() {
		return xxxqList;
	}

	public void setXxxqList(List<Dict> xxxqList) {
		this.xxxqList = xxxqList;
	}

	public List<Bjxx> getBjxxList() {
		return BjxxList;
	}

	public void setBjxxList(List<Bjxx> bjxxList) {
		BjxxList = bjxxList;
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
		canjlxList = dictService.findAllByDM("canjlx");
		dqztList = dictService.findAllByDM("dangqzt");
		jcsqzntList = dictService.findAllByDM("jcwgsqlx");
		sqznList = dictService.findAllByDM("suiqznlx");
		minzList = dictService.findAllByDM("minz");
		zzmmList = dictService.findAllByDM("zzmm");
		jigList  = dictService.findAllByDM("jig");
		rxlxList = dictService.findAllByDM("rxlx");
		swlxList = dictService.findAllByDM("swzyyy");
		txyyList = dictService.findAllByDM("txyy");
		xxxqList = xxxqService.findAll();
		BjxxList = bjxxService.findAll();
		
		xxlxstr = xxxxService.findAll().get(0).getBxlxid();
		
		zszqtqkList = dictService.findAllByDM("zszqtqk");
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
		BanjiList = bjxxService.findAll();

		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (!StringUtils.isEmpty(id)) {
			entity = (Xsxx) entityService.getById(id);
		} else {
			entity = new Xsxx();
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
		List<Xxxx> allList = xxxxService.findAll();
		if(allList != null && allList.size() > 0){
			xxxx = allList.get(0);
		}else{
			xxxx = new Xxxx();
		}

		return "tmimp";
	}
	/**
	 * 导入模板下载
	 */
	public String daor() throws Exception
	{
		List<Xxxx> allList = xxxxService.findAll();
		if(allList != null && allList.size() > 0){
			xxbxlx = allList.get(0).getXxlxmc();
		}else{
			Struts2Utils.setPromptInfo("参数错误:未获取到办学类型,请联系管理人员！");
			return tmimp();
		}
		try {
			String  mbname	=""+xxbxlx+"学生信息Excel模板.xls";
			String scdz = "xsxxptxx.xls";
			if (StringUtils.isNotEmpty(xxbxlx)){
				if (xxbxlx.equals("初级中学") ||  xxbxlx.equals("完全中学")){
					scdz ="/xsxxcjwqzx.xls";
				}else if (xxbxlx.equals("高级中学")){
					scdz ="/xsxxgjzx.xls";
				}else if (xxbxlx.equals("小学") || xxbxlx.equals("九年一贯制学校") || xxbxlx.equals("十二年一贯制九年一贯制学校")){
					scdz ="/xsxxygzxx.xls";
				}else if (xxbxlx.equals("调整后中等职业学校") || xxbxlx.equals("中等技术学校") || xxbxlx.equals("职业高中学校")){
					scdz ="/xsxxgjzx.xls";
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
			List<Xxxx> allList = xxxxService.findAll();
			if(allList != null && allList.size() > 0){
				xxbxlx = allList.get(0).getXxlxmc();
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
			jigList  = dictService.findAllByDM("jig");
			jcsqzntList = dictService.findAllByDM("jcwgsqlx");
			sqznList = dictService.findAllByDM("suiqznlx");
			canjlxList = dictService.findAllByDM("canjlx");
			dqztList = dictService.findAllByDM("dangqzt");
			rxlxList = dictService.findAllByDM("rxlx");
			zszqtqkList = dictService.findAllByDM("zszqtqk");
			txyyList = dictService.findAllByDM("txyy");
			swlxList = dictService.findAllByDM("swzyyy");
			//普通学校导入模板的长度
			int[] colIndex = new int[]{1,2,3,4,5,6,7,8,16,17,18,19,22,24,27,28,29,30,31,32};
			if (StringUtils.isNotEmpty(xxbxlx)){
				if (xxbxlx.equals("初级中学") ||  xxbxlx.equals("完全中学")){
					colIndex = new int[]{1,2,3,4,5,6,7,8,16,17,18,19,20,21,22,24,25,26,27,28,29,30,31,32};
				}else if (xxbxlx.equals("高级中学")){
					colIndex = new int[]{1,2,3,4,5,6,7,8,16,17,18,19,20,21,22,24,28,29,30,31,32};
				}else if (xxbxlx.equals("小学") || xxbxlx.equals("九年一贯制学校") || xxbxlx.equals("十二年一贯制学校")){
					colIndex = new int[]{1,2,3,4,5,6,7,8,15,16,17,18,19,20,21,22,24,25,26,27,28,29,30,31,32};
				}else if (xxbxlx.equals("调整后中等职业学校") || xxbxlx.equals("中等技术学校") || xxbxlx.equals("职业高中学校")){
					colIndex = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,16,17,18,19,22,23,24,27,28,29,30,31,32};
				}
			}
			for(int row = 1; row < sheet.getLastRowNum()+1; row++){
				Xsxx xm = new Xsxx();
				//row为空则跳到下个row
				HSSFRow rowObj = sheet.getRow(row);
				if(rowObj == null || rowObj.getCell(0) == null || StringUtils.isBlank(rowObj.getCell(0).toString())){
					continue;
				}else{
					rowindex++;
				}
				//是否出现错误
				boolean ok = true;
				for (int col = 0; col < colIndex.length; col++) {
					//当前格
						HSSFRow rowObjs = sheet.getRow(0);
						String value = null;
						String values = null;
						//获取当前遍历的标题
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
								boolean sfzs = value.matches(pdrqnian);
								if (sfzs){
									xm.setSjnf(value);
								}else{
									erradd(values,2,row,col);
									ok = false;
								}
							}
						}
						//所属班级
						if(colindex == 2){
							if(StringUtils.isEmpty(value)){
								erradd(values,1,row,col);
								ok = false;
							}else{
								bjxx = bjxxService.getBybjmc(value);
								if (bjxx!=null){
									xm.setSsbj(bjxx);
								}else{
									erradd(values,4,row,col);
									ok = false;
								}

							}
						}
						//学号
						if(colindex == 3){
							if(StringUtils.isEmpty(value)){
								erradd(values,1,row,col);
								ok = false;
							}else{
								if (value.length() > 50){
									erradd(values,3,row,col);
								}else{
									xm.setXh(value);
								}
							}
						}
						//姓名
						if(colindex == 4){
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
						if(colindex == 5){
							if(StringUtils.isEmpty(value)){
								erradd(values,1,row,col);
								ok = false;
							}if (value.length() > 20){
								erradd(values,3,row,col);
							}else{
								boolean sfzs = value.matches(ymd);
								if (sfzs){
									xm.setCsny(value);
								}else{
									erradd(values,2,row,col);
									ok = false;
								}
							}
						}
						//性别
						if(colindex == 6){
							if(StringUtils.isEmpty(value)){
								erradd(values,1,row,col);
								ok = false;
							}else{
								if (value.equals("男") || value.equals("女")){
									if (value.equals("男")){
										xm.setXb("1");
									}else if(value.equals("女")){
										xm.setXb("0");
									}
								}else{
									erradd(values,4,row,col);
									ok = false;
								}
							}
						}
						//入学年份
						if(colindex == 7){
							if(StringUtils.isEmpty(value)){
								erradd(values,1,row,col);
								ok = false;
							}else{
								boolean sfzs = value.matches(pdrqnian);
								if (sfzs){
									xm.setRxnf(value);
								}else{
									erradd(values,2,row,col);
									ok = false;
								}
							}
						}
						//入校类型
						if(colindex == 8){
							if(StringUtils.isEmpty(value)){
								erradd(values,1,row,col);
								ok = false;
							}else{
								for (Dict dict :rxlxList) {
									if (dict.getName().equals(value)){
										xm.setRxlx(dict);
										break;
									}
								}
								if (xm.getRxlx() == null){
									erradd(values,1,row,col);
									ok = false;
								}
							}
						}
						//学制类型(调整后中等职业学校 ,中等技术学校,职业高中学校)
						if (colindex == 9){
							if(StringUtils.isEmpty(value)){
								erradd(values,1,row,col);
								ok = false;
							}else{
								if (value.equals("全日制") || value.equals("非全日制")){
									if (value.equals("全日制 ")){
										xm.setXzlx("1");
									}if (value.equals("非全日制 ")){
										xm.setXzlx("0");
									}
								}else{
									erradd(values,4,row,col);
									ok = false;
								}
							}
						}
						//是否五年制高职中职段(调整后中等职业学校 ,中等技术学校,职业高中学校)
						if (colindex == 10){
							if(StringUtils.isEmpty(value)){
								erradd(values,1,row,col);
								ok = false;
							}else{
								if (value.equals("是") || value.equals("否")){
									if (value.equals("是 ")){
										xm.setWnzgzzzd("1");
									}if (value.equals("否")){
										xm.setWnzgzzzd("0");
									}
								}else{
									erradd(values,4,row,col);
									ok = false;
								}
							}
						}
						//招生中其他情况(调整后中等职业学校 ,中等技术学校,职业高中学校)
						if (colindex == 11){
							if(StringUtils.isEmpty(value)){
								erradd(values,1,row,col);
								ok = false;
							}else{
								for (Dict dict :zszqtqkList) {
									if (dict.getName().equals(value)){
										xm.setZszqtqk(dict);
										break;
									}
								}
								if (xm.getZszqtqk() == null){
									erradd(values,1,row,col);
									ok = false;
								}
							}
						}
						//是否初中毕业生(调整后中等职业学校 ,中等技术学校,职业高中学校)
						if (colindex == 12){
							if(StringUtils.isEmpty(value)){
								erradd(values,1,row,col);
								ok = false;
							}else{
								if (value.equals("是") || value.equals("否")){
									if (value.equals("是 ")){
										xm.setSfczbys("1");
									}if (value.equals("否")){
										xm.setSfczbys("0");
									}
								}else{
									erradd(values,4,row,col);
									ok = false;
								}
							}
						}
						//是否获取职业类证书(调整后中等职业学校 ,中等技术学校,职业高中学校)
						if (colindex == 13){
							if(StringUtils.isEmpty(value)){
								erradd(values,1,row,col);
								ok = false;
							}else{
								if (value.equals("是") || value.equals("否")){
									if (value.equals("是 ")){
										xm.setSfhqzylzs("1");
									}if (value.equals("否")){
										xm.setSfhqzylzs("0");
									}
								}else{
									erradd(values,4,row,col);
									ok = false;
								}
							}
						}
						//是否获取职业技能等级证书(调整后中等职业学校 ,中等技术学校,职业高中学校)
						if (colindex == 14){
							if(StringUtils.isEmpty(value)){
								erradd(values,1,row,col);
								ok = false;
							}else{
								if (value.equals("是") || value.equals("否")){
									if (value.equals("是 ")){
										xm.setSfhqzyjnzs("1");
									}if (value.equals("否")){
										xm.setSfhqzyjnzs("0");
									}
								}else{
									erradd(values,4,row,col);
									ok = false;
								}
							}
						}
						//招生中接受学前教育(小学,九年一贯制学校,十二年一贯制学校)
						if (colindex == 15){
							if(StringUtils.isEmpty(value)){
								erradd(values,1,row,col);
								ok = false;
							}else{
								if (value.equals("未接受") || value.equals("一年")  || value.equals("两年") || value.equals("三年")){
									if (value.equals("未接受 ")){
										xm.setSfhqzyjnzs("0");
									}
									if (value.equals("一年")){
										xm.setSfhqzyjnzs("1");
									}
									if (value.equals("两年")){
										xm.setSfhqzyjnzs("2");
									}
									if (value.equals("三年")){
										xm.setSfhqzyjnzs("3");
									}
								}else{
									erradd(values,4,row,col);
									ok = false;
								}
							}
						}
						//民族
						if(colindex == 16){
							if(StringUtils.isEmpty(value)){
								erradd(values,1,row,col);
								ok = false;
							}else{
								for (Dict dict : minzList) {
									if (dict.getName().equals(value)){
										xm.setMz(dict);
										break;
									}
								}
								if (xm.getMz() == null){
									erradd(values,4,row,col);
									ok = false;
								}
							}
						}
						//政治面貌
						if(colindex == 17){
							if(StringUtils.isEmpty(value)){
								erradd(values,1,row,col);
								ok = false;
							}else{
								for (Dict dict : zzmmList) {
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
						//籍贯
						if(colindex == 18){
							if(StringUtils.isEmpty(value)){
								erradd(values,1,row,col);
								ok = false;
							}else{
								for (Dict dict : jigList) {
									if (dict.getName().equals(value)){
										xm.setJig(dict);
										break;
									}
								}
								if (xm.getJig() == null){
									erradd(values,1,row,col);
									ok = false;
								}
							}
						}
						//残疾类型
						if(colindex == 19){
							if (StringUtils.isNotEmpty(value)){
								for (Dict dict : canjlxList) {
									if (dict.getName().equals(value)){
										xm.setCjlx(dict);
										break;
									}
								}
							}
						}
						//随迁子女类型（小学,初级中学，高级中学,完全中学,九年一贯制学校,十二年一贯制学校）
						if(colindex == 20){
							if (StringUtils.isNotEmpty(value)){
								for (Dict dict : sqznList) {
									if (dict.getName().equals(value)){
										xm.setSqznlx(dict);
										break;
									}
								}
							}
						}
						//进城务工随迁子女类型（小学,初级中学，高级中学,完全中学,九年一贯制学校,十二年一贯制学校）
						if(colindex == 21){
							if (StringUtils.isNotEmpty(value)){
								for (Dict dict : jcsqzntList) {
									if (dict.getName().equals(value)){
										xm.setJcwgsqlx(dict);
										break;
									}
								}
							}
						}
						//是否国际学生
						if(colindex == 22){
							if(StringUtils.isNotEmpty(value)){
								if (value.equals("是")){
									xm.setSfgjxs("1");
								}else if(value.equals("否")){
									xm.setSfjs("0");
								}
							}
						}
						//所属大洲(调整后中等职业学校 ,中等技术学校,职业高中学校)
						if (colindex == 23){
							if (StringUtils.isNotEmpty(value)){
								for (Dict dict : jcsqzntList) {
									if (dict.getName().equals(value)){
										xm.setJcwgsqlx(dict);
										break;
									}
								}
							}

						}
						//是否寄宿
						if(colindex == 24){
							if (value.equals("是")){
								xm.setSfjs("1");
							}else if(value.equals("否")){
								xm.setSfjs("0");
							}
						}
						//是否农村留守儿童（小学,九年一贯制学校,十二年一贯制学校，初级中学，完全中学）
						if(colindex == 25){
							if (value.equals("是")){
								xm.setSfnclset("1");
							}else if(value.equals("否")){
								xm.setSfnclset("0");
							}
						}
						//是否随班就读（小学,九年一贯制学校,十二年一贯制学校，初级中学，完全中学）
						if(colindex == 26){
							if (value.equals("是")){
								xm.setSfsbjd("1");
							}else if(value.equals("否")){
								xm.setSfsbjd("0");
							}
						}
						//是否送教上门（除了高级中学都填）
						if(colindex == 27){
							if (value.equals("是")){
								xm.setSfsjsm("1");
							}else if(value.equals("否")){
								xm.setSfsjsm("0");
							}
						}
						//当前状态
						if(colindex == 28){
							if(StringUtils.isEmpty(value)){
								erradd(values,1,row,col);
								ok = false;
							}else{
								for (Dict dict : dqztList) {
									if (dict.getName().equals(value)){
										xm.setDqzt(dict);
										break;
									}
								}
								if (xm.getDqzt() == null){
									erradd(values,1,row,col);
									ok = false;
								}
							}
						}
						//退学原因
						if(colindex == 29){
							if(StringUtils.isEmpty(value)){

							}else{
								for (Dict txyy : txyyList) {
									if (txyy.getName().equals(value)){
										xm.setTxyy(txyy);
										break;
									}
								}
								if (xm.getDqzt() == null){
									erradd(values,1,row,col);
									ok = false;
								}
							}
						}
						//死亡主要原因
						if(colindex == 30){
							if(StringUtils.isEmpty(value)){

							}else{
								for (Dict swzyyy : swlxList) {
									if (swzyyy.getName().equals(value)){
										xm.setSwlx(swzyyy);
										break;
									}
								}
								if (xm.getDqzt() == null){
									erradd(values,1,row,col);
									ok = false;
								}
							}
						}
						//状态变动原因
						if(colindex == 31){
							if (StringUtils.isNotEmpty(value)){
								xm.setZtbdyy(value);
							}
						}
						//死亡地点
						if(colindex == 32){
							if(StringUtils.isNotEmpty(value)){
								if (value.equals("校园内")){
									xm.setSwdd("1");
								}
								if (value.equals("校园外")){
									xm.setSwdd("2");
								}
							}
						}
				}
				if(ok){
					List<Xsxx> XsxxList = entityService.findbyhql(" from Xsxx r where r.xh = ? and r.sjnf=?", xm.getXh(),xm.getSjnf());
					if (XsxxList != null && XsxxList.size() > 0){
						Xsxx xsxx = XsxxList.get(0);
						xm.setId(xsxx.getId());
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
	public void getxqlxBybjid() throws Exception {
		String bjid = Struts2Utils.getParameter("bjid");
		Bjxx bjxx = bjxxService.getById(bjid);
		Xxxq ssxq = bjxx.getSsxq();
		xxxx = xxxxService.getById(ssxq.getSsxx().getId());
		xxxxList.add(xxxx);
		String retstr = Struts2Utils.listToJson(xxxxList, "id","xxlxmc");
		Struts2Utils.renderText(retstr);
	}
}
