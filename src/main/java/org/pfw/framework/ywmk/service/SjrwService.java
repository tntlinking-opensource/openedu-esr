package org.pfw.framework.ywmk.service;

import java.util.List;
import java.util.LinkedHashMap;

import org.pfw.framework.ywmk.domain.Sjrw;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;



public interface SjrwService {

	Sjrw getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<Sjrw> find(List<PropertyFilter> filters);
	
	List<Sjrw> find(List<PropertyFilter> filters,LinkedHashMap orderbyMap);
	
	void save(Sjrw entity);
	
	void update(Sjrw entity);

	void deleteById(String id);
	
	void delete(List<String> checks);
	
	void deleteHql(String hql,Object...val);

	List<Sjrw> findAll();
	
	List<Sjrw> findbyhql(String hql,Object...val);
	
	List<Sjrw> findBy(String propertyName, Object value, MatchType matchType);
}
