package gui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

import object.TopWord;
import object.Version;

public class WordFreqPanel extends JPanel {
	private final String[] versionArray;
	
	private int yCoordinate=25;
	private int xCoordinate=0;
	private final int versionDidstance=200; //distance between two versions
	private List<Map.Entry<String, Integer>> list;
	private int barWidth;
	private String eachWord=null;
	private TopWord m_topWord=new TopWord();
//	private Map<String, Point> stringPoint = new HashMap<String, Point>();
	/**
	 * Constructor
	 * @param stringArray
	 */
	public WordFreqPanel(String[] stringArray) {
		versionArray=stringArray;
	}
	
	public int GetxCoordinate() {
		return xCoordinate;
	}
	
	public boolean SetVersionxCoordinate(int j){
		xCoordinate=90+(j*versionDidstance);
		return true;
	}
	
	public int getbarWidth(){
		return barWidth;
	}
	
	public boolean setBarWidth(int currentValue){
		
		barWidth=currentValue*20;
		return true;
	}
	
	public int getyCoordinate() {
		return yCoordinate;
	}

	public boolean resetyCoordinate() {
		int firstLineYCoordinate=30;
		yCoordinate=firstLineYCoordinate;
		return true;
	}
	public boolean setRectYCoordinate(){
		int gap=8; //8 is the distance between the right bottom of the string and the left top of the rectangle
		yCoordinate=yCoordinate-gap; 
		return true;
	}
	
	public boolean setNextLineYCoordinate(){
		int gap=21; //21 is the distance between two lines y coordinate
		yCoordinate=yCoordinate+gap; 
		return true;
	}
	
	public String getEachWord(){
		return eachWord;
	}
	
	public boolean setEachWord(String wordName, int wordFrequency){
		eachWord=wordName+" "+wordFrequency+" ";
		return true;
	}
	public boolean setStringXCoordinate(){
		int gap=30;
		xCoordinate-=gap;
		return true;
	}
	public boolean setStringYCoordinate(){
		int gap=15;
		yCoordinate-=gap;
		return true;
	}
	
	public void paintComponent(Graphics g){
		

		List<List<String>> versionStringList=new ArrayList<List<String>>();
		List<List<Point>> versionPointList=new ArrayList<List<Point>>();

		Point stringLocation=new Point();//declare a point object
//		this.setLayout(null);
//		this.setBackground(Color.WHITE);
		super.paintComponent(g);
		String fileName;
		String[] fileNameSplit;
		
		for(int j=0;j<versionArray.length;j++){
			
			List<String> stringArray=new ArrayList<String>();
			List<Point> pointArray=new ArrayList<Point>();
			fileName=versionArray[j];
//			System.out.println(fileName);
//			DrawPanel(fileName);
			readData(fileName);
			fileNameSplit=fileName.split("\\\\");
			setStringXCoordinate();
			setStringYCoordinate();
			g.setColor(Color.BLACK);
			g.drawString(fileNameSplit[2].substring(0, fileNameSplit[2].length()-4), GetxCoordinate(), getyCoordinate());
			SetVersionxCoordinate(j);
			resetyCoordinate();
			FontMetrics fontMetrics = g.getFontMetrics();
			
			
			
				for (Map.Entry<String, Integer> mapping : list){
					setEachWord(mapping.getKey(), mapping.getValue());
					stringArray.add(mapping.getKey());
					g.setColor(Color.BLACK);
					g.drawString(getEachWord(), GetxCoordinate()-fontMetrics.stringWidth(getEachWord()), getyCoordinate());
					stringLocation=new Point(GetxCoordinate(), getyCoordinate());//get the point values
					pointArray.add(stringLocation);
					

					setBarWidth(mapping.getValue());
					setRectYCoordinate();					
					g.setColor(new Color(35,35,32, 40));//make rectangle transparent
					g.fillRect(GetxCoordinate(), getyCoordinate(), getbarWidth(), 10);
					setNextLineYCoordinate();
				}
			
			versionStringList.add(stringArray);	
			versionPointList.add(pointArray);
//			System.out.println(versionPointList);
			resetyCoordinate();
			}
//		System.out.println(versionStringList);
		
		for(int i=2; i<versionStringList.size();i++){
			
			for(int j=0;j<versionStringList.get(i).size();j++){
				final Random r=new Random();
				Color color=new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256));

				for(int u=0;u<versionStringList.get(i).size();u++){
					if(versionStringList.get(i-1).get(j).equals(versionStringList.get(i).get(u))){
						Point p=(Point)versionPointList.get(i-1).get(j);
						Point b=(Point)versionPointList.get(i).get(u);
						g.setColor(color);
						g.drawLine((int)p.getX(), (int)p.getY(), (int)b.getX(), (int)b.getY());
					
					}
				}
			}
		}
		
		
	}

	
	
	public void DrawPanel(String fileName){
		String[] str2 = fileName.split("\\\\");
		String sFileName= str2[2];
		sFileName= sFileName.substring(0, sFileName.length()-4);
		JLabel lblNewLabel = new JLabel(sFileName);
		lblNewLabel.setBounds(76, 0, 150, 31);
		add(lblNewLabel);
	}
	
	public boolean readData(String filePathBase){
		WordFreqProcess dp =new WordFreqProcess();
		dp.setFilePath(filePathBase);
		dp.setStoreWords(new Hashtable<String, Integer>());
		dp.readFile(filePathBase);
		list =dp.sortHash(dp.getStoreWords());
		return true;
	}
	
	
	
	 
}