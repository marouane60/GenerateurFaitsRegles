package com.example.marouane.chajarati;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by Marouane on 6/5/2017.
 */
@Root
public class LesFaits {

    @ElementList
    public ArrayList<Fait> Fait;

}
