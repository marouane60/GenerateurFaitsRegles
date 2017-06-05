package com.example.marouane.chajarati;

import org.simpleframework.xml.*;

/**
 * Created by Marouane on 6/4/2017.
 */
public class Fait {


    @Element
    public String valeur;


    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Fait)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Fait c = (Fait) o;

        // Compare the data members and return accordingly
        return c.valeur.equals(this.valeur);
    }


}
