package org.pfw.framework.ywmk.service;

import java.util.List;
import java.util.LinkedHashMap;

import org.pfw.framework.ywmk.domain.Xiaoshexx;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;



public interface XiaoshexxService {

	Xiaoshexx getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<Xiaoshexx> find(List<PropertyFilter> filters);
	
	List<Xiaoshexx> find(List<PropertyFilter> filters,LinkedHashMap orderbyMap);
	
	void save(Xiaoshexx entity);
	
	void update(Xiaoshexx entity);

	void deleteById(String id);
	
	void delete(List<String> checks);
	
	void deleteHql(String hql,Object...val);

	List<Xiaoshexx> findAll();
	
	List<Xiaoshexx> findbyhql(String hql,Object...val);
	
	List<Xiaoshexx> findBy(String propertyName, Object value, MatchType matchType);
}
