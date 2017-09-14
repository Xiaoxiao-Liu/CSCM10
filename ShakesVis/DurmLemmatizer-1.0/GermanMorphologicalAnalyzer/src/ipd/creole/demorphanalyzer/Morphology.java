//
// $Id: Morphology.java,v 1.2 2006/02/20 10:54:10 perera Exp $
//
// Durm German Lemmatization System
// http://www.ipd.uka.de/~durm/tm/lemma/
//
//
// Abstraction interface for the German Morphological Analyzer, which maintains a reference to an object
// of type implementor (MorphologyImpl).
// Defines the method classifyMorphology(), which is implemented in the sub classes of the implementor interface.
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

public abstract class Morphology
{
   
   MorphologyImpl morphImpl;

   public abstract MorphologyImpl classifyMorphology(Annotation token, gate.Document doc);
   
   public abstract MorphologyImpl getImplementation();

   public abstract void setImplementation(MorphologyImpl impl);
   
}

