package com.gosemathraj.assignment.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.gosemathraj.assignment.data.UserDao;
import com.gosemathraj.assignment.data.UserDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by iamsparsh on 14/9/18.
 */

@Module
public class AppModule {
    Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }
}
