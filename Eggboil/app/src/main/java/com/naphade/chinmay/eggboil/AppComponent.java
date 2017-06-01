package com.naphade.chinmay.eggboil;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by chinmaynaphade on 24/04/16.
 */


@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(EggTempStep eggTempStep);

    void inject(EggSizeStep eggSizeStep);

    void inject(EggTypeStep eggTypeStep);

    void inject(TimerActivity timerActivity);
}
