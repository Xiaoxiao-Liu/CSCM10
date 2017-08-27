package TranslationVisualizatonGUI;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import translationVisualization.Concordance;
import translationVisualization.Version;

public class ConcordancePanel extends JPanel {
	private List<Version> m_VersionList=new ArrayList<Version>();
	
	
	public ConcordancePanel(List<Version> m_VersionList) {
		m_VersionList = m_VersionList;
	}


	public void paintComponent(Graphics g){
		this.setLayout(null);
		this.setBackground(Color.white);
		super.paintComponent(g);
		for(int i=0; i<m_VersionList.size(); i++){
			Version version=m_VersionList.get(i);
			g.setColor(Color.black);
			g.drawString(version.getM_Author(), version.getM_titlePoint().x, version.getM_titlePoint().y);
			FontMetrics fontMetrics = g.getFontMetrics();
			for(int j=0; j<version.getM_ConcordanceList().size(); j++){
				Concordance concordance=new Concordance();
				g.setColor(Color.black);
				g.drawString(concordance.getM_Word(), concordance.getM_StringPoint().x, concordance.getM_StringPoint().y);
				
				g.setColor(Color.gray);
				g.fillRect(concordance.getM_RectPoint().x, concordance.getM_RectPoint().y, concordance.getM_RectWidth(), concordance.getM_RectHeight());
			
				int versionCompare=i+1;
				if(versionCompare<m_VersionList.size()){
					Point point=compareVersion(i, concordance);
					g.setColor(concordance.getM_Color());
					g.drawLine(concordance.getM_RectPoint().x, concordance.getM_RectPoint().y, point.x, point.y);
				}
			}
		}
	}
	
	
	public Point compareVersion(int versionNumber, Concordance concordance){
		Point point=new Point();
		for(int k=0; k<m_VersionList.get(versionNumber).getM_ConcordanceList().size(); k++){
			Concordance concordanceCompare=m_VersionList.get(versionNumber).getM_ConcordanceList().get(k);
			if(concordanceCompare.getM_Word().equals(concordance.getM_Word())){
				point=concordanceCompare.getM_StringPoint();
			}
		}
		return point;
	}

}
