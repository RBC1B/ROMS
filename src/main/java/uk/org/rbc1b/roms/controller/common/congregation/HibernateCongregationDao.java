/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.common.congregation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.db.Congregation;

/**
 * Hibernate implementation of the congregation dao.
 *
 * @author oliver.elder.esq
 */
@Component
public class HibernateCongregationDao implements CongregationDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Congregation findCongregation(Integer congregationId) {
        return (Congregation) this.sessionFactory.getCurrentSession().get(Congregation.class, congregationId);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
