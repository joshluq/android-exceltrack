package pe.exceltransport.domain.interactor;

import io.reactivex.Observable;
import pe.exceltransport.domain.executor.PostExecutionThread;
import pe.exceltransport.domain.executor.ThreadExecutor;
import pe.exceltransport.domain.repository.UserRepository;

public class DeleteSessionSaved extends UseCase<Void ,Void>{

    private final UserRepository repository;

    public DeleteSessionSaved(UserRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository =  repository;
    }

    @Override
    Observable<Void> buildUseCaseObservable(Void params) {
        return repository.deleteSessionSaved();
    }
}
