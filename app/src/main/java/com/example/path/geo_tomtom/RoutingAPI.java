package com.example.path.geo_tomtom;

import com.example.path.models.TTCalculatedRoute;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RoutingAPI {
    @GET("routing/1/calculateRoute/{coordinates}/json")
    //Call<TTCalculatedRoute> calculateRoute(  -- retrofit variant
    Single<TTCalculatedRoute> calculateRoute(
            @Path("coordinates") String coordinates,
            @Query("key") String apiKey);

}
