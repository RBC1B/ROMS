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
package uk.org.rbc1b.roms.db.kingdomhall;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
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
        @SuppressWarnings("unchecked")
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

    @Override
    public void deleteKingdomHall(KingdomHall kingdomHall) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }
}
