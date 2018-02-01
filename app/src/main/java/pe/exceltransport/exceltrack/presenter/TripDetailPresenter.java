package pe.exceltransport.exceltrack.presenter;


import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewTreeObserver;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import javax.inject.Inject;

import pe.exceltransport.exceltrack.view.TripDetailView;

public class TripDetailPresenter implements Presenter<TripDetailView> {

    private TripDetailView view;

    private SupportMapFragment supportMapFragment;
    private View mapView;

    private boolean isViewReady;
    private boolean isMapReady;
    private GoogleMap googleMap;

    @Inject
    public TripDetailPresenter() {
        isViewReady = false;
        isMapReady = false;
        googleMap = null;
    }

    @Override
    public void setView(@NonNull TripDetailView view) {
        this.view = view;
        this.supportMapFragment = view.getSupportMapFragment();
        this.mapView = supportMapFragment.getView();
    }

    @Override
    public void resume() {
        //default implementation
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
    }

    public void mapListeners() {
        view.showMapLoading();
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

}
