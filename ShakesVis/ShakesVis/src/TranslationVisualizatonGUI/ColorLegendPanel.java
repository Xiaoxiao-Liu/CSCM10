package TranslationVisualizatonGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColorLegendPanel extends JPanel {
	private final int PANEL_WIDTH=20;
	private final int PANEL_HEIGHT=20;

	private final int String_WIDTH=35;
	private final int Strign_HEIGHT=30;
	
	
	public void setColorLegend(List<Map.Entry<Integer, Color>> m_ColorIndex){
		for (Map.Entry<Integer, Color> mapping : m_ColorIndex) {
			JLabel colorLegend=new JLabel();
			JLabel frequencyNumber=new JLabel(mapping.getKey().toString());
			frequencyNumber.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
			colorLegend.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
			colorLegend.setBackground(mapping.getValue());
			colorLegend.setOpaque(true);
			colorLegend.setVisible(true);
			this.add(frequencyNumber);
			this.add(colorLegend);
			this.setBackground(Color.white);
			this.setLayout(new GridLayout(m_ColorIndex.size(),1));
		}
		
	}

}
