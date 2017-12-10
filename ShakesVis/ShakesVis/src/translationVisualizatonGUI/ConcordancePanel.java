package translationVisualizatonGUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
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
	
	
	/**
	 * Constructor
	 * @param versionList
	 */
	public ConcordancePanel(List<Version> versionList) {
		m_VersionList = versionList;
		setM_VersionList(m_VersionList);
	//		repaint();
		}

	public boolean isFirstVersion() {
		return firstVersion;
	}

	public void setFirstVersion(boolean firstVersion) {
		this.firstVersion = firstVersion;
		repaint();
	}

	public boolean getM_OnAndOff() {
		return m_OnAndOff;
	}

	public void setOnAndOff(boolean onAndOff) {
		m_OnAndOff = onAndOff;
		repaint();
	}

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
		int lineNumber;
		int versionNumber;
		double scaleValueProcess=scaleValue/100.0;
		int spacingY=25;
		spacingY=(int) (spacingY*scaleValueProcess*scaleValueProcess);
		int spacingX=150;
		spacingX=(int) (spacingX*scaleValueProcess*scaleValueProcess);
		int versionNumberAdjast=75;
		versionNumber=(eventPoint.x-versionNumberAdjast)/spacingX;
		int lineNumberAdjast=4; //The line is moved down by 4 to assure the line clicked is right one
		lineNumber=eventPoint.y/spacingY-lineNumberAdjast;
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
		scaleConcordancePanel(getScaleValue());
		repaint(); 
	}
	
	public void resetLocations(){
		setDataReader(new DataReader());
		int versionNumber;
		for(int i=0; i<getM_VersionList().size(); i++){
			versionNumber=getM_VersionList().get(i).getM_VersionNumber();
//			getM_VersionList().get(i).setM_titlePoint(getDataReader().calculatePoint(versionNumber, 0 , 0));
			for(int j=0; j<getM_VersionList().get(i).getM_WordsList().size(); j++){
				Point point=getDataReader().calculatePoint(i, j, 100);
				getM_VersionList().get(i).getM_ConcordanceList().get(j).setM_StringPoint(point);
				getM_VersionList().get(i).getM_ConcordanceList().get(j).setM_RectPoint(getM_VersionList().get(i).getM_ConcordanceList().get(j).getM_StringPoint());
								
			}
		}
	}
	
//	public void getBooleanValue(boolean bool){
//		if(bool){
//			repaint();
//		}
//	}
	
	/**
	 * rescale concordance panel, recalculate the locations and widths
	 * @param scaleValue
	 */
	public void scaleConcordancePanel(int scaleValue){
		setDataReader(new DataReader()); 
		setScaleValue(scaleValue); //pass scale value to concordance panel class
		for(int i=0; i<getM_VersionList().size(); i++){ //review the version list
			Point titlePoint=getDataReader().calculatePoint(i, 0, scaleValue);
			getM_VersionList().get(i).setM_titlePoint(titlePoint);

			for(int j=0; j<getM_VersionList().get(i).getM_WordsList().size(); j++){// read each version object
				//reset rectangle height
				getM_VersionList().get(i).getM_ConcordanceList().get(j).setM_RectHeight(getScaleValue()); 
				//reset string location
				int lineIncrement=3;
				Point stringPoint=getDataReader().calculatePoint(i, j+lineIncrement, getScaleValue());
				getM_VersionList().get(i).getM_ConcordanceList().get(j).setM_StringPoint(stringPoint);
				//reset rectangle location
				getM_VersionList().get(i).getM_ConcordanceList().get(j).setM_RectPoint(getM_VersionList().get(i).getM_ConcordanceList().get(j).getM_StringPoint());
				//reset rectangle width
				getM_VersionList().get(i).getM_ConcordanceList().get(j).setM_RectWidth(getM_VersionList().get(i).getM_ConcordanceList().get(j).getM_Frequency(), getScaleValue());
			
				getM_VersionList().get(i).getM_ConcordanceList().get(j).setM_Token_Font(getScaleValue());
			}
		}
		repaint();
	}
	
	//when choose one color on color panel
	/**
	 * if one color on color legend panel is clicked,
	 * all tokens with the ame frequency will be highlighted
	 * @param token
	 */
	public void freqHighlight(String token){
//		firstVersion=true;
		int size=13;
		int frequency=Integer.parseInt(token);
		float transparentValue=0.4f;
		Font M_Token_Font=new Font("sansserif",Font.BOLD, size);
		
		for(int i=0; i<getM_VersionList().size(); i++){
			Version version=getM_VersionList().get(i);
			for(int j=0; j<version.getM_ConcordanceList().size(); j++){
				Concordance concordance=version.getM_ConcordanceList().get(j);
				if(!(frequency==concordance.getM_Frequency())){
					Font token_Font=new Font("sansserif",Font.PLAIN, size);
					concordance.setM_Token_Font(getScaleValue());
					concordance.setM_rectLine(false);
					concordance.setM_RectColor(getDataReader().calculateColor(concordance.getM_Frequency(), transparentValue));
				}else{
					concordance.setM_RectColor(getDataReader().calculateColor(concordance.getM_Frequency(), 1f));
					concordance.setM_Token_Font(getScaleValue());
					concordance.setM_rectLine(true);
				}
				}
			}
		repaint();
//		firstVersion=false;
	}
	
	//when choose one word on concordance panel
	/**
	 * set the transparency of the rectangles' when one token is clicked
	 * @param point
	 */
	public void tokenHighLight(Point point){
		int versionNumber=point.x;
		int lineNumber=point.y;
		int size=13;
		float transparentValue=0.4f;
		Version choosenVersion=getM_VersionList().get(versionNumber);
		Concordance choosenConcordance=choosenVersion.getM_ConcordanceList().get(lineNumber);
		//if tokens in base text are clicked 
		if(choosenVersion.getM_Author().equals("BaseText Shakespeare")){
			clickBaseText(versionNumber, lineNumber, choosenVersion, choosenConcordance);
		}else{//if translation version is clicked
			clickTransVersion(versionNumber, lineNumber, choosenVersion, choosenConcordance);
		}
		repaint();
	}
	
	public void clickTransVersion(int versionNumber, int lineNumber, Version choosenVersion, Concordance choosenConcordance){
		int size=13;
		float transparentValue=0.4f;
		String choosenToken=getM_VersionList().get(versionNumber).getM_ConcordanceList().get(lineNumber).getM_Token();
		Font M_Token_Font=new Font("sansserif",Font.BOLD, size);
		for(int i=0; i<getM_VersionList().size(); i++){
			Version version=getM_VersionList().get(i);
			for(int j=0; j<version.getM_ConcordanceList().size(); j++){
				Concordance concordance=version.getM_ConcordanceList().get(j);
				if(version.getM_Author().equals("BaseText Shakespeare")&&concordance.getM_TokenTranslations().contains(choosenConcordance.getM_Token())){
//					if(concordance.getM_TokenTranslations().contains(choosenConcordance.getM_Token())){
						concordance.setM_RectColor(getDataReader().calculateColor(concordance.getM_Frequency(), 1));
						concordance.setM_Token_Font(getScaleValue());
						concordance.setM_rectLine(true);
//					}
				}else{
					if(!choosenToken.equals(concordance.getM_Token())){
						Font token_Font=new Font("sansserif",Font.PLAIN, size);
						concordance.setM_Token_Font(getScaleValue());
						concordance.setM_rectLine(false);
						concordance.setM_TokenColor(getDataReader().calculateColor(lineNumber, transparentValue));
						concordance.setM_RectColor(getDataReader().calculateColor(concordance.getM_Frequency(), transparentValue));
					}else{
						concordance.setM_RectColor(getDataReader().calculateColor(concordance.getM_Frequency(), 1));
						concordance.setM_Token_Font(getScaleValue());
						concordance.setM_rectLine(true);
					}
				}
			}
		}
		repaint();
	}
	
	public void clickBaseText(int versionNumber, int lineNumber, Version choosenVersion, Concordance choosenConcordance){
		int size=13;
		float transparentValue=0.4f;
//		Version choosenVersion=getM_VersionList().get(versionNumber);
//		Concordance choosenConcordance=choosenVersion.getM_ConcordanceList().get(lineNumber);
		Font M_Token_Font=new Font("sansserif",Font.BOLD, size);
		for(int m=0; m<getM_VersionList().size(); m++){
			Version version=getM_VersionList().get(m);
			for(int n=0; n<version.getM_ConcordanceList().size(); n++){
				if(version.getM_Author().equals("BaseText Shakespeare")){
					choosenConcordance.setM_Token_Font(getScaleValue());
					choosenConcordance.setM_rectLine(true);
					choosenConcordance.setM_RectColor(getDataReader().calculateColor(choosenConcordance.getM_Frequency(), 1));
				}
				Concordance concordance=version.getM_ConcordanceList().get(n);
				if(!choosenConcordance.getM_TokenTranslations().contains(concordance.getM_Token())){
					Font token_Font=new Font("sansserif",Font.PLAIN, size);
					concordance.setM_Token_Font(getScaleValue());
					concordance.setM_rectLine(false);
					concordance.setM_TokenColor(getDataReader().calculateColor(lineNumber, transparentValue));
					concordance.setM_RectColor(getDataReader().calculateColor(concordance.getM_Frequency(), transparentValue));
				}else{
					concordance.setM_RectColor(getDataReader().calculateColor(concordance.getM_Frequency(), 1f));
					concordance.setM_Token_Font(getScaleValue());
					concordance.setM_rectLine(true);
//					System.out.println(choosenConcordance.getM_Token());
				}
			}
		}
		repaint();
	}
	
	/**
	 * Draw the stirng of titles, including author names and publish years
	 * @param version - current Version object
	 * @param g - the Graphic to be drawed
	 */
	public void drawTitleString(Version version, Graphics g){
		g.setColor(Color.black); //set color of strings(tokens)
		g.setFont(version.getM_WORD_FONT()); //set font of strings
		//drawString(String, int x, int y), x and y represents the leftmost position of the string
		String author=version.getM_Author();
		String year=String.valueOf(version.getM_VersionYear());
		int yearPositionAdded=20;
		g.drawString(author, version.getM_titlePoint().x, version.getM_titlePoint().y);
		g.drawString(year, version.getM_titlePoint().x, version.getM_titlePoint().y+yearPositionAdded);
	}
	
	/**
	 * Draw token strings
	 * @param g - the Graphic to be drawed
	 */
	public void drawTokenStrings(Graphics g){
		//set color of strings/tokens
		g.setColor(Color.black);
		//paint only token without numbers
		String string;
		string=getM_Concordance().getM_Token()+" ";
//		if(!isFreqOrTfidf()){
//			string=getM_Concordance().getM_Token()+" " +getM_Concordance().getM_Frequency();
//		}else{
//			string=getM_Concordance().getM_Token()+" " +getM_Concordance().getM_TfIdf();
//		}
		
		
		g.setFont(getM_Concordance().getM_WORD_FONT());
		//draw the current string/token, drawString(String, int x, int y)
		// a font metrics object used to set the alignment of version tokens
		FontMetrics fontMetrics = g.getFontMetrics();
		g.drawString(string, getM_Concordance().getM_StringPoint().x-fontMetrics.stringWidth(string), getM_Concordance().getM_StringPoint().y);
	}
	
	/**
	 * Draw all the rectangles for tokens
	 * @param g - the Graphic to be drawed
	 */
	public void drawRectangles(Graphics g){
		
		//set color of rectangles
		g.setColor(getM_Concordance().getM_RectColor());
		//drawr filled rectangles, fillRect(int x, int y, int width, int height),
		//x and y represent the top left position of the rectangle,
		int x=getM_Concordance().getM_RectPoint().x;
		int y=getM_Concordance().getM_RectPoint().y;
		//draw rectangle using double values
		Graphics2D g2d = (Graphics2D)g;
		Rectangle rectangle=new Rectangle();
		rectangle.setRect(x, y, getM_Concordance().getM_RectWidth(), getM_Concordance().getM_RectHeight());
		g2d.fill(rectangle);
		
		//draw line around rectangle
		if(getM_Concordance().isM_rectLine()==true){
			Graphics2D g2 = (Graphics2D)g;
			g2.setStroke(new BasicStroke(2)); //make the line thicker
			g2.setColor(Color.black);
			g2.drawRect(getM_Concordance().getM_RectPoint().x, getM_Concordance().getM_RectPoint().y, (int) getM_Concordance().getM_RectWidth(), getM_Concordance().getM_RectHeight());
			
		}
	}
	/**
	 * Draw the version visualization on ConcordancePanel.
	 * This method is called from ConcordancePanel. 
	 */
	public void paintComponent(Graphics g){
//		repaint();
		MouseAction action=new MouseAction();
		this.addMouseListener(action);
		this.addMouseMotionListener(action);
		//set the ConcordancePanel
		this.setLayout(null);
		this.setBackground(Color.white);
		super.paintComponent(g);  
		//read and paint all 16 versions on the panel
		for(int i=0; i<getM_VersionList().size(); i++){
			setM_Version(getM_VersionList().get(i));
			// draw title Strings
			drawTitleString(getM_Version(), g);
			//read and paint all tokens/Concordances in the current Version
			for(int j=0; j<getM_Version().getM_ConcordanceList().size(); j++){
				setM_Concordance(getM_Version().getM_ConcordanceList().get(j));
				
				//get boolean value from "On/Off" button
				if(this.getM_OnAndOff()==true){
					drawTokenStrings(g);
				}
				
				drawRectangles(g);
				
				//draw the lines connect same word between versions
				int versionCompare=i+1; //comparing from the second version
				
				//if this version is not base text
//				else if(!getM_Version().getM_VersionName().equals("0000 BaseText Shakespeare.txt")&&versionCompare<getM_VersionList().size()){ 
			    if(!getM_Version().getM_VersionName().equals("1604 BaseText Shakespeare.txt")&&versionCompare<getM_VersionList().size()){ 
					//read and compare all the concordances in the compared version
					for(int k=0; k<getM_VersionList().get(versionCompare).getM_ConcordanceList().size(); k++){
						Concordance concordanceCompare=getM_VersionList().get(versionCompare).getM_ConcordanceList().get(k);
						//compare the two tokens
						if(concordanceCompare.getM_Token().equals(getM_Concordance().getM_Token())){
							drawLines(concordanceCompare, g);
							//set line colors
////							g.setColor(getM_Concordance().getM_TokenColor()); // color edge according to strings
//							g.setColor(getM_Concordance().getM_RectColor()); // color edge according to frequency (same with rectangle color)
//							//drawLine(int x1, int y1, int x2, int y2), x1 and y1 represent start point of the line, x2 and y2 represent end point of the line
//							g.drawLine((int) (getM_Concordance().getM_RectPoint().x+getM_Concordance().getM_RectWidth()), getM_Concordance().getM_StringPoint().y, concordanceCompare.getM_RectPoint().x, concordanceCompare.getM_StringPoint().y);
						}
					}
					
				}
			}
		}
		repaint();
	}
	
	public void drawLines(Concordance concordanceCompare, Graphics g){
		//set line colors
//		g.setColor(getM_Concordance().getM_TokenColor()); // color edge according to strings
		g.setColor(getM_Concordance().getM_RectColor()); // color edge according to frequency (same with rectangle color)
		
		//drawLine(int x1, int y1, int x2, int y2), x1 and y1 represent start point of the line, x2 and y2 represent end point of the line
		g.drawLine((int) (getM_Concordance().getM_RectPoint().x+getM_Concordance().getM_RectWidth()), getM_Concordance().getM_StringPoint().y, concordanceCompare.getM_RectPoint().x, concordanceCompare.getM_StringPoint().y);
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
		}
		   

		@Override 
		public void mouseClicked(MouseEvent event) {
			// TODO Auto-generated method stub
			Point point=event.getPoint();
//			highlightPoint(point, getScaleValue());
			tokenHighLight(getHighlightPoint(point, getScaleValue()));
//			rangeListener(point);
//			int x=point.x;
//			int y=point.y;
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

	/** the list of versions passed from translation visualization */
	public List<Version> m_VersionList=new ArrayList<Version>();

	private List<Version> m_VersionListCopied=new ArrayList<Version>();

	/**	an DataReader object */
	private DataReader dataReader;

	private boolean firstVersion=false;
	
	public boolean freqOrTfidf=false;

	public boolean isFreqOrTfidf() {
		return freqOrTfidf;
	}

	public void setFreqOrTfidf(boolean freqOrTfidf) {
		this.freqOrTfidf = freqOrTfidf;
	}

	private int scaleValue;

	/** one Version object */
	Version m_singleVersion=new Version();

	/** a boolean value passed from "Text On/Off" button to turn on and off the text on panel */
	private boolean m_OnAndOff=true;

	/** the default integer used to set zoom level, we use 10 is because when the first time 
	 * the panel painted, we do not get zoom value from 
	 * slider listener(see translationVisualization.getM_Slider().addChangeListener()) */
	private double m_ZoomValue=40; //initiate zoomvalue

	private int m_VersionNumber=0;
	
	/**	a Version object */
	private Version m_Version;
	
	public Version getM_Version() {
		return m_Version;
	}

	public void setM_Version(Version m_Version) {
		this.m_Version = m_Version;
	}

	/**	a concordance object */
	private Concordance m_Concordance;

	public Concordance getM_Concordance() {
		return m_Concordance;
	}

	public void setM_Concordance(Concordance m_Concordance) {
		this.m_Concordance = m_Concordance;
	}
	
	

}
