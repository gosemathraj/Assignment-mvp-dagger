package com.gosemathraj.assignment;

import android.app.Application;

import com.gosemathraj.assignment.data.UserDao;
import com.gosemathraj.assignment.di.component.NetComponent;
import com.gosemathraj.assignment.di.module.AppModule;
import com.gosemathraj.assignment.di.module.DataModule;
import com.gosemathraj.assignment.utility.CustomScope;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by iamsparsh on 15/9/18.
 */
@CustomScope
@Singleton
@Component(modules = {DataModule.class,AppModule.class})
public interface TestComponent{
    void inject(UserAddInsertUpdateDeleteTest test);

    Application application();

    UserDao userDao();
}
