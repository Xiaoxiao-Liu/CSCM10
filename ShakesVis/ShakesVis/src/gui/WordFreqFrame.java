package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingUtilities;
import javax.swing.plaf.LayerUI;

public class WordFreqFrame extends JFrame {

	private JScrollPane scrollPanel;
	private JPanel jPanel_1;
	private JPanel jPanel_2;
	 HashMap<Integer,ArrayList<Point>> storePointList;  
	 ArrayList<Point> pointList;
	 Point p;
	 Point pp;
	 JComponent comp;
	
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
		List<Map.Entry<String, Integer>> list = ShakespearePanel.list;
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
		
		scrollPanel.setBackground(new Color(255,0,0,10));

		Color C = new Color(255,0,0,128); 
//		scrollPanel.setBackground(C);
		
	
		for(int i=0;i<wordFreqPanelList.size();i++){
			
			
			jPanel_2.add(wordFreqPanelList.get(i));

			wordFreqPanelList.get(i).setBounds(i*panelWidth,0,panelWidth,panelHeight);
			wordFreqPanelList.get(i).setVisible(true);
			 System.out.println(wordFreqPanelList.get(i).barButton.getBounds());
//			for(int j=0;j<wordFreqPanelList.get(i).barLocation.size();j++){
//				p=wordFreqPanelList.get(i).barLocation.get(j);
//				pointList.add(p);
//			}

		}
		
		
		scrollPanel.setLayout(new ScrollPaneLayout());
		
		jPanel_1= new JPanel();
		jPanel_1.add(scrollPanel);//add the scrollPanel to a JPanel(JJ)

				
		setContentPane(jPanel_1);//add JJ to the parent Frame 

		
		comp =scrollPanel;
		comp.setBackground(new Color(0,0,0,10));
//		 System.out.println(ShakespearePanel.barButton.getBounds());
//         Rectangle u = SwingUtilities.convertRectangle(ShakespearePanel, ShakespearePanel.barButton.getBounds(), jPanel_1);
//		 System.out.println(u.getX());

//		 JComponent comp = Box.createVerticalBox();
//		 LayerUI<JComponent> ui = new LayerUI<JComponent>() {
//
//		        @Override
//		        public void paint(Graphics g, JComponent c) {
//		            super.paint(g, c);
//		            ShakespearePanel.barButton.getLocationOnScreen();
//		            Rectangle u = SwingUtilities.convertRectangle(ShakespearePanel, ShakespearePanel.barButton.getBounds(), c);
//		            Rectangle l = SwingUtilities.convertRectangle(baudissinEdWenigPanel, baudissinEdWenigPanel.barButton.getBounds(), c);
//
//		            System.out.println(ShakespearePanel.barButton.getLocationOnScreen());
//		            g.setColor(Color.RED);
//		            g.drawLine(u.x, u.y + u.height, l.x, l.y);
//		        }
//
//		    };
//		    JLayer<JComponent> layer = new JLayer<JComponent>(comp, ui);
//		    layer.setVisible(true);
//
//		    jPanel_1.add(layer);
		
		
//				 
//				
		     JComponent upper = ShakespearePanel;  //versionpanel
//		    final JButton upperChild = new JButton("happy in upper");
//		    upper.add(upperChild);
		    final JComponent lower = baudissinEdWenigPanel;
//		    final JButton lowerChild = new JButton("unhappy in lower");
//		    lower.add(lowerChild);
		    comp.add(upper);
		    comp.add(lower);
		    p=ShakespearePanel.barLocation.get(2);
		    pp=baudissinEdWenigPanel.barLocation.get(3);
		    LayerUI<JComponent> ui = new LayerUI<JComponent>() {

		        @Override
		        public void paint(Graphics g, JComponent c) {
		            super.paint(g, c);
		            Point x=SwingUtilities.convertPoint(upper, p, c);
		            Point y=SwingUtilities.convertPoint(lower, pp, c);
//		            Rectangle u = SwingUtilities.convertRectangle(upper, upperChild.getBounds(), c);
//		            Rectangle l = SwingUtilities.convertRectangle(lower, lowerChild.getBounds(), c);

//		            System.out.println(x);
//		            System.out.println(y);
		            c.setBackground(new Color(255,0,0,10));

		            g.setColor(Color.RED);
		            g.drawLine(x.x, x.y, y.x, y.y);
		            
//		            g.drawLine(u.x, u.y + u.height, l.x, l.y);
		        }

		    };
		    
		    JLayer<JComponent> layer = new JLayer<JComponent>(comp, ui);

		    layer.setBackground(new Color(255,0,0,10));
		    
		    jPanel_1.add(layer);
//			

		
		
	}
	
	
}
