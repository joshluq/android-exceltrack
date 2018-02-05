package pe.exceltransport.domain;


import java.util.List;

public class Tracking {

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

    public enum Status{
        CREATED,
        INITIATED,
        LOAD,
        SHIPMENT,
        UNLOADING,
        COMPLETED
    }
}
