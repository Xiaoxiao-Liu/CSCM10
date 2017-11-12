package translationVisualizatonGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import translationVisualization.TranslationVisualization;

public class ScrollPanel extends JPanel{
	/** the width of the ScrollPanel */
	private final static int SCROLL_PANEL_WIDTH=420;
	
	/** the height of the ScrollPanel */
	private final static int SCROLL_PANEL_HEIGHT=330;

	public ScrollPanel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ScrollPanel(boolean paramBoolean) {
		super(paramBoolean);
		// TODO Auto-generated constructor stub
	}

	public ScrollPanel(LayoutManager paramLayoutManager, boolean paramBoolean) {
		super(paramLayoutManager, paramBoolean);
		// TODO Auto-generated constructor stub
	}

	public ScrollPanel(LayoutManager paramLayoutManager) {
		super(paramLayoutManager);
		// TODO Auto-generated constructor stub
	}
	
	public void initialize(){
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH, SCROLL_PANEL_HEIGHT));
	}
	
	public void addComponents(TranslationVisualization transVis){
		transVis.setM_TransViScrollPane(new TransViScrollPane(transVis.getConcordancePanel()));	
		transVis.getM_TransViScrollPane().initialize(); 
		transVis.getM_TransViScrollPane().addComponents(transVis);
		add(transVis.getM_TransViScrollPane());
	}
	
	public GridBagConstraints getConstraint() {
		GridBagConstraints constraint =new GridBagConstraints();
		constraint.gridx = 2;
		constraint.gridy = 1;
		 constraint.weightx=5;
	     constraint.weighty=0;
		constraint.fill = GridBagConstraints.BOTH;
		//Insets(int top, int right, int bottom, int left)
		int top=0;
		int right=0;
		int bottom=0;
		int left=0;
		constraint.insets = new Insets(top, right, bottom, left);
		return constraint;
	}

}
