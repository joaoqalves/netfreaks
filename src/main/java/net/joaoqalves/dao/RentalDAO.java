package net.joaoqalves.dao;

import net.joaoqalves.domain.customer.Customer;
import net.joaoqalves.domain.rental.Rental;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class RentalDAO extends AbstractDAO<Integer, Rental> {

    public RentalDAO() {
        super(Rental.class);
    }

    public RentalDAO(final Class<Rental> clazz) {
        super(clazz);
    }

    public List<Rental> findToDeliverByCustomer(final Session session, final Customer customer) {
        return session.createCriteria(Rental.class)
                .add(Restrictions.eq("customer", customer))
                .add(Restrictions.isNull("deliveredAt"))
                .list();
    }
}
