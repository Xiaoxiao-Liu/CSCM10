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
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import TranslationVisualizatonGUI.ColorLegendPanel;
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
	
	private JSlider m_Slider;

	private JPanel m_visuallizationPanel;

	private JPanel m_buttonPanel;
	
	private static ColorLegendPanel m_colorLegend=new ColorLegendPanel();
	
	static int m_scaleValue=1;
	
//	private JScrollBar m_scrollBar;

	public JSlider getM_Slider() {
		return m_Slider;
	}

	public void setM_Slider(JSlider m_Slider) {
		this.m_Slider = m_Slider;
		m_Slider.setMajorTickSpacing(10);
//		m_Slider.setPaintTicks(true);
		m_Slider.setPaintLabels(true);
		m_Slider.setBackground(Color.WHITE);
	}

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
//		int scaleValue=m_scaleValue;
//		scaleValue=( scaleValue >= 1 ? scaleValue : 1 );
		getScrollPanel().setLayout(null);
		getScrollPanel().setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		getScrollPanel().setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		getScrollPanel().setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH+m_scaleValue*4, SCROLL_PANEL_HEIGHT+m_scaleValue*4));//magic number
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
		m_colorLegend.colorLegend(dataReader.sortColorIndex(dataReader.getM_frequencyColorIndex()));
		
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
		
		TranslationVisualization transVis=new TranslationVisualization();
		transVis.setConcordanceFrame(new JFrame("Translation Visualization"));
		transVis.setConcordancePanel(new ConcordancePanel(transVis.GetVersionList()));
		transVis.setScrollPanel(new JScrollPane(transVis.getConcordancePanel()));
		transVis.setConcordanceButton(new JButton("Concordances"));
		
		transVis.setM_Slider(new JSlider( SwingConstants.HORIZONTAL, 10, 100, 10));//magic number
		
		transVis.setM_visuallizationPanel(new JPanel());
		transVis.getM_visuallizationPanel().add(transVis.getScrollPanel());
		transVis.getM_visuallizationPanel().add(m_colorLegend);
		transVis.setM_buttonPanel(new JPanel());
		transVis.getM_buttonPanel().add(transVis.getConcordanceButton());
		transVis.getM_buttonPanel().add(transVis.getM_Slider());
		
		transVis.getM_Slider().addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent event) {
				m_scaleValue=transVis.getM_Slider().getValue();
				System.out.println(m_scaleValue);
				transVis.getConcordancePanel().setScaleValue(m_scaleValue);//magic number
//				transVis.getConcordancePanel().repaint();
				transVis.setScrollPanel(transVis.getScrollPanel());
				transVis.getScrollPanel().setVisible(true);
				transVis.getConcordanceFrame().revalidate(); 
			}
		});
		
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