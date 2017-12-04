package translationVisualization;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class LemmaProcess {
	/**
	 * pass the file path and read one file
	 * 
	 * @param filePath
	 * @return TRUE on success
	 * @throws Exception
	 */
	public boolean readOneFile(String filePath) throws Exception {
		/*
		 * Read the text line by line and pass each token to other methods
		 */
		try {
			// setM_UnsortedFrequency(new Hashtable<String, Integer>());
			// //initiate the frequency hashtable
			BufferedReader br = new BufferedReader(new FileReader(filePath)); // create
																				// a
																				// BufferedReader
																				// and
																				// read
																				// file
			String sCurrentLine; // used to store string one line as a string
			// List<String> tmpWordsList=new ArrayList<String>();

			String[] tmpWordsList; // a string array used to store strings after
									// sCurrentLine beings splited
			// setM_OneTokenList(new ArrayList<String>());
			int lineCount = 0;
			while ((sCurrentLine = br.readLine()) != null) { // read a line of
																// the text each
																// time
				if (sCurrentLine.trim().isEmpty()) { // get rid of empty lines
				} else if (lineCount == 0) { // get rid of first line
					lineCount++;
				} else {
					tmpWordsList = sCurrentLine.toLowerCase().replaceAll("\\p{Punct}", "").replaceAll("--", "")
							.split(" ");
					/*
					 * pass each word to addWordFrequency() method
					 */
					for (int i = 0; i < tmpWordsList.length; i++) {
						jSonReader(tmpWordsList[i]);
					}
				}
				System.out.println(lineCount);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public boolean jSonReader(String token) throws Exception {

		jsonReader = Json.createReader(new FileReader("src\\data\\GermanLemma.json"));
		JsonObject object = jsonReader.readObject();
		 System.out.println(object.toString());
		JsonArray jsonArray = object.getJsonArray(token);
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.size(); i++) {
				int length = jsonArray.get(i).toString().length() - 1;
				String string = jsonArray.get(i).toString().substring(1, length);
				System.out.println(string);
				// concordance.getM_TokenTranslations().add(string);
			}
		}

		return true;
	}

	public boolean readCorpus(String filePath) throws Exception {
		File newTextFile = new File("D:/thetextfile.txt");
		FileWriter fw = new FileWriter(newTextFile);
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String sCurrentLine;
		String[] tmpWordsList;
		int lineCount = 0;
		fw.write("{" + "\r");
		while ((sCurrentLine = br.readLine()) != null) {
			tmpWordsList = sCurrentLine.replace("\"", "").replaceAll("\\p{Punct}", "").split("	");
			fw.write("	" + "\"" + tmpWordsList[0] + "\"" + ": [" + "\r");
			// System.out.println(lineCount);
			fw.write("		" + "\"" + tmpWordsList[1] + "\"" + "\r");
			fw.write("	" + "]," + "\r");
			lineCount++;
		}

		fw.write("}");
		fw.close();
		System.out.println(lineCount);
		return true;
	}

	public static void main(String[] args) throws Exception {
		LemmaProcess lp = new LemmaProcess();
		// lp.readCorpus("src\\data\\DeReKo-2014-II-MainArchive-STT.100000.freq");
		lp.readOneFile(m_FilePath[1]);

	}

	private Hashtable<String, String> m_GermanCorpus = new Hashtable<String, String>();
	private JsonReader jsonReader;
	private JsonObject object;

	public JsonObject getObject() {
		return object;
	}

	public void setObject(JsonObject object) {
		this.object = object;
	}

	private final static String[] m_FilePath = { "src\\data\\0000 BaseText Shakespeare.txt",
			"src\\data\\1832 Baudissin ed Wenig.txt", "src\\data\\1920 Gundolf.txt", "src\\data\\1941 Schwarz.txt",
			"src\\data\\1947 Baudissin ed Brunner.txt", "src\\data\\1952 Flatter.txt", "src\\data\\1962 Schroeder.txt",
			"src\\data\\1963 Rothe.txt", "src\\data\\1970 Fried.txt", "src\\data\\1973 Lauterbach.txt",
			"src\\data\\1976 Engler.txt", "src\\data\\1978 Laube.txt", "src\\data\\1985 Bolte Hamblock.txt",
			"src\\data\\1992 Motschach.txt", "src\\data\\1995 Guenther.txt", "src\\data\\2003 Zaimoglu.txt" };

}
