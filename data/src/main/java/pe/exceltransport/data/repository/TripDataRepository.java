package pe.exceltransport.data.repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.exceltransport.data.entity.mapper.TripEntityDataMapper;
import pe.exceltransport.data.network.RestApi;
import pe.exceltransport.domain.Trip;
import pe.exceltransport.domain.repository.TripRepository;


public class TripDataRepository implements TripRepository {

    private final RestApi restApi;

    @Inject
    TripDataRepository(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<List<Trip>> getTrips(long userId, Trip.Status status) {
        return restApi.getTrips(userId,status.ordinal()).map(TripEntityDataMapper::transform);
    }
}
