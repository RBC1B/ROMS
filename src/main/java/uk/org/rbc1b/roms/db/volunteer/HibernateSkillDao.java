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
 * @author ramindursingh
 */
@Repository
public class HibernateSkillDao implements SkillDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Skill findSkill(final Integer skillId) {
        return (Skill) this.sessionFactory.getCurrentSession().get(Skill.class, skillId);
    }

    @Override
    public List<Skill> findSkills() {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Skill.class);

        //TODO: support search criteria

        return criteria.list();
    }

    @Override
    public void createSkill(Skill skill) {
        this.sessionFactory.getCurrentSession().save(skill);
    }

    /**
     * @param sessionFactory hibernate session factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
