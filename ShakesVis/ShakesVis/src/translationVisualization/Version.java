package translationVisualization;

import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Version {

    /**the list of all words of one version */
	private List<String> m_WordsList=new ArrayList<String>();

	 /**the list of all translated words of one version */
	private List<String> m_tokenTranslation=new ArrayList<String>();

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
    
    /**the location of title */
    private Point m_titlePoint= new Point();
    
    /**the font of the title */
	private final Font M_Word_Font=new Font("TimesRoman",Font.PLAIN, 12);

	public List<String> getM_tokenTranslation() {
		return m_tokenTranslation;
	}

	public void setM_tokenTranslation(List<String> m_tokenTranslation) {
		this.m_tokenTranslation = m_tokenTranslation;
	}

	/**
	 * @return the fond of the title
	 */
	public Font getM_WORD_FONT() {
		return M_Word_Font;
	}

	/**
	 * @return a list of all tokens in one version 
	 */
	public List<String> getM_WordsList() {
//		System.out.println(m_WordsList);
		return m_WordsList;
	}

	/**
	 * An accessor method to pass the list of words
	 * to Version object and set the list
	 * @param m_Words
	 */
	public void setM_WordsList(List<String> m_Words) {
		this.m_WordsList = m_Words;
	}

	/**
	 * @return version name
	 */
	public String getM_VersionName() {
		return m_VersionName;
	}

	/**
	 * An accessor method to pass the version name
	 * to Version object and set the version name
	 * @param m_VersionName
	 */
	public void setM_VersionName(String m_VersionName) {
		this.m_VersionName = m_VersionName;
	}

	/**
	 * @return author name
	 */
	public String getM_Author() {
		return m_Author;
	}

	/**
	 * An accessor method to pass the version file name
	 * to Version object and fetch the author name
	 * @param versionInformation
	 */
	public void setM_Author(String versionFileName) {
		int startPosition=5;
		int endPosition=versionFileName.length()-4; //substract substring(int beginIndex, int endIndex)
		m_Author = versionFileName.substring(startPosition, endPosition);
	}

	/**
	 * @return the number of version
	 */
	public int getM_VersionNumber() {
		return m_VersionNumber;
	}

	/**
	 * An accessor method to pass the number of version
	 * to Version object and set the number
	 * @param m_VersionNumber
	 */
	public void setM_VersionNumber(int m_VersionNumber) {
		
		this.m_VersionNumber = m_VersionNumber;
	}

	/**
	 * @return the version year
	 */
	public int getM_VersionYear() {
		return m_VersionYear;
	}

	/**
	 * An accessor method to pass one version file name
	 * to Version object and fetch the version year
	 * @param versionInformation
	 */
	public void setM_VersionYear(String versionFileName) {
		int startPosition=0;
		int endPosition=4;
		m_VersionYear=Integer.parseInt(versionFileName.substring(startPosition, endPosition));
	}

	/**
	 * @return one concordance
	 */
	public Concordance getM_Concordance() {
		return m_Concordance;
	}

	/**
	 * An accessor method to set one concordance
	 * @param m_Concordance
	 */
	public void setM_Concordance(Concordance m_Concordance) {
		this.m_Concordance = m_Concordance;
	}

	/**
	 * @return a list of concordance in current version
	 */
	public List<Concordance> getM_ConcordanceList() {
		return m_ConcordanceList;
	}

	/**
	 * An accessor method to set the list of concordance
	 * @param m_Concordance
	 */
	public void setM_ConcordanceList(Concordance m_Concordance) {
		m_ConcordanceList.add(m_Concordance);
	}

	/**
	 * @return the location point of version title
	 */
	public Point getM_titlePoint() {
		return m_titlePoint;
	}

	/**
	 * An accessor method to set location point of version title
	 * @param point
	 */
	public void setM_titlePoint(Point point) {
		
		this.m_titlePoint =point;
	} 
    
    
    
}
