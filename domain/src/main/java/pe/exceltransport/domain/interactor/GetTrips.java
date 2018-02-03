package pe.exceltransport.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.exceltransport.domain.Trip;
import pe.exceltransport.domain.executor.PostExecutionThread;
import pe.exceltransport.domain.executor.ThreadExecutor;
import pe.exceltransport.domain.repository.TripRepository;

public class GetTrips extends UseCase<List<Trip>,GetTrips.Params> {

    private final TripRepository repository;

    @Inject
    public GetTrips(TripRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    Observable<List<Trip>> buildUseCaseObservable(Params params) {
        return repository.getTrips(params.userId,params.status );
    }

    public static final class Params {

        private final long userId;

        private final Trip.Status status;

        Params(long userId, Trip.Status status) {
            this.userId = userId;
            this.status = status;
        }

        public static Params buildParams(long userId, Trip.Status status) {
            return new Params(userId, status);
        }
    }
}
