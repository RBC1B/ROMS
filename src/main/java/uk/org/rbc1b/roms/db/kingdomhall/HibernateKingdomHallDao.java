/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.kingdomhall;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author oliver.elder.esq
 */
@Repository
public class HibernateKingdomHallDao implements KingdomHallDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public KingdomHall findKingdomHall(Integer kingdomHallId) {
        return (KingdomHall) this.sessionFactory.getCurrentSession().get(KingdomHall.class, kingdomHallId);
    }

    @Override
    public List<KingdomHall> findKingdomHalls() {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(KingdomHall.class);
        List<KingdomHall> halls = criteria.list();
        for (KingdomHall hall : halls) {
            hall.setFeatures(null);
        }
        return halls;
    }

    @Override
    public void createKingdomHall(KingdomHall kingdomHall) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
