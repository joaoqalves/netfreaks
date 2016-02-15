package net.joaoqalves.resources;

import net.joaoqalves.domain.rental.Rental;
import net.joaoqalves.services.RentalService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/rentals")
@Produces(MediaType.APPLICATION_JSON)
public class RentalResource {

    private SessionFactory sessionFactory;
    private RentalService rentalService;

    public RentalResource(final SessionFactory sessionFactory, final RentalService rentalService) {
        this.sessionFactory = sessionFactory;
        this.rentalService = rentalService;
    }

    @Path("/")
    @GET
    public List<Rental> getAll() {
        Session session = sessionFactory.openSession();
        List<Rental> result = rentalService.getAll(session);
        session.close();
        return result;
    }


}
