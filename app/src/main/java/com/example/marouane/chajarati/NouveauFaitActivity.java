package com.example.marouane.chajarati;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Marouane on 6/5/2017.
 */

public class NouveauFaitActivity extends AppCompatActivity {




    ArrayList<Fait> StockFaits = new ArrayList<Fait>();
    ArrayList<Regle> BaseRegles = new ArrayList<Regle>();
    ArrayList<Fait> BaseFaits = new ArrayList<Fait>();
    ArrayList<Fait> Buts = new ArrayList<Fait>();
    ArrayList<Regle> StockRegles = new ArrayList<Regle>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveaufait);


        final Button buttonadd = (Button)findViewById( R.id.button2 );
        final EditText FaitEdit = (EditText) findViewById( R.id.editText);
        final Button buttonconfirm = (Button)findViewById( R.id.button3 );

        buttonadd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Fait f = new Fait();
                f.valeur = FaitEdit.getText().toString();
                StockFaits.add(f);


            }
        });

        buttonconfirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click


                LesFaits L = new LesFaits();

                File xmlFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getPath() + "/Regle.xml");

                try
                {



                    L.Fait = StockFaits;
                    Serializer serializer = new Persister();
                    serializer.write(L, xmlFile);




                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Log.d("myTag","ca marche pas");

                }




            }
        });


    }




}
