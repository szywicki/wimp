package com.libertymutual.goforcode.wimp.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.libertymutual.goforcode.wimp.repositories.ActorRepository;

public class MovieModelTests {
	
	private ActorRepository actorRepo;
	private Movie movie;
	
	@Before
	public void setUp() {
		actorRepo = mock(ActorRepository.class);
		Actor actor = new Actor();
		movie = new Movie();
	}
	
	@Test
	public void test_add_Actor_adds_new_actor() {
		// Arrange
		Actor actor = new Actor(); 
		
		// Act
		movie.addActor(actor);
		
		
		// Assert
		assertThat(movie.getActors()).contains(actor);
	}
	
	@Test
	public void test_get_and_set_Actors() {
		// Arrange
		ArrayList<Actor> actors = new ArrayList<Actor>();
		movie.setActors(actors);
		
		// Act
		movie.getActors();
		
		// Assert
		assertThat(movie.getActors()).isSameAs(actors);
	}
	
}
