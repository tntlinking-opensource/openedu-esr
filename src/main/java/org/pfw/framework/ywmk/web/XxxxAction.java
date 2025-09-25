package org.pfw.framework.ywmk.web;

import java.util.ArrayList;
import java.util.List;

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
import org.pfw.framework.ywmk.domain.Sjrw;
import org.pfw.framework.ywmk.domain.Xxxx;
import org.pfw.framework.ywmk.service.SjrwService;
import org.pfw.framework.ywmk.service.XxxxService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 功能管理Action.
 */
@Results
	({ 
		@Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl" , type="freemarker"),
		@Result(name ="layerForparent",location="/util/winopenlayerForparent.ftl", type = "freemarker"),
		@Result(name="success",location="/ywmk/xxxx-list.ftl",type = "freemarker"),
		@Result(name="shujdr",location="/ywmk/shujdr-list.ftl",type = "freemarker"),
		@Result(name ="input1",location="/ywmk/xxxx-input1.ftl", type = "freemarker")
	})
public class XxxxAction extends CrudActionSupport<Xxxx> {
	@Autowired
	protected XxxxService entityService;
	@Autowired
	protected DictService dctService;
	@Autowired
	protected SjrwService service;

	private List<Dict> reqlist4;
	private List reqlist;
	private List<Dict> zdzxList;
	private List<Dict> fsbxlxList;
	private List<String> fslxchecks;
	private Sjrw sjrw;

	public Sjrw getSjrw() {
		return sjrw;
	}

	public void setSjrw(Sjrw sjrw) {
		this.sjrw = sjrw;
	}

	public List<String> getFslxchecks() {
		return fslxchecks;
	}

	public void setFslxchecks(List<String> fslxchecks) {
		this.fslxchecks = fslxchecks;
	}

	public List<Dict> getFsbxlxList() {
		return fsbxlxList;
	}

	public void setFsbxlxList(List<Dict> fsbxlxList) {
		this.fsbxlxList = fsbxlxList;
	}

	public List getZdzxList() {
		return zdzxList;
	}

	public void setZdzxList(List zdzxList) {
		this.zdzxList = zdzxList;
	}

	public List<Dict> getReqlist4() {
		return reqlist4;
	}

	public void setReqlist4(List<Dict> reqlist4) {
		this.reqlist4 = reqlist4;
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
	public String list() throws Exception {

		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		page = entityService.findPage(page, filters);
		zdzxList = dctService.findAllByDM("banxlx");

		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (!StringUtils.isEmpty(id)) {
			entity = (Xxxx) entityService.getById(id);
		} else {
			entity = new Xxxx();
		}
		
	}

	@Override
	public String save() throws Exception {
		String bxlx = Struts2Utils.getParameter("bxlx");
		String fslxchecks = Struts2Utils.getParameter("fslxchecks");
		if(StringUtils.isNotEmpty(bxlx) && StringUtils.isNotEmpty(fslxchecks)){
			entity.setBxlxid(bxlx + "," + fslxchecks);
			Dict bxlxDict = dctService.getById(bxlx);
			Dict fslxDict = dctService.getById(fslxchecks);
			if (bxlxDict != null && fslxDict !=null){
				entity.setXxlxmc(bxlxDict.getName()+","+fslxDict.getName());
			}else if (bxlxDict != null){
				entity.setXxlxmc(bxlxDict.getName());
			}else if(fslxDict !=null){
				entity.setXxlxmc(fslxDict.getName());
			}
		}else if (StringUtils.isNotEmpty(bxlx)) {
			Dict bxlxDict = dctService.getById(bxlx);
			if (bxlxDict != null){
				entity.setXxlxmc(bxlxDict.getName());
			}
			entity.setBxlxid(bxlx);
		}else if (StringUtils.isNotEmpty(fslxchecks)) {
			Dict fslxDict = dctService.getById(fslxchecks);
			if(fslxDict !=null){
				entity.setXxlxmc(fslxDict.getName());
			}
			entity.setBxlxid(fslxchecks);
		}
		if (!StringUtils.isEmpty(id))
		{
			entityService.update(entity);
		}else{
			entityService.save(entity);
		}
		Struts2Utils.setPromptInfoToReq("保存成功!!");

		return input1();
	}

	public void getdictjson() throws Exception {

		String kword = Struts2Utils.getParameter("kword");
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();

		if(StringUtils.isNotEmpty(kword))
		{
			PropertyFilter pft = new PropertyFilter("LIKES_xxmc",kword);
			filters.add(pft);
		}

		List<Xxxx> retlst = entityService.find(filters);

		String retstr = Struts2Utils.listToJson(retlst, "id","xxmc");

		Struts2Utils.renderText(retstr);

	}
	public String input1() throws Exception {
		reqlist4 = dctService.findAllByDM("banxlx");
		List<Xxxx> allList = entityService.findAll();
		if(allList != null && allList.size() > 0){
			entity = allList.get(0);
		}else{
			entity = new Xxxx();
		}
		fsbxlxList = new ArrayList<Dict>();
		for (int i = 0; i < reqlist4.size(); i++) {
			Dict dict = reqlist4.get(i);
			String name = dict.getName();
			if (name.contains("附设")){
				fsbxlxList.add(dict);
			}
		}
		for (int i = 0; i < reqlist4.size(); i++) {
			Dict dict = reqlist4.get(i);
			String name = dict.getName();
			if (name.contains("附设")){
				reqlist4.remove(dict);
			}
		}

		return "input1";
	}

	public String shujdr() throws Exception {
		String sjrwid = Struts2Utils.getParameter("sjrwid");
		 sjrw = service.getById(sjrwid);
		return "shujdr";
	}
	public String baoc() throws Exception {
		String xxdm = Struts2Utils.getParameter("xxdm");
		String xxmc = Struts2Utils.getParameter("xxmc");
		String bz = Struts2Utils.getParameter("bz");
		
		
		prepareModel();
		
		
		entity.setXxdm(xxdm);
		entity.setXxmc(xxmc);
		entity.setBz(bz);
		
		
		String bxlx = Struts2Utils.getParameter("bxlx");
		String fslxchecks = Struts2Utils.getParameter("fslxchecks");
		if(StringUtils.isNotEmpty(bxlx) && StringUtils.isNotEmpty(fslxchecks)){
			entity.setBxlxid(bxlx + "," + fslxchecks);
			Dict bxlxDict = dctService.getById(bxlx);
			Dict fslxDict = dctService.getById(fslxchecks);
			if (bxlxDict != null && fslxDict !=null){
				entity.setXxlxmc(bxlxDict.getName()+","+fslxDict.getName());
			}else if (bxlxDict != null){
				entity.setXxlxmc(bxlxDict.getName());
			}else if(fslxDict !=null){
				entity.setXxlxmc(fslxDict.getName());
			}
		}else if (StringUtils.isNotEmpty(bxlx)) {
			Dict bxlxDict = dctService.getById(bxlx);
			if (bxlxDict != null){
				entity.setXxlxmc(bxlxDict.getName());
			}
			entity.setBxlxid(bxlx);
		}else if (StringUtils.isNotEmpty(fslxchecks)) {
			Dict fslxDict = dctService.getById(fslxchecks);
			if(fslxDict !=null){
				entity.setXxlxmc(fslxDict.getName());
			}
			entity.setBxlxid(fslxchecks);
		}
		if (!StringUtils.isEmpty(id))
		{
			entityService.update(entity);
		}else{
			entityService.save(entity);
		}
		Struts2Utils.setPromptInfoToReq("保存成功!!");

		return input1();
	}
}
