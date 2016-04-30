package com.naphade.chinmay.eggboil;

import android.content.Intent;
import android.os.Bundle;

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
    }

    // called when 'complete' button is pressed
    @Override
    public void onComplete() {
        startActivity(new Intent(this, TimerActivity.class));
    }
}
