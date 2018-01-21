package pe.exceltransport.domain.interactor;


import javax.inject.Inject;

import io.reactivex.Observable;
import pe.exceltransport.domain.executor.PostExecutionThread;
import pe.exceltransport.domain.executor.ThreadExecutor;
import pe.exceltransport.domain.repository.UserRepository;

public class GetEmailSaved extends UseCase<String,Void>{

    private final UserRepository repository;

    @Inject
    public GetEmailSaved(UserRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    Observable<String> buildUseCaseObservable(Void params) {
        return repository.getEmailSaved();
    }
}
