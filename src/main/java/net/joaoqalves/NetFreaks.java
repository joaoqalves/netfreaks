package net.joaoqalves;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.joaoqalves.dao.CustomerDAO;
import net.joaoqalves.dao.FilmDAO;
import net.joaoqalves.dao.RentalDAO;
import net.joaoqalves.resources.CustomerResource;
import net.joaoqalves.resources.FilmResource;
import net.joaoqalves.resources.RentalResource;
import net.joaoqalves.services.CustomerService;
import net.joaoqalves.services.FilmService;
import net.joaoqalves.services.RentalService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class NetFreaks extends Application<NetFreaksConfiguration> {

    @Override
    public void run(NetFreaksConfiguration netFreaksConfiguration, Environment environment) throws Exception {

        // Hibernate Session
        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();

        // DAO
        CustomerDAO customerDAO = new CustomerDAO();
        FilmDAO filmDAO = new FilmDAO();
        RentalDAO rentalDAO = new RentalDAO();

        CustomerService customerService = new CustomerService(customerDAO);
        FilmService filmService = new FilmService(filmDAO);
        RentalService rentalService = new RentalService(customerService, filmService, rentalDAO);


        // Create Data
        Seed.createData(sessionFactory);

        environment.jersey().register(new RentalResource(sessionFactory, rentalService));
        environment.jersey().register(new FilmResource(sessionFactory, filmService));
        environment.jersey().register(new CustomerResource(sessionFactory, customerService, rentalService));
    }

    public void initialize(Bootstrap<NetFreaksConfiguration> bootstrap) {

    }

    public static void main(String[] args) throws Exception {
        new NetFreaks().run(args);
    }

}
