package pe.exceltransport.exceltrack.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.AndroidInjection;
import pe.exceltransport.domain.Trip;
import pe.exceltransport.exceltrack.R;
import pe.exceltransport.exceltrack.presenter.TripDetailPresenter;
import pe.exceltransport.exceltrack.view.TripDetailView;
import pe.exceltransport.exceltrack.view.util.Extra;

public class TripDetailActivity extends BaseActivity implements TripDetailView {

    @BindView(R.id.map_loading)
    View vMapLoading;

    @Inject
    TripDetailPresenter presenter;

    private Trip trip;

    public static Intent getCallingIntent(BaseActivity activity, Trip trip) {
        Intent intent = new Intent(activity, TripDetailActivity.class);
        intent.putExtra(Extra.TRIP.getValue(), trip);
        return intent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_detail);
        injectView(this);
        presenter.setView(this);
        getExtras();
        initUI();
    }

    @Override
    protected void initUI() {
        presenter.mapListeners();
    }

    @Override
    public void showError(String message) {
        //default implementation
    }

    @Override
    public long getTripId() {
        return trip.getId();
    }

    @Override
    public SupportMapFragment getSupportMapFragment() {
        return (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.getUiSettings().setZoomControlsEnabled(false);
        googleMap.getUiSettings().setZoomGesturesEnabled(false);
        googleMap.getUiSettings().setRotateGesturesEnabled(false);
        addMarkersToMap(googleMap);
        moveCamera(googleMap);
    }

    @Override
    public void showMapLoading() {
        vMapLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideMapLoading() {
        vMapLoading.setVisibility(View.GONE);
    }

    private void getExtras() {
        trip = Trip.class.cast(getIntent().getSerializableExtra(Extra.TRIP.getValue()));
    }

    private void addMarkersToMap(GoogleMap googleMap) {
        addMarkerToMap(googleMap,
                new LatLng(trip.getStart().getLatitude(), trip.getStart().getLongitude()),
                getString(R.string.text_start),
                trip.getStart().getAddress());

        addMarkerToMap(googleMap,
                new LatLng(trip.getFinish().getLatitude(), trip.getFinish().getLongitude()),
                getString(R.string.text_finish),
                trip.getFinish().getAddress());

    }

    private void addMarkerToMap(GoogleMap googleMap, LatLng position, String title, String snippet) {
        googleMap.addMarker(new MarkerOptions()
                .position(position)
                .title(title)
                .snippet(snippet));
    }

    private void moveCamera(GoogleMap googleMap) {
        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(new LatLng(trip.getStart().getLatitude(), trip.getStart().getLongitude()))
                .include(new LatLng(trip.getFinish().getLatitude(), trip.getFinish().getLongitude()))
                .build();
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 80));
    }

}
