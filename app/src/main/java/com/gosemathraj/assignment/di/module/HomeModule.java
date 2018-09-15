package com.gosemathraj.assignment.di.module;

import com.gosemathraj.assignment.view.BaseView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by iamsparsh on 15/9/18.
 */

@Module
public class HomeModule {
    private final BaseView mView;


    public HomeModule(BaseView mView) {
        this.mView = mView;
    }

    @Provides
    BaseView providesBaseView() {
        return mView;
    }
}
