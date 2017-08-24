package object;

import java.awt.Color;
import java.awt.Point;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TopWord {
	
	private String m_word;
	private int m_frequency;
	private Point m_point;
	private Color m_color;
	
	public String getM_word() {
		return m_word;
	}
	public void setM_word(String m_word) {
		this.m_word = m_word;
	}
	public int getM_frequency() {
		return m_frequency;
	}
	public void setM_frequency(int m_frequency) {
		this.m_frequency = m_frequency;
	}
	public Point getM_point() {
		return m_point;
	}
	
	public void setM_point(int versionNumber, int lineNumber) {
		int x=30;
		int y=30;
		int columnSpace=200;
		int lineSpace=20;
		x=x+columnSpace*versionNumber;
		y=y+lineSpace*lineNumber;
		m_point=new Point(x, y);
	}
	
	public Color getM_color() {
		return m_color;
	}
	
	public void setM_color(int stringNumber) {
		m_color=new Color(255-stringNumber,stringNumber,stringNumber, 150);
	}
	
	
}
