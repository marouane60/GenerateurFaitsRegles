package com.example.marouane.chajarati;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import static com.example.marouane.chajarati.Fait.*;


public class MainActivity extends AppCompatActivity {

    ArrayList<Fait> StockFaits = new ArrayList<Fait>();
    ArrayList<Regle> BaseRegles = new ArrayList<Regle>();
    ArrayList<Fait> BaseFaits = new ArrayList<Fait>();
    ArrayList<Fait> Buts = new ArrayList<Fait>();
    ArrayList<Regle> StockRegles = new ArrayList<Regle>();


    public void ActivityNF(View view) {
        // Do something in response to button

        Intent intent = new Intent(this, NouveauFaitActivity.class);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Boutton Nouveau Fait



        // Test Serialisation ( enregistrer sur xml )

        ArrayList<Fait> r = new ArrayList<Fait>();
        Fait f = new Fait();
        f.valeur = "type arbre";
        r.add(f);
        r.add(f);

        LesFaits L = new LesFaits();

        L.Fait = r;

        Regle regle1 = new Regle();
        regle1.sousbut=f;
        regle1.premisses=r;

        File xmlFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getPath() + "/Regle.xml");


        try
        {



            Serializer serializer = new Persister();
            serializer.write(L, xmlFile);




        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.d("myTag","ca marche pas");

        }





        // Test Deserialisation ( lire du fichier xml )


        if (xmlFile.exists())
        {
            try
            {
                Serializer serializer = new Persister();
                Regle regle2 = serializer.read(Regle.class, xmlFile);
                Log.d("myTag",regle2.sousbut.valeur.toString());


            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        // Ajouter les entres utilisateur à l array list FaitsVrais


        // Moteur d'inference :


                while(!Buts.isEmpty()){
                    Fait but = Buts.get(0);
                    Buts.remove(0);
                    if(BaseFaits.contains(but)){

                        System.out.println("succes , plante :" + but);


                    }
                    else
                    {
                        if(!BaseRegles.isEmpty())
                        {
                            Regle regle = BaseRegles.get(0);
                            BaseRegles.remove(0);
                            Fait sousbut = regle.sousbut;
                            ArrayList<Fait> premisses = new ArrayList<Fait>(regle.premisses);

                            if(BaseFaits.containsAll(premisses)){
                                if(but.equals(sousbut)){

                                    System.out.println("succes , plante :"+ sousbut);

                                }
                                else
                                {
                                    BaseFaits.add(sousbut);
                                    BaseRegles.addAll(StockRegles);
                                    StockRegles.clear();

                                }



                            }
                            else
                            {
                                StockRegles.add(regle);
                            }
                        }
                        else
                        {

                            System.out.println("Baseregles vide");

                        }


                    }


                }


    }
}
