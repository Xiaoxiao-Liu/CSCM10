//
// $Id: DetModMorphology.java,v 1.2 2006/02/20 10:54:10 perera Exp $
//
// Durm German Lemmatization System
// http://www.ipd.uka.de/~durm/tm/lemma/
//
//
// Morphological classification for nouns with a modifier and a determiner
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


public class DetModMorphology extends MorphologyImpl
{

  String det;
  String mod;

  public MorphologyImpl classifyMorphology(Annotation token,gate.Document doc)
  {
     // System.out.println("HERE");
      FeatureMap fm         =  token.getFeatures();
      head                          =  fm.get("HEAD").toString();
      det                             =  fm.get("DET").toString().toLowerCase();
      mod                           =  fm.get("MOD").toString().toLowerCase();
      findNumberGender(token,doc);
      return this;
  }

  private void findNumberGender(Annotation token,gate.Document doc)
  {
       number = "Sg.Pl";    
       gender  = "Masc.Fem.Neut";
       gcase   = "Nom.Akk.Dat.Gen";  

        if(det.charAt(det.length()-1) == 'e')
        {
      if(mod.charAt(mod.length()-1) == 'e')
             {
       number="Sg";
                     gender  ="Fem";
             }
             else if(mod.charAt(mod.length()-1) == 'n')
             {
       number="Pl"; 
             }
 }
  
        else if(det.charAt(det.length()-1) == 'n')
 {
       if (isCase(token,doc)==true)
              {
                      gcase   = getCase(token,doc);
               if(gcase.equals("Dat"))
                      {
                    number="Pl";
                      }
               else
                      {
                    number="Sg";
                           gender ="Masc";
                       }
              }
 }
       
  }
  
}
