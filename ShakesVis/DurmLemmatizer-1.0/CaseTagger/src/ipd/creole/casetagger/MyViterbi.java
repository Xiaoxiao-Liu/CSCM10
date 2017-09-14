//
// $Id: MyViterbi.java,v 1.5 2006/02/16 17:24:58 perera Exp $
//
// Durm German Lemmatization System
// http://www.ipd.uka.de/~durm/tm/lemma/
//
//
// Implementation of the Viterbi interface. Implements the getBestSequence() method
// which finds the best sequence of case tags for the nouns in the sentence.
//
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

import gate.*;
import gate.creole.*;
import gate.util.*;
import gate.event.*;
import gate.gui.MainFrame;


import java.util.*;


public class MyViterbi implements Viterbi
{
  
  
   double transprobs3[][][];
   double transprobs2[][];
   double score[][][];
   
   double startProb[];
   double wordProb[];
   double stateProb[][];
   int numofstates;
   
   LoadProbs load;
   LexicalProbs lprobs;
   ContextProbs cprobs;  
   ContextTags ctags;  
   
  
  
  public MyViterbi(String filetolook) throws java.io.IOException
  {
    System.out.println(filetolook);
        numofstates = 32;
 transprobs3 = new double[32][32][32];
 transprobs2 = new double[32][32];
 startProb   = new double[4];
 load              = new LoadProbs(filetolook);
 lprobs            = new LexicalProbs(filetolook);
 loadFiles();
 loadStartProbs();
 
 cprobs = new ContextProbs(filetolook);
 ctags = new ContextTags();
  } 
  
  private void loadFiles() throws java.io.IOException
  {
    double val= 1.0/300000;
 
 for(int i=0;i<32;i++)
 {
   for(int j=0;j<32;j++)
    {
      transprobs2[i][j]=val;
   for(int k=0;k<32;k++)
   {
    transprobs3[i][j][k]=val;
   }
    }
  }
 
 load.loadProbs(transprobs2);
 load.loadProbs(transprobs3);
 
   
  }
  
  private double getLexicalProb(String word,int tag,int gcase)
  {
    return lprobs.getProb(word,tag,gcase);
  }
  private double getContextualProb(String context[],int tags[],int num)
  {
    return cprobs.getContextProbability(context,tags,num);
  }
  private void loadStartProbs()
  {
     for(int i=0;i<4;i++)
  {
    startProb[i]=transprobs2[31][i];
   
  }
  }


  // returns the best sequence
  public int[] getBestSequence(boolean[] flags,int[] pos,String[]tags,int[] type,String[][] context,int[][] ctype, boolean[][] idefcase)
  {
    int current = 0;
 double score[][][] = new double[pos.length][32][32];
 int backtrack[][][] = new int[pos.length][32][32] ;
 
 if(flags.length<1)
 {
   int fail[]=new int[1];
   fail[0]=7;
   return fail;
 }
 
 //---------------------------------------------------------intial probabilities-----------------------------------
 
 if(flags[current])
 {
     score[0][0][pos[current]] =1;
     backtrack[0][0][pos[current]]=0;
 }
 else
 {
     for(int i =0 ; i < 4 ; i++)
        {
    if(idefcase[current][i])
    {
           
     score[0][0][i]  = startProb[i]*getLexicalProb(tags[current],type[current],i);
        backtrack[0][0][i] = 0; 
     
    }
  
        }
 }
 
 //-----------------------------------------------------------2 order probabilities----------------------------------
 current++; // increment current
 
 int prev = current-1;
 
 if(pos.length > 1)
 {
 
   if(flags[current])
   {
       if(flags[prev])
    {
    score[1][pos[prev]][pos[current]]=1;
    backtrack[1][pos[prev]][pos[current]]=0;
    }
    else
    {
      for(int i=0;i<4;i++)
      {
         if(idefcase[prev][i])
      {
       score[1][i][pos[current]] = transprobs2[i][pos[current]]*score[0][0][i];
       backtrack[1][i][pos[current]]=0;
      }
   
      }
    }
  
   }
   else
   {
       if(flags[prev])
    {
       for(int i=0;i<4;i++)
       {
          if(idefcase[current][i])
                {    
     
        score[1][pos[prev]][i] = transprobs2[pos[prev]][i]*getLexicalProb(tags[current],type[current],i)*getContextualProb(context[current],ctype[current],i);
        backtrack[1][pos[prev]][i]=0;
     
       }
       }   
     }
    else
    {
       for( int i = 0; i<4 ; i++)
             { 
                if(idefcase[current][i])
                {      
            for( int j =0 ; j< 4 ; j++)
                  {
         if(idefcase[prev][j])
      {
                      score[1][j][i] = transprobs2[j][i]*score[0][0][j]*getLexicalProb(tags[current],type[current],i)*getContextualProb(context[current],ctype[current],i);
                      backtrack[1][j][i] = 0;
      }
         }
       }
             }
    }
     }
 }
 
 if(pos.length == 1)
 {
   
   int ret[] = new int[1];
   ret[0]=0;
   if(flags[prev])
      {
     ret[0]=pos[prev];
      }
      else
      {
     double maxvalue =0.0;
     for(int i=0;i<4;i++)
  {
   if(idefcase[prev][i])
   {
      double value =score[0][0][i];
      if(value > maxvalue)
      {
        maxvalue = value;
        ret[0]=i; 
      }
   }
  }
  
      }
     return ret;   
 }
 
 //-----------------------------------------------trigram probabilities----------------------------------------------------
 
 
 
 for( int i = 2 ; i < pos.length ; i++)
    {
       current = i;
    prev    =i-1;
    int supprev =i-2;
    
    if(flags[current])
    {
           if(flags[prev])
     {
                if(flags[supprev])
       {
         score[i][pos[prev]][pos[current]]=1;
         backtrack[i][pos[prev]][pos[current]]=pos[supprev];
         
       }
       else
       {
         double maxvalue = 0.0;
         for(int l=0;l<4;l++)
         {
           if(idefcase[supprev][l])
        {
              
        double value = transprobs3[l][pos[prev]][pos[current]]*score[i-1][l][pos[prev]];
           if(value > maxvalue)
           {        
           maxvalue = value;
           backtrack[i][pos[prev]][pos[current]] = l;
           }
        }
         }
         score[i][pos[prev]][pos[current]]=maxvalue;
         //System.out.println(backtrack[i][pos[prev]][pos[current]]);
       }
     }
     else
     {
                if(flags[supprev])
       {
           for(int k=0;k<4;k++)
           {
           if(idefcase[prev][k])
                                {        
           
           score[i][k][pos[current]] =transprobs3[pos[supprev]][k][pos[current]]*score[i-1][pos[supprev]][k];
          
           backtrack[i][k][pos[current]] = pos[supprev];
        }
           }
       }
       else
       {
          for(int k =0 ; k< 4;k++)
       {
          if(idefcase[prev][k])
                               {          
          double maxvalue = 0.0;
             for(int l=0;l<4;l++)
             {
                if(idefcase[supprev][l])
          {
             
                
          double value = transprobs3[l][k][pos[current]]*score[i-1][l][k];
                if(value > maxvalue)
                {
                maxvalue = value;
             backtrack[i][k][pos[current]] = l;
                }
          }
              }
          score[i][k][pos[current]] = maxvalue;
          }
       }
       }
     }
    }
    else
    {
           if(flags[prev])
     {
                if(flags[supprev])
       {
          for(int j=0;j<4;j++)
       {
         if(idefcase[current][j])
         {
           score[i][pos[prev]][j] = transprobs3[pos[supprev]][pos[prev]][j]*getLexicalProb(tags[current],type[current],j)*getContextualProb(context[current],ctype[current],j);
           backtrack[i][pos[prev]][j]=pos[supprev];
         }
       }
       }
       else
       {
          for(int j=0; j < 4; j++)
       {
          if(idefcase[current][j])
                               {          
          double maxvalue = 0.0;
          for(int l=0;l<4;l++)
          {
            if(idefcase[supprev][l])
                                    {         
            double value = transprobs3[l][pos[prev]][j]*score[i-1][l][pos[prev]]*getLexicalProb(tags[current],type[current],j)*getContextualProb(context[current],ctype[current],j);
               if(value > maxvalue)
               {
               maxvalue = value;
            backtrack[i][pos[prev]][j]=l;
               }
          }
          }
        score[i][pos[prev]][j]=maxvalue;
          }
       }
       }
     }
     else
     {
                if(flags[supprev])
       {
          for(int j=0;j<4;j++)
       {
         if(idefcase[current][j])
         {
            for(int k=0;k<4;k++)
            {
              if(idefcase[prev][k]) 
           {
            score[i][k][j]=transprobs3[pos[supprev]][k][j]*score[i-1][pos[supprev]][k]*getLexicalProb(tags[current],type[current],j)*getContextualProb(context[current],ctype[current],j);
            backtrack[i][k][j]=pos[supprev];
           }
            }
         }
       }
       }
       else
       {
          for(int j=0;j<4;j++)
       {
          if(idefcase[current][j])
                               {          
           for(int k=0;k<4;k++)
              {
                 if(idefcase[prev][k])
                                      {           
           double maxvalue = 0.0;
                 for(int l=0;l<4;l++)
                 {
                   if(idefcase[supprev][l])
             {
                 double value = transprobs3[l][k][j]*score[i-1][l][k]*getLexicalProb(tags[current],type[current],j)*getContextualProb(context[current],ctype[current],j);
                 if( value > maxvalue)
                 {
                 maxvalue = value;
                 backtrack[i][k][j]=l;
                 }
             }
                 }
                score[i][k][j]=maxvalue;
           }
              }
          }
       }
       }
     }
     
    }
    
    
    
   }
   
   //------------------------------------------------------------------------------------------------------------------------------
 
 

   // get the best path
   
   double mymax = 0.0;
   int one=0;
   int two=0;
   
   
   
   
   if(flags[current])
   {
     if(flags[prev])
  {
    one = pos[current];
    two = pos[prev];
  }
  else
  {
    for(int i=0;i<4;i++)
    {
      if(idefcase[prev][i])
   {
     double val = score[current][i][pos[current]];
        if(val > mymax)
        {
       mymax = val;
       one   =pos[current];
       two   =i;
        }
   }
    }
  }
   }
   else
   {
     if(flags[prev])
  {
     for(int j=0;j<4;j++)
     {
       if(idefcase[current][j])
          {    
   double val = score[current][pos[prev]][j];
         if(val > mymax)
         {
        mymax = val;
        one   =j;
        two   =pos[prev];
         }
    }
    }
  }
  else
  {   for(int i =0 ; i<4; i++)
      {
            if(idefcase[current][i])
   {
       for(int j = 0 ; j<4 ; j++)
             {
                if(idefcase[prev][j])
       {
        double val = score[current][i][j];
              if(val > mymax)
              {
              mymax = val;
           one =  j;
           two = i;
              }
       }
             }
   }
         }
  }
   }
   
       
   //System.out.println("the path  ----------------------------------------------------"); 
   int backret[] = new int[pos.length];
   backret[pos.length-1]=one;
   backret[pos.length-2]=two;
   //System.out.print(one+"  ");
   //System.out.print(two+"  ");
 
   for(int i=current;i > 1; i--)
   {
     int val = backtrack[i][two][one];
  backret[i-2]=val;
  //System.out.print(val+"  ");
  one = two;
  two = val;
  
   }
   
   return backret;


  }  
   
}
