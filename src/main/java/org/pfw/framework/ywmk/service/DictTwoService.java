package org.pfw.framework.ywmk.service;

import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;
import org.pfw.framework.ywmk.domain.DictTwo;

import java.util.LinkedHashMap;
import java.util.List;


public interface DictTwoService {

	DictTwo getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<DictTwo> find(List<PropertyFilter> filters);
	
	List<DictTwo> find(List<PropertyFilter> filters, LinkedHashMap orderbyMap);
	
	void save(DictTwo entity);
	
	void update(DictTwo entity);

	void deleteById(String id);
	
	void delete(List<String> checks);
	
	void deleteHql(String hql, Object... val);

	List<DictTwo> findAll();
	
	List<DictTwo> findbyhql(String hql, Object... val);
	
	List<DictTwo> findBy(String propertyName, Object value, MatchType matchType);

	List<DictTwo> findAllByDM(String dm);

	List<DictTwo> findAllBydictid(String dictid);

}
