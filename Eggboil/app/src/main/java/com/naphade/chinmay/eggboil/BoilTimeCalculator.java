package com.naphade.chinmay.eggboil;

import com.naphade.chinmay.eggboil.enums.EggSize;
import com.naphade.chinmay.eggboil.enums.EggTemp;
import com.naphade.chinmay.eggboil.enums.EggType;

import java.util.concurrent.TimeUnit;

/**
 * Created by chinmaynaphade on 24/04/16.
 */
public class BoilTimeCalculator {

    double sizeWeight;
    double temperature;
    double typeOfBoil;
    double bpOfWater = 100;

    EggSize size;
    EggTemp temp;
    EggType type;

    public EggSize getSize() {
        return size;
    }

    public void setSize(EggSize size) {
        this.size = size;
        switch (size) {
            case Small:
                sizeWeight = 45;
                break;
            case Medium:
                sizeWeight = 58;
                break;
            case Large:
                sizeWeight = 68;
                break;
            case ExtraLarge:
                sizeWeight = 78;
                break;
        }
    }

    public EggType getType() {
        return type;
    }

    public void setType(EggType type) {
        this.type = type;
        switch (type) {
            case Soft:
                typeOfBoil = 60;
                break;
            case Medium:
                typeOfBoil = 70;
                break;
            case Hard:
                typeOfBoil = 80;
        }
    }

    public void setTemp(EggTemp temp) {
        this.temp = temp;
        switch (temp) {
            case Fridge:
                temperature = 3;
                break;
            case Room:
                temperature = 25;
                break;
        }
    }

    public void setBpOfWater(double altitude) {
        this.bpOfWater = 100 - 0.0035 * altitude;
    }


    private float calculate() {
        double a = 0.451 * Math.pow(sizeWeight, 0.66);
        double b = ((temperature - bpOfWater) / (typeOfBoil - bpOfWater)) * 0.76;
        float time = (float) (a * Math.log(b));
        return time;

    }

    public long getMillis() {
        float min = calculate();
        long m = (long) min;
        long s = (long) (((min - m) * 100) * 1.66);
        return TimeUnit.MINUTES.toMillis(m) + TimeUnit.SECONDS.toMillis(s);
    }
}
