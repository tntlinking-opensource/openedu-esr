package org.pfw.framework.ywmk.service;

import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

import org.pfw.framework.ywmk.domain.ReportInfo;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;



public interface ReportInfoService {

	ReportInfo getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<ReportInfo> find(List<PropertyFilter> filters);
	
	List<ReportInfo> find(List<PropertyFilter> filters,LinkedHashMap orderbyMap);
	
	void save(ReportInfo entity);
	
	void update(ReportInfo entity);

	void deleteById(String id);
	
	void delete(List<String> checks);
	
	void deleteHql(String hql,Object...val);

	List<ReportInfo> findAll();
	
	List<ReportInfo> findbyhql(String hql,Object...val);
	
	List<ReportInfo> findBy(String propertyName, Object value, MatchType matchType);
	
	Boolean saveReport(Map map,String bbid,String tabblestr);
	
	void savReport();
	
	ReportInfo getBydm(String dm);
}
