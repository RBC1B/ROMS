/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Implements PersonDao.
 *
 * @author rahulsingh
 */
@Repository
public class HibernatePersonDao implements PersonDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Person findPerson(Integer personId) {
        return (Person) this.sessionFactory.getCurrentSession().get(Person.class, personId);
    }

    @Override
    public List<Person> findPersons(String forename, String surname) {
        Session session = this.sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Person.class)
                .add(Restrictions.eq("forename", forename)).add(Restrictions.eq("surname", surname));

        return criteria.list();
    }

    @Override
    public List<Person> findPersons(PersonSearchCriteria searchCriteria) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Person.class);
        criteria.setMaxResults(100);

        if (searchCriteria.getForename() != null) {
            criteria.add(Restrictions.like("forename", "%" + searchCriteria.getForename() + "%"));
        }
        if (searchCriteria.getSurname() != null) {
            criteria.add(Restrictions.like("surname", "%" + searchCriteria.getSurname() + "%"));
        }
        if (searchCriteria.getEmail() != null) {
            criteria.add(Restrictions.like("email", "%" + searchCriteria.getEmail() + "%"));
        }
        if (searchCriteria.getCongregationId() != null) {
            criteria.add(Restrictions.eq("congregationId", searchCriteria.getCongregationId()));
        }

        return criteria.list();
    }

    @Override
    public void savePerson(Person person) {
        if (person.getPersonId() != null) {
            this.sessionFactory.getCurrentSession().merge(person);
        } else {
            this.sessionFactory.getCurrentSession().save(person);
        }
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
