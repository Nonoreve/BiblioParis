package fr.nonoreve.biblioParis;

import java.io.File;

public class Reseau {

	public static void main(String[] args) {
		if (args.length > 0) {
			File tempFile = new File(args[0]);

			if (tempFile.exists()) {
				System.out.println("[Main] Reading the file " + args[0] + " ...");

				// We start by reading the CSV file
				FileReader.getDataFromCSVFile(args[0]);

				System.out.println("[Main] End of the file " + args[0] + ".");
			} else {
				System.out.println("[Main] No file " + args[0]);
			}
		} else {
			System.out.println("[Main] You should enter the CSV file path as a parameter.");
		}
	}

}
