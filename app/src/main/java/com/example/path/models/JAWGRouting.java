package com.example.path.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class JAWGRouting {

    @Data
    public static class JAWGRoute {

        @Data
        public static class JAWGRouteLeg{

            @Data
            public static class JAWGStep {

                @Data
                public static class JAWGGeometry {



                    @SerializedName("coordinates")
                    private List<List<Float>> coordinates;

                }

                @SerializedName("geometry")
                private JAWGGeometry geometry;

            }

            @SerializedName("steps")
            private List<JAWGStep> steps;

        }

        @SerializedName("legs")
        private List<JAWGRouteLeg> routeLegs;

    }

    @SerializedName("routes")
    private List<JAWGRoute> routes;

}
