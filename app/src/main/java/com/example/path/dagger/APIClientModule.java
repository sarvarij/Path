package com.example.path.dagger;


import com.example.path.BuildConfig;
import com.example.path.geo_jawg.AutocompleteAPI;
import com.example.path.geo_tomtom.RoutingAPI;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class APIClientModule {

    @Provides
    RoutingAPI provideRoutingAPI() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.TOMTOM_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(RoutingAPI.class);
    }

    @Provides
    AutocompleteAPI provideAutoCompleteAPI() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.JAWG_BASE_URL)//.replace("io", "ao"))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(AutocompleteAPI.class);
    }

}
