package pe.exceltransport.data.entity;

import com.google.gson.annotations.SerializedName;

public class EventEntity {

    @SerializedName("id")
    private long id;

    @SerializedName("creationDate")
    private long creationDate;

    @SerializedName("detail")
    private String detail;

    @SerializedName("type")
    private int type;

    @SerializedName("location")
    private LocationEntity location;

    public long getId() {
        return id;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public String getDetail() {
        return detail;
    }

    public int getType() {
        return type;
    }

    public LocationEntity getLocation() {
        return location;
    }
}
