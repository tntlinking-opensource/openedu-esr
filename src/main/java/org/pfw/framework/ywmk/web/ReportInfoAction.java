package org.pfw.framework.ywmk.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
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
import org.pfw.framework.xtgl.domain.Dict;
import org.pfw.framework.xtgl.service.DictService;
import org.pfw.framework.ywmk.dao.ReportInfoDao;
import org.pfw.framework.ywmk.domain.ReportInfo;
import org.pfw.framework.ywmk.domain.ReportInfoRowFilter;
import org.pfw.framework.ywmk.service.ReportInfoRowFilterService;
import org.pfw.framework.ywmk.service.ReportInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 功能管理Action.
 */
@Results
	({ 
		@Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl" , type="freemarker"),
		@Result(name="success",location="/ywmk/report_info-list.ftl",type = "freemarker"),
		@Result(name="layeopen",location="/util/winopenlayerForparent.ftl",type = "freemarker"),
		@Result(name ="inputtablehead",location="/ywmk/report_info-inputtablehead.ftl", type = "freemarker"),
		@Result(name ="inputtable",location="/ywmk/report_info-inputtable.ftl", type = "freemarker"),
		@Result(name ="allreview",location="/ywmk/report_info-allreview.ftl", type = "freemarker"),
		@Result(name ="reptreview",location="/ywmk/report_info-reptreview.ftl", type = "freemarker"),
		@Result(name ="reptretb",location="/ywmk/report_info-reptretb.ftl", type = "freemarker"),
		@Result(name ="openfzym",location="/ywmk/report_info-openfzym.ftl", type = "freemarker"),
		@Result(name ="impExcleMain",location="/ywmk/report_info-impExcleMain.ftl", type = "freemarker"),
		@Result(name ="bdgzpz",location="/ywmk/report_info-bdgzpz.ftl", type = "freemarker"),
		@Result(name ="fzctgzpz",location="/ywmk/report_info-fzctgzpz.ftl", type = "freemarker"),
		@Result(name ="inputtableAction",location="/ywmk/report_info!inputtable.action?id=${id}", type = "redirect"),
		@Result(name ="reptreviewAction",location="/ywmk/report_info!reptreview.action?id=${baobid}", type = "redirect"),
		@Result(name ="inputtableheadAction",location="/ywmk/report_info!inputtablehead.action?id=${id}", type = "redirect"),
		@Result(name ="input",location="/ywmk/report_info-input.ftl", type = "freemarker")
	})
public class ReportInfoAction extends CrudActionSupport<ReportInfo> {
	@Autowired
	protected ReportInfoService entityService;
	@Autowired
	protected DictService dctService;
	@Autowired
	protected ReportInfoDao entityDao;
	@Autowired
	private ReportInfoRowFilterService reportInfoRowFilterService;
	
	
	private List reqlist;
	private String baobid;
	private String tabblestr;
	private String reptTable;
	private String rwid;
	
	private List<String> errList;
	private int errcont = 0;
	private int succont = 0;
	private String drfjFileName;
	private File drfj;

	private String idstr;
	
	public String getIdstr() {
		return idstr;
	}
	public void setIdstr(String idstr) {
		this.idstr = idstr;
	}
	
	public List<String> getErrList() {
		return errList;
	}
	public void setErrList(List<String> errList) {
		this.errList = errList;
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
	public String getRwid() {
		return rwid;
	}
	public void setRwid(String rwid) {
		this.rwid = rwid;
	}
	public String getReptTable() {
		return reptTable;
	}
	public void setReptTable(String reptTable) {
		this.reptTable = reptTable;
	}
	public String getTabblestr() {
		return tabblestr;
	}
	public void setTabblestr(String tabblestr) {
		this.tabblestr = tabblestr;
	}
	public String getBaobid() {
		return baobid;
	}
	public void setBaobid(String baobid) {
		this.baobid = baobid;
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
		reqlist = dctService.findAllByDM("banxlx");
		return INPUT;
	}
	
	public String inputtable() throws Exception {
		if(StringUtils.isNotEmpty(id))
		{
			entity = entityService.getById(id);
		}
		return "inputtable";
	}
	
	public String inputtablehead() throws Exception {
		if(StringUtils.isNotEmpty(id))
		{
			entity = entityService.getById(id);
		}
		return "inputtablehead";
	}
	
	
	private String bbgs;
	public String getBbgs() {
		return bbgs;
	}
	public void setBbgs(String bbgs) {
		this.bbgs = bbgs;
	}
	@Override
	public String list() throws Exception {
		reqlist = dctService.findAllByDM("banxlx");
		bbgs = Struts2Utils.getParameter("bbgs");
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		
		if(StringUtils.isNotEmpty(bbgs)){
			filters.add(new PropertyFilter("EQS_bbgs",bbgs));
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
			entity = (ReportInfo) entityService.getById(id);
		} else {
			entity = new ReportInfo();
		}
		
	}

	@Override
	public String save() throws Exception {
		if(checks != null)
		{
			String str1 = "";
			String str2 = "";
			for(String dctid : checks)
			{
				str1 += dctid + ",";
				Dict enttmp = dctService.getById(dctid);
				str2 += enttmp.getName() + ",";
			}
			if(!str1.equals(""))
			{
				str1 = str1.substring(0,str1.length()-1);
			}
			if(!str2.equals(""))
			{
				str2 = str2.substring(0,str2.length()-1);
			}
			entity.setSyxxids(str1);
			entity.setSyxxmcs(str2);
		}
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
	 * 保存报表配置信息
	 * @return
	 * @throws Exception
	 */
	public String saveconfig() throws Exception 
	{
		if(StringUtils.isNotEmpty(id))
		{
			entity = entityService.getById(id);
			String tabblestr = Struts2Utils.getParameter("tabblestr");
			entity.setReptTable(tabblestr);
			entityService.update(entity);
			
			Struts2Utils.setPromptInfoToReq("保存成功!!");
		}else{
			Struts2Utils.setPromptInfoToReq("非法参数，保存失败!!");
		}
		
		return "inputtableAction";
	}
	
	/**
	 * 保存报表表头配置信息
	 * @return
	 * @throws Exception
	 */
	public String saveconfig2() throws Exception 
	{
		if(StringUtils.isNotEmpty(id))
		{
			entity = entityService.getById(id);
			String tabblestr = Struts2Utils.getParameter("tabblestr");
			entity.setReptTableHead(tabblestr);
			entityService.update(entity);
			
			Struts2Utils.setPromptInfoToReq("保存成功!!");
		}else{
			Struts2Utils.setPromptInfoToReq("非法参数，保存失败!!");
		}
		
		return "inputtableheadAction";
	}
	
	/**
	 * 报表整体预览
	 * @return
	 * @throws Exception
	 */
	public String allreview() throws Exception
	{
		return "allreview";
	}
	/**
	 * 单表预览
	 * @return
	 * @throws Exception
	 */
	public String reptreview() throws Exception
	{
		
		this.prepareModel();
		try {
			/**
			String jgzb=entity.getJgbmc1();
			if(StringUtils.isNotEmpty(jgzb)){
				List zbsfbc=entityDao.getSession().createSQLQuery(" select reptTable from "+jgzb+" where  ssbb ='"+entity.getId()+"' ").list();//后期条件要加批次  所属学校
				if(zbsfbc != null && zbsfbc.size() > 0){
					reptTable = zbsfbc.get(0).toString();
					//entity.setReptTable(zbsfbc.get(0).toString());
				}
			}
			**/
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "reptreview";
	}
	
	public String baocUserReport() {
		

		Map map = HibernateWebUtils.getTableval(Struts2Utils.getRequest());
		
		
		if(!StringUtils.isEmpty(baobid) && !map.isEmpty()  && !StringUtils.isEmpty(tabblestr)){
			Boolean trueoflase= entityService.saveReport(map, baobid,tabblestr);
			if(!trueoflase){
				Struts2Utils.setPromptInfoToReq("保存失败!!");
			}
		}
		
		return "reptreviewAction";
	}
	
	public void getReportInfojson() throws Exception {
		
		String banb = Struts2Utils.getParameter("banb");
		String bhbm = Struts2Utils.getParameter("bhbm");
		String bxlxid = Struts2Utils.getParameter("bxlxid");
		String bbnf = Struts2Utils.getParameter("bbnf");
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		
		if(StringUtils.isNotEmpty(banb))
		{
			PropertyFilter pft = new PropertyFilter("EQS_versionh",banb);
			filters.add(pft);
		}
		if(StringUtils.isNotEmpty(bhbm))
		{
			PropertyFilter pft = new PropertyFilter("LIKES_reportcode_OR_reportname",bhbm);
			filters.add(pft);
		}
		if(StringUtils.isNotEmpty(bxlxid))
		{
			PropertyFilter pft = new PropertyFilter("LIKES_syxxids",bxlxid);
			filters.add(pft);
		}
		if(StringUtils.isNotEmpty(bbnf))
		{
			PropertyFilter pft = new PropertyFilter("EQS_bbnf",bbnf);
			filters.add(pft);
		}
		PropertyFilter pft = new PropertyFilter("EQS_sfyx","1");
		filters.add(pft);
		 
		LinkedHashMap orderMap = new LinkedHashMap();
		orderMap.put("reportcode", "asc");
		
		List<ReportInfo> retlst = entityService.find(filters,orderMap);
		
		String retstr = Struts2Utils.listToJson(retlst, "id","reportcode","reportname");
		
		Struts2Utils.renderText(retstr);
	}
	
	
	
	public static void main(String[] args) {
		
		//处理行23+24+25+30+33
		String hang="行05+行06+行07+行08+行09";//列递增
		hang=hang.replaceAll("行", "");
		List<String> hangList=Struts2Utils.split(hang, "+");
		
		Integer kslie=1;//开始列 默认1
		String hangstr="";
		for (int i = 0; i < hangList.size(); i++) {
			hangstr+=hangList.get(i)+","+kslie.toString()+"+";
		}
		
		
		String lie ="列3+列4+列5+列6+列10+列11+列12+列13+列14+列15+列16+列17+列18+列21+列22+列23+列24"; //行递增
		lie=lie.replaceAll("列", "");
		lie=lie.replaceAll("0", "");
		List<String> lieList=Struts2Utils.split(lie, "+");
		String ksh="01";//开始列 默认
		String liestr="";
		for (int i = 0; i < lieList.size(); i++) {
			liestr+=ksh+","+lieList.get(i)+"+";
		}
		
		System.out.println(liestr);
		
	}
	
	/**
	 * 单表填报
	 * @return
	 * @throws Exception
	 */
	public String reptretb() throws Exception
	{
		
		this.prepareModel();
		try {
			rwid = Struts2Utils.getParameter("rwid");
			String jgzb=entity.getJgbmc1();
			if(StringUtils.isNotEmpty(jgzb)){
				List zbsfbc=entityDao.getSession().createSQLQuery(" select reptTable from "+jgzb+" where  ssbb ='"+entity.getId()+"' ").list();//后期条件要加批次  所属学校
				if(zbsfbc != null && zbsfbc.size() > 0){
					reptTable = zbsfbc.get(0).toString();
					//entity.setReptTable(zbsfbc.get(0).toString());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "reptretb";
	}
	
	/**
	 * 单表导出excle
	 */
	public void expExcle() throws Exception
	{
		if(StringUtils.isNotEmpty(id))
		{
			this.prepareModel();
			OutputStream output=null;
			HSSFWorkbook wb = new HSSFWorkbook();
			try {
				String jgzb = entity.getJgbmc1();
				if(StringUtils.isNotEmpty(jgzb))
				{
					List zbsfbc=entityDao.getSession().createSQLQuery(" select reptTable from "+jgzb+" where  ssbb ='"+entity.getId()+"' ").list();//后期条件要加批次  所属学校
					if(zbsfbc != null && zbsfbc.size() > 0)
					{
						HSSFSheet sheet = wb.createSheet(entity.getReportcode());
						
						//基准样式
						HSSFCellStyle basestyle = wb.createCellStyle();  
						basestyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框  
						basestyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框  
						basestyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框  
						basestyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框  
						basestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
						
						//标题栏样式
						HSSFCellStyle titlestyle = wb.createCellStyle();//标题栏样式
					    titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
					    titlestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
						
					    HSSFCell celltmp2 = sheet.createRow(0).createCell(0);//第一行为报表名称
					    celltmp2.setCellValue(entity.getReportname());
					    celltmp2.setCellStyle(titlestyle);
						sheet.addMergedRegion(new CellRangeAddress(0,0,0,entity.getColumnNumber()-1));
						
						HSSFCell celltmp3 = sheet.createRow(1).createCell(0);//第二行为表号 - 与教育部导出报表一致
						celltmp3.setCellValue("表号：" + entity.getReportcode());
						celltmp3.setCellStyle(titlestyle);
					    HSSFCell celltmp4 = sheet.getRow(1).createCell(entity.getColumnNumber()-1);//第二行最后一列为统计时点 - 与教育部导出报表一致
					    celltmp4.setCellValue("统计时点：2021学年");//2021信息后续需要根据任务的学年度去动态更改
					    celltmp4.setCellStyle(titlestyle);
					    
					    
					    
						reptTable = zbsfbc.get(0).toString();
						Document document = Jsoup.parse(reptTable);
						
						Elements tdlst = document.select("td[id^=td_]");
						
						if(tdlst != null && tdlst.size() > 0)
						{
							for(Element tdelemt : tdlst)
							{
								String tdtyp = tdelemt.attr("typ");
								
								int zb_x = Integer.parseInt(tdelemt.attr("x"))+1;// 坐标x 配置中的x坐标是从1开始的，excle中1，2行为固定信息列，所以后续所有的x坐标+1
								int zb_y = Integer.parseInt(tdelemt.attr("y"))-1;// 坐标y 配置中的y坐标是从1开始的，excle中需要从第0行开始算，所以统计-1
								
								if(StringUtils.isNotEmpty(tdtyp))
								{
									//带typ属性的为输入单元格
									Elements intelemt = tdelemt.select("input");
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
										if(rowtmp.getCell(zb_y) == null)
										{
											celltmp = rowtmp.createCell(zb_y);
										}else
										{
											celltmp = rowtmp.getCell(zb_y);
										}
										celltmp.setCellStyle(basestyle);
										celltmp.setCellValue(valtmp);
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
									if(rowtmp.getCell(zb_y) == null)
									{
										celltmp = rowtmp.createCell(zb_y);
									}else
									{
										celltmp = rowtmp.getCell(zb_y);
									}
									celltmp.setCellStyle(basestyle);
									celltmp.setCellValue(btstr);
								}
							}
						}
						
						Elements rowtdlst = document.select("td[rowspan]");
						if(rowtdlst != null && rowtdlst.size() > 0)
						{
							//考虑有行合并的情况
							for(Element elttmp : rowtdlst)
							{
								int zb_x = Integer.parseInt(elttmp.attr("x"))+1;// 坐标x 配置中的x坐标是从1开始的，excle中1，2行为固定信息列，所以后续所有的x坐标+1
								int zb_y = Integer.parseInt(elttmp.attr("y"))-1;// 坐标y 配置中的y坐标是从1开始的，excle中需要从第0行开始算，所以统计-1
								
								String rowspanstr = elttmp.attr("rowspan");
								if(StringUtils.isNotEmpty(rowspanstr))
								{
									int rowspanint = Integer.parseInt(rowspanstr);
									int endrow = zb_x + rowspanint - 1;
									
									CellRangeAddress cra = new CellRangeAddress(zb_x,endrow,zb_y,zb_y);//合并区域
									HSSFCell celltmp = sheet.getRow(zb_x).getCell(zb_y);
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
								int zb_x = Integer.parseInt(elttmp.attr("x"))+1;// 坐标x 配置中的x坐标是从1开始的，excle中1，2行为固定信息列，所以后续所有的x坐标+1
								int zb_y = Integer.parseInt(elttmp.attr("y"))-1;// 坐标y 配置中的y坐标是从1开始的，excle中需要从第0行开始算，所以统计-1
								
								String colspanstr = elttmp.attr("colspan");
								if(StringUtils.isNotEmpty(colspanstr))
								{
									int colspanint = Integer.parseInt(colspanstr);
									int endcol = (zb_y) + colspanint - 1;
									
									CellRangeAddress cra = new CellRangeAddress(zb_x,zb_x,zb_y,endcol); //合并区域
									HSSFCell celltmp = sheet.getRow(zb_x).getCell(zb_y);
									celltmp.setCellStyle(basestyle);
									RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cra, sheet,wb); // 下边框
									RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, cra, sheet,wb); // 左边框
									RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, cra, sheet,wb); // 有边框
									RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, cra, sheet,wb); // 上边框
									sheet.addMergedRegion(cra);
									
								}
							}
						}
						
						output=Struts2Utils.getResponse().getOutputStream();
						 
						Struts2Utils.getResponse().reset();
						String fname = entity.getReportcode() + ".xls";
						Struts2Utils.getResponse().setHeader("Content-disposition", "attachment; filename=" +  java.net.URLEncoder.encode(fname, "UTF-8") );
				 
						Struts2Utils.getResponse().setContentType("application/msexcel"); 
						wb.setForceFormulaRecalculation(true);
			            wb.write(output);
			            
					}else
					{
						Struts2Utils.renderText("您还未保存填报结果");
					}
				}
			} catch (Exception e) 
			{
				e.printStackTrace();
			}finally
			{
				if(output != null)
				{
					output.flush();
					output.close();
				}
			}
		}
	}
	
	public String impExcleMain() throws Exception
	{
		rwid = Struts2Utils.getParameter("rwid");
		this.prepareModel();
		return "impExcleMain";
	}
	
	/**
	  *  excel导出，有码值的数据使用下拉框展示。
	  * @param col             列名
	  * @param boxMap          码值集合
	  * @param firstRow        插入下拉框开始行号
	  * @param lastRow         插入下拉框结束行号
	  * @param firstCol        插入下拉框开始列号
	  * @param lastCol         插入下拉框结束行号
	  * @return
	  */
	 public static HSSFDataValidation createBox(String col, Map<String , String> boxMap , int firstRow, int lastRow, int firstCol, int lastCol) {
	     HSSFDataValidation dataValidation = null;
	     //查询码值表
	     String cols = "";
	     if(null != boxMap.get(col)) {
	         cols = boxMap.get(col);
	     }
	     //设置下拉框
	     if(cols.length() > 0 && null != cols) {
	         String str[] = cols.split(",");
	         //指定0-9行，0-0列为下拉框
	         CellRangeAddressList cas = new CellRangeAddressList(firstRow , lastRow , firstCol , lastCol);
	         //创建下拉数据列
	         DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(str);
	         //将下拉数据放入下拉框
	         dataValidation = new HSSFDataValidation(cas, dvConstraint);
	     }
	     return dataValidation;
	 }
	
	/**
	 * 根据传入的报表id，动态生成报表模板，并下载
	 * @throws Exception
	 
	public void downBbmb() throws Exception
	{
		
		if(StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(rwid))
		{
			this.prepareModel();
			OutputStream output=null;
			HSSFWorkbook wb = new HSSFWorkbook();
			
			HSSFSheet sheet = wb.createSheet(entity.getReportcode());
			
			//基准样式
			HSSFCellStyle basestyle = wb.createCellStyle();  
			basestyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框  
			basestyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框  
			basestyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框  
			basestyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框  
			basestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
			
		    //标题栏样式
			HSSFCellStyle titlestyle = wb.createCellStyle();//标题栏样式
		    titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		    titlestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		    
		    //公式栏样式
		    HSSFCellStyle frumstyle = wb.createCellStyle();//公式栏样式
		    frumstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		    frumstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		    frumstyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		    frumstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			
		    HSSFCell celltmp2 = sheet.createRow(0).createCell(0);//第一行为报表名称
		    celltmp2.setCellValue(entity.getReportname());
		    celltmp2.setCellStyle(titlestyle);
			sheet.addMergedRegion(new CellRangeAddress(0,0,0,entity.getColumnNumber()-1));
			
			HSSFCell celltmp3 = sheet.createRow(1).createCell(0);//第二行为表号 - 与教育部导出报表一致
			celltmp3.setCellValue("表号：" + entity.getReportcode());
			celltmp3.setCellStyle(titlestyle);
		    HSSFCell celltmp4 = sheet.getRow(1).createCell(entity.getColumnNumber()-1);//第二行最后一列为统计时点 - 与教育部导出报表一致
		    Nianbgl ent = nianbglService.getById(rwid);
		    celltmp4.setCellValue("统计时点："+ent.getNianf()+"学年");//2021信息后续需要根据任务的学年度去动态更改
		    celltmp4.setCellStyle(titlestyle);
			
			
			try {
				reptTable = entity.getReptTable();
				
				if(StringUtils.isNotEmpty(reptTable))
				{
					Document document = Jsoup.parse(reptTable);
					
					//考虑有些表需要通过学校办学类型过滤行，先把需要行过滤的去了
					Set<ReportInfoRowFilter> rftset = entity.getRowFilterSet();
					if(rftset != null && rftset.size() > 0)
					{
						//得到当前用户办学类型
						User usr = PFWSecurityUtils.getCurrentUser();
						List<Xxtby> xxtbyList = xxtbyService.findbyhql(" from Xxtby a where a.user.id = '"+usr.getId()+"' ");
						String hangs = "";
						if(xxtbyList != null && xxtbyList.size() > 0){
							
							String bxlx = xxtbyList.get(0).getXxxx().getBxlxids();
							List<String> bxlxList = Struts2Utils.split(bxlx, ",");
							for(ReportInfoRowFilter boj : rftset)
							{
								for (String bxl : bxlxList) {
									
									if(boj.getSsbxlx().getId().equals(bxl)){
										hangs += boj.getHanghs()+",";
									}
									
								}
								
							}
						}
						
						if(StringUtils.isNotEmpty(hangs)){
							
							hangs = hangs.substring(0,hangs.length() - 1);
							List<String> hangsList = Struts2Utils.split(hangs, ",");
							
							for (String hanghs : hangsList) {
								
								document.select("td[ywx="+hanghs+"]").get(0).parent().attr("ifdis","1");
								
							}
							Elements jzelmtlst = document.select("td[ifjzcell=1]");
							int rowx2 = -1;
							if(jzelmtlst != null && jzelmtlst.size() > 0)
							{
								Element jzelmt = jzelmtlst.get(0);
								if(jzelmt != null)
								{
									rowx2 = Integer.parseInt(jzelmt.attr("x"));
								}
							}
							rowx2 = rowx2 + 1;
							
							Elements tdtmpobj = document.select("td[x="+rowx2+"]");
							while(tdtmpobj != null && tdtmpobj.size() > 0)
							{
								String ifids = tdtmpobj.get(0).parent().attr("ifdis");
								if(StringUtils.isNotEmpty(ifids) && ifids.equals("1"))
								{
								}else{
									tdtmpobj.get(0).parent().remove();
								}
								rowx2 = rowx2 + 1;
								tdtmpobj = document.select("td[x="+rowx2+"]");
							}
							
							
						}
					 
					}
					
					Elements tdlst = document.select("td[id^=td_]");
					if (tdlst != null && tdlst.size() > 0) {
						for (Element tdelemt : tdlst) {
							String tdtyp = tdelemt.attr("typ");

							int zb_x = Integer.parseInt(tdelemt.attr("x"))+1;// 坐标x 配置中的x坐标是从1开始的，excle中1，2行为固定信息列，所以后续所有的x坐标+1
							int zb_y = Integer.parseInt(tdelemt.attr("y"))-1;// 坐标y 配置中的y坐标是从1开始的，excle中需要从第0行开始算，所以统计-1
							
							
							if (StringUtils.isNotEmpty(tdtyp)) 
							{
								String valtmp = null;
								HSSFRow rowtmp;
								if(sheet.getRow(zb_x) == null)
								{
									rowtmp = sheet.createRow(zb_x);
								}else
								{
									rowtmp = sheet.getRow(zb_x);
								}
								HSSFCell celltmp;
								if(rowtmp.getCell(zb_y) == null)
								{
									celltmp = rowtmp.createCell(zb_y);
								}else
								{
									celltmp = rowtmp.getCell(zb_y);
								}
								//如果单元格为数字
								if(tdtyp.equals("numb") || tdtyp.equals("textstr") )
								{
									celltmp.setCellStyle(basestyle);
									celltmp.setCellValue(valtmp);
									//celltmp.setCellType(Cell.CELL_TYPE_STRING);
								}
								if( tdtyp.equals("selectstr") ){//增加下拉框导出
									celltmp.setCellStyle(basestyle);
									String selectStr = tdelemt.attr("selectrules");
									if(StringUtils.isNotEmpty(selectStr)){
										String[] selectarr = selectStr.split("\\$"); 
										String seectVal = "";
										for (String str : selectarr) {
											String strnew[] = str.split("\\#");
											seectVal += strnew[0]+",";
										}
										seectVal = seectVal.substring(0,seectVal.length() - 1);
										Map<String, String> map = new HashMap<>();
										map.put("selectStr", seectVal);
										 //指定将下拉框添加至1-10行，0-0列。即第一列的第2到11行
										HSSFDataValidation dataValidation = createBox("selectStr", map , zb_x , zb_x , zb_y , zb_y);
										if(dataValidation != null) {
											sheet.addValidationData(dataValidation);
										}
									}
								}
								//如果单元格为公式
								if(tdtyp.equals("formula"))
								{
									String rulesstr = tdelemt.attr("rules");
									
									int jzcell_x = 0;//基准单元格的x
									int jzcell_y = 0;//基准单元格的y
									Elements jzelmtlst = document.select("td[ifjzcell=1]");
									if(jzelmtlst != null && jzelmtlst.size() > 0)
									{
										
										Element jzelmt = jzelmtlst.get(0);
										if(jzelmt != null)
										{

											jzcell_x = Integer.parseInt(jzelmt.attr("x"))+2;//坐标x 配置中的x坐标是从1开始的，excle中1，2行为固定信息列，所以后续所有的x坐标+1
											jzcell_y = Integer.parseInt(jzelmt.attr("y"))-1;//坐标y 配置中的y坐标是从1开始的，excle中需要从第0行开始算，所以y坐标-1
//											if(entity.getReportcode().equals("教基3347") || entity.getReportcode().equals("教基5377")){
//												jzcell_y = Integer.parseInt(jzelmt.attr("y"))-2;//坐标y 配置中的y坐标是从1开始的，excle中需要从第0行开始算，所以y坐标-1
//											}
											
										}
									}
									
									if(StringUtils.isNotEmpty(rulesstr))
									{
										//原始校验公式元素必须为:表号.行号,列号
										List<String> chfruleArr = ruleSplit(rulesstr);
										if(chfruleArr != null && chfruleArr.size() > 0)
										{
											for(String elmt : chfruleArr)
											{
												String[] elmtarr = elmt.split(",");
												String ywx = elmtarr[0];//业务行x
												String ywy = elmtarr[1];//业务行y
												String sjzb = "";//最终在Excel中的坐标,如：A2
												Elements elmlsttmp = document.select("td[ywx="+ywx+"][ywy="+ywy+"]");
												if(elmlsttmp != null && elmlsttmp.size() > 0)
												{
													Element ywelemt = elmlsttmp.get(0);
													if(ywelemt != null)
													{
														//坐标x 配置中的x坐标是从1开始的，excle中1，2行为固定信息列，所以后续所有的x坐标+1，另外还需要加上基准单元格的坐标步长
														int ywelemt_x = Integer.parseInt(ywelemt.attr("x"))+2;
														//坐标y 配置中的y坐标是从1开始的，excle中需要从第0行开始算，所以统计-1，另外还需要加上基准单元格的坐标步长
//														int ywelemt_y = Integer.parseInt(ywelemt.attr("y"))-jzcell_y+1;
														int ywelemt_y = Integer.parseInt(ywelemt.attr("y"));
														String colstr = ExcelColumn.excelColIndexToStr(ywelemt_y);
														sjzb = colstr + String.valueOf(ywelemt_x);//最终在Excel中的坐标,如：A2
													}
												}
												if(!sjzb.equals(""))
												{
													rulesstr = rulesstr.replaceFirst(elmt, sjzb);//不能使用repalaceAll
												}else
												{
													rulesstr = rulesstr.replaceFirst(elmt, "0");
												}
											}
										}
									}
									valtmp = "SUM(" + rulesstr + ")";
									celltmp.setCellStyle(frumstyle);
									celltmp.setCellFormula(valtmp);
								}
							} else 
							{
								//没有带typ属性的为标题单元格
								String btstr = "";// 单元格标题

								if (tdelemt.children() != null
										&& tdelemt.children().size() > 0) {
									btstr = tdelemt.child(0).html();
								} else {
									btstr = tdelemt.html();// 单元格标题
								}
								btstr = btstr.replaceAll("&nbsp;", " ");

								HSSFRow rowtmp;
								if (sheet.getRow(zb_x) == null) {
									rowtmp = sheet.createRow(zb_x);
								} else {
									rowtmp = sheet.getRow(zb_x);
								}
								HSSFCell celltmp;
								if (rowtmp.getCell(zb_y) == null) {
									celltmp = rowtmp.createCell(zb_y);
								} else {
									celltmp = rowtmp.getCell(zb_y);
								}
								celltmp.setCellStyle(basestyle);
								celltmp.setCellValue(btstr);
							}
						}
					}
					
					Elements rowtdlst = document.select("td[rowspan]");
					if (rowtdlst != null && rowtdlst.size() > 0) {
						// 考虑有行合并的情况
						for (Element elttmp : rowtdlst) {
							int zb_x = Integer.parseInt(elttmp.attr("x"))+1;// 坐标x 配置中的x坐标是从1开始的，excle中1，2行为固定信息列，所以后续所有的x坐标+1
							int zb_y = Integer.parseInt(elttmp.attr("y"))-1;// 坐标y 配置中的y坐标是从1开始的，excle中需要从第0行开始算，所以统计-1
							String rowspanstr = elttmp.attr("rowspan");
							if (StringUtils.isNotEmpty(rowspanstr)) {
								int rowspanint = Integer.parseInt(rowspanstr);
								int endrow = zb_x + rowspanint - 1;

								CellRangeAddress cra = new CellRangeAddress(
										zb_x, endrow, zb_y, zb_y);// 合并区域
								HSSFCell celltmp = sheet.getRow(zb_x).getCell(
										zb_y);
								celltmp.setCellStyle(basestyle);
								RegionUtil.setBorderBottom(
										HSSFCellStyle.BORDER_THIN, cra, sheet,
										wb); // 下边框
								RegionUtil.setBorderLeft(
										HSSFCellStyle.BORDER_THIN, cra, sheet,
										wb); // 左边框
								RegionUtil.setBorderRight(
										HSSFCellStyle.BORDER_THIN, cra, sheet,
										wb); // 有边框
								RegionUtil.setBorderTop(
										HSSFCellStyle.BORDER_THIN, cra, sheet,
										wb); // 上边框
								sheet.addMergedRegion(cra);
							}
						}
					}

					Elements coltdlst = document.select("td[colspan]");
					if (coltdlst != null && coltdlst.size() > 0) {
						// 考虑有列合并的情况
						for (Element elttmp : coltdlst) {
							int zb_x = Integer.parseInt(elttmp.attr("x"))+1;// 坐标x 配置中的x坐标是从1开始的，excle中1，2行为固定信息列，所以后续所有的x坐标+1
							int zb_y = Integer.parseInt(elttmp.attr("y"))-1;// 坐标y 配置中的y坐标是从1开始的，excle中需要从第0行开始算，所以统计-1
							String colspanstr = elttmp.attr("colspan");
							if (StringUtils.isNotEmpty(colspanstr)) {
								int colspanint = Integer.parseInt(colspanstr);
								int endcol = (zb_y) + colspanint - 1;

								CellRangeAddress cra = new CellRangeAddress(
										zb_x, zb_x, zb_y, endcol); // 合并区域
								HSSFCell celltmp = sheet.getRow(zb_x).getCell(
										zb_y);
								celltmp.setCellStyle(basestyle);
								RegionUtil.setBorderBottom(
										HSSFCellStyle.BORDER_THIN, cra, sheet,
										wb); // 下边框
								RegionUtil.setBorderLeft(
										HSSFCellStyle.BORDER_THIN, cra, sheet,
										wb); // 左边框
								RegionUtil.setBorderRight(
										HSSFCellStyle.BORDER_THIN, cra, sheet,
										wb); // 有边框
								RegionUtil.setBorderTop(
										HSSFCellStyle.BORDER_THIN, cra, sheet,
										wb); // 上边框
								sheet.addMergedRegion(cra);

							}
						}
					}
					
					
					//隐藏空行开始
					Integer sum = sheet.getLastRowNum();
					for(int row=0;row<=sum;row++){  //row行
						
					    HSSFRow rowObj = sheet.getRow(row);
						
						if (rowObj == null || rowObj.equals("")  || rowObj.getCell(0) == null) {
							rowObj = sheet.createRow(row);
							rowObj.setZeroHeight(true); 
						}
					}
					//隐藏空行结束
					

					output = Struts2Utils.getResponse().getOutputStream();

					Struts2Utils.getResponse().reset();
					String fname = entity.getReportcode() + "导入模板.xls";
					Struts2Utils.getResponse().setHeader(
							"Content-disposition",
							"attachment; filename="
									+ java.net.URLEncoder
											.encode(fname, "UTF-8"));

					Struts2Utils.getResponse().setContentType(
							"application/msexcel");
					wb.setForceFormulaRecalculation(true);
					wb.write(output);
				}
			} catch (Exception e) 
			{
				e.printStackTrace();
			}finally{
				output.flush();
				output.close();
			}
		}
	}
	*/
	 
	/**
	 * 根据输入的表id，执行单表导入
	 * @return
	 */
	public String dotjimps() throws Exception
	{
		Map<String,String> retmap = new HashMap<String, String>();
		String errstr = "";
		
		try
		{
			if(StringUtils.isNotEmpty(id))
			{
				//报表实体类
				entity = (ReportInfo) entityService.getById(id);
				
				succont = 0;
				errcont = 0;
				
				FileInputStream input = new FileInputStream(drfj);
				POIFSFileSystem sf = new POIFSFileSystem(input);
				HSSFWorkbook wb = new HSSFWorkbook(sf);
				HSSFSheet sheet = wb.getSheetAt(0);

				errList = new ArrayList<String>();
				
				reptTable = entity.getReptTable();
				
				if(StringUtils.isNotEmpty(reptTable))
				{
					Document document = Jsoup.parse(reptTable);

					Elements tdlst = document.select("td[id^=td_]");
					
					if (tdlst != null && tdlst.size() > 0) 
					{
						for (Element tdelemt : tdlst) 
						{
							String tdtyp = tdelemt.attr("typ");
							if(StringUtils.isNotEmpty(tdtyp))
							{
								//只针对填写的取值，汇总的不处理
								if(tdtyp.equals("numb") || tdtyp.equals("textstr") || tdtyp.equals("selectstr") )
								{
									//配置中的x,y坐标
									int zb_x = Integer.parseInt(tdelemt.attr("x"))+1;// 坐标x 配置中的x坐标是从1开始的，excle中1，2行为固定信息列，所以后续所有的x坐标+1
									int zb_y = Integer.parseInt(tdelemt.attr("y"))-1;// 坐标y 配置中的y坐标是从1开始的，excle中需要从第0行开始算，所以统计-1
									
									HSSFRow rowtmp = sheet.getRow(zb_x);
									HSSFCell celltmp = null;
									if(rowtmp != null)
									{
										celltmp = rowtmp.getCell(zb_y);
									}
									if(celltmp != null)
									{
										String cellstr = celltmp.toString().trim();
										if(StringUtils.isNotEmpty(cellstr))
										{
											//把最后2位是.0的去掉
											String endstr = cellstr.substring(cellstr.length()-2);
											if(endstr.equals(".0"))
											{
												cellstr = cellstr.replaceAll("\\.0", "");
											}
											if(tdtyp.equals("numb") ){
												boolean ifnumb = false;//判断一下是否是数字
												try
												{
													double tmpd = Double.parseDouble(cellstr); 
													ifnumb = true;
												}catch(Exception e)
												{
													ifnumb = false;
												}
												if(ifnumb)
													retmap.put(tdelemt.attr("x")+","+tdelemt.attr("y"), cellstr);
											}else{
												retmap.put(tdelemt.attr("x")+","+tdelemt.attr("y"), cellstr);
											}
											
										}
									}
								}
							}
							
						}
					}
				}
				
			}else{
				errstr = "非法参数";
			}
		} catch (Exception e) {
			errstr = "程序异常，请联系管理员";
		}

		JSONObject jsonObject = JSONObject.fromObject(retmap);
		
		bystr1 = jsonObject.toString();
		
		return "impExcleMain";
	}
	/**
	 * 
	 * 公式拆分
	 * 
	 */
	public List ruleSplit(String orgrule) 
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
	
	public void getTabByid() {
		String rptid = Struts2Utils.getParameter("rptid");
		rwid = Struts2Utils.getParameter("rwid");
		if(StringUtils.isNotEmpty(rptid) ){
			
			//找到用户填的表，找不到就不过录
		
			
			entity = entityService.getById(rptid);
			
			if(entity.getConfigytype().equals("2")){
				String sqlstr = " select reptTable from  "+entity.getJgbmc1()+" where nianbgl = '"+rwid+"'   ";
				
				
				if(entity.getReportcode().equals("教基1001")){//表单类型  1：教基1001  2：教基1102   3：教基1102（续表） 4.教基1203
					sqlstr +=" and leix = '1' ";
				}
				if(entity.getReportcode().equals("教基1102")){
					sqlstr +=" and leix = '2' ";
				}
				if(entity.getReportcode().equals("教基1102（续表）")){
					sqlstr +=" and leix = '3' ";
				}
				if(entity.getReportcode().equals("教基1203")){
					sqlstr +=" and leix = '4' ";
				}
				
				List tabList = entityDao.getSession().createSQLQuery(sqlstr).list();
				if(tabList != null && tabList.size() > 0){
					Struts2Utils.renderText(tabList.get(0).toString());
				} 
				
			}else{
				String sql = " 	select reptTable from t_config_reportinfo where id = '"+rptid+"' ";
				List tabList = entityDao.getSession().createSQLQuery(sql).list();
				if(tabList != null && tabList.size() > 0 && !tabList.get(0).equals("null")){
					Struts2Utils.renderText(tabList.get(0).toString());
				} 
			}
			
			
			
		}
		
	}
	//报表复制页面
	public String openfzym() {
		idstr = Struts2Utils.getParameter("idstr");
		return "openfzym";
	}
	public String bbfzgn() {
		String bbnf = Struts2Utils.getParameter("bbnf");
		String bbh = Struts2Utils.getParameter("bbh");
		
		if(StringUtils.isNotEmpty(idstr) && StringUtils.isNotEmpty(bbnf) && StringUtils.isNotEmpty(bbh)){
			
			List<String> bbidList = Struts2Utils.split(idstr, ",");
			List<PropertyFilter> filters = new ArrayList<>();
			filters.add(new PropertyFilter("INC_id",bbidList));
			List<ReportInfo> rptList =	entityService.find(filters);
			
			for (ReportInfo info : rptList) {
				
				ReportInfo rpt = new ReportInfo();
				
				rpt.setVersionh(bbh);
				
				rpt.setDm(info.getDm());
				
				rpt.setReportcode(info.getReportcode());
				
				rpt.setReportname(info.getReportname());
				
				rpt.setReportlx(info.getReportlx());
				
				rpt.setSequenceNumber(info.getSequenceNumber());
				
				rpt.setConfigytype(info.getConfigytype());
				
				rpt.setCustomURL(info.getCustomURL());
				
				rpt.setCustomURLDetails(info.getCustomURLDetails());
				
				rpt.setCustomURLShenh(info.getCustomURLShenh());
				
				rpt.setCustomURLzdzxcx(info.getCustomURLzdzxcx());
				
				rpt.setRowNumber(info.getRowNumber());
				
				rpt.setColumnNumber(info.getColumnNumber());
				
				rpt.setExplains(info.getExplains());
				
				rpt.setReportDescribe(info.getReportDescribe());
				
				rpt.setVerifyRelations(info.getVerifyRelations());
				
				rpt.setSyxxmcs(info.getSyxxmcs());
				
				rpt.setSyxxids(info.getSyxxids());
				
				rpt.setBz(info.getBz());
				
				rpt.setReptTable(info.getReptTable());
				
				rpt.setReptTableHead(info.getReptTableHead());
				
				rpt.setJldw(info.getJldw());
				
				rpt.setJgbmc1(info.getJgbmc1());
				
				rpt.setJgbmc2(info.getJgbmc2());
				
				rpt.setSffxqtb(info.getSffxqtb());
				
				rpt.setSffzxy(info.getSffzxy());
				
				rpt.setPzggsj(info.getPzggsj());

				rpt.setPzgguser(info.getPzgguser());
				
				rpt.setDcmbwjm(info.getDcmbwjm());
				
				rpt.setDcmbscsj(info.getDcmbscsj());
				
				rpt.setDcmbscuser(info.getDcmbscuser());
				
				rpt.setDrmbwjm(info.getDrmbwjm());
				
				rpt.setDrmbscsj(info.getDrmbscsj());
				
				rpt.setDrmbscuser(info.getDrmbscuser());
				
				rpt.setBbnf(bbnf);
				
				rpt.setBbgs(info.getBbgs());
				
				rpt.setSfyx("1");
				
				//rpt.setRowFilterSet(rowFilters);
				entityService.save(rpt);
				
				//添加行过滤
				for (ReportInfoRowFilter oldrow : info.getRowFilterSet()) {
					
					ReportInfoRowFilter rowf = new ReportInfoRowFilter();
					
					rowf.setReportinfo(rpt);
					
					rowf.setSsbxlx(oldrow.getSsbxlx());
					
					rowf.setHanghs(oldrow.getHanghs());
					
					rowf.setBz(oldrow.getBz());
					
					reportInfoRowFilterService.save(rowf);
					
				}
			}
			
		}
		
		
		return "layeopen";
	}
	//规则配置页面
	public String bdgzpz() {
		String bbid = Struts2Utils.getParameter("bbid");
		
		if(StringUtils.isNotEmpty(bbid)){
			entity = entityService.getById(bbid);
			
			reqlist = entityService.findbyhql(" from ReportInfo a where a.reportcode = '"+entity.getReportcode()+"' order by bbnf desc ");
			
		}
		
		return "bdgzpz";
	}
	//规则配置页面
	public String fzctgzpz() {
		String bbid = Struts2Utils.getParameter("bbid");
		
		if(StringUtils.isNotEmpty(bbid)){
			entity = entityService.getById(bbid);
			
			reqlist = entityService.findbyhql(" from ReportInfo a where a.reportcode = '"+entity.getReportcode()+"' order by bbnf desc ");
			
		}
		
		return "fzctgzpz";
	}
	//规则配置保存
	public void insertgzpz() {
		
		String bbid = Struts2Utils.getParameter("bbid");
		String checkstr = Struts2Utils.getParameter("checkstr");
		
		if(StringUtils.isNotEmpty(bbid) && StringUtils.isNotEmpty(checkstr)){
 			ReportInfo ent = entityService.getById(bbid);
 			ent.setBdgzpz(checkstr);
 			entityService.update(ent);
		}
		Struts2Utils.renderText(checkstr);
		
	}
	//复制填充规则配置保存
	public void insertfztcgzpz() {
		
		String bbid = Struts2Utils.getParameter("bbid");
		String checkstr = Struts2Utils.getParameter("checkstr");
		
		if(StringUtils.isNotEmpty(bbid) && StringUtils.isNotEmpty(checkstr)){
			ReportInfo ent = entityService.getById(bbid);
			ent.setFztcgzpz(checkstr);
			entityService.update(ent);
		}
		Struts2Utils.renderText(checkstr);
		
	}
	public void czgz() {
		String bbid = Struts2Utils.getParameter("bbid");
		if(StringUtils.isNotEmpty(bbid)){
 			
 			ReportInfo ent = entityService.getById(bbid);
 			ent.setBdgzpz(null);
 			entityService.update(ent);
		}
		Struts2Utils.renderText("重置成功！");
		
	}
	
	public void getreptBycode() throws Exception {
		
		String bbid = Struts2Utils.getParameter("bbid");
		String resultinfo = "";
		if(StringUtils.isNotEmpty(bbid)){
			
			ReportInfo rpt = entityService.getById(bbid);
			List<ReportInfo> rptList = entityService.findbyhql(" from ReportInfo a where a.reportcode = '"+rpt.getReportcode()+"' order by bbnf desc ");//and a.id != '"+rpt.getReportcode()+"'
			if(rptList != null && rptList.size() > 0 ){
				resultinfo = Struts2Utils.listToJson(rptList, "id","reportname","versionh","bbnf");
			}
		}
		Struts2Utils.renderText(resultinfo);		
	}
	
	
	
	
	
	
	
	
	
}
