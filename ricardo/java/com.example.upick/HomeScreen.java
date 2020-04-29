package com.example.upick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeScreen extends AppCompatActivity {

    ImageButton optionsButtonClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        configurePickButton();
        configureGetLocationButton();

        optionsButtonClick = (ImageButton) findViewById(R.id.options);

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
                startActivity(new Intent(HomeScreen.this,SelectionScreen.class));
            }
        });
    }

}
