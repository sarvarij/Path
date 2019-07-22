package com.example.path.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class JAWGFeature {

    @Data
    public static class JAWGGeometry{
        @SerializedName("coordinates")
        private List<Float> coordinates;
    }

    @Data
    public static class JAWGProperties {
        @SerializedName("name")
        private String name;

        @SerializedName("label")
        private String label;
    }

    @SerializedName("properties")
    private JAWGProperties properties;

    @SerializedName("geometry")
    private JAWGGeometry geometry;

}
