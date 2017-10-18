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
	
	/**a way to access the tokens*/
	private String m_Token;
	
	/**the frequency number of each token*/
	private int m_Frequency;
	
	/**use point to show the location of each token*/
	private Point m_StringPoint;
	
	/** the color of each token*/
	private Color m_TokenColor;
	
	/**the location of the rectangle bar for each token */
	private Point m_RectPoint;

    /**the width of each rectangle */
	private int m_RectWidth;
	
	/**the height of each rectangle*/
    private final int m_RectHeight=17;
    
    /** the color of each rectangle*/
	private Color m_RectColor;
	
	/**the right-bottom point of rectangle applied to mouse listener method */
	private Point m_RangeEndPoint;
	
    public Point getM_RangeEndPoint() {
		return m_RangeEndPoint;
	}

	public void setM_RangeEndPoint(Point rectPoint, int rectHeight, int rectWidth) {
		int x=rectPoint.x+rectWidth;
		int y=rectPoint.y+rectHeight;
		m_RangeEndPoint = new Point(x, y);
	}

	/** the font of tokens */
	private final Font M_Token_Font=new Font("sansserif",Font.PLAIN, 13);
	
	/**a way to access the English token translation*/
	private String m_TokenTranslation;
	
	/**a way to access the lemmatized English token*/
	private String m_TokenLemma;
	
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
		int y=-15;  //the location of the rectangle needs to move up
		x=stringPoint.x+x;
		y=stringPoint.y+y;
		m_RectPoint=new Point(x, y);
	}

	/**
	 * @return the width of the rectangle
	 */
	public int getM_RectWidth() {
		return m_RectWidth;
	}

	/**
	 * Pass the frequency number to 
	 * compute the width of rectangle
	 * @param wordFrequency 
	 */
	public void setM_RectWidth(int wordFrequency) {
		int blockWidth=4; //width of each rectangle block
		m_RectWidth=wordFrequency*blockWidth;
	}

    
	
	
}  /* end class Concordance */
