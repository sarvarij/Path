package com.example.path.view.mapview;

import android.graphics.Color;

import androidx.annotation.NonNull;

import com.mapbox.mapboxsdk.annotations.Polyline;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class AnnotateRouteOperation implements OnMapReadyCallback {

    private final AnnotationSetting annotationSetting;
    private final ArrayList<LatLng> routePoints;

    public AnnotateRouteOperation(AnnotationSetting annotationSetting, ArrayList<LatLng> routePoints){

        this.annotationSetting = annotationSetting;
        this.routePoints = routePoints;
    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        annotationSetting.eraseRoute();
        Polyline routePolyline = mapboxMap.addPolyline(new PolylineOptions()
                .add(routePoints.toArray(new LatLng[routePoints.size()]))
                .width(2f)
                .color(Color.RED)
        );

        annotationSetting.setRoute(routePolyline);

    }
}
