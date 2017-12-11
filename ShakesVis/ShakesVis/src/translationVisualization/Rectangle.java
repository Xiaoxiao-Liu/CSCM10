package translationVisualization;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


public class Rectangle extends JPanel {
	 /**the list of points of one rectangle**/
	private List<Point> m_RegionPoints = new ArrayList<Point>();
	
	public List<Point> getM_RegionPoints() {
		return m_RegionPoints;
	}

	public void setM_RegionPoints(List<Point> m_RegionPoints) {
		this.m_RegionPoints = m_RegionPoints;
	}

	public Point getM_RectPoint() {
		return m_RectPoint;
	}

	/**
	 * Pass the point of string to
	 * Item object and calculate location
	 * @param stringPoint
	 */
	public void setM_RectPoint(Point stringPoint) {
		int x=5;  // the distance between the string and rectangle
		int y=-15;  //the location of the rectangle needs to move up
		x=stringPoint.x+x;
		y=stringPoint.y+y;
		m_RectPoint=new Point(x, y);
	}

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

	public Color getM_RectColor() {
		return m_RectColor;
	}

	public void setM_RectColor(Color m_RectColor) {
		this.m_RectColor = m_RectColor;
	}

	public int getM_RectHeight() {
		return m_RectHeight;
	}

	private Point m_RectPoint;
	
	private Point m_RangeEndPoint;
	
	public Point getM_RangeEndPoint() {
		return m_RangeEndPoint;
	}

	public void setM_RangeEndPoint(Point rectPoint, int rectHeight, int rectWidth) {
		int x=rectPoint.x+rectWidth;
		int y=rectPoint.y+rectHeight;
		m_RangeEndPoint = new Point(x, y);
	}

	private int m_RectWidth;
	
	private final int m_RectHeight=17;
	
	private Color m_RectColor;

}
