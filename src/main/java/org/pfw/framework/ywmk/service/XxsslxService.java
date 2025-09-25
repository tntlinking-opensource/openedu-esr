package org.pfw.framework.ywmk.service;

import java.util.List;
import java.util.LinkedHashMap;

import org.pfw.framework.ywmk.domain.Xxsslx;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;



public interface XxsslxService {

	Xxsslx getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<Xxsslx> find(List<PropertyFilter> filters);
	
	List<Xxsslx> find(List<PropertyFilter> filters,LinkedHashMap orderbyMap);
	
	void save(Xxsslx entity);
	
	void update(Xxsslx entity);

	void deleteById(String id);
	
	void delete(List<String> checks);
	
	void deleteHql(String hql,Object...val);

	List<Xxsslx> findAll();
	
	List<Xxsslx> findbyhql(String hql,Object...val);
	
	List<Xxsslx> findBy(String propertyName, Object value, MatchType matchType);
}
