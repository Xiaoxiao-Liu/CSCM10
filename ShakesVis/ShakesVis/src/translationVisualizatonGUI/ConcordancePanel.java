package translationVisualizatonGUI;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import translationVisualization.Concordance;
import translationVisualization.DataReader;
import translationVisualization.Version;

public class ConcordancePanel extends JPanel {
	
	/** the list of versions passed from translation visualization */
	public List<Version> m_VersionList=new ArrayList<Version>();
	
	private List<Version> m_VersionListCopied=new ArrayList<Version>();
	
	/** one Version object */
	Version m_singleVersion=new Version();
	

	/** the default integer used to set zoom level, we use 10 is because when the first time 
	 * the panel painted, we do not get zoom value from 
	 * slider listener(see translationVisualization.getM_Slider().addChangeListener()) */
	private double m_ZoomValue=40; //initiate zoomvalue

	private int m_VersionNumber=0;
	
		public int getM_VersionNumber() {
		return m_VersionNumber;
	}


	public void setM_VersionNumber(int m_VersionNumber) {
		this.m_VersionNumber = m_VersionNumber;
	}


		public List<Version> getM_VersionList() {
			
	//		return m_VersionList;
			return m_VersionListCopied;
		}


	public void setM_VersionList(List<Version> m_VersionList) {
		this.m_VersionListCopied = m_VersionList;
	}


	public Version getM_singleVersion() {
		return m_singleVersion;
	}


	public void setM_singleVersion(Version m_singleVersion) {
		this.m_singleVersion = m_singleVersion;
	}


	/**
	 * Constructor
	 * @param versionList
	 */
	public ConcordancePanel(List<Version> versionList) {
		m_VersionList = versionList;
		setM_VersionList(m_VersionList);
		System.out.println("Hello"+m_VersionList.size());
		
	}
	
	
	/**
	 * a formula is applied here to make sure we return a float value
	 * to fit the .scale() method.
	 * @return zoomValue
	 */
	public double getZoomValue() {
		int divideValue=100; //m_scaleValue/divideValu, divide integers into double values
		double plusValue=0.25; //plus this value to fit the zoom value into property size
		double zoomValue=m_ZoomValue/divideValue+plusValue;
		return zoomValue;
	}

	/**
	 * this method pass zoomValue from translationVisualization.getM_Slider().addChangeListener()
	 * @param scaleValue
	 */
	public void setZoomValue(int zoomValue) {
		//let m_scaleValue=scaleValue, if diameter invalid, default to 10
		int defaultValue=10;
		m_ZoomValue =( zoomValue >= 0 ? zoomValue : defaultValue ); 
		repaint(); 
	}
	
	
	/**
	 * 
	 * @param List<String>
	 */
	public void setVersionDisplaying(List<String> VersionSelected){
		List<Version> m_VersionListChoosen=new ArrayList<Version>();
		int versionNumber=0;
		
		for(int j=0; j<VersionSelected.size(); j++){
			String str=VersionSelected.get(j);
			for(int i=0; i<m_VersionList.size(); i++){
				if(str.equals(m_VersionList.get(i).getM_VersionName())){
					m_VersionListChoosen.add(m_VersionList.get(i));
					m_VersionListChoosen.get(versionNumber).setM_VersionNumber(versionNumber);
					System.out.println("vN"+m_VersionListChoosen.get(versionNumber).getM_VersionName()+" "+m_VersionListChoosen.get(versionNumber).getM_VersionNumber());
					setM_VersionNumber(versionNumber);
					versionNumber++;
				}
			}
		}
		setM_VersionList(m_VersionListChoosen);
		resetLocations();
		repaint(); 
	}
	
	public void setAllVersionDisplaying(boolean displayOrNot){
		if(displayOrNot==true){
			setM_VersionList(m_VersionList);
		}else{
			setM_VersionList(null);
		}
		repaint();
	}
	
	public void resetLocations(){
		DataReader dataReader=new DataReader();
		int versionNumber;
		for(int i=0; i<getM_VersionList().size(); i++){
			versionNumber=getM_VersionList().get(i).getM_VersionNumber();
			getM_VersionList().get(i).setM_titlePoint(dataReader.calculatePoint(versionNumber, 0));
			for(int j=0; j<getM_VersionList().get(i).getM_WordsList().size(); j++){
				getM_VersionList().get(i).getM_ConcordanceList().get(j).setM_StringPoint(dataReader.calculatePoint(i, j+1));
				getM_VersionList().get(i).getM_ConcordanceList().get(j).setM_RectPoint(dataReader.calculatePoint(versionNumber, j+1));
				
			}
		}
	}
	
	/**
	 * Draw the version visualization on ConcordancePanel.
	 * This method is called from ConcordancePanel. 
	 */
	public void paintComponent(Graphics g){
		
		//create Graphics2D object to zoom ConcordancePanel
		Graphics2D g2d=(Graphics2D)g;
		g2d.scale(getZoomValue(),getZoomValue());
		
		//set the ConcordancePanel
		this.setLayout(null);
		this.setBackground(Color.white);
		super.paintComponent(g);
		
		//read and paint all 16 versions on the panel
		for(int i=0; i<getM_VersionList().size(); i++){
			Version version=getM_VersionList().get(i); //get current Version object to fetch information stored in the Version
			System.out.println(version.getM_VersionName());
			// draw title Strings
			g.setColor(Color.black); //set color of strings(tokens)
			g.setFont(version.getM_WORD_FONT()); //set font of strings
			//drawString(String, int x, int y), x and y represents the leftmost position of the string
			String title=version.getM_Author()+" "+ version.getM_VersionYear();
			g.drawString(title, version.getM_titlePoint().x, version.getM_titlePoint().y);
			
			// a font metrics object used to set the alignment of version tokens
			FontMetrics fontMetrics = g.getFontMetrics();
			
			//read and paint all 50 tokens/Concordance in the current Version
			for(int j=0; j<version.getM_ConcordanceList().size(); j++){
				Concordance concordance=new Concordance(); //every time read a new concordance
				concordance=version.getM_ConcordanceList().get(j);
				
				//set color of strings/tokens
				g.setColor(Color.black);
				//paint both token and its frequency
				String string=concordance.getM_Token()+" "+concordance.getM_Frequency();
				g.setFont(concordance.getM_WORD_FONT());
				//draw the current string/token, drawString(String, int x, int y)
				g.drawString(string, concordance.getM_StringPoint().x-fontMetrics.stringWidth(string), concordance.getM_StringPoint().y);
				
				//set color of rectangles
				g.setColor(concordance.getM_RectColor());
				//drawr filled rectangles, fillRect(int x, int y, int width, int height),
				//x and y represent the top left position of the rectangle,
				g.fillRect(concordance.getM_RectPoint().x, concordance.getM_RectPoint().y, concordance.getM_RectWidth(), concordance.getM_RectHeight());

				//draw the lines for rectangle
				g.setColor(Color.GRAY);
				g.drawRect(concordance.getM_RectPoint().x, concordance.getM_RectPoint().y, concordance.getM_RectWidth(), concordance.getM_RectHeight());
				
				//draw the lines connect same word between versions
				int versionCompare=i+1; //comparing from the second version
				if(version.getM_VersionName().equals("0000 BaseText Shakespeare.txt")&&versionCompare<getM_VersionList().size()){
					for(int m=0; m<getM_VersionList().get(versionCompare).getM_ConcordanceList().size(); m++){
						Concordance concordanceCompare=getM_VersionList().get(versionCompare).getM_ConcordanceList().get(m);
						if(concordanceCompare.getM_TokenTranslation().equals(concordance.getM_Token())){
							g.setColor(concordance.getM_RectColor()); 
							System.out.println("Shake: "+concordanceCompare.getM_Token());
							System.out.println("ShakesPoint: "+concordanceCompare.getM_RectPoint().x+", "+concordanceCompare.getM_StringPoint().y);
							System.out.println("Trans: "+concordance.getM_TokenTranslation());
							System.out.println("Trans:Point:"+concordance.getM_RectPoint().x+", "+ concordance.getM_StringPoint().y);
							g.drawLine( concordanceCompare.getM_RectPoint().x, concordanceCompare.getM_StringPoint().y, concordance.getM_RectPoint().x, concordance.getM_StringPoint().y);
						}
					}
					
				}
				//versionCompare>1, means we the compared version start from the third one
				//versionCompare<m_VersionList.size(), controal the last version compared
//				if(versionCompare>1&&versionCompare<getM_VersionList().size()){ 
				else if(!version.getM_VersionName().equals("0000 BaseText Shakespeare.txt")&&versionCompare<getM_VersionList().size()){ 
					//read and compare all the concordances in the compared version
					for(int k=0; k<getM_VersionList().get(versionCompare).getM_ConcordanceList().size(); k++){
						Concordance concordanceCompare=getM_VersionList().get(versionCompare).getM_ConcordanceList().get(k);
						
						//compare the two tokens
						if(concordanceCompare.getM_Token().equals(concordance.getM_Token())){
							
							//set line colors
//							g.setColor(concordance.getM_TokenColor()); // color edge according to strings
							g.setColor(concordance.getM_RectColor()); // color edge according to frequency (same with rectangle color)
							
							//drawLine(int x1, int y1, int x2, int y2), x1 and y1 represent start point of the line, x2 and y2 represent end point of the line
							g.drawLine(concordance.getM_RectPoint().x, concordance.getM_StringPoint().y, concordanceCompare.getM_RectPoint().x, concordanceCompare.getM_StringPoint().y);
						}
					}
					
				}
			}
		}
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
