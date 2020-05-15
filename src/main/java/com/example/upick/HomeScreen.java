package com.example.upick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.security.Permissions;

public class HomeScreen extends AppCompatActivity {

    ImageButton optionsButtonClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        configurePickButton();
        configureGetLocationButton();

        optionsButtonClick = (ImageButton) findViewById(R.id.options);


        if(ContextCompat.checkSelfPermission(HomeScreen.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) { //checking for location permission
            Intent intentPopUpScreen = new Intent(HomeScreen.this, LocationPopUp.class);
            startActivity(intentPopUpScreen);
        }

        optionsButtonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadOptionsScreen = new Intent(HomeScreen.this, OptionsMenu.class);
                startActivity(intentLoadOptionsScreen);
            }
        });

    }

    private void configureGetLocationButton() {
        Button getLocationButton = (Button) findViewById(R.id.setlocation);
        getLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, LocationPage.class));
            }
        });
    }
        private void configurePickButton(){
        Button nextButton = (Button)findViewById(R.id.randompick);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this,GoToRestaurant.class));
            }
        });
    }

}
