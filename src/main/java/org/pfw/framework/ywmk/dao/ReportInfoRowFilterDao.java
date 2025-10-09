package org.pfw.framework.ywmk.dao;

import org.pfw.framework.ywmk.domain.ReportInfoRowFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateDao;
import org.springframework.stereotype.Repository;

/**
 * 用户对象的泛型DAO类.
 */
@Repository
public class ReportInfoRowFilterDao extends HibernateDao<ReportInfoRowFilter, String> {
}