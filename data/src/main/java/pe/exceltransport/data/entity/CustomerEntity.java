package pe.exceltransport.data.entity;

import com.google.gson.annotations.SerializedName;

public class CustomerEntity {

    @SerializedName("company")
    private CompanyEntity company;

    public CompanyEntity getCompany() {
        return company;
    }

}
