//
// $Id: MorphologyImpl.java,v 1.2 2006/02/20 10:54:10 perera Exp $
//
// Durm German Lemmatization System
// http://www.ipd.uka.de/~durm/tm/lemma/
//
//
// Defines the interface for implementation classes of the
// German Morphological Analyzer.
// Defines the method classifyMorphology(), which classifies a noun
// into morphological classes based on number, gender, and case.
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
import gate.*;
import java.util.*;
import gate.creole.*;
import gate.util.*;
import gate.event.*;

public abstract class MorphologyImpl
{
   
   String head;
   String gender;
   String number;
   String gcase;
   CLaCUtils cutils = new CLaCUtils();

   
   public abstract MorphologyImpl classifyMorphology(Annotation token,gate.Document doc);
  

   public String getCase(Annotation token,gate.Document document)
   {
                
 Annotation casetoken   =  cutils.getAnnotationContainingId(document,token.getId(),"Case");
 FeatureMap fmap         =  casetoken.getFeatures();
        if(fmap.get("Case") == null)
           return "Nom.Akk.Dat.Gen";
       else 
           return fmap.get("Case").toString();
 
 
    }

  /*
  Check The Preposition
  */
   public boolean isPrepPhrase(Annotation token,gate.Document doc)
     {
  
      if(cutils.getAnnotationContainingId(doc,token.getId(),"PN")!= null)
   return true;
      else
         return false; 
     }
  
  
    /*
    get the prepsotion in the preposition phrase within the noun
    */
    
    public String getPreposition(Annotation token,gate.Document doc) throws ExecutionException
    {
        Annotation prepNP     = cutils.getAnnotationContainingId(doc,token.getId(),"PN");
 Annotation prepostion = cutils.getAnnotationContainingId(doc,prepNP.getId(),"Preposition");
 String prepName;
          try {
              prepName = doc.getContent().getContent(
       prepNP.getStartNode().getOffset(),
       prepNP.getEndNode().getOffset()).toString().replace('\n', ' ');
            } catch (InvalidOffsetException ioe) {
                 throw new ExecutionException("Invalid offset of the annotation");
            }
    
         StringTokenizer st = new StringTokenizer(prepName);
 
 return st.nextToken().toLowerCase();
   }
   

    public boolean isNpfNpPhrase(Annotation token,gate.Document doc)
    {
   
   
   
        if(cutils.getAnnotationContainingId(doc,token.getId(),"NPFNP")!= null)
  {
     String npfnpString=null;
  String tokenString=null;
     try
        {    
      Annotation npfnp   = cutils.getAnnotationContainingId(doc,token.getId(),"NPFNP");
          npfnpString = CLaCUtils.getTokenContentInAnnotation( doc,npfnp).trim();
   tokenString = CLaCUtils.getTokenContentInAnnotation( doc, token).trim();
     
  }catch(Exception e)
  {}
  
  
  return npfnpString.endsWith(tokenString);
  
  
  
  }
  else
    return false;


     
  }

/*
check case has been tagged
*/  

 public boolean isCase(Annotation token,gate.Document document)
     {
       if(cutils.getAnnotationContainingId(document,token.getId(),"Case")!= null)
         return true;
       else
         return false;
 
     }

}
