//
// $Id: LoadProbs.java,v 1.7 2006/02/16 17:24:58 perera Exp $
//
// Durm German Lemmatization System
// http://www.ipd.uka.de/~durm/tm/lemma/
//
//
// Load the n-gram probabilites to the case tagger.
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

public class LoadProbs
{

  String filepath;
  public LoadProbs(String path)
  {
    filepath = path;
   System.out.println(path);
  }

  public void loadProbs(double probs[][],ArrayList words,int tag) throws java.io.IOException
  {
    
    FileReader fr1 = new FileReader(filepath+"/WordStat.txt");
    FileReader fr2 = new FileReader(filepath+"/pperstat.txt");
    FileReader fr3 = new FileReader(filepath+"/prfstat.txt");
    BufferedReader br;
    if(tag==0)
      br = new BufferedReader(fr1);
    else if(tag == 1)
      br = new BufferedReader(fr2);
      else
        br = new BufferedReader(fr3);

    String str;

    while( !((str = br.readLine()) == null))
    {
      StringTokenizer st = new StringTokenizer(str);
      String word = st.nextToken();
      words.add(word);
      String vals[]= new String[4];
      int pos =words.indexOf(word);
      for(int i=0;i<4;i++)
      {
        vals[i]=st.nextToken();
        probs[pos][i]=Double.parseDouble(vals[i]);
      }

    }

  }


  public void loadProbs(double[][][] probs) throws java.io.IOException
  {
    FileReader fr = new FileReader(filepath+"/context3.txt");
    BufferedReader br = new BufferedReader(fr);
    Tags tg = new Tags();
    String str;

    while( !((str = br.readLine()) == null))
    {
     StringTokenizer st = new StringTokenizer(str);
     String gram1=st.nextToken();
     String gram2=st.nextToken();
     String gram3=st.nextToken();
     String prob =st.nextToken();
     if(!gram1.equals("*") && !gram2.equals("*") && !gram3.equals("*"))
     {
       double biprob = Double.parseDouble(prob);
       int pos1 = tg.getTag(gram1);
       int pos2 = tg.getTag(gram2);
       int pos3 = tg.getTag(gram3);
       probs[pos1][pos2][pos3] = biprob;
     }
    }



  }

  public void loadProbs(double[][] probs) throws java.io.IOException
  {
    FileReader fr = new FileReader(filepath+"/context2.txt");
    BufferedReader br = new BufferedReader(fr);
    Tags tg = new Tags();

    String str;

    while( !((str = br.readLine()) == null))
    {
     StringTokenizer st = new StringTokenizer(str);
     String gram1=st.nextToken();
     String gram2=st.nextToken();
     String prob =st.nextToken();
     if(!gram1.equals("*") && !gram2.equals("*"))
     {
       double biprob = Double.parseDouble(prob);
       int pos1 = tg.getTag(gram1);
       int pos2 = tg.getTag(gram2);
       probs[pos1][pos2] = biprob;
     }
    }

  }
}
