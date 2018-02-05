package pe.exceltransport.data.entity.mapper;


import pe.exceltransport.data.entity.TrackingEntity;
import pe.exceltransport.domain.Tracking;

public class TrackingEntityDataMapper {

    private TrackingEntityDataMapper(){
        //empty constructor
    }

    public static Tracking transform(TrackingEntity entity){
        if(entity == null){
            return null;
        }
        Tracking tracking = new Tracking();
        tracking.setTrackingId(entity.getId());
        tracking.setStatus(transform(entity.getStatus()));
        tracking.setEvents(EventEntityDataMapper.transform(entity.getEvents()));
        return tracking;
    }

    private static Tracking.Status transform(int status){
        if(status == 0){
            return Tracking.Status.CREATED;
        }else if(status == 1){
            return Tracking.Status.INITIATED;
        }else if(status == 2){
            return Tracking.Status.LOAD;
        }else if(status == 3){
            return Tracking.Status.SHIPMENT;
        }else if(status == 4){
            return Tracking.Status.UNLOADING;
        }else {
            return Tracking.Status.COMPLETED;
        }
    }

}
