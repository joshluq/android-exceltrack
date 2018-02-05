package pe.exceltransport.domain;

import java.io.Serializable;

public class Customer implements Serializable{

    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
