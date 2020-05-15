package com.example.upick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LocationPage extends AppCompatActivity {

    ImageButton locationBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_page);

        configureAddressButton();
        configureLocationSlideButton();

        locationBackButton = (ImageButton)findViewById(R.id.locationBackButton);

        locationBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
                Intent intentPopUpScreen = new Intent(LocationPage.this, LocationPopUp.class);
                startActivity(intentPopUpScreen);
            }
        });
    }

}
