package org.pfw.framework.ywmk.service.impl;

import java.util.List;
import java.util.LinkedHashMap;

import org.apache.commons.lang.StringUtils;
import org.pfw.framework.ywmk.dao.BjxxDao;
import org.pfw.framework.ywmk.domain.Bjxx;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.ywmk.domain.Xxxq;
import org.pfw.framework.ywmk.service.BjxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;


@Service
@Transactional
public class BjxxServiceImpl implements BjxxService {

	@Autowired
	private BjxxDao entityDao;

	@Transactional(readOnly=true)
	public Bjxx getById(String id) {
		return entityDao.get(id);
	}

	@Override
	public Bjxx getBybjmc(String bjmc) {
		Bjxx bjxx = null;
		if(StringUtils.isNotEmpty(bjmc)){
			String hql = "  from Bjxx a where a.bjmc= '"+bjmc+"'";
			List<Bjxx> bjxxList = entityDao.find(hql);
			if(bjxxList != null && bjxxList.size() > 0){
				bjxx = bjxxList.get(0);
			}
		}
		return bjxx;
	}

	@Transactional(readOnly=true)
	public Page findPage(Page page, List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}	
	
	@Transactional(readOnly=true)
	public List<Bjxx> find(List<PropertyFilter> filters)
	{
		return entityDao.find(filters);
	}
	
	@Transactional(readOnly=true)
	public List<Bjxx> find(List<PropertyFilter> filters, LinkedHashMap orderbyMap) {
		return entityDao.find(filters, orderbyMap);
	}
	
	public void save(Bjxx entity)
	{
		entityDao.save(entity);
	}
	
	public void update(Bjxx entity)
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
	public List<Bjxx> findAll()
	{
		return entityDao.find(" from Bjxx r ");
	}
	
	@Override
	public List<Bjxx> findbyhql(String hql, Object... val) 
	{
		return entityDao.find(hql, val);
	}
	
	@Override
	public List<Bjxx> findBy(String propertyName, Object value,
			MatchType matchType) {
		return entityDao.findBy(propertyName, value, matchType);
	}
	
	@Override
	public void deleteHql(String hql, Object... val) {
		entityDao.createQuery(hql, val).executeUpdate();
	}
}
