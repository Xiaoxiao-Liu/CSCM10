package translationVisualization;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import org.json.JSONException;
import org.json.JSONObject;  
 
public class JsonReader {
	
	String m_FilePath="src\\data\\DeReKo-2014-II-MainArchive-STT.100000.freq";

	private Hashtable<String, String> m_GermanLemmaList;

	private JSONObject jsonObject; 
	
	public JSONObject getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public Hashtable<String, String> getM_GermanLemmaList() {
		return m_GermanLemmaList;
	}

	public void initial() throws Exception{
		readTextFile(m_FilePath);
	}
	
	public void setM_GermanLemmaList(Hashtable<String, String> m_GermanLemmaList) {
		this.m_GermanLemmaList = m_GermanLemmaList;
	}
	
	
	public void readTextFile(String filePath) throws IOException, JSONException{
		
		setM_GermanLemmaList(new Hashtable<String, String>());
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
		
			String sCurrentLine; // used to store string one line as a string 
			String[] tmpWordsList = null; // a string array used to store strings after sCurrentLine beings splited

			while ((sCurrentLine = br.readLine()) != null) {
				setJsonObject(new JSONObject());  //read a line of the text each time
				if (sCurrentLine.trim().isEmpty()) { // get rid of empty lines
				}else{
					tmpWordsList = sCurrentLine.split("	");
				}
				getJsonObject().put(tmpWordsList[0], tmpWordsList[1]);
//				getM_GermanLemmaList().put(tmpWordsList[0], tmpWordsList[1]);
				System.out.print(getJsonObject());
		       
			}
//			String str=getJsonObject().toString()+"\r\n";
			 writeFile("src\\data\\test.json", getJsonObject().toString());  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void jsonWriter() throws JSONException, Exception{
		 JSONObject jsonObject = new JSONObject();  
	        jsonObject.put("1", "一");  
	        jsonObject.put("2", "二");  
	        jsonObject.put("3", "三");  
	        jsonObject.put("4", "四");  
	        jsonObject.put("5", "五");  
	        jsonObject.put("6", "六");  
	        jsonObject.put("7", "⑦");  
	        System.out.println(jsonObject);  
	  
	        writeFile("src\\data\\test.json", jsonObject.toString());  
	}
 
   /** 
    * @param args 
    */  
//   public static void main(String[] args) {  

	    /** 
	     * @param args 
	     * @throws Exception 
	     */  
	    public static void main(String[] args) throws Exception {  
	        // TODO Auto-generated method stub  
	    	JsonReader jr=new JsonReader();
//	    	jr.jsonWriter();
	    	jr.initial();
	        // String s = ReadFile("./src/test.json");  
	        // System.out.println(s);  
	  
//	        JSONObject jsonObject = new JSONObject();  
//	        jsonObject.put("1", "一");  
//	        jsonObject.put("2", "二");  
//	        jsonObject.put("3", "三");  
//	        jsonObject.put("4", "四");  
//	        jsonObject.put("5", "五");  
//	        jsonObject.put("6", "六");  
//	        jsonObject.put("7", "⑦");  
//	        System.out.println(jsonObject);  
//	  
//	        writeFile("src\\data\\test.json", jsonObject.toString());  
	    }  
	  
	    public static void writeFile(String filePath, String sets)  
	            throws IOException {  
	        FileWriter fw = new FileWriter(filePath);  
	        PrintWriter out = new PrintWriter(fw);  
	        out.write(sets);  
	        out.println();  
	        fw.close();  
	        out.close();  
	    }  
	  
	    public static String ReadFile(String path) {  
	        File file = new File(path);  
	        BufferedReader reader = null;  
	        String laststr = "";  
	        try {  
	            reader = new BufferedReader(new FileReader(file));  
	            String tempString = null;  
	            while ((tempString = reader.readLine()) != null) {  
	                laststr = laststr + tempString;  
	            }  
	            reader.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (reader != null) {  
	                try {  
	                    reader.close();  
	                } catch (IOException e1) {  
	                }  
	            }  
	        }  
	        return laststr;  
	    }  
 
   }  
 
//}  