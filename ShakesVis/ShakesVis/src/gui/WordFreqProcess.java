package gui;
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

import object.TopWord;

public class WordFreqProcess{
	private Hashtable<String, Integer> m_storeWords;
	private static String m_filePath;
	private TopWord m_topWord;
	
	
	public static void main(String[] args){
		List<Map.Entry<String, Integer>> list;
		String[] stringArray={"src\\data\\BaseText Shakespeare.txt","src\\data\\1832 Baudissin ed Wenig.txt","src\\data\\1920 Gundolf.txt","src\\data\\1941 Schwarz.txt","src\\data\\1947 Baudissin ed Brunner.txt","src\\data\\1952 Flatter.txt","src\\data\\1962 Schroeder.txt","src\\data\\1963 Rothe.txt","src\\data\\1970 Fried.txt","src\\data\\1973 Lauterbach.txt","src\\data\\1976 Engler.txt","src\\data\\1978 Laube.txt","src\\data\\1985 Bolte Hamblock.txt","src\\data\\1992 Motschach.txt","src\\data\\1995 Guenther.txt","src\\data\\2003 Zaimoglu.txt"};
		WordFreqProcess dp =new WordFreqProcess();
		dp.setFilePath(stringArray[0]);
		dp.setStoreWords(new Hashtable<String, Integer>());
		dp.readFile(stringArray[0]);
		list =dp.sortHash(dp.getStoreWords());
		System.out.println(list);
	}

	public Hashtable<String, Integer> getStoreWords() {
		return m_storeWords;
	}

	public void setStoreWords(Hashtable<String, Integer> storeWords) {
		this.m_storeWords = storeWords;
	}

	public String getFilePath() {
		return m_filePath;
	}

	public void setFilePath(String filePath) {
		this.m_filePath = filePath;
	}
	
	//Read file
	public void readFile(String filePath){
		try {
			BufferedReader br= new BufferedReader(new FileReader(filePath));
			String sCurrentLine;
			String[] tmpWordsArray=null;
			int lineCount = 0;
			while((sCurrentLine=br.readLine())!=null){			
				if(sCurrentLine.trim().isEmpty()){					
				}
				else if(lineCount==0){
					lineCount++;
				}
				else{
					tmpWordsArray=sCurrentLine.toLowerCase().replaceAll("\\p{Punct}", "").split(" ");
					for(int i=0; i<tmpWordsArray.length; i++){
						if(lineCount<50){
			    			if(!m_storeWords.containsKey(tmpWordsArray[i])){
			    				m_storeWords.put(tmpWordsArray[i], new Integer(1));
								lineCount=getStoreWords().size();
							}
							else{
								m_storeWords.put(tmpWordsArray[i], m_storeWords.get(tmpWordsArray[i]).intValue()+1);
								lineCount=getStoreWords().size();
							}
			    		}else{
			    		}
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
	}
	
	public List<Map.Entry<String, Integer>> sortHash(Hashtable<String, Integer> m_storeWords){
		 List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(m_storeWords.entrySet());  
         Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {  
             //decending order  
             public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {               	                    
                 return o2.getValue().compareTo(o1.getValue());  
             }				
         });  
         
         return list;
	}
	
	
	
//	Map.Entry<String , TopWord topword>
	
}