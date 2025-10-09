package org.pfw.framework.ywmk.web;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.xtgl.service.DictService;
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
		@Result(name ="layerForparent",location="/util/winopenlayerForparent.ftl", type = "freemarker"),
		@Result(name="success",location="/ywmk/report_info_row_filter-list.ftl",type = "freemarker"),
		@Result(name ="input",location="/ywmk/report_info_row_filter-input.ftl", type = "freemarker")
	})
public class ReportInfoRowFilterAction extends CrudActionSupport<ReportInfoRowFilter> {
	@Autowired
	protected ReportInfoRowFilterService entityService;
	@Autowired
	protected ReportInfoService rptInfoService;
	@Autowired
	protected DictService dctService;
	
	private List reqlist;
	private String bbid;
	private ReportInfo rptinfo;
	
	public ReportInfo getRptinfo() {
		return rptinfo;
	}
	public void setRptinfo(ReportInfo rptinfo) {
		this.rptinfo = rptinfo;
	}
	public String getBbid() {
		return bbid;
	}
	public void setBbid(String bbid) {
		this.bbid = bbid;
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
	public String input() throws Exception 
	{
		reqlist = dctService.findAllByDM("banxlx");
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		
		if(StringUtils.isNotBlank(bbid))
		{
			rptinfo = rptInfoService.getById(bbid);
			List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
			
			PropertyFilter pft = new PropertyFilter("EQS_reportinfo.id",bbid);
			filters.add(pft);
			
			//设置默认排序方式
			page.setOrderBy("hanghs");
			page.setOrder(Page.ASC);
			
			page = entityService.findPage(page, filters);
		}
		
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (!StringUtils.isEmpty(id)) {
			entity = (ReportInfoRowFilter) entityService.getById(id);
		} else {
			entity = new ReportInfoRowFilter();
		}
		
	}

	@Override
	public String save() throws Exception 
	{
		if(StringUtils.isNotBlank(bbid))
		{
			ReportInfo rptinfo = new ReportInfo();
			rptinfo.setId(bbid);
			entity.setReportinfo(rptinfo);
			
			if (!StringUtils.isEmpty(id))
			{
				entityService.update(entity);
			}else{
				entityService.save(entity);
			}
			Struts2Utils.setPromptInfoToReq("保存成功!!");
		}
		
		return RELOAD;
	}
}
