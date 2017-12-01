package translationVisualization;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class GermanLemmaProcess {

	/**an string array to store file paths for basic text and translation versions*/
//	private final String[] m_FilePath = { "src\\data\\DeReKo-2014-II-MainArchive-STT.100000.freqt" };

	String m_FilePath="src\\data\\DeReKo-2014-II-MainArchive-STT.100000.freq";
	
	private Hashtable<String, String> m_GermanLemmaList;

	public void initial() throws Exception{
		readOneFile(m_FilePath);
	}
	
	public Hashtable<String, String> getM_GermanLemmaList() {
		return m_GermanLemmaList;
	}

	public void setM_GermanLemmaList(Hashtable<String, String> m_GermanLemmaList) {
		this.m_GermanLemmaList = m_GermanLemmaList;
	}
	
	/**
	 * 
	 * @param filePath-a string array of file paths 
	 * @param versionNumber - an integer of version's number
	 * @param position - an integer telling the method which string in the array you want
	 * @return name -  the name of the version
	 */
	public String filePathProcess(String[] filePath, int versionNumber, int position){
		String[] fileNameSplit=filePath[versionNumber].split("\\\\"); //split the file path
		String name=fileNameSplit[position];
		return name;
	}
	
	/**
	 * pass the file path and read one file 
	 * @param filePath
	 * @return TRUE on success
	 * @throws Exception 
	 * @throws Exception
	 */
	public boolean readOneFile(String filePath) throws Exception {
		try {
			setM_GermanLemmaList(new Hashtable<String, String>());
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String sCurrentLine; // used to store string one line as a string 
			String[] tmpWordsList = null; // a string array used to store strings after sCurrentLine beings splited

			while ((sCurrentLine = br.readLine()) != null) {//read a line of the text each time
				if (sCurrentLine.trim().isEmpty()) { // get rid of empty lines
				}else{
					tmpWordsList = sCurrentLine.split("	");
				}
				
				getM_GermanLemmaList().put(tmpWordsList[0], tmpWordsList[1]);
				System.out.print(getM_GermanLemmaList());
				
				
			}

		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //create a BufferedReader and read file
		
		return true;
	}
	
	public static void main(String[] args) throws Exception{
		GermanLemmaProcess glp=new GermanLemmaProcess();
		glp.initial();
	}
		
	
	
}
