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
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/actors")
@Api(description="Use this to get and create actors.")
public class ActorApiController {

	private ActorRepository actorRepo;
	
	public ActorApiController(ActorRepository actorRepo) {
		this.actorRepo = actorRepo;
		
//		actorRepo.save(new Actor("Curtis", "Schlak"));
//		actorRepo.save(new Actor("Kalea", "Wolff"));
	}
	
	@ApiOperation("Gets list of actors.")
	@GetMapping("")
	public List<Actor> getAll() {
		return actorRepo.findAll();
	}
	
	@ApiOperation("Find actor by id. Throws exception if id not found.")
	@GetMapping("{id}")
	public Actor getOne(@PathVariable long id) throws StuffNotFoundException{
		Actor actor = actorRepo.findOne(id);
		if (actor == null) {
			throw new StuffNotFoundException();
		}
//		ActorWithMovies newActor = new ActorWithMovies();
//		newActor.setId(actor.getId());
//		newActor.setActiveSinceYear(actor.getActiveSinceYear());
//		newActor.setBirthDate(actor.getBirthDate());
//		newActor.setFirstName(actor.getFirstName());
//		newActor.setLastName(actor.getLastName());
//		newActor.setMovies(actor.getMovies());
//		
		return actor;
	}
	
	@ApiOperation("Find actor by id and deletes it. Returns null if id not found.")
	@DeleteMapping("{id}")
	public Actor deleteOne(@PathVariable long id) {
		try {
			Actor actor = actorRepo.findOne(id);
			actorRepo.delete(id);
			return actor;
		} catch (EmptyResultDataAccessException erdae) {
			return null;
	}
	}
	
	@ApiOperation("Creates an actor and saves it.")
	@PostMapping("")
	public Actor create(@RequestBody Actor actor) {
		return actorRepo.save(actor);
	}
	
	@ApiOperation("Updates an actor and saves changes.")
	@PutMapping("{id}")
	public Actor update(@RequestBody Actor actor, @PathVariable long id) throws StuffNotFoundException {
		actor.setId(id);
		return actorRepo.save(actor);
	}
}
