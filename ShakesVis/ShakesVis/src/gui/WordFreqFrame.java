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
		WordFreqPanel ShakespearePanel=new WordFreqPanel();
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