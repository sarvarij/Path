package com.example.path.dagger;

import com.example.path.geo_jawg.AutocompleteAPI;
import com.example.path.geo_tomtom.RoutingAPI;
import com.example.path.db.EndpointDAO;
import com.example.path.viewmodel.MainActivityVM;
import com.example.path.viewmodel.MainActivityVMFactory;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {APIClientModule.class, DataModule.class, VMFactoryModule.class})
public interface PathApplicationComponent {


    MainActivityVMFactory mainActivityVmFactory();
    AutocompleteAPI autocompleteApi();
    RoutingAPI ttRoutingInterface();
    EndpointDAO endpointDao();
    //void inject(MainActivity activity);
}
