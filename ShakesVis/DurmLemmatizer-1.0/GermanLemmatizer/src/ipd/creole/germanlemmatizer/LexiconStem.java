package ipd.creole.germanlemmatizer;

import java.util.*;

import gate.*;
import gate.creole.*;
import gate.util.*;
import gate.event.*;
import gate.gui.MainFrame;
import clac.CLaCUtils;


public class LexiconStem
{
   HashMap onestem;
   HashMap morestems;
   
   public LexiconStem(String filepath) 
   {
    try
	{
         LoadLexicon ld = new LoadLexicon();
	 ld.loadHashMap(filepath);
	 onestem = ld.getOneStem();
	 morestems = ld.getMoreStemms();
        }catch(java.io.IOException e){}
   }	 
  
  
   public String lemmatize(String word)
   {
      if(onestem.containsKey(word))
	    return onestem.get(word).toString();
     else
	    return null;
   }
  
}
