package pe.exceltransport.domain.interactor;

import io.reactivex.Observable;
import pe.exceltransport.domain.Session;
import pe.exceltransport.domain.executor.PostExecutionThread;
import pe.exceltransport.domain.executor.ThreadExecutor;
import pe.exceltransport.domain.repository.UserRepository;

public class SaveSession extends UseCase<Void, SaveSession.Params> {

    private final UserRepository repository;


    public SaveSession(UserRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    Observable<Void> buildUseCaseObservable(Params params) {
        return repository.saveSession(params.session);
    }

    public static final class Params {

        private final Session session;

        Params(Session session) {
            this.session = session;
        }

        public static Params buildParams(Session session) {
            return new Params(session);
        }
    }
}
