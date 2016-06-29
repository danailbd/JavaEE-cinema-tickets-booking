package rest;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import dao.impl.JpaMovieDAO;
import dao.models.Movie;

@Path("/movie")
public class MovieResource {
	private static final Response RESPONSE_OK = Response.ok().build();
	private static final Response RESPONSE_FAIL = Response.status(400).build();
	private static JpaMovieDAO dao = new JpaMovieDAO();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movie> getAllMovies() {
		try {
			List<Movie> allMovies = dao.getAllMovies();
			return allMovies;
		} catch (Exception e) {
			return null;
		}
	}

	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Movie getById(@PathParam("movieId") Integer movieId) {
		try {
			Movie movie = dao.getMovieById(movieId);
			return movie;
		} catch (Exception e) {
			return null;
		}
	}
	
	@PUT
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addMovie(Movie movie) {
		try {
			dao.addMovie(movie);
			return RESPONSE_OK;
		}catch(Exception e){
			return RESPONSE_FAIL;
		}

	};

	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateMovie(Movie movie) {
		try {
			dao.updateMovie(movie);
			return RESPONSE_OK;
		}catch(Exception e){
			return RESPONSE_FAIL;
		}
		
	};

	@POST
	@Path("/delete/{movieId}")
	public Response deleteMovie(@PathParam("movieId") Integer movieId) {
		try {
			dao.removeMovie(movieId);
			return RESPONSE_OK;
		} catch (Exception e) {
			return RESPONSE_FAIL;
		}
	};

}
