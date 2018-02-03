package pe.exceltransport.exceltrack.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import pe.exceltransport.domain.Trip;
import pe.exceltransport.exceltrack.R;
import pe.exceltransport.exceltrack.presenter.TripDetailPresenter;
import pe.exceltransport.exceltrack.view.TripDetailView;
import pe.exceltransport.exceltrack.view.util.Extra;

public class TripDetailActivity extends BaseActivity implements TripDetailView {

    @BindView(R.id.map_loading)
    View vMapLoading;

    @BindView(R.id.v_bottom_sheet)
    View vBottomSheet;

    @BindView(R.id.fab_events)
    FloatingActionButton fabEvents;

    @BindView(R.id.tv_customer_name)
    TextView tvCustomerName;

    @BindView(R.id.tv_start)
    TextView tvStart;

    @BindView(R.id.tv_finish)
    TextView tvFinish;

    @Inject
    TripDetailPresenter presenter;

    private Trip trip;

    private GoogleMap googleMap;

    private BottomSheetBehavior bottomSheetBehavior;

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
        setupBottomSheetBehavior();
        tvCustomerName.setText(trip.getCustomer().getCompany().getTradeName());
        tvStart.setText(trip.getStart().getAddress());
        tvFinish.setText(trip.getFinish().getAddress());
    }

    @OnClick(R.id.ib_location)
    public void onIbLocation(){
        moveCamera();
    }

    @OnClick(R.id.fab_events)
    public void onFabEvents(){
        bottomSheetBehavior.setState(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED ? BottomSheetBehavior.STATE_EXPANDED : BottomSheetBehavior.STATE_COLLAPSED);
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
        this.googleMap = googleMap;
        this.googleMap.getUiSettings().setZoomControlsEnabled(false);
        this.googleMap.getUiSettings().setZoomGesturesEnabled(false);
        this.googleMap.getUiSettings().setRotateGesturesEnabled(false);
        addMarkersToMap();
        moveCamera();
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

    private void addMarkersToMap() {
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

    private void moveCamera() {
        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(new LatLng(trip.getStart().getLatitude(), trip.getStart().getLongitude()))
                .include(new LatLng(trip.getFinish().getLatitude(), trip.getFinish().getLongitude()))
                .build();
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 80));
    }

    private void setupBottomSheetBehavior(){
        bottomSheetBehavior = BottomSheetBehavior.from(vBottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (BottomSheetBehavior.STATE_EXPANDED == newState) {
                    fabEvents.animate().scaleX(0).scaleY(0).setDuration(300).start();
                } else if (BottomSheetBehavior.STATE_COLLAPSED == newState) {
                    fabEvents.animate().scaleX(1).scaleY(1).setDuration(300).start();
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                //default implementation
            }
        });
    }

}
