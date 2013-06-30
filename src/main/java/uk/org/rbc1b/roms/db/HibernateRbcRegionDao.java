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
public class HibernateRbcRegionDao implements RbcRegionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Cacheable("rbcregion.rbcregion")
    public List<RbcRegion> findAllRbcRegion() {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(RbcRegion.class);
        return criteria.list();
    }
}
