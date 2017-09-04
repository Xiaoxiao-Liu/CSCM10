package translationVisualization;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;

import TranslationVisualizatonGUI.ConcordancePanel;

public class TranslationVisualization {
	private final static int FRAME_WIDTH=800;
	private final static int FRAME_HEIGHT=800;

	private final static int PANEL_WIDTH=3400;
	private final static int PANEL_HEIGHT=1800;
	
	private final static int SCROLL_PANEL_WIDTH=500;
	private final static int SCROLL_PANEL_HEIGHT=300;
	
	
	
	public static void main(String[] args){
		
		List<Version> m_VersionList=new ArrayList<Version>();
		DataReader dataReader=new DataReader();
		
		m_VersionList=dataReader.readAllFile();

		JFrame concordanceFrame=new JFrame("Translation Visualization");
		
		ConcordancePanel concordancePanel=new ConcordancePanel(m_VersionList);
		concordancePanel.setLayout(null);
		concordancePanel.setBackground(Color.white);
		concordancePanel.setVisible(true);
		concordancePanel.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
		
		JScrollPane scrollPanel=new JScrollPane(concordancePanel);
		scrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanel.setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH, SCROLL_PANEL_HEIGHT));
		scrollPanel.setLayout(new ScrollPaneLayout());
		scrollPanel.setVisible(false);
		
		
		JButton ConcordanceButton=new JButton("Concordances");
		
		JPanel jPanel_1= new JPanel();
		jPanel_1.setBackground(Color.WHITE);
		jPanel_1.add(scrollPanel);
		
		jPanel_1.add(ConcordanceButton);
		
		
			
		
		
		concordanceFrame.setContentPane(jPanel_1);
		concordanceFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);	
		concordanceFrame.setVisible(true);
		concordanceFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		concordanceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ConcordanceButton.addActionListener(new ActionListener(){
			@Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("按钮被点击");
                scrollPanel.setVisible(true);
                jPanel_1.add(scrollPanel);
                concordanceFrame.revalidate(); 
//                Object source = e.getSource();
//                JButton button = (JButton) source;
//                String text = button.getText();
//                if ("按钮被点击".equals(text)) {
//                    button.setText("点我");
//                } else {
//                    button.setText("按钮被点击");
//                }
            }
		});
	}
	
	

}
