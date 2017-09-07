//
// $Id: Lemmatizer.java,v 1.2 2006/02/16 18:15:02 perera Exp $
//
// Durm German Lemmatization System
// http://www.ipd.uka.de/~durm/tm/lemma/
//
//
// Interface to the lemmatizer. Defines the method
// getLemma() that returns the lemma for a noun 
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


public interface Lemmatizer
{

  public String getLemma(String word, String number, String gender, String gcase);
  public void updateLexicon();
  
}