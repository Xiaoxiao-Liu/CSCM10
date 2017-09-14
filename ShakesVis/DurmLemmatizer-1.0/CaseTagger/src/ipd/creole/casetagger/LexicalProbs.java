//
// $Id: LexicalProbs.java,v 1.3 2006/02/16 17:24:58 perera Exp $
//
// Durm German Lemmatization System
// http://www.ipd.uka.de/~durm/tm/lemma/
//
//
// Produces lexical probabilities to the case tagger from the probability files
// 
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

import java.io.*;
import java.util.*;

public class LexicalProbs
{
  
  double[][] nouns = new double[40000][4];
  ArrayList nounlist  = new ArrayList();
  double[][] ppers = new double[20][4];
  ArrayList pperlist = new ArrayList();
  double[][] prfs  = new double[6][4];
  ArrayList prflist = new ArrayList();

  public LexicalProbs(String filetolook) throws java.io.IOException
  {
    LoadProbs lp = new LoadProbs(filetolook);
    lp.loadProbs(nouns,nounlist,0);
    lp.loadProbs(ppers,pperlist,1);
    lp.loadProbs(prfs,prflist,2);
  }

  public double getProb(String word,int tag,int gcase)
  {
    //System.out.println(word);
 //System.out.println(tag);
 //System.out.println(gcase);
    if(tag == 0)
    {
      int position = nounlist.indexOf(word);
   //System.out.println(position);
   if(position < 0)
      return 1.0/30000;
   else
        return nouns[position][gcase];

    }
    else if(tag == 1)
    {
      int position = pperlist.indexOf(word);
   if(position < 0)
      return 1.0/30000;
   else
         return ppers[position][gcase];
    }
    else
    {
      int position = prflist.indexOf(word);
   if(position < 0)
      return 1.0/30000;
   else
         return prfs[position][gcase];
    }
  }

}
