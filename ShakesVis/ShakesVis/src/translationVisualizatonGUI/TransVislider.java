package translationVisualizatonGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TransVislider extends JSlider{

	private static int orientation=SwingConstants.HORIZONTAL;
	private static int min=50; //minimum value
	private static int max=200; //maximum value
	private static int initialVar=100; //initial value
	private GridBagConstraints m_Constraint;

	
	public GridBagConstraints getM_Constraint() {
		return m_Constraint;
	}

	public TransVislider() {
		super(orientation, min, max, initialVar);
		// TODO Auto-generated constructor stub
	}

	public void initializeSlider(){
		int fontSize=11;
		int tickSpacing=10; //set tick space: 0, 10, 20...100
		Font sliderFont=new Font("Serif", Font.PLAIN, fontSize);
		this.setMajorTickSpacing(tickSpacing);
		this.setPaintTicks(true);
		this.setBackground(Color.WHITE);
		this.setFont(sliderFont);
		this.setPaintLabels(true);
		this.setPaintTicks(true);
		
	}
	
	public void concordanceSlider(ConcordancePanel concordancePanel, JFrame jFrame){
		initializeSlider();
		int gridx=1;
		int gridy=3;
		int top=0;
		int bottom=10;
		setM_Constraint( gridx,  gridy,  top,  bottom);
		this.addChangeListener(new ChangeListener(){
        	public void stateChanged(ChangeEvent event){
        		double m_scaleValue=getValue();
        		concordancePanel.scaleConcordancePanel((int) m_scaleValue);
        		jFrame.revalidate(); 
        	}
        });
	}
	
	public void scrollPaneSlider(JScrollPane scrollPane, JFrame jFrame){
		int SCROLL_PANEL_WIDTH=420;
		int SCROLL_PANEL_HEIGHT=330;
		initializeSlider();
		int gridx=1;
		int gridy=4;
		int top=13;
		int bottom=50;
		setM_Constraint( gridx,  gridy,  top,  bottom);
		this.addChangeListener(new ChangeListener(){
        	public void stateChanged(ChangeEvent event) {
        		double m_scaleValue=getValue();
        		m_scaleValue=m_scaleValue/100.0;
        		int widthScale=(int) (SCROLL_PANEL_WIDTH*m_scaleValue);
        		int heightScale=(int) (SCROLL_PANEL_HEIGHT*m_scaleValue);
        		Dimension dimension=new Dimension(widthScale, heightScale);
        		scrollPane.setPreferredSize(dimension);
        		jFrame.revalidate(); 
			}
        });
	}

	public void setM_Constraint(int gridx, int gridy, int top, int bottom) {
		
		m_Constraint =new GridBagConstraints();
//		constraint.gridx = 1;
//		constraint.gridy = 3;
		m_Constraint.gridx = gridx;
		m_Constraint.gridy = gridy;
//	     constraint.gridwidth=1;
//		constraint.weightx=1;
//		constraint.weighty=1;
		m_Constraint.fill = GridBagConstraints.BOTH;
//		int top=0;
		int right=0;
//		int bottom=10;
		int left=5;
		m_Constraint.insets = new Insets(top, right, bottom, left);
	}
	
}
