package com.example.path.view.mapview;

import androidx.annotation.NonNull;

import com.example.path.BuildConfig;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

public class InitMapViewOperation implements OnMapReadyCallback {
    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {

        String url = "https://api.jawg.io/styles/jawg-streets.json?access-token="
                + BuildConfig.JAWG_API_KEY;
        Style.Builder builder = new Style.Builder().fromUrl(url);

        mapboxMap.setStyle(builder, null);

    }
}
