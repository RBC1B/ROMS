/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ramindursingh
 */
@Repository
public class HibernateRbcSubRegionDao implements RbcSubRegionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Cacheable("rbcsubregion.rbcsubregion")
    public List<RbcSubRegion> findAllRbcSubRegion() {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(RbcSubRegion.class);
        return criteria.list();
    }
}
