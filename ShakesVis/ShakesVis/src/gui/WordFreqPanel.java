package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class WordFreqPanel extends JPanel {
	private final String[] versionArray;
	private String filePathBase;
	private int yCoordinate=30;
	private int xCoordinate=0;
	private final int widthBetween=200; //distance between two versions
	private List<Map.Entry<String, Integer>> list;
	int barWidth=0;
	
	/**
	 * Constructor
	 * @param stringArray
	 */
	public WordFreqPanel(String[] stringArray) {
		versionArray=stringArray;
	}
	
	public int getxCoordinate() {
		return xCoordinate;
	}
	
	public boolean setxCoordinate(int j){
		xCoordinate=30*3+(j*widthBetween);
		return true;
	}
	
	
	public int getyCoordinate() {
		return yCoordinate;
	}

	public boolean resetyCoordinate() {
		yCoordinate=30;
		return true;
	}
	
	public boolean setNextLineyCoordinate(){
		yCoordinate+=13;
		return true;
	}
	

	public void paintComponent(Graphics g){
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		super.paintComponent(g);
		for(int j=0;j<versionArray.length;j++){
		filePathBase=versionArray[j];
//		DrawPanel();
		readData(filePathBase);
		setxCoordinate(j);
		String str=null;
		
		FontMetrics fontMetrics = g.getFontMetrics();
			for (Map.Entry<String, Integer> mapping : list){
				str=mapping.getKey()+" "+mapping.getValue()+" ";
				g.drawString(str, getxCoordinate()-fontMetrics.stringWidth(str), getyCoordinate());
				barWidth=mapping.getValue()*20;
				g.fillRect(getxCoordinate(), getyCoordinate()-5, barWidth, 10);
				setNextLineyCoordinate();
			}
		resetyCoordinate();
		}
	}

	public void DrawPanel(){
		String[] str2 = filePathBase.split("\\\\");
		String fileName= str2[2];
		fileName= fileName.substring(0, fileName.length()-4);
		JLabel lblNewLabel = new JLabel(fileName);
		lblNewLabel.setBounds(76, 0, 150, 31);
		add(lblNewLabel);
	}
	
	public boolean readData(String filePathBase){
		WordFreqProcess dp =new WordFreqProcess();
		dp.setFilePath(filePathBase);
		dp.setStoreWords(new Hashtable<String, Integer>());
		dp.readFile(filePathBase);
		list =dp.sortHash();
		return true;
	}
	
	
	
	 
}