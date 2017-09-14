//
// $Id: CaseTaggerInt.java,v 1.2 2006/02/16 17:24:57 perera Exp $
//
// Durm German Lemmatization System
// http://www.ipd.uka.de/~durm/tm/lemma/
//
//
// Interface to the case tagger
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

import java.util.*;

import gate.*;
import gate.creole.*;
import gate.util.*;

public interface CaseTaggerInt
{

   public String[] getCaseTags(gate.Document gatedoc, ArrayList tokens);
  
}