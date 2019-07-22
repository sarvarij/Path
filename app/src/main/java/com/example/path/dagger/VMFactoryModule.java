package com.example.path.dagger;

import com.example.path.viewmodel.MainActivityVMFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class VMFactoryModule {

    @Provides
    @Singleton
    MainActivityVMFactory provideMainActivityVMFactory(){
        return new MainActivityVMFactory();
    }

}
