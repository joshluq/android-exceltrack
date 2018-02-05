package pe.exceltransport.domain.interactor;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.exceltransport.domain.Tracking;
import pe.exceltransport.domain.executor.PostExecutionThread;
import pe.exceltransport.domain.executor.ThreadExecutor;
import pe.exceltransport.domain.repository.TrackingRepository;

public class GetTracking extends UseCase<Tracking,GetTracking.Params> {

    private TrackingRepository repository;

    @Inject
    public GetTracking(TrackingRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    Observable<Tracking> buildUseCaseObservable(Params params) {
        return repository.getTracking(params.tripId);
    }

    public static final class Params {

        private final long tripId;

        private Params(long tripId) {
            this.tripId = tripId;
        }

        public static Params buildParams(long tripId) {
            return new Params(tripId);
        }
    }
}
