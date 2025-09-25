package org.pfw.framework.ywmk.service;

import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;
import org.pfw.framework.ywmk.domain.Bjxx;
import org.pfw.framework.ywmk.domain.DictThree;
import org.pfw.framework.ywmk.domain.DictTwo;

import java.util.LinkedHashMap;
import java.util.List;


public interface DictThreeService {

	DictThree getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<DictThree> find(List<PropertyFilter> filters);
	
	List<DictThree> find(List<PropertyFilter> filters, LinkedHashMap orderbyMap);
	
	void save(DictThree entity);
	
	void update(DictThree entity);

	void deleteById(String id);
	
	void delete(List<String> checks);
	
	void deleteHql(String hql, Object... val);

	List<DictThree> findAll();
	
	List<DictThree> findbyhql(String hql, Object... val);
	
	List<DictThree> findBy(String propertyName, Object value, MatchType matchType);

	List<DictThree> findAllBydictid(String dictid);
}
