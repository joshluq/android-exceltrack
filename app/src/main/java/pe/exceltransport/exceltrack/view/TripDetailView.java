package pe.exceltransport.exceltrack.view;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import pe.exceltransport.domain.Tracking;

public interface TripDetailView extends LoadDataView {

    long getTripId();

    SupportMapFragment getSupportMapFragment();

    void onMapReady(GoogleMap googleMap);

    void showMapLoading();

    void hideMapLoading();

    void showTrackingLoading();

    void hideTrackingLoading();

    void renderTracking(Tracking tracking);

}
