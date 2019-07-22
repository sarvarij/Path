package com.example.path;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import com.example.path.geo_jawg.AutocompleteProvider;
import com.example.path.dagger.DaggerMainActivityComponent;
import com.example.path.dagger.MainActivityModule;
import com.example.path.models.JAWGFeature;
import com.example.path.view.AutocompleteAdapter;
import com.example.path.view.LocationTextWatcher;
import com.example.path.view.mapview.AnnotationSetting;
import com.example.path.view.mapview.FocusRouteOperation;
import com.example.path.view.mapview.InitMapViewOperation;
import com.example.path.viewmodel.MainActivityVM;
import com.example.path.viewmodel.MainActivityVMFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {


    @Inject
    protected AnnotationSetting annotationSetting;
    @Inject
    protected AutocompleteProvider autocompleteProvider;
    @Inject
    protected MainActivityVMFactory mainActivityVMFactory;

    @BindView(R.id.root_layout)
    protected ViewGroup rootLayout;
    @BindView(R.id.map_view)
    protected MapView mapView;
    @BindView(R.id.edit_source_location)
    protected AutoCompleteTextView sourceLocationEdit;
    @BindView(R.id.edit_destination_location)
    protected AutoCompleteTextView destinationLocationEdit;
    @BindView(R.id.fab_focus_route)
    protected FloatingActionButton fab;

    MainActivityVM viewModel;

    private void injectMainActivityComponent() {

        DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .pathApplicationComponent(PathApplication.getPathApplicationComponent())
                .build()
                .inject(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, null);
        setContentView(R.layout.activity_main);


        injectMainActivityComponent();

        ButterKnife.bind(this);
        rootLayout.requestFocus();

        viewModel = ViewModelProviders.of(this, mainActivityVMFactory).get(MainActivityVM.class);
        viewModel.setEndpointObservers(this, sourceLocationEdit, destinationLocationEdit);
        viewModel.setRouteObserver(this, mapView, annotationSetting);

        viewModel.getEndpointStore().loadFromDB();

        sourceLocationEdit.addTextChangedListener(new LocationTextWatcher(sourceLocationEdit, autocompleteProvider));
        sourceLocationEdit.setOnItemClickListener(new EndpointSelectListener(sourceLocationEdit, 0));

        destinationLocationEdit.addTextChangedListener(new LocationTextWatcher(destinationLocationEdit, autocompleteProvider));
        destinationLocationEdit.setOnItemClickListener(new EndpointSelectListener(destinationLocationEdit, 1));

        mapView.onCreate(savedInstanceState);

        initMapView();

    }

    @OnClick(R.id.fab_focus_route)
    protected void focusRoute() {
        if (viewModel.getEndpointSetting().isComplete()) {
            mapView.getMapAsync(new FocusRouteOperation(viewModel.getEndpointSetting()));
        }
    }



    private class EndpointSelectListener implements AdapterView.OnItemClickListener {

        private AutoCompleteTextView textView;
        private int pointIndex;

        public EndpointSelectListener(AutoCompleteTextView textView, int pointIndex) {
            this.textView = textView;

            this.pointIndex = pointIndex;
        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            JAWGFeature jawgFeature = (JAWGFeature)
                    ((AutocompleteAdapter) adapterView.getAdapter()).getSearchResult(i);

            viewModel.getEndpointSetting().setEndpoint(pointIndex,
                    jawgFeature.getGeometry().getCoordinates().get(1),
                    jawgFeature.getGeometry().getCoordinates().get(0));

            viewModel.getEndpointStore().saveToDB(
                    viewModel.getEndpointSetting().asEndpointEntity(pointIndex),
                    textView.getText().toString());

        }
    }





    private void initMapView() {
        mapView.getMapAsync(new InitMapViewOperation());

    }



    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        autocompleteProvider.dispose();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

}
