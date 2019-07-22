package com.example.path.models;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class TTPosition {
    @SerializedName("lat")
    double lat;

    @SerializedName("lon")
    double lon;
}
