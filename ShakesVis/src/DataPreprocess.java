import java.io.BufferedReader;
import java.io.File;
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

public class DataPreprocess{
	Hashtable<String, Integer> storeWords=new Hashtable<String, Integer>();
	String filePath="D:\\dataProcess\\SHAKESPEAREbaseText.txt";
	File file=new File(filePath);
	int count=0;
	
	public static void main(String[] args){
		
		DataPreprocess dp=new DataPreprocess();
		dp.readFile();
		dp.sortHash();
	}
	//Read file
	public void readFile(){		
		try {
			BufferedReader br= new BufferedReader(new FileReader(filePath));
			String sCurrentLine;
			String[] words=null;
			while((sCurrentLine=br.readLine())!=null){			
				if(sCurrentLine.trim().isEmpty()){					
				}
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
             	//ascending order：
//            	 return o1.getValue().compareTo(o2.getValue());  
                 return o2.getValue().compareTo(o1.getValue());  
             }				
         });  
//         System.out.println(list);
         for (Map.Entry<String, Integer> mapping : list) {      	 
             System.out.println("map key: "+mapping.getKey() + ":\t" + mapping.getValue());        
         }
//         String xyz=list.toString();
//         System.out.println(xyz);
         return list;
	}

}