package NLP;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOut {

	public void logNewLineInFile(String lineToWrite) {

		File writeFile = new File("SQL.txt");

		try (FileWriter fileWriter = new FileWriter(writeFile, true);
				BufferedWriter bufferWriter = new BufferedWriter(fileWriter)) {

			bufferWriter.write(lineToWrite);
			bufferWriter.newLine();
			
		} catch (IOException e) {
			System.out.println(e.getMessage() + "Were at a file not found exception in changeGivenLog");
		}
	}
}
