package translationVisualizatonGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColorLegendPanel extends JPanel {
	private final int PANEL_WIDTH=150;
	private final int PANEL_HEIGHT=50;

	private final int m_NumberWidth=20;
	private final int m_NumberHeight=50;
	
	private final int m_TitleWidth=50;
	private final int m_TitleHeight=30;
	
//	JLabel m_Title;
//	
//	public JLabel getM_Title() {
//		return m_Title;
//	}
//
//	public void setM_Title(JLabel m_Title) {
//		this.m_Title = m_Title;
////		m_Title.setPreferredSize(new Dimension(m_TitleWidth, m_TitleHeight));
//	}

	public void setColorLegend(List<Map.Entry<Integer, Color>> m_ColorIndex){
		
		for (Map.Entry<Integer, Color> mapping : m_ColorIndex) {
			JLabel colorLegend=new JLabel();
			JLabel frequencyNumber=new JLabel(mapping.getKey().toString());
			frequencyNumber.setPreferredSize(new Dimension(m_NumberWidth, m_NumberHeight));
			colorLegend.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
			colorLegend.setBackground(mapping.getValue());
			colorLegend.setOpaque(true);
			colorLegend.setVisible(true);
			this.add(colorLegend);
			this.add(frequencyNumber);
			
			this.setBackground(Color.white);
			this.setLayout(new GridLayout(m_ColorIndex.size(),1));

		}
		
//		this.setM_Title(new JLabel("Frequency Legend"));
//		this.add(getM_Title());
		
	}

}
