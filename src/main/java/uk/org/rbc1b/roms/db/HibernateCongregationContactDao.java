/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ramindursingh
 */
@Repository
public class HibernateCongregationContactDao implements CongregationContactDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Person> findCongregationContacts(Congregation congregation) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(CongregationContact.class);
        criteria.add(Restrictions.eq("congregationId", congregation.getCongregationId()));
        return criteria.list();
    }
}
