package com.example.path.viewmodel;

import android.widget.AutoCompleteTextView;

import androidx.lifecycle.Observer;

import com.example.path.db.EndpointEntity;

class EndpointObserver implements Observer<EndpointEntity> {

    private final AutoCompleteTextView textView;

    EndpointObserver(AutoCompleteTextView textView) {
        this.textView = textView;
    }

    @Override
    public void onChanged(EndpointEntity endpointEntity) {
        textView.setText(endpointEntity.getLocationText());
    }
}
