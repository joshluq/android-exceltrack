package pe.exceltransport.exceltrack.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ebanx.swipebtn.OnActiveListener;
import com.ebanx.swipebtn.SwipeButton;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import pe.exceltransport.domain.Event;
import pe.exceltransport.domain.Location;
import pe.exceltransport.domain.Tracking;
import pe.exceltransport.domain.Trip;
import pe.exceltransport.exceltrack.R;
import pe.exceltransport.exceltrack.presenter.TripDetailPresenter;
import pe.exceltransport.exceltrack.view.TripDetailView;
import pe.exceltransport.exceltrack.view.adapter.EventTimeLineAdapter;
import pe.exceltransport.exceltrack.view.util.DateUtil;
import pe.exceltransport.exceltrack.view.util.Extra;
import pe.exceltransport.exceltrack.view.util.PermissionUtil;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;


public class TripDetailActivity extends BaseActivity implements TripDetailView, OnActiveListener, DialogInterface.OnClickListener, EventTimeLineAdapter.OnItemClickListener {

    @BindView(R.id.map_loading)
    View vMapLoading;

    @BindView(R.id.event_loading)
    View vEventLoading;

    @BindView(R.id.button_loading)
    View vButtonLoading;

    @BindView(R.id.v_bottom_sheet)
    View vBottomSheet;

    @BindView(R.id.tv_customer_name)
    TextView tvCustomerName;

    @BindView(R.id.tv_start)
    TextView tvStart;

    @BindView(R.id.tv_finish)
    TextView tvFinish;

    @BindView(R.id.rv_tracking)
    RecyclerView rvTracking;

    @BindView(R.id.swipe_button)
    SwipeButton swipeButton;

    @BindView(R.id.fab_events)
    FloatingActionButton fabEvents;

    @Inject
    TripDetailPresenter presenter;

    @Inject
    EventTimeLineAdapter trackingAdapter;

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
        requiresPermission();
    }

    @Override
    protected void initUI() {
        presenter.mapListeners();
        presenter.getTracking();
        setupBottomSheetBehavior();
        tvCustomerName.setText(trip.getCustomer().getCompany().getTradeName());
        tvStart.setText(trip.getStart().getAddress());
        tvFinish.setText(trip.getFinish().getAddress());
        setupRecyclerView();
    }

    @OnClick(R.id.ib_location)
    public void onIbLocation() {
        moveCamera(true);
    }

    @OnClick(R.id.fab_events)
    public void onFabEvents() {
        navigator.showAddEventDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.resume();
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
    public long getTrackingId() {
        return trip.getTracking().getTrackingId();
    }

    @Override
    public Location getCurrentLocation() {
        Location currentLocation = new Location();
        currentLocation.setLatitude(presenter.getCurrentLocation().getLatitude());
        currentLocation.setLongitude(presenter.getCurrentLocation().getLongitude());
        return currentLocation;
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
        moveCamera(false);
    }

    @Override
    public void showMapLoading() {
        vMapLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideMapLoading() {
        vMapLoading.setVisibility(View.GONE);
    }

    @Override
    public void showTrackingLoading() {
        swipeButton.setVisibility(View.INVISIBLE);
        vButtonLoading.setVisibility(View.VISIBLE);
        vEventLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTrackingLoading() {
        swipeButton.setVisibility(View.VISIBLE);
        vEventLoading.setVisibility(View.GONE);
        vButtonLoading.setVisibility(View.GONE);
    }

    @Override
    public void renderTracking(Tracking tracking) {
        trip.setTracking(tracking);
        trackingAdapter.bindList(tracking.getEvents());
        addEventsToMap(tracking.getEvents());
        setupSwipeButton();
    }

    @Override
    public void showTracking() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        swipeButton.toggleState();
    }

    private void getExtras() {
        trip = Trip.class.cast(getIntent().getSerializableExtra(Extra.TRIP.getValue()));
    }

    private void addMarkersToMap() {
        addMarkerToMap(googleMap,
                new LatLng(trip.getStart().getLatitude(), trip.getStart().getLongitude()),
                getString(R.string.text_start),
                trip.getStart().getAddress(),
                BitmapDescriptorFactory.fromResource(R.drawable.marker_local_shipping));

        addMarkerToMap(googleMap,
                new LatLng(trip.getFinish().getLatitude(), trip.getFinish().getLongitude()),
                getString(R.string.text_finish),
                trip.getFinish().getAddress(),
                BitmapDescriptorFactory.fromResource(R.drawable.marker_beenhere));

    }

    private void addEventsToMap(List<Event> events) {
        for (Event event : events) {
            if (event.getLocation() != null) {
                addMarkerToMap(googleMap,
                        new LatLng(event.getLocation().getLatitude(), event.getLocation().getLongitude()),
                        DateUtil.milliSecondsToDateFormatted(event.getCreationDate(), DateUtil.DEFAULT_FORMAT),
                        event.getDetail(),
                        BitmapDescriptorFactory.fromResource(R.drawable.marker_place));
            }

        }
    }

    private void addMarkerToMap(GoogleMap googleMap, LatLng position, String title, String snippet, BitmapDescriptor icon) {
        googleMap.addMarker(new MarkerOptions()
                .icon(icon)
                .position(position)
                .title(title)
                .snippet(snippet));
    }

    private void moveCamera(boolean animate) {
        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(new LatLng(trip.getStart().getLatitude(), trip.getStart().getLongitude()))
                .include(new LatLng(trip.getFinish().getLatitude(), trip.getFinish().getLongitude()))
                .build();
        if (animate) {
            googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 80));
        } else {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 80));
        }
    }

    private void setupBottomSheetBehavior() {
        bottomSheetBehavior = BottomSheetBehavior.from(vBottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (BottomSheetBehavior.STATE_EXPANDED == newState) {
                    fabEvents.animate().scaleX(1).scaleY(1).setDuration(300).start();
                } else if (BottomSheetBehavior.STATE_COLLAPSED == newState) {
                    fabEvents.animate().scaleX(0).scaleY(0).setDuration(300).start();
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                //default implementation
            }
        });
    }

    private void setupRecyclerView() {
        trackingAdapter.setListener(this);
        rvTracking.setLayoutManager(new LinearLayoutManager(this));
        rvTracking.setHasFixedSize(true);
        rvTracking.setAdapter(trackingAdapter);
    }

    private void setupSwipeButton() {
        swipeButton.setVisibility(View.VISIBLE);
        swipeButton.setOnActiveListener(this);
        swipeButton.setText(getNextTrackName(trip.getTracking().getStatus()));
    }

    private String getNextTrackName(Tracking.Status status) {
        switch (status) {
            case CREATED:
                return getString(R.string.text_accept);
            case INITIATED:
                return getString(R.string.text_go_to_starting);
            case STARTING:
                return getString(R.string.text_starting_load);
            case LOAD:
                return getString(R.string.text_starting_shipment);
            case SHIPMENT:
                return getString(R.string.text_starting_unloading);
            default:
                return getString(R.string.text_finished);
        }
    }

    @Override
    public void onActive() {
        navigator.showAlertDialog("Mensaje",
                "¿Esta seguro de cambiar el estado?",
                "Si, Estoy Seguro",
                this);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
        presenter.addTrackingEvent();
    }

    @Override
    public void onMarkerClick(Location location) {
        if (location != null) {
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(),
                    location.getLongitude()), 15));
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

    @AfterPermissionGranted(PermissionUtil.LOCATION)
    private void requiresPermission() {
        String[] perms = {PermissionUtil.Permission.LOCATION.getPerm()};
        if (EasyPermissions.hasPermissions(this, perms)) {
            turnGPSOn();
            initUI();
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(
                    new PermissionRequest.Builder(this, PermissionUtil.Permission.LOCATION.getCode(), perms)
                            .setRationale(R.string.location_rationale)
                            .setPositiveButtonText(R.string.rationale_ask_ok)
                            .build());
        }
    }

    private void turnGPSOn() {
        if (!isGpsEnabled()) { //if gps is disabled
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }
    }
}
