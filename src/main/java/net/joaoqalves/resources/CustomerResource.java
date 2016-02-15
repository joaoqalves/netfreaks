package net.joaoqalves.resources;

import net.joaoqalves.domain.customer.Customer;
import net.joaoqalves.domain.rental.NewRental;
import net.joaoqalves.domain.returnfilm.NewReturn;
import net.joaoqalves.domain.customer.CustomerNotExists;
import net.joaoqalves.services.CustomerService;
import net.joaoqalves.services.RentalService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {

    private SessionFactory sessionFactory;
    private CustomerService customerService;
    private RentalService rentalService;

    public CustomerResource(SessionFactory sessionFactory,
            CustomerService customerService, RentalService rentalService) {
        this.sessionFactory = sessionFactory;
        this.customerService = customerService;
        this.rentalService = rentalService;
    }

    @Path("/")
    @GET
    public List<Customer> getAll() {
        Session session = sessionFactory.openSession();
        List<Customer> result = customerService.getAll(session);
        session.close();
        return result;
    }

    @Path("/{id}")
    @GET
    public Response getOne(@PathParam("id") final Integer id) {
        Session session = sessionFactory.openSession();
        Optional<Customer> result = customerService.getOne(session, id);
        session.close();
        if(result.isPresent()) {
            return Response.ok(result.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/{id}/rent")
    @POST
    public Response rentFilms(@PathParam("id") final Integer id, final List<NewRental> rentals) {
        Session session = sessionFactory.openSession();
        try {
            return Response.ok(rentalService.rentFilms(session, id, rentals)).build();
        } catch (CustomerNotExists ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } finally {
            session.close();
        }
    }


    @Path("/{id}/return")
    @POST
    public Response returnFilms(@PathParam("id") final Integer id, List<NewReturn> returns) {
        Session session = sessionFactory.openSession();
        try {
            return Response.ok(rentalService.returnFilms(session, id, returns)).build();
        } catch (CustomerNotExists ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } finally {
            session.close();
        }
    }

}
