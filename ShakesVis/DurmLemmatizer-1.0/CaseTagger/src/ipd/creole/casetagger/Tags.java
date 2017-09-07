//
// $Id: Tags.java,v 1.2 2006/02/16 17:24:58 perera Exp $
//
// Durm German Lemmatization System
// http://www.ipd.uka.de/~durm/tm/lemma/
//
//
// Defines the Tags used for case tagging and getTag() method
// to get the Tag number given the POS and Tag given the Tag number
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

public class Tags {

  String[] tags ={"Nom","Akk","Dat","Gen","APPR","APPRART","APPO","KOUI","KOKOM","KON","KOUS",
                 "PDS","PIS","PPOSS","PRELAT","PRELS","PTKZU","PWAV","PWS","VAFIN","VAINF","VAPP",
                 "VMFIN","VMINF","VMPP","VVFIN","VVIMP","VVINF","VVIZU","VVPP","$,","$."};
     
    

  ArrayList mytags;

  public Tags()
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
