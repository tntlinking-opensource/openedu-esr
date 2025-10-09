package org.pfw.framework.ywmk.dao;

import org.pfw.framework.modules.orm.hibernate.HibernateDao;
import org.pfw.framework.ywmk.domain.DictThree;
import org.pfw.framework.ywmk.domain.DictTwo;
import org.springframework.stereotype.Repository;

/**
 * 用户对象的泛型DAO类.
 */
@Repository
public class DictThreeDao extends HibernateDao<DictThree, String> {
}