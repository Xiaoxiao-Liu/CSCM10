package translationVisualizatonGUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import translationVisualization.TranslationVisualization;

public class VisualizationPanel extends JPanel{

	private GridBagConstraints m_Constraint=new GridBagConstraints();

	
	public GridBagConstraints getM_Constraint() {
		return m_Constraint;
	}
	
	public void setVisPanelConstraint(){
		m_Constraint =new GridBagConstraints();
		m_Constraint.gridwidth=5;
		m_Constraint.weightx=1;
		m_Constraint.weighty=1;
		m_Constraint.fill = GridBagConstraints.BOTH;

//		getM_Constraint().gridx = 1;
//		getM_Constraint().gridy = 2;
//		//Insets(int top, int right, int bottom, int left)
//		int top=0;
//		int right=0;
//		int bottom=0;
//		int left=0;
//		getM_Constraint().insets = new Insets(top, right, bottom, left);
	}

	public VisualizationPanel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VisualizationPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public VisualizationPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public VisualizationPanel(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}
	
	public void initialize(){
		this.setBorder(BorderFactory.createTitledBorder(" --Visualization-- "));
		this.setVisible(true);
		this.setBackground(Color.white);
		GridBagLayout panelLayout = new GridBagLayout( );
		this.setLayout(panelLayout);
		setVisPanelConstraint();
	}
	
	public void addComponents(TranslationVisualization transVis){
		
		//color legend panel
		transVis.setM_ColorLegendPanel(new ColorLegendPanel(), transVis.getDataReader()); 
		this.add(transVis.getM_ColorLegendPanel(),transVis.getM_ColorLegendPanel().getConstraint());
		
		//scroll panel
		transVis.setM_ScrollPanel(new ScrollPanel());
		transVis.getM_ScrollPanel().initialize();
		transVis.getM_ScrollPanel().addComponents(transVis);
		this.add(transVis.getM_ScrollPanel(),transVis.getM_ScrollPanel().getConstraint());
	}

}
