package com.libertymutual.goforcode.wimp.models;

import org.junit.Test;
import org.meanbean.test.BeanTester;

public class TestBean {

	@Test
	public void testActor() {
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(Actor.class);
	}
	
	@Test
	public void testMovie() {
		BeanTester beanTester= new BeanTester();
		beanTester.testBean(Movie.class);
	}
}
