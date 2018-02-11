package pe.exceltransport.data.entity.mapper;

import java.util.ArrayList;
import java.util.List;

import pe.exceltransport.data.entity.EventEntity;
import pe.exceltransport.domain.Event;

public class EventEntityDataMapper {

    private EventEntityDataMapper() {
        //empty constructor
    }

    private static Event transform(EventEntity entity) {
        if (entity == null) {
            return null;
        }
        Event event = new Event();
        event.setId(entity.getId());
        event.setCreationDate(entity.getCreationDate());
        event.setDetail(entity.getDetail());
        event.setType(transform(entity.getType()));
        event.setLocation(LocationEntityDataMapper.transform(entity.getLocation()));
        return event;
    }

    private static Event.Type transform(int type){
        if(type == 0){
            return Event.Type.TRACKING;
        }else{
            return Event.Type.INCIDENCE;
        }
    }

    public static List<Event> transform(List<EventEntity> entities){
        ArrayList<Event> list = new ArrayList<>();
        if(entities == null){
            return list;
        }
        for (EventEntity entity : entities){
            list.add(transform(entity));
        }
        return list;
    }

}
