package translationVisualizatonGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import translationVisualization.TranslationVisualization;

public class UserOptionPanel extends JPanel{
	
	private JLabel m_SliderLabel;
	
	private VersionChoosenPanel versionChoosingPanel;

	
	public VersionChoosenPanel getVersionChoosingPanel() {
		return versionChoosingPanel;
	}

	public void setVersionChoosingPanel(VersionChoosenPanel versionChoosingPanel) {
		this.versionChoosingPanel = versionChoosingPanel;
	}

	private GridBagConstraints m_Constraint =new GridBagConstraints();

	public GridBagConstraints getM_Constraint() {
		return m_Constraint;
	}

	public JLabel getM_SliderLabel() {
		return m_SliderLabel;
	}

	public void setM_SliderLabel(JLabel sliderLabel, String string) {
		this.m_SliderLabel = sliderLabel;
		int fontSize=13;
		m_SliderLabel.setText(string);
		m_SliderLabel.setForeground(Color.darkGray);
		m_SliderLabel.setFont(new Font("Serif", Font.BOLD, fontSize));
	
	}

	public UserOptionPanel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserOptionPanel(boolean arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public UserOptionPanel(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public UserOptionPanel(LayoutManager arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	public void initialize(){
		this.setBorder(BorderFactory.createTitledBorder(" User Options "));
		this.setVisible(true);
		this.setBackground(Color.white);
		GridBagLayout userOptionPaneLayout = new GridBagLayout( );
		this.setLayout(userOptionPaneLayout);
//		JLabel sliderlabel=new JLabel("Heloo");
	}
	
	//get userOptionPanelConstraint
	public GridBagConstraints getConstraint() {
		GridBagConstraints constraint =new GridBagConstraints();
//		constraint.gridx = 1;
//		constraint.gridy = 2;
		constraint.fill = GridBagConstraints.BOTH;
		constraint.gridwidth=1;
		constraint.weightx=0;
		constraint.weighty=0;
		//Insets(int top, int right, int bottom, int left)
//		int top=0;
//		int right=30;
//		int bottom=10;
//		int left=30;
//		constraint.insets = new Insets(top, right, bottom, left);
		return constraint;
	}
	
	public void addComponents(TranslationVisualization transVis){
		
        this.add(transVis.getConcordanceButton(),transVis.getConcordanceButton().getConstraint());
		
        this.add(transVis.getM_TextOnOffButton(),transVis.getM_TextOnOffButton().getConstraint());

        transVis.setM_TransViSlider(new TransVislider());
		transVis.getM_TransViSlider().concordanceSlider(transVis.getConcordancePanel(), transVis.getConcordanceFrame());
        this.add(transVis.getM_TransViSlider(),transVis.getM_TransViSlider().getM_Constraint());

        transVis.setM_TransViSlider(new TransVislider());
        transVis.getM_TransViSlider().scrollPaneSlider(transVis.getScrollPane(), transVis.getConcordanceFrame());
        this.add(transVis.getM_TransViSlider(), transVis.getM_TransViSlider().getM_Constraint());

        
		//concordanceSlider label
		this.setM_SliderLabel(new JLabel(), "Concordance");
		int gridy=3;
		int bottomValue=0;
		setLabelConstraint(gridy,bottomValue);
		this.add(getM_SliderLabel(), getM_Constraint());
		
		//scrollpaneSlider label
		this.setM_SliderLabel(new JLabel(), "Panel");
		gridy=4;
		bottomValue=50;
		setLabelConstraint(gridy,bottomValue);
		this.add(getM_SliderLabel(), getM_Constraint());
		
		//version Choosen Panel
		this.setVersionChoosingPanel(new VersionChoosenPanel());
		getVersionChoosingPanel().initialize(transVis.getConcordancePanel(), transVis.getDataReader().getM_VersionNameList());
		this.add(getVersionChoosingPanel(), getVersionChoosingPanel().getConstraint());
		
	}
	
public void setLabelConstraint(int gridy, int bottom) {
		
		m_Constraint =new GridBagConstraints();
//		constraint.gridx = 1;
//		constraint.gridy = 3;
		m_Constraint.gridx = 2;
		m_Constraint.gridy = gridy;
//	     constraint.gridwidth=1;
//		constraint.weightx=1;
//		constraint.weighty=1;
		m_Constraint.fill = GridBagConstraints.BOTH;
		int top=9;
		int left=5;
//		int bottom=10;
		int right=0;
		m_Constraint.insets = new Insets(top, left, bottom, right);
	}
	
	
	
	
	

}
