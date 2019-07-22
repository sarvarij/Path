package com.example.path.geo_tomtom;

import com.example.path.models.TTSearch;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SearchAPI {

    @GET("search/2/search/{query}.json")
    Single<TTSearch> search(
            @Path("query") String query,
            @Query("typeahead") boolean typeahead,
            @Query("limit") int limit,
            @Query("countrySet") String countrySet,
            @Query("lat") float latBias,
            @Query("lon") float lonBias,
            @Query("idxSet") String idxSet,
            @Query("key") String apiKey);
}
