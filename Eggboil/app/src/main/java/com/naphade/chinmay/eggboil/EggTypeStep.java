package com.naphade.chinmay.eggboil;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.fcannizzaro.materialstepper.AbstractStep;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chinmaynaphade on 23/04/16.
 */
public class EggTypeStep extends AbstractStep implements View.OnClickListener {

    @Inject
    BoilTimeCalculator calculator;

    @Bind(R.id.soft)
    LinearLayout soft;

    @Bind(R.id.medium)
    LinearLayout medium;

    @Bind(R.id.hard)
    LinearLayout hard;

    boolean selectedType = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((App) getActivity().getApplication()).getAppComponent().inject(this);
        View v = inflater.inflate(R.layout.fragment_egg_type, container, false);
        ButterKnife.bind(this, v);
        init();
        return v;
    }

    private void init() {
        soft.setOnClickListener(this);
        medium.setOnClickListener(this);
        hard.setOnClickListener(this);

        if (calculator.getType() != null) {
            switch (calculator.getType()) {
                case Soft:
                    soft.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                    break;
                case Medium:
                    medium.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                    break;
                case Hard:
                    hard.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                    break;
            }
        }


    }

    // step name
    @Override
    public String name() {
        return "Boil Type";
    }

    // step optional title (default: "Optional")
    @Override
    public String optional() {
        return "Optional subtitle";
    }

    // override only if step is limited by condition
    @Override
    public boolean nextIf() {
        return selectedType;
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
        v.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        switch (v.getId()) {
            case R.id.soft:
                calculator.setType(EggType.Soft);
                break;
            case R.id.medium:
                calculator.setType(EggType.Medium);
                break;
            case R.id.hard:
                calculator.setType(EggType.Hard);
                break;
        }
        selectedType = true;
    }

    private void clearSelection() {
        soft.setBackgroundColor(Color.TRANSPARENT);
        medium.setBackgroundColor(Color.TRANSPARENT);
        hard.setBackgroundColor(Color.TRANSPARENT);
    }
}
