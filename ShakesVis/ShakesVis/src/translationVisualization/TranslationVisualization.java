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

	private final static int CONCORDANCE_PANEL_WIDTH=2500;
	private final static int CONCORDANCE_PANEL_HEIGHT=900;
	
	private final static int SCROLL_PANEL_WIDTH=500;
	private final static int SCROLL_PANEL_HEIGHT=300;
	
	private final static int VISUALIZATION_PANEL_WIDTH=600;
	private final static int VISUALIZATION_PANEL_HEIGHT=800;
	
	private final static int BUTTON_PANEL_WIDTH=500;
	private final static int BUTTON_PANEL_HEIGHT=300;
	
	private JFrame concordanceFrame;
	
	private JScrollPane scrollPanel;

	private List<Version> m_VersionList=new ArrayList<Version>();

	private ConcordancePanel concordancePanel;
	
	private JButton ConcordanceButton;
	
	private JPanel m_visuallizationPanel;

	private JPanel m_buttonPanel;



	public JFrame getConcordanceFrame() {
		return concordanceFrame;
	}

	public void setConcordanceFrame(JFrame concordanceFrame) {
		this.concordanceFrame = concordanceFrame;
		getConcordanceFrame().getContentPane().setBackground(Color.BLACK);
		getConcordanceFrame().setLayout(new BorderLayout());
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
	
	public JPanel getM_visuallizationPanel() {
		return m_visuallizationPanel;
	}

	public void setM_visuallizationPanel(JPanel m_visuallizationPanel) {
		this.m_visuallizationPanel = m_visuallizationPanel;
		getM_visuallizationPanel().setLayout(new FlowLayout());
		getM_visuallizationPanel().setVisible(true);
		getM_visuallizationPanel().setBackground(Color.WHITE);
		getM_visuallizationPanel().setPreferredSize(new Dimension(VISUALIZATION_PANEL_WIDTH, VISUALIZATION_PANEL_HEIGHT));
	}

	public JPanel getM_buttonPanel() {
		return m_buttonPanel;
	}


	public void setM_buttonPanel(JPanel m_buttonPanel) {
		this.m_buttonPanel = m_buttonPanel;
//		getM_buttonPanel().setLayout(new FlowLayout());
		getM_buttonPanel().setVisible(true);
		getM_buttonPanel().setBackground(Color.WHITE);
		getM_buttonPanel().setPreferredSize(new Dimension(BUTTON_PANEL_WIDTH, BUTTON_PANEL_HEIGHT ));
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
		getConcordancePanel().setPreferredSize(new Dimension(CONCORDANCE_PANEL_WIDTH,CONCORDANCE_PANEL_HEIGHT));
		
	}

	public static void main(String[] args){
		TranslationVisualization transVis=new TranslationVisualization();
		transVis.setConcordanceFrame(new JFrame("Translation Visualization"));
		transVis.setConcordancePanel(new ConcordancePanel(transVis.GetVersionList()));
		transVis.setScrollPanel(new JScrollPane(transVis.getConcordancePanel()));
		transVis.setConcordanceButton(new JButton("Concordances"));
		
		
		transVis.setM_visuallizationPanel(new JPanel());
		transVis.getM_visuallizationPanel().add(transVis.getScrollPanel());
		
		transVis.setM_buttonPanel(new JPanel());
		transVis.getM_buttonPanel().add(transVis.getConcordanceButton());
		transVis.getConcordanceButton().addActionListener(new ActionListener(){
			@Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button pressed");
                transVis.getScrollPanel().setVisible(true);
                transVis.getConcordanceFrame().revalidate(); 
            }
		});

//		transVis.getConcordanceFrame().setContentPane(transVis.getM_visuallizationPanel());
		transVis.getConcordanceFrame().add("West", transVis.getM_buttonPanel());
		transVis.getConcordanceFrame().add("Center",transVis.getM_visuallizationPanel());
		transVis.getConcordanceFrame().setVisible(true);
		transVis.getConcordanceFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		transVis.getConcordanceFrame().setLocationRelativeTo(null);
		transVis.getConcordanceFrame().setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
	}
}
