package com.naphade.chinmay.eggboil;

import android.app.Application;

/**
 * Created by chinmaynaphade on 24/04/16.
 */
public class App extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
