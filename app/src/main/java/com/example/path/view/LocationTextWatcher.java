package com.example.path.view;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;

import com.example.path.geo_jawg.AutocompleteProvider;
import com.example.path.geo_jawg.AutocompleteSuggestion;
import com.example.path.models.JAWGFeatureCollection;

public class LocationTextWatcher implements TextWatcher, AutocompleteProvider.Listener {

    private final AutoCompleteTextView textEdit;
    private AutocompleteProvider.AutocompleteSubscription autocompleteSubscription;




    public LocationTextWatcher(AutoCompleteTextView textEdit, AutocompleteProvider autocompleteProvider){
        this.textEdit = textEdit;
        autocompleteSubscription = autocompleteProvider.subscribe(this);

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (textEdit.hasFocus()) {
            autocompleteSubscription.requestSearch(charSequence.toString());
        }

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onAutocompleteReady(JAWGFeatureCollection jawgFeatureCollection) {
        if (textEdit.getAdapter() == null){
            textEdit.setAdapter(new AutocompleteAdapter(textEdit.getContext(), new AutocompleteSuggestion()));
        }

        ((AutocompleteAdapter)textEdit.getAdapter()).setItems(jawgFeatureCollection.getFeatures());
    }
}
