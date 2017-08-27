package TranslationVisualizatonGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;

import gui.WordFreqFrame;
import gui.WordFreqPanel;
import translationVisualization.DataReader;
import translationVisualization.Version;

public class ConcordanceFrame extends JFrame {
	private List<Version> m_VersionList=new ArrayList<Version>();
	
	private JScrollPane scrollPanel;
	private JPanel jPanel_1;
	public static void main(String[] args) {
		
		ConcordanceFrame frame = new ConcordanceFrame();
		frame.setSize(800, 800);			
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}
	/**
	 * Create the frame.
	 */
	
	public ConcordanceFrame() {
		
		DataReader dataReader=new DataReader();
		m_VersionList=dataReader.readAllFile();
		long startTime = System.currentTimeMillis();
		ConcordancePanel ShakespearePanel=new ConcordancePanel(m_VersionList);
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
	
		System.out.println(System.currentTimeMillis()-startTime);
	}
	
	
	
}
