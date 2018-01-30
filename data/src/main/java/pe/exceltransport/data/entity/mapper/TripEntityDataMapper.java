package pe.exceltransport.data.entity.mapper;

import java.util.ArrayList;
import java.util.List;

import pe.exceltransport.data.entity.TripEntity;
import pe.exceltransport.domain.Trip;

public class TripEntityDataMapper {

    private TripEntityDataMapper(){
        //empty constructor
    }

    static Trip transform(TripEntity entity){
        if(entity == null){
            return null;
        }
        Trip trip = new Trip();
        trip.setId(entity.getId());
        trip.setStart(LocationEntityDataMapper.transform(entity.getStart()));
        trip.setFinish(LocationEntityDataMapper.transform(entity.getFinish()));
        trip.setStatus(transform(entity.getStatus()));
        trip.setCustomer(CustomerEntityDataMapper.transform(entity.getCustomer()));
        return trip;
    }

    private static Trip.Status transform(int status){
        if(status == 0){
            return Trip.Status.PENDING;
        }else{
            return Trip.Status.ACTIVE;
        }
    }

    public static List<Trip> transform(List<TripEntity> entities){
        ArrayList<Trip> list = new ArrayList<>();
        if(entities == null){
            return list;
        }
        for (TripEntity entity : entities){
            list.add(transform(entity));
        }
        return list;
    }

}
