/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.person;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.db.Person;

/**
 * Implements PersonDao.
 * @author rahulsingh
 */
@Component
public class HibernatePersonDao implements PersonDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Retrieve list of people from database against criteria.
     *
     * @param keyword terms to search person.
     * @return List of people.
     */
    @Override
    public List<Person> findPersons(String keyword) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Person.class)
                .add(Restrictions.eq("forename", keyword));

        return criteria.list();
    }
}
