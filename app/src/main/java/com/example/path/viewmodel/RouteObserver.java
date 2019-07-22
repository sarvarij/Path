package com.example.path.viewmodel;

import androidx.lifecycle.Observer;

import com.example.path.view.mapview.AnnotateRouteOperation;
import com.example.path.view.mapview.AnnotationSetting;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;

import java.util.ArrayList;

class RouteObserver implements Observer<ArrayList<LatLng>> {

    private final MapView mapView;
    private final AnnotationSetting annotationSetting;

    public RouteObserver(MapView mapView, AnnotationSetting annotationSetting) {
        this.mapView = mapView;
        this.annotationSetting = annotationSetting;
    }

    @Override
    public void onChanged(ArrayList<LatLng> routePoints) {
        mapView.getMapAsync(new AnnotateRouteOperation(annotationSetting, routePoints));
    }
}
