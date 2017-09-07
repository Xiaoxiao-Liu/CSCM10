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
	/**the string of each word*/
	private String m_Token;
	
	/**the frequency of each word*/
	private int m_Frequency;
	
	/**the point of each string*/
	private Point m_StringPoint;
	
	/** the color of each string*/
	private Color m_Color;
	
	/**the point of the rectangle bar for each string */
	private Point m_RectPoint;

    /**the width of each rectangle */
	private int m_RectWidth;
	
	 /**the list of all tokens */
    private List<Concordance> m_TokenList=new ArrayList<Concordance>();
	
	/**the height of the rectangle*/
    private final int m_RectHeight=17;
	
    /** the font of the words */
	private final Font M_WORD_FONT=new Font("sansserif",Font.PLAIN, 13);
	

	public Font getM_WORD_FONT() {
		return M_WORD_FONT;
	}

	public int getM_RectHeight() {
		return m_RectHeight;
	}

	public String getM_Token() {
		return m_Token;
	}

	public void setM_Token(String m_Token) {
		this.m_Token = m_Token;
	}

	public int getM_Frequency() {
		return m_Frequency;
	}

	public void setM_Frequency(int m_Frequency) {
		this.m_Frequency = m_Frequency;
	}

	public Point getM_StringPoint() {
		return m_StringPoint;
	}

	public void setM_StringPoint(Point point) {
		m_StringPoint=point;
	}

	public Color getM_Color() {
		return m_Color;
	}

	public void setM_Color(Color color) {
		this.m_Color=color;

	}

	public Point getM_RectPoint() {
		return m_RectPoint;
	}

	public void setM_RectPoint(Point stringPoint) {
		int x=5;
		int y=-15;
		x=x+stringPoint.x;
		y=y+stringPoint.y;
		m_RectPoint=new Point(x, y);
	}

	public int getM_RectWidth() {
		return m_RectWidth;
	}

	public void setM_RectWidth(int wordFrequency) {
		int blockWidth=4; //width of each rectangle block
		m_RectWidth=wordFrequency*blockWidth;
	}

    
	
	
}
