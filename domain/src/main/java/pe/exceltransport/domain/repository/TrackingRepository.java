package pe.exceltransport.domain.repository;

import io.reactivex.Observable;
import pe.exceltransport.domain.Tracking;

public interface TrackingRepository {

    Observable<Tracking> getTracking(long tripId);

}
