package com.example.upick;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class GetNearbyPlaces extends AsyncTask<Object, String, String> {

    GoogleMap mMap;
    String url;
    InputStream is;
    BufferedReader bufferedReader;
    StringBuilder stringBuilder;
    String data;
    Context context;

    public GetNearbyPlaces(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Object... params) {
        mMap = (GoogleMap) params[0];
        url = (String) params[1];

        try {
            URL myurl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) myurl.openConnection();
            httpURLConnection.connect();
            is = httpURLConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(is));

            String line = "";
            stringBuilder = new StringBuilder();

            while((line = bufferedReader.readLine())!= null){
                stringBuilder.append(line);
            }

            data = stringBuilder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e){
            e.printStackTrace();
        }


        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        try {
            JSONObject parentObject = new JSONObject(s);
            JSONArray resultsArray = parentObject.getJSONArray("results");

            int rand = (int)(Math.random()*resultsArray.length());

            for(int i = 0; i < resultsArray.length(); i++){
                GoToRestaurant goToRestaurant = new GoToRestaurant();
                JSONObject jsonObject = resultsArray.getJSONObject(i);
                JSONObject locationObj = jsonObject.getJSONObject("geometry").getJSONObject("location");

                String latitude = locationObj.getString("lat");
                String longitude = locationObj.getString("lng");

                JSONObject nameObject = resultsArray.getJSONObject(i);

                String name_restaurant = nameObject.getString("name");
                String vicinity = nameObject.getString("vicinity");

                LatLng latLng = new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude));


                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(name_restaurant)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                        );

                    if(i != rand)
                {
                    marker.remove();
                }

                    else if(i==rand)
                    {
                        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng,15);
                        mMap.animateCamera(update);
                    }




            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}