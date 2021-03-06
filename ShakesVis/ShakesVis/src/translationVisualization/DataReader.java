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

/**
 * 
 * @author Rosa
 * This is a logic class to read the data from .txt file and compute the token frequency and other information
 */
public class DataReader {
	/**
	 * an accessor method used to get m_tokenlists
	 * @return m_tokenlists
	 */
	public List<Hashtable<String, Integer>> getM_tokenLists() {
		return m_tokenLists;
	}

	/**
	 * an accessor method used to access 
	 * @param m_OneTokenList
	 */
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
	 * 
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
	
	public void setInitialFilePath(){
		String[] FilePath={ "src\\data\\1604 BaseText Shakespeare.txt", "src\\data\\1832 Baudissin ed Wenig.txt", "src\\data\\1920 Gundolf.txt", "src\\data\\1941 Schwarz.txt",
				"src\\data\\1947 Baudissin ed Brunner.txt",	"src\\data\\1952 Flatter.txt", "src\\data\\1962 Schroeder.txt",
				"src\\data\\1963 Rothe.txt", "src\\data\\1970 Fried.txt", "src\\data\\1973 Lauterbach.txt",
				"src\\data\\1976 Engler.txt", "src\\data\\1978 Laube.txt", "src\\data\\1985 Bolte Hamblock.txt",
				"src\\data\\1992 Motschach.txt", "src\\data\\1995 Guenther.txt", "src\\data\\2003 Zaimoglu.txt" };

		m_FilePath=FilePath;
	}
	
	public void setGermanLemmaFilePath(){
		String[] FilePath={ "src\\data\\1604 BaseText Shakespeare.txt", "src\\data\\1832 Baudissin ed Wenig-Lemma.txt", "src\\data\\1920 Gundolf-Lemma.txt", "src\\data\\1941 Schwarz-Lemma.txt",
				"src\\data\\1947 Baudissin ed Brunner-Lemma.txt",	"src\\data\\1952 Flatter-Lemma.txt", "src\\data\\1962 Schroeder-Lemma.txt",
				"src\\data\\1963 Rothe-Lemma.txt", "src\\data\\1970 Fried-Lemma.txt", "src\\data\\1973 Lauterbach-Lemma.txt",
				"src\\data\\1976 Engler-Lemma.txt", "src\\data\\1978 Laube-Lemma.txt", "src\\data\\1985 Bolte Hamblock-Lemma.txt",
				"src\\data\\1992 Motschach-Lemma.txt", "src\\data\\1995 Guenther-Lemma.txt", "src\\data\\2003 Zaimoglu-Lemma.txt" };
 
		m_FilePath=FilePath;
		 
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
//					tmpWordsList = sCurrentLine.replaceAll("\\p{Punct}", "").replaceAll("--", "").split(" ");

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
		if(!(eachWord.equals(""))){
			if (!getM_UnsortedFrequency().containsKey(eachWord)) { //if the token appears first time
				getM_UnsortedFrequency().put(eachWord, new Integer(1)); // set the frequency as 1
			} else {
				getM_UnsortedFrequency().put(eachWord, getM_UnsortedFrequency().get(eachWord).intValue() + 1);
				//increment frequency number
			}
		}
		return true;
	}

	/**
	 * Sort the frequency in m_fequencyIndex as descending order.
	 * @param frequencyUnsorted
	 * @return TRUE on success.
	 */
	public boolean sortFrequencyIndex(Hashtable<String, Integer> frequencyUnsorted) {
		setM_FrequencyIndex(new ArrayList<Map.Entry<String, Integer>>(frequencyUnsorted.entrySet()));
//		m_FrequencyIndex = new ArrayList<Map.Entry<String, Integer>>(frequencyUnsorted.entrySet());
		//map the hashtable of unsorted frequency and save them into arraylist
		Collections.sort(getM_FrequencyIndex(), new Comparator<Map.Entry<String, Integer>>() {// sort the frequency
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
		int titleLine=0;
		getVersion().setM_titlePoint(calculatePoint(versionNumber, titleLine, 100)); //0 is line number, title has only one line
		int lineNumber = 1; //used to count line and pass the number to calculate the point location
//		int listSize = 50; //used to fetch top 50 frequent tokens
		for (Map.Entry<String, Integer> mapping : getM_FrequencyIndex()) { //read each token of the index
//			if (getVersion().getM_ConcordanceList().size() < listSize) { //get the top 50 frequent tokens in each version
				getVersion().getM_WordsList().add(mapping.getKey());
				Item item = new Item();
				item.setM_Token(mapping.getKey());
				item.getM_Tokens().add(mapping.getKey());
				if(versionNumber==0){
				item.setM_TokenTranslations(new ArrayList<String>());
				jSonReader(item.getM_Token(), item);
				}
				item.setM_Frequency(mapping.getValue());
				item.getM_Frequencies().add(mapping.getValue());
				item.setM_RectWidth(mapping.getValue(),100);
				item.setM_RectHeight(100);
				int lineIncrement=2; //to low down the line in case the first line cover title
				item.setM_StringPoint(calculatePoint(versionNumber,lineNumber+lineIncrement, 100));
				item.setM_RectPoint(item.getM_StringPoint());
				addStringIndex(mapping);
				item.setM_TokenColor(calculateColor(m_StringIndex.get(item.getM_Token()), 1f));
				item.setM_TokenColor(calculateColor(item.getM_Frequency(), 1f));
				item.setM_RectColor(calculateColor(item.getM_Frequency(), 1f));
				getVersion().setM_ConcordanceList(item);
				addfrequencyColorIndex(mapping,item.getM_RectColor()); 
				lineNumber++;
//			}
		}
//		if(versionNumber==0){
//			googleAPIEng(getVersion());
//		}
//		googleAPIAuth(getVersion());
		
		return true;
	}

	private boolean frequentValue=true;
	
	public boolean isFrequentValue() {
		return frequentValue;
	}

	public void setFrequentValue(boolean frequentValue) {
		this.frequentValue = frequentValue;
	}
	
	public List<Version> initiateDataReader() throws Exception{
		if(isFrequentValue()){
			readAllFile();
		}else{
			initiateTfIdf();
		}
		
		return getM_VersionList();
	}

	/**
	 * Read all files and process the data
	 * @return
	 * @throws Exception
	 */
	public void readAllFile() throws Exception {
		setM_VersionList(new ArrayList<Version>());
		for (int i = 0; i < getM_FilePath().length; i++) { //get one path of file
			readOneFile(getM_FilePath()[i]); //pass the file path and read the file
			sortFrequencyIndex(getM_UnsortedFrequency()); //sort the frequency as descending order
			addVersionInfo(i); //pass i to method as version number and add information for one version
			getM_VersionList().add(getVersion()); //add one version to the version list
		}
	}
	
	public void addTfidf(List<Hashtable<String, Integer>> lists) throws Exception{
		for(int i=0; i<lists.size(); i++){
			sortFrequencyIndex(lists.get(i));
			addVersionInfo(i+1);
			getM_VersionList().add(getVersion());
		}
	}
	public void initiateTfIdf() throws Exception{
		setM_VersionList(new ArrayList<Version>());
		for (int i = 0; i < getM_FilePath().length; i++) { //get one path of file
			readOneFile(getM_FilePath()[i]); //pass the file path and read the file
			sortFrequencyIndex(getM_UnsortedFrequency()); //sort the frequency as descending order
			if(i==0){
				addVersionInfo(i);
				getM_VersionList().add(getVersion());
				
			}else{
				getM_tokenLists().add(getM_UnsortedFrequency());
			}
		}
			TFIDFCalculator calculator = new TFIDFCalculator();
			addTfidf(calculator.initiate(getM_tokenLists()));
//		return getM_VersionList();
	}
	


	/**
	 * Calculate the point location
	 * @param versionNumber
	 * @param lineNumber
	 * @return 
	 * @return the point calculated
	 */
	public Point calculatePoint(int versionNumber, int lineNumber, int scaleValue) {
		double scaleValueProcess=scaleValue/100.0;
		int versionDistance=150;
		int versionDistanceScale=(int) (versionDistance*scaleValueProcess);
		int lineDistance=25;
		int lineDistanceScale=(int) (lineDistance*scaleValueProcess);
		Point point=new Point();
		// the versionNumber start from 0 in the array, so we add 1 for each version
		point.x=(int) ((versionNumber+1)*versionDistanceScale*scaleValueProcess);
		point.y=(int) ((lineNumber+2)*lineDistanceScale*scaleValueProcess);

		return point;
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
		float red = (float) (Math.sin(colorFrequency * toDouble + redVar) * halfRange + (halfRange + 1)) / colorRange;
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

	public boolean jSonReader(String token, Item item) throws Exception{
			jsonReader = Json.createReader(new FileReader("src\\data\\English-German.json"));
	
		JsonObject object = jsonReader.readObject();
		JsonArray jsonArray = object.getJsonArray(token);
		if(jsonArray!=null){
		for(int i=0; i<jsonArray.size(); i++){
			int length=jsonArray.get(i).toString().length()-1;
			String string=jsonArray.get(i).toString().substring(1, length);
			item.getM_TokenTranslations().add(string);
		}
		}
		
		return true;
	}


	/**an array list of word and frequency index after sorting as ascending order*/
	private List<Map.Entry<String, Integer>> m_FrequencyIndex = new ArrayList<Map.Entry<String, Integer>>();

	/**an string array to store file paths for basic text and translation versions*/
	private String[] m_FilePath = { "src\\data\\1604 BaseText Shakespeare.txt", "src\\data\\1832 Baudissin ed Wenig.txt", "src\\data\\1920 Gundolf.txt", "src\\data\\1941 Schwarz.txt",
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

	public List<Version> getM_VersionList() {
		return m_VersionList;
	}

	public void setM_VersionList(List<Version> m_VersionList) {
		this.m_VersionList = m_VersionList;
	}

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

	private List<String> m_OneTokenList;
	
	
}