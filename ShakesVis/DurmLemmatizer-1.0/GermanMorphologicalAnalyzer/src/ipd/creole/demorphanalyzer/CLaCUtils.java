//
// $Id: CLaCUtils.java,v 1.1 2006/02/17 12:19:42 perera Exp $
//
// Durm German Lemmatization System
// http://www.ipd.uka.de/~durm/tm/lemma/
//
//
// A class contains miscellaneous funtional methods that might ease the work in Gate Component Developing.
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

package ipd.creole.demorphanalyzer;

import java.util.*;
import java.io.*;
import java.net.*;

import gate.*;
import gate.util.*;
import gate.creole.ANNIEConstants;

/**A class contains miscellaneous funtional methods that might ease the work in Gate Component Developing.*/
public class CLaCUtils
   implements GateConstants, ANNIEConstants{

  static protected final String NO_TOKEN_MSG = "There is no token annotation!" ;
  static protected final String NO_TOKEN_WITHIN_MSG = "There is no token annotation within the span of given annotation!" ;

  /**AnnotationSet name for multiple document*/

  /**The head string of annotation set name for single document*/

  /** Annotation type for CLaC multidoc markup */
  public static final String MULTIDOC_ORIGIN_ANNOTATION_TYPE = "MultiDoc";

  public static final String SINGLE_DOC_ANNOTATION_NAME_HEADER_STRING = "Doc";
  public static final String SINGLE_DOC_XML_TAG = "CLaCSingleDoc";
  public static final String SINGLE_DOC_ID = "ID";
     
  /**The Map used for customized URL protocol mapping*/
  protected static String clacresourcesMap;
  protected static String clacclassesMap;
  protected static String clactoolsMap;
  protected static Map protocolMap;

  static protected Map resourceMap = null ;
  static protected boolean protocolMapped = false ;
  static{

    resourceMap = new HashMap();

    // flag showing that if the protocols have been initialized already
    // because we only want to initialize them once
    // currently I don't provide the method to reset it
    // because for now there is no need to reset the protocol
    // after Gate is started
    protocolMapped = false ;
  }

  public CLaCUtils(){
  }


    //////////////////////////////////////////////////////////////
    //
    // Methods to manipulate Gate annotations
    //
    ////////////////////////////////////////////////////////////


  /** 
   * Get the content of the document that has been marked up by the given gate.Annotation.
   * 
   * @param doc The gate.Document object representing the current document.
   * @param anno The desired gate.Annotation representing the markup.
   * @return The <code>java.lang.String</code> object representing the annotated content.
   * @throws InvalidOffsetException When the given annotation is invalid.
   */
  public static String getDocAnnotated( gate.Document doc , gate.Annotation anno ) throws InvalidOffsetException{
 
    if( anno == null )
      return null;
 
    gate.DocumentContent content = null ;
    try{
 content = doc.getContent().getContent( anno.getStartNode().getOffset(),
            anno.getEndNode().getOffset() );
    }catch( InvalidOffsetException e ){
      throw e;
    }

    // remove newlines , multiple space, and etc.
    String contentStr = content.toString();
    contentStr = contentStr.replaceAll( "\t|\n|\r|\f|\u0085|\u2028|\u2029", " " );
    contentStr = contentStr.replaceAll( "   ", " " );
    contentStr = contentStr.replaceAll( "  ", " " );
    return contentStr;
  }
    
  /** 
   * Sort the given AnnotationSet that contains annotations
   * according the order defined by gate.util.OffsetComparator
   *
   * @param annoSet The AnnotationSet object that to be sorted.
   * @return One java.util.List object that contains that sorted annotation(s).
   */
  public static List sortAnnotations( AnnotationSet annoSet ){
 
      // sort all tokens
      // according to the order defined by
      // gate.util.OffsetComparator
    return sortAnnotations( annoSet , new gate.util.OffsetComparator() );
  }

  /** 
   * Sort the given AnnotationSet that contains some annotations with
   * the provided comparator.
   *
   * @param annoSet The AnnotationSet object that to be sorted.
   * @param comparator The specified comparator representing certain defined orders.
   * @return One List that contains annotations sorted according to the
   * order defined by the comparator.
   */
  public static List sortAnnotations( AnnotationSet annoSet , Comparator comparator){
    ArrayList tmpList = new ArrayList( annoSet );
 
    // sort all tokens by start offset
    Collections.sort( tmpList , comparator );
  
    return tmpList ;
  }

  /** 
   * Sort the given AnnotationSet that contains some annotations of the same type, using the gate.util.OffsetComparator
   *
   * @param annoList The AnnotationSet object that to be sorted.
   * @return One java.util.List object that contains that sorted annotation(s).
   */
  public static void sortAnnotations( List annoList ){
 
    // sort all tokens by start offset
    Collections.sort( annoList , new gate.util.OffsetComparator() );
 
  }

  /** 
   * Get annotations in the annotation set that have span larger than the specified span.
   * The difference between this method and Gate.AnnotationSet.get() method is
   * the latter will also retrieve annotations whose span are contained by the specified span.
   *
   * @param set The AnnotationSet object that to be searched.
   * @param startOffset The start offset of the span.
   * @param endOffset The end offset of the span.
   * @param strict If the returned annotation must be strictly larger than the specified span or could be partially
   * contained by the span
   * @return One java.util.List object that contains that sorted annotation(s).
   */
    public static List getAnnotationCovering( AnnotationSet set , Long startOffset , Long endOffset , boolean strict ){
 ArrayList list = new ArrayList() ;

 if( startOffset.compareTo( endOffset ) >= 0 )
     return list ;

 Iterator iterator = set.iterator() ;
 while( iterator.hasNext() ){
     Annotation cur = (Annotation) iterator.next() ;
     if( strict ){
  if( cur.getStartNode().getOffset().compareTo( startOffset ) <= 0 &&
      cur.getEndNode().getOffset().compareTo( endOffset ) >= 0 )
      list.add( cur ) ;
     }else{
  if( cur.getStartNode().getOffset().compareTo( startOffset ) <= 0 ||
      cur.getEndNode().getOffset().compareTo( endOffset ) >= 0 )
      list.add( cur ) ;
     }
 }
 return list;
    }      
      
  /**
   * Get the concatencated String from the included token annotations.
   * Because We might do some post processings with the tokens, such as the number combination,
   * sometimes the concatenated string of those tokens might be different with the orginal
   * content that is directly extracted from the document body.
   *
   * @param doc The gate.Document instance that comes with the DEFAULT annotation set
   * @param anno The gate.Annotation instance
   * @return The String of the concatenated tokens within the annoation span
   * @throws Exception When there is no token within the given annotation span or something else.
   */
  public static String getTokenContentInAnnotation( Document doc , Annotation anno ) throws Exception {
 
    return  getTokenContentInAnnotation( doc.getAnnotations() , anno );
 
  }

  /**
   * Get the concatencated String from the token annotations covered
   * by the specified annotation.
   * Because We might do some post processings with the tokens, such as the number combination,
   * sometimes the concatenated string of those tokens might be different with the orginal
   * content that is directly extracted from the document body.
   *
   * @param inputAS The given input AnnotationSet
   * @param anno The gate.Annotation instance
   * @return The String of the concatenated tokens within the annoation span
   * @throws Exception When there is no token within the given annotation span or something else.
   */
  public static String getTokenContentInAnnotation( AnnotationSet inputAS , Annotation anno )
      throws Exception{
      return getTokenContentInSpan( inputAS , 
        anno.getStartNode().getOffset(),
        anno.getEndNode().getOffset());
  }

  /** returns a sorted list **/
  public static List getTokenSpaceAnnoListInSpan( AnnotationSet inputAS , 
        Long spanStartOffset, Long spanEndOffset, boolean sort ){
    if( spanStartOffset.compareTo( spanEndOffset ) >= 0 )
      return null ;
    HashSet set = new HashSet() ;
    set.add( gate.creole.ANNIEConstants.TOKEN_ANNOTATION_TYPE );
    set.add( gate.creole.ANNIEConstants.SPACE_TOKEN_ANNOTATION_TYPE );

    AnnotationSet tokens = inputAS.get( set  );
    if( tokens == null || tokens.isEmpty() ){
 //throw new Exception( NO_TOKEN_MSG  );
      return new ArrayList() ;
    }

    tokens = tokens.get( spanStartOffset,
    spanEndOffset );
    if( tokens == null && tokens.size() == 0 ){
      //throw new Exception( NO_TOKEN_WITHIN_MSG );
      return new ArrayList() ;
    }
 
    List list = new ArrayList( tokens );

    if( sort ) sortAnnotations(list);
    return list ;
  }

  /** returns a sorted list **/
  public static List getTokenAnnoListInSpan( AnnotationSet inputAS , 
          Long spanStartOffset, Long spanEndOffset, boolean sort ){
    if( spanStartOffset.compareTo( spanEndOffset ) >= 0 )
      return null ;
    HashSet set = new HashSet() ;
    set.add( gate.creole.ANNIEConstants.TOKEN_ANNOTATION_TYPE );

    AnnotationSet tokens = inputAS.get( set  );
    if( tokens == null || tokens.isEmpty() ){
 //throw new Exception( NO_TOKEN_MSG  );
      return new ArrayList() ;
    }

    tokens = tokens.get( spanStartOffset,
    spanEndOffset );
    if( tokens == null && tokens.size() == 0 ){
      //throw new Exception( NO_TOKEN_WITHIN_MSG );
      return new ArrayList() ;
    }
 
    List list = new ArrayList(tokens) ;
    
    if(sort) sortAnnotations( list );
    return list ;
  }


  /**
   * Get the concatencated String from the tokens covered by 
   * the specified span.
   * Because We might do some post processings with the tokens, such as the number combination,
   * sometimes the concatenated string of those tokens might be different with the orginal
   * content that is directly extracted from the document body.
   *
   * @param inputAS The given input AnnotationSet
   * @param spanStartOffset The starting offset of the span
   * @param spanEndOffset The endding offset of the span
   * @return The String of the concatenated tokens within the annoation span
   * @throws Exception When there is no token within the given annotation span or something else.
   */
  public static String getTokenContentInSpan( AnnotationSet inputAS , 
           Long spanStartOffset, Long spanEndOffset)
      throws Exception {

    if( spanStartOffset.compareTo( spanEndOffset ) >= 0 )
      return "" ;

    List listTokenAndSpace = getTokenSpaceAnnoListInSpan( inputAS,spanStartOffset,spanEndOffset,false);
    
    String content = getTokenContentFromTokenSpaceList( listTokenAndSpace , true );

    return content ;
  }

  public static String getTokenContentFromTokenSpaceList( List listTokenAndSpace , boolean useDocSpace)
      throws Exception {
    
    if( listTokenAndSpace == null )
      return "";

    sortAnnotations( listTokenAndSpace );

    Iterator iter = listTokenAndSpace.iterator() ;
 
    String content = "";
 
    final String space = " ";

    Annotation tmp = null ;
    FeatureMap features = null ;
    String string = null ;
    String annoType = null ;

    //find the first token, omit all the preceding spacetokens
    do{
      if( iter.hasNext() == false ){
 throw new Exception( NO_TOKEN_MSG );
      }

      tmp = (Annotation) iter.next() ;
      annoType = tmp.getType() ;

      // continue while the obtained annotation is spacetoken
    }while( annoType.equals( ANNIEConstants.SPACE_TOKEN_ANNOTATION_TYPE ) == true );

    // now we get the first token
    features = tmp.getFeatures() ;
    string = (String) features.get( ANNIEConstants.TOKEN_STRING_FEATURE_NAME );
    content = content + string ;

    int prevAnnoType = 0 ;

    while( iter.hasNext() ){
      tmp = (Annotation) iter.next() ;
      annoType = tmp.getType() ;

      // if we encounter one space token, add one space to the complete content
      if( annoType.equals( ANNIEConstants.SPACE_TOKEN_ANNOTATION_TYPE ) == true ){
   if( prevAnnoType == 0 ){
       content = content + space ;
       prevAnnoType = 1 ;
   }
      }else{
 // add the string of the token to the complete content
 features = tmp.getFeatures() ;
 string = (String) features.get( ANNIEConstants.TOKEN_STRING_FEATURE_NAME );

 if( useDocSpace ){
   prevAnnoType = 0 ;
   content = content + string ;
 }else{
   prevAnnoType = 0 ;
   content = content + space + string ;
 }
      }
    }

    // return the complete string
    return content ;
  }

  /**
   * Remove all the annotaitons contained in the specified annotationset from the container.
   *
   * @param set The annotation set where the specified annotations to be removed from.
   * @param toBeRemoved The annotation set containing the annotations to be removed
   */
  static public void removeAnnotationSet( AnnotationSet set , AnnotationSet toBeRemoved ){
    ArrayList tmpList = new ArrayList( toBeRemoved ) ;
    set.removeAll( tmpList ) ;
  }

  /**
   * Remove all the annotaitons with the specified name from the container.
   *
   * @param set The annotation set where the specified annotations to be removed from.
   * @param nameToBeRemoved The name of annotation that is to be removed from set
   */
  static public void removeAnnotationSet( AnnotationSet set , String nameToBeRemoved ){

    AnnotationSet remove = set.get( nameToBeRemoved ) ;
    ArrayList tmpList = new ArrayList( remove ) ;
    set.removeAll( tmpList ) ;
  }

     
  /** 
   * Retrieve specific annotation containing another annotation with a specified ID.
   * This method is added to tackle the weird problem caused by Gate's
   * Nominal/Pronominal correferencer.
   *
   * @param document the document containing the specified annotation
   * @param id the ID of the annotation
   * @param type the type of the annotation to be retrieved
   * @return the annotation of type <type> containing the specified annotation
   */
  public Annotation getAnnotationContainingId( Document document, Integer id, String type ) {
    Integer sId = null;  // id of the annotation we are searching for
    Annotation anno = document.getAnnotations().get( id );
    if( anno == null ) {
      System.out.println("ERROR in CLaCUtils::getAnnotationContainingId: trying to retrieve non-existing annotation with ID=" + id );
      return null;
    }
    long anno_start = anno.getStartNode().getOffset().longValue();
    long anno_end = anno.getEndNode().getOffset().longValue();
    AnnotationSet allAnnos = document.getAnnotations().get( type );
    if( allAnnos != null ) {
      Iterator annoIterator = allAnnos.iterator();
      boolean annoFound = false;
      while( annoIterator.hasNext() && !annoFound ) {
 Annotation superAnno = (Annotation)annoIterator.next();
 long superAnno_start = superAnno.getStartNode().getOffset().longValue();
 long superAnno_end = superAnno.getEndNode().getOffset().longValue();
 if( superAnno_start <= anno_start && superAnno_end >= anno_end ) {
   // found it!
   sId = superAnno.getId();
   annoFound = true;
 }
      }
    }
    if( sId == null )
      return null;
    else
      return document.getAnnotations().get( sId );    
  }


  /** 
   * Retrieve sentence containing a specified ID
   * This method is added to tackle the weird problem caused by Gate's
   * Nominal/Pronominal correferencer.
   *
   * @param document the document containing the specified annotation
   * @param id the ID of the annotation
   * @return the sentence annotation containing the specified annotation
   */
  public Annotation getSentenceContainingAnnotationId( Document document, Integer id ) {
    return getAnnotationContainingId( document, id, SENTENCE_ANNOTATION_TYPE );
  }


    /////////////////////////////////////////////////
    //
    // Methods to access CLaC customized multiple-document representation
    //
    //////////////////////////////////////////////



  /** 
   * Return the number (id) of the single document within a collection the 
   * provided annotation is part of
   *
   * @param document the multi-document collection (created with combine-multi-docs.pl)
   * @param anno the annotation to be checked
   * @return the ID of the single document containing anno, null if not found
   */
  public static Integer getDocumentId( Document document, Annotation anno ) {
    Integer id = null; 
    long anno_start = anno.getStartNode().getOffset().longValue();
    long anno_end = anno.getEndNode().getOffset().longValue();
    AnnotationSet originalSet = document.getAnnotations( ORIGINAL_MARKUPS_ANNOT_SET_NAME );
    if( originalSet != null ) {
      AnnotationSet singleDocs = originalSet.get( SINGLE_DOC_XML_TAG );
      if( singleDocs != null ) {
 Iterator singleDocIterator = singleDocs.iterator();
 boolean docFound = false;
 while( singleDocIterator.hasNext() && !docFound ) {
   Annotation singleDoc = (Annotation)singleDocIterator.next();
   long doc_start = singleDoc.getStartNode().getOffset().longValue();
   long doc_end = singleDoc.getEndNode().getOffset().longValue();
   if( doc_start <= anno_start && doc_end >= anno_end ) {
     // found it!
     FeatureMap docFeatures = singleDoc.getFeatures();
     String docId = (String)docFeatures.get( SINGLE_DOC_ID );
     id = new Integer( docId );
     docFound = true;
   }
 }
      }
    }

    return id;     
  }

    
  /**
   * Return the number of single documents in a multi-document collection
   *
   * @param document the multi-document collection (created with combine-multi-docs.pl)
   * @return the number of single documents in this collection
   */
  public static int getNumberOfDocuments( Document document ) {
    int numOfDocs = 0;

    AnnotationSet originalSet = document.getAnnotations( ORIGINAL_MARKUPS_ANNOT_SET_NAME );
    if( originalSet != null ) {
      AnnotationSet singleDocs = originalSet.get( SINGLE_DOC_XML_TAG );
      if( singleDocs != null )
 numOfDocs = singleDocs.size();
    }
  
    return numOfDocs;
  }


  /**
   * Get the default annotation set for a certain single document.
   * REMEMBER:!!! The No/Index for the first single doc is 0!!
   *
   * @param doc The gate.Document instance referring to the current "big" combined document
   * @param docNo The number of the desired document. NOTICE that the no. for the first document is 0
   */
  public static AnnotationSet getSingleDocAnnotationSet( Document doc , int docNo ){

    //to save my typing
    String head = SINGLE_DOC_ANNOTATION_NAME_HEADER_STRING ;

    String docAnnoSetName = head + String.valueOf( docNo ) ;

    // Notice that we are getting those annotations that are in the Default 
    // AnnotationSet but covered by "DocX"
    // annotation, which are put within the Original Annotation Set!
    AnnotationSet defaultSet  = doc.getAnnotations();
    AnnotationSet originalSet = doc.getAnnotations( ORIGINAL_MARKUPS_ANNOT_SET_NAME );
 
    //first we check if the "DocX" is there in the original annotation set
    AnnotationSet docSet = originalSet.get( docAnnoSetName ) ;

    //if not there, simple return null
    if( docSet == null ){
      return null ;
    }

    //User can check the gate documentation for why I use getContained instead of get here.
    //Actually I think they are the same in this case
    AnnotationSet result = (AnnotationSet) defaultSet.getContained( docSet.firstNode().getOffset(),
            docSet.lastNode().getOffset() );

    return result ;

  }

  /**
   * Get the annotation set for a certain single document from the specified annotation set.
   * This method is used for one single doc within a multidoc. It is to get the annotations
   * that are located within an annotation set other than the default annotation set, such
   * as the original markup annotation set
   *
   * @param doc The gate.Document instance referring to the current "big" combined document
   * @param docNo The number of the desired document. NOTICE that the no. for the first document is 0
   * @param annoSetName The name of the annotation set
   * @return The annotation set that has the particular name, or null if it does not exist. NOTICE:
   * the difference
   */
  public static AnnotationSet getSingleDocNamedAnnotationSet( Document doc , int docNo , String annoSetName ){

    //to save my typing
    String head = SINGLE_DOC_ANNOTATION_NAME_HEADER_STRING ;

    String docAnnoSetName = head + String.valueOf( docNo ) ;

    // Notice that we are getting the those annotations 
    // that are in the named AnnotationSet and covered by "DocX"
    // annotation that are located in the Original Annotation Set!
    AnnotationSet namedAnnoSet  = doc.getAnnotations( annoSetName );
    AnnotationSet originalSet = doc.getAnnotations( ORIGINAL_MARKUPS_ANNOT_SET_NAME );
 
    //first we check if the "DocX" is there in the original annotation set
    AnnotationSet docSet = originalSet.get( docAnnoSetName ) ;

    //if not there, simple return null
    if( docSet == null ){
      return null ;
    }

    //User can check the gate documentation for why I use getContained instead of get here.
    //Actually I think they are the same in this case
    AnnotationSet result = (AnnotationSet) namedAnnoSet.getContained( docSet.firstNode().getOffset(),
              docSet.lastNode().getOffset() );

    return result ;

  }

  /**
   * Check whether or not the given gate.Document object is an XML
   * in CLaC customized multi-doc format.
   *
   * @param document The Gate.Document object to be checked
   */
  public static boolean isMultiDoc( Document document ){
      if( document == null ){
   return false ;
      }

      try{
   AnnotationSet origin = document.getAnnotations( ORIGINAL_MARKUPS_ANNOT_SET_NAME );
   if( origin == null || origin.isEmpty() )
       //throw new Exception() ;
       // we just return false meaning that this is not
       return false ;
   origin = origin.get(MULTIDOC_ORIGIN_ANNOTATION_TYPE);
   if( origin == null || origin.size() == 0 )
       //throw new Exception();
       return false ;
      }catch( Exception e ){
   return false ;
      }
      return true ;
  }      


  ///////////////////////////////////////////
  //
  // Part to process the CLaC customized URL protocols
  //
  //////////////////////////////////////////

  /**
   * Get the mapping between customized URL protocol and the actual
   * local path.
   * <pre>
   *   clac :          by default it should actually point to $HOME/clac
   *   clacclasses :   by default it should actually point to $HOME/clac/Gate
   *   clacresources : by default it should actually point to $HOME/clac/Resources
   *   clactools :     by default it should actually point to $HOME/tools
   *   userhome:       by default it should point to $HOME
   * </pre>
   * @param  u    The encountered protocol
   * @return String of the real local path the protocol points to
   */
  public static String mapURL2LocalPath( URL u ){

    String protocol = u.getProtocol() ;
    if( protocol.equals( "file" ) )
      return u.getPath() ;
    System.out.println(protocol);
    Object head = protocolMap.get( protocol );
    if( head == null ){
      if( protocolMapped == false ){
 try{
   init() ;
   head = protocolMap.get( protocol );
 }catch( Exception e ){
   return u.getPath() ;
 }
      }
      else
 return u.getPath() ;
    }
 
    return (String)head+u.getPath() ;
    //return "userHome"+u.getPath() ;

  }

  /** 
   * This method is obsolete and is kept here
   * for compability only, please refer to or use
   * {@link #mapURL2LocalPath(URL) mapURL2LocalPath} instead.
   * @param  u    The encountered protocol
   * @return String of the real local path the protocol points to.
   */
  public static String mapURL2LocalDir( URL u ){
    return mapURL2LocalPath( u );
  }

  /**
   * perform the customized initialization tasks.
   * <pre>
   * 1. Check the location of the local repository directory and the external tools directory, 
   *    The checking is based on the startup environment variables ( not the system default ones that
   *    are set in the .bash_profile, because the Java code can't access them ).
   * 2. Register the customized URL protocol handlers, such as "clac:" "clacresources:"
   * </pre>
   * 
   */

  public static void init() throws Exception{

    //check if we have initialized the protocols or not
    //if yes, do nothing
    if( protocolMapped == true ){
      return ;
    }
     
    ////////////////////////////////////////////////
    //          ATTENTION TO THE FOLLOWING INSTRUCTION
    ////////////////////////////////////////////////

    //////////////////////////////////////////////
    //      CONFIGURATION OPTION 1
    //////////////////////////////////////////////

    //         SET THE STARTUP ENVIRONMENT VARIABLE

    // first we check whether the system environment is set when user starts gate.sh

    // expected environment variable:

    // CVSLOCAL     the absolute path for the local cvs directory 
    // e.g. $HOME/clac

    // TOOLSDIR       the absolute path for the external tools directory, where you put the gambit, GURU, and something like that
    // e.g. $HOME/tools

    // please note that simply setting the global environment variable in .bash_profile does not work here
    // you have to set it in the gate.sh like this:
    // the last line in gate.sh where the Gate system gets started
    // java xxx -DCVSLOCAL=$HOME/clac -DTOOLSDIR=$HOME/tools

    // if you don't like this way, you have another option
    // see the next instruction


    String cvsDir = null ;
    String resDir = null ;
    String classesDir = null ;
    String toolsDir = null ;

    //get current user's home directory
    String userHome = null ;
    userHome = System.getProperty( "user.home" );

    boolean cvsVarSet = false ;
    boolean toolVarSet = false ;

    cvsDir = System.getProperty( "CVSLOCAL" );
    if( cvsDir != null ){ // the environment variable is set
      resDir = cvsDir+"/Resources" ;
      classesDir = cvsDir+"/Gate" ;
      cvsVarSet = true ;
    }
    toolsDir = System.getProperty( "TOOLSDIR" ) ;
    if( toolsDir != null ){ // the environment variable is set
      toolVarSet = true ;
    }
  
     
    //////////////////////////////////////////////
    //      CONFIGURATION OPTION 2
    //////////////////////////////////////////////

    //        USE THE LOCAL .clac.properties file for path information

    // put one file named .clac.properties in your HOME directory
    // put this two lines in it
    // ( assume your local repository is $HOME/clac , 
    // and assume your tools directory is $HOME/tools )

    // cvs.directory=clac
    // tools.directory=tools


    if( cvsVarSet == false || toolVarSet == false ){ 
 // use the .clac.properties file
 // because we can't find them via environment variables

 System.out.println( "the environment variable in gate.sh is not set, "+
       "useing the $HOME/.clac.properties file " );

      String homeDir = null ;
      homeDir = System.getProperty( "user.home" );
     
      File clacPropertyFile = new File( homeDir+"/.clac.properties" ) ;
      Properties property = new Properties() ;

      try{
   FileInputStream stream = new FileInputStream( clacPropertyFile ) ;
   property.load( stream ) ;
      }catch( FileNotFoundException e ){
   System.out.println( ".clac.properties not found , using the default path information!" );
      }catch( IOException e ){
   throw new Exception( e.toString() );
      }
  
      cvsDir = homeDir + "/" + property.getProperty( "cvs.directory" , "clac" );
      resDir = cvsDir + "/" + property.getProperty( "cvs.resources.directory" , "Resources" );
      classesDir = cvsDir + "/" + property.getProperty( "cvs.classes.directory", "Gate" ) ;
      toolsDir = homeDir + "/" + property.getProperty( "tools.directory" , "tools" );
    }  
    
    /*
      //no need to set the system property
    System.setProperty( "cvs.directory", cvsDir ) ;
    System.setProperty( "cvs.classes.directory", classesDir );
    System.setProperty( "cvs.resources.directory", resDir );
    System.setProperty( "tools.directory", toolsDir );
    */

    protocolMap = new HashMap() ;

    // now put the mapping of the CLaC-defined URL protocols:

    // clac : by default it should actually point to $HOME/clac
    // clacclasses : by default it should actually point to $HOME/clac/Gate
    // clacresources : by default it should actually point to $HOME/clac/Resources
    // clactools : by default it should actually point to $HOME/tools

    protocolMap.put( "clac" , cvsDir );
    protocolMap.put( "clacclasses" , classesDir );
    protocolMap.put( "clacresources" , resDir );
    protocolMap.put( "clactools" , toolsDir );
    protocolMap.put( "userhome" , userHome );
     

    // register the "clac*://" url protocol handler

    // basically you have to use the fully qualified path
    // for all the pakcages that contain the handlers
    // therefore we use clac.protocols here

    System.setProperty( "java.protocol.handler.pkgs" , 
   System.getProperty( "java.protocol.handler.pkgs" )+
   "|"+"clac.protocols");


    //set the flag
    protocolMapped = true ;
  }

    /** 
     * A tentative method to get resource that is shared among different
     * processing resource.
     */
  static public Object getCLaCResource( String nameResource ){
    return resourceMap.get( nameResource );
  }

    /** 
     * A tentative method to add resource that is shared among different
     * processing resource.
     */
  static public void putCLaCResource( String nameResource , Object newResource ){
    resourceMap.put( nameResource , newResource );
  }

  /**
   * Access the environment variable 
   * 
   * @param varName name of the environment variable
   * @return String value of the environment variable or null 
   * if it is unaccessible in the current shell or the system call fails.
   */
    static public String getEnvVar( String varName ){
 
 String[] cmd = new String[1] ;
 cmd[0] = "env" ;
 try{
     Process proc = Runtime.getRuntime().exec(cmd) ;
     proc.waitFor() ;

     BufferedReader outputOfProc1 = 
  new BufferedReader
  ( new InputStreamReader( proc.getInputStream() ) );
     String line = null ;
     int idx = -1 ;
     do{
  line = outputOfProc1.readLine();
     }while( line != null && (idx=line.indexOf( varName+"=")) == -1 );
     String result = null ;
     if(line != null )
  result =  line.substring( idx+varName.length()+1 , line.length()) ;
     //System.out.println(result );
     return result ;
 }catch( Exception e ){
     return null ;
 }
    }
  
}//class CLaCUtils


/**
 *
 * $Log: CLaCUtils.java,v $
 * Revision 1.1  2006/02/17 12:19:42  perera
 * #133 change LemmatizazionAlg to GermanMorphologicalAnalyzer
 *
 * Revision 1.2  2006/02/16 18:37:53  perera
 *
 * #132 Header added to source files in LemmatizationAlg
 *
 * Revision 1.1  2006/01/25 14:46:39  perera
 * *** empty log message ***
 *
 * Revision 1.1  2005/12/02 19:28:53  perera
 * CLacUtils, should be changed later
 *
 * Revision 1.1  2005/11/29 18:06:06  perera
 * Clac Utils ( temp) should be changed
 *
 * Revision 1.17  2005/03/21 22:43:33  zhuoy_li
 * updated
 *
 * Revision 1.16  2005/02/09 04:49:14  zhuoy_li
 * now we don't sort list of annotation everywhere
 *
 * Revision 1.15  2005/02/04 04:53:06  zhuoy_li
 * updated for linkparser access
 *
 * Revision 1.14  2005/01/18 19:34:29  zhuoy_li
 * mapURL2LocalDir moved to mapURL2LocalPath because the name is more precise
 *
 * Revision 1.13  2004/12/09 22:37:06  zhuoy_li
 * long log lines moved to the end of the file
 *
 *
 * Revision 1.12  2004/12/06 01:20:24  zhuoy_li
 * long lines wrapped
 *
 * Revision 1.11  2004/11/30 01:43:30  zhuoy_li
 * new annontation extraction method based on span added
 *
 * Revision 1.10  2004/09/11 23:26:33  zhuoy_li
 * source code was re-edited for better readability of different
 * functional units
 *
 * Revision 1.9  2004/08/26 03:13:41  zhuoy_li
 * one new method added to retrieve annotations via offset
 *
 * Revision 1.8  2004/08/09 01:44:58  zhuoy_li
 * bug in getTokenAnnotationContent, which caused the space between the first and the second tokens
 * skipped, fixed
 *
 * Revision 1.7  2004/08/04 21:04:29  zhuoy_li
 * funtionality for accessing Unix/Linux environment variable via external program "env" added
 *
 * Revision 1.6  2004/08/01 05:03:19  zhuoy_li
 * funtionality that extracts annotation based on Token's string feature enhanced.
 *
 * Revision 1.5  2004/02/23 00:33:12  rwitte
 * Added some more wacky whitespace characters to be removed from doc content
 *
 * Revision 1.4  2004/02/19 10:57:08  zhuoy_li
 * verification added for one method
 *
 * Revision 1.3  2004/02/16 18:54:08  rwitte
 * Added check for non-existent annotation ID in getAnnotationContainingId()
 *
 * Revision 1.2  2004/02/15 07:18:26  zhuoy_li
 * new getDocAnnotated method added
 *
 * Revision 1.1  2004/02/09 20:41:54  zhuoy_li
 * first version of micellaneous clac classes:
 * currently we have CLaCUtils and timex3 package here. (CLaCUtils is moved from
 * JniLinkParser)
 *
 * Revision 1.13  2004/02/09 00:16:05  zhuoy_li
 * 1. comment updated
 * 2. in getSingleDocNamedAnnotationSet method, use getContained instead of get
 * to retrieve the annotation set
 *
 * Revision 1.12  2004/02/08 19:17:47  zhuoy_li
 * multiDoc manipulation method updated
 *
 * Revision 1.11  2004/01/21 20:03:30  zhuoy_li
 * clac-customized URL protocols updated. "userhome:/" protocol added
 *
 * Revision 1.10  2003/12/20 12:02:45  zhuoy_li
 * wrong javadoc tag fixed
 *
 * Revision 1.9  2003/12/15 16:34:28  rwitte
 * added a more general function to find an annotation of a specified that
 * contains an annotation with a given ID
 *
 * Revision 1.8  2003/12/11 15:58:49  rwitte
 * Added more methods for summarizer.
 *
 * Revision 1.7  2003/12/03 18:11:44  rwitte
 * Added methods getNumberOfDocuments and getDocumentId, based on the changed
 * markup generated by combine-multi-docs.pl.
 *
 * Revision 1.6  2003/11/25 19:40:55  zhuoy_li
 * sortAnnotations method updated
 *
 * Revision 1.5  2003/11/24 01:16:54  zhuoy_li
 * method of removing specified annotaitonset/annotations added
 *
 * Revision 1.4  2003/10/26 03:16:58  zhuoy_li
 * cvs Id and Log added
 *
 */
