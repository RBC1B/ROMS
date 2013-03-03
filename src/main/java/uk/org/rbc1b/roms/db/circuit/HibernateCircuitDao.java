/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.circuit;

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
public class HibernateCircuitDao implements CircuitDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Circuit findCircuit(final Integer circuitId) {
        return (Circuit) this.sessionFactory.getCurrentSession().get(Circuit.class, circuitId);
    }

    @Override
    public List<Circuit> findCircuits() {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Circuit.class);

        //TODO: support search criteria

        return criteria.list();
    }

    /**
     * Update or create a new circuit.
     *
     * @param circuit object to be updated
     */
    @Override
    public void saveCircuit(Circuit circuit) {
        if (circuit.getCircuitId() == null) {
            this.sessionFactory.getCurrentSession().save(circuit);
        } else {
            this.sessionFactory.getCurrentSession().merge(circuit);
        }
    }

    /**
     * @param sessionFactory hibernate session factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
