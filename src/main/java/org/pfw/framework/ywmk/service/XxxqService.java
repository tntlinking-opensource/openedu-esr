package org.pfw.framework.ywmk.service;

import java.util.List;
import java.util.LinkedHashMap;

import org.pfw.framework.ywmk.domain.Xxxq;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;



public interface XxxqService {

	Xxxq getById(String id);

	Xxxq getByxqmc(String xqmc);

	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<Xxxq> find(List<PropertyFilter> filters);
	
	List<Xxxq> find(List<PropertyFilter> filters,LinkedHashMap orderbyMap);
	
	void save(Xxxq entity);
	
	void update(Xxxq entity);

	void deleteById(String id);
	
	void delete(List<String> checks);
	
	void deleteHql(String hql,Object...val);

	List<Xxxq> findAll();
	
	List<Xxxq> findbyhql(String hql,Object...val);
	
	List<Xxxq> findBy(String propertyName, Object value, MatchType matchType);
}
