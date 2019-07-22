package com.example.path;

import android.app.Application;

import com.example.path.dagger.APIClientModule;
import com.example.path.dagger.DaggerPathApplicationComponent;
import com.example.path.dagger.DataModule;
import com.example.path.dagger.MainActivityComponent;
import com.example.path.dagger.PathApplicationComponent;

public class PathApplication extends Application {
    private static PathApplicationComponent pathApplicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        pathApplicationComponent = DaggerPathApplicationComponent
                .builder()
                .aPIClientModule(new APIClientModule())
                .dataModule(new DataModule(this))
                .build();

    }

    public static PathApplicationComponent getPathApplicationComponent() {
        return pathApplicationComponent;
    }
}
