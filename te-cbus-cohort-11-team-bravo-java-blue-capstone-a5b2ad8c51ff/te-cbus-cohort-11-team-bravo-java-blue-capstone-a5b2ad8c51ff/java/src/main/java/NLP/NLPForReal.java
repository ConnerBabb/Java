package NLP;

import java.io.IOException;

public class NLPForReal {
	
	public static void main(String[] args) throws IOException {
		System.out.println("Starting Stanford Lemmatizer");
		SqlGenerator sqlGenerator = new SqlGenerator();
		sqlGenerator.processDocument();
	}
}
