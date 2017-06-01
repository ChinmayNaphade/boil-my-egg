package com.naphade.chinmay.eggboil;

import android.view.View;

import com.naphade.chinmay.eggboil.utils.MillisToTime;

import javax.inject.Inject;

/**
 * Created by chinmaynaphade on 13/05/16.
 */
public class TimerPresenter implements Presenter<TimerView> {
    TimerView view;

    long millis;
    boolean timerRunning = false;

    BoilTimeCalculator calculator;

    @Inject
    public TimerPresenter(BoilTimeCalculator calculator) {
        this.calculator = calculator;
        millis = calculator.getMillis();
    }

    @Override
    public void setView(TimerView view) {
        this.view = view;
    }

    public void onButtonClick(View v) {
        view.updateTimerText(MillisToTime.getTime(millis));
        if (timerRunning) {
            view.cancelTimer();
            timerRunning = false;
        } else {
            view.startTimer();
            timerRunning = true;
        }
    }
}
