package translationVisualizatonGUI;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;

import translationVisualization.TranslationVisualization;

public class TransViScrollPane extends JScrollPane{

	public TransViScrollPane() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransViScrollPane(Component paramComponent, int paramInt1, int paramInt2) {
		super(paramComponent, paramInt1, paramInt2);
		// TODO Auto-generated constructor stub
	}

	public TransViScrollPane(Component paramComponent) {
		super(paramComponent);
		// TODO Auto-generated constructor stub
	}

	public TransViScrollPane(int paramInt1, int paramInt2) {
		super(paramInt1, paramInt2);
		// TODO Auto-generated constructor stub
	}
	
	public void initialize(){
		int SCROLL_PANEL_WIDTH=420;
		int SCROLL_PANEL_HEIGHT=330;
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH, SCROLL_PANEL_HEIGHT));

		//The layout manager used by JScrollPane. JScrollPaneLayout is responsible for nine components: 
		//a viewport, two scrollbars, a row header, a column header, and four "corner" components.
		setLayout(new ScrollPaneLayout());
		//the m_ScrollPanel only display after concordance button is clicked
		setVisible(false);
	}
	
	public void addComponents(TranslationVisualization transVis){
		transVis.setConcordancePanel(new ConcordancePanel(transVis.getVersionList()));
		this.add(transVis.getConcordancePanel());
	}
	

}
