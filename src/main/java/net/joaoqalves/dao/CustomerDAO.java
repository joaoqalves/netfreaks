package net.joaoqalves.dao;

import net.joaoqalves.domain.customer.Customer;

public class CustomerDAO extends AbstractDAO<Integer, Customer> {

    public CustomerDAO() {
        super(Customer.class);
    }

    public CustomerDAO(final Class<Customer> clazz) {
        super(clazz);
    }
}
