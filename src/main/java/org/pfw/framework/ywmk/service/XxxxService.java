package org.pfw.framework.ywmk.service;

import java.util.List;
import java.util.LinkedHashMap;

import org.pfw.framework.ywmk.domain.Xxxx;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;



public interface XxxxService {

	Xxxx getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<Xxxx> find(List<PropertyFilter> filters);
	
	List<Xxxx> find(List<PropertyFilter> filters,LinkedHashMap orderbyMap);
	
	void save(Xxxx entity);
	
	void update(Xxxx entity);

	void deleteById(String id);
	
	void delete(List<String> checks);
	
	void deleteHql(String hql,Object...val);

	List<Xxxx> findAll();
	
	List<Xxxx> findbyhql(String hql,Object...val);
	
	List<Xxxx> findBy(String propertyName, Object value, MatchType matchType);
}
