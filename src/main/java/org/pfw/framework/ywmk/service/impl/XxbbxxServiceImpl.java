package org.pfw.framework.ywmk.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;

import org.pfw.framework.ywmk.dao.XxbbxxDao;
import org.pfw.framework.ywmk.domain.Xxbbxx;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.ywmk.service.XxbbxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;

import cn.hutool.core.util.NumberUtil;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;


@Service
@Transactional
public class XxbbxxServiceImpl implements XxbbxxService {

	@Autowired
	private XxbbxxDao entityDao;

	@Transactional(readOnly=true)
	public Xxbbxx getById(String id) {
		return entityDao.get(id);
	}
	
	@Transactional(readOnly=true)
	public Page findPage(Page page, List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}	
	
	@Transactional(readOnly=true)
	public List<Xxbbxx> find(List<PropertyFilter> filters)
	{
		return entityDao.find(filters);
	}
	
	@Transactional(readOnly=true)
	public List<Xxbbxx> find(List<PropertyFilter> filters, LinkedHashMap orderbyMap) {
		return entityDao.find(filters, orderbyMap);
	}
	
	public void save(Xxbbxx entity)
	{
		entityDao.save(entity);
	}
	
	public void update(Xxbbxx entity)
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
	public List<Xxbbxx> findAll()
	{
		return entityDao.find(" from Xxbbxx r ");
	}
	
	@Override
	public List<Xxbbxx> findbyhql(String hql, Object... val) 
	{
		return entityDao.find(hql, val);
	}
	
	@Override
	public List<Xxbbxx> findBy(String propertyName, Object value,
			MatchType matchType) {
		return entityDao.findBy(propertyName, value, matchType);
	}
	
	@Override
	public void deleteHql(String hql, Object... val) {
		entityDao.createQuery(hql, val).executeUpdate();
	}
	/**
	 * 测试回调服务取值
	 * **/
	@Override
	public List<String> getcssj(String sjnf) {
		List<String> strList = new ArrayList<>();
		
		strList.add("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15");
		strList.add("2,1,1,1,1,1,1,1,9,10,11,12,13,14,15");
		strList.add("3,1,1,1,1,1,1,9,9,10,11,12,13,14,15");
		strList.add("4,1,1,1,1,1,1,8,9,10,11,12,13,14,15");
		strList.add("5,1,1,1,1,1,1,7,9,10,11,12,13,14,15");
		strList.add("6,1,1,1,1,1,1,6,9,10,11,12,13,14,15");
		strList.add("7,1,1,1,1,1,1,5,9,10,11,12,13,14,15");
		strList.add("8,1,1,1,1,1,1,4,9,10,11,12,13,14,15");
		strList.add("9,1,1,1,1,1,1,3,9,10,11,12,13,14,15");
		strList.add("10,1,1,1,1,1,1,2,9,10,11,12,13,14,15");
		strList.add("11,1,1,1,1,1,1,1,9,10,11,12,13,14,15");
		strList.add("12,1,1,1,1,1,1,1,9,10,11,12,13,14,15");
		
		return strList;
	}
	/**
	 * 幼儿园校舍面积计算
	 * **/
	@Override
	public List<String> getyeyxsmj(String sjnf) {
		List<String> strList = new ArrayList<>();

		
		String sqlstr = "";
		sqlstr+=" select                                                                                                                                                                                       ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 ) AS sxnmj ,                                                                                                     ";
		sqlstr+="	 ((ifnull( SUM( CASE WHEN a.sjnf = '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 )  ) )as zjmj,                      ";
		sqlstr+="	  case when ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  > 0 THEN         ";
		sqlstr+="	  ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  else 0 END as jsmj,        ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"' THEN a.xsmj ELSE 0 END ), 0 ) as dqmj                                                                                                             ";
		sqlstr+="	 from t_ywmk_xiaoshexx   a where       a.dqzt = '8a8080888610a33f018610b500360002'   and a.sfcjwf = '1'                                                                                    ";
		sqlstr+="	 union all                                                                                                                                                                                 ";
		sqlstr+="	  select                                                                                                                                                                                   ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 ) AS sxnmj ,                                                                                                     ";
		sqlstr+="	 ((ifnull( SUM( CASE WHEN a.sjnf = '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 )  ) )as zjmj,                      ";
		sqlstr+="	  case when ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  > 0 THEN         ";
		sqlstr+="	  ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  else 0 END as jsmj,        ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"' THEN a.xsmj ELSE 0 END ), 0 ) as dqmj                                                                                                             ";
		sqlstr+="	 from t_ywmk_xiaoshexx   a where       a.dqzt = '8a8080888610a33f018610b500360002'   and a.sfdjwf = '1'                                                                                    ";
		sqlstr+="	 union all                                                                                                                                                                                 ";
		sqlstr+="	  select                                                                                                                                                                                   ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 ) AS sxnmj ,                                                                                                     ";
		sqlstr+="	 ((ifnull( SUM( CASE WHEN a.sjnf = '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 )  ) )as zjmj,                      ";
		sqlstr+="	  case when ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  > 0 THEN         ";
		sqlstr+="	  ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  else 0 END as jsmj,        ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"' THEN a.xsmj ELSE 0 END ), 0 ) as dqmj                                                                                                             ";
		sqlstr+="	 from t_ywmk_xiaoshexx   a where       a.dqzt = '8a8080888610a33f018610b500360002'   and a.sfzywdw = '1'                                                                                   ";
		sqlstr+="	 union all                                                                                                                                                                                 ";
		sqlstr+="	 select                                                                                                                                                                                    ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 ) AS sxnmj ,                                                                                                     ";
		sqlstr+="	 ((ifnull( SUM( CASE WHEN a.sjnf = '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 )  ) )as zjmj,                      ";
		sqlstr+="	  case when ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  > 0 THEN         ";
		sqlstr+="	  ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  else 0 END as jsmj,        ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"' THEN a.xsmj ELSE 0 END ), 0 ) as dqmj                                                                                                             ";
		sqlstr+="	 from t_ywmk_xiaoshexx   a where   a.syfx = '8a8080a4866e61c101866e6e0f3b0002'    and a.syfxtwo = '8a8080a4866e61c101866e76745c0007' and a.dqzt = '8a8080888610a33f018610b500360002'       ";
		sqlstr+="	 union all                                                                                                                                                                                 ";
		sqlstr+="	 select                                                                                                                                                                                    ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 ) AS sxnmj ,                                                                                                     ";
		sqlstr+="	 ((ifnull( SUM( CASE WHEN a.sjnf = '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 )  ) )as zjmj,                      ";
		sqlstr+="	  case when ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  > 0 THEN         ";
		sqlstr+="	  ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  else 0 END as jsmj,        ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"' THEN a.xsmj ELSE 0 END ), 0 ) as dqmj                                                                                                             ";
		sqlstr+="	 from t_ywmk_xiaoshexx   a where   a.syfx = '8a8080a4866e61c101866e6e0f3b0002'  and a.syfxtwo = '8a8080a48671a033018671ab7d580002' and a.dqzt = '8a8080888610a33f018610b500360002'         ";
		sqlstr+="	 union all                                                                                                                                                                                 ";
		sqlstr+="	 select                                                                                                                                                                                    ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 ) AS sxnmj ,                                                                                                     ";
		sqlstr+="	 ((ifnull( SUM( CASE WHEN a.sjnf = '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 )  ) )as zjmj,                      ";
		sqlstr+="	  case when ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  > 0 THEN         ";
		sqlstr+="	  ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  else 0 END as jsmj,        ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"' THEN a.xsmj ELSE 0 END ), 0 ) as dqmj                                                                                                             ";
		sqlstr+="	 from t_ywmk_xiaoshexx   a where   a.syfx = '8a8080a4866e61c101866e6e0f3b0002'  and a.syfxtwo = '8a8080a48671a033018671ac470a0003' and a.dqzt = '8a8080888610a33f018610b500360002'         ";
		sqlstr+="	 union all                                                                                                                                                                                 ";
		sqlstr+="	 select                                                                                                                                                                                    ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 ) AS sxnmj ,                                                                                                     ";
		sqlstr+="	 ((ifnull( SUM( CASE WHEN a.sjnf = '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 )  ) )as zjmj,                      ";
		sqlstr+="	  case when ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  > 0 THEN         ";
		sqlstr+="	  ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  else 0 END as jsmj,        ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"' THEN a.xsmj ELSE 0 END ), 0 ) as dqmj                                                                                                             ";
		sqlstr+="	 from t_ywmk_xiaoshexx   a where   a.syfx = '8a8080a4866e61c101866e6e0f3b0002'  and a.syfxtwo = '8a8080a48671a033018671ad4d8e0004' and a.dqzt = '8a8080888610a33f018610b500360002'         ";
		sqlstr+="	 union all                                                                                                                                                                                 ";
		sqlstr+="	 select                                                                                                                                                                                    ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 ) AS sxnmj ,                                                                                                     ";
		sqlstr+="	 ((ifnull( SUM( CASE WHEN a.sjnf = '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 )  ) )as zjmj,                      ";
		sqlstr+="	  case when ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  > 0 THEN         ";
		sqlstr+="	  ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  else 0 END as jsmj,        ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"' THEN a.xsmj ELSE 0 END ), 0 ) as dqmj                                                                                                             ";
		sqlstr+="	 from t_ywmk_xiaoshexx   a where   a.syfx = '8a8080a4866e61c101866e6e0f3b0002'  and a.syfxtwo = '8a8080a48671a033018671ae03cc0005' and a.dqzt = '8a8080888610a33f018610b500360002'         ";
		sqlstr+="	 union all                                                                                                                                                                                 ";
		sqlstr+="	 select                                                                                                                                                                                    ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 ) AS sxnmj ,                                                                                                     ";
		sqlstr+="	 ((ifnull( SUM( CASE WHEN a.sjnf = '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 )  ) )as zjmj,                      ";
		sqlstr+="	  case when ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  > 0 THEN         ";
		sqlstr+="	  ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  else 0 END as jsmj,        ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"' THEN a.xsmj ELSE 0 END ), 0 ) as dqmj                                                                                                             ";
		sqlstr+="	 from t_ywmk_xiaoshexx   a where   a.syfx = '8a8080a4866e61c101866e6f1c310003'  and a.syfxtwo = '8a8080a48671a033018671aee5ff0006' and a.dqzt = '8a8080888610a33f018610b500360002'         ";
		sqlstr+="	 union all                                                                                                                                                                                 ";
		sqlstr+="	 select                                                                                                                                                                                    ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 ) AS sxnmj ,                                                                                                     ";
		sqlstr+="	 ((ifnull( SUM( CASE WHEN a.sjnf = '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 )  ) )as zjmj,                      ";
		sqlstr+="	  case when ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  > 0 THEN         ";
		sqlstr+="	  ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  else 0 END as jsmj,        ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"' THEN a.xsmj ELSE 0 END ), 0 ) as dqmj                                                                                                             ";
		sqlstr+="	 from t_ywmk_xiaoshexx   a where   a.syfx = '8a8080a4866e61c101866e6f1c310003'  and a.syfxtwo = '8a8080a48671a033018671af6f410007' and a.dqzt = '8a8080888610a33f018610b500360002'         ";
		sqlstr+="	 union all                                                                                                                                                                                 ";
		sqlstr+="	 select                                                                                                                                                                                    ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 ) AS sxnmj ,                                                                                                     ";
		sqlstr+="	 ((ifnull( SUM( CASE WHEN a.sjnf = '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 )  ) )as zjmj,                      ";
		sqlstr+="	  case when ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  > 0 THEN         ";
		sqlstr+="	  ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  else 0 END as jsmj,        ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"' THEN a.xsmj ELSE 0 END ), 0 ) as dqmj                                                                                                             ";
		sqlstr+="	 from t_ywmk_xiaoshexx   a where   a.syfx = '8a8080a4866e61c101866e6f1c310003'  and a.syfxtwo = '8a8080a48671a033018671b0b69f0008' and a.dqzt = '8a8080888610a33f018610b500360002'         ";
		sqlstr+="	 union all                                                                                                                                                                                 ";
		sqlstr+="	 select                                                                                                                                                                                    ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 ) AS sxnmj ,                                                                                                     ";
		sqlstr+="	 ((ifnull( SUM( CASE WHEN a.sjnf = '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 )  ) )as zjmj,                      ";
		sqlstr+="	  case when ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  > 0 THEN         ";
		sqlstr+="	  ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  else 0 END as jsmj,        ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"' THEN a.xsmj ELSE 0 END ), 0 ) as dqmj                                                                                                             ";
		sqlstr+="	 from t_ywmk_xiaoshexx   a where   a.syfx = '8a8080a4866e61c101866e6f88760004'  and a.syfxtwo = '8a8080a48671a033018671b1f7250009' and a.dqzt = '8a8080888610a33f018610b500360002'         ";
		sqlstr+="	 union all                                                                                                                                                                                 ";
		sqlstr+="	 select                                                                                                                                                                                    ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 ) AS sxnmj ,                                                                                                     ";
		sqlstr+="	 ((ifnull( SUM( CASE WHEN a.sjnf = '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 )  ) )as zjmj,                      ";
		sqlstr+="	  case when ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  > 0 THEN         ";
		sqlstr+="	  ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  else 0 END as jsmj,        ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"' THEN a.xsmj ELSE 0 END ), 0 ) as dqmj                                                                                                             ";
		sqlstr+="	 from t_ywmk_xiaoshexx   a where   a.syfx = '8a8080a4866e61c101866e6f88760004'  and a.syfxtwo = '8a8080a48671a033018671b2ac54000a' and a.dqzt = '8a8080888610a33f018610b500360002'         ";
		

		sqlstr+="		union all ";
		sqlstr+="		select  ";
		sqlstr+="		ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 ) AS sxnmj , ";
		sqlstr+="		((ifnull( SUM( CASE WHEN a.sjnf = '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 )  ) )as zjmj, ";
		sqlstr+="		 case when ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  > 0 THEN ";
		sqlstr+="		 ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  else 0 END as jsmj, ";
		sqlstr+="		ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"' THEN a.xsmj ELSE 0 END ), 0 ) as dqmj ";
		sqlstr+="		from t_ywmk_xiaoshexx   a where   a.syfx = '8a8080a4866e61c101866e6ffd460005'   and a.dqzt = '8a8080888610a33f018610b500360002'   ";
				
				
		
		sqlstr+="	 union all                                                                                                                                                                                 ";
		sqlstr+="	 select                                                                                                                                                                                    ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 ) AS sxnmj ,                                                                                                     ";
		sqlstr+="	 ((ifnull( SUM( CASE WHEN a.sjnf = '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf = ('"+sjnf+"'-1)   THEN a.xsmj ELSE 0 END ), 0 )  ) )as zjmj,                      ";
		sqlstr+="	  case when ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  > 0 THEN         ";
		sqlstr+="	  ((ifnull( SUM( CASE WHEN a.sjnf =  ('"+sjnf+"'-1) THEN a.xsmj ELSE 0 END ), 0 ) ) - (ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"'  THEN a.xsmj ELSE 0 END ), 0 )  ) )  else 0 END as jsmj,        ";
		sqlstr+="	 ifnull( SUM( CASE WHEN a.sjnf =  '"+sjnf+"' THEN a.xsmj ELSE 0 END ), 0 ) as dqmj                                                                                                             ";
		sqlstr+="	 from t_ywmk_xiaoshexx   a where   a.sfbwdwzjy = '1'   and a.dqzt = '8a8080888610a33f018610b500360002'                                                                                     ";
		
		List tmpList = entityDao.getSession().createSQLQuery(sqlstr).list();
		if(tmpList != null && tmpList.size() > 0){
			//总计
			Integer  zj1 = 0;
			Integer  zj2 = 0;
			Integer  zj3 = 0;
			Integer  zj4 = 0;
			//教学及辅助用房
			Integer jxfzyf1 = 0;
			Integer jxfzyf2 = 0;
			Integer jxfzyf3 = 0;
			Integer jxfzyf4 = 0;
			
			//班级活动单元
			Integer bjhddw1 = 0;
			Integer bjhddw2 = 0;
			Integer bjhddw3 = 0;
			Integer bjhddw4 = 0;
			
			//行政办公用房
			Integer xzbg1 = 0;
			Integer xzbg2 = 0;
			Integer xzbg3 = 0;
			Integer xzbg4 = 0;
			
			//生活用房
			Integer shyf1 = 0;
			Integer shyf2 = 0;
			Integer shyf3 = 0;
			Integer shyf4 = 0;
			
			for (int i = 0; i < tmpList.size(); i++) {
				Object[] tmpObjects = (Object[]) tmpList.get(i);
				Integer val1 = 0; 
				if(NumberUtil.parseInt(tmpObjects[0].toString()) > 0){
					val1 = NumberUtil.parseInt(tmpObjects[0].toString());
				}
				Integer val2 = 0;
				if(NumberUtil.parseInt(tmpObjects[1].toString()) > 0){
					val2 = NumberUtil.parseInt(tmpObjects[1].toString());
				}
				Integer val3 = 0;
				if(NumberUtil.parseInt(tmpObjects[2].toString()) > 0){
					val3 = NumberUtil.parseInt(tmpObjects[2].toString());
				}
				Integer val4 = 0;
				if(NumberUtil.parseInt(tmpObjects[3].toString()) > 0){
					val4 = NumberUtil.parseInt(tmpObjects[3].toString());
				}
				
				if(i > 2 && i != 14){
					zj1 = zj1+val1;
					zj2 = zj2+val2;
					zj3 = zj3+val3;
					zj4 = zj4+val4;
				}
				
				if(i>= 3 && i<= 7){
					jxfzyf1 = jxfzyf1+val1;
					jxfzyf2 = jxfzyf2+val2;
					jxfzyf3 = jxfzyf3+val3;
					jxfzyf4 = jxfzyf4+val4;
				}
				
				
				if(i>= 3 && i<= 6){
					bjhddw1 = bjhddw1+val1;
					bjhddw2 = bjhddw2+val2;
					bjhddw3 = bjhddw3+val3;
					bjhddw4 = bjhddw4+val4;
				}
				
				if(i>= 8 && i<= 10){
					xzbg1 = xzbg1+val1;
					xzbg2 = xzbg2+val2;
					xzbg3 = xzbg3+val3;
					xzbg4 = xzbg4+val4;
				}
				if(i>= 11 && i<= 12){
					shyf1 = shyf1+val1;
					shyf2 = shyf2+val2;
					shyf3 = shyf3+val3;
					shyf4 = shyf4+val4;
				}
 			}
			strList.add(zj1+","+zj2+","+zj3+","+zj4);
			//重新组装tab
			for (int i = 0; i < tmpList.size(); i++) {
				Object[] tmpObjects = (Object[]) tmpList.get(i);
				
				if(i == 3){
					strList.add(jxfzyf1+","+jxfzyf2+","+jxfzyf3+","+jxfzyf4);
					strList.add(bjhddw1+","+bjhddw2+","+bjhddw3+","+bjhddw4);
				}
				
				if(i == 8){
					strList.add(xzbg1+","+xzbg2+","+xzbg3+","+xzbg4);
				}
				
				if(i == 11){
					strList.add(shyf1+","+shyf2+","+shyf3+","+shyf4);
				}
				
				
				String tmpstr = "";
				for (int j = 0; j < tmpObjects.length; j++) {
					Integer val1 = 0; 
					if(NumberUtil.parseInt(tmpObjects[j].toString()) > 0){
						val1 = NumberUtil.parseInt(tmpObjects[j].toString());
					}
					tmpstr+= clear0(String.valueOf(val1))+",";
				}
				tmpstr = tmpstr.substring(0,tmpstr.length()-1);
				strList.add(tmpstr);
				
			}
			
		}
		return strList;
	}
	public String clear0(String val) {
		BigDecimal value = new BigDecimal(val);
	    BigDecimal noZeros = value.stripTrailingZeros();
	    return  noZeros.toPlainString(); 
	}
}
