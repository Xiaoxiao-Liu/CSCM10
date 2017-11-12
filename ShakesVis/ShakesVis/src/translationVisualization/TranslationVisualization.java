package translationVisualization;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;

import translationVisualizatonGUI.ColorLegendPanel;
import translationVisualizatonGUI.ConcordanceButton;
import translationVisualizatonGUI.ConcordancePanel;
import translationVisualizatonGUI.ScrollPanel;
import translationVisualizatonGUI.TextOnOffButton;
import translationVisualizatonGUI.TransViScrollPane;
import translationVisualizatonGUI.TransVislider;
import translationVisualizatonGUI.UserOptionPanel;
import translationVisualizatonGUI.VersionChoosenPanel;
import translationVisualizatonGUI.VisualizationPanel;

public class TranslationVisualization {
	
//	/** the width of the frame */
//	private final static int FRAME_WIDTH=800;
// 	  
//	/** the height of the frame */
//	private final static int FRAME_HEIGHT=800;

	/** the width of the ConcordancePanel */
	private final static int CONCORDANCE_PANEL_WIDTH=3500;
	
	/** the height of the ConcordancePanel */
	private final static int CONCORDANCE_PANEL_HEIGHT=2900;
	
//	/** the width of the ScrollPanel */
//	private final static int SCROLL_PANEL_WIDTH=420;
//	
//	/** the height of the ScrollPanel */
//	private final static int SCROLL_PANEL_HEIGHT=330;
	
	private UserOptionPanel m_UserOptionPanel;

	/** a way to access the JFrame object (Layer 1) */
	private JFrame m_ConcordanceFrame;

	/** a way to access the ConcordancePanel object (Layer 5)*/
	private ConcordancePanel m_ConcordancePanel;

	/** a JPanel object containing visualization components (Layer 2)*/
	private VisualizationPanel m_visuallizationPanel;
	
	private ScrollPanel m_ScrollPanel;

	/** a ColorLegendPanel object containing color legend for frequency colors (Layer 4) */
	private  ColorLegendPanel m_ColorLegendPanel=new ColorLegendPanel();

	/** a way to access the JScrollPane object (Layer 4)*/ 
	private TransViScrollPane m_TransViScrollPane;

	/** a JPanel object containing user-option components (Layer 2)*/
//	private JPanel m_UserOptionPanel;
	
	/** a JPanel object used to select which version displayed (Layer 4)*/
	private JPanel m_VersionSelectionPanel;

	/** a JButton to initiate concordancePanel */
	private ConcordanceButton m_ConcordanceButton;
	
	/** a JToggleButton to turn text on and off */
	private  TextOnOffButton m_TextOnOffButton;


	public TextOnOffButton getM_TextOnOffButton() {
		return m_TextOnOffButton;
	}

	public void setM_TextOnOffButton(TextOnOffButton m_TextOnOffButton) {
		this.m_TextOnOffButton = m_TextOnOffButton;
	}

	private JLabel m_SliderLabel;
	
	
	public JLabel getM_SliderLabel() {
		return m_SliderLabel;
	}

	/**
	 * 
	 * @param m_SliderLabel
	 * @param string
	 */
	public void setM_SliderLabel(JLabel m_SliderLabel, String string) {
		this.m_SliderLabel = m_SliderLabel;
		int fontSize=13;
//		m_SliderLabel.setPreferredSize(new Dimension(10,10));
		m_SliderLabel.setText(string);
		m_SliderLabel.setForeground(Color.darkGray);
		m_SliderLabel.setFont(new Font("Serif", Font.BOLD, fontSize));
	}

	/** a JSlider to zoom in and out concordancePanel */
//	private JSlider m_ConcordanceSlider;
	
	/** a JSlider to zoom in and out concordancePanel */
//	private TransVislider m_ConcordanceSlider;
	
	private TransVislider m_TransViSlider;
	

	public TransVislider getM_TransViSlider() {
		return m_TransViSlider;
	}

	public void setM_TransViSlider(TransVislider m_TransViSlider) {
		this.m_TransViSlider = m_TransViSlider;
	}

	/** a JSlider to zoom in and out scrollPane */
//	private TransVislider m_ScrollPaneSlider;
	
	/** an arrayList to pass version list to other classes */
	private List<Version> m_VersionList=new ArrayList<Version>();
	

	private DataReader dataReader;
	
	/**  */
	private static double m_scaleValue=100;

	
	/** a JCheckBox to show version names */
	private JCheckBox versionMenu;
	
	private VersionChoosenPanel versionChoosingPanel;
	
	public DataReader getDataReader() {
		return dataReader;
	}

	public void setDataReader(DataReader dataReader) throws Exception {
		this.dataReader = dataReader;
		setM_VersionList(dataReader.readAllFile());
//		dataReader.googleAPIAuth(m_VersionList.get(1).getM_WordsList());
		
	}

	/**
	 * Use this method to access m_VersionList
	 * @return m_VersionList
	 * @throws Exception
	 */
	public List<Version> getVersionList(){
		return m_VersionList;
	}

	public void setM_VersionList(List<Version> m_VersionList) {
		this.m_VersionList = m_VersionList;
	}

	public VersionChoosenPanel getVersionChoosingPanel() {
		return versionChoosingPanel;
	}

	public void setVersionChoosingPanel(VersionChoosenPanel versionChoosingPanel, ConcordancePanel concordancePanel, List<String> versionNames) {
		this.versionChoosingPanel = versionChoosingPanel;
//		versionChoosingPanel.addVersions(versionNames, concordancePanel);
//		versionChoosingPanel.setLayout(new GridLayout(17,1)); //magic number
//		//
//		versionChoosingPanel.setBackground(Color.WHITE);
//		versionChoosingPanel.setPreferredSize(new Dimension(100, 400)); //magic number
//		versionChoosingPanel.setVisible(true);
	}

	//accessor methods


	public JCheckBox getVersionMenu() {
		return versionMenu;
	}

	/**
	 *  Use this method to access m_ConcordanceFrame
	 * @return m_ConcordanceFrame
	 */
	public JFrame getConcordanceFrame() {
		return m_ConcordanceFrame;
	}

	/**
	 * Use this method to create and set m_ConcordanceFrame
	 * @param concordanceFrame
	 */
	public void setConcordanceFrame(JFrame concordanceFrame) {
		this.m_ConcordanceFrame = concordanceFrame;
//		getConcordanceFrame().setSize(FRAME_WIDTH, FRAME_HEIGHT);	
	}
	

	public ScrollPanel getM_ScrollPanel() {
		return m_ScrollPanel;
	}

	public void setM_ScrollPanel(ScrollPanel m_ScrollPanel) {
		this.m_ScrollPanel = m_ScrollPanel;
//		m_ScrollPanel.setBackground(Color.white);
//		m_ScrollPanel.setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH, SCROLL_PANEL_HEIGHT));
		
	}

	/**
	 * Use this method to access m_ScrollPanel
	 * @return m_ScrollPanel
	 */
	public TransViScrollPane getM_TransViScrollPane() {
		return m_TransViScrollPane;
	}

	/**
	 * Use this method to create and set m_ScrollPanel
	 * @param scrollPanel
	 */
	public void setM_TransViScrollPane(TransViScrollPane transViScrollPane) {
		this.m_TransViScrollPane = transViScrollPane;
		//we need both horizontal and vertical scroll bars
//		getScrollPane().setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
//		getScrollPane().setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
////		getScrollPane().setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH, SCROLL_PANEL_HEIGHT));
//
//		//The layout manager used by JScrollPane. JScrollPaneLayout is responsible for nine components: 
//		//a viewport, two scrollbars, a row header, a column header, and four "corner" components.
//		getScrollPane().setLayout(new ScrollPaneLayout());
//		//the m_ScrollPanel only display after concordance button is clicked
//		getScrollPane().setVisible(false); 
	}
	
	/**
	 * Use this method to access m_ConcordanceButton
	 * @return m_ConcordanceButton
	 */
	public ConcordanceButton getConcordanceButton() {
		return m_ConcordanceButton;
	}

	/**
	 * Use this method to create and set m_ConcordanceButton
	 * @param concordanceButton
	 */
	public void setConcordanceButton(ConcordanceButton concordanceButton) {
		m_ConcordanceButton = concordanceButton;
	}

	/**
	 * Use this method to access m_visuallizationPanel
	 * @return m_visuallizationPanel
	 */
	public VisualizationPanel getM_visuallizationPanel() {
		return m_visuallizationPanel;
	}

	/**
	 * Use this method to create and set m_visuallizationPanel
	 * @param m_visuallizationPanel
	 */
	public void setM_visuallizationPanel(VisualizationPanel m_visuallizationPanel) {
		this.m_visuallizationPanel = m_visuallizationPanel;
//		m_visuallizationPanel.setBorder(BorderFactory.createTitledBorder(" --Visualization-- "));
//		getM_visuallizationPanel().setVisible(true);
//		getM_visuallizationPanel().setBackground(Color.WHITE);
		
		
	}

	/**
	 * Use this method to access m_buttonPanel
	 * @return m_buttonPanel
	 */
	public UserOptionPanel getM_UserOptionPanel() {
		return m_UserOptionPanel;
	}

	/**
	 * Use this method to create and set m_buttonPanel
	 * @param buttonPanel
	 */
	public void setM_UserOptionPanel(UserOptionPanel buttonPanel) {
		this.m_UserOptionPanel = buttonPanel;
//		m_UserOptionPanel.setBorder(BorderFactory.createTitledBorder(" User Options "));
//		getM_UserOptionPanel().setVisible(true);
//		getM_UserOptionPanel().setBackground(Color.WHITE);
	}
	
	public JPanel getM_VersionSelectionPanel() {
		return m_VersionSelectionPanel;
	}

	public void setM_VersionSelectionPanel(JPanel m_VersionSelectionPanel) {
		this.m_VersionSelectionPanel = m_VersionSelectionPanel;
	}

	/**
	 * Use this method to access m_ColorLegendPanel
	 * @return m_ColorLegendPanel
	 */
	public ColorLegendPanel getM_ColorLegendPanel() {
		return m_ColorLegendPanel;
	}

	/**
	 * Use this method to create and set m_ColorLegendPanel
	 * @param colorLegendPanel
	 * @param dataReader
	 */
	public void setM_ColorLegendPanel(ColorLegendPanel colorLegendPanel, DataReader dataReader) {
		this.m_ColorLegendPanel = colorLegendPanel;
		m_ColorLegendPanel.setM_ConcordancePanel(getConcordancePanel());
		m_ColorLegendPanel.setColorLegend(dataReader.sortColorIndex(dataReader.getM_frequencyColorIndex()), dataReader.getM_FrequencyIndex());

		
	}
	
//	public void initialJslider(){
//		int min=50; //minimum value
//		int max=200; //maximum value
//		int initialVar=100; //initial value
//	}

	/**
	 * Use this method to access m_ConcordancePanel
	 * @return m_ConcordancePanel
	 */
	public ConcordancePanel getConcordancePanel() {
		return m_ConcordancePanel;
	}

	/**
	 * Use this method to create and set m_ConcordancePanel
	 * @param concordancePanel
	 */
	public void setConcordancePanel(ConcordancePanel concordancePanel) {
		this.m_ConcordancePanel = concordancePanel;
		getConcordancePanel().setBackground(Color.white);
		getConcordancePanel().setVisible(true);
		getConcordancePanel().setPreferredSize(new Dimension(CONCORDANCE_PANEL_WIDTH,CONCORDANCE_PANEL_HEIGHT));
		
	}

	public static void main(String[] args) throws Exception{
		 
		TranslationVisualization transVis=new TranslationVisualization();
		transVis.setDataReader(new DataReader());
		
		
		//layer 2 - Visualization Panel
		transVis.setM_visuallizationPanel(new VisualizationPanel());
		transVis.getM_visuallizationPanel().initialize();
		transVis.getM_visuallizationPanel().addComponents(transVis);

		
		//layer 2 - User-option Panel
		transVis.setM_UserOptionPanel(new UserOptionPanel());
		transVis.getM_UserOptionPanel().initialize();
		transVis.getM_UserOptionPanel().addComponents(transVis);
		
			
		//layer 1 - Concordance Frame
		transVis.setConcordanceFrame(new JFrame("Translation Visualization"));
		
		transVis.getConcordanceFrame().setVisible(true);
		transVis.getConcordanceFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		transVis.getConcordanceFrame().setLocationRelativeTo(null);
		transVis.getConcordanceFrame().setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
		GridBagLayout layout = new GridBagLayout();
		transVis.getConcordanceFrame().setLayout(layout);
		transVis.getConcordanceFrame().add(transVis.getM_UserOptionPanel(),transVis.getM_UserOptionPanel().getM_Constraint());
		transVis.getConcordanceFrame().add(transVis.getM_visuallizationPanel(),transVis.getM_visuallizationPanel().getM_Constraint());
	}
	
	public void initializeConcordanceFrame(){
//		this.setConcordanceFrame(concordanceFrame);
	}
}