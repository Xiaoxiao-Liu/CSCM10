package translationVisualizatonGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColorLegendPanel extends JPanel {

	private ConcordancePanel m_ConcordancePanel;
	
//	private GridBagConstraints constraint;
	
	public GridBagConstraints getConstraint() {
		GridBagConstraints constraint =new GridBagConstraints();
		constraint.gridx = 1;
		constraint.gridy = 1;
//	     constraint.gridwidth=1;
		constraint.weightx=1;
		constraint.weighty=1;
		constraint.fill = GridBagConstraints.BOTH;
		constraint.insets = new Insets(0,0,0,0);
		return constraint;
	}


	public ConcordancePanel getM_ConcordancePanel() {
		return m_ConcordancePanel;
	}

	public void setM_ConcordancePanel(ConcordancePanel m_ConcordancePanel) {
		this.m_ConcordancePanel = m_ConcordancePanel;
	}

	public void setColorLegend(List<Map.Entry<Integer, Color>> m_ColorIndex, List<Map.Entry<String, Integer>> m_FrequencyIndex){
		int i=0;
		String[] currentIndex;
		for (Map.Entry<Integer, Color> mapping : m_ColorIndex) {
//			currentIndex=m_FrequencyIndex.get(i).toString().split("=");
			JLabel colorLegend=new JLabel();
			JLabel frequencyNumber=new JLabel(mapping.getKey().toString());
//			frequencyNumber.setPreferredSize(new Dimension(m_NumberWidth, m_NumberHeight));
//			colorLegend.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
			colorLegend.setName(mapping.getKey().toString());
			colorLegend.setBackground(mapping.getValue());
			colorLegend.setOpaque(true);
			colorLegend.setVisible(true);
			this.add(colorLegend);
			this.add(frequencyNumber);
			
			this.setBackground(Color.white);
			this.setLayout(new GridLayout(m_ColorIndex.size(),1));

			colorLegend.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent event) {
                	Object objectAll=((JLabel) event.getSource()).getName();
                	String thisString=((JLabel) event.getSource()).getName();
                    getM_ConcordancePanel().freqHighlight(thisString);
                }

            });
			i++;
		}
		
	}

}
