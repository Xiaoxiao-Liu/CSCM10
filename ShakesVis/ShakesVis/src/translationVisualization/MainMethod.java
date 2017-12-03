//package translationVisualization;
//
//import java.awt.Dimension;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.Insets;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.ItemEvent;
//import java.awt.event.ItemListener;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JSlider;
//import javax.swing.JToggleButton;
//import javax.swing.SwingConstants;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeListener;
//
//import translationVisualizatonGUI.VersionChoosenPanel;
//
//public class MainMethod {
//	private static double m_scaleValue=100;
//	/** the width of the ScrollPanel */
//	private final static int SCROLL_PANEL_WIDTH=420;
//	
//	/** the height of the ScrollPanel */
//	private final static int SCROLL_PANEL_HEIGHT=330;
//	
//	public static void main(String[] args) throws Exception{
//		 
//		TranslationVisualization transVis=new TranslationVisualization();
//		transVis.setComponents(transVis);
//		
//		transVis.getM_ScrollPanel().add(transVis.getScrollPane());
//		
//		//concordance slider
//		int min=50; //minimum value
//		int max=200; //maximum value
//		int initialVar=100; //initial value
//		//JSlider(int orientation, int min, int max, int value)
//		//JSlider(orientation, minimum value, maximum value, and initial value)
//		transVis.setM_ConcordanceSlider(new JSlider(SwingConstants.HORIZONTAL, min, max, initialVar));
//		
//		transVis.setM_ScrollPaneSlider(new JSlider(SwingConstants.HORIZONTAL, min, max, initialVar));
////		//tfidf slider
////				int minTfidf=0;
////				int maxTfidf=25;
////				transVis.setM_TfidfSlider(new JSlider(SwingConstants.HORIZONTAL, min, max, initialVar));
////		        // end initialize concordance slide
//		transVis.setVersionChoosingPanel(new VersionChoosenPanel(), transVis.getConcordancePanel(), transVis.getDataReader().getM_VersionNameList());
//		
//		
//		
//		// set layout for visualization panel
//		GridBagLayout panelLayout = new GridBagLayout( );
//		GridBagConstraints constraint=new GridBagConstraints();
//		transVis.getM_visuallizationPanel().setLayout(panelLayout);
//
//		 constraint.gridx = 1;
//	     constraint.gridy = 1;
////	     constraint.gridwidth=1;
//	     constraint.weightx=1;
//	     constraint.weighty=1;
//	     constraint.fill = GridBagConstraints.BOTH;
//	     constraint.insets = new Insets(0,0,0,0);
//	     transVis.getM_visuallizationPanel().add(transVis.getM_ColorLegendPanel(),constraint);
//		
//		constraint.gridx = 2;
//		constraint.gridy = 1;
////		constraint.gridwidth=5;
//	     constraint.weightx=5;
//	     constraint.weighty=0;
////		constraint.fill=GridBagConstraints.
//		constraint.fill = GridBagConstraints.BOTH;
//		constraint.insets = new Insets(0,0,0,0);
//		transVis.getM_visuallizationPanel().add(transVis.getM_ScrollPanel(),constraint);
//
//		// set layout for user-option panel
//		GridBagLayout userOptionPaneLayout = new GridBagLayout( );
//		GridBagConstraints useroptionConstraint=new GridBagConstraints();
//		transVis.getM_UserOptionPanel().setLayout(userOptionPaneLayout);
//		
//		//ConcordanceButton
//		transVis.setConcordanceButton(new JButton("Frequency"));
//		useroptionConstraint.gridx = 1;
//		useroptionConstraint.gridy = 1;
//		useroptionConstraint.fill = GridBagConstraints.BOTH;
//        useroptionConstraint.insets = new Insets(25,30,5,30);
//        transVis.getM_UserOptionPanel().add(transVis.getConcordanceButton(),useroptionConstraint);
//        transVis.getConcordanceButton().addActionListener(new ActionListener(){
//			@Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Button pressed");
//
//                transVis.getScrollPane().setVisible(true);
//                transVis.getConcordanceFrame().revalidate(); 
//            }
//		});
//        
//        //TfIdfButton
//        transVis.setM_TfIdfButton(new JButton("Tf-Idf"));
//    	useroptionConstraint.gridx = 1;
//		useroptionConstraint.gridy = 2;
//		useroptionConstraint.fill = GridBagConstraints.BOTH;
//        useroptionConstraint.insets = new Insets(0,30,5,30);
//        transVis.getM_UserOptionPanel().add(transVis.getM_TfIdfButton(),useroptionConstraint);
//        transVis.getM_TfIdfButton().addActionListener(new ActionListener(){
//        	@Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Another button pressed");
//                transVis.setM_VersionList(null);
////                transVis.getDataReader().setFrequentValue(false);
//                try {
//					transVis.setM_VersionList(transVis.getDataReader().initiateTfIdf());
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
////                transVis.setM_VersionList(getDataReader().initiateDataReader(););
//                transVis.getConcordancePanel().getBooleanValue(true);
//                transVis.getScrollPane().setVisible(true);
//                transVis.getConcordanceFrame().revalidate(); 
//            }
//        });
//        
//        //text on off button
//		transVis.setM_TextOnOffButton(new JToggleButton("Text On"));
//		useroptionConstraint.gridx = 1;
//		useroptionConstraint.gridy = 3;
//		useroptionConstraint.fill = GridBagConstraints.BOTH;
//        useroptionConstraint.insets = new Insets(0,30,5,30);
//        transVis.getM_UserOptionPanel().add(transVis.getM_TextOnOffButton(),useroptionConstraint);
//        ItemListener itemListener = new ItemListener() {
//            public void itemStateChanged(ItemEvent itemEvent) {
//                int state = itemEvent.getStateChange();
//                if (state == ItemEvent.SELECTED) {
//                	 transVis.getM_TextOnOffButton().setText("Text On");//change the text on button
//                	 transVis.getConcordancePanel().setOnAndOff(false);
//                } else {
//                	transVis.getM_TextOnOffButton().setText("Text Off");
//                	 transVis.getConcordancePanel().setOnAndOff(true);
//                }
//            }
//        };
//        transVis.getM_TextOnOffButton().addItemListener(itemListener);
//		
//        //concordanceSlider
//        useroptionConstraint.gridx = 1;
//        useroptionConstraint.gridy = 4;
////        useroptionConstraint.fill = GridBagConstraints.BOTH;
////        useroptionConstraint.anchor = GridBagConstraints.EAST;
//        useroptionConstraint.insets = new Insets(5,0,0,5);
//        transVis.getM_UserOptionPanel().add(transVis.getM_ConcordanceSlider(),useroptionConstraint);
//        transVis.getM_ConcordanceSlider().addChangeListener(new ChangeListener(){
//			public void stateChanged(ChangeEvent event) {
//				m_scaleValue=transVis.getM_ConcordanceSlider().getValue();
//				transVis.getConcordancePanel().scaleConcordancePanel((int) m_scaleValue);
//				transVis.getConcordanceFrame().revalidate(); 
//			}
//		});
//        
//        //concordanceSlider label
//        transVis.setM_SliderLabel(new JLabel(), "Concordance");
//        useroptionConstraint.gridx = 2;
//        useroptionConstraint.gridy = 4;
////      useroptionConstraint.anchor = GridBagConstraints.WEST;
//        useroptionConstraint.insets = new Insets(2,5,0,0);
//        transVis.getM_UserOptionPanel().add(transVis.getM_SliderLabel(),useroptionConstraint);
//        
//		//scrollpaneSlider
//        useroptionConstraint.gridx = 1;
//        useroptionConstraint.gridy =5;
////        useroptionConstraint.fill = GridBagConstraints.BOTH;
//        useroptionConstraint.insets = new Insets(3,0,20,5);
//        transVis.getM_ScrollPaneSlider().setToolTipText("Hello");
//        transVis.getM_UserOptionPanel().add(transVis.getM_ScrollPaneSlider(), useroptionConstraint);
//        transVis.getM_ScrollPaneSlider().addChangeListener(new ChangeListener(){
//        	public void stateChanged(ChangeEvent event) {
//        		m_scaleValue=transVis.getM_ScrollPaneSlider().getValue();
//        		m_scaleValue=m_scaleValue/100.0;
//        		int widthScale=(int) (SCROLL_PANEL_WIDTH*m_scaleValue);
//        		int heightScale=(int) (SCROLL_PANEL_HEIGHT*m_scaleValue);
//        		Dimension dimension=new Dimension(widthScale, heightScale);
//        		transVis.getScrollPane().setPreferredSize(dimension);
//        		transVis.getConcordanceFrame().revalidate(); 
//        		transVis.getConcordanceFrame().repaint(); 
//			}
//        });
//        
//      //scrollpaneSlider label
//        transVis.setM_SliderLabel(new JLabel(), "Panel");
//        useroptionConstraint.gridx = 2;
//        useroptionConstraint.gridy = 5;
////        useroptionConstraint.anchor = GridBagConstraints.WEST;
//        useroptionConstraint.insets = new Insets(2,5,20,0);
//        transVis.getM_UserOptionPanel().add(transVis.getM_SliderLabel(),useroptionConstraint);
//
//        // versionChoosingPanel
//		useroptionConstraint.gridx = 1;
//		useroptionConstraint.gridy = 7;
//		constraint.weightx=1;
//		useroptionConstraint.fill = GridBagConstraints.BOTH;
//		transVis.getM_UserOptionPanel().add(transVis.getVersionChoosingPanel(),useroptionConstraint);
//		
//		//Layer 2 layout
//		GridBagLayout layout = new GridBagLayout();
//		transVis.getConcordanceFrame().setLayout(layout);
//		GridBagConstraints s=new GridBagConstraints();
//		s.fill=GridBagConstraints.BOTH;
//		s.gridwidth=1;
//		s.weightx=0;
//		s.weighty=0;
//		layout.setConstraints(transVis.getM_UserOptionPanel(), s);
//		s.gridwidth=5;
//		s.weightx=1;
//		s.weighty=1;
//		layout.setConstraints(transVis.getM_visuallizationPanel(), s);
//		
//
//		transVis.getConcordanceFrame().add(transVis.getM_UserOptionPanel());
//		transVis.getConcordanceFrame().add(transVis.getM_visuallizationPanel());
//		transVis.getConcordanceFrame().setVisible(true);
//		transVis.getConcordanceFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		transVis.getConcordanceFrame().setLocationRelativeTo(null);
//		transVis.getConcordanceFrame().setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
//		
//	}
//
//}
