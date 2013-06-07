/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.application;

import java.util.HashSet;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * User Hibernate to look up the user information.
 *
 * @author oliver.elder.esq
 */
@Repository
public class HibernateUserDao implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Cacheable("user.userName")
    public User findUser(String userName) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("userName", userName));

        List<User> users = criteria.list();
        if (users.isEmpty()) {
            return null;
        }

        User user = users.get(0);

        criteria = this.sessionFactory.getCurrentSession().createCriteria(ApplicationAccess.class);
        criteria.setFetchMode("application", FetchMode.JOIN);
        criteria.add(Restrictions.eq("person.personId", user.getPersonId()));
        List<ApplicationAccess> access = criteria.list();

        user.setApplicationAccess(new HashSet<ApplicationAccess>(access));

        return user;
    }
}
