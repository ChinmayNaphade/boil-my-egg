package com.naphade.chinmay.eggboil;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.github.fcannizzaro.materialstepper.style.TabStepper;

/**
 * Created by chinmaynaphade on 23/04/16.
 */
public class EggTabStepper extends TabStepper {
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        setErrorTimeout(1500);

        // --- only TabStepper ---

        // set linear stepper or not
        setLinear(true);

        // show a "previous" button in tab navigation
        showPreviousButton();

        // disable touch event on tab
        disabledTouch();

        // alternative tab style (see screenshot)
        //setAlternativeTab(false);

        setTitle(getResources().getString(R.string.app_name));

        /*LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        location.getAltitude();*/
        addStep(new EggSizeStep());
        addStep(new EggTempStep());
        addStep(new EggTypeStep());

        super.onCreate(savedInstanceState);
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            askForPermission();
        }
    }

    private void askForPermission() {
        Toast.makeText(EggTabStepper.this, "Location is used for calculating the current altitude", Toast.LENGTH_SHORT).show();
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
    }

    // called when 'complete' button is pressed
    @Override
    public void onComplete() {
        startActivity(new Intent(this, TimerActivity.class));
    }
}
