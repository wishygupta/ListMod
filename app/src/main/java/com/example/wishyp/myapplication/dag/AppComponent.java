package com.example.wishyp.myapplication.dag;

import com.example.wishyp.myapplication.ActivityDeliveryList;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by wishy.gupta on 13-03-2018.
 */

@Singleton
@Component(modules = {AppModule.class, AppClient.class})
public interface AppComponent {

    void inject(ActivityDeliveryList activity);



}