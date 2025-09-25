package org.pfw.framework.ywmk.service.impl;

import java.util.List;
import java.util.LinkedHashMap;

import org.apache.commons.lang.StringUtils;
import org.pfw.framework.ywmk.dao.XxxqDao;
import org.pfw.framework.ywmk.domain.Xxxq;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.ywmk.service.XxxqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;


@Service
@Transactional
public class XxxqServiceImpl implements XxxqService {

	@Autowired
	private XxxqDao entityDao;

	@Transactional(readOnly=true)
	public Xxxq getById(String id) {
		return entityDao.get(id);
	}



	@Transactional(readOnly=true)
	public Page findPage(Page page, List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}	
	
	@Transactional(readOnly=true)
	public List<Xxxq> find(List<PropertyFilter> filters)
	{
		return entityDao.find(filters);
	}
	
	@Transactional(readOnly=true)
	public List<Xxxq> find(List<PropertyFilter> filters, LinkedHashMap orderbyMap) {
		return entityDao.find(filters, orderbyMap);
	}
	
	public void save(Xxxq entity)
	{
		entityDao.save(entity);
	}
	
	public void update(Xxxq entity)
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
	public List<Xxxq> findAll()
	{
		return entityDao.find(" from Xxxq r ");
	}
	
	@Override
	public List<Xxxq> findbyhql(String hql, Object... val) 
	{
		return entityDao.find(hql, val);
	}
	
	@Override
	public List<Xxxq> findBy(String propertyName, Object value,
			MatchType matchType) {
		return entityDao.findBy(propertyName, value, matchType);
	}
	
	@Override
	public void deleteHql(String hql, Object... val) {
		entityDao.createQuery(hql, val).executeUpdate();
	}

	@Override
	public Xxxq getByxqmc(String xqmc) {
		Xxxq xxxq = null;

		if(StringUtils.isNotEmpty(xqmc)){
			String hql = "  from Xxxq a where a.xqmc= '"+xqmc+"'";
			List<Xxxq> xxxqList = entityDao.find(hql);
			if(xxxqList != null && xxxqList.size() > 0){
				xxxq = xxxqList.get(0);
			}
		}
		return xxxq;
	}

}
