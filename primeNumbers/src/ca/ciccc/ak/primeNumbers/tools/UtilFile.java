package ca.ciccc.ak.primeNumbers.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

	public static void printFromInputStream(InputStream inputStream) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			System.out.println("No file to read.");
		}
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

			File f = new File(path);
			boolean append = f.exists();

			FileWriter writer = new FileWriter(path, append);
			CSVUtils.writeLine(writer, list, append);

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
