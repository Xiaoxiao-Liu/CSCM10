package translationVisualization;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Version {

    /**the list of all words of one version */
	private List<String> m_Words=new ArrayList<String>();

    /**the name of the version */
	private String m_VersionName;

    /**the author of the version */
    private String m_Author;

    /**the number of the version */
	private int m_VersionNumber;

    /**the year of the version */
    private int m_VersionYear;

    /**a concordance object */
	private Concordance m_Concordance=new Concordance();

    /**the list of all concordances */
    private List<Concordance> m_ConcordanceList=new ArrayList<Concordance>();
    
    /**the point of title */
    private Point m_titlePoint= new Point();

	public List<String> getM_Words() {
		return m_Words;
	}

	public void setM_Words(List<String> m_Words) {
		this.m_Words = m_Words;
	}

	public String getM_VersionName() {
		return m_VersionName;
	}

	public void setM_VersionName() {
		this.m_VersionName = m_VersionName;
	}

	public String getM_Author() {
		return m_Author;
	}

	public void setM_Author(String versionInformation) {
		int startPosition=5;
		int endPosition=versionInformation.length()-4;
		m_Author = versionInformation.substring(startPosition, endPosition);
	}

	public int getM_VersionNumber() {
		return m_VersionNumber;
	}

	public void setM_VersionNumber(int m_VersionNumber) {
		
		this.m_VersionNumber = m_VersionNumber;
	}

	public int getM_VersionYear() {
		return m_VersionYear;
	}

	public void setM_VersionYear(String versionInformation) {
		int startPosition=0;
		int endPosition=4;
		m_VersionYear=Integer.parseInt(versionInformation.substring(startPosition, endPosition));
	}

	public Concordance getM_Concordance() {
		return m_Concordance;
	}

	public void setM_Concordance(Concordance m_Concordance) {
		this.m_Concordance = m_Concordance;
	}

	public List<Concordance> getM_ConcordanceList() {
		return m_ConcordanceList;
	}

	public void setM_ConcordanceList(Concordance m_Concordance) {
		m_ConcordanceList.add(m_Concordance);
	}

	public Point getM_titlePoint() {
		return m_titlePoint;
	}

	public void setM_titlePoint(Point point) {
		
		this.m_titlePoint =point;
	} 
    
    
    
}
