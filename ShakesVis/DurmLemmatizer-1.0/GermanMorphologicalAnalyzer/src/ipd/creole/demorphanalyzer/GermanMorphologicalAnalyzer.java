//
// $Id: GermanMorphologicalAnalyzer.java,v 1.3 2006/02/23 17:22:00 witte Exp $
//
// Durm German Lemmatization System
// http://www.ipd.uka.de/~durm/tm/lemma/
//
//
// Gate Wrapper for GermanMorphologicalClassifier
//
//
// (c) 2005--2006 University of Karlsruhe, IPD,
//                Praharshana Perera
//
//
// This program is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation; either version 2 of the License, or
// (at your option) any later version.
// 

package ipd.creole.demorphanalyzer;

import java.util.*;

import gate.*;
import gate.creole.*;
import gate.util.*;
import gate.event.*;
import gate.gui.MainFrame;


/** 
 * This class is the implementation of the resource GERMANMORPHOLOGICALANALYZER.
 */
public class GermanMorphologicalAnalyzer extends AbstractLanguageAnalyser
  implements ProcessingResource, ANNIEConstants {

  // configuration options that can be modified from GATE
     protected String inputASName;
     protected String outputASName;
     protected List extractAnnotations;

 // Annotation Set....
  AnnotationSet    outputAnnotationSet;

    
 // clac utils
  CLaCUtils cutils = new CLaCUtils();
  

    
    private int substCount = 0;
  // private TokenStructure class
  class TokenStructure {
    Annotation token;         // the GATE token
    String     tokenString;   // the textual string for this token
    String     tokenCategory; // the token's category for Scheme
    String     tokenLocation; // a sortable token location
    String     tokenType;     // original Type from Gate
    String     annotationId;  // id of the corresponding Gate annotation
    
  }

  /** Feature name (slot) for a "category" */
  public static final String CATEGORY_FEATURE = "category";

  /**
   * Get the InputASName value.
   * @return the InputASName value.
   */
  public String getInputASName() {
    return inputASName;
  }

  /**
   * Set the InputASName value.
   * @param newInputASName The new InputASName value.
   */
  public void setInputASName(String newInputASName) {
    this.inputASName = newInputASName;
  }

  /**
   * Get the OutputASName value.
   * @return the OutputASName value.
   */
  public String getOutputASName() {
    return outputASName;
  }

  /**
   * Set the OutputASName value.
   * @param newOutputASName The new OutputASName value.
   */
  public void setOutputASName(String newOutputASName) {
    this.outputASName = newOutputASName;
  }

  /**
   * Get the ExtractAnnotation value.
   * @return the ExtractAnnotation value.
   */
  public List getExtractAnnotations() {
    return extractAnnotations;
  }

  /**
   * Set the ExtractAnnotation value.
   * @param newExtractAnnotations The new ExtractAnnotation value.
   */
  public void setExtractAnnotations(List newExtractAnnotations) {
    this.extractAnnotations = newExtractAnnotations;
  }

  /**
   *  Retrive sentences from document in sorted order
   */
  private ArrayList extractSortedSentences() {
    AnnotationSet allSentenceAnnotations = null ;
    if( inputASName == null || inputASName.equals( "" ))
 allSentenceAnnotations = document.getAnnotations();
    else
 allSentenceAnnotations = document.getAnnotations( inputASName );

    if( allSentenceAnnotations.get( SENTENCE_ANNOTATION_TYPE ) == null ) {
      //System.out.println( "WARNING: no sentences found in document --- parser aborting!" );
      return null;
    }
    ArrayList allSentences = new ArrayList( allSentenceAnnotations.get( SENTENCE_ANNOTATION_TYPE ) );
    Collections.sort( allSentences, new gate.util.OffsetComparator() );

    return allSentences;
  }


  /**
   *  Get Tokens from Sentence
   */
  private ArrayList getTokensFromSentence(Annotation sentenceAnnotation ) {
    AnnotationSet allSentenceAnnotations = null ;
    if( inputASName == null|| inputASName.equals( "" ) )
 allSentenceAnnotations = document.getAnnotations();
    else
 allSentenceAnnotations = document.getAnnotations( inputASName );

    // Get all "token" tokens from this sentence
    List tokens = new ArrayList((Set)allSentenceAnnotations.get( 
    TOKEN_ANNOTATION_TYPE,
    sentenceAnnotation.getStartNode().getOffset(),
    sentenceAnnotation.getEndNode().getOffset()));

    // create an ArrayList to hold all tokens, dates, etc.
    ArrayList allTokens = new ArrayList( tokens );
    allTokens.ensureCapacity( 2*tokens.size() );

    // now get all specified annotations from the sentence
 
 
 
 
    for( int i=0; i<getExtractAnnotations().size(); i++ ) {
      List annoTokens = new ArrayList((Set)allSentenceAnnotations.get( 
    (String) getExtractAnnotations().get(i),
    sentenceAnnotation.getStartNode().getOffset(),
    sentenceAnnotation.getEndNode().getOffset()));
      allTokens.addAll( annoTokens );
    }

    // sort all tokens by start offset
    Collections.sort( allTokens, new gate.util.OffsetComparator() );

    return allTokens;
  }

  /**
   *  Determine Token string, class, and category information
   */
  private TokenStructure getTokenStructure( Annotation token, int location ) throws ExecutionException {
    String category, tokenClass;
    String tokenString = null;

    try {
      tokenString = document.getContent().getContent(
       token.getStartNode().getOffset(),
       token.getEndNode().getOffset()).toString().replace('\n', ' ');
    } catch (InvalidOffsetException ioe) {
      throw new ExecutionException("Invalid offset of the annotation");
    }

    FeatureMap tokenFeatures = token.getFeatures();
    if( token.getType().equals( TOKEN_ANNOTATION_TYPE )) {
      // a hep tagged token, get HepTag feature if it exists
      if( tokenFeatures.containsKey( CATEGORY_FEATURE ))
 category = tokenFeatures.get( CATEGORY_FEATURE ).toString();
      else return null;
      String tokenClasslookup;
    } else {
  category= null;
  //System.out.println( "TOKEN TYPE NOT SUPPORTED!" );
    }
    
    // build the token structure
    TokenStructure tokenStruc = new TokenStructure();
    tokenStruc.token = token;
    tokenStruc.tokenString = tokenString;
    tokenStruc.tokenCategory = category;
    tokenStruc.tokenLocation = new Integer(location).toString();
    tokenStruc.tokenType = token.getType();
    tokenStruc.annotationId = token.getId().toString();
    
 // ------------------------------------
 //        if(category != null)
 //     System.out.println(category);
 //------------------------------------
 
    return tokenStruc;
  }

  private void addResultNumber(String number, Annotation token) {
       // get annotation set where results will be stored
       if( outputASName == null|| outputASName.equals( "" ))
     outputAnnotationSet = document.getAnnotations();
       else
     outputAnnotationSet = document.getAnnotations( outputASName );
 
       FeatureMap newFm = Factory.newFeatureMap();

       newFm.put( "Number", number );

       
       
       // add result feature map
       Long startOffset = token.getStartNode().getOffset();
       Long endOffset = token.getEndNode().getOffset();
       try {
     outputAnnotationSet.add( startOffset,
         endOffset,
         "Num",
         newFm );
       } catch(InvalidOffsetException ioe) {
     // This REALLY shouldn't happen!  (but sometimes does ;-)
     throw new GateRuntimeException(ioe.toString());
       }    
       
  }
  
  
  private void addResultGender(String gender, Annotation token) {
       // get annotation set where results will be stored
       if( outputASName == null|| outputASName.equals( "" ))
     outputAnnotationSet = document.getAnnotations();
       else
     outputAnnotationSet = document.getAnnotations( outputASName );
 
       FeatureMap newFm = Factory.newFeatureMap();

      
 newFm.put("Gender", gender);
    
       
       
       // add result feature map
       Long startOffset = token.getStartNode().getOffset();
       Long endOffset = token.getEndNode().getOffset();
       try {
     outputAnnotationSet.add( startOffset,
         endOffset,
         "Gender",
         newFm );
       } catch(InvalidOffsetException ioe) {
     // This REALLY shouldn't happen!  (but sometimes does ;-)
     throw new GateRuntimeException(ioe.toString());
       }    
       
  }
  
  // converts to tok info object
  
  
  
  
     
  /**
   * Reinitialises the processing resource. After calling this method the
   * resource should be in the state it is after calling init.
   * If the resource depends on external resources (such as rules files) then
   * the resource will re-read those resources. If the data used to create
   * the resource has changed since the resource has been created then the
   * resource will change too after calling reInit().
   */
  public void reInit() throws ResourceInstantiationException
  {
    init();
  } // reInit()

  /**
   * Notifies all the PRs in this controller that they should stop their
   * execution as soon as possible.
   */
  public synchronized void interrupt(){
    interrupted = true;
  }


    

  /** Run the resource. */
  public void execute() throws ExecutionException {
    interrupted = false;

    if(document == null)
      throw new GateRuntimeException("No document to process!");

         // get a StatusListener to display currently processed sentence no. in Gate interface
           StatusListener sListener = (StatusListener) MainFrame.getListeners().get("gate.event.StatusListener");
 
 // get the current document
   String docname = document.getSourceUrl().toString();
   //System.out.println("name of the document :-"+docname);
 
        //AnnotationSet    outputAnnotationSet;
        AnnotationSet    allMarkups;
        AnnotationSet    textMarkupSet;
        Annotation       textMarkup;
        AnnotationSet    headMarkupSet;
        Annotation       headMarkup = null;
        Iterator         sentenceAnnotationIterator;
        int              currentSentenceNo = 0;
        int              progress = 0;
 
 // boolean flag to check the head
 
 boolean flag = false;
 String Head="";
        String det   ="";
        String mod = "";
        MorphologyImpl moimpl = new DetMorphology();
 
      // get sentences from document in sorted order
     ArrayList allSentences = extractSortedSentences();
     if( allSentences == null) return; // nothing to do

    // get annotation set where results will be stored
    if( outputASName == null|| outputASName.equals( "" ))
 outputAnnotationSet = document.getAnnotations();
    else
 outputAnnotationSet = document.getAnnotations( outputASName );

    sentenceAnnotationIterator = allSentences.iterator();
    while( sentenceAnnotationIterator.hasNext() ) {
      Annotation     sentenceAnnotation = (Annotation) sentenceAnnotationIterator.next();
      StringBuffer   sentenceText = new StringBuffer();
      int            tokenLocation = 1;

      if(isInterrupted()) 
 throw new ExecutionInterruptedException(
   "The execution of the \"" + getName() +
   "\" German Morphological Analyzer has been brutally interrupted!");

      // a new sentence is born
      currentSentenceNo++;
      if(sListener != null)
 sListener.statusChanged("Morphological analysis of sentence " + currentSentenceNo + "/" + allSentences.size() + " of '" + getDocument().getName() );

      // get all tokens for this sentence
      ArrayList allTokens = getTokensFromSentence( sentenceAnnotation );
      
      

      // Now we build the token structure for Scheme and do stuff
      ListIterator tokenIterator = allTokens.listIterator();
   
      while( tokenIterator.hasNext() ) {
 
    Annotation token = (Annotation) tokenIterator.next();
    // -------------------------------------------------------------------------------------------
       
        String tokenString ;
        try {
              tokenString = document.getContent().getContent(
       token.getStartNode().getOffset(),
       token.getEndNode().getOffset()).toString().replace('\n', ' ');
            } catch (InvalidOffsetException ioe) {
                 throw new ExecutionException("Invalid offset of the annotation");
            }

       
         //System.out.println("token :- "+ tokenString);
           
          if(flag)
          {
             if(tokenString.equals(Head))
              {
                  //System.out.println("HERE I AM ");
                  addResultNumber( moimpl.number, token);
                  addResultGender(moimpl.gender, token);
                  flag = false;
              }
  
       
         }
   
    String tokentippe = token.getType();
    int j = 0;
  
  
    
   if( tokentippe.equals("NP"))
          {  
                
                //Initilaize abstract interface
         Morphology morph = new GermanMorphology();
                FeatureMap fm = token.getFeatures();
                Head = fm.get("HEAD").toString();
                
                
                if(fm.get("DET") != null)
                {
                   morph.morphImpl = new DetMorphology();
                   moimpl  = morph.classifyMorphology(token,document);
                   flag        = true;
                          if(fm.get("MOD")!=null)
                          {
                              if(moimpl.number.equals("Sg.Pl"))
                              {
                                 morph.morphImpl = new DetModMorphology();
                                 moimpl = morph.classifyMorphology(token,document);
                                 flag       = true;
                               }
                          }
                }
                else if(fm.get("MOD")!=null)
                {
                    morph.morphImpl = new ModMorphology();
                    moimpl  = morph.classifyMorphology(token,document);
                    flag        = true;
                }
                else 
                {
                     morph.morphImpl = new NonDetModMorphology();
                     moimpl  = morph.classifyMorphology(token,document);
                     flag        = true;
                 }
                
         //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  if(cutils.getAnnotationContainingId(document,token.getId(),"Token")!= null)
         {
                    
             try
                    {    
                  Annotation testtoken   = cutils.getAnnotationContainingId(document,token.getId(),"Token");
           if(flag)
           {
                            //System.out.println("HERE I AM ");
                            addResultNumber( moimpl.number, testtoken);
                            addResultGender(moimpl.gender, testtoken);
                            
                            flag = false;        
           }
             }catch(Exception e)
           {}
         }     
   }
     
     
     
      
       // --------------------------------------------------------------------------------------------------


     
 // get Token structure (string, class, category)
 TokenStructure tokenStruc = getTokenStructure( token, tokenLocation++ );
 
 if( tokenStruc == null ) continue;
 //System.out.print( tokenLocation + ", " );
 
 //tokInfo.add(toTokenInformation(tokenStruc));
 
    }
   
   
   
   
    }
 
 
       
 
  } // execute

} // class GermanMorphologicalAnalyzer
