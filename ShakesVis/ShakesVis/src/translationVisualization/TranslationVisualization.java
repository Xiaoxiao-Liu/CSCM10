package translationVisualization;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import TranslationVisualizatonGUI.ConcordanceFrame;

public class TranslationVisualization {
//	private List<Version> m_VersionList=new ArrayList<Version>();
	
	
	
	public static void main(String[] args){
		List<Version> m_VersionList=new ArrayList<Version>();
		DataReader dataReader=new DataReader();
		m_VersionList=dataReader.readAllFile();
		
		
		
		
		ConcordanceFrame frame = new ConcordanceFrame();
		
		
		
	}
	
	

}
