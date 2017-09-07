//
// $Id: ContextTags.java,v 1.2 2006/02/16 17:24:58 perera Exp $
//
// Durm German Lemmatization System
// http://www.ipd.uka.de/~durm/tm/lemma/
//
//
// Defines context tags and defines methods, setTag (String tag) and getTag(String tag) to
// to retrive tha tag  given the its name and number.
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

import gate.*;
import gate.creole.*;
import gate.util.*;
import gate.event.*;
import gate.gui.MainFrame;


import java.util.*;

public class ContextTags
{

  String[] tags ={"APPR","APPRART","ADJA","PDAT","PIAT","PIDAT","PPOSAT","ART"};

  ArrayList mytags;

  public ContextTags()
  {
     mytags = new ArrayList(Arrays.asList(tags));
  }

  public int getTag(String tag)
  {
    return mytags.indexOf(tag);
  }

  public String getTag(int index)
  {
    return mytags.get(index).toString();
  }

}
