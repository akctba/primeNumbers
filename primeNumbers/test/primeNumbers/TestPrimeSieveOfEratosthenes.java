package primeNumbers;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import ca.ciccc.ak.primeNumbers.core.Prime;

class TestPrimeSieveOfEratosthenes {

	@Test
	void testGen() {
		//test 2 to 10 using the Sieve of Eratosthenes
		List<Integer> list = Prime.generatePrimeNumbers(10);
		
		Integer[] actuals = new Integer[list.size()];
		actuals = list.toArray(actuals);
		
		Integer[] expecteds = {2, 3, 5, 7};
		
		Assert.assertArrayEquals("2-10 prime numbers", expecteds, actuals);
	}

}
