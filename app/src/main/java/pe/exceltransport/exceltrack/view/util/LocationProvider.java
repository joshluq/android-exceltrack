package pe.exceltransport.exceltrack.view.util;


import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.os.Looper;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;


import javax.inject.Inject;

public class LocationProvider {

    private static final long UPDATE_INTERVAL = 60000;  /* 60 secs */
    private static final long FASTEST_INTERVAL = 5000; /* 5 secs */

    private final Context context;
    private final LocationRequest locationRequest;

    private LocationListener listener;

    private Location currentLocation;

    @Inject
    LocationProvider(Context context) {
        this.context = context;
        this.locationRequest = new LocationRequest();

    }

    @SuppressLint("MissingPermission")
    public void connect() {
        FusedLocationProviderClient locationClient = getFusedLocationProviderClient(context);
        locationClient.getLastLocation()
                .addOnSuccessListener(location -> {
                    if (location == null) return;
                    onLocationChanged(location);
                })
                .addOnFailureListener(e -> {
                    if (listener != null) listener.onLocationNotAvailable();
                });
    }

    @SuppressLint("MissingPermission")
    public void startLocationUpdates() {
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        locationRequest.setInterval(UPDATE_INTERVAL);
        locationRequest.setFastestInterval(FASTEST_INTERVAL);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(locationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        SettingsClient settingsClient = LocationServices.getSettingsClient(context);
        settingsClient.checkLocationSettings(locationSettingsRequest);
        getFusedLocationProviderClient(context).requestLocationUpdates(locationRequest,
                new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        onLocationChanged(locationResult.getLastLocation());
                    }
                }, Looper.myLooper());
    }


    private void onLocationChanged(Location location) {
        // GPS may be turned off
        if (location == null) {
            if (listener != null) listener.onLocationNotAvailable();
            return;
        }
        // Report to the UI that the location was updated
        if (listener != null&& location.hasAccuracy()) {
            this.currentLocation = location;
            listener.onNewLocation(location);
        }
    }

    public void setListener(LocationListener listener) {
        this.listener = listener;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public interface LocationListener {

        void onNewLocation(Location location);

        void onLocationNotAvailable();

    }
}
