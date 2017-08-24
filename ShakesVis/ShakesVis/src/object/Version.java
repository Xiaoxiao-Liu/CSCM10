package object;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import gui.WordFreqProcess;

public class Version {
	private DataReader m_dataReader =new DataReader();
	private String m_versionName;
	private int m_versionYear;
	private String m_author;
	private final String[] fileArray={"src\\data\\BaseText Shakespeare.txt","src\\data\\1832 Baudissin ed Wenig.txt","src\\data\\1920 Gundolf.txt","src\\data\\1941 Schwarz.txt","src\\data\\1947 Baudissin ed Brunner.txt","src\\data\\1952 Flatter.txt","src\\data\\1962 Schroeder.txt","src\\data\\1963 Rothe.txt","src\\data\\1970 Fried.txt","src\\data\\1973 Lauterbach.txt","src\\data\\1976 Engler.txt","src\\data\\1978 Laube.txt","src\\data\\1985 Bolte Hamblock.txt","src\\data\\1992 Motschach.txt","src\\data\\1995 Guenther.txt","src\\data\\2003 Zaimoglu.txt"};
	private List<Map.Entry<String, Integer>> m_frequencyIndex=new ArrayList<Map.Entry<String, Integer>>();
	private Hashtable<String, Integer> m_stringIndex=new Hashtable<String, Integer>();
	private List<TopWord> m_topWordList=new ArrayList<TopWord>();
	
	
//	public List<List<Map.Entry<String, Integer>>> getM_frequencyIndex() {
//		return m_frequencyIndex;
//	}

	public Hashtable<String, Integer> getM_stringIndex() {
		return m_stringIndex;
	}

	

	public void setM_frequencyIndex(int versionNumber) {
			m_dataReader.setFilePath(fileArray[versionNumber]);
			m_dataReader.setM_wordFrequency(new Hashtable<String, Integer>());
			m_dataReader.readFile(fileArray[versionNumber]);
			m_dataReader.sortHash(m_dataReader.getM_wordFrequency());
			m_frequencyIndex=m_dataReader.getM_frequencyIndex();
			if(versionNumber>0){
				m_dataReader.setStringIndex(m_dataReader.getM_frequencyIndex(), m_stringIndex);
			}
			else{
			}
	}
	
	public void setM_TopWordList(int versionNumber) {
			int lineNumber=0;
			for(Map.Entry<String, Integer> mapping : m_frequencyIndex){
				TopWord topWord=new TopWord();
				topWord.setM_word(mapping.getKey());
				topWord.setM_frequency(mapping.getValue());
				topWord.setM_point(versionNumber, lineNumber);
				if(m_stringIndex.containsKey(mapping.getKey())){
					topWord.setM_color(m_stringIndex.get(mapping.getKey()));
				}
					
				m_topWordList.add(topWord);
				lineNumber++;
			}
	}
	
	public List<TopWord> getM_topWordList() {
		return m_topWordList;
	}

	public String getVersionName() {
		return m_versionName;
	}
	public void setVersionName(String versionName) {
		this.m_versionName = versionName;
	}
	public int getVersionYear() {
		return m_versionYear;
	}
	public void setVersionYear(int versionYear) {
		this.m_versionYear = versionYear;
	}
	public String getAuthor() {
		return m_author;
	}
	public void setAuthor(String author) {
		this.m_author = author;
	}
	
	public static void main(String[] args){
		Version version=new Version();
//		version.setM_frequencyIndex();
		
//		System.out.println(m_frequencyIndex.size());
//		version.setM_stringIndex();
//		version.setTopWordList();
	}
	

}
