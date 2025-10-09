package org.pfw.framework.ywmk.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
import org.pfw.framework.xtgl.domain.Dict;
import org.pfw.framework.xtgl.service.DictService;
import org.pfw.framework.ywmk.domain.*;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.ywmk.service.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 功能管理Action.
 */
@Results
	({ 
		@Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl" , type="freemarker"),
		@Result(name ="layerForparent",location="/util/winopenlayerForparent.ftl", type = "freemarker"),
		@Result(name="success",location="/ywmk/xiaoshexx-list.ftl",type = "freemarker"),
		@Result(name ="input",location="/ywmk/commonpage.ftl", type = "freemarker"),
		@Result(name="tmimp",location="/ywmk/xiaoshexx-daor.ftl",type = "freemarker"),
		@Result(name = "download", type = "stream", params = { "contentType",
				"application/octet-stream;charset=utf-8", "contentDisposition",
				"attachment;filename=${wjname}", "inputName", "downloadFile",
				"bufferSize", "1002410" })
	})
public class XiaoshexxAction extends CrudActionSupport<Xiaoshexx> {
	@Autowired
	protected XiaoshexxService entityService;
	@Autowired
	protected DictService  dictService;
	@Autowired
	protected XxxqService xxxqService;
	@Autowired
	protected XxxxService xxxxService;
	@Autowired
	protected DictTwoService dictTwoService;
	@Autowired
	protected DictThreeService dictThreeService;


	private List xxxqList;
	private List reqlist;
	private List syfxList;
	private List<Dict> dqztList;
	private List<Dict> alldict;
	private List<DictTwo> twosall;
	private List<DictThree> threesall;

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
	private String ljdz;
	private String xxbxlx;
	private String ymlx = "xiaoshexx";
	private Xxxx xxxx;

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

	public String getXxbxlx() {
		return xxbxlx;
	}

	public void setXxbxlx(String xxbxlx) {
		this.xxbxlx = xxbxlx;
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

	public List<DictThree> getThreesall() {
		return threesall;
	}

	public void setThreesall(List<DictThree> threesall) {
		this.threesall = threesall;
	}

	public List<DictTwo> getTwosall() {
		return twosall;
	}

	public void setTwosall(List<DictTwo> twosall) {
		this.twosall = twosall;
	}

	public List<Dict> getAlldict() {
		return alldict;
	}

	public void setAlldict(List<Dict> alldict) {
		this.alldict = alldict;
	}

	public List getXxxqList() {
		return xxxqList;
	}

	public void setXxxqList(List xxxqList) {
		this.xxxqList = xxxqList;
	}

	public List getSyfxList() {
		return syfxList;
	}

	public void setSyfxList(List syfxList) {
		this.syfxList = syfxList;
	}

	public List getDqztList() {
		return dqztList;
	}

	public void setDqztList(List dqztList) {
		this.dqztList = dqztList;
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
	private String bxlxstr;
	public String getBxlxstr() {
		return bxlxstr;
	}

	public void setBxlxstr(String bxlxstr) {
		this.bxlxstr = bxlxstr;
	}

	@Override
	public String input() throws Exception {
		dqztList = dictService.findAllByDM("xiaosdqzt");

		/**学校类型:幼儿园:
		 *        一级字典代码:yeyxssyfx
		 *        小学,初级中学,九年一贯制学校,完全中学,高级中学,十二年一贯制学校
		 *        一级字典分类代码：zxxxsqk1
		 * 		  培智学校,其他特教学校,
		 * 		  一级字典分类代码：tj
		 * 		  调整后中等职业学校 ,中等技术学校,职业高中学校必填
		 * 		  一级分类代码:zzxs
		 * 		  */
		syfxList = dictService.findAllByDM("syfx");
		xxxqList = xxxqService.findAll();
		
		bxlxstr = xxxxService.findAll().get(0).getBxlxid();
		List<Xxxx> xxxxAll = xxxxService.findAll();
		if (xxxxAll != null && xxxxAll.size() >0){
			xxxx = xxxxAll.get(0);
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
		syfxList = dictService.findAllByDM("syfx");
		page = entityService.findPage(page, filters);


		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (!StringUtils.isEmpty(id)) {
			entity = (Xiaoshexx) entityService.getById(id);
		} else {
			entity = new Xiaoshexx();
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
	public void getdictjson() throws Exception {

		String lxid = Struts2Utils.getParameter("lxid");
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();

		if(StringUtils.isNotEmpty(lxid))
		{
			PropertyFilter pft = new PropertyFilter("EQS_id",lxid);
			filters.add(pft);
		}
		Dict stl = dictService.getById(lxid);
		alldict = dictService.findAllByDM(stl.getDm());
		String retstr = Struts2Utils.listToJson(alldict, "id","name","dm");
		Struts2Utils.renderText(retstr);
	}

	public void getdictjsontwo() throws Exception {
		String lxid = Struts2Utils.getParameter("lxid");
		twosall = dictTwoService.findbyhql(" from DictTwo a where a.sszd.id = ? and a.sfyx = '1'",lxid);
		String retstr = Struts2Utils.listToJson(twosall, "id","name","dm");
		Struts2Utils.renderText(retstr);
	}

	public void getdictjsonthree() throws Exception {
		String lxid = Struts2Utils.getParameter("lxid");

		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_sfyx","1"));
		if(StringUtils.isNotEmpty(lxid))
		{
			PropertyFilter pft = new PropertyFilter("EQS_sszd",lxid);
			filters.add(pft);
		}
		threesall = dictThreeService.findbyhql(" from DictThree a where a.sszd.id = ?",lxid);
		String retstr = Struts2Utils.listToJson(threesall, "id","name","dm");
		Struts2Utils.renderText(retstr);
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
				xxbxlx = allList.get(0).getXxlxmc();
			}else{
				Struts2Utils.setPromptInfo("参数错误:未获取到办学类型,请联系管理人员！");
				return tmimp();
			}
			String  mbname	=""+xxbxlx+"-校舍信息Excel模板.xls";
			String scdz = "/xiaosptxx.xls";
			if (StringUtils.isNotEmpty(xxbxlx)){
				if (xxbxlx.equals("幼儿园")){
					scdz ="/xiaosyey.xls";
				}else if (xxbxlx.equals("小学") || xxbxlx.equals("初级中学") || xxbxlx.equals("九年一贯制学校")  || xxbxlx.equals("完全中学") || xxbxlx.equals("高级中学") || xxbxlx.equals("十二年一贯制学校")){
					scdz ="/xiaoszongxx.xls.xls";
				}else if (xxbxlx.equals("培智学校") || xxbxlx.equals("其他特教学校")){
					scdz ="/xiaostej.xls";
				}else if (xxbxlx.equals("调整后中等职业学校") || xxbxlx.equals("中等技术学校") || xxbxlx.equals("职业高中学校")){
					scdz ="/xiaosxxzyxx.xls";
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
			dqztList = dictService.findAllByDM("xiaosdqzt");
			//普通学校导入模板的长度
			int[] colIndex = new int[]{1,2,3,4,5,6,7,8,9,10,11,15};
			if (StringUtils.isNotEmpty(xxbxlx)){
			 if (xxbxlx.equals("调整后中等职业学校") || xxbxlx.equals("中等技术学校") || xxbxlx.equals("职业高中学校")){
					colIndex = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
				}
			}
			//判断是否为数字
			String reg = "^-?[0-9]+(.[0-9]+)?$";
			//判断日期是否正确格式YYYY-DD
			String pdrq = "^\\d{4}-(0[1-9]|1[0-2])$";
			//判断日期是否正确格式YYYY
			String pdrqnian = "^(19|20)\\d\\d$";
			for(int row = 1; row < sheet.getLastRowNum()+1; row++){
				Xiaoshexx xm = new Xiaoshexx();
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
				for(int col = 0; col < colIndex.length; col++){

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
					}
					int colindex =   colIndex[col];
					if(colindex == 1){
						//判断是不是整数
						boolean sfzs = value.matches(pdrqnian);
						if (sfzs == true) {
							xm.setSjnf(value);
						}else{
							erradd(values,2,row,col);
							ok = false;
						}
					}
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
					//使用方向一级
					if(colindex == 3){
						/**学校类型:幼儿园:
						 *        一级字典代码:yeyxssyfx
						 *        小学,初级中学,九年一贯制学校,完全中学,高级中学,十二年一贯制学校
						 *        一级字典分类代码：zxxxsqk1
						 * 		  培智学校,其他特教学校,
						 * 		  一级字典分类代码：tj
						 * 		  调整后中等职业学校 ,中等技术学校,职业高中学校必填
						 * 		  一级分类代码:zzxs
						 * 		  */
						if (xxbxlx.equals("幼儿园")){
							List<Dict> onedict = dictService.findAllByDM("yeyxssyfx");
							for (Dict dict : onedict) {
								if (value.equals(dict.getName())){
									xm.setSyfx(dict);
								}
							}
						}
						//中小
						if (xxbxlx.equals("小学") || xxbxlx.equals("初级中学") || xxbxlx.equals("九年一贯制学校")  || xxbxlx.equals("完全中学") || xxbxlx.equals("高级中学") || xxbxlx.equals("十二年一贯制学校")){
							List<Dict> onedict = dictService.findAllByDM("zxxxsqk1");
							for (Dict dict : onedict) {
								if (value.equals(dict.getName())){
									xm.setSyfx(dict);
								}
							}
						}
						//特教
						if (xxbxlx.equals("培智学校") || xxbxlx.equals("其他特教学校")){
							List<Dict> onedict = dictService.findAllByDM("tj");
							for (Dict dict : onedict) {
								if (value.equals(dict.getName())){
									xm.setSyfx(dict);
								}
							}
						}
						//职中
						if (xxbxlx.equals("调整后中等职业学校") || xxbxlx.equals("中等技术学校") || xxbxlx.equals("职业高中学校")){
							List<Dict> onedict = dictService.findAllByDM("zzxs");
							for (Dict dict : onedict) {
								if (value.equals(dict.getName())){
									xm.setSyfx(dict);
								}
							}
						}
						if (xm.getSyfx() == null){
							erradd(values,4,row,col);
						}
					}
					//二级使用方向
					if(colindex == 4){
						if (StringUtils.isNotEmpty(value)){
							if (xm.getSyfx() != null && StringUtils.isNotEmpty(xm.getSyfx().getName())){
								List<DictTwo> twos = dictTwoService.findAllBydictid(xm.getSyfx().getId());
								//选中一级标题下面的值
								for (DictTwo two : twos) {
									if (value.equals(two.getName())){
										xm.setSyfxtwo(two);
									}
								}
								if (xm.getSyfxtwo() == null){
									erradd(values,4,row,col);
								}
							}else{
								errList.add("第"+(row+1) +"行:二级使用方向 出错，原因:未填写一级使用方向,(第"+(row+1)+"行，第"+(col+1)+"列)");

							}

						}
					}
					if(colindex == 5){
						if (StringUtils.isNotEmpty(value)){
							if (xm.getSyfx() != null && StringUtils.isNotEmpty(xm.getSyfx().getName())){
								List<DictThree> twos = dictThreeService.findAllBydictid(xm.getSyfx().getId());
								//选中二级标题下面的值
								   if(twos != null && twos.size() > 0){
									   for (DictThree Three : twos) {
										   if (value.equals(Three.getName())){
											   xm.setSyfxthree(Three);
										   }
									   }
									   if (xm.getSyfxthree() == null){
										   erradd(values,4,row,col);
									   }
								   }
							}else{
								errList.add("第"+(row+1) +"行:二级使用方向 出错，原因:未在二级使用方向里找到该三级使用方向,(第"+(row+1)+"行，第"+(col+1)+"列)");
							}

						}
					}
					//校舍名称
					if(colindex == 6){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else if(value.length() >50){
							erradd(values,3,row,col);
							ok = false;
						}else{
							xm.setXsmc(value);
						}
					}
					//校舍面积
					if(colindex == 7){
						if(StringUtils.isEmpty(value)){
							erradd(values,1,row,col);
							ok = false;
						}else if (value.length() >10){
							erradd(values,3,row,col);
							ok = false;
						}else{
							xm.setXsmj(value);
						}
					}
					//是否c#危房
					if(colindex == 8){
						if (StringUtils.isNotEmpty(value)){
							if (value.equals("是")){
								xm.setSfcjwf("1");
							}else if(value.equals("否")){
								xm.setSfcjwf("0");
							}
						}
					}
					//是否d#危房
					if(colindex == 9){
						if (StringUtils.isNotEmpty(value)){
							if (value.equals("是")){
								xm.setSfdjwf("1");
							}else if(value.equals("否")){
								xm.setSfdjwf("0");
							}
						}
					}
					//是否租用外单位
					if(colindex == 10){
						if (StringUtils.isNotEmpty(value)){
							if (value.equals("是")){
								xm.setSfzywdw("1");
							}else if(value.equals("否")){
								xm.setSfzywdw("0");
							}
						}
					}
					//是否被外单位租(借)用
					if(colindex == 11){
						if (StringUtils.isNotEmpty(value)){
							if (value.equals("是")){
								xm.setSfbwdwzjy("1");
							}else if(value.equals("否")){
								xm.setSfbwdwzjy("0");
							}
						}
					}
					//是否正在施工(调整后中等职业学校 ,中等技术学校,职业高中学校)
					if(colindex == 12){
						if (StringUtils.isNotEmpty(value)){
							if (value.equals("是")){
								xm.setSfzzsg("1");
							}else if(value.equals("否")){
								xm.setSfzzsg("0");
							}
						}
					}
					//是否有产权(调整后中等职业学校 ,中等技术学校,职业高中学校)
					if(colindex == 13){
						if (StringUtils.isNotEmpty(value)){
							if (value.equals("是")){
								xm.setSfycq("1");
							}else if(value.equals("否")){
								xm.setSfycq("0");
							}
						}
					}
					//使用类型
					if(colindex == 14){
						if (value.equals("独立使用") || value.equals("共同使用")){
							if (value.equals("独立使用")){
								xm.setZjsylx("1");
							}else if(value.equals("共同使用")){
								xm.setZjsylx("0");
							}
						}else{
							erradd(values,4,row,col);
						}
					}
					//当前状态
					if(colindex == 15){
						if (StringUtils.isNotEmpty(value)){
							for (Dict dict : dqztList) {
								if (dict.getName().equals(value)){
									xm.setDqzt(dict);
									break;
								}
							}
						}
					}
				}
				if(ok){
					List<Xiaoshexx> XiaoshexxList = entityService.findbyhql(" from Xiaoshexx r where r.sjnf = ? and r.xsmc=?", xm.getSjnf(),xm.getXsmc());
					if (XiaoshexxList != null && XiaoshexxList.size() > 0){
						Xiaoshexx xiaosxx = XiaoshexxList.get(0);
						xm.setId(xiaosxx.id);
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
}
