package com.example.marouane.chajarati;

import org.simpleframework.xml.*;

import java.util.ArrayList;

/**
 * Created by Marouane on 5/31/2017.
 */

@Root
public class Regle {

    @Element
    public Fait sousbut;

    @ElementList
    public ArrayList<Fait> premisses;


}
