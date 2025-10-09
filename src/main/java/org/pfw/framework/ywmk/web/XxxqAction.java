package org.pfw.framework.ywmk.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.ywmk.domain.Xxxq;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.ywmk.domain.Xxxx;
import org.pfw.framework.ywmk.service.XxxqService;
import org.pfw.framework.ywmk.service.XxxxService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 功能管理Action.
 */
@Results
	({ 
		@Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl" , type="freemarker"),
		@Result(name ="layerForparent",location="/util/winopenlayerForparent.ftl", type = "freemarker"),
		@Result(name="success",location="/ywmk/xxxq-list.ftl",type = "freemarker"),
		@Result(name ="input",location= "/ywmk/xxxq-input.ftl", type = "freemarker")
	})
public class XxxqAction extends CrudActionSupport<Xxxq> {
	@Autowired
	protected XxxqService entityService;

	@Autowired
	protected XxxxService xxxxService;

	private  Xxxx xxxx;
	private List<Xxxx> xxxxList;
	private List reqlist;
	public List getReqlist() {
		return reqlist;
	}
	public void setReqlist(List reqlist) {
		this.reqlist = reqlist;
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
		xxxxList =	xxxxService.findAll();
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
			entity = (Xxxq) entityService.getById(id);
		} else {
			entity = new Xxxq();
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

		String kword = Struts2Utils.getParameter("kword");
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();

		if(StringUtils.isNotEmpty(kword))
		{
			PropertyFilter pft = new PropertyFilter("LIKES_xqmc_OR_xqdm",kword);
			filters.add(pft);
		}

		List<Xxxq> retlst = entityService.find(filters);

		String retstr = Struts2Utils.listToJson(retlst, "id","xqmc","xqdm");

		Struts2Utils.renderText(retstr);

	}

	public  void getxxlxjson() throws Exception {
 
		Xxxx xxxx = xxxxService.findAll().get(0);
		
		Struts2Utils.renderText(xxxx.getBxlxid());
	}
}
