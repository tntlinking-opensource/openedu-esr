package org.pfw.framework.ywmk.service;

import java.util.List;
import java.util.LinkedHashMap;

import org.pfw.framework.ywmk.domain.ReportInfoRowFilter;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;



public interface ReportInfoRowFilterService {

	ReportInfoRowFilter getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<ReportInfoRowFilter> find(List<PropertyFilter> filters);
	
	List<ReportInfoRowFilter> find(List<PropertyFilter> filters,LinkedHashMap orderbyMap);
	
	void save(ReportInfoRowFilter entity);
	
	void update(ReportInfoRowFilter entity);

	void deleteById(String id);
	
	void delete(List<String> checks);
	
	void deleteHql(String hql,Object...val);

	List<ReportInfoRowFilter> findAll();
	
	List<ReportInfoRowFilter> findbyhql(String hql,Object...val);
	
	List<ReportInfoRowFilter> findBy(String propertyName, Object value, MatchType matchType);
}
