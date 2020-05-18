package com.example.upick;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoToRestaurantViaLocation extends FragmentActivity implements
        OnMapReadyCallback

{

    private GoogleMap mMap;
    LocationRequest request;
    GoogleApiClient googleApiClient;
    LatLng latlngCurrent;
    Marker urhere;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_restaurant_via_location);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    public void findRestaurants(View v) {
        mMap.clear();

        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latlngCurrent, 15);
        mMap.animateCamera(update);

        urhere = mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .position(latlngCurrent)
                .title("You Are Here")
        );

        StringBuilder stringBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        stringBuilder.append("location=" + latlngCurrent.latitude + "," + latlngCurrent.longitude);
        stringBuilder.append("&radius=" + 1000);
        stringBuilder.append("&keyword=" + "restaurants");
        stringBuilder.append("&key=" + "AIzaSyDHGi2yUfBsZZrOvsLaYSuUYUIdf0qOKCU");

        String url = stringBuilder.toString();

        Object dataTransfer[] = new Object[2];
        dataTransfer[0] = mMap;
        dataTransfer[1] = url;

        GetNearbyPlaces getNearbyPlaces = new GetNearbyPlaces(this);
        getNearbyPlaces.execute(dataTransfer);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .build();

        googleApiClient.connect();

    }


}