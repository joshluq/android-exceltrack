package pe.exceltransport.domain.interactor;


import javax.inject.Inject;

import io.reactivex.Observable;
import pe.exceltransport.domain.Session;
import pe.exceltransport.domain.User;
import pe.exceltransport.domain.executor.PostExecutionThread;
import pe.exceltransport.domain.executor.ThreadExecutor;
import pe.exceltransport.domain.repository.UserRepository;

public class SignIn extends UseCase<Session, SignIn.Params> {

    private final UserRepository repository;

    @Inject
    public SignIn(UserRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    Observable<Session> buildUseCaseObservable(Params params) {
        return repository.signIn(params.email,params.password,"");
    }

    public static final class Params {

        private final String email;

        private final String password;

        Params(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public static Params buildParams(String email, String password) {
            return new Params(email, password);
        }
    }
}
