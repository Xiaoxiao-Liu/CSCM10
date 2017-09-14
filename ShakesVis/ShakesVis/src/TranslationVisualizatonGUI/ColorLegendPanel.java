package TranslationVisualizatonGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColorLegendPanel extends JPanel {
	
	public void colorLegend(List<Map.Entry<Integer, Color>> m_ColorIndex){
		for (Map.Entry<Integer, Color> mapping : m_ColorIndex) {
			JLabel colorLegend=new JLabel(mapping.getKey().toString());
			colorLegend.setPreferredSize(new Dimension(20, 20));
//			mapping.getKey().toString()
			colorLegend.setBackground(mapping.getValue());
//			colorLegend.setForeground(mapping.getValue());
			colorLegend.setOpaque(true);
			colorLegend.setVisible(true);
			this.add(colorLegend);
//			colorLegend.addActionListener(new ActionListener(){
//				@Override
//	            public void actionPerformed(ActionEvent e) {
//	                System.out.println(mapping.getKey());
//	                //we can get the frequency value from the clicking button
//	           
//				}
//			});
		}
		
	}

}
