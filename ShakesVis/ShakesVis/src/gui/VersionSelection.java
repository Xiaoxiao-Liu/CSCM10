package gui;

import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class VersionSelection extends JPanel {

	/**
	 * Create the panel.
	 */
	public VersionSelection() {
		JLabel label3 = new JLabel("Media Outlets:");  
		Object[] value = new String[]{ "Selected All","Al-Jazeera" , "BBC News" ,"Daily Mail" ,"Fox News","New York Daily News", "New York Times","the Guardian","Wall Street Journal"};    
		Object[] defaultValue = new String[]{ "Selected All" };   
		  
		MultiComboBox mulit = new MultiComboBox(value, defaultValue);  
		
		Hashtable<String, Integer> baicai=new Hashtable<String, Integer>();
		
		WordFreqProcess wp= new WordFreqProcess();
		//wp.setFilePath("dsfsjdfhs");
		//wp.setStoreWords(baicai);
//??? where are those methods?
		
	}

}
