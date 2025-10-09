package org.pfw.framework.ywmk.service;

import java.util.List;
import java.util.LinkedHashMap;

import org.pfw.framework.ywmk.domain.Dwpxqk;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;



public interface DwpxqkService {

	Dwpxqk getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<Dwpxqk> find(List<PropertyFilter> filters);
	
	List<Dwpxqk> find(List<PropertyFilter> filters,LinkedHashMap orderbyMap);
	
	void save(Dwpxqk entity);
	
	void update(Dwpxqk entity);

	void deleteById(String id);
	
	void delete(List<String> checks);
	
	void deleteHql(String hql,Object...val);

	List<Dwpxqk> findAll();
	
	List<Dwpxqk> findbyhql(String hql,Object...val);
	
	List<Dwpxqk> findBy(String propertyName, Object value, MatchType matchType);
}
