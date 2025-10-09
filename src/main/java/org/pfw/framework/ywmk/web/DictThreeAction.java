package org.pfw.framework.ywmk.web;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.xtgl.service.DictService;
import org.pfw.framework.ywmk.domain.Bjxx;
import org.pfw.framework.ywmk.domain.DictThree;
import org.pfw.framework.ywmk.domain.DictTwo;
import org.pfw.framework.ywmk.service.BjxxService;
import org.pfw.framework.ywmk.service.DictThreeService;
import org.pfw.framework.ywmk.service.DictTwoService;
import org.pfw.framework.ywmk.service.XxxqService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能管理Action.
 */
@Results
	({ 
		@Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl" , type="freemarker"),
		@Result(name ="layerForparent",location="/util/winopenlayerForparent.ftl", type = "freemarker"),
		@Result(name="success",location="/ywmk/dictthree-list.ftl",type = "freemarker"),
		@Result(name ="input",location="/ywmk/dictthree-input.ftl", type = "freemarker")
	})
public class DictThreeAction extends CrudActionSupport<DictThree> {
	@Autowired
	protected DictThreeService entityService;
	@Autowired
	protected DictTwoService dictTwoService;

	
	private List reqlist;
	private List twoList;

	public List getTwoList() {
		return twoList;
	}

	public void setTwoList(List twoList) {
		this.twoList = twoList;
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
		twoList = dictTwoService.findAll();
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
		twoList = dictTwoService.findAll();
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (!StringUtils.isEmpty(id)) {
			entity =  entityService.getById(id);
		} else {
			entity = new DictThree();
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
