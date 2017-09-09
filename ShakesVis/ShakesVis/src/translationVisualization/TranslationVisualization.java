package translationVisualization;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
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
	
	private JFrame concordanceFrame;
	
	private JScrollPane scrollPanel;

	private List<Version> m_VersionList=new ArrayList<Version>();

	private ConcordancePanel concordancePanel;
	
	private JButton ConcordanceButton;
	
	private JPanel m_visuallizationPanel;

	private JPanel m_buttonPanel;
	
//	private JScrollBar m_scrollBar;

	public JFrame getConcordanceFrame() {
		return concordanceFrame;
	}

	public void setConcordanceFrame(JFrame concordanceFrame) {
		this.concordanceFrame = concordanceFrame;
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
		getM_visuallizationPanel().setVisible(true);
		getM_visuallizationPanel().setBackground(Color.WHITE);
	}

	public JPanel getM_buttonPanel() {
		return m_buttonPanel;
	}

	public void setM_buttonPanel(JPanel m_buttonPanel) {
		this.m_buttonPanel = m_buttonPanel;
		getM_buttonPanel().setVisible(true);
		getM_buttonPanel().setBackground(Color.WHITE);
	}
	
	public List<Version> GetVersionList() throws Exception{
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

	public static void main(String[] args) throws Exception{
		JScrollBar m_scrollBar=new JScrollBar();
		TranslationVisualization transVis=new TranslationVisualization();
		transVis.setConcordanceFrame(new JFrame("Translation Visualization"));
		transVis.setConcordancePanel(new ConcordancePanel(transVis.GetVersionList()));
		transVis.setScrollPanel(new JScrollPane(transVis.getConcordancePanel()));
		transVis.setConcordanceButton(new JButton("Concordances"));
		
		
		transVis.setM_visuallizationPanel(new JPanel());
		transVis.getM_visuallizationPanel().add(transVis.getScrollPanel());
		transVis.getM_visuallizationPanel().add(m_scrollBar);
		
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
		GridBagLayout layout = new GridBagLayout();
		transVis.getConcordanceFrame().setLayout(layout);
		GridBagConstraints s=new GridBagConstraints();
		s.fill=GridBagConstraints.BOTH;
		s.gridwidth=1;
		s.weightx=0;
		s.weighty=1;
		layout.setConstraints(transVis.getM_buttonPanel(), s);
		s.gridwidth=5;
		s.weightx=1;
		s.weighty=1;
		layout.setConstraints(transVis.getM_visuallizationPanel(), s);

		transVis.getConcordanceFrame().add(transVis.getM_buttonPanel());
		transVis.getConcordanceFrame().add(transVis.getM_visuallizationPanel());
		transVis.getConcordanceFrame().setVisible(true);
		transVis.getConcordanceFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		transVis.getConcordanceFrame().setLocationRelativeTo(null);
		transVis.getConcordanceFrame().setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
		
	}
}
