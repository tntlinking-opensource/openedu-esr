package org.pfw.framework.ywmk.service.impl;

import java.util.List;
import java.util.LinkedHashMap;

import org.pfw.framework.ywmk.dao.BbqzpzDao;
import org.pfw.framework.ywmk.domain.Bbqzpz;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.ywmk.service.BbqzpzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;


@Service
@Transactional
public class BbqzpzServiceImpl implements BbqzpzService {

	@Autowired
	private BbqzpzDao entityDao;

	@Transactional(readOnly=true)
	public Bbqzpz getById(String id) {
		return entityDao.get(id);
	}
	
	@Transactional(readOnly=true)
	public Page findPage(Page page, List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}	
	
	@Transactional(readOnly=true)
	public List<Bbqzpz> find(List<PropertyFilter> filters)
	{
		return entityDao.find(filters);
	}
	
	@Transactional(readOnly=true)
	public List<Bbqzpz> find(List<PropertyFilter> filters, LinkedHashMap orderbyMap) {
		return entityDao.find(filters, orderbyMap);
	}
	
	public void save(Bbqzpz entity)
	{
		entityDao.save(entity);
	}
	
	public void update(Bbqzpz entity)
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
	public List<Bbqzpz> findAll()
	{
		return entityDao.find(" from Bbqzpz r ");
	}
	
	@Override
	public List<Bbqzpz> findbyhql(String hql, Object... val) 
	{
		return entityDao.find(hql, val);
	}
	
	@Override
	public List<Bbqzpz> findBy(String propertyName, Object value,
			MatchType matchType) {
		return entityDao.findBy(propertyName, value, matchType);
	}
	
	@Override
	public void deleteHql(String hql, Object... val) {
		entityDao.createQuery(hql, val).executeUpdate();
	}
}
