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




    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Regle)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Regle r = (Regle) o;

        // Compare the data members and return accordingly
        return r.sousbut.equals(this.sousbut)
                && r.premisses.containsAll(this.premisses)&& this.premisses.containsAll(r.premisses);
    }


}
