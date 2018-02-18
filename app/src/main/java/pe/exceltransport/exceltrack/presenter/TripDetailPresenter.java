package pe.exceltransport.exceltrack.presenter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewTreeObserver;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import javax.inject.Inject;

import pe.exceltransport.data.exception.DefaultException;
import pe.exceltransport.domain.Event;
import pe.exceltransport.domain.Tracking;
import pe.exceltransport.domain.interactor.AddEvent;
import pe.exceltransport.domain.interactor.DefaultObserver;
import pe.exceltransport.domain.interactor.GetTracking;
import pe.exceltransport.exceltrack.exception.ErrorMessageFactory;
import pe.exceltransport.exceltrack.view.TripDetailView;
import pe.exceltransport.exceltrack.view.util.LocationProvider;

public class TripDetailPresenter implements Presenter<TripDetailView>, LocationProvider.LocationListener {

    private TripDetailView view;

    private SupportMapFragment supportMapFragment;
    private View mapView;

    private boolean isViewReady;
    private boolean isMapReady;
    private GoogleMap googleMap;

    private final LocationProvider locationProvider;
    private final GetTracking getTracking;
    private final AddEvent addEvent;
    private final Context context;

    @Inject
    public TripDetailPresenter(Context context, GetTracking getTracking, AddEvent addEvent, LocationProvider locationProvider) {
        this.context = context;
        this.getTracking = getTracking;
        this.addEvent = addEvent;
        this.locationProvider = locationProvider;
        this.isViewReady = false;
        this.isMapReady = false;
        this.googleMap = null;
    }

    @Override
    public void setView(@NonNull TripDetailView view) {
        this.view = view;
        this.supportMapFragment = view.getSupportMapFragment();
        this.mapView = supportMapFragment.getView();
    }

    @Override
    public void resume() {
        locationProvider.startLocationUpdates();
    }

    @Override
    public void pause() {
        //default implementation
    }

    @Override
    public void destroy() {
        view = null;
        isViewReady = false;
        isMapReady = false;
        googleMap = null;
        getTracking.dispose();
        addEvent.dispose();
    }

    public void mapListeners() {
        view.showMapLoading();
        locationProvider.setListener(this);
        locationProvider.connect();
        locationProvider.startLocationUpdates();
        if ((mapView.getWidth() != 0) && (mapView.getHeight() != 0)) {
            isViewReady = true;
        } else {
            mapView.getViewTreeObserver().addOnGlobalLayoutListener(new GlobalLayoutObserver());
        }
        supportMapFragment.getMapAsync(new MapReadyObserver());
    }


    private void fireCallbackIfReady() {
        if (isViewReady && isMapReady) {
            view.hideMapLoading();
            view.onMapReady(googleMap);
        }
    }

    public void getTracking() {
        getTracking.execute(new GetTrackingObserver(), GetTracking.Params.buildParams(view.getTripId()));
    }

    public void addTrackingEvent() {
        Event event = new Event();
        event.setType(Event.Type.TRACKING);
        addEvent.execute(new AddEventObserver(), AddEvent.Params.buildParams(view.getTrackingId(), event));
    }

    public Location getCurrentLocation(){
        return locationProvider.getCurrentLocation();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onNewLocation(Location location) {
        googleMap.setMyLocationEnabled(true);
    }

    @Override
    public void onLocationNotAvailable() {
        //
    }

    private final class GlobalLayoutObserver implements ViewTreeObserver.OnGlobalLayoutListener {

        @Override
        public void onGlobalLayout() {
            mapView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            isViewReady = true;
            fireCallbackIfReady();
        }
    }

    private final class MapReadyObserver implements OnMapReadyCallback {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            TripDetailPresenter.this.googleMap = googleMap;
            TripDetailPresenter.this.isMapReady = true;
            fireCallbackIfReady();
        }
    }

    private class GetTrackingObserver extends DefaultObserver<Tracking> {

        @Override
        protected void onStart() {
            super.onStart();
            view.showTrackingLoading();
        }

        @Override
        public void onComplete() {
            super.onComplete();
            view.hideTrackingLoading();
        }

        @Override
        public void onNext(Tracking tracking) {
            super.onNext(tracking);
            view.renderTracking(tracking);
        }

        @Override
        public void onError(Throwable exception) {
            super.onError(exception);
            view.hideLoading();
            view.showError(ErrorMessageFactory.create(context, (DefaultException) exception));
        }
    }

    private final class AddEventObserver extends GetTrackingObserver {

        @Override
        protected void onStart() {
            super.onStart();
            view.showLoading();
        }

        @Override
        public void onComplete() {
            super.onComplete();
            view.hideLoading();
        }

        @Override
        public void onNext(Tracking tracking) {
            super.onNext(tracking);
            view.showTracking();
        }
    }

}
