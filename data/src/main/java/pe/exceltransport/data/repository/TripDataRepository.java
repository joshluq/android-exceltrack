package pe.exceltransport.data.repository;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.exceltransport.data.network.RestApi;
import pe.exceltransport.domain.repository.TripRepository;


public class TripDataRepository implements TripRepository {

    private final RestApi restApi;

    @Inject
    TripDataRepository(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<Void> getTrips(long userId) {
        return null;
    }
}
