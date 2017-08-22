package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingUtilities;
import javax.swing.plaf.LayerUI;

public class WordFreqFrame extends JFrame {

	private JScrollPane scrollPanel;
	private JPanel jPanel_1;
	private static JPanel jPanel_2;
	 JComponent c;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		WordFreqFrame frame = new WordFreqFrame();
		frame.setSize(800, 800);			
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}
	/**
	 * Create the frame.
	 */
	
	public WordFreqFrame() {

		String[] stringArray={"src\\data\\BaseText Shakespeare.txt","src\\data\\1832 Baudissin ed Wenig.txt","src\\data\\1920 Gundolf.txt","src\\data\\1941 Schwarz.txt","src\\data\\1947 Baudissin ed Brunner.txt","src\\data\\1952 Flatter.txt","src\\data\\1962 Schroeder.txt","src\\data\\1963 Rothe.txt","src\\data\\1970 Fried.txt","src\\data\\1973 Lauterbach.txt","src\\data\\1976 Engler.txt","src\\data\\1978 Laube.txt","src\\data\\1985 Bolte Hamblock.txt","src\\data\\1992 Motschach.txt","src\\data\\1995 Guenther.txt","src\\data\\2003 Zaimoglu.txt"};
		WordFreqPanel ShakespearePanel=new WordFreqPanel(stringArray);
		ShakespearePanel.setVisible(true);
		ShakespearePanel.setPreferredSize(new Dimension(3400, 800));
		ShakespearePanel.setLayout(null);
		ShakespearePanel.setBackground(Color.WHITE);
		ShakespearePanel.repaint();
		
		scrollPanel=new JScrollPane(ShakespearePanel);//add a parent panel (pp) which holds 16 WFPanels
		scrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanel.setPreferredSize(new Dimension(700, 500));
		
		scrollPanel.setLayout(new ScrollPaneLayout());
		jPanel_1= new JPanel();
		jPanel_1.setBackground(Color.WHITE);
		jPanel_1.add(scrollPanel);//add the scrollPanel to a JPanel(JJ)
		setContentPane(jPanel_1);//add JJ to the parent Frame 
		
	}
	
	
}