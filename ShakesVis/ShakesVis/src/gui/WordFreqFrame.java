package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingUtilities;
import javax.swing.plaf.LayerUI;

public class WordFreqFrame extends JFrame {

	private JScrollPane scrollPanel;
	private JPanel jPanel_1;
	private JPanel jPanel_2;
	 JComponent c;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					WordFreqFrame frame = new WordFreqFrame();
					frame.setSize(800, 800);			
					
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public WordFreqFrame() {
		int xCoordinate=10;
		int panelWidth=210;
		int panelHeight=700;
		WordFreqPanel ShakespearePanel=new WordFreqPanel("src\\data\\BaseText Shakespeare.txt");
		ShakespearePanel.DrawPanel();
		List<Point> barLocation = ShakespearePanel.barLocation;
		List<JButton> buttonList=ShakespearePanel.buttonList;
		
		buttonList.get(0);
		barLocation.get(0);
		WordFreqPanel baudissinEdWenigPanel=new WordFreqPanel("src\\data\\1832 Baudissin ed Wenig.txt");
		WordFreqPanel gundolfPanel=new WordFreqPanel("src\\data\\1920 Gundolf.txt");
		WordFreqPanel schwarzPanel=new WordFreqPanel("src\\data\\1941 Schwarz.txt");
		WordFreqPanel baudissinEdBrunnerPanel=new WordFreqPanel("src\\data\\1947 Baudissin ed Brunner.txt");
		WordFreqPanel flatterPanel=new WordFreqPanel("src\\data\\1952 Flatter.txt");
		WordFreqPanel schroederPanel=new WordFreqPanel("src\\data\\1962 Schroeder.txt");
		WordFreqPanel rothePanel=new WordFreqPanel("src\\data\\1963 Rothe.txt");
		WordFreqPanel friedPanel=new WordFreqPanel("src\\data\\1970 Fried.txt");
		WordFreqPanel lauterbachPanel=new WordFreqPanel("src\\data\\1973 Lauterbach.txt");
		WordFreqPanel englerPanel=new WordFreqPanel("src\\data\\1976 Engler.txt");
		WordFreqPanel laubePanel=new WordFreqPanel("src\\data\\1978 Laube.txt");
		WordFreqPanel bolteHamblockPanel=new WordFreqPanel("src\\data\\1985 Bolte Hamblock.txt");
		WordFreqPanel motschachPanel=new WordFreqPanel("src\\data\\1992 Motschach.txt");
		WordFreqPanel guentherPanel=new WordFreqPanel("src\\data\\1995 Guenther.txt");
		WordFreqPanel zaimogluPanel=new WordFreqPanel("src\\data\\2003 Zaimoglu.txt");
		jPanel_2=new JPanel();
		jPanel_2.setPreferredSize(new Dimension(3400, 800));
		jPanel_2.setLayout(null);
		
		
		List<WordFreqPanel> wordFreqPanelList=new ArrayList<WordFreqPanel>();
		wordFreqPanelList.add(ShakespearePanel);
		wordFreqPanelList.add(baudissinEdWenigPanel);
		wordFreqPanelList.add(gundolfPanel);
		wordFreqPanelList.add(schwarzPanel);
		wordFreqPanelList.add(baudissinEdBrunnerPanel);
		wordFreqPanelList.add(flatterPanel);
		wordFreqPanelList.add(schroederPanel);
		wordFreqPanelList.add(rothePanel);
		wordFreqPanelList.add(friedPanel);
		wordFreqPanelList.add(lauterbachPanel);
		wordFreqPanelList.add(englerPanel);
		wordFreqPanelList.add(laubePanel);
		wordFreqPanelList.add(bolteHamblockPanel);
		wordFreqPanelList.add(motschachPanel);
		wordFreqPanelList.add(guentherPanel);
		wordFreqPanelList.add(zaimogluPanel);
		
		scrollPanel=new JScrollPane(jPanel_2);//add a parent panel (pp) which holds 16 WFPanels
		scrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanel.setPreferredSize(new Dimension(700, 500));
//		System.out.println( scrollPanel.isShowing());

//		for(int i=0;i<wordFreqPanelList.size();i++){
//			jPanel_2.add(wordFreqPanelList.get(i));
//			wordFreqPanelList.get(i).setBounds(i*panelWidth,0,panelWidth,panelHeight);
//			wordFreqPanelList.get(i).setVisible(true);
//		}
		
		scrollPanel.setLayout(new ScrollPaneLayout());
		
		jPanel_1= new JPanel();
		jPanel_1.add(scrollPanel);//add the scrollPanel to a JPanel(JJ)
		
				
		setContentPane(jPanel_1);//add JJ to the parent Frame 
		JComponent comp = Box.createHorizontalBox();
//		comp.setLayout(null);
//				
		 
				 
//		 scrollPanel_1.add(comp);
//		 comp.setPreferredSize(new Dimension(200, 100));
		   
		for(int i=0;i<wordFreqPanelList.size()-1;i++){
			
			WordFreqPanel upper = wordFreqPanelList.get(i);
//		    upper.setPreferredSize(new Dimension(200, 200));
		    upper.setBounds(0, 0, 400, 100);
		     JButton upperChild = new JButton("happy in upper");
		    upper.add(upperChild);
		    WordFreqPanel lower = wordFreqPanelList.get(i+1);
//		    lower.setPreferredSize(new Dimension(200, 200));

		    lower.setBounds(100, 300, 200, 200);
		     JButton lowerChild = new JButton("unhappy in lower");
		    lower.add(lowerChild);

		    comp.add(upper);
//		    comp.add(lower);
		    LayerUI<JComponent> ui = new LayerUI<JComponent>() {   	

		        @Override
		        public void paint(Graphics g, JComponent c) {
		        	
		        	
		            super.paint(g, c);
		            Rectangle u = SwingUtilities.convertRectangle(upper, upper.buttonList.get(0).getBounds(), c);
		            int tmp_3=u.x;
		            int tmp_4=u.y ;
		            Rectangle l = SwingUtilities.convertRectangle(lower, lower.buttonList.get(0).getBounds(), c);
		            int tmp_1=l.x;
		            int tmp_2=l.y;
		            g.setColor(Color.RED);
		            g.drawLine(u.x, u.y + u.height, l.x, l.y);
		            l.x=tmp_1;
		           l.y=tmp_2;
		          u.x=tmp_3;
		            u.y = tmp_4;
		        }

		    };
		    JLayer<JComponent> layer = new JLayer<JComponent>(comp, ui);
		    layer.setBounds(30, 0, 3500, 950); //Notice width!!!!
		    jPanel_2.add(layer);
//		    jPanel_1.add(layer);
		
		}
	}
	
	
}
