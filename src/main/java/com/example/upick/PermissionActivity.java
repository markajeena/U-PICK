package com.example.upick;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.*;
import com.karumi.dexter.listener.single.PermissionListener;

public class PermissionActivity extends AppCompatActivity {

    private Button btnGrant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        if(ContextCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
        {
            startActivity(new Intent(PermissionActivity.this, HomeScreen.class));
            finish();
            return;
        }
        btnGrant=findViewById(R.id.btn_grant);

        btnGrant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Dexter.withActivity(PermissionActivity.this)
                        .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                        .withListener(new PermissionListener()
                        {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse)
                            {
                                startActivity(new Intent(PermissionActivity.this, HomeScreen.class));
                                finish();
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse)
                            {
                                if(permissionDeniedResponse.isPermanentlyDenied())
                                {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(PermissionActivity.this);
                                    builder.setTitle("Permission Denied, Sit Down")
                                            .setMessage("Permission to access location is permanently denied, Please change in your settings :)")
                                            .setNegativeButton("Cancel",null)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which)
                                                {
                                                    Intent intent = new Intent();
                                                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                    intent.setData(Uri.fromParts("package", getPackageName(), null));

                                                }
                                            })
                                            .show();
                                }
                                else
                                {
                                    Toast.makeText(PermissionActivity.this, "Permission Denied, Sit Down", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken)
                            {
                                permissionToken.continuePermissionRequest();
                            }
                        })
                        .check();
            }
        });
    }
}
