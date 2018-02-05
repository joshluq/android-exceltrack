package pe.exceltransport.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrackingEntity {

    @SerializedName("id")
    private long id;

    @SerializedName("status")
    private int status;

    @SerializedName("events")
    private List<EventEntity> events;

    public long getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public List<EventEntity> getEvents() {
        return events;
    }

}
