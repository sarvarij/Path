package com.example.path.models;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class TTPoint {

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;

}
