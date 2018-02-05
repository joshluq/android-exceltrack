package pe.exceltransport.data.network.response;

import com.google.gson.annotations.SerializedName;

import pe.exceltransport.data.entity.TrackingEntity;

public class TrackingResponse {

    @SerializedName("tracking")
    private TrackingEntity tracking;

    public TrackingEntity getTrackin() {
        return tracking;
    }

}
