package ca.ciccc.ak.primeNumbers.view.console;

import java.util.List;
import java.util.Scanner;

import ca.ciccc.ak.primeNumbers.core.Prime;
import ca.ciccc.ak.primeNumbers.interfaces.View;
import ca.ciccc.ak.primeNumbers.tools.UtilFile;

public class ConsoleApplication implements View {

	private Scanner scanner;
	private String option = "";

	public static final int SAVE_LIMIT = 100;

	@Override
	public void start() {
		scanner = new Scanner(System.in);

		while (!"v".equalsIgnoreCase(option) && !"g".equalsIgnoreCase(option)) {
			System.out.println("# OPTIONS:");
			System.out.println("# V - Verify if a number is prime");
			System.out.println("# G - Generate a list of prime numbers");
			System.out.println("# Q - Quit the program");

			option = scanner.nextLine().toLowerCase();

			if ("q".equals(option)) {
				scanner.close();
				return;
			}

			switch (option) {
			case "v":
				addOutput("Number to be verified: ");
				int suspect = scanner.nextInt();
				boolean isPrime = Prime.isPrimeBruteForce(suspect);
				addOutput(suspect + " is" + (isPrime ? " " : " not ") + "a prime number.");
				break;
			case "g":
				addOutput("Number limit: ");
				int limit = scanner.nextInt();
				addOutput("Path to save the list: ");
				String path = scanner.nextLine();

				// por partes
				int a = 2, b = (limit > SAVE_LIMIT) ? SAVE_LIMIT : limit;
				while (b <= limit) {
					List<Integer> primes = Prime.segmentedEratosthenes(a, b);
					UtilFile.saveOnFile(path, primes);
					a = b + 1;
					b = (b + SAVE_LIMIT) > limit ? limit : (b + SAVE_LIMIT);
				}

				Prime.generatePrimeNumbers(limit);

			default:
				throw new IllegalArgumentException("Unexpected value: " + option);
			}

		}

		scanner.close();

	}

	@Override
	public void setTaskProgress(long progress) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setStatusText(String statusText) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addOutput(String text) {
		System.out.println(">> " + text);
	}

}
