package tltools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PRCodePasser {

	public static void main(String[] args) throws IOException {
		File inputDir = new File(args[0]);
		File outputFile  = new File(inputDir.getParentFile(), "out" + System.currentTimeMillis() + ".txt");
		Writer writer = new FileWriter(outputFile);
		writeDirectory(inputDir, writer);

	}

	private static void writeDirectory(File inputDir, Writer writer) throws IOException {
		if (inputDir.isDirectory()){
			for (File inputFile : inputDir.listFiles()){
				if (inputFile.isDirectory()){
					writeDirectory(inputFile, writer);
				}
				else if (!inputFile.isHidden()) {
					if (inputFile.getName().endsWith("java") || inputFile.getName().endsWith("txt") || 
						inputFile.getName().endsWith("md")){
						String fileContents = new String(Files.readAllBytes(Paths.get(inputFile.getAbsolutePath())));
						writer.write(inputFile.getName() + ":\n");
						writer.write(fileContents);
						writer.write("\n");
						writer.flush();
					}
				}
			}
		}
	}

}
