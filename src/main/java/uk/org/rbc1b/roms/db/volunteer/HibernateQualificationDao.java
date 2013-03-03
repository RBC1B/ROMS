/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.volunteer;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tina
 */
@Repository
public class HibernateQualificationDao implements QualificationDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Qualification findQualification(final String name) {
        return (Qualification) this.sessionFactory.getCurrentSession().get(Qualification.class, name);
    }

    @Override
    public List<Qualification> findQualifications() {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Qualification.class);

        //TODO: support search criteria

        return criteria.list();
    }

    @Override
    public void createQualification(Qualification qualification) {
        this.sessionFactory.getCurrentSession().save(qualification);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
