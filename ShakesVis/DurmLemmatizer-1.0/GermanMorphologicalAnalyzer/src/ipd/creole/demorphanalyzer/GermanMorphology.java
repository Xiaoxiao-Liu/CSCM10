//
// $Id: GermanMorphology.java,v 1.2 2006/02/20 10:54:10 perera Exp $
//
// Durm German Lemmatization System
// http://www.ipd.uka.de/~durm/tm/lemma/
//
//
// Implements the interface defined by the abstract class Morphology
// 
//
// (c) 2005--2006 University of Karlsruhe, IPD,
//                Praharshana Perera
//
//
// This program is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation; either version 2 of the License, or
// (at your option) any later version.
// 

package ipd.creole.demorphanalyzer;
import gate.*;


public class GermanMorphology extends Morphology
{

   public MorphologyImpl classifyMorphology(Annotation token, gate.Document doc)
   {
      
      return morphImpl.classifyMorphology(token,doc);
   }

   public MorphologyImpl getImplementation()
   {
     return morphImpl;
   }

   public void setImplementation(MorphologyImpl impl)
   {
     morphImpl = impl;
   }
 
}
