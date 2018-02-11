package pe.exceltransport.data.repository;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.exceltransport.data.entity.mapper.TrackingEntityDataMapper;
import pe.exceltransport.data.network.RestApi;
import pe.exceltransport.data.network.body.EventBody;
import pe.exceltransport.domain.Event;
import pe.exceltransport.domain.Tracking;
import pe.exceltransport.domain.repository.TrackingRepository;

public class TrackingDataRepository implements TrackingRepository{

    private final RestApi restApi;

     @Inject
     TrackingDataRepository(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<Tracking> getTracking(long tripId) {
        return restApi.getTracking(tripId).map(TrackingEntityDataMapper::transform);
    }

    @Override
    public Observable<Tracking> addEvent(long trackingId, Event event) {
        EventBody body = new EventBody();
        body.setType(event.getType().ordinal());
        body.setDetail(event.getDetail());
        return restApi.addEvent(trackingId, body).map(TrackingEntityDataMapper::transform);
    }

}
