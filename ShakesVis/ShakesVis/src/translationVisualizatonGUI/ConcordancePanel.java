package translationVisualizatonGUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
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
	
	private DataReader dataReader;
	
	private int scaleValue;
	
//	private Point startPoint=new Point();
//	
//	private Point endPoint=new Point();
	
	public int getScaleValue() {
		return scaleValue;
	}


	public void setScaleValue(int scaleValue) {
		this.scaleValue = scaleValue;
	}


	public DataReader getDataReader() {
		return dataReader;
	}


	public void setDataReader(DataReader dataReader) {
		this.dataReader = dataReader;
	}

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
	
	public Point getHighlightPoint(Point eventPoint, int scaleValue){
		System.out.println(eventPoint);
		int lineNumber;
		int versionNumber;
		double scaleValueProcess=scaleValue/100.0;
		int spacingY=(int) (25*scaleValueProcess*scaleValueProcess);
		int spacingX=(int) (150*scaleValueProcess*scaleValueProcess);
		versionNumber=(eventPoint.x-55)/spacingX;
		lineNumber=eventPoint.y/spacingY;
		
		System.out.println(versionNumber);
		Point point=new Point(versionNumber, lineNumber);
		return point;
	}
	
	
	
	
	/**
	 * 
	 * @param List<String>
	 */
	public void displaySingleVersion(List<String> VersionSelected){
		List<Version> m_VersionListChoosen=new ArrayList<Version>();
		int versionNumber=0;
		
		
		//
		for(int j=0; j<VersionSelected.size(); j++){
			String str=VersionSelected.get(j);
			for(int i=0; i<m_VersionList.size(); i++){
				if(str.equals(m_VersionList.get(i).getM_VersionName())){
					m_VersionListChoosen.add(m_VersionList.get(i));
					m_VersionListChoosen.get(versionNumber).setM_VersionNumber(versionNumber);
					setM_VersionNumber(versionNumber);
					versionNumber++;
				}
			}
		}
		setM_VersionList(m_VersionListChoosen);
		resetLocations();
		repaint(); 
	}
	
	public void resetLocations(){
		setDataReader(new DataReader());
		int versionNumber;
		for(int i=0; i<getM_VersionList().size(); i++){
			versionNumber=getM_VersionList().get(i).getM_VersionNumber();
//			getM_VersionList().get(i).setM_titlePoint(getDataReader().calculatePoint(versionNumber, 0 , 0));
			for(int j=0; j<getM_VersionList().get(i).getM_WordsList().size(); j++){
				Point point=getDataReader().calculatePoint(i, j, 100, getM_VersionList().get(i).getM_ConcordanceList().get(j).getM_RectWidth(), getM_VersionList().get(i).getM_ConcordanceList().get(j).getM_RectHeight());
				getM_VersionList().get(i).getM_ConcordanceList().get(j).setM_StringPoint(point);
				getM_VersionList().get(i).getM_ConcordanceList().get(j).setM_RectPoint(getM_VersionList().get(i).getM_ConcordanceList().get(j).getM_StringPoint());
								
			}
		}
	}
	
	public void scaleConcordancePanel(int scaleValue){
		setDataReader(new DataReader());
		setScaleValue(scaleValue);
		for(int i=0; i<getM_VersionList().size(); i++){
//			versionNumber=getM_VersionList().get(i).getM_VersionNumber();
//			getM_VersionList().get(i).setM_titlePoint(getDataReader().calculatePoint(i, 0 , scaleValue));
			for(int j=0; j<getM_VersionList().get(i).getM_WordsList().size(); j++){
				getM_VersionList().get(i).getM_ConcordanceList().get(j).setM_RectHeight(getScaleValue());

				Point point=getDataReader().calculatePoint(i, j, getScaleValue(), getM_VersionList().get(i).getM_ConcordanceList().get(j).getM_RectWidth(), getM_VersionList().get(i).getM_ConcordanceList().get(j).getM_RectHeight());
				
				getM_VersionList().get(i).getM_ConcordanceList().get(j).setM_StringPoint(point);
				getM_VersionList().get(i).getM_ConcordanceList().get(j).setM_RectPoint(getM_VersionList().get(i).getM_ConcordanceList().get(j).getM_StringPoint());
				getM_VersionList().get(i).getM_ConcordanceList().get(j).setM_RectWidth(getM_VersionList().get(i).getM_ConcordanceList().get(j).getM_Frequency(), getScaleValue());

			}
		}
		repaint();
	}
	
	
	public void hightLightColor(Point point){
		int versionNumber=point.x;
		int lineNumber=point.y;
		String hightlightToken=getM_VersionList().get(versionNumber).getM_ConcordanceList().get(lineNumber).getM_Token();

		for(int i=0; i<getM_VersionList().size(); i++){
			Version version=getM_VersionList().get(i);
			for(int j=0; j<version.getM_ConcordanceList().size(); j++){
				Concordance concordance=version.getM_ConcordanceList().get(j);
				if(!hightlightToken.equals(concordance.getM_Token())){
					

//					concordance.setM_TokenColor(getDataReader().calculateColor(lineNumber, 0.4f));
					concordance.setM_RectColor(getDataReader().calculateColor(concordance.getM_Frequency(), 0.4f));
					System.out.println("Color: "+concordance.getM_RectColor());
				}else{
					concordance.setM_RectColor(getDataReader().calculateColor(concordance.getM_Frequency(), 1f));
				}
					
			}
//				if(j!=lineNumber){
//					System.out.println("Worked");
//					version.getM_ConcordanceList().get(j).setM_TokenColor(getDataReader().calculateColor(lineNumber, 0.5f));
//				}
//			}
//			version.getM_ConcordanceList().get(lineNumber).setM_TokenColor(getDataReader().calculateColor(lineNumber, 0.5f));
//			version.getM_ConcordanceList().get(lineNumber).setM_RectColor(getDataReader().calculateColor(lineNumber, 0.5f));

			
		}
		repaint();
	}
	
	public void defaultColor(int lineNumber){
		for(int i=0; i<getM_VersionList().size(); i++){
			Version version=getM_VersionList().get(i);
			version.getM_ConcordanceList().get(lineNumber).setM_TokenColor(getDataReader().calculateColor(lineNumber, 1f));
			version.getM_ConcordanceList().get(lineNumber).setM_RectColor(getDataReader().calculateColor(lineNumber, 1f));

			
		}
		repaint();
	}
	/**
	 * Draw the version visualization on ConcordancePanel.
	 * This method is called from ConcordancePanel. 
	 */
	public void paintComponent(Graphics  g){
		MouseAction action=new MouseAction();
		this.addMouseListener(action);
		this.addMouseMotionListener(action);
		
		//create Graphics2D object to zoom ConcordancePanel
//		Graphics2D g2d=(Graphics2D)g;
//		g2d.scale(getZoomValue(),getZoomValue());
		
		//set the ConcordancePanelg
		this.setLayout(null);
		this.setBackground(Color.white);
		super.paintComponent(g);  
		
		//read and paint all 16 versions on the panel
		for(int i=0; i<getM_VersionList().size(); i++){
			Version version=getM_VersionList().get(i); //get current Version object to fetch information stored in the Version
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
//				g.fillRect(concordance.getM_RectPoint().x, concordance.getM_RectPoint().y, concordance.getM_RectWidth(), concordance.getM_RectHeight());
				int x=concordance.getM_RectPoint().x;
				int y=concordance.getM_RectPoint().y;
//				double x=(double)(concordance.getM_RectPoint().x);
//				double y=(double)(concordance.getM_RectPoint().y);
//				double height=(double)(concordance.getM_RectHeight());
				//draw rectangle using double values
				Graphics2D g2d = (Graphics2D)g;
				Rectangle rectangle=new Rectangle();
				rectangle.setRect(x, y, concordance.getM_RectWidth(), concordance.getM_RectHeight());
				g2d.fill(rectangle);
				
				
				//draw the lines for rectangle
				g.setColor(Color.GRAY);
//				g.drawRect(concordance.getM_RectPoint().x, concordance.getM_RectPoint().y, concordance.getM_RectWidth(), concordance.getM_RectHeight());
				//draw the lines connect same word between versions
				int versionCompare=i+1; //comparing from the second version
				//if this version is Base text
				if(version.getM_VersionName().equals("0000 BaseText Shakespeare.txt")&&versionCompare<getM_VersionList().size()){
					for(int m=0; m<getM_VersionList().get(versionCompare).getM_ConcordanceList().size(); m++){
						Concordance concordanceCompare=getM_VersionList().get(versionCompare).getM_ConcordanceList().get(m);
						
						for(int transArray=0; transArray<concordance.getM_TokenTranslations().size(); transArray++){
							if(concordanceCompare.getM_Token().equals(concordance.getM_TokenTranslations().get(transArray))){
								g.setColor(concordance.getM_RectColor()); 
								g.drawLine((int) (concordance.getM_StringPoint().x+concordance.getM_RectWidth()), concordance.getM_StringPoint().y, concordanceCompare.getM_RectPoint().x, concordanceCompare.getM_RectPoint().y);
							}
						}
					}
					
				}
				//versionCompare>1, means we the compared version start from the third one
				//versionCompare<m_VersionList.size(), controal the last version compared
				//if this version is not base text
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
							g.drawLine((int) (concordance.getM_RectPoint().x+concordance.getM_RectWidth()), concordance.getM_StringPoint().y, concordanceCompare.getM_RectPoint().x, concordanceCompare.getM_StringPoint().y);
						}
					}
					
				}
			}
		}
	}
	
	private class MouseAction implements MouseListener, MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent event) {
			// TODO Auto-generated method stub
			Point point=event.getPoint();
			int x=point.x;
			int y=point.y;
//			System.out.println("Get Point:"+event.getPoint());
		}

		@Override
		public void mouseMoved(MouseEvent event) {
			// TODO Auto-generated method stub
			
			

//			rangeListener(point,)
//			int x=point.x;
//			int y=point.y;
//			System.out.println("Moved:"+event.getSource().toString());
		
			repaint();
		}

		@Override
		public void mouseClicked(MouseEvent event) {
			// TODO Auto-generated method stub
			
			Point point=event.getPoint();
//			highlightPoint(point, getScaleValue());
			hightLightColor(getHighlightPoint(point, getScaleValue()));
//			rangeListener(point);
//			int x=point.x;
//			int y=point.y;
			System.out.println("getScaleValue(): "+getScaleValue());
		
			repaint();
		}

		@Override
		public void mouseEntered(MouseEvent event) {
			
//			Point point=event.getPoint();
//			hightLightColor(getHighlightPoint(point, getScaleValue()));
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent event) {
			// TODO Auto-generated method stub
//			Point point=event.getPoint();
//			defaultColor(getHighlightPoint(point, getScaleValue()));
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
