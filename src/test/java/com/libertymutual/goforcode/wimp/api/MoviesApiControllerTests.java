package com.libertymutual.goforcode.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.MovieRepository;






public class MoviesApiControllerTests {

	private MovieRepository movieRepo;
	private MovieApiController controller;
	private ActorRepository actorRepo;

	
	
	@Before
	public void setUp() {
		movieRepo = mock(MovieRepository.class);
		actorRepo = mock(ActorRepository.class);
		controller = new MovieApiController(movieRepo, actorRepo);
		
	}
	
	@Test
	public void test_associateAnActor_returns_movie_with_id_given() {
		// Arrange
		Movie movie = new Movie();
		Actor actor = new Actor();
		actor.setId(3l);
		when(movieRepo.findOne(2l)).thenReturn(movie);
		when (actorRepo.findOne(3l)).thenReturn(actor);
		
		// Act
		Movie actual = controller.associateAnActor(2l, actor);
		
		//Assert
		verify(movieRepo).save(movie);
		verify(movieRepo).findOne(2l);
		verify(actorRepo).findOne(3l);
		assertThat(actual.getActors()).hasSize(1);
	}
	
	@Test
	public void test_getAll_returns_all_movies_returned_by_the_repo() {
		// Arrange
		ArrayList<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie());
		movies.add(new Movie());
		
		when(movieRepo.findAll()).thenReturn(movies);
		
				
		// Act
		List<Movie> actual = controller.getAll();
		
		// Assert
		assertThat(actual.size()).isEqualTo(2);
		assertThat(actual.get(0)).isSameAs(movies.get(0));
		verify(movieRepo).findAll();
		
	}
	
	@Test
	public void test_getOne_returns_Movie_returned_from_repo() throws StuffNotFoundException {
		// Arrange
		Movie Rocky = new Movie();
		when(movieRepo.findOne(4l)).thenReturn(Rocky);
		
		// Act
		Movie actual = controller.getOne(4l);
		
		// Assert
		assertThat(actual).isSameAs(Rocky);
		verify(movieRepo).findOne(4l);
	}
	
	@Test
	public void test_create_saves_and_returns_a_movie() {
		// Arrange
		Movie Rocky = new Movie();
		when(movieRepo.save(Rocky)).thenReturn(Rocky);
				
		// Act
		Movie actual = controller.create(Rocky);
		
		// Assert
		verify(movieRepo).save(Rocky);
		assertThat(Rocky).isSameAs(actual);
	}
	
	@Test
	public void test_update_returns_movie_with_changes_made() throws StuffNotFoundException {
		// Arrange
		Movie Rocky = new Movie();
		when(movieRepo.save(Rocky)).thenReturn(Rocky);
		
		// Act
		Movie actual = controller.update(Rocky, 4l);
		
		// Assert
		verify(movieRepo).save(Rocky);
		assertThat(actual).isSameAs(Rocky);
	}
	
	@Test
	public void test_getOne_throws_StuffNotFound_when_no_movie_returned_from_repo() {
		try {
			controller.getOne(1);
			
			// This line of code SHOULD NOT run
			fail("The controller did not throw the stuff not found exception");
		} catch(StuffNotFoundException snfe) {}
	}
	
	@Test
	public void test_delete_returns_movie_deleted_when_found() {
		
		// Arrange
		Movie movie = new Movie();
		when(movieRepo.findOne(3l)).thenReturn(movie);
		
		// Act
		Movie actual = controller.deleteOne(3l);
		
		// Assert
		assertThat(movie).isSameAs(actual);
		verify(movieRepo).delete(3l);
		verify(movieRepo).findOne(3l);
	}
	
	@Test 
	public void test_that_null_is_returned_one_findOne_throws_EmptyResultDataAccessException() throws StuffNotFoundException {
		// Arrange
		when(movieRepo.findOne(8l)).thenThrow(new EmptyResultDataAccessException(0));
		
		// Act
		Movie actual = controller.deleteOne(8l);
		
		// Assert
		assertThat(actual).isNull();
		verify(movieRepo).findOne(8l);
	}

}
