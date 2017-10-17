package otherDemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;



public class DataPreprocess{
	Hashtable<String, Integer> storeWords=new Hashtable<String, Integer>();
	static String filePath="D:\\dataProcess\\SHAKESPEAREbaseText.txt";
	File file=new File(filePath);
	int count=0;
	public static void main(String[] args){
		
		DataPreprocess dp=new DataPreprocess();
		dp.readFile(filePath);
		System.out.println(dp.sortHash());
	}
	//Read file
	public void readFile(String filePath){
		
		try {
			BufferedReader br= new BufferedReader(new FileReader(filePath));
			String sCurrentLine;
			String[] words=null;
			int count = 0;
			while((sCurrentLine=br.readLine())!=null){			
				if(sCurrentLine.trim().isEmpty()){					
				}
				else if (count==0)
					count++;
				else{
					words=sCurrentLine.toLowerCase().replaceAll("\\p{Punct}", "").split(" ");
					for(int i=0; i<words.length; i++){
			    		if(count<50){
			    			if(!storeWords.containsKey(words[i])){
								storeWords.put(words[i], new Integer(1));
								count=storeWords.size();
							}
							else{
								storeWords.put(words[i], storeWords.get(words[i]).intValue()+1);
								count=storeWords.size();
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
      
         return list;
	}

}