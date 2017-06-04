package com.example.marouane.chajarati;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.ArrayList;

import static com.example.marouane.chajarati.Fait.*;


public class MainActivity extends AppCompatActivity {

    ArrayList<Regle> BaseRegles = new ArrayList<Regle>();
    ArrayList<Fait> BaseFaits = new ArrayList<Fait>();
    ArrayList<Fait> Buts = new ArrayList<Fait>();
    ArrayList<Regle> StockRegles = new ArrayList<Regle>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("myTag", getFilesDir().getPath());

        // Test Serialisation ( enregistrer sur xml )

        ArrayList<Fait> r = new ArrayList<Fait>();
        r.add(GRANDE);

        Regle regle1 = new Regle();
        regle1.sousbut=ARBRE;
        regle1.premisses=r;

        File xmlFile = new File(getFilesDir().getPath() + "/Regle.xml");


        try
        {
            Serializer serializer = new Persister();
            serializer.write(regle1, xmlFile);
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
                Log.d("myTag",regle2.sousbut.toString());
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
                                if(but == sousbut){

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
