/*
 * The MIT License
 *
 * Copyright 2013 RBC1B.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
 * Implementation of PersonChangeDao.
 *
 * @author ramindursingh
 */
@Repository
public class HibernatePersonChangeDao implements PersonChangeDao {
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public PersonChange findPersonChange(Integer personChangeId) {
        return (PersonChange) this.sessionFactory.getCurrentSession().get(PersonChange.class, personChangeId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PersonChange> findPersonChanges(Integer personId) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(PersonChange.class).add(Restrictions.eq("personId", personId));
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PersonChange> findPersonChangeNotUpdated() {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(PersonChange.class).add(Restrictions.eq("formUpdated", false));
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PersonChange> findPersonChange() {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(PersonChange.class);
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void updatePersonChange(PersonChange personChange) {
        this.sessionFactory.getCurrentSession().merge(personChange);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void savePersonChange(PersonChange personChange) {
        this.sessionFactory.getCurrentSession().save(personChange);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Person getOldPerson(Integer personId, Person person) {
        Session session = this.sessionFactory.openSession();
        session.evict(person);
        Person oldPerson = (Person) session.get(Person.class, personId);
        session.close();
        return oldPerson;
    }

}
