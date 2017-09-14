//
// $Id: Viterbi.java,v 1.2 2006/02/16 17:24:58 perera Exp $
//
// Durm German Lemmatization System
// http://www.ipd.uka.de/~durm/tm/lemma/
//
//
// Interface to the Viterbi Algorithm. Defines the method getBestSequence() which
// finds the best sequence of case tags for the nouns in the sentence.
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

public interface Viterbi
{
 
    public int[] getBestSequence(boolean[] flags,int[] pos,String[]tags,int[] type,String[][] context,int[][] ctype, boolean[][] idefcase);

}