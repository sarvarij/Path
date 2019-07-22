package com.example.path.geo_tomtom;

import com.example.path.models.TTCalculatedRoute;
import com.example.path.models.TTPoint;
import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.ArrayList;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class RequestRouteObserver implements SingleObserver<TTCalculatedRoute> {

    private Listener listener;

    public interface Listener{
        void onRouteProvided(ArrayList<LatLng> routePoints);
    }


    public RequestRouteObserver(Listener listener){
        this.listener = listener;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onSuccess(TTCalculatedRoute calculatedRoute) {
        ArrayList<LatLng> routePoints = extractTTRoute(calculatedRoute);

        if (listener != null){
            listener.onRouteProvided(routePoints);
        }
    }

    @Override
    public void onError(Throwable e) {

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
