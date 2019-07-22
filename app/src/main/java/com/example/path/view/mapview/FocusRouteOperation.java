package com.example.path.view.mapview;

import androidx.annotation.NonNull;

import com.example.path.view.EndpointSetting;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

public class FocusRouteOperation implements OnMapReadyCallback {

    private EndpointSetting endpointSetting;

    public FocusRouteOperation(EndpointSetting endpointSetting){

        this.endpointSetting = endpointSetting;
    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(endpointSetting.getEndpoint(0))
                .include(endpointSetting.getEndpoint(1))
                .build();


        int padding = (int) (Math.min(mapboxMap.getWidth(), mapboxMap.getHeight()) / 4f);
        mapboxMap.easeCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding), 500);

    }
}
