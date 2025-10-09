package org.pfw.framework.ywmk.service;

import java.util.List;
import java.util.LinkedHashMap;

import org.pfw.framework.ywmk.domain.Xsxx;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;



public interface XsxxService {

	Xsxx getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<Xsxx> find(List<PropertyFilter> filters);
	
	List<Xsxx> find(List<PropertyFilter> filters,LinkedHashMap orderbyMap);
	
	void save(Xsxx entity);
	
	void update(Xsxx entity);

	void deleteById(String id);
	
	void delete(List<String> checks);
	
	void deleteHql(String hql,Object...val);

	List<Xsxx> findAll();
	
	List<Xsxx> findbyhql(String hql,Object...val);
	
	List<Xsxx> findBy(String propertyName, Object value, MatchType matchType);

}
