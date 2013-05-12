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
    public Qualification findQualification(final Integer qualificationId) {
        return (Qualification) this.sessionFactory.getCurrentSession().get(Qualification.class, qualificationId);
    }

    @Override
    public List<Qualification> findQualifications() {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Qualification.class);

        //TODO: support search criteria

        return criteria.list();
    }

    @Override
    public void saveQualification(Qualification qualification) {
        this.sessionFactory.getCurrentSession().update(qualification);
    }

    @Override
    public void createQualification(Qualification qualification) {
        this.sessionFactory.getCurrentSession().save(qualification);
    }

    @Override
    public void deleteQualification(Qualification qualification) {
        this.sessionFactory.getCurrentSession().delete(qualification);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
