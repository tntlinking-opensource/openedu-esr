package org.pfw.framework.ywmk.service;

import java.util.List;
import java.util.LinkedHashMap;

import org.pfw.framework.ywmk.domain.ReportCheckRule;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;



public interface ReportCheckRuleService {

	ReportCheckRule getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<ReportCheckRule> find(List<PropertyFilter> filters);
	
	List<ReportCheckRule> find(List<PropertyFilter> filters,LinkedHashMap orderbyMap);
	
	void save(ReportCheckRule entity);
	
	void update(ReportCheckRule entity);

	void deleteById(String id);
	
	void delete(List<String> checks);
	
	void deleteHql(String hql,Object...val);

	List<ReportCheckRule> findAll();
	
	List<ReportCheckRule> findbyhql(String hql,Object...val);
	
	List<ReportCheckRule> findBy(String propertyName, Object value, MatchType matchType);
	
	//公式拆分
	List<String> ruleSplit(String orgrule);
	

	List jyruleSplit(String orgrule);
	/**
	 * 校验规则
	 * 返回数学公式+校验规则数学公式对应关系字符串
	 * 格式：数学公式$校验规则数学公式对应关系
	 * @return
	 * @throws Exception
	 */
	public String valrule(String rulestr);


	String jyvalrule(String rulestr);

	
}
