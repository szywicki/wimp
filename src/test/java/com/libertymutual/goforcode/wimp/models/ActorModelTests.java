package com.libertymutual.goforcode.wimp.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.libertymutual.goforcode.wimp.repositories.ActorRepository;

public class ActorModelTests {

	private ActorRepository actorRepo;
	private Actor actor;
	
	
	@Before
	public void setUp() {
		actorRepo = mock(ActorRepository.class);
		actor = new Actor();
	}
	
		
	@Test
	public void test_firstName_and_lastName() {
		// Arrange
		actor = new Actor();
		actor.setFirstName("Sarah");
		actor.setLastName("Jones");
//		when(actorRepo.save(actor)).thenReturn(actor);
		
		// Act
		actor.getFirstName();
		actor.getLastName();
		
		// Assert
//		verify(actorRepo).save(actor);
		assertThat(actor.getFirstName()).isSameAs("Sarah");
		assertThat(actor.getLastName()).isSameAs("Jones");
		
	}
	
	@Test
	public void test_getId_and_setId_returns_id() {
		// Arrange
		actor.setId(3l);
		
		// Act
		actor.getId();
		
		// Assert
		assertThat(actor.getId()).isSameAs(3l);
	}
	

	
}
