package com.example.path.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class TTSearch {

    @Data
    public static class TTSearchResult{

        @Data
        public static class TTAddress{
            @SerializedName("municipalitySubdivision")
            String municipalitySubdivision;

            @SerializedName("municipality")
            String municipality;

            @SerializedName("countrySubdivision")
            String countrySubdivision;

            @SerializedName("country")
            String country;

            @SerializedName("freeFormAddress")
            String freeFormAddress;


        }

        @SerializedName("type")
        String type; //"Geography", "POI", ...

        @SerializedName("entityType") //Only present if type == "Geography"
        String entityType; //"Municipality", ...

        @SerializedName("position")
        TTPosition position;

        @SerializedName("address")
        TTAddress address;


    }

    @SerializedName("results")
    List<TTSearchResult> results;
}
