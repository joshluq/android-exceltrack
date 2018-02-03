package pe.exceltransport.data.entity;

import com.google.gson.annotations.SerializedName;

public class CompanyEntity {

    @SerializedName("id")
    private long id;

    @SerializedName("businessName")
    private String businessName;

    @SerializedName("tradeName")
    private String tradeName;

    @SerializedName("ruc")
    private String ruc;

    @SerializedName("registeredAddress")
    private LocationEntity registeredAddress;

    public long getId() {
        return id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getTradeName() {
        return tradeName;
    }

    public String getRuc() {
        return ruc;
    }

    public LocationEntity getRegisteredAddress() {
        return registeredAddress;
    }
}
