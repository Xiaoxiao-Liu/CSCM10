package translationVisualization;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;

import TranslationVisualizatonGUI.ConcordancePanel;

public class TranslationVisualization {
	private final static int FRAME_WIDTH=800;
	private final static int FRAME_HEIGHT=800;

	private final static int PANEL_WIDTH=3400;
	private final static int PANEL_HEIGHT=1800;
	
	private final static int SCROLL_PANEL_WIDTH=500;
	private final static int SCROLL_PANEL_HEIGHT=300;
	
	private JFrame concordanceFrame;
	
	private JScrollPane scrollPanel;

	private List<Version> m_VersionList=new ArrayList<Version>();

	private ConcordancePanel concordancePanel;
	
	private JButton ConcordanceButton;
	
	private JPanel jPanel_1;

	private JPanel m_buttonPanel;



	public JFrame getConcordanceFrame() {
		return concordanceFrame;
	}


	public void setConcordanceFrame(JFrame concordanceFrame) {
		this.concordanceFrame = concordanceFrame;
		getConcordanceFrame().setLayout(new FlowLayout(FlowLayout.LEADING));
		getConcordanceFrame().setSize(FRAME_WIDTH, FRAME_HEIGHT);	
	}
	
	public JScrollPane getScrollPanel() {
		return scrollPanel;
	}

	public void setScrollPanel(JScrollPane scrollPanel) {
		this.scrollPanel = scrollPanel;
		getScrollPanel().setLayout(null);
		getScrollPanel().setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		getScrollPanel().setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		getScrollPanel().setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH, SCROLL_PANEL_HEIGHT));
		getScrollPanel().setLayout(new ScrollPaneLayout());
		getScrollPanel().setVisible(false);
	}
	
	public JButton getConcordanceButton() {
		return ConcordanceButton;
	}

	public void setConcordanceButton(JButton concordanceButton) {
		ConcordanceButton = concordanceButton;
	}
	
	public JPanel getjPanel_1() {
		return jPanel_1;
	}


	public void setjPanel_1(JPanel jPanel_1) {
		this.jPanel_1 = jPanel_1;
		getjPanel_1().setLayout(new FlowLayout(FlowLayout.LEFT));
		getjPanel_1().setVisible(true);
		getjPanel_1().setBackground(Color.GRAY);
		getjPanel_1().setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT/2));
	}
	
	public JPanel getM_buttonPanel() {
		return m_buttonPanel;
	}


	public void setM_buttonPanel(JPanel m_buttonPanel) {
		this.m_buttonPanel = m_buttonPanel;
		getM_buttonPanel().setLayout(new FlowLayout(FlowLayout.LEFT));
		getM_buttonPanel().setVisible(true);
		getM_buttonPanel().setBackground(Color.WHITE);
		getM_buttonPanel().setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT/2));
	}
	
	public List<Version> GetVersionList(){
		DataReader dataReader=new DataReader();
		m_VersionList=dataReader.readAllFile();
		return m_VersionList;
	}

	public ConcordancePanel getConcordancePanel() {
		return concordancePanel;
	}


	public void setConcordancePanel(ConcordancePanel concordancePanel) {
		this.concordancePanel = concordancePanel;
		getConcordancePanel().setLayout(null);
		getConcordancePanel().setBackground(Color.white);
		getConcordancePanel().setVisible(true);
		getConcordancePanel().setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
		
	}

	public static void main(String[] args){
		TranslationVisualization transVis=new TranslationVisualization();
		transVis.setConcordanceFrame(new JFrame("Translation Visualization"));
		transVis.setConcordancePanel(new ConcordancePanel(transVis.GetVersionList()));

		transVis.setScrollPanel(new JScrollPane(transVis.getConcordancePanel()));
		
		transVis.setConcordanceButton(new JButton("Concordances"));
		transVis.getConcordanceButton().setBounds(900, 100, 10, 10);//magic numbers
		
		transVis.setjPanel_1(new JPanel());
		transVis.getjPanel_1().add(transVis.getScrollPanel());
		
		transVis.setM_buttonPanel(new JPanel());
		transVis.getM_buttonPanel().add(transVis.getConcordanceButton());
		transVis.getConcordanceButton().addActionListener(new ActionListener(){
			@Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button pressed");
                transVis.getScrollPanel().setVisible(true);
//                jPanel_1.add(scrollPanel);
                transVis.getConcordanceFrame().revalidate(); 
            }
		});

//		transVis.getConcordanceFrame().setContentPane(transVis.getjPanel_1());
		transVis.getConcordanceFrame().add(transVis.getM_buttonPanel());
		transVis.getConcordanceFrame().add(transVis.getjPanel_1());
//		transVis.getConcordanceFrame().setLayout(new FlowLayout(FlowLayout.LEADING));
//		transVis.getConcordanceFrame().setSize(FRAME_WIDTH, FRAME_HEIGHT);	
		transVis.getConcordanceFrame().setVisible(true);
		transVis.getConcordanceFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
	}


	
	
	

}
