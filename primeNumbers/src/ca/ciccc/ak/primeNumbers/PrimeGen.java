package ca.ciccc.ak.primeNumbers;

import java.io.InputStream;

import ca.ciccc.ak.primeNumbers.interfaces.View;
import ca.ciccc.ak.primeNumbers.tools.UtilFile;
import ca.ciccc.ak.primeNumbers.view.console.ConsoleApplication;
import ca.ciccc.ak.primeNumbers.view.swing.GUIApplication;

public class PrimeGen {

	public static void main(String[] args) {

		System.out.println("##########################");
		System.out.println("# PRIME NUMBER GENERATOR #");
		System.out.println("##########################");
		System.out.println();

		String option = "";
		
		if (args != null && args.length > 0) {
			option = args[0];
		}

		if (isHelp(option) || args.length == 0) {
			printHelp();
		} else {
			View view;
			
			if ("gui".equalsIgnoreCase(option)) {
				view = new GUIApplication();
			} else {
				//Console application
				view = new ConsoleApplication(args);
			}

			view.start();
		}
	}

	/**
	 * Print help instructions
	 */
	private static void printHelp() {
		Class<UtilFile> clazz = UtilFile.class;
		InputStream helpFile = clazz.getResourceAsStream("/help.txt");
		System.out.println(helpFile);
		
		UtilFile.printFromInputStream(helpFile);

	}

	/**
	 * Options to call help instructions
	 * 
	 * @param option
	 * @return
	 */
	private static boolean isHelp(String option) {
		boolean isHelp = false;
		if (option != null && !"".equals(option)) {
			switch (option) {
			case "?":
			case "-?":
			case "-h":
			case "h":
			case "help":
			case "-help":
				isHelp = true;
				break;
			default:
				isHelp = false;
			}
		}
		return isHelp;
	}

}
