/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of the congregation dao.
 *
 * @author oliver.elder.esq
 */
@Repository
public class HibernateCongregationDao implements CongregationDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Congregation findCongregation(Integer congregationId) {
        return (Congregation) this.sessionFactory.getCurrentSession().get(Congregation.class, congregationId);
    }

    @Override
    public List<Congregation> findCongregations(String name) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Congregation.class);
        criteria.add(Restrictions.like("name", "%" + name + "%"));
        return criteria.list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
