//
// $Id: NonDetModMorphology.java,v 1.2 2006/02/20 10:54:10 perera Exp $
//
// Durm German Lemmatization System
// http://www.ipd.uka.de/~durm/tm/lemma/
//
//
// Morphological classification for nouns without a modifier or a determiner
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
import gate.creole.*;
import gate.util.*;
import gate.event.*;


public class NonDetModMorphology extends MorphologyImpl
{



  public MorphologyImpl classifyMorphology(Annotation token, gate.Document doc)
  {
      //System.out.println("HERE");
      FeatureMap fm         =  token.getFeatures();
      head                          =  fm.get("HEAD").toString();
      findNumberGender(token,doc);
      return this;
  }

private void findNumberGender(Annotation token,gate.Document doc)
  {
       number = "Sg.Pl";    
       gender  = "Masc.Fem.Neut";
       gcase   = "Nom.Akk.Dat.Gen";  
       
       if(isPrepPhrase(token, doc))
       {
            try
            {        
                    String prep  = getPreposition(token, doc);
                    if( prep.equals("vom") || prep.equals("zum") || prep.equals("im") || prep.equals("am") || prep.equals("beim"))
     {
                gender ="Masc.Neut";
         number ="Sg";
    
      }
      else if( prep.equals("zur"))
      {
                gender ="Fem";
         number ="Sg";
      
      }
      else if( prep.equals("ins") || prep.equals("ans"))
      {
                 gender ="Neut";
           number ="Sg";
    
      }
            
            }catch(ExecutionException e) { }
       }  
 }
 
  

}
