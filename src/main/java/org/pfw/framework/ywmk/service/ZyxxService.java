package org.pfw.framework.ywmk.service;

import java.util.List;
import java.util.LinkedHashMap;

import org.pfw.framework.ywmk.domain.Zyxx;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;



public interface ZyxxService {

	Zyxx getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<Zyxx> find(List<PropertyFilter> filters);
	
	List<Zyxx> find(List<PropertyFilter> filters,LinkedHashMap orderbyMap);
	
	void save(Zyxx entity);
	
	void update(Zyxx entity);

	void deleteById(String id);
	
	void delete(List<String> checks);
	
	void deleteHql(String hql,Object...val);

	List<Zyxx> findAll();
	
	List<Zyxx> findbyhql(String hql,Object...val);
	
	List<Zyxx> findBy(String propertyName, Object value, MatchType matchType);
}
