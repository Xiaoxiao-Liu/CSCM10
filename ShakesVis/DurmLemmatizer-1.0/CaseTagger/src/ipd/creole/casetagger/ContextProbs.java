//
// $Id: ContextProbs.java,v 1.6 2006/02/16 17:24:58 perera Exp $
//
// Durm German Lemmatization System
// http://www.ipd.uka.de/~durm/tm/lemma/
//
//
// Produces context probabilities to the case tagger
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

public class ContextProbs
{
    ArrayList maps;
    String filepath;
    
    

    
    
    public ContextProbs(String path) throws java.io.IOException
    {
      maps = new ArrayList();
      filepath = path;
      initiate();
      loadProbs();
    }

    public double getContextProbability(String context[],int tags[],int num)
    {
      double retval =1.0;
      for(int i=0;i<tags.length;i++)
      {
        if(((HashMap[])maps.get(tags[i]))[num].containsKey(context[i].toLowerCase()))
           retval = retval *((Double)((HashMap[])maps.get(tags[i]))[num].get(context[i].toLowerCase())).doubleValue();
        else
           retval = retval;

      }
        return retval;

    }

    private void initiate()
    {
      HashMap[] hashmaps = new HashMap[4];
      for(int i=0;i<hashmaps.length;i++)
        hashmaps[i] = new HashMap();

      for(int i=0;i<8;i++)
        maps.add(hashmaps);

    }
    //"APPR","APPRART","ADJA","PDAT","PIAT","PIDAT","PPOSAT","ART"

    private void loadProbs() throws java.io.IOException
    {
    
     FileReader fr[] = new FileReader[8];
     fr[0] = new FileReader(filepath+"/apprstat.txt");
     fr[1] = new FileReader(filepath+"/apprartstat.txt");
     fr[2] = new FileReader(filepath+"/adjstat.txt");
     fr[3] = new FileReader(filepath+"/pdatstat.txt");
     fr[4] = new FileReader(filepath+"/piatstat.txt");
     fr[5] = new FileReader(filepath+"/pidatstat.txt");
     fr[6] = new FileReader(filepath+"/pposatstat.txt");
     fr[7] = new FileReader(filepath+"/artstat.txt");

     for(int i=0;i<maps.size();i++)
     {
       BufferedReader br = new BufferedReader(fr[i]);
       String str;

       while( !((str = br.readLine()) == null))
       {
        StringTokenizer st = new StringTokenizer(str);
        String word = st.nextToken();
        String nom  = st.nextToken();
        String akk  = st.nextToken();
        String dat  = st.nextToken();
        String gen  = st.nextToken();

        ((HashMap[])maps.get(i))[0].put(word,new Double(Double.parseDouble(nom)));
        ((HashMap[])maps.get(i))[1].put(word,new Double(Double.parseDouble(akk)));
        ((HashMap[])maps.get(i))[2].put(word,new Double(Double.parseDouble(dat)));
        ((HashMap[])maps.get(i))[3].put(word,new Double(Double.parseDouble(gen)));
       }
       br.close();
       fr[i].close();




     }



    }
}
