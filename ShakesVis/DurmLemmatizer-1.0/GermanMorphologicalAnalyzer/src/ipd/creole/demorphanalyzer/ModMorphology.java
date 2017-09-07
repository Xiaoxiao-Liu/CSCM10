//
// $Id: ModMorphology.java,v 1.2 2006/02/20 10:54:10 perera Exp $
//
// Durm German Lemmatization System
// http://www.ipd.uka.de/~durm/tm/lemma/
//
//
// Morphological classification for nouns only with a modifier
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


public class ModMorphology extends  MorphologyImpl
{

  String mod;
 
  public MorphologyImpl classifyMorphology(Annotation token, gate.Document doc)
  {
      //System.out.println("HERE");
      FeatureMap fm         =  token.getFeatures();
      head                          =  fm.get("HEAD").toString();
      mod                           =  fm.get("MOD").toString().toLowerCase();
      findNumberGender(token,doc);
      return this;
  }

 private void findNumberGender(Annotation token,gate.Document doc)
  {
          number = "Sg.Pl";    
          gender  = "Masc.Fem.Neut";
          gcase   = "Nom.Akk.Dat.Gen";  

          if(mod.charAt(mod.length()-1) == 'm')
          {
       number = "Sg";
              gender  = "Masc.Neut";
          }
   else if(mod.charAt(mod.length()-1) == 's')
          {
        number ="Sg";
               gender  = "Masc.Neut";
          }
   else if(mod.charAt(mod.length()-1) == 'r')
     {
     if (isCase(token,doc)==true)
                  {
                           gcase   = getCase(token,doc);
                           if(gcase.equals("Nom") )
                          {
                  number = "Sg";
                                gender  = "Masc";
                           }
             else if(gcase.equals("Dat"))
                          {
                 number = "Sg";
                               gender  ="Fem";
                          }
    
    }
         }
  else if(mod.charAt(mod.length()-1) == 'n')
  {
       if (isCase(token,doc)==true)
                  {
                         gcase   = getCase(token,doc);
                         if(gcase.equals("Akk"))
                         {
                number ="Sg";
                              gender  ="Masc";
                          }
                         else if(gcase.equals("Dat"))
                         {
                              number = "Pl";
                            
                         }
    
                  }
    }

}

}
