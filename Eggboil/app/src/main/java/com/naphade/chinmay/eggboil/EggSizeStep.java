package com.naphade.chinmay.eggboil;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.fcannizzaro.materialstepper.AbstractStep;

import javax.inject.Inject;


/**
 * Created by chinmaynaphade on 23/04/16.
 */
public class EggSizeStep extends AbstractStep implements View.OnClickListener {
    boolean sizeSelected = false;
    ImageView small, medium, large, xLarge;
    @Inject
    BoilTimeCalculator calculator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ((App) getActivity().getApplication()).getAppComponent().inject(this);

        View v = inflater.inflate(R.layout.fragment_egg_size, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        small = (ImageView) v.findViewById(R.id.small);
        medium = (ImageView) v.findViewById(R.id.medium);
        large = (ImageView) v.findViewById(R.id.large);
        xLarge = (ImageView) v.findViewById(R.id.xlarge);
        small.setOnClickListener(this);
        medium.setOnClickListener(this);
        large.setOnClickListener(this);
        xLarge.setOnClickListener(this);

        if (calculator.getSize() != null) {
            switch (calculator.getSize()) {
                case Small:
                    small.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.selection));
                    break;
                case Medium:
                    medium.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.selection));
                    break;
                case Large:
                    large.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.selection));
                    break;
                case ExtraLarge:
                    xLarge.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.selection));
                    break;
            }
        }
    }


    // step name
    @Override
    public String name() {
        return "Egg size";
    }

    // step optional title (default: "Optional")
    @Override
    public String optional() {
        return "Optional subtitle";
    }


    // override only if step is limited by condition
    @Override
    public boolean nextIf() {
        return sizeSelected;
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
            case R.id.small:
                calculator.setSize(EggSize.Small);
                break;
            case R.id.medium:
                calculator.setSize(EggSize.Medium);
                break;
            case R.id.large:
                calculator.setSize(EggSize.Large);
                break;
            case R.id.xlarge:
                calculator.setSize(EggSize.ExtraLarge);
                break;
        }
        sizeSelected = true;
    }

    private void clearSelection() {
        small.setBackground(null);
        medium.setBackground(null);
        large.setBackground(null);
        xLarge.setBackground(null);
    }

}
