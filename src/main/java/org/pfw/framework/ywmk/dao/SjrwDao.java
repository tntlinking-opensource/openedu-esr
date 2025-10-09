package org.pfw.framework.ywmk.dao;

import org.pfw.framework.ywmk.domain.Sjrw;
import org.pfw.framework.modules.orm.hibernate.HibernateDao;
import org.springframework.stereotype.Repository;

/**
 * 用户对象的泛型DAO类.
 */
@Repository
public class SjrwDao extends HibernateDao<Sjrw, String> {
}