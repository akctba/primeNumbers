package primeNumbers;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import ca.ciccc.ak.primeNumbers.core.Prime;


class TestPrimeBruteForce {

	@Test
	void testThree() {
		boolean primeBruteForce = Prime.isPrimeBruteForce(3);
		
		Assert.assertTrue("3 is prime number", primeBruteForce);
	}
	
	@Test
	void testSix() {
		boolean primeBruteForce = Prime.isPrimeBruteForce(6);
		
		Assert.assertFalse("6 is not prime number", primeBruteForce);
	}
	
	
	@Test
	void testGen() {
		//test brute force 2 to 10
		List<Integer> list = Prime.primeNumbers(10);
		
		Integer[] actuals = new Integer[list.size()];
		actuals = list.toArray(actuals);
		
		Integer[] expecteds = {2, 3, 5, 7};
		
		Assert.assertArrayEquals("2-10 prime numbers", expecteds, actuals);
	}
	

}
