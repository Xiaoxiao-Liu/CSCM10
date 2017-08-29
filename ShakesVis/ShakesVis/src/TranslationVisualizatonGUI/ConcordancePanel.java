package TranslationVisualizatonGUI;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import translationVisualization.Concordance;
import translationVisualization.Version;

public class ConcordancePanel extends JPanel {
	public List<Version> m_VersionList=new ArrayList<Version>();
	
	
	public ConcordancePanel(List<Version> versionList) {
		m_VersionList = versionList;
	}


	public void paintComponent(Graphics g){
		this.setLayout(null);
		this.setBackground(Color.white);
		super.paintComponent(g);
		for(int i=0; i<m_VersionList.size(); i++){
			Version version=m_VersionList.get(i);
			g.setColor(Color.black);
			g.setFont(version.getM_WORD_FONT());
			g.drawString(version.getM_Author(), version.getM_titlePoint().x, version.getM_titlePoint().y);
			FontMetrics fontMetrics = g.getFontMetrics();
			for(int j=0; j<version.getM_ConcordanceList().size(); j++){
				Concordance concordance=new Concordance();
				concordance=version.getM_ConcordanceList().get(j);
				g.setColor(Color.black);
				String string=concordance.getM_Word()+" "+concordance.getM_Frequency();
				g.setFont(concordance.getM_WORD_FONT());
				g.drawString(string, concordance.getM_StringPoint().x-fontMetrics.stringWidth(string), concordance.getM_StringPoint().y);
				g.setColor(Color.LIGHT_GRAY);
				g.drawRect(concordance.getM_RectPoint().x, concordance.getM_RectPoint().y, concordance.getM_RectWidth(), concordance.getM_RectHeight());
//				g.fillRect(concordance.getM_RectPoint().x, concordance.getM_RectPoint().y, concordance.getM_RectWidth(), concordance.getM_RectHeight());
				int versionCompare=i+1;
				if(versionCompare>1&&versionCompare<m_VersionList.size()){
				
					for(int k=0; k<m_VersionList.get(versionCompare).getM_ConcordanceList().size(); k++){
						Concordance concordanceCompare=m_VersionList.get(versionCompare).getM_ConcordanceList().get(k);
						if(concordanceCompare.getM_Word().equals(concordance.getM_Word())){
							int gap=15;
							g.setColor(concordance.getM_Color());
							g.drawLine(concordance.getM_RectPoint().x, concordance.getM_StringPoint().y, concordanceCompare.getM_RectPoint().x, concordanceCompare.getM_StringPoint().y);
						}
					}
					
				}
			}
		}
	}
	
	public boolean compareVersion(int versionNumber, Concordance concordance){
		
		for(int k=0; k<m_VersionList.get(versionNumber).getM_ConcordanceList().size(); k++){
			Concordance concordanceCompare=m_VersionList.get(versionNumber).getM_ConcordanceList().get(k);
			if(concordanceCompare.getM_Word().equals(concordance.getM_Word())){
//				Point point=concordanceCompare.getM_StringPoint();
			}
		}
		return true;
	}
	
	
	private class MouseAction implements MouseListener, MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
			
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
		
		
	}
	
	

}
