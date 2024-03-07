package com.example.geolocalitzacio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;

public class MainActivity extends AppCompatActivity {

    public final static String[] PERMISSIONS = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermissions();


    }

    private void checkPermissions() {
        if(Build.VERSION.SDK_INT > 23) {
            if (checkPermissions(PERMISSIONS)) {
                start();
            } else {
                requestPermissions(PERMISSIONS);
            }
        } else {
            start();
        }
    }


    private boolean checkPermissions (String [] permissions) {
        boolean checked = true;
        int i = 0;

        while(i < permissions.length && checked) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                checked = false;
            }

            i++;
        }

        return checked;
    }

    private void requestPermissions(String [] permissions) {
        ActivityCompat.requestPermissions(this, permissions, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        boolean checked = true;
        int i = 0;

        while(i < permissions.length && checked) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                checked = false;
            }

            i++;
        }

        if(checked) {
            start();
        } else {
            Toast.makeText(this, "Permissions were not granted", Toast.LENGTH_LONG).show();
        }
    }

    private void start() {


        Toast.makeText(this, "Permissions granted", Toast.LENGTH_LONG).show();
        Intent intent = new Intent (this, MapActivity.class);
        startActivity(intent);
    }


}