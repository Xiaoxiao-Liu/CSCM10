package translationVisualization;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class DataReader {
	private Hashtable<String, Integer> m_frequencySort=new Hashtable<String, Integer>();
	private List<Map.Entry<String, Integer>> m_frequencyIndex = new ArrayList<Map.Entry<String, Integer>>();
	private final String[] m_stringArray={"src\\data\\BaseText Shakespeare.txt","src\\data\\1832 Baudissin ed Wenig.txt","src\\data\\1920 Gundolf.txt","src\\data\\1941 Schwarz.txt","src\\data\\1947 Baudissin ed Brunner.txt","src\\data\\1952 Flatter.txt","src\\data\\1962 Schroeder.txt","src\\data\\1963 Rothe.txt","src\\data\\1970 Fried.txt","src\\data\\1973 Lauterbach.txt","src\\data\\1976 Engler.txt","src\\data\\1978 Laube.txt","src\\data\\1985 Bolte Hamblock.txt","src\\data\\1992 Motschach.txt","src\\data\\1995 Guenther.txt","src\\data\\2003 Zaimoglu.txt"};
	private Version m_version=new Version();
	private Concordance m_wordConcordance=new Concordance();
	private String m_filePath;

	public boolean readOneFile(String filePath){
		try {
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
				}

			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return true;
	}
}
	
	
	
