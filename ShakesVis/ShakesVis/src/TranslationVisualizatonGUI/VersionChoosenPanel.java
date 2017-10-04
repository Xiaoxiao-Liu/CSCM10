package TranslationVisualizatonGUI;

import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class VersionChoosenPanel extends JPanel {
	private JCheckBox m_VersionNameCBox;
	
	public JCheckBox getM_VersionNameCBox() {
		return m_VersionNameCBox;
	}

	public void setM_VersionNameCBox(JCheckBox m_VersionNameCBox) {
		this.m_VersionNameCBox = m_VersionNameCBox;
	}

	public void chooseOneVersion(String versionName){
		
		setM_VersionNameCBox(new JCheckBox("Version No.1"));
		getM_VersionNameCBox().setName("Version No.1");
		this.add(getM_VersionNameCBox());
		getM_VersionNameCBox().addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) {
        	if(getM_VersionNameCBox().isSelected()){
        		 System.out.println(getM_VersionNameCBox().getName());
        	}
          

        }
    });
		
	}
	
	public void addVersions(String[] string){
		
		
		 ItemListener itemListener = new ItemListener() {   
		      public void itemStateChanged(ItemEvent itemEvent) {
		    	  System.out.println(itemEvent.getStateChange());
		        int state = itemEvent.getStateChange();   
		        System.out.println((state == ItemEvent.SELECTED) ? "Selected" : "Deselected");   
		        System.out.println("Item: " + itemEvent.getItem().toString());   
		        ItemSelectable is = itemEvent.getItemSelectable();   
		         
		      }   
		    };   
		    
		    ActionListener actionListener = new ActionListener() {
	            public void actionPerformed(ActionEvent actionEvent) {
//	                abstractButton = (AbstractButton) actionEvent.getSource();
//	                selected = abstractButton.getModel().isSelected();
	            }};
		
		for(int i=0; i<string.length; i++){
			String str=string[i];
			setM_VersionNameCBox(new JCheckBox(string[i]));
			getM_VersionNameCBox().setName(string[i]);
			getM_VersionNameCBox().setVisible(true);
			this.add(getM_VersionNameCBox());
			getM_VersionNameCBox().addItemListener(itemListener);
		}
		
	}

}
