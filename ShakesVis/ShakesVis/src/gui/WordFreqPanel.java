package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
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
	private String filePathBase;
	private int yCoordinate;
	 List<Map.Entry<String, Integer>> list;
	public List<Point> barLocation = new ArrayList<Point>();
	 JComponent c;
	 JButton barButton;
		public List<JButton> buttonList = new ArrayList<JButton>();

//	JLabel lblNewLabel = new JLabel(fileName);
	/**
	 * Create the panel.
	 * @param c2 
	 */
	public WordFreqPanel(String string) {
		setLayout(null);
		filePathBase=string;
		yCoordinate=30;
		paintEverything();
	}
	
	

	public void paintEverything(){

		
		DrawPanel();
		int xCoordinate=yCoordinate*2+30;
		int tmp=yCoordinate;
		WordFreqProcess dp =new WordFreqProcess();
		
		dp.setFilePath(filePathBase);
		dp.setStoreWords(new Hashtable<String, Integer>());
		dp.readFile(filePathBase);
		list =dp.sortHash();
		String str=null;
		int barWidth=0;
		
		barLocation=new ArrayList<Point>();
		FontMetrics fontMetrics;
		for (Map.Entry<String, Integer> mapping : list){
			
			str=mapping.getKey()+" "+mapping.getValue()+" ";
//			g.setFont("Serif");
//			g.setFont(new Font("Serif", Font.PLAIN, 14));
			Font myFont = new Font("Serif", Font.BOLD, 12);

			//draw words
			JLabel wordLabel=new JLabel();
			wordLabel.setText(str);
			wordLabel.setFont(myFont);
			fontMetrics=wordLabel.getFontMetrics(this.getFont());
			wordLabel.setBounds(xCoordinate-fontMetrics.stringWidth(str), yCoordinate-5, 100, 10);
			
			add(wordLabel);
//			g.drawString(str, xCoordinate-fontMetrics.stringWidth(str), yCoordinate);
			barWidth=mapping.getValue()*20;
			
			barButton = new JButton("");
			barButton.setBounds(xCoordinate, yCoordinate-5, barWidth, 10);
			
//			g.fillRect(xCoordinate, yCoordinate-5, barWidth, 10);
			barButton.setVisible(true);
			barButton.setBackground(Color.DARK_GRAY);
			add(barButton);
			buttonList.add(barButton);
			Point p;
			p=new Point(xCoordinate, yCoordinate-5);
			
//			p=barButton.getLocationOnScreen();
//			System.out.println(p+"before");
			SwingUtilities.convertPointToScreen(p, barButton);
			barLocation.add(p);
//			Point p=new Point(xCoordinate,yCoordinate-5);
//			SwingUtilities.convertPointToScreen(p, c);
			yCoordinate+=13;
		}
//		xCoordinate=tmp*5;
		yCoordinate=tmp;
	}
	
	
	public void DrawPanel(){
		String[] str2 = filePathBase.split("\\\\");
		String fileName= str2[2];
		fileName= fileName.substring(0, fileName.length()-4);
//		System.out.println(fileName);
//		System.out.println(str2[str2.length-1]);
		JLabel lblNewLabel = new JLabel(fileName);
		lblNewLabel.setBounds(76, 0, 150, 31);
		add(lblNewLabel);
	}
	
	
	 
}
