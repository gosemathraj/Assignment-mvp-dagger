package com.gosemathraj.assignment;

import android.app.Application;
import android.content.Context;

import com.gosemathraj.assignment.di.component.DaggerNetComponent;
import com.gosemathraj.assignment.di.component.NetComponent;
import com.gosemathraj.assignment.di.module.AppModule;
import com.gosemathraj.assignment.di.module.DataModule;
import com.gosemathraj.assignment.di.module.NetModule;

/**
 * Created by iamsparsh on 14/9/18.
 */

public class App extends Application {

    private NetComponent mNetComponent;
    private static App appInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://reqres.in/"))
                .dataModule(new DataModule())
                .build();
    }

    public static App getAppInstance() {
        return appInstance;
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
