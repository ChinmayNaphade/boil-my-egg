package com.naphade.chinmay.eggboil;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.fcannizzaro.materialstepper.AbstractStep;
import com.naphade.chinmay.eggboil.enums.EggTemp;

import javax.inject.Inject;

/**
 * Created by chinmaynaphade on 23/04/16.
 */
public class EggTempStep extends AbstractStep implements View.OnClickListener {

    @Inject
    BoilTimeCalculator calculator;
    boolean tempSelected = false;
    ImageView fridge, room;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((App) getActivity().getApplication()).getAppComponent().inject(this);
        View v = inflater.inflate(R.layout.fragment_egg_temp, container, false);
        fridge = (ImageView) v.findViewById(R.id.fridge);
        room = (ImageView) v.findViewById(R.id.room);
        fridge.setOnClickListener(this);
        room.setOnClickListener(this);
        return v;
    }

    // step name
    @Override
    public String name() {
        return "Egg temperature";
    }

    // step optional title (default: "Optional")
    @Override
    public String optional() {
        return "Optional subtitle";
    }


    // override only if step is limited by condition
    @Override
    public boolean nextIf() {
        return tempSelected;
    }

    // error showed on change next step ( see nextIf )
    @Override
    public String error() {
        return "<b>Condition</b>";
    }

    // do something when step is visible
    @Override
    public void onStepVisible() {
        super.onStepVisible();
    }

    @Override
    public void onClick(View v) {
        clearSelection();
        v.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.selection));
        switch (v.getId()) {
            case R.id.fridge:
                calculator.setTemp(EggTemp.Fridge);
                break;
            case R.id.room:
                calculator.setTemp(EggTemp.Room);
                break;
        }

        tempSelected = true;
    }

    private void clearSelection() {
        fridge.setBackground(null);
        room.setBackground(null);
    }
}
