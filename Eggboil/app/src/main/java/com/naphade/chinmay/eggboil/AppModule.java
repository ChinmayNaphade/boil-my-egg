package com.naphade.chinmay.eggboil;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chinmaynaphade on 24/04/16.
 */

@Module
public class AppModule {
    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application providesApplication() {
        return application;
    }

    @Provides
    @Singleton
    public BoilTimeCalculator providesCalulator() {
        BoilTimeCalculator calculator = new BoilTimeCalculator();
        return calculator;
    }

}
