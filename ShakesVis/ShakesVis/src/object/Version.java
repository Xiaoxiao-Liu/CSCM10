package object;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import gui.WordFreqProcess;

public class Version {
	
	private List<TopWord> m_topWordList=new ArrayList<TopWord>();
	private String m_versionName;
	private int m_versionYear;
	private String m_author;
	private final String[] fileArray={"src\\data\\BaseText Shakespeare.txt","src\\data\\1832 Baudissin ed Wenig.txt","src\\data\\1920 Gundolf.txt","src\\data\\1941 Schwarz.txt","src\\data\\1947 Baudissin ed Brunner.txt","src\\data\\1952 Flatter.txt","src\\data\\1962 Schroeder.txt","src\\data\\1963 Rothe.txt","src\\data\\1970 Fried.txt","src\\data\\1973 Lauterbach.txt","src\\data\\1976 Engler.txt","src\\data\\1978 Laube.txt","src\\data\\1985 Bolte Hamblock.txt","src\\data\\1992 Motschach.txt","src\\data\\1995 Guenther.txt","src\\data\\2003 Zaimoglu.txt"};
	private List<List<Map.Entry<String, Integer>>> m_readerList=new ArrayList<List<Map.Entry<String, Integer>>>();
	private Hashtable<String, Integer> m_stringIndex=new Hashtable<String, Integer>();
//	private List<List<TopWord>>
	
	public Hashtable<String, Integer> getM_stringIndex() {
		return m_stringIndex;
	}

	public List<TopWord> getTopWordList() {
		return m_topWordList;
	}

	public void setDataReader(String[] fileArray) {
		DataReader dataReader =new DataReader();
		for(int i=0; i<fileArray.length; i++){
			List<Map.Entry<String, Integer>> list;
			dataReader.setFilePath(fileArray[i]);
			dataReader.setStoreWords(new Hashtable<String, Integer>());
			dataReader.readFile(fileArray[i]);
			list =dataReader.sortHash(dataReader.getStoreWords());
			m_readerList.add(list);
//			System.out.println(list);
		}
	}
	
	public void setM_stringIndex() {
		setDataReader(fileArray);
		int count=0;
		for(int i=1; i<m_readerList.size();i++){
			for(Map.Entry<String, Integer> mapping : m_readerList.get(i)){
				if(!m_stringIndex.containsKey(mapping.getKey())){
					m_stringIndex.put(mapping.getKey(),count);
					count++;
				}
				else{
				}
				
				
			}
		}
	}
	

	public void setTopWordList() {
		TopWord topWord=new TopWord();
		setDataReader(fileArray);
		setM_stringIndex();
		int count=0;
		for(int i=0; i<m_readerList.size();i++){
			int lineCount=0;
			for(Map.Entry<String, Integer> mapping : m_readerList.get(i)){
				topWord.setM_word(mapping.getKey());
				topWord.setM_frequency(mapping.getValue());
				topWord.setM_point(i, lineCount);
//				System.out.println(topWord.getM_point());
				topWord.setM_color(m_stringIndex.get(mapping.getKey()));
				lineCount++;
				m_topWordList.add(topWord);
			}
			
			
		}
		
		
		this.m_topWordList = m_topWordList;
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
	
//	public void readContent(){
//		for(int i=0; i<50; i++){
//			
//		}
//	}
//	
//	public void readVersions(){
//		for(int j=0; j<16; j++){
//			
//		}
//	}
	
	
	
	public static void main(String[] args){
		Version version=new Version();
		version.setM_stringIndex();
		
	}
	

}
