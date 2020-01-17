package com.qa.bae.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FoodDomainTests {
	
	@Test
	public void equalsTest() {
	    
		Food x = new Food("Test name", "Test allergens", "Test description", 1);
		Food y = new Food("Test name", "Test allergens", "Test description", 1);
		assertTrue(x.equals(y) && y.equals(x));
		assertTrue(x.hashCode() == y.hashCode());	    
	}

}
