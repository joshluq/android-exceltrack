package pe.exceltransport.domain.interactor;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.exceltransport.domain.executor.PostExecutionThread;
import pe.exceltransport.domain.executor.ThreadExecutor;
import pe.exceltransport.domain.repository.UserRepository;

public class SaveEmail extends UseCase<Void,SaveEmail.Params>{

    private final UserRepository repository;

    @Inject
    public SaveEmail(UserRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    Observable<Void> buildUseCaseObservable(Params params) {
        return repository.saveEmail(params.email);
    }

    public static final class Params {

        private final String email;

        Params(String email) {
            this.email = email;
        }

        public static Params buildParams(String email) {
            return new Params(email);
        }
    }
}
