package NLP;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class SqlGenerator {
	private String mainLink = "";
	private int responseId = 0;
	NumberFormat formatter = new DecimalFormat("#0.000000");
	FileOut fileOut = new FileOut();
	public void generateSql(int category, String type, String name, String mainLink, String transcriptLink, String slidesLink, String term, double tfidf) {
		
		
		if (!this.mainLink.equals(mainLink)) {
			this.responseId++;
			this.mainLink = mainLink;
			String sqlResponse = "INSERT INTO response (response_id, category_id, type, name, main_link, transcript_link, slides_link) VALUES"+
			"("+responseId+","+category+" , '"+type+"', '"+name+"', '"+mainLink+"', '"+transcriptLink+"', '"+slidesLink+"');";
			fileOut.logNewLineInFile(sqlResponse);
		}
		if (!(Double.parseDouble(formatter.format(tfidf)) == 0.000000)) {
			String sqlTerm = "INSERT INTO term (term_id, term, tfidf, response_id) VALUES (DEFAULT, '"+term+"', "+formatter.format(tfidf)+", "+responseId+");";
			fileOut.logNewLineInFile(sqlTerm);
		}
	}

	public void processDocument() throws FileNotFoundException {

		FileIn readfile = new FileIn();

		readfile.fileReader();
		List<List<String>> corpus = readfile.getCorpus();
		List<Document> documents = readfile.getDocuments();
		TFIDF tfidf = new TFIDF();

		for (Document document : documents) {
			for (String term : document.getGrams()) {
				generateSql(document.getCategory(), document.getType(), document.getName(), document.getMainLink(), document.getTranscipt(), document.getSlides(), term, tfidf.tfIdf(document.getGrams(), corpus, term));
			}
		}
	}

}
