package com.example.iem.tp6_connexionserveurdistant;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



/**
 * Created by iem on 18/10/2016.
 */

class AffichageDateHeureTask extends AsyncTask< Object , Void , String> {
      TextView tx;



    @Override
    protected String doInBackground(Object... params) {

        String urlname = "http://www.timeapi.org/utc/now";
        URL url = null;
        HttpURLConnection urlConnection = null;
        String catchDateTime = null;
        tx = (TextView)params[0];


        try {
            url = new URL(urlname);
            urlConnection = (HttpURLConnection) url.openConnection();


            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                catchDateTime = in.readLine();
                in.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return catchDateTime;
    }

    protected void  onPostExecute (String datetime){

        tx.setText(datetime);

    }
}


