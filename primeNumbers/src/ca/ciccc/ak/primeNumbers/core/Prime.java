package ca.ciccc.ak.primeNumbers.core;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * This class have methods to manage prime numbers.
 * 
 * @author alexkayser
 *
 */
public class Prime {

	/**
	 * This method generates a list of prime numbers using simple one-by-one
	 * mathematical verification. This is also known as brute force.
	 * 
	 * @param n
	 * @return List of prime numbers till n.
	 */
	public static List<Integer> primeNumbers(int n) {
		List<Integer> primeNumbers = new LinkedList<>();
		for (int i = 2; i <= n; i++) {
			if (isPrimeBruteForce(i)) {
				primeNumbers.add(i);
			}
		}
		return primeNumbers;
	}

	/**
	 * This method verify if a number is prime testing the division one-by-one from
	 * 2 to the number. This kind of verification is also known as brute force.
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isPrimeBruteForce(int number) {
		for (int i = 2; i < number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This method generates a list of prime numbers using the sieve of
	 * Eratosthenes.
	 * 
	 * @param n
	 * @return list of prime numbers till n.
	 */
	public static List<Integer> generatePrimeNumbers(int n) {
		// create a boolean array with the size of the numbers to be verified.
		boolean prime[] = new boolean[n + 1];
		// if the position is true means that this position is a prime number
		// e.g. prime[n]==true means n is a prime number
		Arrays.fill(prime, true);
		// starting by 2 because it's the first prime number, we don't need to test 0
		// and 1.
		for (int p = 2; p * p <= n; p++) {
			if (prime[p]) {
				for (int i = p * 2; i <= n; i += p) {
					prime[i] = false;
				}
			}
		}
		List<Integer> primeNumbers = new LinkedList<>();
		for (int i = 2; i <= n; i++) {
			if (prime[i]) {
				primeNumbers.add(i);
			}
		}
		return primeNumbers;
	}
	
	//Segmented Sieve of Eratosthenes 

	static int array[];
	static int primes[];

	public static List<Integer> segmentedEratosthenes(int n, int m) {
		int j = 0;
		int sqt = (int) Math.sqrt(m);
		array = new int[sqt + 1];
		primes = new int[sqt + 1];

		//initialise(sqt + 1);
		Arrays.fill(array, 1);
		for (int i = 2; i <= sqt; i++) {
			if (array[i] == 1) {
				primes[j] = i;
				j++;
				for (int k = i + i; k <= sqt; k += i) {
					array[k] = 0;
				}
			}
		}

		// printPrimesArray(j);
		int diff = (m - n + 1);
		array = new int[diff];
		//initialise(diff);
		Arrays.fill(array, 1);
		for (int k = 0; k < j; k++) {
			int div = n / primes[k];
			div *= primes[k];
			while (div <= m) {
				if (div >= n && primes[k] != div)
					array[div - n] = 0;
				div += primes[k];
			}
		}
		
		List<Integer> primeNumbers = new LinkedList<>();
		for (int i = 0; i < diff; i++) {
			if (array[i] == 1 && (i + n) != 1) {
				//System.out.println(i + n);
				primeNumbers.add(i + n);
			}
		}
		return primeNumbers;
	}

//	public static void initialise(int sqt) {
//		for (int i = 0; i < sqt; i++) {
//			array[i] = 1;
//		}
//	}

}
