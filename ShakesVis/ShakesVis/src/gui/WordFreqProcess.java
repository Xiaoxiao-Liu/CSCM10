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

public class WordFreqProcess{
	private Hashtable<String, Integer> m_storeWords;
	private static String m_filePath;

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
	
	public List<Map.Entry<String, Integer>> sortHash(){
		 List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(m_storeWords.entrySet());  
         Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {  
             //decending order  
             public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {               	                    
                 return o2.getValue().compareTo(o1.getValue());  
             }				
         });  
         return list;
	}
	
	
}