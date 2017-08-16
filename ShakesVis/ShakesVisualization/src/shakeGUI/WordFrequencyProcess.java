package shakeGUI;

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

public class WordFrequencyProcess {
	public BufferedReader bufferdReader;
	public String strCurrentLine;
	public String[] tmpWordsArray;
	public int lineCount=0;
	public Hashtable<String, Integer> hashStoreWords;
	

	public void readFile(String filePath){
		filePath="src\\data\\BaseText Shakespeare.txt";
			try {				
				bufferdReader=new BufferedReader(new FileReader(filePath));
				strCurrentLine=bufferdReader.readLine();
				while(strCurrentLine!=null){
					if(strCurrentLine.trim().isEmpty()){
						
					}else if(lineCount==0){
						lineCount++;
					}else{
						tmpWordsArray=strCurrentLine.toLowerCase().replaceAll("\\p{Punct}", "").split(" ");
						for(int i=0; i<tmpWordsArray.length;i++){
							//Count top 50 words
							if(lineCount<50){
								if(!hashStoreWords.containsKey(tmpWordsArray[i])){
									hashStoreWords.put(tmpWordsArray[i], new Integer(1));
									lineCount=hashStoreWords.size();
								}else{
									hashStoreWords.put(tmpWordsArray[i], hashStoreWords.get(tmpWordsArray[i]).intValue()+1);
									lineCount=hashStoreWords.size();
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
			
			
			
			public List<Map.Entry<String, Integer>> sortWordsFrequency(){
				List<Map.Entry<String, Integer>> sortList=new ArrayList<Map.Entry<String, Integer>>(hashStoreWords.entrySet());
				Collections.sort(sortList, new Comparator<Map.Entry<String, Integer>>(){
					//decending order
					public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2){
						return o2.getValue().compareTo(o1.getValue());
					}
					
				});
				System.out.println(sortList);
				return sortList;
			}
			
			
			public static void main(String[] args){
				
				WordFrequencyProcess wordFP=new WordFrequencyProcess();
				wordFP.readFile("src\\data\\BaseText Shakespeare.txt");
				System.out.println(wordFP.sortWordsFrequency());
				
				
			}
				 
	

}
