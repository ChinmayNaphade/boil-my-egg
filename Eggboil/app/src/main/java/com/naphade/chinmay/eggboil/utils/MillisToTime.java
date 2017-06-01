package com.naphade.chinmay.eggboil.utils;

import java.util.concurrent.TimeUnit;

/**
 * Created by chinmaynaphade on 24/04/16.
 */
public class MillisToTime {
    public static String getTime(long millis) {
        long min = TimeUnit.MILLISECONDS.toMinutes(millis);
        long sec = TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(min);
        return String.format("%02d:%02d", min, sec);
    }
}
