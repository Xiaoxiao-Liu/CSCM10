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

	public void setM_StringPoint(Point m_StringPoint) {
		this.m_StringPoint = m_StringPoint;
	}

	public Color getM_Color() {
		return m_Color;
	}

	public void setM_Color(Color m_Color) {
		this.m_Color = m_Color;
	}

	public Point getM_RectPoint() {
		return m_RectPoint;
	}

	public void setM_RectPoint(Point m_RectPoint) {
		this.m_RectPoint = m_RectPoint;
	}

	public int getM_RectWidth() {
		return m_RectWidth;
	}

	public void setM_RectWidth(int m_RectWidth) {
		this.m_RectWidth = m_RectWidth;
	}

    
	
	
}
