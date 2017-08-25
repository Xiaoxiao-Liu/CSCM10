package object;

import java.awt.Color;
import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

public class TopWord {
	
	private String m_word;
	private int m_frequency;
	private Point m_stringPoint;
	private Color m_color;
	private Point m_rectPoint;
	private int m_rectWidth;
	
	public int getM_rectWidth() {
		return m_rectWidth;
	}
	public void setM_rectWidth(int wordFrequency) {
		int rectWidth=25;
		m_rectWidth=wordFrequency*rectWidth;
	}
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
	public Point getM_rectPoint() {
		return m_rectPoint;
	}
	public void setM_rectPoint(Point stringPoint) {
		int x=10;
		int y=-8;
		x=x+stringPoint.x;
		y=y+stringPoint.y;
		m_rectPoint=new Point(x, y);
	}
	
	public Point getM_stringPoint() {
		return m_stringPoint;
	}
	
	public void setM_stringPoint(int versionNumber, int lineNumber) {
		int x=30;
		int y=30;
		int columnSpace=200;
		int lineSpace=20;
		x=x+columnSpace*versionNumber;
		y=y+lineSpace*lineNumber;
		m_stringPoint=new Point(x, y);
	}
	
	public Color getM_color() {
		return m_color;
	}
	
	public void setM_color(int stringNumber) {
		int r=ThreadLocalRandom.current().nextInt(stringNumber, 255);
		int b=ThreadLocalRandom.current().nextInt(stringNumber, 255);
		int g=ThreadLocalRandom.current().nextInt(stringNumber, 255);
		m_color=new Color(255-stringNumber,stringNumber+20,230-stringNumber, 150);
//		System.out.println(m_color);
	}
	
	
}
