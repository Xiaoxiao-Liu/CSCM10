package ipd.creole.germanlemmatizer;


import java.util.*;
import java.io.*;

import gate.*;
import gate.creole.*;
import gate.util.*;
import gate.event.*;
import gate.gui.MainFrame;
import clac.CLaCUtils;


public class LoadLexicon
{

        HashMap stemms = new HashMap();
        HashMap manystemms = new HashMap();
	
	

        public void loadHashMap(String filepath)throws java.io.IOException
        {

          FileReader dicReader     = new  FileReader(filepath+"/dwithtime.txt");
          BufferedReader dicBufReader  = new BufferedReader(dicReader);


     String entry;

     while( !((entry = dicBufReader.readLine()) == null))
     {
           StringTokenizer st = new StringTokenizer(entry);
       if(st.countTokens() == 11)
           {
             String word = st.nextToken();
                 String number = st.nextToken();
                 String gender = st.nextToken();
                 String gcase = st.nextToken();
                 String stem = st.nextToken();

                 StringTokenizer stdot = new StringTokenizer(stem,".");
                 if(!(stdot.countTokens() > 1))
                  stemms.put(word,stem);
                 else
                  manystemms.put(word,stem);

           }
     }

   }


   public HashMap getOneStem()
   {
     return stemms;
   }
   public HashMap getMoreStemms()
   {
     return manystemms;
   }


}
