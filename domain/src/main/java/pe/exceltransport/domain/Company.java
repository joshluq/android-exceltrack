package pe.exceltransport.domain;

import java.io.Serializable;

public class Company implements Serializable {

    private String businessName;

    private String tradeName;

    private String ruc;

    private Location registeredAddress;

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public Location getRegisteredAddress() {
        return registeredAddress;
    }

    public void setRegisteredAddress(Location registeredAddress) {
        this.registeredAddress = registeredAddress;
    }
}
