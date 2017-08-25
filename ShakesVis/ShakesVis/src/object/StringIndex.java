package object;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class StringIndex {
	private Hashtable<String, Integer> m_stringIndex=new Hashtable<String, Integer>();
	private final String[] fileArray={"src\\data\\BaseText Shakespeare.txt","src\\data\\1832 Baudissin ed Wenig.txt","src\\data\\1920 Gundolf.txt","src\\data\\1941 Schwarz.txt","src\\data\\1947 Baudissin ed Brunner.txt","src\\data\\1952 Flatter.txt","src\\data\\1962 Schroeder.txt","src\\data\\1963 Rothe.txt","src\\data\\1970 Fried.txt","src\\data\\1973 Lauterbach.txt","src\\data\\1976 Engler.txt","src\\data\\1978 Laube.txt","src\\data\\1985 Bolte Hamblock.txt","src\\data\\1992 Motschach.txt","src\\data\\1995 Guenther.txt","src\\data\\2003 Zaimoglu.txt"};

	
	
	
	public Hashtable<String, Integer> getM_stringIndex() {
		return m_stringIndex;
	}
	
	public void setM_stringIndex() {

		DataReader dataReader=new DataReader();
		for(int i=1; i<fileArray.length; i++){
			dataReader.setFilePath(fileArray[i]);
			dataReader.setM_wordFrequency(new Hashtable<String, Integer>());
			dataReader.readFile(fileArray[i]);
			dataReader.sortHash(dataReader.getM_wordFrequency());
			List<Map.Entry<String, Integer>> m_frequencyIndex=new ArrayList<Map.Entry<String, Integer>>();
			for(Map.Entry<String, Integer> mapping : dataReader.getM_frequencyIndex()){
				if(!m_stringIndex.containsKey(mapping.getKey())){
					m_stringIndex.put(mapping.getKey(),m_stringIndex.size());
//				System.out.println(m_stringIndex.size());
				}
				else{
				}
			}
		}
	}

	
}
