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
package uk.org.rbc1b.roms.db.circuit;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author oliver.elder.esq
 */
@Repository
public class HibernateCircuitDao implements CircuitDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Circuit findCircuit(final Integer circuitId) {
        return (Circuit) this.sessionFactory.getCurrentSession().get(Circuit.class, circuitId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Circuit> findCircuits() {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Circuit.class);

        // TODO: support search criteria

        return criteria.list();
    }

    @Override
    public void createCircuit(Circuit circuit) {
        this.sessionFactory.getCurrentSession().save(circuit);
    }

    @Override
    public void updateCircuit(Circuit circuit) {
        this.sessionFactory.getCurrentSession().merge(circuit);
    }

    /**
     * @param sessionFactory hibernate session factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
