package com.example.path.view;

import com.example.path.db.EndpointEntity;
import com.mapbox.mapboxsdk.geometry.LatLng;

public class EndpointSetting {


    public interface Listener{
        void onEndpointsProvided(LatLng source, LatLng destination);
    }

    private Listener listener;

    LatLng[] endpoints = new LatLng[2];


    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public LatLng getEndpoint(int index){
        return endpoints[index];
    }

    public void setEndpoint(int index, double latitude, double longitude){
        LatLng newEndpoint = new LatLng(latitude, longitude);

        if (!newEndpoint.equals(endpoints[index])) {
            endpoints[index] = newEndpoint;
            notifyListener();
        }
    }

    public void setEndpoint(EndpointEntity endpointEntity){
        setEndpoint(endpointEntity.getEndpointIndex(),
                endpointEntity.getLatitude(),
                endpointEntity.getLongitude());
    }

    public EndpointEntity asEndpointEntity(int endpointIndex){
        EndpointEntity endpointEntity = new EndpointEntity();
        endpointEntity.setEndpointIndex(endpointIndex);
        endpointEntity.setLatitude(endpoints[endpointIndex].getLatitude());
        endpointEntity.setLongitude(endpoints[endpointIndex].getLongitude());
        return endpointEntity;
    }

    public String asParamString(){
        return
                endpoints[0].getLatitude() + "," + endpoints[0].getLongitude()
                + ":" +
                endpoints[1].getLatitude() + "," + endpoints[1].getLongitude();
    }


    public boolean isComplete(){
        return endpoints[0] != null && endpoints[1] != null;
    }


    private void notifyListener(){
        if (listener != null){
            if (isComplete()){
                listener.onEndpointsProvided(endpoints[0], endpoints[1]);
            }
        }
    }

}
