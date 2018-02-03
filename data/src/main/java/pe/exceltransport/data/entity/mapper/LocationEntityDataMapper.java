package pe.exceltransport.data.entity.mapper;

import pe.exceltransport.data.entity.LocationEntity;
import pe.exceltransport.domain.Location;

public class LocationEntityDataMapper {

    private LocationEntityDataMapper() {
        //empty constructor
    }

    static Location transform(LocationEntity entity) {
        if (entity == null) {
            return null;
        }
        Location location = new Location();
        location.setAddress(entity.getAddress());
        location.setLatitude(entity.getLatitude());
        location.setLongitude(entity.getLongitude());
        return location;
    }
}
