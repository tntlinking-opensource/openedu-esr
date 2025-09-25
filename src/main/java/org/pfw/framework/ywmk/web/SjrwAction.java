package org.pfw.framework.ywmk.web;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.ywmk.domain.Sjrw;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.ywmk.service.SjrwService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 功能管理Action.
 */
@Results
	({ 
		@Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl" , type="freemarker"),
		@Result(name ="layerForparent",location="/util/winopenlayerForparent.ftl", type = "freemarker"),
		@Result(name="success",location="/ywmk/sjrw-list.ftl",type = "freemarker"),
		@Result(name ="input",location="/ywmk/sjrw-input.ftl", type = "freemarker")
	})
public class SjrwAction extends CrudActionSupport<Sjrw> {
	@Autowired
	protected SjrwService entityService;
	
	private List reqlist;
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
			entity = (Sjrw) entityService.getById(id);
		} else {
			entity = new Sjrw();
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
