package net.joaoqalves.services;

import net.joaoqalves.dao.CustomerDAO;
import net.joaoqalves.domain.customer.Customer;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class CustomerService {

    private CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> getAll(final  Session session) {
        return customerDAO.getAll(session);
    }

    public Optional<Customer> getOne(final Session session, final Integer id) {
        return customerDAO.getOne(session, id);
    }

    public Optional<Customer> save(final Session session, final Customer customer) {
        try {
            customerDAO.save(session, customer);
            return Optional.of(customer);
        } catch (Exception ex) {
            ex.printStackTrace();
            return Optional.empty();
        }
    }
}
