//
// $Id: DetMorphology.java,v 1.2 2006/02/20 10:54:10 perera Exp $
//
// Durm German Lemmatization System
// http://www.ipd.uka.de/~durm/tm/lemma/
//
//
// Morphological classification for nouns with a determiner
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

public class DetMorphology extends MorphologyImpl
{
 
  String det;

  public MorphologyImpl classifyMorphology(Annotation token,gate.Document doc)
  {
      
      //System.out.println("HERE");
      FeatureMap fm        = token.getFeatures();
      head                       = fm.get("HEAD").toString();
      det                         = fm.get("DET").toString().toLowerCase();
      findNumberGender(token,doc);
      return this;
  }

   

  private void findNumberGender(Annotation token,gate.Document doc)
  {
                 number = "Sg.Pl";    
                 gender  = "Masc.Fem.Neut";
                 gcase   = "Nom.Akk.Dat.Gen";         
              
                 if( det.charAt(det.length()-1) == 's' || det.charAt(det.length()-1) == 'm' ||
                 det.equals("eine")                || det.equals("einen")               || det.equals("einer")    ||
          det.equals("mein")                || det.equals("dein")                || det.equals("sein")     ||
          det.equals("ihr")                   || det.equals("unser")               || det.equals("euer")      )
                 {  
                          
                        //--------------------------------------------------------------get the number---------------------------------------------------------------------------
                         number = "Sg";
                         
                         //-------------------------------------------------------------get the gender------------------------------------------------------------------------------
                         if(det.charAt(det.length()-1) == 's')
                         {
                               if(det.equals("das"))
                               {
                                  gender   = "Neut";
                                } 
                               else
                               {
                                 gender  = "Masc.Neut";
                                }
                         }
                         else if(det.equals("eine") || det.equals("einer"))
                         {
                            gender    = "Fem";
                         }
                         else if(det.equals("einen"))
                        {
                           gender    = "Masc";
                         }
                         else
                            gender  = "Masc.Neut";                                             
          }
                
                else if(det.charAt(det.length()-1) == 'n' && !det.equals("einen"))
                {
                
                //------------------------------------------------------------------number------------------------------------------------------------------------------ 
                 if(isCase(token,doc)==true)
                 {
                      //System.out.println("Here Case ");      
                      gcase   = getCase(token,doc);
               if(gcase.equals("Dat"))
                  number="Pl";
               else
                  number="Sg";
                 //----------------------------------------------------------------gender------------------------------------------------------------------------------------
                     if(number.equals("Sg"))
                        gender    = "Masc";
                   } 
                }
                
                else if(isPrepPhrase(token, doc))
                {
                        try
                        {        
                               String prep  = getPreposition(token, doc);
                                if   (  prep.equals("ab")  || prep.equals("aus")  ||  prep.equals("außer") ||   prep.equals("bei")  ||
                                       prep.equals("dank")   ||  prep.equals("entgegen")  ||   prep.equals("entsprechend")   ||  prep.equals("gegenüber")  ||   prep.equals("gemäß") ||
                                       prep.equals("mit")   ||  prep.equals("nach")   ||  prep.equals("nebst")  ||  prep.equals("samt")  ||  prep.equals("seit") ||
                                       prep.equals("von")  ||   prep.equals("zu")  ||  prep.equals("zufolge") )                   
                                      {
                              
                                              if(det.charAt(det.length()-1) == 'r')
                                             {
                                              number = "Sg";
                                              gender  = "Fem";
                                              }
                                              else if(det.charAt(det.length()-1)=='n')
                                              {
                                              number  = "Pl";
                                              }
                                      }
                                else if(prep.equals("bis") ||  prep.equals("durch")  ||  prep.equals("entlang")  ||  prep.equals("für")  ||  
                                           prep.equals("gegen")  ||  prep.equals("ohne") ||  prep.equals("um")     ||  prep.equals("wieder") )
                                         {
                                              if(det.charAt(det.length()-1) == 'n')
                                              {
                                               number = "Sg";
                                               gender  = "Masc";
                                               }   
                                            
                                         }
                                 else if(prep.equals("an") ||  prep.equals("auf") || prep.equals("hinter") ||  prep.equals("in")  || prep.equals("neben")   ||
                                             prep.equals("über")  ||  prep.equals("unter")  ||  prep.equals("vor") ||  prep.equals("zwischen"))
                                            {
                                              if(det.charAt(det.length()-1) == 'r')
                                              {
                                               number = "Sg";
                                               gender  = "Fem";
                                              }
                                            }
                     }catch(ExecutionException e) { }
                 }
                else if(det.charAt(det.length()-1) == 'r')
                {
                       if(det.equals("der")) 
                       {
                             if(!isNpfNpPhrase(token,doc))
                            {
                               number = "Sg";
                               gender  = "Masc.Fem";
                            } 
                      }
                      else
                      {
                           if(!isNpfNpPhrase(token,doc))
                           {
                                number = "Sg";
                                gender   = "Fem";
                           }
                      }
                }
       
}

}

