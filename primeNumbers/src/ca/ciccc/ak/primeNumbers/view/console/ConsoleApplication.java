package ca.ciccc.ak.primeNumbers.view.console;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import ca.ciccc.ak.primeNumbers.core.Prime;
import ca.ciccc.ak.primeNumbers.interfaces.View;
import ca.ciccc.ak.primeNumbers.tools.UtilFile;

public class ConsoleApplication implements View {

	private Scanner scanner;
	private String option = "";
	private Integer num;
	private String path;

	public static final int SAVE_LIMIT = 100;

	public ConsoleApplication() {
		// default contructor
	}

	public ConsoleApplication(String[] args) {
		if (args.length > 0) {
			option = args[0];
		}
		if (args.length > 1) {
			try {
				num = Integer.decode(args[1]);
			} catch (NumberFormatException e) {
				num = null;
			}
		}
		
		if (args.length > 2) {
			path = args[2];
		}
	}

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
		}

		switch (option) {
		case "v":
			if (num == null) {
				addOutput("Number to be verified: ");
				num = scanner.nextInt();
			}
			boolean isPrime = Prime.isPrimeBruteForce(num);
			addOutput(num + " is" + (isPrime ? " " : " not ") + "a prime number.");
			break;
		case "g":
			if(num == null) {
				addOutput("Number limit: ");
				num = scanner.nextInt();
			}
			if(path == null) {
				addOutput("Path to save the list: ");
				path = scanner.next();
			}
			//TODO UtilFile.validateFile(path);
			
			Date ini = new Date();

			// por partes
			int a = 2, b = (num > SAVE_LIMIT) ? SAVE_LIMIT : num;
			while (b <= num) {
				List<Integer> primes = Prime.segmentedEratosthenes(a, b);
				UtilFile.saveOnFile(path, primes);
				a = b + 1;
				b = (b + SAVE_LIMIT) > num ? num : (b + SAVE_LIMIT);
				if(a >= num)
					break;
			}
			
			Date end = new Date();
			long dif = end.getTime() - ini.getTime();
			UtilFile.saveOnFile(path, dif + " milliseconds");

			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + option);
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
