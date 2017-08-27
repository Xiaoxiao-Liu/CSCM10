package translationVisualization;

import java.awt.Color;
import java.awt.Point;

/**
 * @author Rosa
 *
 */
public class Concordance {
	/**the string of each word*/
	private String m_Word;
	
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
	
	private int m_RectHeight=25;
	

	public int getM_RectHeight() {
		return m_RectHeight;
	}

	public void setM_RectHeight(int m_RectHeight) {
		this.m_RectHeight = m_RectHeight;
	}

	public String getM_Word() {
		return m_Word;
	}

	public void setM_Word(String m_Word) {
		this.m_Word = m_Word;
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
		int x=8;
		int y=-8;
		x=x+stringPoint.x;
		y=y+stringPoint.y;
		m_RectPoint=new Point(x, y);
	}

	public int getM_RectWidth() {
		return m_RectWidth;
	}

	public void setM_RectWidth(int wordFrequency) {
		int blockWidth=25; //width of each rectangle block
		m_RectWidth=wordFrequency*blockWidth;
	}

    
	
	
}
