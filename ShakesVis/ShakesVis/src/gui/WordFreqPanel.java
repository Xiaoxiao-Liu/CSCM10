package gui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class WordFreqPanel extends JPanel {
	private String filePathBase;
	private int yCoordinate;
	 JComponent c;
	
//	JLabel lblNewLabel = new JLabel(fileName);
	/**
	 * Create the panel.
	 * @param c2 
	 */
	public WordFreqPanel(String string) {
		setLayout(null);
		filePathBase=string;
		yCoordinate=30;
	}
	
	

	public void paintComponent(Graphics g){
		DrawPanel();
		int xCoordinate=yCoordinate*2+30;
		int tmp=yCoordinate;
		super.paintComponent(g);
//		this.setBackground(Color.GRAY);
		WordFreqProcess dp =new WordFreqProcess();
		
		dp.setFilePath(filePathBase);
		dp.setStoreWords(new Hashtable<String, Integer>());
		dp.readFile(filePathBase);
		List<Map.Entry<String, Integer>> list =dp.sortHash();
		String str=null;
		int barWidth=0;
		
		
		FontMetrics fontMetrics = g.getFontMetrics();
		for (Map.Entry<String, Integer> mapping : list){
			str=mapping.getKey()+" "+mapping.getValue()+" ";
//			g.setFont("Serif");
//			g.setFont(new Font("Serif", Font.PLAIN, 14));
			
			g.drawString(str, xCoordinate-fontMetrics.stringWidth(str), yCoordinate);
			barWidth=mapping.getValue()*20;
			g.fillRect(xCoordinate, yCoordinate-5, barWidth, 10);
//			Point p=new Point(xCoordinate,yCoordinate-5);
//			SwingUtilities.convertPointToScreen(p, c);
			
			yCoordinate+=13;
		
		}
//		xCoordinate=tmp*5;
		yCoordinate=tmp;
//		System.out.println(yCoordinate);
	}
	
	
	public void DrawPanel(){
		String[] str2 = filePathBase.split("\\\\");
		String fileName= str2[2];
		fileName= fileName.substring(0, fileName.length()-4);
		System.out.println(fileName);
//		System.out.println(str2[str2.length-1]);
		JLabel lblNewLabel = new JLabel(fileName);
		lblNewLabel.setBounds(76, 0, 150, 31);
		add(lblNewLabel);
	}
	
	
	 
}
