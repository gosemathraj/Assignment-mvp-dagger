package com.gosemathraj.assignment.di.component;

import com.gosemathraj.assignment.di.module.HomeModule;
import com.gosemathraj.assignment.utility.CustomScope;
import com.gosemathraj.assignment.view.HomeActivity1;

import dagger.Component;

/**
 * Created by iamsparsh on 15/9/18.
 */
@CustomScope
@Component(dependencies = NetComponent.class, modules = HomeModule.class)
public interface HomeComponent {
    void inject(HomeActivity1 activity);
}