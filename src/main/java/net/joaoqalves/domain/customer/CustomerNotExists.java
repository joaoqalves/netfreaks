package net.joaoqalves.domain.customer;

public class CustomerNotExists extends Exception {
    public CustomerNotExists() {
    }

    public CustomerNotExists(final String userId) {
        super("User with ID " + userId + " does not exist");
    }
}
