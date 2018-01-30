package pe.exceltransport.data.entity.mapper;

import pe.exceltransport.data.entity.CompanyEntity;
import pe.exceltransport.domain.Company;

public class CompanyEntityDataMapper {

    private CompanyEntityDataMapper(){
        //empty constructor
    }

    static Company transform(CompanyEntity entity){
        if (entity == null) {
            return null;
        }
        Company company = new Company();
        company.setBusinessName(entity.getBusinessName());
        company.setTradeName(entity.getTradeName());
        company.setRuc(entity.getRuc());
        company.setRegisteredAddress(LocationEntityDataMapper.transform(entity.getRegisteredAddress()));
        return company;
    }

}
