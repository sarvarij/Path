package com.example.path.geo_tomtom;

import com.example.path.models.TTCalculatedRoute;
import com.example.path.models.TTPoint;
import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestRouteOperation implements Callback<TTCalculatedRoute> {

    private Listener listener;

    public interface Listener{
        void onRouteProvided(ArrayList<LatLng> routePoints);
    }

    public RequestRouteOperation(Listener listener){
        this.listener = listener;
    }

    @Override
    public void onResponse(Call<TTCalculatedRoute> call, Response<TTCalculatedRoute> response) {
        ArrayList<LatLng> routePoints = extractTTRoute(response.body());

        if (listener != null){
            listener.onRouteProvided(routePoints);
        }
    }

    @Override
    public void onFailure(Call<TTCalculatedRoute> call, Throwable t) {

    }

    private ArrayList<LatLng> extractTTRoute(TTCalculatedRoute calculatedRoute) {
        ArrayList<LatLng> result = new ArrayList<>();

        if (calculatedRoute != null) {
            for (TTCalculatedRoute.TTRoute ttRoute : calculatedRoute.getRoutes()) {
                for (TTCalculatedRoute.TTRoute.TTRouteLeg ttRouteLeg : ttRoute.getLegs()) {
                    for (TTPoint ttPoint : ttRouteLeg.getPoints()) {
                        result.add(new LatLng(ttPoint.getLatitude(), ttPoint.getLongitude()));
                    }
                }
            }
        }

        return result;
    }
}
