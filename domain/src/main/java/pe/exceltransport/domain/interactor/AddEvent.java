package pe.exceltransport.domain.interactor;

import io.reactivex.Observable;
import pe.exceltransport.domain.Event;
import pe.exceltransport.domain.Tracking;
import pe.exceltransport.domain.executor.PostExecutionThread;
import pe.exceltransport.domain.executor.ThreadExecutor;
import pe.exceltransport.domain.repository.TrackingRepository;

public class AddEvent extends UseCase<Tracking, AddEvent.Params> {

    private TrackingRepository repository;

    public AddEvent(TrackingRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    Observable<Tracking> buildUseCaseObservable(Params params) {
        return repository.addEvent(params.trackingId, params.event);
    }

    public static final class Params {

        private final long trackingId;
        private final Event event;

        private Params(long trackingId, Event event) {
            this.trackingId = trackingId;
            this.event = event;
        }

        public static Params buildParams(long trackingId, Event event) {
            return new Params(trackingId, event);
        }
    }

}
