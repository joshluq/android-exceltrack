package pe.exceltransport.data.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import pe.exceltransport.data.entity.TripEntity;

public class TripsResponse {

    @SerializedName("trips")
    private List<TripEntity> trips;

    public List<TripEntity> getTrips() {
        return trips;
    }
}
