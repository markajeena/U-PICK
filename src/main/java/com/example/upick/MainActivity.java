package com.example.upick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);


        LoadScreen loadScreen = new LoadScreen();
        loadScreen.start();

    }

    private class LoadScreen extends Thread{
        public void run(){
            try{
                sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            Intent intent = new Intent(MainActivity.this,HomeScreen.class);
            startActivity(intent);
            MainActivity.this.finish();
        }
    }
}