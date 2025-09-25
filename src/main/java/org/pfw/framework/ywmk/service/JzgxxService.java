package org.pfw.framework.ywmk.service;

import java.util.List;
import java.util.LinkedHashMap;

import org.pfw.framework.ywmk.domain.Jzgxx;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;



public interface JzgxxService {

	Jzgxx getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<Jzgxx> find(List<PropertyFilter> filters);
	
	List<Jzgxx> find(List<PropertyFilter> filters,LinkedHashMap orderbyMap);
	
	void save(Jzgxx entity);
	
	void update(Jzgxx entity);

	void deleteById(String id);
	
	void delete(List<String> checks);
	
	void deleteHql(String hql,Object...val);

	List<Jzgxx> findAll();
	
	List<Jzgxx> findbyhql(String hql,Object...val);
	
	List<Jzgxx> findBy(String propertyName, Object value, MatchType matchType);
}
