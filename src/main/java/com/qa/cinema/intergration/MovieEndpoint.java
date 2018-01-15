package com.qa.cinema.intergration;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import com.qa.cinema.business.repository.MovieDBImpe;
import com.qa.cinema.persistence.Movie;
import com.qa.cinema.util.JSONUtil;

@Path("/cinema")
public class MovieEndpoint {

	@Inject
	private MovieDBImpe service;
	@Inject
	private JSONUtil util;

	@GET
	@Path("/json")
	public String getAllMovies() {
		List<Movie> movieList = service.findAllMovies();
		
		return util.getJSONForObject(movieList);
	}

	@GET
	@Path("/json/{id}")
	public String getMovie(@PathParam("id") Long id) {
		Movie toReturn = service.findMovie(id);

		return util.getJSONForObject(toReturn);
	}

	@POST
	@Path("/json")
	public String createMovie(String movieToAdd) {
		return service.createMovieFromString(movieToAdd);
	}

	@PUT
	@Path("/json/{id}")
	public String updateMovie(@PathParam("id") Long id, String newDetails) {
		return service.updateMovieFromString(id, newDetails);
	}
	
	@DELETE
	@Path("/json/{id}")
	public String deleteMovie(@PathParam("id") Long id) {
		return service.deleteMovie(id);
	}
}
