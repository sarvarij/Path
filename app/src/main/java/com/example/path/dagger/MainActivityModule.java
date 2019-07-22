package com.example.path.dagger;

import com.example.path.MainActivity;
import com.example.path.geo_jawg.AutocompleteAPI;
import com.example.path.geo_jawg.AutocompleteProvider;
import com.example.path.db.EndpointDAO;
import com.example.path.db.EndpointStore;
import com.example.path.view.EndpointSetting;
import com.example.path.view.mapview.AnnotationSetting;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {

        this.mainActivity = mainActivity;
    }


    @Provides
    @MainActivityScope
    public AnnotationSetting provideAnnotationSetting() {
        return new AnnotationSetting();
    }


    @Provides
    @MainActivityScope
    public AutocompleteProvider provideAutocompleteProvider(AutocompleteAPI autocompleteAPI){
        return new AutocompleteProvider(mainActivity, autocompleteAPI);
    }

}
