package org.pfw.framework.ywmk.web;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.xtgl.domain.Dict;
import org.pfw.framework.xtgl.service.DictService;
import org.pfw.framework.ywmk.domain.DictTwo;
import org.pfw.framework.ywmk.service.DictTwoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 功能管理Action.
 */
@Results
	({ 
		@Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl" , type="freemarker"),
		@Result(name ="layerForparent",location="/util/winopenlayerForparent.ftl", type = "freemarker"),
		@Result(name="success",location="/ywmk/dicttwo-list.ftl",type = "freemarker"),
		@Result(name ="input",location="/ywmk/dicttwo-input.ftl", type = "freemarker")
	})
public class DictTwoAction extends CrudActionSupport<DictTwo> {
	@Autowired
	protected DictTwoService entityService;
	@Autowired
	protected DictService dictService;

	private List reqlist;
	private List<Dict> zhidList;

	public List getZhidList() {
		return zhidList;
	}

	public void setZhidList(List zhidList) {
		this.zhidList = zhidList;
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
			for (String id : checks) {
				DictTwo bean = entityService.getById(id);
				bean.setSfyx("0");
				entityService.update(bean);
			}
		}
		Struts2Utils.renderText("删除成功");
		return null;
	}

	@Override
	public String input() throws Exception {
		zhidList = dictService.getDictByhql("  from Dict where  parent is not null");
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
		zhidList = dictService.getDictByhql("  from Dict where  parent is not null");
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (!StringUtils.isEmpty(id)) {
			entity =  entityService.getById(id);
		} else {
			entity = new DictTwo();
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
}
