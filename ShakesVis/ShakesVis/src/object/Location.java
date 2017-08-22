package object;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Location {
	private int m_xCoordinate;
	private int m_yCoordinate;
	private Point m_point;
	private List<Point> m_pointList=new ArrayList<Point>();
	private int listLength=50;
	
	public int getM_xCoordinate() {
		return m_xCoordinate;
	}
	
	public int getM_yCoordinate() {
		return m_yCoordinate;
	}
	
	public Point getM_point() {
		return m_point;
	}
	
	public void IncrementM_xCoordinate(int columnCount) {
		int columnGap=30;
		m_xCoordinate=columnGap*columnCount;
	}
	
	public void IncrementM_yCoordinate(int lineCount) {
		int lineGap=15;
		m_yCoordinate = lineGap*lineCount;
	}
	
	
	public void setM_point() {
		m_point = new Point(getM_xCoordinate(), getM_yCoordinate());
	}
	
	public List<Point> getM_pointList() {
		return m_pointList;
	}
	public void setM_pointList(List<Point> m_pointList) {
		for(int i=0; i<listLength; i++){
			m_pointList.add(getM_point());
		}
		this.m_pointList = m_pointList;
	}
	
	public boolean resetYCoordinate(int gap) {
		m_yCoordinate=gap;
		return true;
	}
	
	public boolean resetXCoordinate(int gap) {
		m_xCoordinate=gap;
		return true;
	}
	
	

}
