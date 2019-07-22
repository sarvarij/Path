package com.example.path.viewmodel;

import android.widget.AutoCompleteTextView;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.path.BuildConfig;
import com.example.path.db.EndpointEntity;
import com.example.path.db.EndpointStore;
import com.example.path.geo_tomtom.RequestRouteObserver;
import com.example.path.geo_tomtom.RoutingAPI;
import com.example.path.view.EndpointSetting;
import com.example.path.view.mapview.AnnotationSetting;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("unchecked")
@RequiredArgsConstructor
public class MainActivityVM extends ViewModel
        implements
        EndpointSetting.Listener,
        EndpointStore.Listener,
        RequestRouteObserver.Listener {


    @Inject
    protected EndpointSetting endpointSetting;
    @Inject
    protected EndpointStore endpointStore;
    @Inject
    protected RoutingAPI routingAPI;


    public EndpointSetting getEndpointSetting() {
        return endpointSetting;
    }
    public EndpointStore getEndpointStore() {
        return endpointStore;
    }


    private MutableLiveData<ArrayList<LatLng>> routePointsMLD = new MutableLiveData<>();
    private MutableLiveData[] endpointMLD = new MutableLiveData[]{
            new MutableLiveData<EndpointEntity>(), new MutableLiveData<EndpointEntity>()
    };

    public void setEndpointObservers(LifecycleOwner lifecycleOwner, AutoCompleteTextView... textViews){
        for (int c1 = 0; c1 < 2; c1++){
            endpointMLD[c1].observe(lifecycleOwner, new EndpointObserver(textViews[c1]));
        }
    }

    public void setRouteObserver(LifecycleOwner lifecycleOwner, MapView mapView, AnnotationSetting annotationSetting){
        routePointsMLD.observe(lifecycleOwner, new RouteObserver(mapView, annotationSetting));
    }


    @Override
    public void onEndpointLoaded(EndpointEntity endpointEntity) {
        endpointSetting.setEndpoint(endpointEntity);
        endpointMLD[endpointEntity.getEndpointIndex()].setValue(endpointEntity);
    }

    @Override
    public void onEndpointsProvided(LatLng source, LatLng destination) {
        routingAPI
                .calculateRoute(endpointSetting.asParamString(), BuildConfig.TOMTOM_API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestRouteObserver(this));
    }

    @Override
    public void onRouteProvided(ArrayList<LatLng> routePoints) {
        routePointsMLD.setValue(routePoints);
    }
}
