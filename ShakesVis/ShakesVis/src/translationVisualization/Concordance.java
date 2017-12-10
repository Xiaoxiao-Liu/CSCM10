package translationVisualization;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rosa
 *
 */
public class Concordance {
	
	private List<String> m_Tokens=new ArrayList<String>();
	
	 public List<Rectangle> getM_Rectangles() {
		return m_Rectangles;
	}

	public void setM_Rectangles(List<Rectangle> m_Rectangles) {
		this.m_Rectangles = m_Rectangles;
	}

	private List<String> m_TokenTranslations;

	private List<Integer> m_Frequencies=new ArrayList<Integer>();
	
	private List<Rectangle> m_Rectangles=new ArrayList<Rectangle>();
	
	/**a boolean value used to highlight choosen rectangles*/
	private boolean m_rectLine=false;
	
	public boolean isM_rectLine() {
		return m_rectLine;
	}

	/**
	 * if the value turns to true, 
	 * this boolean value will be passed to graphic class and set this token's rectangle highLighted
	 * @param m_rectLine
	 */
	public void setM_rectLine(boolean m_rectLine) {
		this.m_rectLine = m_rectLine;
	}

	/**a way to access the tokens*/
	private String m_Token;
	
	/**the frequency number of each token*/
	private int m_Frequency;
	
	/**	the tf-idf value for eath token */
	private int m_TfIdf;
	
	public int getM_TfIdf() {
		return m_TfIdf;
	}

	public void setM_TfIdf(int m_TfIdf) {
		this.m_TfIdf = m_TfIdf;
	}

	/**use point to show the location of each token*/
	private Point m_StringPoint;
	
	/** the color of each token*/
	private Color m_TokenColor;
	
	/**the location of the rectangle bar for each token */
	private Point m_RectPoint;

    /**the width of each rectangle */
	private double m_RectWidth;
	
	/**the height of each rectangle*/
    private int m_RectHeight;
    
    public void setM_RectHeight( int scaleValue) {
		
		int originalHeight=25;
		double scaleLevel=scaleValue/100.0;
		m_RectHeight=(int) (originalHeight*scaleLevel*scaleLevel);
	}

	/** the color of each rectangle*/
	private Color m_RectColor;
	
	private int fontSize=13;
	/** the font of tokens */
	private Font M_Token_Font;
//	private Font M_Token_Font=new Font("sansserif",Font.PLAIN, fontSize); 
	public Font getM_Token_Font() {
		return M_Token_Font;
	}

	public void setM_Token_Font(int scalValue) {
		
		M_Token_Font=new Font("sansserif",Font.PLAIN, fontSize);
		
	}

	/**a way to access the English token translation*/
	private String m_TokenTranslation;

	/**the right-bottom point of rectangle applied to mouse listener method */
	private Point m_RangeEndPoint;

	/**a way to access the lemmatized English token*/
	private String m_TokenLemma;

	public Point getM_RangeEndPoint() {
		return m_RangeEndPoint;
	}

	public void setM_RangeEndPoint(Point rectPoint, int rectHeight, int rectWidth) {
		int x=rectPoint.x+rectWidth;
		int y=rectPoint.y+rectHeight;
		m_RangeEndPoint = new Point(x, y);
	}

	public List<String> getM_Tokens() {
		return m_Tokens;
	}

	public void setM_Tokens(List<String> m_Tokens) {
		this.m_Tokens = m_Tokens;
	}

	public List<Integer> getM_Frequencies() {
		return m_Frequencies;
	}

	public void setM_Frequencies(List<Integer> m_Frequencies) {
		this.m_Frequencies = m_Frequencies;
	}

	public List<String> getM_TokenTranslations() {
		return m_TokenTranslations;
	}

	public void setM_TokenTranslations(List<String> m_TokenTranslations) {
		this.m_TokenTranslations = m_TokenTranslations;
	}

	public String getM_TokenTranslation() {
		return m_TokenTranslation;
	}

	public void setM_TokenTranslation(String m_TokenTranslation) {
		this.m_TokenTranslation = m_TokenTranslation;
	}

	public String getM_TokenLemma() {
		return m_TokenLemma;
	}

	public void setM_TokenLemma(String m_TokenLemma) {
		this.m_TokenLemma = m_TokenLemma;
	}

	public Color getM_RectColor() {
		return m_RectColor;
	}

	public void setM_RectColor(Color m_RectColor) {
		this.m_RectColor = m_RectColor;
	}

	/**
	 * @return tokens
	 */
	public String getM_Token() {
		return m_Token;
	}

	/**
	 * An accessor method to pass string to
	 * Concordance object and set token  
	 * @param m_Token
	 */
	public void setM_Token(String m_Token) {
		this.m_Token = m_Token;
	}

	/**
	 * @return the fond
	 */
	public Font getM_WORD_FONT() {
		return M_Token_Font;
	}

	/**
	 * @return the height of rectangle
	 */
	public int getM_RectHeight() {
		return m_RectHeight;
	}

	/**
	 * @return the frequency of each token
	 */
	public int getM_Frequency() {
		return m_Frequency;
	}

	/**
	 * An accessor method to pass number to 
	 * Concordance object and set frequency
	 * @param m_Frequency 
	 */
	public void setM_Frequency(int m_Frequency) {
		this.m_Frequency = m_Frequency;
	}

	/**
	 * @return the location point of each token
	 */
	public Point getM_StringPoint() {
		return m_StringPoint;
	}

	/**
	 * An accessor method to pass point to 
	 * Concordance object and set location 
	 * of the string
	 * @param point
	 */
	public void setM_StringPoint(Point point) {
		m_StringPoint=point;
	}

	/**
	 * @return the color of each token
	 */
	public Color getM_TokenColor() {
		return m_TokenColor;
	}

	/**
	 * An accessor method to pass the color to
	 * Concordance object and set color
	 * @param color
	 */
	public void setM_TokenColor(Color color) {
		this.m_TokenColor=color;

	}

	/**
	 * @return the location of the rectangle
	 */
	public Point getM_RectPoint() {
		return m_RectPoint;
	}

	/**
	 * Pass the point of string to
	 * Concordance object and calculate location
	 * @param stringPoint
	 */
	public void setM_RectPoint(Point stringPoint) {
		int x=5;  // the distance between the string and rectangle
		int y=-16;  //the location of the rectangle needs to move up
		x=stringPoint.x+x;
		y=stringPoint.y+y;
		m_RectPoint=new Point(x, y);
	}

	/**
	 * @return the width of the rectangle
	 */
	public double getM_RectWidth() {
		return m_RectWidth;
	}

	/**
	 * Pass the frequency number to 
	 * compute the width of rectangle
	 * @param wordFrequency 
	 */
	public void setM_RectWidth(int wordFrequency, double scaleValue) {
		double scaleLevel=scaleValue/100.0;
		double unit=4.7*scaleLevel; //width of each rectangle block
//		int originalValue=wordFrequency*blockWidth;
		m_RectWidth=wordFrequency*unit*scaleLevel;
	}

    
	
	
}  /* end class Concordance */
