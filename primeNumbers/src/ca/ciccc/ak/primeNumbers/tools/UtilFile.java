package ca.ciccc.ak.primeNumbers.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class UtilFile {

	public static void printFile(String path) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));

			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {
			System.out.println("No file to read.");
		}
	}

	public static void printFile(URL resource) {
		String path = resource.getPath();

		printFile(path);

	}

	/**
	 * Save the list on a CSV file.
	 * 
	 * @param list
	 */
	public static void saveOnFile(String path, List<Integer> list) {

		try {
			if (!path.endsWith(".csv")) {
				path += "prime.csv";
			}

			FileWriter writer = new FileWriter(path, true);
			CSVUtils.writeLine(writer, list);

			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void saveOnFile(String path, String string) {
		try {
			FileWriter writer = new FileWriter(path, true);
			writer.append("\n");
			writer.append(string);

			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
