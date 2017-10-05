package TranslationVisualizatonGUI;

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
	
	public List<String> m_versionNames;
	
//	private ConcordancePanel concordancePanel;
	
	public String[] string={ "src\\data\\0000 BaseText Shakespeare.txt", "src\\data\\1832 Baudissin ed Wenig.txt", "src\\data\\1920 Gundolf.txt", "src\\data\\1941 Schwarz.txt",
			"src\\data\\1947 Baudissin ed Brunner.txt",	"src\\data\\1952 Flatter.txt", "src\\data\\1962 Schroeder.txt",
			"src\\data\\1963 Rothe.txt", "src\\data\\1970 Fried.txt", "src\\data\\1973 Lauterbach.txt",
			"src\\data\\1976 Engler.txt", "src\\data\\1978 Laube.txt", "src\\data\\1985 Bolte Hamblock.txt",
			"src\\data\\1992 Motschach.txt", "src\\data\\1995 Guenther.txt", "src\\data\\2003 Zaimoglu.txt" };
	
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

	public void setM_VersionNameCBox(JCheckBox m_VersionNameCBox) {
		this.m_VersionNameCBox = m_VersionNameCBox;
		getM_checkList().add(m_VersionNameCBox);
	}

	public ActionListener add_MActionListener(ConcordancePanel concordancePanel){
		 ActionListener actionListener = new ActionListener() {
	            public void actionPerformed(ActionEvent actionEvent) {
	            	Object objjj=((JCheckBox) actionEvent.getSource());
//	            	Object obj=((JCheckBox) actionEvent.getSource()).getName();
	            	Object bool=((JCheckBox) actionEvent.getSource()).isSelected();
	                Boolean bobj = new Boolean((boolean) bool);
	            	if(((JCheckBox) actionEvent.getSource()).getName().toString().equals("All")){
	            		Object objectAll=((JCheckBox) actionEvent.getSource()).isSelected();
		                Boolean boolAll = new Boolean((boolean) objectAll);
		                if(boolAll){
		                	System.out.println("Yes");
		            		defaultState(string, true);
		                }
		                else{
		                	defaultState(string, false);
		                }
	            		
	            	}
	            	else if(bobj){
	            		Object stringObj=((JCheckBox)actionEvent.getSource()).getName();
	            		String[] fileNameSplit=stringObj.toString().split("\\\\");
	            		int fileNamePosition = 2;
	            		 System.out.println(fileNameSplit[fileNamePosition]);
	            		System.out.println("Selected index=" + ((JCheckBox)actionEvent.getSource()).getName()); 
	            		String str=((JCheckBox)actionEvent.getSource()).getName().toString();
	            		getM_versionNames().add(fileNameSplit[fileNamePosition]);
	            		 System.out.println("Should be this?"+fileNameSplit[fileNamePosition]);

	            		concordancePanel.setVersionDisplaying(getM_versionNames());
	            	}
	            }};
	            return actionListener;
	}
	
	public void addVersions(String[] string, ConcordancePanel concordancePanel){
		setM_versionNames(new ArrayList<String>());
		this.setLayout(new GridLayout(16, 1));
		for(int i=0; i<string.length; i++){
			String str=string[i];
			setM_VersionNameCBox(new JCheckBox(string[i]));
			getM_VersionNameCBox().setName(string[i]);
			getM_VersionNameCBox().setVisible(true);
			this.add(getM_VersionNameCBox());
			getM_VersionNameCBox().addActionListener(add_MActionListener(concordancePanel));;
		}
		String allSelecting="All";
		setM_VersionNameCBox(new JCheckBox(allSelecting));
		getM_VersionNameCBox().setName(allSelecting);
		getM_VersionNameCBox().setVisible(true);
		this.add(getM_VersionNameCBox());
		getM_VersionNameCBox().addActionListener(add_MActionListener(concordancePanel));;
	}
	
	public void defaultState(String[] string, boolean bool){
		for(int i=0; i<getM_checkList().size(); i++){
			getM_checkList().get(i).setSelected(bool);
		}
		
	}

}
