package pe.exceltransport.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import pe.exceltransport.domain.Trip;

public interface TripRepository {

    Observable<List<Trip>> getTrips(long userId, Trip.Status status);

}
