package translationVisualization;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import translationVisualizatonGUI.ColorLegendPanel;
import translationVisualizatonGUI.ConcordancePanel;
import translationVisualizatonGUI.VersionChoosenPanel;

public class TranslationVisualization {
	// Hello, World
	// /** the width of the frame */
	// private final static int FRAME_WIDTH=800;
	//
	// /** the height of the frame */
	// private final static int FRAME_HEIGHT=800;

	/** the width of the ConcordancePanel */
	private final static int CONCORDANCE_PANEL_WIDTH = 3500;

	/** the height of the ConcordancePanel */
	private final static int CONCORDANCE_PANEL_HEIGHT = 2900;

	/** the width of the ScrollPanel */
	private final static int SCROLL_PANEL_WIDTH = 420;

	/** the height of the ScrollPanel */
	private final static int SCROLL_PANEL_HEIGHT = 330;

	private String frameTitle="Translation Visualization";
	
	public String getFrameTitle() {
		return frameTitle;
	}

	public void setFrameTitle(String frameTitle) {
		this.frameTitle = frameTitle;
	}

	/** a way to access the JFrame object (Layer 1) */
	private JFrame m_ConcordanceFrame;

	/** a way to access the ConcordancePanel object (Layer 2) */
	private ConcordancePanel m_ConcordancePanel;

	/** a JPanel object containing visualization components (Layer 3) */
	private JPanel m_visuallizationPanel;

	private JPanel m_ScrollPanel;

	/**
	 * a ColorLegendPanel object containing color legend for frequency colors
	 * (Layer 4)
	 */
	private ColorLegendPanel m_ColorLegendPanel = new ColorLegendPanel();

	/** a way to access the JScrollPane object (Layer 4) */
	private JScrollPane m_ScrollPane;

	/** a JPanel object containing user-option components (Layer 3) */
	private JPanel m_UserOptionPanel;

	/** a JPanel object used to select which version displayed (Layer 4) */
	private JPanel m_VersionSelectionPanel;

	/** a JButton to initiate concordancePanel */
	private JButton m_ConcordanceButton;

	/** a JSlider to zoom in and out concordancePanel */
	private JSlider m_ConcordanceSlider;

	/** a JSlider to zoom in and out scrollPane */
	private JSlider m_ScrollPaneSlider;

	/** an arrayList to pass version list to other classes */
	private List<Version> m_VersionList = new ArrayList<Version>();

	private DataReader dataReader;

	/**  */
	private static double m_scaleValue = 100;

	/** a JCheckBox to show version names */
	private JCheckBox versionMenu;

	private VersionChoosenPanel versionChoosingPanel;

	/** a JToggleButton to turn text on and off */
	private JToggleButton m_TextOnOffButton;

	private JLabel m_SliderLabel;

	/** a JButton to initiate concordancePanel */
	private JButton m_TfIdfButton;
	
	private JButton m_lemmaButton;

	public JButton getM_lemmaButton() {
		return m_lemmaButton;
	}

	public void setM_lemmaButton(JButton m_lemmaButton) {
		this.m_lemmaButton = m_lemmaButton;
	}

	private GridBagLayout m_PanelLayout = new GridBagLayout();

	private GridBagConstraints m_Constraint = new GridBagConstraints();

	public JButton getM_TfIdfButton() {
		return m_TfIdfButton;
	}

	public void setM_TfIdfButton(JButton m_TfIdfButton) {
		this.m_TfIdfButton = m_TfIdfButton;
	}

	public JToggleButton getM_TextOnOffButton() {
		return m_TextOnOffButton;
	}

	public void setM_TextOnOffButton(JToggleButton m_TextOnOffButton) {
		this.m_TextOnOffButton = m_TextOnOffButton;
	}

	public JLabel getM_SliderLabel() {
		return m_SliderLabel;
	}

	/**
	 * @param m_SliderLabel
	 * @param string
	 */
	public void setM_SliderLabel(JLabel m_SliderLabel, String string) {
		this.m_SliderLabel = m_SliderLabel;
		int fontSize = 13;
		// m_SliderLabel.setPreferredSize(new Dimension(10,10));
		m_SliderLabel.setText(string);
		m_SliderLabel.setForeground(Color.darkGray);
		m_SliderLabel.setFont(new Font("Serif", Font.BOLD, fontSize));
	}

	public DataReader getDataReader() {
		return dataReader;
	}

	public void setDataReader(DataReader dataReader) throws Exception {
		this.dataReader = dataReader;
		
	}

	/**
	 * Use this method to access m_VersionList
	 * 
	 * @return m_VersionList
	 * @throws Exception
	 */
	public List<Version> getVersionList() {
		return m_VersionList;
	}

	public void setM_VersionList(List<Version> m_VersionList) {
		this.m_VersionList = m_VersionList;
	}

	public VersionChoosenPanel getVersionChoosingPanel() {
		return versionChoosingPanel;
	}

	public void setVersionChoosingPanel(VersionChoosenPanel versionChoosingPanel, ConcordancePanel concordancePanel,
			List<String> versionNames) {
		this.versionChoosingPanel = versionChoosingPanel;
		versionChoosingPanel.addVersions(versionNames, concordancePanel);
		versionChoosingPanel.setLayout(new GridLayout(17, 1)); // magic number
		//
		versionChoosingPanel.setBackground(Color.WHITE);
		versionChoosingPanel.setPreferredSize(new Dimension(100, 400)); // magic
																		// number
		versionChoosingPanel.setVisible(true);
	}

	// accessor methods
	/**
	 * Use this method to access m_ConcordanceSlider
	 * 
	 * @return m_ConcordanceSlider
	 */
	public JSlider getM_ConcordanceSlider() {
		return m_ConcordanceSlider;
	}

	/**
	 * Use this method to create and set m_ConcordanceSlider
	 * 
	 * @param m_Slider
	 */
	public void setM_ConcordanceSlider() {
		// public void setM_ConcordanceSlider(JSlider m_Slider) {

		int min = 50; // minimum value
		int max = 200; // maximum value
		int initialVar = 100; // initial value
		// JSlider(int orientation, int min, int max, int value)
		// JSlider(orientation, minimum value, maximum value, and initial value)
		// setM_ConcordanceSlider(new
		// JSlider(SwingConstants.HORIZONTAL, min, max, initialVar));
		m_ConcordanceSlider = new JSlider(SwingConstants.HORIZONTAL, min, max, initialVar);
		// this.m_ConcordanceSlider = m_Slider;
		int fontSize = 11;
		int tickSpacing = 10; // set tick space: 0, 10, 20...100
		m_ConcordanceSlider.setMajorTickSpacing(tickSpacing);
		m_ConcordanceSlider.setFont(new Font("Serif", Font.PLAIN, fontSize));
		m_ConcordanceSlider.setPaintLabels(true);
		m_ConcordanceSlider.setBackground(Color.WHITE);
	}

	public JCheckBox getVersionMenu() {
		return versionMenu;
	}

	/**
	 * Use this method to access m_ConcordanceFrame
	 * 
	 * @return m_ConcordanceFrame
	 */
	public JFrame getConcordanceFrame() {
		return m_ConcordanceFrame;
	}

	/**
	 * Use this method to create and set m_ConcordanceFrame
	 * 
	 * @param concordanceFrame
	 */
	public void setConcordanceFrame(JFrame concordanceFrame) {
		this.m_ConcordanceFrame = concordanceFrame;
		// getConcordanceFrame().setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	public JSlider getM_ScrollPaneSlider() {
		return m_ScrollPaneSlider;
	}

	public void setM_ScrollPaneSlider() {
		int min = 50; // minimum value
		int max = 200; // maximum value
		int initialVar = 100; // initial value
		// JSlider(int orientation, int min, int max, int value)
		// JSlider(orientation, minimum value, maximum value, and initial value)
		// setM_ConcordanceSlider(new
		// JSlider(SwingConstants.HORIZONTAL, min, max, initialVar));
		// setM_ConcordanceSlider();
		// setM_ScrollPaneSlider(new JSlider(SwingConstants.HORIZONTAL,
		// min, max, initialVar));

		this.m_ScrollPaneSlider = new JSlider(SwingConstants.HORIZONTAL, min, max, initialVar);
		int fontSize = 11;
		int tickSpacing = 10; // set tick space: 0, 10, 20...100
		m_ScrollPaneSlider.setMajorTickSpacing(tickSpacing);
		m_ScrollPaneSlider.setFont(new Font("Serif", Font.PLAIN, fontSize));
		m_ScrollPaneSlider.setPaintLabels(true);
		m_ScrollPaneSlider.setBackground(Color.WHITE);
	}

	public JPanel getM_ScrollPanel() {
		return m_ScrollPanel;
	}

	public void setM_ScrollPanel(JPanel m_ScrollPanel) {
		this.m_ScrollPanel = m_ScrollPanel;
		m_ScrollPanel.setBackground(Color.white);
		m_ScrollPanel.setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH, SCROLL_PANEL_HEIGHT));

	}

	/**
	 * Use this method to access m_ScrollPanel
	 * 
	 * @return m_ScrollPanel
	 */
	public JScrollPane getScrollPane() {
		return m_ScrollPane;
	}

	/**
	 * Use this method to create and set m_ScrollPanel
	 * 
	 * @param scrollPanel
	 */
	public void setScrollPane(JScrollPane scrollPanel) {
		this.m_ScrollPane = scrollPanel;
		// we need both horizontal and vertical scroll bars
		getScrollPane().setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		getScrollPane().setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		getScrollPane().setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH, SCROLL_PANEL_HEIGHT));

		// The layout manager used by JScrollPane. JScrollPaneLayout is
		// responsible for nine components:
		// a viewport, two scrollbars, a row header, a column header, and four
		// "corner" components.
		getScrollPane().setLayout(new ScrollPaneLayout());
		// the m_ScrollPanel only display after concordance button is clicked
		getScrollPane().setVisible(true);
	}

	/**
	 * Use this method to access m_ConcordanceButton
	 * 
	 * @return m_ConcordanceButton
	 */
	public JButton getConcordanceButton() {
		return m_ConcordanceButton;
	}

	/**
	 * Use this method to create and set m_ConcordanceButton
	 * 
	 * @param concordanceButton
	 */
	public void setConcordanceButton(JButton concordanceButton) {
		m_ConcordanceButton = concordanceButton;
	}

	/**
	 * Use this method to access m_visuallizationPanel
	 * 
	 * @return m_visuallizationPanel
	 */
	public JPanel getM_visuallizationPanel() {
		return m_visuallizationPanel;
	}

	/**
	 * Use this method to create and set m_visuallizationPanel
	 * 
	 * @param m_visuallizationPanel
	 */
	public void setM_visuallizationPanel(JPanel m_visuallizationPanel) {
		this.m_visuallizationPanel = m_visuallizationPanel;
		m_visuallizationPanel.setBorder(BorderFactory.createTitledBorder(" --Visualization-- "));
		getM_visuallizationPanel().setVisible(true);
		getM_visuallizationPanel().setBackground(Color.WHITE);

	}

	/**
	 * Use this method to access m_buttonPanel
	 * 
	 * @return m_buttonPanel
	 */
	public JPanel getM_UserOptionPanel() {
		return m_UserOptionPanel;
	}

	/**
	 * Use this method to create and set m_buttonPanel
	 * 
	 * @param buttonPanel
	 */
	public void setM_UserOptionPanel(JPanel buttonPanel) {
		this.m_UserOptionPanel = buttonPanel;
		m_UserOptionPanel.setBorder(BorderFactory.createTitledBorder(" User Options "));
		getM_UserOptionPanel().setVisible(true);
		getM_UserOptionPanel().setBackground(Color.WHITE);
	}

	/**
	 * Use this method to access m_ColorLegendPanel
	 * 
	 * @return m_ColorLegendPanel
	 */
	public ColorLegendPanel getM_ColorLegendPanel() {
		return m_ColorLegendPanel;
	}

	/**
	 * Use this method to create and set m_ColorLegendPanel
	 * 
	 * @param colorLegendPanel
	 * @param dataReader
	 */
	public void setM_ColorLegendPanel(ColorLegendPanel colorLegendPanel, DataReader dataReader) {
		this.m_ColorLegendPanel = colorLegendPanel;
		m_ColorLegendPanel.setM_ConcordancePanel(getConcordancePanel());
		m_ColorLegendPanel.setColorLegend(dataReader.sortColorIndex(dataReader.getM_frequencyColorIndex()),
				dataReader.getM_FrequencyIndex());
	}

	/**
	 * Use this method to access m_ConcordancePanel
	 * 
	 * @return m_ConcordancePanel
	 */
	public ConcordancePanel getConcordancePanel() {
		return m_ConcordancePanel;
	}

	/**
	 * Use this method to create and set m_ConcordancePanel
	 * 
	 * @param concordancePanel
	 */
	public void setConcordancePanel(ConcordancePanel concordancePanel) {
		this.m_ConcordancePanel = concordancePanel;
		getConcordancePanel().setBackground(Color.white);
		getConcordancePanel().setVisible(true);
		getConcordancePanel().setPreferredSize(new Dimension(CONCORDANCE_PANEL_WIDTH, CONCORDANCE_PANEL_HEIGHT));

	}

	public void setComponents() throws Exception {
		
		// layer 1 - Concordance Frame
		setConcordanceFrame(new JFrame(getFrameTitle()));
		// layer 2 - Concordance Panel
		setConcordancePanel(new ConcordancePanel(getVersionList()));
		// setConcordancePanel(new ConcordancePanel());
		setM_ColorLegendPanel(new ColorLegendPanel(), getDataReader());

		// layer 3 - Visualization Panel
		setM_visuallizationPanel(new JPanel());

		// layer 3 - User-option Panel
		setM_UserOptionPanel(new JPanel());

		// layer 4 - Scroll Panel

		setScrollPane(new JScrollPane(getConcordancePanel()));
		// getM_visuallizationPanel().add( getScrollPane());

		setM_ScrollPanel(new JPanel());
		getM_ScrollPanel().add(getScrollPane());

		// concordance slider
		setM_ConcordanceSlider();
		setM_ScrollPaneSlider();
		setVersionChoosingPanel(new VersionChoosenPanel(), getConcordancePanel(),
				getDataReader().getM_VersionNameList());

	}

	public GridBagConstraints getM_Constraint() {
		return m_Constraint;
	}

	public void setM_Constraint(int gridx, int gridy, int weightx, int weighty) {
		m_Constraint = new GridBagConstraints();
		getM_Constraint().gridx = gridx;
		getM_Constraint().gridy = gridy;
		getM_Constraint().weightx = weightx;
		getM_Constraint().weighty = weighty;
		getM_Constraint().fill = GridBagConstraints.BOTH;
	}

	public GridBagLayout getM_PanelLayout() {
		return m_PanelLayout;
	}

	public void setM_PanelLayout(GridBagLayout m_PanelLayout) {
		this.m_PanelLayout = m_PanelLayout;
	}

	public void initiateVisPanel() {
		// set layout for visualization panel
		GridBagLayout visPanelLayout = new GridBagLayout();
		GridBagConstraints visPanelConstraint = new GridBagConstraints();
		getM_visuallizationPanel().setLayout(getM_PanelLayout());
		setM_Constraint(1, 1, 1, 1);
		getM_Constraint().insets = new Insets(0, 0, 0, 0);
		getM_visuallizationPanel().add(getM_ColorLegendPanel(), getM_Constraint());
		setM_Constraint(2, 1, 5, 0);
		getM_Constraint().insets = new Insets(0, 0, 0, 0);
		getM_visuallizationPanel().add(getM_ScrollPanel(), getM_Constraint());
	}

	public void initiateTransVis() throws Exception{
		setDataReader(new DataReader());
		setM_VersionList(getDataReader().initiateDataReader());
		setComponents();
		initiateVisPanel();
		initiateUserOptPanel();
//		initiateFrame();
	}
	
	public void initiateUserOptPanel() {

		// set layout for user-option panel
		GridBagLayout userOptionPaneLayout = new GridBagLayout();
		GridBagConstraints useroptionConstraint = new GridBagConstraints();
		initiateVisPanel();
		getM_UserOptionPanel().setLayout(userOptionPaneLayout);

		// ConcordanceButton
		setConcordanceButton(new JButton("Frequency"));
		useroptionConstraint.gridx = 1;
		useroptionConstraint.gridy = 1;
		useroptionConstraint.fill = GridBagConstraints.BOTH;
		useroptionConstraint.insets = new Insets(25, 30, 5, 30);
		getM_UserOptionPanel().add(getConcordanceButton(), useroptionConstraint);
		getConcordanceButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button pressed");
//				getConcordanceFrame().dispose();
				try {
					setFrameTitle("Frequency Visualization");
					setDataReader(new DataReader());
					getDataReader().setInitialFilePath();
					getDataReader().setFrequentValue(true);
					setM_VersionList(getDataReader().initiateDataReader());
					setComponents();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				initiateVisPanel();
				initiateUserOptPanel();
				initiateFrame();
				
				getConcordanceFrame().revalidate();
			}
		});

		// TfIdfButton
		setM_TfIdfButton(new JButton("Tf-Idf"));
		useroptionConstraint.gridx = 1;
		useroptionConstraint.gridy = 2;
		useroptionConstraint.fill = GridBagConstraints.BOTH;
		useroptionConstraint.insets = new Insets(0, 30, 5, 30);
		getM_UserOptionPanel().add(getM_TfIdfButton(), useroptionConstraint);
		getM_TfIdfButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Another button pressed");
//				getConcordanceFrame().dispose();
				try {
					setFrameTitle("Tf-Idf Visualization");
					setDataReader(new DataReader());
					getDataReader().setFrequentValue(false);
					setM_VersionList(getDataReader().initiateDataReader());
					setComponents();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				initiateVisPanel();
				initiateUserOptPanel();
				initiateFrame();
				getScrollPane().setVisible(true);
				getConcordanceFrame().revalidate();
			}
		});

		//German lemma button
		setM_lemmaButton(new JButton("Lemma+Frequency"));
		useroptionConstraint.gridx = 1;
		useroptionConstraint.gridy = 3;
		useroptionConstraint.fill = GridBagConstraints.BOTH;
		useroptionConstraint.insets = new Insets(0, 30, 5, 30);
		getM_UserOptionPanel().add(getM_lemmaButton(), useroptionConstraint);
		getM_lemmaButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Lemma button pressed");
				try {
					setFrameTitle("Lemma Visualization");
					setDataReader(new DataReader());
					getDataReader().setGermanLemmaFilePath();
					getDataReader().setFrequentValue(true);
					setM_VersionList(getDataReader().initiateDataReader());
					setComponents();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				initiateVisPanel();
				initiateUserOptPanel();
				initiateFrame();
				getScrollPane().setVisible(true);
				getConcordanceFrame().revalidate();
				
			}
		});
		
		//German lemma button
				setM_lemmaButton(new JButton("Lemma+TfIdf"));
				useroptionConstraint.gridx = 1;
				useroptionConstraint.gridy = 4;
				useroptionConstraint.fill = GridBagConstraints.BOTH;
				useroptionConstraint.insets = new Insets(0, 30, 5, 30);
				getM_UserOptionPanel().add(getM_lemmaButton(), useroptionConstraint);
				getM_lemmaButton().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("Lemma button pressed");
						try {
							setFrameTitle("Lemma+TfIdf Visualization");
							setDataReader(new DataReader());
							getDataReader().setGermanLemmaFilePath();
							getDataReader().setFrequentValue(false);
							setM_VersionList(getDataReader().initiateDataReader());
							setComponents();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						initiateVisPanel();
						initiateUserOptPanel();
						initiateFrame();
						getScrollPane().setVisible(true);
						getConcordanceFrame().revalidate();
						
					}
				});
		
		// text on off button
		setM_TextOnOffButton(new JToggleButton("Text On"));
		useroptionConstraint.gridx = 1;
		useroptionConstraint.gridy = 5;
		useroptionConstraint.fill = GridBagConstraints.BOTH;
		useroptionConstraint.insets = new Insets(0, 30, 5, 30);
		getM_UserOptionPanel().add(getM_TextOnOffButton(), useroptionConstraint);
		ItemListener itemListener = new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				int state = itemEvent.getStateChange();
				if (state == ItemEvent.SELECTED) {
					getM_TextOnOffButton().setText("Text Off");// change
					getConcordancePanel().setOnAndOff(false);
				} else {
					getM_TextOnOffButton().setText("Text On");
					getConcordancePanel().setOnAndOff(true);
				}
			}
		};
		getM_TextOnOffButton().addItemListener(itemListener);

		// concordanceSlider
		useroptionConstraint.gridx = 1;
		useroptionConstraint.gridy = 6;
		// useroptionConstraint.fill = GridBagConstraints.BOTH;
		// useroptionConstraint.anchor = GridBagConstraints.EAST;
		useroptionConstraint.insets = new Insets(5, 0, 0, 5);
		getM_UserOptionPanel().add(getM_ConcordanceSlider(), useroptionConstraint);
		getM_ConcordanceSlider().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				m_scaleValue = getM_ConcordanceSlider().getValue();
				getConcordancePanel().scaleConcordancePanel((int) m_scaleValue);
				
				getConcordanceFrame().revalidate();
			}
		});

		// concordanceSlider label
		setM_SliderLabel(new JLabel(), "Concordance");
		useroptionConstraint.gridx = 2;
		useroptionConstraint.gridy = 6;
		// useroptionConstraint.anchor = GridBagConstraints.WEST;
		useroptionConstraint.insets = new Insets(2, 5, 0, 0);
		getM_UserOptionPanel().add(getM_SliderLabel(), useroptionConstraint);

		// scrollpaneSlider
		useroptionConstraint.gridx = 1;
		useroptionConstraint.gridy = 7;
		// useroptionConstraint.fill = GridBagConstraints.BOTH;
		useroptionConstraint.insets = new Insets(3, 0, 20, 5);
		getM_ScrollPaneSlider().setToolTipText("Hello");
		getM_UserOptionPanel().add(getM_ScrollPaneSlider(), useroptionConstraint);
		getM_ScrollPaneSlider().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				m_scaleValue = getM_ScrollPaneSlider().getValue();
				m_scaleValue = m_scaleValue / 100.0;
				int widthScale = (int) (SCROLL_PANEL_WIDTH * m_scaleValue);
				int heightScale = (int) (SCROLL_PANEL_HEIGHT * m_scaleValue);
				Dimension dimension = new Dimension(widthScale, heightScale);
				getScrollPane().setPreferredSize(dimension);
				getConcordanceFrame().revalidate();
				getConcordanceFrame().repaint();
			}
		});

		// scrollpaneSlider label
		setM_SliderLabel(new JLabel(), "Panel");
		useroptionConstraint.gridx = 2;
		useroptionConstraint.gridy = 7;
		// useroptionConstraint.anchor = GridBagConstraints.WEST;
		useroptionConstraint.insets = new Insets(2, 5, 20, 0);
		getM_UserOptionPanel().add(getM_SliderLabel(), useroptionConstraint);

		// versionChoosingPanel
		useroptionConstraint.gridx = 1;
		useroptionConstraint.gridy = 9;
		getM_Constraint().weightx = 1;
		useroptionConstraint.fill = GridBagConstraints.BOTH;
		getM_UserOptionPanel().add(getVersionChoosingPanel(), useroptionConstraint);

	}

	public void initiateFrame() {
		// Layer 2 layout
		GridBagLayout layout = new GridBagLayout();
		getConcordanceFrame().setLayout(layout);
		GridBagConstraints s = new GridBagConstraints();
		s.fill = GridBagConstraints.BOTH;
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(getM_UserOptionPanel(), s);
		s.gridwidth = 5;
		s.weightx = 1;
		s.weighty = 1;
		layout.setConstraints(getM_visuallizationPanel(), s);

		getConcordanceFrame().add(getM_UserOptionPanel());
		getConcordanceFrame().add(getM_visuallizationPanel());
		getConcordanceFrame().setVisible(true);
		getConcordanceFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getConcordanceFrame().setLocationRelativeTo(null);
		getConcordanceFrame().setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
	}
}