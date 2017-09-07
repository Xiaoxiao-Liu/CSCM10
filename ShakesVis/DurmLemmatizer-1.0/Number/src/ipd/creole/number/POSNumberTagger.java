//
// $Id: POSNumberTagger.java,v 1.2 2006/02/16 17:33:46 perera Exp $
//
// Durm German Lemmatization System
// http://www.ipd.uka.de/~durm/tm/lemma/
//
//
// Implementation of the POS-based number tagger. Implements the interface
// NumberTagger and its method getNumber() that analyzes a sentence and returns the number of the subject.
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

package ipd.creole.number;

import java.util.*;
import gate.*;


public class POSNumberTagger implements NumberTagger
{
   
   CLaCUtils cutils = new CLaCUtils();   
   
   
   public String getNumber(ArrayList tokens, gate.Document document)
   {
        String subject  = getSubject(tokens,document);
 String mainverb = getMainVerb(tokens);
 if(!subject.equals("NO") && !mainverb.equals("NO"))
 {
   if(mainverb.equals("sind"))
    return subject+"1";
   if(mainverb.endsWith("en"))
   {
    return subject+"1";
   }
   else
          {
    return subject+"0";
   }
  }
  else
    return "NO";
   }
   
   
   private String getMainVerb(ArrayList tokens)
   {
     for(int i=0;i<tokens.size();i++)
     {
     
        Annotation token =(Annotation)tokens.get(i);
 if(token.getType().equals("Token"))
 {
   FeatureMap fm = token.getFeatures();
   String pos    =fm.get("category").toString();
   String word   =fm.get("string").toString();
    
          if(pos.equals("$,"))
     return "NO";
      
   if(word.equals("und"))
     return "NO"; 
    
   if(pos.equals("VVFIN") || pos.equals("VAFIN") || pos.equals("VMFIN"))
                 {
            return word;      
    
                 }    
    
    
  }
      } 
      return "NO";
   }
   
   private String getSubject(ArrayList tokens,gate.Document document)
   {
      int counter = 0;
      for(int i=0;i<tokens.size();i++)
      {
     
        Annotation token =(Annotation)tokens.get(i);
 if(token.getType().equals("Token"))
 {
   FeatureMap fm = token.getFeatures();
   String pos    =fm.get("category").toString();
   String word   =fm.get("string").toString();
   
   if(pos.equals("NN") || pos.equals("NE"))
                 {
      counter++;
             if(cutils.getAnnotationContainingId(document,token.getId(),"Case")!= null)
                {
                 try
                          {    
                     Annotation casetoken   = cutils.getAnnotationContainingId(document,token.getId(),"Case");
       FeatureMap fmap        = casetoken.getFeatures();
                                   String gcase           = fmap.get("Case").toString();
       if(gcase.equals("Nom"))
         return (new Integer(counter)).toString();
       }catch(Exception e)
             {}
                 } 
   }    
    
    
 }
      } 
      return "NO";
   }
   
   
  
}