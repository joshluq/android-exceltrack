package pe.exceltransport.data.network.body;


public class EventBody {

    private int type;
    private String detail;
    private Double latitude;
    private Double longitude;

    public void setType(int type) {
        this.type = type;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
