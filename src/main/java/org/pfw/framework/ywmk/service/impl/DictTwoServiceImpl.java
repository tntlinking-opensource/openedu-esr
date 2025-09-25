package org.pfw.framework.ywmk.service.impl;

import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;
import org.pfw.framework.xtgl.domain.Dict;
import org.pfw.framework.ywmk.dao.DictTwoDao;
import org.pfw.framework.ywmk.domain.DictTwo;
import org.pfw.framework.ywmk.service.DictTwoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


@Service
@Transactional
public class DictTwoServiceImpl implements DictTwoService {

	@Autowired
	private DictTwoDao entityDao;

	@Transactional(readOnly=true)
	public DictTwo getById(String id) {
		return entityDao.get(id);
	}
	
	@Transactional(readOnly=true)
	public Page findPage(Page page, List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}	
	
	@Transactional(readOnly=true)
	public List<DictTwo> find(List<PropertyFilter> filters)
	{
		return entityDao.find(filters);
	}
	
	@Transactional(readOnly=true)
	public List<DictTwo> find(List<PropertyFilter> filters, LinkedHashMap orderbyMap) {
		return entityDao.find(filters, orderbyMap);
	}
	
	public void save(DictTwo entity)
	{
		entityDao.save(entity);
	}
	
	public void update(DictTwo entity)
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
	public List<DictTwo> findAll()
	{
		return entityDao.find(" from DictTwo r ");
	}
	
	@Override
	public List<DictTwo> findbyhql(String hql, Object... val)
	{
		return entityDao.find(hql, val);
	}
	
	@Override
	public List<DictTwo> findBy(String propertyName, Object value,
			MatchType matchType) {
		return entityDao.findBy(propertyName, value, matchType);
	}
	public List<DictTwo> getByPro(List<PropertyFilter> filters) {
		return this.entityDao.find(filters);
	}

	@Override
	public List<DictTwo> findAllByDM(String dm) {
		List<PropertyFilter> filterList = new ArrayList();
		PropertyFilter propertyFilter_dm = new PropertyFilter("EQS_dm", dm);
		filterList.add(propertyFilter_dm);
		List<DictTwo> mdList = this.getByPro(filterList);
		return mdList;
	}

	@Override
	public List<DictTwo> findAllBydictid(String dictid) {
		List<PropertyFilter> filterList = new ArrayList();
		PropertyFilter propertyFilter = new PropertyFilter("EQS_sszd.id", dictid);
		filterList.add(propertyFilter);
		List<DictTwo> ejdictList = this.getByPro(filterList);
		return ejdictList;
	}


	@Override
	public void deleteHql(String hql, Object... val) {
		entityDao.createQuery(hql, val).executeUpdate();
	}
}
