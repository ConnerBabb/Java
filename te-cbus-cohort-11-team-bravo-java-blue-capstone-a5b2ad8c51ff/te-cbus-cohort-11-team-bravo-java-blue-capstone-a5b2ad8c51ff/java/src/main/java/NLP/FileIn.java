package NLP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class FileIn {

	private List<List<String>> corpus = new ArrayList<List<String>>();
	private List<Document> documents = new ArrayList<Document>();

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public List<List<String>> getCorpus() {
		return corpus;
	}

	public void setCorpus(List<List<String>> corpus) {
		this.corpus = corpus;
	}

	private static File folder = new File("/Users/cbabb/Desktop/NLP");

	public void fileReader() throws FileNotFoundException {

		Lemmatize lemmatize = new Lemmatize();
		lemmatize.setUpLemmatizer();

		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {

			if (file.isFile()) {
				Document doc = new Document();
				Scanner fileScanner = new Scanner(file);
				List<String> lineList = new ArrayList<String>();
				int lineCount = 1;
				String type = "";
				while (fileScanner.hasNextLine()) {
					String line = fileScanner.nextLine();
					if (lineCount == 1) {
						doc.setType(line);
						type = line;
						lineCount++;
					} else if (lineCount == 2) {
						doc.setMainLink(line);
						lineCount++;
					} else if (lineCount == 3 && doc.getType().equals("Zoom")) {
						doc.setTranscipt(line);
						lineCount++;
					} else if (lineCount == 4 && doc.getType().equals("Zoom")) {
						doc.setSlides(line);
						lineCount++;
					} else {
						lineList.add(line);
					}
				}
				String docWordString = lemmatize.stringify(lineList);
				lineList = lemmatize.lemmatize(docWordString);
				lineList = lemmatize.gramify(lineList);
				lineList = lemmatize.removeSpecialCharacters(lineList);
				corpus.add(lineList);

				Set<String> set = new LinkedHashSet<>();
				set.addAll(lineList);
				lineList.clear();
				lineList.addAll(set);
				String name = createName(type, file.getName());
				doc.setName(name);
				doc.setCategory(category(type));
				doc.setGrams(lineList);
				documents.add(doc);

				fileScanner.close();
			}
		}

	}

	private String nameCleaner(String name) {
		name = name.replace("_", " ").replace(".txt", "");
		String firstThree = name.substring(0, 3);
		name = name.substring(3);
		
		firstThree = firstThree.replaceAll("[0-9]", "");
		name = firstThree + name;
		return name;
	}

	private String appendLinkType(String type, String name) {

		if (type.equals("Zoom")) {
			return "Zoom Link - " + name;
		} else if (type.equals("Book")) {
			return "Student Handbook-" + name;
		} else if (type.equals("Pathway")) {
			return "Pathway resource-" + name;
		}else {
		return name;
		}
	}

	private String createName(String type, String name) {
		name = appendLinkType(type, nameCleaner(name));
		return name;
	}
	private int category(String category) {
		if (category.equals("Zoom")) {
			return 2;
		} else if (category.equals("Book")) {
			return 2;
		} else if (category.equals("Pathway")) {
			return 1;
		}else {
		return 0;
		}
	}
}
