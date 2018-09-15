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
 * Created by iamsparsh on 15/9/18.
 */
@Module
public class DataModule {
    @Singleton
    @Provides
    public UserDatabase provideMyDatabase(Application context){
        return Room.databaseBuilder(context, UserDatabase.class, "user-db").allowMainThreadQueries().build();
    }

    @Singleton @Provides
    public UserDao provideUserDao(UserDatabase myDatabase){
        return myDatabase.userDao();
    }
}
