package com.libertymutual.goforcode.wimp.api;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.MovieRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/movies")
@Api(description="Use this to get and create movies and add actors to movies.")
public class MovieApiController {

	private MovieRepository movieRepo;
	private ActorRepository actorRepo;
	
	public MovieApiController(MovieRepository movieRepo, ActorRepository actorRepo) {
		this.movieRepo = movieRepo;
		this.actorRepo = actorRepo;
		
//		Movie movie = new Movie();
//		movie.setTitle("The Iron Yard Chronicles");
//		movie.setDistributor("Low Budget Films, Inc.");
////		movie.addActor(actorRepo.findAll().get(0));
//		movieRepo.save(movie);
	}
	
	@ApiOperation("gets movie by id and the actors related to that movie.")
	@PostMapping("{movieId}/actors")
	public Movie associateAnActor(@PathVariable long movieId, @RequestBody Actor actor) {
		 Movie movie = movieRepo.findOne(movieId);
		 Long actorId = actor.getId();
		 actor = actorRepo.findOne(actorId);
		 
		 movie.addActor(actor);
		 movieRepo.save(movie);
		 
		 return movie;
	}
	
	@ApiOperation("Gets list of movies.")
	@GetMapping("")
	public List<Movie> getAll() {
		return movieRepo.findAll();
	}
	
	@ApiOperation("Gets movie by id.")
	@GetMapping("{id}")
	public Movie getOne(@PathVariable long id) throws StuffNotFoundException {
		Movie movie = movieRepo.findOne(id);
		if (movie == null) {
			throw new StuffNotFoundException();
		}
		return movie;
	}
	
	@ApiOperation("Creates a movie.")
	@PostMapping("")
	public Movie create(@RequestBody Movie movie) {
		return movieRepo.save(movie);
	}
	
	@ApiOperation(value="Gets movie by id and updates changes.",
					notes="you only need to POST the id of the movie")
	@PutMapping("{id}")
	public Movie update(@RequestBody Movie movie, @PathVariable long id) throws StuffNotFoundException {
		movie.setId(id);
		return movieRepo.save(movie);
	}
	
	@ApiOperation("Gets movie by id and deletes. Returns null if id not found.")
	@DeleteMapping("{id}")
	public Movie deleteOne(@PathVariable long id) {
		try {
			Movie movie = movieRepo.findOne(id);
			movieRepo.delete(id);
			return movie;
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
		
	}
}
