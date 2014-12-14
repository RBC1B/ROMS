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
package uk.org.rbc1b.roms.db.application;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;

/**
 * Use hibernate to look up the user information.
 */
@Repository
public class HibernateUserDao implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findUser(String userName) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("userName", userName));

        @SuppressWarnings("unchecked")
        List<User> users = criteria.list();
        if (users.isEmpty()) {
            return null;
        }

        return users.get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findUsersByUserNamePrefix(String userName) {
        this.sessionFactory.getCurrentSession().clear();
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.like("userName", userName + "%"));
        criteria.addOrder(Order.asc("userName"));
        return criteria.list();
    }

    @Override
    public User findUser(Integer userId) {
        this.sessionFactory.getCurrentSession().clear();
        return (User) this.sessionFactory.getCurrentSession().get(User.class, userId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.gt("personId", 0));
        return criteria.list();
    }

    @Override
    public void updateUser(User user) {
        this.sessionFactory.getCurrentSession().merge(user);
    }

    @Override
    @CacheEvict(value = "user.userId", allEntries = true)
    public void createUser(User user) {
        this.sessionFactory.getCurrentSession().save(user);
    }
}
