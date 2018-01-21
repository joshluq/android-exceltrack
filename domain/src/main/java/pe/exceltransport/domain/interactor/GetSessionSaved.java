package pe.exceltransport.domain.interactor;

import io.reactivex.Observable;
import pe.exceltransport.domain.Session;
import pe.exceltransport.domain.executor.PostExecutionThread;
import pe.exceltransport.domain.executor.ThreadExecutor;
import pe.exceltransport.domain.repository.UserRepository;

public class GetSessionSaved extends UseCase<Session,Void>{

    private final UserRepository repository;

    public GetSessionSaved(UserRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository =  repository;
    }

    @Override
    Observable<Session> buildUseCaseObservable(Void params) {
        return repository.getSessionSaved();
    }
}
