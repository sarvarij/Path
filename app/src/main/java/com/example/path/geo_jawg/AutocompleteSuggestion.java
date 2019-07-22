package com.example.path.geo_jawg;

import com.example.path.models.JAWGFeature;
import com.example.path.view.IAutocompleteSuggestion;

public class AutocompleteSuggestion implements IAutocompleteSuggestion<JAWGFeature> {
    @Override
    public String getCaption(JAWGFeature jawgFeature) {
        return jawgFeature.getProperties().getName();
    }

    @Override
    public String getHint(JAWGFeature jawgFeature) {
        return jawgFeature.getProperties().getLabel();
    }
}
