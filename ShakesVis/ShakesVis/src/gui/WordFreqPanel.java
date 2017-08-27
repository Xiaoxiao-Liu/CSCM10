package gui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

import object.TopWord;
import object.Version;

public class WordFreqPanel extends JPanel {
	private final String[] fileArray = { "src\\data\\BaseText Shakespeare.txt", "src\\data\\1832 Baudissin ed Wenig.txt", "src\\data\\1920 Gundolf.txt", "src\\data\\1941 Schwarz.txt","src\\data\\1947 Baudissin ed Brunner.txt", "src\\data\\1952 Flatter.txt", "src\\data\\1962 Schroeder.txt","src\\data\\1963 Rothe.txt", "src\\data\\1970 Fried.txt", "src\\data\\1973 Lauterbach.txt","src\\data\\1976 Engler.txt", "src\\data\\1978 Laube.txt", "src\\data\\1985 Bolte Hamblock.txt","src\\data\\1992 Motschach.txt", "src\\data\\1995 Guenther.txt", "src\\data\\2003 Zaimoglu.txt" };
	private Point m_point = new Point();
	private int yCoordinate = 25;
	private int xCoordinate = 0;
	private final int versionDidstance = 200; // distance between two versions
	private int barWidth;
	private String eachWord = null;
	TopWord topWordCompare=new TopWord();
	private List<Version> m_versionList=new ArrayList<Version>();
	
	/**
	 * Constructor
	 * 
	 * @param stringArray
	 */
	public WordFreqPanel() {
		readAllVersions();
	}
	
	public void readAllVersions(){
		for(int i=0; i<fileArray.length; i++){
			Version version=new Version(i);
			m_versionList.add(i, version);
		}
		
	}


	public void paintComponent(Graphics g){
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		super.paintComponent(g);
		for(int j=1;j<=fileArray.length;j++){
			Version version=m_versionList.get(j-1);
			
			g.setColor(Color.BLACK);
			
			String[] fileNameSplit=version.getVersionName().split("\\\\");
			g.drawString(fileNameSplit[2].substring(0, fileNameSplit[2].length()-4), version.getM_titlePoint(j-1).x, version.getM_titlePoint(j-1).y);//Title
			FontMetrics fontMetrics = g.getFontMetrics();
				for (int i=0;i<version.getM_topWordList().size(); i++){
					TopWord topWord=version.getM_topWordList().get(i);
					String string=topWord.getM_word();
					//set string coordinates
					int x=topWord.getM_stringPoint().x-fontMetrics.stringWidth(string);
					int y=topWord.getM_stringPoint().y;
					g.setColor(Color.BLACK);
					g.drawString(string, x, y);
					//set rectangle coordinates
					x=topWord.getM_rectPoint().x;
					y=topWord.getM_rectPoint().y;
					g.setColor(new Color(35,35,32, 40));//make rectangle transparent
					g.fillRect(x, y, topWord.getM_rectWidth(), 10);
					if(j>1&&j<fileArray.length){
						Version versionCompare=m_versionList.get(j);
						for(int k=0; k<versionCompare.getM_topWordList().size(); k++){
							TopWord topWordCompare=versionCompare.getM_topWordList().get(k);
							if(topWord.getM_word().equals(topWordCompare.getM_word())){
								Point point=topWordCompare.getM_stringPoint();
								g.setColor(topWord.getM_color());
								g.drawLine(topWord.getM_stringPoint().x, topWord.getM_stringPoint().y, point.x, point.y);
							}
						}
						}else{
							}
			}
	}
	}
	

	
}