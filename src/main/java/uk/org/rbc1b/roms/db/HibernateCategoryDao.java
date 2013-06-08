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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ramindursingh
 */
@Repository
public class HibernateCategoryDao implements CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Cacheable("category.category")
    public Category findCategoryById(Integer categoryId) {
        return (Category) this.sessionFactory.getCurrentSession().get(Category.class, categoryId);
    }

    @Override
    public Category findCategoryByName(String name) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Category.class);
        return (Category) criteria.add(Restrictions.naturalId().set("name", name)).setCacheable(true).uniqueResult();
    }

    @Override
    public List<Category> getAllCategories() {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Category.class);
        return criteria.list();
    }

    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
