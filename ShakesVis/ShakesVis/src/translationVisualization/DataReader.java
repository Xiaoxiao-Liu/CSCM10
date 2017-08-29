package translationVisualization;

import java.awt.Color;
import java.awt.Point;
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

public class DataReader {
	private Hashtable<String, Integer> frequencySort=new Hashtable<String, Integer>();
	private List<Map.Entry<String, Integer>> frequencyIndex = new ArrayList<Map.Entry<String, Integer>>();
	private final String[] stringArray={"src\\data\\0000 BaseText Shakespeare.txt","src\\data\\1832 Baudissin ed Wenig.txt","src\\data\\1920 Gundolf.txt","src\\data\\1941 Schwarz.txt","src\\data\\1947 Baudissin ed Brunner.txt","src\\data\\1952 Flatter.txt","src\\data\\1962 Schroeder.txt","src\\data\\1963 Rothe.txt","src\\data\\1970 Fried.txt","src\\data\\1973 Lauterbach.txt","src\\data\\1976 Engler.txt","src\\data\\1978 Laube.txt","src\\data\\1985 Bolte Hamblock.txt","src\\data\\1992 Motschach.txt","src\\data\\1995 Guenther.txt","src\\data\\2003 Zaimoglu.txt"};
	private Hashtable<String, Integer> frequency=new Hashtable<String, Integer>();
	public Hashtable<String, Integer> m_StringIndex=new Hashtable<String, Integer>();
	public List<Version> m_VersionList=new ArrayList<Version>();
//	private Hashtable<String, Integer> m_ColorIndex=new Hashtable<String, Integer>();


	
	public boolean readOneFile(String filePath){
		try {
			frequency=new Hashtable<String, Integer>();
			BufferedReader br=new BufferedReader(new FileReader(filePath));
			String sCurrentLine;
			String[] tmpWordsArray=null;
			int lineCount=0;
			
			while((sCurrentLine=br.readLine())!=null){
				if(sCurrentLine.trim().isEmpty()){	
				}else if(lineCount==0){
						lineCount++;
				}else{
					tmpWordsArray=sCurrentLine.toLowerCase().replaceAll("\\p{Punct}", "").split(" ");
					for(int i=0; i<tmpWordsArray.length; i++){
							addWordFrequency(tmpWordsArray[i]);							
				    }
				}
			}
//			System.out.println(frequency.size());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	
	public void addWordFrequency(String word){
		if(!frequency.containsKey(word)){
			frequency.put(word, new Integer(1));
		}
		else{
			frequency.put(word, frequency.get(word).intValue()+1);
		}
	}


	public boolean sortFrequencyIndex(Hashtable<String, Integer> frequencyUnsorted){
//		System.out.println(frequencyIndex.size());
		frequencyIndex = new ArrayList<Map.Entry<String, Integer>>(frequencyUnsorted.entrySet());  
        Collections.sort(frequencyIndex, new Comparator<Map.Entry<String, Integer>>() {  
            //decending order  
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {               	                    
                return o2.getValue().compareTo(o1.getValue());  
            }				
        });  
        return true;
	}
	
	public void getTopFrequencyIndex(List<Map.Entry<String, Integer>> frequencyIndex){
		List<Map.Entry<String, Integer>> topFrequencyIndex;
		int countSize=0;
//		for(Map.Entry<String, Integer> mapping : frequencyIndex){
//			
//		}
//		
	}

	public List<Version> readAllFile(){
		for(int i=0; i<stringArray.length; i++){
			Version version=new Version();
			readOneFile(stringArray[i]);
			sortFrequencyIndex(frequency);
			String[] fileNameSplit=stringArray[i].split("\\\\");
			int fileNamePosition=2;
			String versionInfomation=fileNameSplit[fileNamePosition];
			version.setM_VersionYear(versionInfomation);
			version.setM_Author(versionInfomation);
			version.setM_titlePoint(calculatePoint(i,0));
			int lineNumber=1;
			int listSize=50;
				for(Map.Entry<String, Integer> mapping : frequencyIndex){
					if(version.getM_ConcordanceList().size()<listSize){ //get the top 50 frequency words in every version
					 Concordance concordance=new Concordance();
					 concordance.setM_Word(mapping.getKey());
					 concordance.setM_Frequency(mapping.getValue());
					 concordance.setM_RectWidth(mapping.getValue());
					 concordance.setM_StringPoint(calculatePoint(i, lineNumber));
					 concordance.setM_RectPoint(concordance.getM_StringPoint());
					 addStringIndex(mapping);
					 concordance.setM_Color(calculateColor(m_StringIndex.get(concordance.getM_Word())));
					 version.setM_ConcordanceList(concordance);
					 lineNumber++;
					}
				}
				System.out.println(version.getM_ConcordanceList().size());
				m_VersionList.add(version);
				
			
			
			
		}
		
		return m_VersionList;
	}
	
	public Point calculatePoint(int versionNumber, int lineNumber){
		int x=55;
		int y=30;
		int columnSpace=200;
		int lineSpace=25;
		x=x+columnSpace*versionNumber;
		y=y+lineSpace*lineNumber;
		return new Point(x,y);
	}
	
	public Color calculateColor(int stringNumber){
		double colorFrequency=0.3;
		double toDouble=(double)stringNumber;
		float red=(float)(Math.sin(colorFrequency*toDouble+0)*127+128)/255;
		float green=(float)(Math.sin(colorFrequency*toDouble+2)*127+128)/255;
		float blue=(float)(Math.sin(colorFrequency*toDouble+4)*127+128)/255;
		return new Color(red, green, blue);
	}
	
	public void addStringIndex(Map.Entry<String, Integer> mapping){
		if(!m_StringIndex.containsKey(mapping.getKey())){
			 m_StringIndex.put(mapping.getKey(), m_StringIndex.size());
			 
		 }
	}
	
}
	
	
	
