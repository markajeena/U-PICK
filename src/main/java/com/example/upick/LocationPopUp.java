package com.example.upick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class LocationPopUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_pop_up);

        DisplayMetrics dm = new DisplayMetrics(); //Getting the size of the pop-up window right
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels; // width for the pop-up
        int height = dm.heightPixels; // height for the pop-up

        getWindow().setLayout((int)(width*.6),(int)(height*.3));

        configureAcceptButton();//to configure accept button
        configureDeclineButton();//to configure decline button
    }

    private void configureAcceptButton(){ //sets button to go back to homescreen
        Button acceptButton = (Button)findViewById(R.id.accept);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LocationPopUp.this, GoToRestaurant.class));
                finish();
            }
        });
    }

    private void configureDeclineButton(){ //sets button to go back to homescreen
        Button declineButton = (Button)findViewById(R.id.decline);
        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
