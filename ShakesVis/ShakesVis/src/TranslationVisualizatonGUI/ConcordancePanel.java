package TranslationVisualizatonGUI;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JPanel;

import translationVisualization.Concordance;
import translationVisualization.Version;

public class ConcordancePanel extends JPanel {
	public List<Version> m_VersionList=new ArrayList<Version>();
	
	private double m_scaleValue=10;

	/**
	 * Constructor
	 * @param versionList
	 */
	public ConcordancePanel(List<Version> versionList) {
		m_VersionList = versionList;
	}
	

	public double getScaleValue() {
		int divideValue=100;
		int plusValue=1;
		return m_scaleValue/divideValue+plusValue;
	}

	public void setScaleValue(int scaleValue) {
		// if diameter invalid, default to 10
		m_scaleValue =( scaleValue >= 0 ? scaleValue : 10 ); 
		repaint();
	}
	
	

	
	public void paintComponent(Graphics g){
		Graphics2D g2d=(Graphics2D)g;
		g2d.scale(getScaleValue(),getScaleValue());
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
				String string=concordance.getM_Token()+" "+concordance.getM_Frequency();
				g.setFont(concordance.getM_WORD_FONT());
				g.drawString(string, concordance.getM_StringPoint().x-fontMetrics.stringWidth(string), concordance.getM_StringPoint().y);
				g.setColor(concordance.getM_RectColor());
				g.fillRect(concordance.getM_RectPoint().x, concordance.getM_RectPoint().y, concordance.getM_RectWidth(), concordance.getM_RectHeight());

				g.setColor(Color.GRAY);
				g.drawRect(concordance.getM_RectPoint().x, concordance.getM_RectPoint().y, concordance.getM_RectWidth(), concordance.getM_RectHeight());
				int versionCompare=i+1;
				if(versionCompare>1&&versionCompare<m_VersionList.size()){
				
					for(int k=0; k<m_VersionList.get(versionCompare).getM_ConcordanceList().size(); k++){
						Concordance concordanceCompare=m_VersionList.get(versionCompare).getM_ConcordanceList().get(k);
						if(concordanceCompare.getM_Token().equals(concordance.getM_Token())){
							int gap=15;
							g.setColor(concordance.getM_TokenColor());
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
			if(concordanceCompare.getM_Token().equals(concordance.getM_Token())){
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
