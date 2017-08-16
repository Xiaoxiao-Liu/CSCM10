import java.awt.Point;

import javax.swing.JPanel;

public class PaintPanelBob extends JPanel {
	
	public final static int FRAME_WIDTH=500;
	
	public final static int FRAME_HEIGHT=300;
	
	/**the maximum number of drawable points**/
	private final int MAX_POINTS=10000;
	
	private final int OVAL_WIDTH=4;
	
	private final int OVAL_HEIGHT=4;
	
	private int m_PointCount=0;
	
	private String m_SampleData;
	
	private Point[] m_Points=new Point[MAX_POINTS];
	
	/**
	 * 
	 * @return the current number of points
	 */
	public int GetPointCount(){
		return m_PointCount;
	}
	
	/**
	 * @return TRUE on success
	 */
	public boolean IncrementPointCount(){
		m_PointCount++;
		return true;
	}
	
	/**
	 * @return the current number of points
	 */
	public Point[] GetPoints(){
		return m_Points;
	}
	
	/**
	 * @return TRUE on success
	 */
	public boolean SetPoint(Point point){
		boolean test=false;
		if(test){
	          System.out.println("PaintPanel::SetPoint() - " + m_PointCount + ", " + point.toString());
		}
		m_Points[GetPointCount()]=point;
		return true;
	}
	
	/**
	 * @return the current sample data
	 */
	public String GetSampleData(){
		return m_SampleData;
	}
	
	/**
	 * An accessor method demonstrating passing data to this 
	 * PaintPanel object
	 * @return TRUE on success
	 */
	public boolean SetSampleData(String newSampleData){
		m_SampleData=newSampleData;
		return true;
	}
	
	/**
	 * We can use accessor methods to access this object from
	 * the Painter object.
	 * @return TRUE on success
	 */
	public boolean PassData(String newSampleData){
		this.SetSampleData(newSampleData);
		return true;
	}
	
	
	
	/**
	 * Constructor:
	 * set up GUI and register mouse event handler
	 */
	public PaintPanelBob(){
		boolean test=false;
		if(test){
			
		}
		
		
	}
	
	
	
	
	
	
	
	
	

}
