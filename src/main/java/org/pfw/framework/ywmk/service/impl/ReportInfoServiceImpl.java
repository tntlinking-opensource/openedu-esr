package org.pfw.framework.ywmk.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.management.Query;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.impl.SessionImpl;
import org.pfw.framework.domain.security.User;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.PropertyFilter.MatchType;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.util.PFWSecurityUtils;
import org.pfw.framework.ywmk.dao.ReportInfoDao;
import org.pfw.framework.ywmk.domain.ReportInfo;
import org.pfw.framework.ywmk.service.ReportInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ReportInfoServiceImpl implements ReportInfoService {

	@Autowired
	private ReportInfoDao entityDao;

	@Transactional(readOnly=true)
	public ReportInfo getById(String id) {
		return entityDao.get(id);
	}
	
	@Transactional(readOnly=true)
	public Page findPage(Page page, List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}	
	
	@Transactional(readOnly=true)
	public List<ReportInfo> find(List<PropertyFilter> filters)
	{
		return entityDao.find(filters);
	}
	
	@Transactional(readOnly=true)
	public List<ReportInfo> find(List<PropertyFilter> filters, LinkedHashMap orderbyMap) {
		return entityDao.find(filters, orderbyMap);
	}
	
	public void save(ReportInfo entity)
	{
		entityDao.save(entity);
	}
	
	public void update(ReportInfo entity)
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
	public List<ReportInfo> findAll()
	{
		return entityDao.find(" from ReportInfo r ");
	}
	
	@Override
	public List<ReportInfo> findbyhql(String hql, Object... val) 
	{
		return entityDao.find(hql, val);
	}
	
	@Override
	public List<ReportInfo> findBy(String propertyName, Object value,
			MatchType matchType) {
		return entityDao.findBy(propertyName, value, matchType);
	}
	
	@Override
	public void deleteHql(String hql, Object... val) {
		entityDao.createQuery(hql, val).executeUpdate();
	}

	@Override
	public Boolean saveReport(Map map,String bbid,String tabblestr) {
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			String nianf = sdf.format(new Date());
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dqsj = sdf1.format(new Date());
			
			User user=PFWSecurityUtils.getCurrentUser();
			
			ReportInfo reportInfo=entityDao.get(bbid);
			
			String jgzb = reportInfo.getJgbmc1();
//			String jgfb = reportInfo.getJgbmc2();
			String baom = reportInfo.getReportname();
			String baoh = reportInfo.getReportcode();
			
			//首先判断保存过没有
			String zbid = null;  
			List zbsfbc= entityDao.getSession().createSQLQuery(" select id from t_ywmk_moban where  ssbb =? ").setParameter(0, bbid).list();//后期条件要加批次  所属学校
			
			if(zbsfbc != null && zbsfbc.size() > 0){
				zbid = zbsfbc.get(0).toString().trim();
				SQLQuery updatesql=entityDao.getSession().createSQLQuery(" update t_ywmk_moban set reptTable = ? , xgr =?  , xgsj =?  where id =? ");
				updatesql.setParameter(0, tabblestr);
				updatesql.setParameter(1, user.getId());
				updatesql.setParameter(2, dqsj);
				updatesql.setParameter(3, zbid);
				updatesql.executeUpdate();
				
			}else{
				//保存进入主表
				SQLQuery insertsql=entityDao.getSession().createSQLQuery(" insert into t_ywmk_moban (xxid,pcid,nianf,ssbb,reportcode,reportname,reptTable,reportsmxx,cjr,cjsj,xgr,xgsj,id) "
						+"values (null,null,?,?,?,?,?,null,?,? ,null,null,? )" );
				insertsql.setParameter(0, nianf);
				insertsql.setParameter(1, bbid);
				insertsql.setParameter(2, baoh);
				insertsql.setParameter(3, baom);
				insertsql.setParameter(4, tabblestr);
				insertsql.setParameter(5, user.getId());
				insertsql.setParameter(6, dqsj);
				insertsql.setParameter(7, UUID.randomUUID().toString().replaceAll("-", ""));
				insertsql.executeUpdate();
				
			}
			
			if(zbid == null ){
				List zbidList=entityDao.getSession().createSQLQuery(" select id from t_ywmk_moban where  ssbb =? ").setParameter(0, bbid).list();//后期条件要加批次  所属学校
				zbid = zbidList.get(0).toString().trim();
			}
			/**
			String delfbsql=" delete from  "+jgfb+"  where   zbid = ? "; 
			entityDao.getSession().createSQLQuery(delfbsql).setParameter(0, zbid).executeUpdate();
			
			if (!map.isEmpty()) {
				
				Set keys = map.keySet();
				
				Iterator it = keys.iterator();
				
				while (it.hasNext()) {
					
					String iptName = (String) it.next();
					
					Object val = map.get(iptName);
					
					List<String> iptNameList=Struts2Utils.split(iptName, "_");

					List<String> xyList=Struts2Utils.split(iptNameList.get(1),"$");//实际xy坐标:  0x  1y
					
					List<String> ywxyList=Struts2Utils.split(iptNameList.get(2),"$");//业务x，y:  0x  1y
					
					List<String> ywhyxyList=Struts2Utils.split(iptNameList.get(3),"$");//业务含义x，y:  0x  1y
					
					String ywhyxyStr=ywhyxyList.get(0)+","+ywhyxyList.get(1);
					
					
					SQLQuery jgfbsql=entityDao.getSession().createSQLQuery("insert into "+jgfb+"   (x,y,ywx,ywy,ywhyxy,val,zbid,id) "
							+ "values(?,?,?,?,?,?,?,?)");
					jgfbsql.setParameter(0, xyList.get(0));
					jgfbsql.setParameter(1, xyList.get(1));
					jgfbsql.setParameter(2, ywxyList.get(0));
					jgfbsql.setParameter(3, ywxyList.get(1));
					jgfbsql.setParameter(4, ywhyxyStr);
					jgfbsql.setParameter(5, val);
					jgfbsql.setParameter(6, zbid);
					jgfbsql.setParameter(7, UUID.randomUUID().toString().replaceAll("-", ""));
					jgfbsql.executeUpdate();
					
				}

			}
			**/
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
	@Override
	public void savReport()
	{
		Session ssmSession = entityDao.getSession();
//		Transaction tx = ssmSession.beginTransaction();
		Connection conn = ssmSession.connection();
		try{
			PreparedStatement  pstm = conn.prepareStatement("insert into employee values(?,?,?,?)"); 
			conn.setAutoCommit(false);//将Auto commit设置为false,不允许自动提交  
			//设置第一条语句 
			for(int i = 0; i < 100;i++)
			{
				pstm.setInt(1, 33);  

				pstm.setString(2,"wang");  

				pstm.setString(3, "man");  

				pstm.setDouble(4, 20);  

				pstm.addBatch();  //将一组参数添加到此 PreparedStatement 对象的批处理命令中。
			}
			
			
//			//设置第二条语句  
//
//			pstm.setInt(1, 34);  
//
//			pstm.setString(2,"xiaowu");  
//
//			pstm.setString(3, "woman");  
//
//			pstm.setDouble(4, 24);  
//
//			pstm.addBatch();  
			
			pstm.executeBatch();//将一批参数提交给数据库来执行，如果全部命令执行成功，则返回更新计数组成的数组

			conn.commit();

//			conn.setAutoCommit(true);//将Auto commit还原为true
			
		}catch(Exception e)
		{
			
		}finally{
//			ssmSession.close();
//			tx.commit();
		}
		 
	}

	@Override
	public ReportInfo getBydm(String dm) {
		List<ReportInfo> rpt = entityDao.find(" from ReportInfo a where a.dm = '"+dm+"'  and a.sfyx = '1'");
		ReportInfo rptInfo = null;
		if(rpt != null && rpt.size() > 0){
			rptInfo = rpt.get(0);
		}else{
			rptInfo = new ReportInfo();
		}
		return rptInfo;
	}
}
