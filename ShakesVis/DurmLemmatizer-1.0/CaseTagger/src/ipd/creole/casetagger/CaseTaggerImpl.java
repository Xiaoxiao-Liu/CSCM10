//
// $Id: CaseTaggerImpl.java,v 1.5 2006/03/07 17:48:10 perera Exp $
//
// Durm German Lemmatization System
// http://www.ipd.uka.de/~durm/tm/lemma/
//
//
// Implementation of the case tagger. Implements the interface CaseTaggerInt, which returns an array of
// strings that contains the grammatical case for each noun.
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
 


public class CaseTaggerImpl implements CaseTaggerInt
{

         
  
  String features[]={"APPR","APPRART","APPO","KOUI","KOUS","KON","KOKOM","NN","NE","PPER","PRF","PDS","PIS",
                            "PPOSS","PRELS","PRELAT","PWS","PWAV","PTKZU","VVFIN","VVIMP","VVINF","VVIZU","VVPP",
                        "VAFIN","VAINF","VAPP","VMFIN","VMINF","VMPP","$,","$."};

         String[] contextfeatures ={"APPR","APPRART","ADJA","PDAT","PIAT","PIDAT","PPOSAT","ART"};


         ArrayList fet ;
         ArrayList confet;

         boolean flags[];
         String words[];
         String  ptags[];
         int     pos[];
         int     type[];
         String  context[][];
         int     contag[][];

         Tags  tg;
         ContextTags contg;
         Viterbi viterbi;

         // intergration----------------------
         CLaCUtils cutils = new CLaCUtils();
         boolean idefcase[][];


         public CaseTaggerImpl(String filetolook)
         {
           try
           {
           fet = new ArrayList(Arrays.asList(features));
           confet = new ArrayList(Arrays.asList(contextfeatures));
           tg = new Tags();
           contg = new ContextTags();
           viterbi = new MyViterbi(filetolook);
           }catch(java.io.IOException e)
           {
             System.out.println(e.toString());
             System.out.println("Please enter the directory containing the probability files");
           }
         }

         //public int[] getBestSequence(boolean[] flags,int[] pos,String[]tags,int[] type)


         public String[] getCaseTags(gate.Document gatedoc, ArrayList tokens)
         {
            extractFeatures(tokens, gatedoc);
            int ret[]=viterbi.getBestSequence(flags,pos,words,type,context,contag,idefcase);

                  String retcase[]=new String[ret.length];
                  int casecount =0;
                  for(int i=0;i<ret.length;i++)
                  {
                    //System.out.println(words[i]+"     "+tg.getTag(ret[i]));
                        if( ret[i] == 0 || ret[i] == 1 || ret[i] == 2 || ret[i] == 3 )
                        {
                            if(type[i]==0)
                                {
                                retcase[casecount]=tg.getTag(ret[i]);
                                casecount++;
                                }
                        }

                  }


                //System.out.println("-------------------------------------END--------------------------------");
                return retcase;
         }

         private void extractFeatures(ArrayList tokens,gate.Document doc)
         {

                 int sentencesize=tokens.size();
                 boolean currentflags[] = new boolean[sentencesize];
                 String  currentwords[] = new String[sentencesize];
                 String currentptags[]  = new String[sentencesize];
                 int currentPos[]       = new int[sentencesize];
                 int currentTyp[]       = new int[sentencesize];
                 int count = 0;
                 //-----------context
                 String currcontext[][]     =new String[sentencesize][];
                 int    currcontag[][]      =new int[sentencesize][];

                 //-----------

                 //----------intergration from rule based morphology
                 boolean currentidefcase[][] = new boolean[sentencesize][4];

                 for(int i=0;i<tokens.size();i++)
                 {

                   Annotation token =(Annotation)tokens.get(i);
                   if(token.getType().equals("Token"))
                   {
                     FeatureMap fm = token.getFeatures();
                     String pos    =fm.get("category").toString();
                     String word   =fm.get("string").toString();

                     if(fet.contains(pos))
                     {
                        if(pos.equals("NN") || pos.equals("NE"))
                         {
                                    currentTyp[count]=0;
                                        currentflags[count]=false;
                                        currcontext[count] = getContext(tokens,i);
                                        currcontag[count]  = getContext1(tokens,i);

                                        // additional code of intergrating---------------------------------------------------------------------



                                        if(cutils.getAnnotationContainingId(doc,token.getId(),"Morphology")!= null)
                        {

                        try
                    {
                            Annotation morphology   = cutils.getAnnotationContainingId(doc,token.getId(),"Morphology");
                                        FeatureMap fmorph = morphology.getFeatures();
                            String detcase= fmorph.get("CASE").toString();
                                        if(detcase.indexOf("Nom") > -1)
                                         currentidefcase[count][0] = true;
                                        if(detcase.indexOf("Akk") > -1)
                                         currentidefcase[count][1] = true;
                                        if(detcase.indexOf("Dat") > -1)
                                         currentidefcase[count][2] = true;
                                        if(detcase.indexOf("Gen") > -1)
                         currentidefcase[count][3] = true;
                                         //System.out.println("case is :-   "+detcase);
                            }catch(Exception e)
                            {}

                            }
                                        else
                                        {
                                          currentidefcase[count][0] = true;
                                          currentidefcase[count][1] = true;
                                          currentidefcase[count][2] = true;
                                          currentidefcase[count][3] = true;
                                        }






                                        //-------------------------------------------------------------------------------------------------------


                }
                    else if(pos.equals("PPER"))
                                {
                                    currentTyp[count]=1;
                                        currentflags[count]=false;
                                        currcontext[count] = getContext(tokens,i);
                                        currcontag[count]  = getContext1(tokens,i);

                                        // intergration

                                          currentidefcase[count][0] = true;
                                          currentidefcase[count][1] = true;
                                          currentidefcase[count][2] = true;
                                          currentidefcase[count][3] = true;
                                }
                                else if(pos.equals("PRF"))
                                {
                                   currentTyp[count]=2;
                                   currentflags[count]=false;
                                   currcontext[count] = getContext(tokens,i);
                                   currcontag[count]  = getContext1(tokens,i);

                                   // intergration

                                          currentidefcase[count][0] = true;
                                          currentidefcase[count][1] = true;
                                          currentidefcase[count][2] = true;
                                          currentidefcase[count][3] = true;
                                }
                            else
                                {
                               currentflags[count]=true;
                                   currentTyp[count]=-1;
                                   currcontext[count]= new String[0];
                                   currcontag[count] = new int[0];
                            }

                            currentwords[count]=word;
                            currentptags[count]=pos;
                                currentPos[count]=tg.getTag(pos);

                            count++;

                     }

                 }




                 }
                 flags = new boolean[count];
                 words = new String[count];
                 ptags = new String[count];
                 pos   = new int[count];
                 type  = new int[count];
                 context=new String[count][];
                 contag =new int[count][];

                 //intergration
                 idefcase = new boolean[count][4];

                 for(int i=0;i<count;i++)
                 {
                  flags[i]=currentflags[i];
                  words[i]=currentwords[i];
                  ptags[i]=currentptags[i];
                  pos[i]=currentPos[i];
                  type[i]=currentTyp[i];
                  context[i]=currcontext[i];
                  contag[i]= currcontag[i];

                  //intergration
                  idefcase[i]=currentidefcase[i];

                 }




         }


         private String[] getContext(ArrayList tokens,int pointer)
         {

                 String currTokens[]= new String[20];
                 int    currTags[]  = new int[20];
                 int count =0;
                 while( pointer-1 > 0 && count < 4)
         {
                   Annotation token =(Annotation)tokens.get(pointer-1);
                   pointer = pointer -1;
           if(!token.getType().equals("Token"))
                   {

                          continue;
                   }
                   else
                   {
                      FeatureMap fm = token.getFeatures();
                      String pos    =fm.get("category").toString();
                      String word   =fm.get("string").toString();

                      if(fet.contains(pos))
                          {
                             if(pos.equals("APPR") || pos.equals("APPRART"))
                            {

                                    currTokens[count]=word;
                                    currTags[count]=contg.getTag(pos);
                                        count++;
                                    pointer = -1;
                                 }
                                 else
                                    pointer = -1;
                          }
                          else if(confet.contains(pos))
                          {
                              currTokens[count]=word;
                                  currTags[count]=contg.getTag(pos);

                                  count++;
                          }
                          else
                              pointer = -1;
                   }
         }

                 String mycontext[] = new String[count];
                 int mytags[]    = new int[count];
         for(int i=0;i<count;i++)
                 {
                    mycontext[i]=currTokens[i];
                        mytags[i]   =currTags[i];
                 }

                 return mycontext;

         }

         private int[] getContext1(ArrayList tokens,int pointer)
         {
            String currTokens[]= new String[20];
                 int    currTags[]  = new int[20];
                 int count =0;
                 while( pointer-1 > 0 && count < 4)
         {
                   Annotation token =(Annotation)tokens.get(pointer-1);
                   pointer = pointer -1;
           if(!token.getType().equals("Token"))
                   {

                          continue;
                   }
                   else
                   {
                      FeatureMap fm = token.getFeatures();
                      String pos    =fm.get("category").toString();
                      String word   =fm.get("string").toString();

                      if(fet.contains(pos))
                          {
                             if(pos.equals("APPR") || pos.equals("APPRART"))
                            {

                                    currTokens[count]=word;
                                    currTags[count]=contg.getTag(pos);
                                        count++;
                                    pointer = -1;
                                 }
                                 else
                                    pointer = -1;
                          }
                          else if(confet.contains(pos))
                          {
                              currTokens[count]=word;
                                  currTags[count]=contg.getTag(pos);

                                  count++;
                          }
                          else
                              pointer = -1;
                   }
         }

                 String mycontext[] = new String[count];
                 int mytags[]    = new int[count];
         for(int i=0;i<count;i++)
                 {
                    mycontext[i]=currTokens[i];
                        mytags[i]   =currTags[i];
                 }

                 return mytags;


         }




}
