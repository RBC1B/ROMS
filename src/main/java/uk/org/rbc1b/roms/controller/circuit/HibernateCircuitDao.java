/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.circuit;



import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.db.congregation.Circuit;

/**
 *
 * @author oliver.elder.esq
 */
@Component
public class HibernateCircuitDao implements CircuitDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Circuit findCircuit(final String name) {
        return (Circuit) this.sessionFactory.getCurrentSession().get(Circuit.class, name);
    }

    @Override
    public List<Circuit> findCircuits() {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Circuit.class);

        //TODO: support search criteria

        return criteria.list();
    }

    @Override
    public void createCircuit(Circuit circuit) {
        this.sessionFactory.getCurrentSession().save(circuit);
    }

    /**
     * @param sessionFactory  hibernate session factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
