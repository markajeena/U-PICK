package com.example.upick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LocationPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_page);

        configureAddressButton();
        configureLocationSlideButton();
    }

    private void configureAddressButton(){
        Button addressButton = (Button)findViewById(R.id.addressenter);
        addressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void configureLocationSlideButton(){
        Button locationSlideButton = (Button)findViewById(R.id.locationslide);
        locationSlideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}