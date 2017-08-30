package NLP;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import edu.stanford.nlp.ling.CoreAnnotations.FreqAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class StanfordLemmatizer {
	private static List<Map.Entry<String, Integer>> frequencyIndex = new ArrayList<Map.Entry<String, Integer>>();

	private static Hashtable<String, Integer> frequency=new Hashtable<String, Integer>();

	private final static String[] stringArray={"src\\data\\0000 BaseText Shakespeare.txt","src\\data\\1832 Baudissin ed Wenig.txt","src\\data\\1920 Gundolf.txt","src\\data\\1941 Schwarz.txt","src\\data\\1947 Baudissin ed Brunner.txt","src\\data\\1952 Flatter.txt","src\\data\\1962 Schroeder.txt","src\\data\\1963 Rothe.txt","src\\data\\1970 Fried.txt","src\\data\\1973 Lauterbach.txt","src\\data\\1976 Engler.txt","src\\data\\1978 Laube.txt","src\\data\\1985 Bolte Hamblock.txt","src\\data\\1992 Motschach.txt","src\\data\\1995 Guenther.txt","src\\data\\2003 Zaimoglu.txt"};


    protected StanfordCoreNLP pipeline;

    public StanfordLemmatizer() {
        // Create StanfordCoreNLP object properties, with POS tagging
        // (required for lemmatization), and lemmatization
        Properties props;
        props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma");

        /*
         * This is a pipeline that takes in a string and returns various analyzed linguistic forms. 
         * The String is tokenized via a tokenizer (such as PTBTokenizerAnnotator), 
         * and then other sequence model style annotation can be used to add things like lemmas, 
         * POS tags, and named entities. These are returned as a list of CoreLabels. 
         * Other analysis components build and store parse trees, dependency graphs, etc. 
         * 
         * This class is designed to apply multiple Annotators to an Annotation. 
         * The idea is that you first build up the pipeline by adding Annotators, 
         * and then you take the objects you wish to annotate and pass them in and 
         * get in return a fully annotated object.
         * 
         *  StanfordCoreNLP loads a lot of models, so you probably
         *  only want to do this once per execution
         */
        this.pipeline = new StanfordCoreNLP(props);
    }

    public boolean readOneFile(String filePath){
		try {
			frequency=new Hashtable<String, Integer>();
			BufferedReader br=new BufferedReader(new FileReader(filePath));
			String sCurrentLine;
			String[] tmpWordsArray=null;
			int lineCount=0;
			
			while((sCurrentLine=br.readLine())!=null){
				if(sCurrentLine.trim().isEmpty()){	
				}else if(lineCount==0){
						lineCount++;
				}else{
					tmpWordsArray=sCurrentLine.toLowerCase().replaceAll("\\p{Punct}", "").split(" ");
					for(int i=0; i<tmpWordsArray.length; i++){
							addWordFrequency(tmpWordsArray[i]);							
				    }
				}
			}
//			System.out.println(frequency.size());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	
	public void addWordFrequency(String word){
		if(!frequency.containsKey(word)){
			frequency.put(word, new Integer(1));
		}
		else{
			frequency.put(word, frequency.get(word).intValue()+1);
		}
	}
	
	
	public boolean sortFrequencyIndex(Hashtable<String, Integer> frequencyUnsorted){
//		System.out.println(frequencyIndex.size());
		frequencyIndex = new ArrayList<Map.Entry<String, Integer>>(frequencyUnsorted.entrySet());  
        Collections.sort(frequencyIndex, new Comparator<Map.Entry<String, Integer>>() {  
            //decending order  
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {               	                    
                return o2.getValue().compareTo(o1.getValue());  
            }				
        });  
        return true;
	}
	
    public List<String> lemmatize(String documentText)
    {
    	int m=0;
    	int k=0;
        List<String> lemmas = new LinkedList<String>();
        List<String> lemmamath = new LinkedList<String>();
        Hashtable<String, Integer> lemma=new Hashtable<String, Integer>();
        // Create an empty Annotation just with the given text
        Annotation document = new Annotation(documentText);
        // run all Annotators on this text
        this.pipeline.annotate(document);
        // Iterate over all of the sentences found
        List<CoreMap> sentences = document.get(SentencesAnnotation.class);
//        System.out.println(sentences);
        for(CoreMap sentence: sentences) {
            // Iterate over all tokens in a sentence
            for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
//            	System.out.println(token);
                // Retrieve and add the lemma for each word into the
                // list of lemmas
                lemmas.add(token.get(LemmaAnnotation.class));
                k++;
//                lemma.put(LemmaAnnotation.class), Integer.parseInt(FreqAnnotation.class));
                lemmamath.add(token.value());
                
                
                
                
//                token.get(FreqAnnotation.class);
           
                System.out.println(token.get(LemmaAnnotation.class));
            }
            m++;
           
        }
        System.out.println(lemmas.size());
        
       
        return lemmas;
    }
    
    public static void main(String[] args) {
        System.out.println("Starting Stanford Lemmatizer");
        String text = "How could you be seeing into my eyes like open doors? \n"+
                "You led me down into my core where I've became so numb \n"+
                "Without a soul my spirit's sleeping somewhere cold \n"+
                "Until you find it there and led it back home \n"+
                "You woke me up inside \n"+
                "Called my name and saved me from the dark \n"+
                "You have bidden my blood and it ran \n"+
                "Before I would become undone \n"+
                "You saved me from the nothing I've almost become \n"+
                "You were bringing me to life \n"+
                "Now that I knew what I'm without \n"+
                "You can've just left me \n"+
                "You breathed into me and made me real \n"+
                "Frozen inside without your touch \n"+
                "Without your love, darling \n"+
                "Only you are the life among the dead \n"+
                "I've been living a lie, there's nothing inside \n"+
                "You were bringing me to life.";
        StanfordLemmatizer slem = new StanfordLemmatizer();
        slem.readOneFile(stringArray[0]);
        slem.sortFrequencyIndex(frequency);
        List<String> textArray= new ArrayList<String>();
        
        for(Map.Entry<String, Integer> mapping : frequencyIndex){
        	textArray.add(mapping.getKey());
        }
        System.out.println(textArray.size());
        System.out.println(slem.lemmatize(textArray.toString().replaceAll("\\p{Punct}", "")).size());
//        System.out.println(slem.lemmatize(text));
    }

}
