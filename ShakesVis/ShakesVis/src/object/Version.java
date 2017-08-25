package object;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Version {
	private DataReader m_dataReader =new DataReader();
	private String m_versionName;
	private int m_versionYear;
	private int m_versionNumber;
	private String m_author;
	private final String[] m_fileArray = { "src\\data\\BaseText Shakespeare.txt", "src\\data\\1832 Baudissin ed Wenig.txt", "src\\data\\1920 Gundolf.txt", "src\\data\\1941 Schwarz.txt","src\\data\\1947 Baudissin ed Brunner.txt", "src\\data\\1952 Flatter.txt", "src\\data\\1962 Schroeder.txt","src\\data\\1963 Rothe.txt", "src\\data\\1970 Fried.txt", "src\\data\\1973 Lauterbach.txt","src\\data\\1976 Engler.txt", "src\\data\\1978 Laube.txt", "src\\data\\1985 Bolte Hamblock.txt","src\\data\\1992 Motschach.txt", "src\\data\\1995 Guenther.txt", "src\\data\\2003 Zaimoglu.txt" };
	private List<TopWord> m_topWordList=new ArrayList<TopWord>();
	private StringIndex m_stringIndex=new StringIndex();
	private Point m_titlePoint=new Point();
	


	public Point getM_titlePoint(int versionNumber) {
		int x=20;
		int y=20;
		int columnSpace=200;
		x=x+columnSpace*versionNumber;
		y=y;
		m_titlePoint=new Point(x, y);
		return m_titlePoint;
	}
	
	
	
	public Version(int versionNumber) {
		m_versionNumber = versionNumber;
		setM_TopWordList();
	}

	public Hashtable<String, Integer> getM_stringIndex() {
		m_stringIndex.setM_stringIndex();
		return m_stringIndex.getM_stringIndex();
	}

	public void setM_frequencyIndex() {
//		System.out.println(fileArray[m_versionNumber]);

			m_dataReader.setFilePath(m_fileArray[m_versionNumber]);
			m_dataReader.setM_wordFrequency(new Hashtable<String, Integer>());
			m_dataReader.readFile(m_fileArray[m_versionNumber]);
			m_dataReader.sortHash(m_dataReader.getM_wordFrequency());
//			m_frequencyIndex=m_dataReader.getM_frequencyIndex();
			
	}
	
	public void setM_TopWordList() {
			setM_frequencyIndex();
			int lineNumber=0;
			for(Map.Entry<String, Integer> mapping : m_dataReader.getM_frequencyIndex()){
				TopWord topWord=new TopWord();
				topWord.setM_word(mapping.getKey());
				topWord.setM_frequency(mapping.getValue());
				topWord.setM_stringPoint(m_versionNumber, lineNumber);
				
				topWord.setM_rectPoint(topWord.getM_stringPoint());
				topWord.setM_rectWidth(topWord.getM_frequency());
//				System.out.println(topWord.getM_rectWidth());
				if(getM_stringIndex().containsKey(mapping.getKey())){
					topWord.setM_color(getM_stringIndex().get(mapping.getKey()));
				}
				m_topWordList.add(topWord);
				lineNumber++;
			}
	}
	
	public List<TopWord> getM_topWordList() {
		return m_topWordList;
	}

	public String getVersionName() {
		setVersionName();
		System.out.println(m_versionName);
		return m_versionName;
	}
	public void setVersionName() {
		 m_versionName=m_fileArray[m_versionNumber];
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
	
//	public static void main(String[] args){
//		Version version=new Version(1);
//		version.setM_frequencyIndex();
//		
//		System.out.println(getM_topWordList());
//		version.setM_frequencyIndex();
//		version.setTopWordList();
//	}
	

}
