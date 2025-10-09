package org.pfw.framework.ywmk.service.impl;

import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;
import org.pfw.framework.ywmk.dao.DictThreeDao;
import org.pfw.framework.ywmk.domain.DictThree;
import org.pfw.framework.ywmk.domain.DictTwo;
import org.pfw.framework.ywmk.service.DictThreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


@Service
@Transactional
public class DictThreeServiceImpl implements DictThreeService {

	@Autowired
	private DictThreeDao entityDao;

	@Transactional(readOnly=true)
	public DictThree getById(String id) {
		return entityDao.get(id);
	}
	
	@Transactional(readOnly=true)
	public Page findPage(Page page, List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}	
	
	@Transactional(readOnly=true)
	public List<DictThree> find(List<PropertyFilter> filters)
	{
		return entityDao.find(filters);
	}
	
	@Transactional(readOnly=true)
	public List<DictThree> find(List<PropertyFilter> filters, LinkedHashMap orderbyMap) {
		return entityDao.find(filters, orderbyMap);
	}
	
	public void save(DictThree entity)
	{
		entityDao.save(entity);
	}
	
	public void update(DictThree entity)
	{
		entityDao.update(entity);
	}

	public void deleteById(String id)
	{
		entityDao.delete(id);
	}
	
	public void delete(List<String> checks)
	{
		if(checks!=null&&checks.size()>0){
			for (String id : checks) {
				entityDao.delete(id);
			}
		}
	}

	@Override
	public List<DictThree> findAll()
	{
		return entityDao.find(" from DictThree r ");
	}
	
	@Override
	public List<DictThree> findbyhql(String hql, Object... val)
	{
		return entityDao.find(hql, val);
	}
	
	@Override
	public List<DictThree> findBy(String propertyName, Object value,
			MatchType matchType) {
		return entityDao.findBy(propertyName, value, matchType);
	}

	@Override
	public List<DictThree> findAllBydictid(String dictid) {
		List<PropertyFilter> filterList = new ArrayList();
		PropertyFilter propertyFilter = new PropertyFilter("EQS_sszd.id", dictid);
		filterList.add(propertyFilter);
		List<DictThree> dictThrees = entityDao.find(filterList);
		return dictThrees;
	}

	@Override
	public void deleteHql(String hql, Object... val) {
		entityDao.createQuery(hql, val).executeUpdate();
	}
}
