package translationVisualization;

import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.api.services.translate.Translate;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.api.services.translate.model.TranslationsResource;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

/**
 * 
 * @author Rosa
 * This is a logic class to read the data from .txt file and compute the token frequency and other information
 */
public class DataReader {
	
	/**an array list of word and frequency index after sorting as ascending order*/
	private List<Map.Entry<String, Integer>> m_FrequencyIndex = new ArrayList<Map.Entry<String, Integer>>();
	
	/**an string array to store file paths for basic text and translation versions*/
	private final String[] m_FilePath = { "src\\data\\0000 BaseText Shakespeare.txt", "src\\data\\1832 Baudissin ed Wenig.txt", "src\\data\\1920 Gundolf.txt", "src\\data\\1941 Schwarz.txt",
			"src\\data\\1947 Baudissin ed Brunner.txt",	"src\\data\\1952 Flatter.txt", "src\\data\\1962 Schroeder.txt",
			"src\\data\\1963 Rothe.txt", "src\\data\\1970 Fried.txt", "src\\data\\1973 Lauterbach.txt",
			"src\\data\\1976 Engler.txt", "src\\data\\1978 Laube.txt", "src\\data\\1985 Bolte Hamblock.txt",
			"src\\data\\1992 Motschach.txt", "src\\data\\1995 Guenther.txt", "src\\data\\2003 Zaimoglu.txt" };
	
	

	/**a hashtable to store tokens and frequency but without sorting*/
	private Hashtable<String, Integer> m_UnsortedFrequency = new Hashtable<String, Integer>();
	
	/***/
	public Hashtable<String, Integer> m_StringIndex = new Hashtable<String, Integer>();
	
	/***/
	public List<Version> m_VersionList = new ArrayList<Version>();

	/***/
	protected StanfordCoreNLP pipeline;

	/***/
	private List<String> lemmas; 

	/***/
	private Hashtable<Integer, Color> m_frequencyColorIndex=new Hashtable<Integer, Color>();

	/***/
	private List<Map.Entry<Integer, Color>> m_ColorIndex = new ArrayList<Map.Entry<Integer, Color>>();

	/***/
	private Version version;

	
	public Version getVersion() {
		return version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	public Hashtable<Integer, Color> getM_frequencyColorIndex() {
		return m_frequencyColorIndex;
	}
	
	/**
	 * @return m_UnsortedFrequency
	 */
	public Hashtable<String, Integer> getM_UnsortedFrequency() {
		return m_UnsortedFrequency;
	}

	/**
	 * an accessor method to set the unsorted frequency hashtable
	 * @param m_UnsortedFrequency
	 */
	public void setM_UnsortedFrequency(Hashtable<String, Integer> m_UnsortedFrequency) {
		this.m_UnsortedFrequency = m_UnsortedFrequency;
	}

	/**
	 * @return file path string array
	 */
	public String[] getM_FilePath() {
		return m_FilePath;
	}
	
	
	public void googleAPIAuth(List<String> stringList){
		try {           
            // See comments on 
            //   https://developers.google.com/resources/api-libraries/documentation/translate/v2/java/latest/
            // on options to set
            Translate t = new Translate.Builder(
                    com.google.api.client.googleapis.javanet.GoogleNetHttpTransport.newTrustedTransport()
                    , com.google.api.client.json.gson.GsonFactory.getDefaultInstance(), null)                                   
                    //Need to update this to your App-Name
                    .setApplicationName("ShakesVis")                    
                    .build(); 
            Translate.Translations.List list = t.new Translations().list(
            		stringList, 
                        //Target language
                        "en");  
            //Set your API-Key from https://console.developers.google.com/
            list.setKey("AIzaSyAs48FHTLNCZlmNLzTPPnpCjkgIz6THIFU");
            TranslationsListResponse response = list.execute();
            for(TranslationsResource tr : response.getTranslations()) {
                System.out.println(tr.getTranslatedText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	
	/**
	 * pass the file path and read one file 
	 * @param filePath
	 * @return TRUE on success
	 * @throws Exception
	 */
	public boolean readOneFile(String filePath) throws Exception {
		
		
		/*
		 * Read the text line by line and pass each token to other methods
		 */
		try { 
			setM_UnsortedFrequency(new Hashtable<String, Integer>()); //initiate the frequency hashtable
			BufferedReader br = new BufferedReader(new FileReader(filePath)); //create a BufferedReader and read file
			String sCurrentLine; // used to store string one line as a string 
			// List<String> tmpWordsList=new ArrayList<String>();

			String[] tmpWordsList; // a string array used to store strings after sCurrentLine beings splited
			int lineCount = 0; 
			while ((sCurrentLine = br.readLine()) != null) { //read a line of the text each time
				if (sCurrentLine.trim().isEmpty()) { // get rid of empty lines
				} else if (lineCount == 0) { //get rid of first line
					lineCount++;
				} else { 
				
					tmpWordsList = sCurrentLine.toLowerCase().replaceAll("\\p{Punct}", "").replaceAll("--", "").split(" ");
					//split the sCurrentLine, make every string to lower case, remove all punctuation
					
//					 if(filePath=="src\\data\\0000 BaseText Shakespeare.txt"){ //lemmatization for English version
//					 lemmatizer(tmpWordsList.toString().replaceAll("\\p{Punct}",
//					 ""));
//					 for(int i=0; i<lemmas.size(); i++){
//					 addWordFrequency(lemmas.get(i));
//					 }
//					 }
//					germanLemmatizer(tmpWordsList);
//					for (int i = 0; i < lemmas.size(); i++) {
//						addWordFrequency(lemmas.get(i));
//					}
//					
//					System.out.println(tmpWordsList.length);

					/*
					 * pass each word to addWordFrequency() method
					 */
					for (int i = 0; i < tmpWordsList.length; i++) {
						addWordFrequency(tmpWordsList[i]);
					}
				}
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

	/**
	 * Compute the frequency for each word.
	 * @param eachWord
	 * @return TRUE on success.
	 */
	public boolean addWordFrequency(String eachWord) {
		if (!getM_UnsortedFrequency().containsKey(eachWord)) { //if the token appears first time
			getM_UnsortedFrequency().put(eachWord, new Integer(1)); // set the frequency as 1
		} else {
			getM_UnsortedFrequency().put(eachWord, getM_UnsortedFrequency().get(eachWord).intValue() + 1);
			//increment frequency number
		}
		return true;
	}

	/**
	 * Sort the frequency in m_fequencyIndex as descending order.
	 * @param frequencyUnsorted
	 * @return TRUE on success.
	 */
	public boolean sortFrequencyIndex(Hashtable<String, Integer> frequencyUnsorted) {
		m_FrequencyIndex = new ArrayList<Map.Entry<String, Integer>>(frequencyUnsorted.entrySet());
		//map the hashtable of unsorted frequency and save them into arraylist
		Collections.sort(m_FrequencyIndex, new Comparator<Map.Entry<String, Integer>>() {// sort the frequency
			// descending order
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		return true;
	}
	
	/**
	 * Sort the Color index as descending order
	 * @param frequencyColorIndex
	 * @return
	 */
	public List<Map.Entry<Integer, Color>> sortColorIndex(Hashtable<Integer, Color> frequencyColorIndex){
		m_ColorIndex = new ArrayList<Map.Entry<Integer, Color>>(frequencyColorIndex.entrySet());
		Collections.sort(m_ColorIndex, new Comparator<Map.Entry<Integer, Color>>() {
			// descending order
			public int compare(Entry<Integer, Color> o1, Entry<Integer, Color> o2) {
				return o2.getKey().compareTo(o1.getKey());
			}
		});
		System.out.println(m_ColorIndex);
		return m_ColorIndex;
	}

	/**
	 * Process the data and set the data into the version
	 * @param versionNumber
	 * @return
	 */
	public boolean addVersionInfo(int versionNumber){
		setVersion(new Version()); //initialize a new version
		int fileNamePosition = 2; //the file name is the third element in the array
		String[] fileNameSplit=getM_FilePath()[versionNumber].split("\\\\"); //split the file path
		String versionName=fileNameSplit[fileNamePosition]; //fetch the version name
		getVersion().setM_VersionYear(versionName);
		getVersion().setM_Author(versionName);
		getVersion().setM_titlePoint(calculatePoint(versionNumber, 0)); //0 is line number, title has only one line
		int lineNumber = 1; //used to count line and pass the number to calculate the point location
		int listSize = 50; //used to fetch top 50 frequent tokens
		for (Map.Entry<String, Integer> mapping : m_FrequencyIndex) { //read each token of the index
			if (getVersion().getM_ConcordanceList().size() < listSize) { //get the top 50 frequent tokens in each version
				getVersion().getM_WordsList().add(mapping.getKey());
				Concordance concordance = new Concordance();
				concordance.setM_Token(mapping.getKey());
				concordance.setM_Frequency(mapping.getValue());
				concordance.setM_RectWidth(mapping.getValue());
				concordance.setM_StringPoint(calculatePoint(versionNumber, lineNumber));
				concordance.setM_RectPoint(concordance.getM_StringPoint());
				addStringIndex(mapping);
//				concordance.setM_TokenColor(calculateColor(m_StringIndex.get(concordance.getM_Token())));
				concordance.setM_TokenColor(calculateColor(concordance.getM_Frequency()));
				concordance.setM_RectColor(calculateColor(concordance.getM_Frequency()));
				getVersion().setM_ConcordanceList(concordance);
				addfrequencyColorIndex(mapping,concordance.getM_RectColor()); 
				lineNumber++;
			}
		}
		return true;
	}

	/**
	 * Read all files and process the data
	 * @return
	 * @throws Exception
	 */
	public List<Version> readAllFile() throws Exception {
		/*
		 * 
		 */
		for (int i = 0; i < getM_FilePath().length; i++) { //get one path of file
//			Version version = new Version(); 
			readOneFile(getM_FilePath()[i]); //pass the file path and read the file
			sortFrequencyIndex(getM_UnsortedFrequency()); //sort the frequency as descending order
			addVersionInfo(i); //pass i to method as version number and add information for one version
			m_VersionList.add(getVersion()); //add one version to the version list
		}
		return m_VersionList;
	}

	/**
	 * Calculate the point location
	 * @param versionNumber
	 * @param lineNumber
	 * @return the point calculated
	 */
	public Point calculatePoint(int versionNumber, int lineNumber) {
		int x = 55;
		int y = 30;
		int columnSpace = 150;
		int lineSpace = 17;
		x = x + columnSpace * versionNumber;
		y = y + lineSpace * lineNumber;
		return new Point(x, y);
	}

	/**
	 * Calculate the color according to the variable numbers
	 * @param stringNumber
	 * @return color variable
	 */
	public Color calculateColor(int stringNumber) {
		int colorRange = 255;
		int halfRange = 127;
		int redVar = 0;
		int greenVar = 2;
		int blueVar = 4;
		double colorFrequency = 0.3;
		double toDouble = (double) stringNumber;
		float red = (float) (Math.sin(colorFrequency * toDouble + redVar) * halfRange + halfRange + 1) / colorRange;
		float green = (float) (Math.sin(colorFrequency * toDouble + greenVar) * halfRange + halfRange + 1) / colorRange;
		float blue = (float) (Math.sin(colorFrequency * toDouble + blueVar) * halfRange + halfRange + 1) / colorRange;
		return new Color(red, green, blue);
	}

	/**
	 * Compute how many strings occurred in all versions
	 * used to compute the color for each token
	 * @param mapping
	 */
	public void addStringIndex(Map.Entry<String, Integer> mapping) {
		if (!m_StringIndex.containsKey(mapping.getKey())) {
			m_StringIndex.put(mapping.getKey(), m_StringIndex.size());

		}
	}
	
	/**
	 * Compute the color index
	 * @param mapping
	 * @param color
	 */
	public void addfrequencyColorIndex(Map.Entry<String, Integer> mapping, Color color){
		if(!m_frequencyColorIndex.containsKey(mapping.getValue())){
			m_frequencyColorIndex.put(mapping.getValue(), color);
		}
	}

//	/**
//	 * Lemmatize the German tokens.
//	 * @param documentText
//	 * @return TRUE on success.
//	 * @throws Exception
//	 */
//	public boolean germanLemmatizer(String[] documentText) throws Exception {
//		lemmas = new ArrayList<String>();
//		System.setProperty("treetagger.home", "src\\org\\annolab\\tt4j\\TreeTagger");
//		TreeTaggerWrapper<String> tt = new TreeTaggerWrapper<String>();
//		try {
//			tt.setModel("src\\org\\annolab\\tt4j\\german.par");
//			tt.setHandler(new TokenHandler<String>() {
//				public void token(String token, String pos, String lemma) {
//					lemmas.add(lemma);
//					// System.out.println(token + "\t" + pos + "\t" + lemma);
//				}
//			});
//			tt.process(documentText);
//
//		} finally {
//			tt.destroy();
//		}
//		return true;
//	}

//	/**
//	 * Lemmatize English tokens.
//	 * @param documentText
//	 * @return TRUE on success.
//	 */
//	public boolean EnglishLemmatizer(String documentText) {
//		Properties props = new Properties();
//		props.put("annotators", "tokenize, ssplit, pos, lemma");
//
//		this.pipeline = new StanfordCoreNLP(props);
//
//		lemmas = new ArrayList<String>();
//		Annotation document = new Annotation(documentText);
//		this.pipeline.annotate(document);
//		List<CoreMap> sentences = document.get(SentencesAnnotation.class);
//		for (CoreMap sentence : sentences) {
//			for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
//				lemmas.add(token.get(LemmaAnnotation.class));
//			}
//		}
//		return true;
//	}
	
	
}
