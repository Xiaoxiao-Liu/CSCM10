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
	private int widthBetween=200;
	private List<Map.Entry<String, Integer>> list;
	public List<Point> barLocation = new ArrayList<Point>();
	public List<JButton> buttonList = new ArrayList<JButton>();
	
	/**
	 * This method is inherited from JComponent.
	 * Render various rectangles and strings
	 * @param graphics - generic graphics object
	 */
	public WordFreqPanel(String[] stringArray) {
		versionArray=stringArray;
	}
	
	public void paintComponent(Graphics g){
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		super.paintComponent(g);
		for(int j=0;j<versionArray.length;j++){
			filePathBase=versionArray[j];
//		DrawPanel();
		int xCoordinate=yCoordinate*2+30+(j*widthBetween);
		int tmp=yCoordinate;
//		super.paintComponent(g);
		WordFreqProcess dp =new WordFreqProcess();
		dp.setFilePath(filePathBase);
		dp.setStoreWords(new Hashtable<String, Integer>());
		dp.readFile(filePathBase);
		list =dp.sortHash();
		String str=null;
		int barWidth=0;
		FontMetrics fontMetrics = g.getFontMetrics();
		for (Map.Entry<String, Integer> mapping : list){
			str=mapping.getKey()+" "+mapping.getValue()+" ";
			g.drawString(str, xCoordinate-fontMetrics.stringWidth(str), yCoordinate);
			barWidth=mapping.getValue()*20;
			g.fillRect(xCoordinate, yCoordinate-5, barWidth, 10);
			yCoordinate+=13;
		}
		yCoordinate=tmp;
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
	
	public void compareTwoVersions(){
		Map.Entry<String, Integer> mapping_1;
	}
	
	
	 
}