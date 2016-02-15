package net.joaoqalves.resources;

import net.joaoqalves.domain.film.Film;
import net.joaoqalves.services.FilmService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/films")
@Produces(MediaType.APPLICATION_JSON)
public class FilmResource {

    private SessionFactory sessionFactory;
    private FilmService filmService;

    public FilmResource(final SessionFactory sessionFactory, final FilmService filmService) {
        this.sessionFactory = sessionFactory;
        this.filmService = filmService;
    }

    @Path("/")
    @GET
    public List<Film> getAll() {
        Session session = sessionFactory.openSession();
        List<Film> result = filmService.getAll(session);
        session.close();
        return result;
    }

   @Path("/{id}")
    @GET
    public Response getOne(@PathParam("id") final Integer id) {
       Session session = sessionFactory.openSession();
       Optional<Film> result = filmService.getOne(session, id);
       session.close();
       if(result.isPresent()) {
           return Response.ok(result.get()).build();
       } else {
           return Response.status(Response.Status.NOT_FOUND).build();
       }
    }

}
