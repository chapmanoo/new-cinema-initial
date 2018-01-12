package com.qa.cinema.business.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.qa.cinema.persistence.Movie;
import com.qa.cinema.util.JSONUtil;

@Transactional(Transactional.TxType.SUPPORTS)
public class MovieDBImpe {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	private JSONUtil util = new JSONUtil();

	@Transactional(Transactional.TxType.REQUIRED)
	public String createMovie(String movie) {
		Movie aMovie = util.getObjectForJSON(movie, Movie.class);
		em.persist(aMovie);
		return "{\"message\": \"movie sucessfully added\"}";
	}
	
	@Transactional(Transactional.TxType.REQUIRED)
	public String updateMovie(String newDetails) {
		Movie aMovie = util.getObjectForJSON(newDetails, Movie.class);
		em.merge(aMovie);
		return "{\"message\": \"movie sucessfully updated\"}";
	}
	
	@Transactional(Transactional.TxType.REQUIRED)
	public String deleteMovie(long id) {
		em.remove(findMovie(id));				
		return "{\"message\": \"movie sucessfully removed\"}";
	}

	public List<Movie> findAllMovies() {
        TypedQuery<Movie> query = em.createQuery(
        		"SELECT m FROM Movie m ORDER BY m.title DESC",
        		Movie.class);
        
        return query.getResultList();
    }
	
	public Movie findMovie(Long id) {
        return em.find(Movie.class, id);
    }

	@Transactional(Transactional.TxType.REQUIRED)
    public Movie create(Movie movie) {
        em.persist(movie);
        return movie;
    }


}
