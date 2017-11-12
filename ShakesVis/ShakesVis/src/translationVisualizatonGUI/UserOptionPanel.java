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
		this.setLayout(new GridBagLayout());
//		this.setUserOptionConstraint();
//		JLabel sliderlabel=new JLabel("Heloo");
	}
	
	public GridBagConstraints userOptionConstraint(){
		GridBagConstraints constraint =new GridBagConstraints();
//		m_Constraint.fill = GridBagConstraints.BOTH;
		constraint.gridwidth=1;
		constraint.weightx=0;
		constraint.weighty=0;
//		
//		m_Constraint.gridx = 1;
//		m_Constraint.gridy = 1;
		constraint.fill = GridBagConstraints.BOTH;
		//Insets(int top, int right, int bottom, int left)
		return constraint;
	}
	
	public void setLabelConstraint(int gridy, int bottom) {
		m_Constraint =new GridBagConstraints();
		m_Constraint.gridx = 2;
		m_Constraint.gridy = gridy;
		m_Constraint.fill = GridBagConstraints.BOTH;
		int top=9;
		int left=5;
		int right=0;
		m_Constraint.insets = new Insets(top, left, bottom, right);
	}
	
	public void setM_SliderLabel(JLabel sliderLabel, String string) {
		this.m_SliderLabel = sliderLabel;
		int fontSize=13;
		m_SliderLabel.setText(string);
		m_SliderLabel.setForeground(Color.darkGray);
		m_SliderLabel.setFont(new Font("Serif", Font.BOLD, fontSize));
	
	}
	
	public void addComponents(TranslationVisualization transVis){
		
		//concordance button
		transVis.setConcordanceButton(new ConcordanceButton("Concordances"));
		transVis.getConcordanceButton().initialize(transVis);
		this.add(transVis.getConcordanceButton(),transVis.getConcordanceButton().getConstraint());
       
        //text on and off button
		transVis.setM_TextOnOffButton(new TextOnOffButton());
        this.add(transVis.getM_TextOnOffButton(),transVis.getM_TextOnOffButton().getConstraint());
        transVis.getM_TextOnOffButton().initialize(transVis.getConcordancePanel());
        
        //concordance slider
        transVis.setM_TransViSlider(new TransVislider());
		transVis.getM_TransViSlider().concordanceSlider(transVis, transVis.getConcordancePanel());
        this.add(transVis.getM_TransViSlider(),transVis.getM_TransViSlider().getM_Constraint());
        //scroll panel slider
        transVis.setM_TransViSlider(new TransVislider());
        transVis.getM_TransViSlider().scrollPaneSlider(transVis);
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
	

	
	
	
	
	

}
