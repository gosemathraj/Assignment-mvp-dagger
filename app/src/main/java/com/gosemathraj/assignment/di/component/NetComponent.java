package com.gosemathraj.assignment.di.component;

import com.gosemathraj.assignment.data.UserDao;
import com.gosemathraj.assignment.data.UserDatabase;
import com.gosemathraj.assignment.di.module.AppModule;
import com.gosemathraj.assignment.di.module.DataModule;
import com.gosemathraj.assignment.di.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by iamsparsh on 14/9/18.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class, DataModule.class})
public interface NetComponent {
    Retrofit retrofit();

    UserDatabase userDatabase();

    UserDao userDao();
}
