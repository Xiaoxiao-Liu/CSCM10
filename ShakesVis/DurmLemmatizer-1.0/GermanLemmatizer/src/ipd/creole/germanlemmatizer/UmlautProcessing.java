//
// $Id: UmlautProcessing.java,v 1.2 2006/02/16 18:15:02 perera Exp $
//
// Durm German Lemmatization System
// http://www.ipd.uka.de/~durm/tm/lemma/
//
//
// Class for processing umlauts in German. 
// Replaces umlauts with vowels and produces word forms for a given
// word with umlaut/s
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

package ipd.creole.germanlemmatizer;

import java.util.*;

import gate.*;
import gate.creole.*;
import gate.util.*;
import gate.event.*;
import gate.gui.MainFrame;
import clac.CLaCUtils;



public class UmlautProcessing
{



public int countUmlauts(String word)
  {
    int count =0;
 for(int i=0;i<word.length(); i++)
 {
     char c = word.charAt(i);
  if( c=='ä' || c == 'ö' || c=='ü')
  count++;
 }
 
 return count;
  }

public int[] getUmlautPositions(String word,int count)
  {
    int positions[] = new int[count];
 int number = 0;
 for(int i=0;i<word.length(); i++)
 {
     char c = word.charAt(i);
  if( c=='ä' || c == 'ö' || c=='ü')
    positions[number++]=i;
 }
      
    return positions ;
  }
  
  
  public String[] replaceUmlaut(String word, int count)
  {
      
     String umlautStrings[] = new String[count];
  int umlautPositions[]   = getUmlautPositions(word,count); // get the positions of the umlauts
  
  for(int i=0;i<umlautPositions.length;i++)
  {
  
     StringBuffer buffer = new StringBuffer(word);
     char c= buffer.charAt(umlautPositions[i]);
     
     if(c == 'ä')
      buffer.setCharAt(umlautPositions[i],'a');
     else if( c == 'ü')
      buffer.setCharAt(umlautPositions[i],'u');
     else if( c == 'ö')
      buffer.setCharAt(umlautPositions[i],'o');
     else
     {
       //do nothing
     }
     
    umlautStrings[i]=buffer.toString();
    buffer.delete(0, word.length());
  }
  
    return umlautStrings;
  
  
  }



}
