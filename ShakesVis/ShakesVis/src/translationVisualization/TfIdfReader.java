package translationVisualization;

import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.google.api.services.translate.Translate;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.api.services.translate.model.TranslationsResource;

//import edu.stanford.nlp.pipeline.StanfordCoreNLP;

/**
 * 
 * @author Rosa
 * This is a logic class to read the data from .txt file and compute the token frequency and other information
 */
public class TfIdfReader {
	
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
	
	/**a list of String stored all version names*/
	public List<String> m_VersionNameList = new ArrayList<String>();

	/***/
	private Hashtable<Integer, Color> m_frequencyColorIndex=new Hashtable<Integer, Color>();

	/***/
	private List<Map.Entry<Integer, Color>> m_ColorIndex = new ArrayList<Map.Entry<Integer, Color>>();

	/***/
	private Version version;

	/***/
	private JsonReader jsonReader;
  
	/**a list of string lists to store all tokens of all files*/
	private List<Hashtable<String, Integer>> m_tokenLists = new ArrayList<Hashtable<String, Integer>>();

	public List<Hashtable<String, Integer>> getM_tokenLists() {
		return m_tokenLists;
	}


	private List<String> m_OneTokenList;
	
	public void setM_OneTokenList(List<String> m_OneTokenList) {
		this.m_OneTokenList = m_OneTokenList;
	}

	/**
	 * an accessor method used to add and get one list of token
	 * @return
	 */
	public List<String> getM_OneTokenList() {
		return m_OneTokenList;
	}

	public List<Map.Entry<String, Integer>> getM_FrequencyIndex() {
		return m_FrequencyIndex;
	}

	public void setM_FrequencyIndex(List<Map.Entry<String, Integer>> m_FrequencyIndex) {
		this.m_FrequencyIndex = m_FrequencyIndex;
	}

	/**
	 *  @return m_VersionNameList
	 * */
	public List<String> getM_VersionNameList() {
		return m_VersionNameList;
	}

	/**
	 * 
	 * @param filePath-a string array of file paths 
	 * @param versionNumber - an integer of version's number
	 * @param position - an integer telling the method which string in the array you want
	 * @return name -  the name of the version
	 */
	public String filePathProcess(String[] filePath, int versionNumber, int position){
		String[] fileNameSplit=filePath[versionNumber].split("\\\\"); //split the file path
		String name=fileNameSplit[position];
		return name;
	}

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
//			setM_OneTokenList(new ArrayList<String>());
			int lineCount = 0; 
			while ((sCurrentLine = br.readLine()) != null) { //read a line of the text each time
				if (sCurrentLine.trim().isEmpty()) { // get rid of empty lines
				} else if (lineCount == 0) { //get rid of first line
					lineCount++;
				} else { 
					tmpWordsList = sCurrentLine.toLowerCase().replaceAll("\\p{Punct}", "").replaceAll("--", "").split(" ");

					//split the sCurrentLine, make every string to lower case, remove all punctuation
//					
//					 if(filePath=="src\\data\\0000 BaseText Shakespeare.txt"){ //lemmatization for English version
//					 EnglishLemmatizer(tmpWordsList.toString().replaceAll("\\p{Punct}", ""));
//					 for(int i=0; i<lemmas.size(); i++){
//					 addWordFrequency(lemmas.get(i));
//					 
//					 }
//					 }
//					germanLemmatizer(tmpWordsList);
//					for (int i = 0; i < lemmas.size(); i++) {
//						addWordFrequency(lemmas.get(i));
//					}
					
//					System.out.println(tmpWordsList.length);

					/*
					 * pass each word to addWordFrequency() method
					 */
					for (int i = 0; i < tmpWordsList.length; i++) {
						addWordFrequency(tmpWordsList[i]);
					}
					
					
					
				}
			}
//			if(!filePath.equals("src\\data\\0000 BaseText Shakespeare.txt")){
//				getM_tokenLists().add(getM_OneTokenList());
//			}
			
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
		if(!(eachWord.equals(""))){
			if (!getM_UnsortedFrequency().containsKey(eachWord)) { //if the token appears first time
				getM_UnsortedFrequency().put(eachWord, new Integer(1)); // set the frequency as 1
			} else {
				getM_UnsortedFrequency().put(eachWord, getM_UnsortedFrequency().get(eachWord).intValue() + 1);
				//increment frequency number
			}
		}
		
//		System.out.println(eachWord+": "+getM_UnsortedFrequency().get(eachWord).intValue() + 1);

		
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
		return m_ColorIndex;
	}

//	public boolean addConcordanceInfo(int versionNumber)throws Exception{
//		Concordance concordance = new Concordance();
//		concordance.setM_Tokens(new ArrayList<String>());
//		concordance.setM_Frequencies(new ArrayList<Integer>());
//		concordance.setM_Rectangles(new ArrayList<Rectangle>());
//		
//		
//		
//		setVersion(new Version()); //initialize a new version
//		int fileNamePosition = 2; //the author name and the year is the third element in the array
//		String fileName=filePathProcess(getM_FilePath(), versionNumber, fileNamePosition);
//		m_VersionNameList.add(fileName); //create a list of String to store all version names
//		getVersion().setM_VersionName(fileName);
//		getVersion().setM_VersionYear(fileName);
//		getVersion().setM_Author(fileName);
//		getVersion().setM_titlePoint(calculatePoint(versionNumber, 0, 0)); //0 is line number, title has only one line
//		int lineNumber = 1; //used to count line and pass the number to calculate the point location
//		int listSize = 50; //used to fetch top 50 frequent tokens
//		for (Map.Entry<String, Integer> mapping : m_FrequencyIndex) { //read each token of the index
//			if (getVersion().getM_ConcordanceList().size() < listSize) { //get the top 50 frequent tokens in each version
//				getVersion().getM_WordsList().add(mapping.getKey());
//				
//				concordance.setM_Token(mapping.getKey());
//				
//				if(versionNumber==0){
//				concordance.setM_TokenTranslations(new ArrayList<String>());
//				jSonReader(concordance.getM_Token(), concordance);
//				}
//				concordance.setM_Frequency(mapping.getValue());
//				concordance.setM_RectWidth(mapping.getValue(), 1);
//				concordance.setM_StringPoint(calculatePoint(versionNumber, lineNumber, 0));
//				concordance.setM_RectPoint(concordance.getM_StringPoint());
//				concordance.setM_RangeEndPoint(concordance.getM_RectPoint(), concordance.getM_RectHeight(), concordance.getM_RectWidth());
//				addStringIndex(mapping);
//				concordance.setM_TokenColor(calculateColor(m_StringIndex.get(concordance.getM_Token())));
//				concordance.setM_TokenColor(calculateColor(concordance.getM_Frequency()));
//				concordance.setM_RectColor(calculateColor(concordance.getM_Frequency()));
//				getVersion().setM_ConcordanceList(concordance);
//				addfrequencyColorIndex(mapping,concordance.getM_RectColor()); 
//				lineNumber++;
//			}
//		}
////		if(versionNumber==0){
////			googleAPIEng(getVersion());
////		}
//		googleAPIAuth(getVersion());
//		
//		return true;
//	}
	
	/**
	 * Process the data and set the data into the version
	 * @param versionNumber
	 * @return
	 * @throws Exception 
	 */
	public boolean addVersionInfo(int versionNumber) throws Exception{
		setVersion(new Version()); //initialize a new version
		int fileNamePosition = 2; //the author name and the year is the third element in the array
		String fileName=filePathProcess(getM_FilePath(), versionNumber, fileNamePosition);
		m_VersionNameList.add(fileName); //create a list of String to store all version names
		getVersion().setM_VersionName(fileName);
		getVersion().setM_VersionYear(fileName);
		getVersion().setM_Author(fileName);
		getVersion().setM_titlePoint(calculatePoint(versionNumber, 0, 100, 0, 0)); //0 is line number, title has only one line
		
		int lineNumber = 1; //used to count line and pass the number to calculate the point location
		int listSize = 50; //used to fetch top 50 frequent tokens
		for (Map.Entry<String, Integer> mapping : m_FrequencyIndex) { //read each token of the index
			if (getVersion().getM_ConcordanceList().size() < listSize) { //get the top 50 frequent tokens in each version
				getVersion().getM_WordsList().add(mapping.getKey());
				Concordance concordance = new Concordance();
				concordance.setM_Token(mapping.getKey());
				concordance.getM_Tokens().add(mapping.getKey());
//				if(versionNumber==0){
//				concordance.setM_TokenTranslations(new ArrayList<String>());
//				jSonReader(concordance.getM_Token(), concordance);
//				}
				concordance.setM_Frequency(mapping.getValue());
				concordance.getM_Frequencies().add(mapping.getValue());
				concordance.setM_RectWidth(mapping.getValue(),100);
				concordance.setM_RectHeight(100);
				concordance.setM_StringPoint(calculatePoint(versionNumber,lineNumber, 100, concordance.getM_RectWidth(), concordance.getM_RectHeight()));
				concordance.setM_RectPoint(concordance.getM_StringPoint());
//				concordance.setM_RangeEndPoint(concordance.getM_RectPoint(), concordance.getM_RectHeight(), concordance.getM_RectWidth());
				addStringIndex(mapping);
				concordance.setM_TokenColor(calculateColor(m_StringIndex.get(concordance.getM_Token()), 1f));
//				concordance.setM_TokenColor(calculateColor(concordance.getM_Frequency(), 1f));
				concordance.setM_RectColor(calculateColor(concordance.getM_Frequency(), 1f));
				getVersion().setM_ConcordanceList(concordance);
				addfrequencyColorIndex(mapping,concordance.getM_RectColor()); 
				lineNumber++;
			}
		}
//		googleAPIAuth(getVersion());
		
		return true;
	}

	/**
	 * Read all files and process the data
	 * @return
	 * @throws Exception
	 */
	public List<Version> readAllFile() throws Exception {
		
		for (int i = 0; i < getM_FilePath().length; i++) { //get one path of file
			readOneFile(getM_FilePath()[i]); //pass the file path and read the file
			sortFrequencyIndex(getM_UnsortedFrequency()); //sort the frequency as descending order
			if(!(i==0)){
				getM_tokenLists().add(getM_UnsortedFrequency());
			}
//			addVersionInfo(i); //pass i to method as version number and add information for one version
//			m_VersionList.add(getVersion()); //add one version to the version list
		}

		TFIDFCalculator calculator = new TFIDFCalculator();
		addTfidf(calculator.initiate(getM_tokenLists()));
		return m_VersionList;
	}
	
	public void addTfidf(List<Hashtable<String, Integer>> lists) throws Exception{
		for(int i=0; i<lists.size(); i++){
			sortFrequencyIndex(lists.get(i));
			addVersionInfo(i);
			m_VersionList.add(getVersion());
		}
	}

	/**
	 * Calculate the point location
	 * @param versionNumber
	 * @param lineNumber
	 * @return 
	 * @return the point calculated
	 */
	public Point calculatePoint(int versionNumber, int lineNumber, int scaleValue, double rectWidth, double rectHeight) {
		double scaleValueProcess=scaleValue/100.0;
		int spacingX=(int) (150*scaleValueProcess);
		int spacingY=(int) (25*scaleValueProcess);

		int offSetX=75;
		Point stringPoint=new Point();
		stringPoint.x=(int) (versionNumber*spacingX*scaleValueProcess)+offSetX;
		stringPoint.y=(int) ((lineNumber+2)*spacingY*scaleValueProcess);

		return stringPoint;
	}
	
	

	/**
	 * Calculate the color according to the variable numbers
	 * @param stringNumber
	 * @return color variable
	 */
	public Color calculateColor(int frequency, float a) {
		int colorRange = 255;
		int halfRange = 127;
		int redVar = 0;
		int greenVar = 2;
		int blueVar = 4;
		double colorFrequency = 0.22;
		double toDouble = (double) frequency;
		float red = (float) (Math.sin(colorFrequency * toDouble + redVar) * halfRange + halfRange + 1) / colorRange;
		float green = (float) (Math.sin(colorFrequency * toDouble + greenVar) * halfRange + halfRange + 1) / colorRange;
		float blue = (float) (Math.sin(colorFrequency * toDouble + blueVar) * halfRange + halfRange + 1) / colorRange;
		float b=1f;// transparent
		return new Color(red, green, blue, a);
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

//	public void googleAPIAuth(Version version){
//		List<String> tokenTranslation=new ArrayList<String>();
//		tokenTranslation=version.getM_WordsList();
//		try {           
//	        // See comments on 
//	        //   https://developers.google.com/resources/api-libraries/documentation/translate/v2/java/latest/
//	        // on options to set
//	        Translate t = new Translate.Builder(
//	                com.google.api.client.googleapis.javanet.GoogleNetHttpTransport.newTrustedTransport()
//	                , com.google.api.client.json.gson.GsonFactory.getDefaultInstance(), null)                                   
//	                //Need to update this to your App-Name
//	                .setApplicationName("ShakesVis")                    
//	                .build(); 
//	        Translate.Translations.List list = t.new Translations().list(
//	        		version.getM_WordsList(), 
//	                    //Target language
//	        		    //To find the abbreviation of language: https://cloud.google.com/translate/docs/languages
//	                    "en");  
//	        //Set your API-Key from https://console.developers.google.com/
//	        list.setKey("AIzaSyAs48FHTLNCZlmNLzTPPnpCjkgIz6THIFU");
//	        TranslationsListResponse response = list.execute();
//	        int i=0;
//	        for(TranslationsResource tr : response.getTranslations()) {
////	        	tokenTranslation.add(tr.getTranslatedText());
////	            System.out.println(i+": "+tr.getTranslatedText());
//	            version.getM_ConcordanceList().get(i).setM_TokenTranslation(tr.getTranslatedText());
////	            System.out.println(version.getM_ConcordanceList().get(i).getM_Token()+": "+tr.getTranslatedText());
//	            i++;
//	        }
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
//	}
//	
	public void googleAPIEng(Version version){
		List<String> tokenTranslation=new ArrayList<String>();
		tokenTranslation=version.getM_WordsList();
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
	        		version.getM_WordsList(), 
	                    //Target language 
	                    "de");  
	        //Set your API-Key from https://console.developers.google.com/
	        list.setKey("AIzaSyAs48FHTLNCZlmNLzTPPnpCjkgIz6THIFU");
	        TranslationsListResponse response = list.execute();
	        int i=0;
	        for(TranslationsResource tr : response.getTranslations()) {
//	        	tokenTranslation.add(tr.getTranslatedText());
//	            System.out.println(i+": "+tr.getTranslatedText());
	            version.getM_ConcordanceList().get(i).setM_TokenTranslation(tr.getTranslatedText());
//	            System.out.println(version.getM_ConcordanceList().get(i).getM_Token()+": "+tr.getTranslatedText());
	            i++;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	

	public boolean jSonReader(String token, Concordance concordance) throws Exception{
			jsonReader = Json.createReader(new FileReader("src\\data\\English-German.json"));

		JsonObject object = jsonReader.readObject();
		JsonArray jsonArray = object.getJsonArray(token);
		if(jsonArray!=null){
		for(int i=0; i<jsonArray.size(); i++){
			int length=jsonArray.get(i).toString().length()-1;
			String string=jsonArray.get(i).toString().substring(1, length);
			concordance.getM_TokenTranslations().add(string);
		}
		}
		
		return true;
	}
	
	
	/**
	 * Lemmatize English tokens.
	 * @param documentText
	 * @return TRUE on success.
	 */
//	public boolean EnglishLemmatizer(String documentText) {
//		Properties props = new Properties();
//		String str;
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
//				str=token.get(LemmaAnnotation.class);
//				
//			}
//		}
//		
//		return true;
//	}
	
	
}
