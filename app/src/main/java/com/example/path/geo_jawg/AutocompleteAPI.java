package com.example.path.geo_jawg;

import com.example.path.models.JAWGFeatureCollection;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AutocompleteAPI {

    @GET("places/v1/autocomplete")
    //Call<JAWGFeatureCollection> getAutocomplete(  --retrofit variant
    Single<JAWGFeatureCollection> getAutocomplete(
            @Query("access-token") String accessToken,
            @Query("text") String text,
            @Query("focus.point.lat") float latitude,
            @Query("focus.point.lon") float longitude
            );


}
