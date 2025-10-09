package org.pfw.framework.ywmk.web;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.xtgl.service.DictService;
import org.pfw.framework.ywmk.domain.Bbqzpz;
import org.pfw.framework.ywmk.domain.ReportInfo;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.ywmk.service.BbqzpzService;
import org.pfw.framework.ywmk.service.ReportInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 功能管理Action.
 */
@Results
	({ 
		@Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl" , type="freemarker"),
		@Result(name ="layerForparent",location="/util/winopenlayerForparent.ftl", type = "freemarker"),
		@Result(name="success",location="/ywmk/bbqzpz-list.ftl",type = "freemarker"),
		@Result(name ="input",location="/ywmk/bbqzpz-input.ftl", type = "freemarker")
	})
public class BbqzpzAction extends CrudActionSupport<Bbqzpz> {
	@Autowired
	protected BbqzpzService entityService;
	@Autowired
	private DictService dictService;
	@Autowired
	private ReportInfoService reportInfoService;
	private List reqlist;
	private List bxlxList;
	private List bbList;
	
	
	public List getBbList() {
		return bbList;
	}
	public void setBbList(List bbList) {
		this.bbList = bbList;
	}
	public List getBxlxList() {
		return bxlxList;
	}
	public void setBxlxList(List bxlxList) {
		this.bxlxList = bxlxList;
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
		bxlxList = dictService.findAllByDM("banxlx");
		bbList = reportInfoService.findbyhql(" from ReportInfo a where a.sfyx = '1' ");
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
			entity = (Bbqzpz) entityService.getById(id);
		} else {
			entity = new Bbqzpz();
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
}
