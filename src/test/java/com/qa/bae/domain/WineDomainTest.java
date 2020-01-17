package com.qa.bae.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WineDomainTest {
	
	@Test
	public void equalsTest() {
	    
		Wine x = new Wine("test name", "test grape", "test description", "test tasting", 1);
		Wine y = new Wine("test name", "test grape", "test description", "test tasting", 1);
		assertTrue(x.equals(y) && y.equals(x));
		assertTrue(x.hashCode() == y.hashCode());	    
	}

}
