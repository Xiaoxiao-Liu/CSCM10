package translationVisualization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * @author Mohamed Guendouz
 */
public class TFIDFCalculator {
	
	private List<Map.Entry<String, Integer>> oneDoc; 
	
	private List<Hashtable<String, Integer>> lists=new ArrayList<Hashtable<String, Integer>>();
	
	public List<Hashtable<String, Integer>> getLists() {
		return lists;
	}

	private Hashtable<String, Integer> tfidfList;
	
	public Hashtable<String, Integer> getTfidfList() {
		return tfidfList;
	}

	public void setTfidfList(Hashtable<String, Integer> tfidfList) {
		this.tfidfList = tfidfList;
	}

	public List<Map.Entry<String, Integer>> getOneDoc() {
		return oneDoc;
	}

	public void setOneDoc(List<Map.Entry<String, Integer>> oneDoc) {
		this.oneDoc = oneDoc;
	}

	public List<Hashtable<String, Integer>> initiate(List<Hashtable<String, Integer>> documents){
		String currenToken;
		double termFrequency;
		double tf;
		double idfValue;
		double tfIDF;
		int widthValue=10;
		double maxtfidf = 0;
		double mintfidf=10000;
		for (Hashtable<String, Integer> doc : documents) {
			setTfidfList(new Hashtable<String, Integer>());
			setOneDoc(new ArrayList<Map.Entry<String, Integer>>(doc.entrySet()));
			for(Map.Entry<String, Integer> mapping : getOneDoc()){
				currenToken=mapping.getKey();
//				System.out.println("H"+currenToken+"M");
				termFrequency=mapping.getValue();
				tf= 1+Math.log(termFrequency);
				idfValue=idf(documents,currenToken);
				tfIDF=tf*idfValue;
				getTfidfList().put(currenToken, (int) (tfIDF*widthValue));
//				System.out.println("currenToken: "+currenToken);
//				System.out.println("termFrequency: "+tf);
//				System.out.println("idfValue: "+idfValue);
//				System.out.println("tfIDF: "+tfIDF);
				//System.out.println("int: "+(int) (tfIDF*widthValue));
				if(tfIDF > maxtfidf){
					maxtfidf = tfIDF;
				}
				if(tfIDF<mintfidf){
					mintfidf=tfIDF;
				}
					
			}
			getLists().add(getTfidfList());
		}
		System.out.println(maxtfidf);
		System.out.println(mintfidf);

		return getLists();
	}
	

    /**
     * @param docs list of list of strings represents the dataset
     * @param term String represents a term
     * @return the inverse term frequency of term in documents
     */
    public double idf(List<Hashtable<String, Integer>> documents, String term) {
        double n = 0;
        for(int i=0; i<documents.size(); i++){
        	if(documents.get(i).containsKey(term)){
        		n++;
        	}
        }
//        for (Hashtable<String, Integer> doc : docs) {
        	
//        }
//        	System.out.println("n: "+n);
        return Math.log10((documents.size())/n);
    }


}