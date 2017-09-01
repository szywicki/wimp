package com.libertymutual.goforcode.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import java.util.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;

public class ActorApiControllerTests {

	private ActorRepository actorRepo;
	private ActorApiController controller;
	
	@Before
	public void setUp() {
		actorRepo = mock(ActorRepository.class);
		controller = new ActorApiController(actorRepo);
	}

	@Test
	public void test_getAll_returns_all_actors_returned_by_the_repo() {
		// Arrange
		ArrayList<Actor> actors = new ArrayList<Actor>();
		actors.add(new Actor());
		actors.add(new Actor());
		actors.add(new Actor());
		
		when(actorRepo.findAll()).thenReturn(actors);
		
		// Act
		List<Actor> actual = controller.getAll();
		
		// Assert 
		assertThat(actual.size()).isEqualTo(3);
		assertThat(actual.get(0)).isSameAs(actors.get(0));
		verify(actorRepo).findAll();
	}
	
	@Test
	public void test_getOne_returns_Actor_returned_from_repo() throws StuffNotFoundException {
		// Arrange
		Actor Stone = new Actor();
		when(actorRepo.findOne(2l)).thenReturn(Stone);
		
		// Act
		Actor actual = controller.getOne(2l);
		
		//Assert
		assertThat(actual).isSameAs(Stone);
		verify(actorRepo).findOne(2l);
		
	}
	
	@Test
	public void test_getOne_throws_StuffNotFoundException_when_no_actor_returned_from_repo() {
		try {
			controller.getOne(1);
			
			fail("The controller did not throw the stuff not found exception");
		} catch(StuffNotFoundException snfe) {}
	}
	
	@Test
	public void test_delete_returns_actor_deleted_when_found() {
		// Arrange
		Actor actor = new Actor();
		when(actorRepo.findOne(5l)).thenReturn(actor);
		
		// Act
		Actor actual = controller.deleteOne(5l);
		
		// Assert
		assertThat(actor).isSameAs(actual);
		verify(actorRepo).delete(5l);
		verify(actorRepo).findOne(5l);
	}
	
	@Test
	public void test_that_null_is_returned_when_findOne_throws_EmptyResultDataAccessException() {
		// Arrange
		when(actorRepo.findOne(5l)).thenThrow(new EmptyResultDataAccessException(0));
		
		// Act
		Actor actual = controller.deleteOne(5l);
		
		// Assert
		assertThat(actual).isNull();
		verify(actorRepo).findOne(5l);
	}
	
	@Test
	public void test_create_saves_and_returns_an_actor() {
		// Arrange
		Actor Stone = new Actor();
		when(actorRepo.save(Stone)).thenReturn(Stone);
		
		// Act
		Actor actual = controller.create(Stone);
		
		// Assert
		verify(actorRepo).save(Stone);
		assertThat(Stone).isSameAs(actual);
	}
	
	@Test
	public void test_update_returns_actor_with_changes_made() throws StuffNotFoundException {
		// Arrange
		Actor Stone = new Actor();
		when(actorRepo.save(Stone)).thenReturn(Stone);
		
		// Act
		Actor actual = controller.update(Stone, 3l);
		
		// Assert
		verify(actorRepo).save(Stone);
		assertThat(actual).isSameAs(Stone);
	}
}
