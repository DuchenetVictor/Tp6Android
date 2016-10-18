package com.example.iem.tp6_connexionserveurdistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class ConnexionHttpActivity extends AppCompatActivity {

    Button boutonDateHeure;
    Button boutonTextHTML;
    Button boutonLoadURL;
    Button boutonMeteoLoadData;
    WebView WBV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion_http);

        boutonDateHeure = (Button) findViewById(R.id.btnAffichDateHeure);
        boutonTextHTML = (Button) findViewById(R.id.btnTextHTML);
        boutonLoadURL = (Button) findViewById(R.id.btnMeteoLoadUrl);
        boutonMeteoLoadData = (Button)findViewById(R.id.btnMeteoLoadData);
        WBV = (WebView) findViewById(R.id.WebView);


        final TextView txtviewTime = (TextView) findViewById(R.id.txtvTime);

        boutonDateHeure.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                new AffichageDateHeureTask().execute(txtviewTime);
            }
        });

        boutonTextHTML.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String strHtml = "<html><body><b> Ceci est un texte au format HTML</b></br>qui s’affiche très simplement</body></html>)";
                WBV.loadData(strHtml, "text/html; charset=utf-8", "UTF-8");
            }
        });

        boutonLoadURL.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WBV.loadUrl("http://api.meteorologic.net/forecarss?p=Lyon");
            }
        });

        boutonMeteoLoadData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                new AffichageMeteoData().execute(WBV);

            }
        });



    }
}
