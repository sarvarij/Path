package com.example.path.view.mapview;

import com.mapbox.mapboxsdk.annotations.Polyline;
@SuppressWarnings("deprecation")
public class AnnotationSetting {
    private Polyline routePolyline;

    public void eraseRoute(){
        if (routePolyline != null) {
            routePolyline.remove();
            routePolyline = null;
        }
    }

    public void setRoute(Polyline polyline){
        this.routePolyline = polyline;
    }

}
