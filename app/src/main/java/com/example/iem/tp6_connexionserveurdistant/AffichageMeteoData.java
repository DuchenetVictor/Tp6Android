package com.example.iem.tp6_connexionserveurdistant;

import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



/**
 * Created by iem on 18/10/2016.
 */

class AffichageMeteoData extends AsyncTask <Object, Void,String>{

     WebView webView;

    @Override
    protected String doInBackground(Object[] params) {
        webView = (WebView)params[0];

        String urlname = "http://api.meteorologic.net/forecarss?p=Lyon";
        URL url = null;
        HttpURLConnection urlConnection = null;
        String line ;
        String meteoData= "";

        try {
            url = new URL(urlname);
            urlConnection = (HttpURLConnection) url.openConnection();

            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                for(int i =0;i<23; i++){
                    in.readLine();
                }
                 while( (line = in.readLine())!= null){
                     meteoData += in.readLine();
                 }
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return meteoData;
    }

    protected void  onPostExecute (String dataMeteo){
        webView.loadData(dataMeteo, "text/html; charset=utf-8", "UTF-8");
    }
}
