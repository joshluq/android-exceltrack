package pe.exceltransport.domain.repository;

import io.reactivex.Observable;

public interface TripRepository {

    Observable<Void> getTrips(long userId);

}
