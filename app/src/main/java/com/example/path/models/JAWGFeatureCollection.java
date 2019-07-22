package com.example.path.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class JAWGFeatureCollection {
    @SerializedName("features")
    List<JAWGFeature> features;

}
