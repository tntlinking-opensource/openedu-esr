package org.pfw.framework.ywmk.service;

import java.util.List;
import java.util.LinkedHashMap;

import org.pfw.framework.ywmk.domain.Xxbbxx;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;



public interface XxbbxxService {

	Xxbbxx getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<Xxbbxx> find(List<PropertyFilter> filters);
	
	List<Xxbbxx> find(List<PropertyFilter> filters,LinkedHashMap orderbyMap);
	
	void save(Xxbbxx entity);
	
	void update(Xxbbxx entity);

	void deleteById(String id);
	
	void delete(List<String> checks);
	
	void deleteHql(String hql,Object...val);

	List<Xxbbxx> findAll();
	
	List<Xxbbxx> findbyhql(String hql,Object...val);
	
	List<Xxbbxx> findBy(String propertyName, Object value, MatchType matchType);

	List<String> getcssj(String sjnf);

	List<String> getyeyxsmj(String sjnf);
}
