//
// $Id: NumberTagger.java,v 1.4 2006/02/16 17:33:46 perera Exp $
//
// Durm German Lemmatization System
// http://www.ipd.uka.de/~durm/tm/lemma/
//
//
// Interface to the POS-based number tagger. Defines the method
// getNumber() that analyzes a sentence and returns the number of the subject.
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

package ipd.creole.number;

import java.util.*;


public interface NumberTagger
{
   public String getNumber(ArrayList tokens,gate.Document document);
} 