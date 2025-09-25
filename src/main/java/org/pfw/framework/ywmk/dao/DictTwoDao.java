package org.pfw.framework.ywmk.dao;

import org.pfw.framework.modules.orm.hibernate.HibernateDao;
import org.pfw.framework.ywmk.domain.Bjxx;
import org.pfw.framework.ywmk.domain.DictTwo;
import org.springframework.stereotype.Repository;

/**
 * 用户对象的泛型DAO类.
 */
@Repository
public class DictTwoDao extends HibernateDao<DictTwo, String> {
}