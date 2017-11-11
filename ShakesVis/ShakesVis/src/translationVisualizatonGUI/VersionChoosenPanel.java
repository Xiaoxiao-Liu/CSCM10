package translationVisualizatonGUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class VersionChoosenPanel extends JPanel {
	private JCheckBox m_VersionNameCBox;
	
	private List<JCheckBox> m_checkList=new ArrayList<JCheckBox>();
	
	
	/**the list of String to store version names and pass this list to concordance panel and repaint new panel with versions only selected*/
	public List<String> m_versionNames;
	
//	private ConcordancePanel concordancePanel;
	
	public String[] string={ "0000 BaseText Shakespeare.txt", "1832 Baudissin ed Wenig.txt", "1920 Gundolf.txt", "1941 Schwarz.txt",
			"1947 Baudissin ed Brunner.txt",	"1952 Flatter.txt", "1962 Schroeder.txt",
			"1963 Rothe.txt", "1970 Fried.txt", "1973 Lauterbach.txt",
			"1976 Engler.txt", "1978 Laube.txt", "1985 Bolte Hamblock.txt",
			"1992 Motschach.txt", "1995 Guenther.txt", "2003 Zaimoglu.txt" };
	
	public void addAllVersions(String[] string){
		
		setM_versionNames(new ArrayList<String>());
		for(int i=0; i<string.length; i++){
			getM_versionNames().add(string[i]);
		}
		
	}
	
	public List<String> getM_versionNames() {
		return m_versionNames;
	}

	public void setM_versionNames(List<String> m_versionNames) {
		this.m_versionNames = m_versionNames;
	}

	public List<JCheckBox> getM_checkList() {
		return m_checkList;
	}

	public void setM_checkList(List<JCheckBox> m_checkList) {
		this.m_checkList = m_checkList;
	}

	public JCheckBox getM_VersionNameCBox() {
		return m_VersionNameCBox;
	}

	public void setM_VersionNameCBox(JCheckBox m_VersionNameCBox, String str) {
		this.m_VersionNameCBox = m_VersionNameCBox;
		m_VersionNameCBox.setText(str);
		getM_checkList().add(m_VersionNameCBox);
	}

	public ActionListener add_ActionListener(ConcordancePanel concordancePanel){
		 ActionListener actionListener = new ActionListener() {
	            public void actionPerformed(ActionEvent actionEvent) {
	            	Object oneBoxSelect=((JCheckBox) actionEvent.getSource()).isSelected(); //the object used to listen the selecting states of items
	                Boolean oneSelected = new Boolean((boolean) oneBoxSelect); //change the oneBoxSelect Object to Boolean
	               
	                // if select "all"
	            	if(((JCheckBox) actionEvent.getSource()).getName().toString().equals("All")){
	            		Object objectAll=((JCheckBox) actionEvent.getSource()).isSelected();
		                Boolean boolAll = new Boolean((boolean) objectAll);
		                if(boolAll){
		            		addAllVersions(string);
		            		concordancePanel.displaySingleVersion(getM_versionNames()); //invoke concordancePanel method to repaint
		            		for(int  i=0; i<getM_versionNames().size(); i++){
		            			getM_checkList().get(i).setSelected(true);
		            		}
		                }
		                else{
		                	
		                	String[] strArr=new String[0];
		                	addAllVersions(strArr);
		            		concordancePanel.displaySingleVersion(getM_versionNames()); //invoke concordancePanel method to repaint
		            		for(int i=0; i<getM_versionNames().size(); i++){
		            			getM_checkList().get(i).setSelected(false);
		            		}
		                }
	            	}
	            	
	            	// if select only one item
	            	else{
	            		Object objName;
	            		if(oneSelected==true){ //if the item is selected
		            		objName=((JCheckBox)actionEvent.getSource()).getName(); //get the name of the object
		            		getM_versionNames().add(objName.toString()); //add the name of item to m_VersionNamelist
		            		concordancePanel.displaySingleVersion(getM_versionNames());//invoke concordancePanel method to repaint
		            				
		            	}else{ //if the item is unselected
		            		objName=((JCheckBox)actionEvent.getSource()).getName(); //get the name of the object
//		            		System.out.println("Selected item=" + ((JCheckBox)actionEvent.getSource()).getName()); 
		            		getM_versionNames().remove(objName); //remove this item from versionName list
		            		concordancePanel.displaySingleVersion(getM_versionNames()); //invoke concordancePanel method to repaint

		            	}
	            	}
	            		
	            	
	            }};
	            return actionListener;
	}
	
	public void addVersions( List<String> versionNameList, ConcordancePanel concordancePanel){
		setM_versionNames(new ArrayList<String>());
		this.setLayout(new GridLayout(16, 1));
		for(int i=0; i<versionNameList.size(); i++){
			setM_VersionNameCBox(new JCheckBox(), versionNameList.get(i));
			getM_VersionNameCBox().setName(versionNameList.get(i));
			getM_VersionNameCBox().setVisible(true);
			this.add(getM_VersionNameCBox());
			getM_VersionNameCBox().addActionListener(add_ActionListener(concordancePanel));;
		}
		String allSelecting="All";
		setM_VersionNameCBox(new JCheckBox(), allSelecting);
		getM_VersionNameCBox().setName(allSelecting);
		getM_VersionNameCBox().setVisible(true);
		this.add(getM_VersionNameCBox());
		getM_VersionNameCBox().addActionListener(add_ActionListener(concordancePanel));;
	}
	
	public void setSelected(){
		
	}
	

}
