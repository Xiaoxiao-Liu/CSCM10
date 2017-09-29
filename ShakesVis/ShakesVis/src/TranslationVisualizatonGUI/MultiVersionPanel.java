package TranslationVisualizatonGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MultiVersionPanel extends JPanel{
	
	private JPanel m_MultiVersionPanel;
	
	private List<Boolean> m_Bools=new ArrayList<Boolean>();
	
	private Hashtable<String, Boolean> m_BooleanIndex=new Hashtable<String, Boolean>();
	
	private static JCheckBox versionItem;
	
//	Hashtable<String, Integer> m_StringIndex = new Hashtable<String, Integer>();
	
	public void setOneVersionItem(){
		versionItem = new JCheckBox("FirstItem", true);
		
		ActionListener actionListener = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        System.out.println(abstractButton);
		        if(selected){
		        	addToBooList(selected);
		        }
		      }
		    };
		    versionItem.addActionListener(actionListener);
	}
	
	public void addToBooList(boolean bool){
		m_Bools.add(bool);
//		m_BooleanIndex.put("First", bool);
		System.out.println(m_Bools);
	}
	
//	public void setVersionMenu(List<String> versionNames) {
////		for(int i=0; i<versionNames.length; i++){
////			versionMenu = new JCheckBox(versionNames[i], true);
////		}
//		    comboMenu=new JComboBox();
//		    comboMenu.addItem(versionMenu);
//		    comboMenu.addActionListener(new ActionListener() {   
//		        public void actionPerformed(ActionEvent e) {
//		        	bool=false;
//		        	addtoList(bool);
//		          System.out.println("Selected index=" + ((JComboBox)e.getSource()).getItemCount());   
//		          System.out.println( comboMenu.getSelectedIndex());
//		        }   
//		      });   
//		    
//		    
//		    
//	}


	public JPanel getM_MultiVersionPanel() {
		return m_MultiVersionPanel;
	}


	public void setM_MultiVersionPanel(JPanel m_MultiVersionPanel) {
		this.m_MultiVersionPanel = m_MultiVersionPanel;
	} 
	
	public void addToPanel(){
		setM_MultiVersionPanel(new JPanel());
	}
	
	
	public static void main(String[] args){
		JFrame frame=new JFrame();
		frame.setVisible(true);
		MultiVersionPanel multiVersion=new MultiVersionPanel();
		multiVersion.setVisible(true);
		multiVersion.setOneVersionItem();
		multiVersion.add(versionItem);
		frame.add(multiVersion);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getConcordanceFrame().setLocationRelativeTo(null);
//		frame.getConcordanceFrame().setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
		
	}
}
