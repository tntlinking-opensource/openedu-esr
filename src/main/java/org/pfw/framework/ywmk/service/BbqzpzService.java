package org.pfw.framework.ywmk.service;

import java.util.List;
import java.util.LinkedHashMap;

import org.pfw.framework.ywmk.domain.Bbqzpz;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;



public interface BbqzpzService {

	Bbqzpz getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<Bbqzpz> find(List<PropertyFilter> filters);
	
	List<Bbqzpz> find(List<PropertyFilter> filters,LinkedHashMap orderbyMap);
	
	void save(Bbqzpz entity);
	
	void update(Bbqzpz entity);

	void deleteById(String id);
	
	void delete(List<String> checks);
	
	void deleteHql(String hql,Object...val);

	List<Bbqzpz> findAll();
	
	List<Bbqzpz> findbyhql(String hql,Object...val);
	
	List<Bbqzpz> findBy(String propertyName, Object value, MatchType matchType);
}
