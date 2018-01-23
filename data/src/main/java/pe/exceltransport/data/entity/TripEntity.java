package pe.exceltransport.data.entity;



import com.google.gson.annotations.SerializedName;

public class TripEntity {

    @SerializedName("id")
    private long id;

    @SerializedName("start")
    private LocationEntity start;

    @SerializedName("finish")
    private LocationEntity finish;

    @SerializedName("status")
    private int status;

    @SerializedName("customer")
    private CustomerEntity customer;

}
