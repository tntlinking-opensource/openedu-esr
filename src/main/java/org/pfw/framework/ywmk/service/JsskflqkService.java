package org.pfw.framework.ywmk.service;

import java.util.List;
import java.util.LinkedHashMap;

import org.pfw.framework.ywmk.domain.Jsskflqk;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;



public interface JsskflqkService {

	Jsskflqk getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<Jsskflqk> find(List<PropertyFilter> filters);
	
	List<Jsskflqk> find(List<PropertyFilter> filters,LinkedHashMap orderbyMap);
	
	void save(Jsskflqk entity);
	
	void update(Jsskflqk entity);

	void deleteById(String id);
	
	void delete(List<String> checks);
	
	void deleteHql(String hql,Object...val);

	List<Jsskflqk> findAll();
	
	List<Jsskflqk> findbyhql(String hql,Object...val);
	
	List<Jsskflqk> findBy(String propertyName, Object value, MatchType matchType);
}
