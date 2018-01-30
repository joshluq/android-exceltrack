package pe.exceltransport.data.entity.mapper;

import pe.exceltransport.data.entity.CustomerEntity;
import pe.exceltransport.domain.Customer;

public class CustomerEntityDataMapper {

    private CustomerEntityDataMapper(){
        //empty constructor
    }

    static Customer transform(CustomerEntity entity){
        if(entity == null){
            return null;
        }
        Customer customer = new Customer();
        customer.setCompany(CompanyEntityDataMapper.transform(entity.getCompany()));
        return customer;
    }
}
