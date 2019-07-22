package com.example.path.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class TTCalculatedRoute {

    @Data
    public static class TTRoute {

        @Data
        public static class TTRouteLeg {
            @SerializedName("points")
            private List<TTPoint> points;
        }

        @SerializedName("legs")
        private List<TTRouteLeg> legs;
    }

    @SerializedName("routes")
    private List<TTRoute> routes;

}
