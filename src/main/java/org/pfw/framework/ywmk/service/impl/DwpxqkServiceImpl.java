package org.pfw.framework.ywmk.service.impl;

import java.util.List;
import java.util.LinkedHashMap;

import org.pfw.framework.ywmk.dao.DwpxqkDao;
import org.pfw.framework.ywmk.domain.Dwpxqk;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.ywmk.service.DwpxqkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;


@Service
@Transactional
public class DwpxqkServiceImpl implements DwpxqkService {

	@Autowired
	private DwpxqkDao entityDao;

	@Transactional(readOnly=true)
	public Dwpxqk getById(String id) {
		return entityDao.get(id);
	}
	
	@Transactional(readOnly=true)
	public Page findPage(Page page, List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}	
	
	@Transactional(readOnly=true)
	public List<Dwpxqk> find(List<PropertyFilter> filters)
	{
		return entityDao.find(filters);
	}
	
	@Transactional(readOnly=true)
	public List<Dwpxqk> find(List<PropertyFilter> filters, LinkedHashMap orderbyMap) {
		return entityDao.find(filters, orderbyMap);
	}
	
	public void save(Dwpxqk entity)
	{
		entityDao.save(entity);
	}
	
	public void update(Dwpxqk entity)
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
	public List<Dwpxqk> findAll()
	{
		return entityDao.find(" from Dwpxqk r ");
	}
	
	@Override
	public List<Dwpxqk> findbyhql(String hql, Object... val) 
	{
		return entityDao.find(hql, val);
	}
	
	@Override
	public List<Dwpxqk> findBy(String propertyName, Object value,
			MatchType matchType) {
		return entityDao.findBy(propertyName, value, matchType);
	}
	
	@Override
	public void deleteHql(String hql, Object... val) {
		entityDao.createQuery(hql, val).executeUpdate();
	}
}
