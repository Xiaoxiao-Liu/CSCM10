//
// $Id: MorphLemmatizer.java,v 1.5 2006/03/07 17:49:00 perera Exp $
//
// Durm German Lemmatization System
// http://www.ipd.uka.de/~durm/tm/lemma/
//
//
// Implementation of the Lemmatizer. Implements the interface Lemmatizer
// Produces the lemma for nouns looking up the durm-lexicon and using the simple
// lemmatization algorithm based on morphological features number, gender, and case. 
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


public class MorphLemmatizer implements Lemmatizer
{

   String noun;
   String number;
   String gender;
   String gcase;
   String lemma;
   String lemmacandidates;
   
   private StringBuffer sb  = new StringBuffer();
   private StringBuffer dsb = new StringBuffer();
   private int substCount = 0;
   UmlautProcessing umprocess = new UmlautProcessing();
   
   Lexicon lexicon;
   
   public MorphLemmatizer(String filepath)
   {
     try
     {
     System.out.println(filepath);
     lexicon = new LexiconImpl(filepath);
     }catch(java.io.IOException io)
     {
        System.out.println(io.toString());
        System.out.println("Please enter the correct file containing the German lexicon");
     }
   }
   
   
   private void addWord()
   {
    
    if(!lemma.equals("NOT-FOUND"))
    {
      lexicon.addWord(noun,lemmacandidates,gender,number,gcase);
    }
   }
   
   public void updateLexicon()
   {
    try
    {
    lexicon.updateLexicon();
    }catch(java.io.IOException e)
    {}
   }
   
   public String getLemma(String word, String number, String gender, String gcase)
   {
           
    getMorphLemma(word,number,gender,gcase);
    addWord();
    if(lexicon.getLemma(word)!=null)
    {
      return lexicon.getLemma(word);
    }
           else
    {
               if(!lemma.equals("NOT-FOUND"))
          return lemma;
        else
         return "NO_LEMMA";
           }
   }
   
   private void getMorphLemma(String word, String number, String gender, String gcase)
   {
     
           this.noun   = word;
    this.number = number;
    this.gender = gender;
    this.gcase  = gcase;
    
    sb.delete(0,sb.length());
    dsb.delete(0,dsb.length());
    sb.insert( 0, noun );
    dsb.insert(0, noun);
    
    if(number.equals("Pl"))
    {
      if(gcase.equals("Dat"))
               stemmDP(sb,dsb);
      else
        stemmNPAPGP(sb,dsb);
     
   this.lemma = sb.toString();
   this.lemmacandidates = dsb.toString();
    }
    else if(number.equals("Sg"))
    {
       if(gcase.equals("Gen"))
    {
      if( gender.equals("Masc") || gender.equals("Neut") || gender.equals("Masc.Neut"))
     stemmGS(sb,dsb);
   else
    stemmSG(sb,dsb);
    }
    else 
      stemmSG(sb,dsb);
    
    
    this.lemma = sb.toString();
    this.lemmacandidates = dsb.toString();
    }
    else
       this.lemma ="NOT-FOUND";
   
   }
   
   
   private void getLexiconLemma()
   {
   }
   
   
   private void stemmSG(StringBuffer buffer, StringBuffer dicbuffer)
   {
     if(  buffer.length() > 1 )
  {
      if(buffer.substring( buffer.length() - 2, buffer.length() ).equals("en"))
   {
      dicBufferUpdate( buffer, dicbuffer, 2);
   }
   else if(buffer.substring( buffer.length() - 2, buffer.length() ).equals("er"))
   {
      dicBufferUpdate( buffer, dicbuffer, 2);
   }
   else if(buffer.charAt( buffer.length() - 1 ) == 's')
   {
      dicBufferUpdate(buffer, dicbuffer,1);
   }
   
  }
   }
   
   private void stemmGS(StringBuffer buffer, StringBuffer dicbuffer)
   {
        
      
    if(  buffer.length() > 1)
       {
         if(buffer.charAt( buffer.length() - 1 ) == 's')
   {
      buffer.deleteCharAt(buffer.length() -1);
     
         if(buffer.charAt( buffer.length() - 1 ) == 'e')  
      {
          buffer.deleteCharAt(buffer.length() -1); 
       deleteDicBuffer(buffer,dicbuffer);
      }   
      else if(buffer.charAt(buffer.length() - 1) == 'a' ||  buffer.charAt(buffer.length() - 1) == 'i' || buffer.charAt(buffer.length() - 1) == 'o' || buffer.charAt(buffer.length() - 1) == 'u')
      {
        addCurrentDicBuffer(buffer, dicbuffer);
      }
      else
      {
     deleteDicBuffer(buffer, dicbuffer);
      }
            }
  
           
   else if( buffer.substring(buffer.length() - 2, buffer.length()).equals("en"))
               {   
          buffer.delete( buffer.length() -2, buffer.length());
       addCurrentDicBuffer(buffer, dicbuffer);
      }
   
       }
   
   
   
      
   }
  

   private void stemmNPAPGP(StringBuffer buffer, StringBuffer dicbuffer)
   {
        if ( buffer.length() > 2 )
  {
     int umnumber = umprocess.countUmlauts(buffer.toString());
     
     if( buffer.charAt( buffer.length()-1) == 'e')
     {
       buffer.deleteCharAt(buffer.length() -1);
    if(umnumber > 0)
    deleteDicBufferWithUmlauts(buffer, dicbuffer);
    else
    deleteDicBuffer(buffer, dicbuffer);
     }
     
     else if( buffer.charAt( buffer.length()-1) == 's')
     {
       buffer.deleteCharAt(buffer.length() -1);
    if(umnumber > 0)
    deleteDicBufferWithUmlauts(buffer, dicbuffer);
    else
    deleteDicBuffer(buffer, dicbuffer);
     }
     
     
     else if( buffer.substring(buffer.length() - 2, buffer.length()).equals("en"))  
     {
       dicbuffer.delete(0,dicbuffer.length());
    if(umnumber > 0)
    dicBufferUpdateWithUmlauts(buffer, dicbuffer,2);
    else
    dicBufferUpdate(buffer, dicbuffer,2);
    buffer.delete( buffer.length() -2, buffer.length());
     }
     
     else if( buffer.charAt( buffer.length()-1) == 'n')
     {
       buffer.deleteCharAt(buffer.length() -1);
    if(umnumber > 0)
    deleteDicBufferWithUmlauts(buffer, dicbuffer);
    else
    deleteDicBuffer(buffer, dicbuffer);
     }
     
     else if( buffer.substring(buffer.length() - 2, buffer.length()).equals("er"))
     {
               if(umnumber > 0)
      {
      deleteDicBufferWithUmlauts(buffer, dicbuffer);
      dicBufferUpdateWithUmlauts(buffer, dicbuffer,2);
      }
      else
      dicBufferUpdate(buffer, dicbuffer,2);
  
      buffer.delete( buffer.length() -2, buffer.length());        
     }
   
           
     }
   
   }
   
   
   
   private void stemmDP(StringBuffer buffer, StringBuffer dicbuffer)
   {
       if ( buffer.length() > 3 )
  {
     int umnumber = umprocess.countUmlauts(buffer.toString());
     
     if( buffer.substring(buffer.length() - 2, buffer.length()).equals("en"))  
     {
         
      dicbuffer.delete(0, dicbuffer.length());
      if(umnumber > 0)
      dicBufferUpdateWithUmlauts(buffer, dicbuffer, 2);
      else
      dicBufferUpdate(buffer, dicbuffer, 2);
      buffer.delete( buffer.length() -2, buffer.length());
      
      
     }
     
     else if( buffer.substring(buffer.length() - 3, buffer.length()).equals("ern"))
           {     
        dicbuffer.delete(0, dicbuffer.length());
     if(umnumber > 0)
     dicBufferUpdateWithUmlauts(buffer, dicbuffer, 3);
     else
     dicBufferUpdate(buffer, dicbuffer, 3);
     buffer.delete( buffer.length() -3, buffer.length());
    
    
        }

           else  if( buffer.charAt( buffer.length()-1) == 'n')
     {
       buffer.deleteCharAt(buffer.length() -1);
    if(umnumber > 0)
    deleteDicBufferWithUmlauts(buffer, dicbuffer);
    else
    deleteDicBuffer(buffer, dicbuffer);
     }
    
     else if(buffer.charAt(buffer.length()-1) == 's')
     {
       buffer.deleteCharAt(buffer.length() -1);
    if(umnumber > 0)
    deleteDicBufferWithUmlauts(buffer, dicbuffer);
    else
    deleteDicBuffer(buffer, dicbuffer);
     }
     
            
   
           
     }
   
    }
 
 private void dicBufferUpdate(StringBuffer buffer, StringBuffer dicbuffer, int number)
 {
    
    for(int i=1;i < number+1;i++)
    {
     String newbuf = buffer.substring(0,buffer.length() -i);
  if(dicbuffer.length() > 0)
          dicbuffer.append('.'); 
   dicbuffer.append(newbuf);
    }
 }
 
 private void dicBufferUpdateWithUmlauts(StringBuffer buffer, StringBuffer dicbuffer, int number)
 {
    
    for(int i=1;i < number+1;i++)
    {
     String newbuf = buffer.substring(0,buffer.length() -i);
  int umlauts = umprocess.countUmlauts(newbuf);
  String umstrings[] = umprocess.replaceUmlaut(newbuf,umlauts);
  if(dicbuffer.length() > 0)
    dicbuffer.append('.');
  dicbuffer.append(newbuf);
  for(int j=0;j<umstrings.length;j++)
  {

        dicbuffer.append('.'); 
   dicbuffer.append(umstrings[j]);
  }
    }
 }
 
 private void deleteDicBuffer(StringBuffer buffer, StringBuffer dicbuffer)
 {
   dicbuffer.delete(0, dicbuffer.length());
   dicbuffer.append(buffer);
 }
 
 
 private void deleteDicBufferWithUmlauts(StringBuffer buffer, StringBuffer dicbuffer)
 {
   dicbuffer.delete(0, dicbuffer.length());
   String string = buffer.toString();
   int umlauts = umprocess.countUmlauts(string);
   String umstrings[] = umprocess.replaceUmlaut(string,umlauts);
   dicbuffer.append(buffer);
   for(int i=0;i<umstrings.length;i++)
   {
    
    dicbuffer.append('.');
    dicbuffer.append(umstrings[i]);
   }
   
 }
 
 private void addCurrentDicBuffer(StringBuffer buffer, StringBuffer dicbuffer)
 {
   dicbuffer.append('.');
   dicbuffer.append(buffer);
 }

   

}
