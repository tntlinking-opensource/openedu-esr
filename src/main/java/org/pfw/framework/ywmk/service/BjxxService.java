package org.pfw.framework.ywmk.service;

import java.util.List;
import java.util.LinkedHashMap;

import org.pfw.framework.ywmk.domain.Bjxx;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;



public interface BjxxService {

	Bjxx getById(String id);
	Bjxx getBybjmc(String bjmc);
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<Bjxx> find(List<PropertyFilter> filters);
	
	List<Bjxx> find(List<PropertyFilter> filters,LinkedHashMap orderbyMap);
	
	void save(Bjxx entity);
	
	void update(Bjxx entity);

	void deleteById(String id);
	
	void delete(List<String> checks);
	
	void deleteHql(String hql,Object...val);

	List<Bjxx> findAll();
	
	List<Bjxx> findbyhql(String hql,Object...val);
	
	List<Bjxx> findBy(String propertyName, Object value, MatchType matchType);
}
