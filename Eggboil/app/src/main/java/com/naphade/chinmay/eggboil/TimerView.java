package com.naphade.chinmay.eggboil;

/**
 * Created by chinmaynaphade on 13/05/16.
 */
public interface TimerView {
    void updateTimerText(String text);

    void cancelTimer();

    void startTimer();
}
