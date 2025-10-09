package org.pfw.framework.ywmk.service;

import java.util.List;
import java.util.LinkedHashMap;

import org.pfw.framework.ywmk.domain.Zrjspxqk;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;



public interface ZrjspxqkService {

	Zrjspxqk getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<Zrjspxqk> find(List<PropertyFilter> filters);
	
	List<Zrjspxqk> find(List<PropertyFilter> filters,LinkedHashMap orderbyMap);
	
	void save(Zrjspxqk entity);
	
	void update(Zrjspxqk entity);

	void deleteById(String id);
	
	void delete(List<String> checks);
	
	void deleteHql(String hql,Object...val);

	List<Zrjspxqk> findAll();
	
	List<Zrjspxqk> findbyhql(String hql,Object...val);
	
	List<Zrjspxqk> findBy(String propertyName, Object value, MatchType matchType);
}
