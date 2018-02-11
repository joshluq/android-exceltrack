package pe.exceltransport.domain;


import java.io.Serializable;
import java.util.List;

public class Tracking implements Serializable {

    private long trackingId;

    private Status status;

    private List<Event> events;

    public long getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(long trackingId) {
        this.trackingId = trackingId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public enum Status implements Serializable {
        CREATED,
        INITIATED,
        STARTING,
        LOAD,
        SHIPMENT,
        UNLOADING,
        COMPLETED
    }
}
