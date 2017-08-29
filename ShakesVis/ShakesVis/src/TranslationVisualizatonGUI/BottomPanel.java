package TranslationVisualizatonGUI;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import translationVisualization.Version;

public class BottomPanel extends JPanel{
	public Hashtable<String, Integer> m_StringIndex=new Hashtable<String, Integer>();


	public BottomPanel(Hashtable<String, Integer> stringIndex){
		m_StringIndex = stringIndex;
	}
	
	public void paintComponent(Graphics g){

		for(int i=0; i<m_StringIndex.size(); i++){
			
			
			
		}
	}
	
	public Color calculateColor(int stringNumber){
		double colorFrequency=0.3;
		double toDouble=(double)stringNumber;
		float red=(float)(Math.sin(colorFrequency*toDouble+0)*127+128)/255;
		float green=(float)(Math.sin(colorFrequency*toDouble+2)*127+128)/255;
		float blue=(float)(Math.sin(colorFrequency*toDouble+4)*127+128)/255;
		return new Color(red, green, blue);
	}
	

}
