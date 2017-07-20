package gui;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.opencsv.CSVWriter;

public class WordFreqProcess{
	Hashtable<String, Integer> storeWords;


	public Hashtable<String, Integer> getStoreWords() {
		return storeWords;
	}

	public void setStoreWords(Hashtable<String, Integer> storeWords) {
		this.storeWords = storeWords;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	String filePath;
	
	
//	public static void main(String[] args){
//		WordFreqProcess dp=new WordFreqProcess();
//		dp.readFile(filePath);
//		dp.sortHash();
//	}
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
				else if (lineCount==0)
					lineCount++;
				else{
					tmpWordsArray=sCurrentLine.toLowerCase().replaceAll("\\p{Punct}", "").split(" ");
					for(int i=0; i<tmpWordsArray.length; i++){
						//Count top 50 words
			    		if(lineCount<50){
			    			if(!storeWords.containsKey(tmpWordsArray[i])){
								storeWords.put(tmpWordsArray[i], new Integer(1));
								lineCount=storeWords.size();
							}
							else{
								storeWords.put(tmpWordsArray[i], storeWords.get(tmpWordsArray[i]).intValue()+1);
								lineCount=storeWords.size();
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
		 List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(storeWords.entrySet());  
         Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {  
             //decending order  
             public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {               	                    
             	//ascending order
//            	 return o1.getValue().compareTo(o2.getValue());  
                 return o2.getValue().compareTo(o1.getValue());  
             }				
         });  
//         System.out.println(list);
         String csv="src\\data\\baseTextOccurance.csv";
    	 List<String[]> stringList=new ArrayList<String[]>();
    	 
         for (Map.Entry<String, Integer> mapping : list) {  
        	 stringList.add(new String[]{mapping.getKey(),mapping.getValue().toString()});     
//        	 System.out.println(mapping.getKey());
         }
    	 try {
			CSVWriter writer = new CSVWriter(new FileWriter(csv));
			writer.writeAll(stringList);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//         String xyz=list.toString();
//         System.out.println(xyz);
         return list;
	}
	
	
	
	

}