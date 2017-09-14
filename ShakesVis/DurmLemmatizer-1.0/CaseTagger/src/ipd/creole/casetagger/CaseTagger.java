//
// $Id: CaseTagger.java,v 1.10 2006/02/23 15:22:40 perera Exp $
//
// Durm German Lemmatization System
// http://www.ipd.uka.de/~durm/tm/lemma/
//
//
//The Gate-Wrapper for the Case Tagger
//
//
// (c) 2005--2006 University of Karlsruhe, IPD,
//                Praharshana Perera
//
//
// This program is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation; either version 2 of the License, or
// (at your option) any later version.
//

package ipd.creole.casetagger;

import java.util.*;

import gate.*;
import gate.creole.*;
import gate.util.*;
import gate.event.*;
import gate.gui.MainFrame;
//import clac.CLaCUtils;
                      
/** 
 * This class is the implementation of the resource CASETAGGER.
 */
public class CaseTagger  extends AbstractLanguageAnalyser
  implements ProcessingResource, ANNIEConstants {



  protected String inputASName;
  protected String outputASName;
  protected List extractAnnotations;
  private  java.net.URL probabilityFiles = null;  
  CaseTaggerInt cs;
  CLaCUtils cutils = new CLaCUtils();

// Annotation Set....
  AnnotationSet    outputAnnotationSet;
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
  
  public  java.net.URL getProbabilityFiles() {
    return this.probabilityFiles;
  }
  
public void setProbabilityFiles(java.net.URL newProbabilityFiles ) {
    this.probabilityFiles = newProbabilityFiles;
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

 //---------------------------------------------------------------------------------------------// 
  // add the case of noun to the output annotations
  private void addResult(String caseofNoun, Annotation token) {
       // get annotation set where results will be stored
       if( outputASName == null|| outputASName.equals( "" ))
     outputAnnotationSet = document.getAnnotations();
       else
     outputAnnotationSet = document.getAnnotations( outputASName );
 
       FeatureMap newFm = Factory.newFeatureMap();

       newFm.put( "Case", caseofNoun );

       
       
       // add result feature map
       Long startOffset = token.getStartNode().getOffset();
       Long endOffset = token.getEndNode().getOffset();
       try {
     outputAnnotationSet.add( startOffset,
         endOffset,
         "Case",
         newFm );
       } catch(InvalidOffsetException ioe) {
     // This REALLY shouldn't happen!  (but sometimes does ;-)
     throw new GateRuntimeException(ioe.toString());
       }    
       
  }
  //-----------------------------------------------------------------------------------------------//

 public Resource init() throws ResourceInstantiationException {
                //Initialize the case tagger
                cs = new CaseTaggerImpl(cutils.mapURL2LocalPath(getProbabilityFiles()));
                return super.init();
    }
  
    
    

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
   "\" GermanStemmer has been brutally interrupted!");

      // a new sentence is born
      currentSentenceNo++;
      if(sListener != null)
 sListener.statusChanged("Case Tagging sentence " + currentSentenceNo + "/" + allSentences.size() + " of '" + getDocument().getName() );

      // get all tokens for this sentence
      ArrayList allTokens = getTokensFromSentence( sentenceAnnotation );

      //----------------------------------------------------START THE CASE TAGGER------------------------------------------------------------
   
   
   String caseofNouns[] = cs.getCaseTags(document, allTokens );
   int caseCounter =0;
 
   
      //-----------------------------------------------------START THE CASE TAGGER---------------------------------------------------------------
   
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
         
   
   
     //get the token Type
       String tokentippe = token.getType();
    
     //add the results
 
    if(token.getType().equals("Token"))
     {
       FeatureMap fm = token.getFeatures();
       String pos         = fm.get("category").toString();
       String word       = fm.get("string").toString();
       
       
       
       if(pos.equals("NN") || pos.equals("NE"))
       {
                          
           addResult( caseofNouns[caseCounter],token);
           if(cutils.getAnnotationContainingId(document,token.getId(),"NP")!= null)
           {
                    
               try
               {    
                  Annotation nptoken   = cutils.getAnnotationContainingId(document,token.getId(),"NP");
                  addResult( caseofNouns[caseCounter],nptoken);
                  
                }catch(Exception e)
               {}
           }
           caseCounter++;
       }
       
   }
    
    
   
          
       
//------------------------------------------------------------------------------------------------------       
      

     
 // get Token structure (string, class, category)
 TokenStructure tokenStruc = getTokenStructure( token, tokenLocation++ );
 
 if( tokenStruc == null ) continue;
 //System.out.print( tokenLocation + ", " );
 
 
 
    }
   
      
   
    }
 
   
  
  
  
  
  
       
 
  } // execute


} // class CaseTagger
