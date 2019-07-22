package com.example.path.geo_tomtom;


import com.example.path.models.TTSearch;
import com.example.path.view.IAutocompleteSuggestion;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AutocompleteSuggestion implements IAutocompleteSuggestion<TTSearch.TTSearchResult> {



    public String getCaption(TTSearch.TTSearchResult searchResult) {
        switch (searchResult.getType()) {
            case "Geography":
                return getGeoCaption(searchResult);
            case "POI":
                return getPOICaption(searchResult);
            default:
                return " * unknown search result type * ";
        }
    }

    public String getHint(TTSearch.TTSearchResult searchResult) {
        switch (searchResult.getType()) {
            case "Geography":
                return getGeoHint(searchResult);
            //case "POI":
            //    return getPOIHint();
            default:
                return " * unknown search result type * ";
        }
    }

    private String getPOICaption(TTSearch.TTSearchResult searchResult) {
        return null;
    }

    private String getGeoCaption(TTSearch.TTSearchResult searchResult) {
        String caption = "";

        switch (searchResult.getEntityType()) {

            case "Municipality":
                caption = searchResult.getAddress().getMunicipality();
            case "MunicipalitySubdivision":
                caption = searchResult.getAddress().getMunicipalitySubdivision();
            case "CountrySubdivision":
                caption = searchResult.getAddress().getCountrySubdivision();
        }


        return caption;
    }

    private String getGeoHint(TTSearch.TTSearchResult searchResult) {
        String hint = "";

        switch (searchResult.getEntityType()) {

            case "Municipality":
                hint = searchResult.getAddress().getCountrySubdivision() + ", " +
                                searchResult.getAddress().getCountry();
            case "MunicipalitySubdivision":
                hint = searchResult.getAddress().getMunicipality() + ", " +
                        searchResult.getAddress().getCountrySubdivision() + ", " +
                        searchResult.getAddress().getCountry();
            case "CountrySubdivision":
                hint = searchResult.getAddress().getCountry();
        }

        return hint;
    }

}
