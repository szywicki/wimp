package com.libertymutual.goforcode.wimp.models;

import static org.mockito.Mockito.mock;

import org.junit.Before;

import com.libertymutual.goforcode.wimp.repositories.ActorRepository;

public class MovieModelTests {
	
	private ActorRepository actorRepo;
	
	@Before
	public void setUp() {
		actorRepo = mock(ActorRepository.class);
		Actor actor = new Actor();
	}
	
//	@Test
//	public void test_add_Actor_if_null_creates_new_list
	
}
