package pe.exceltransport.domain.interactor;


import javax.inject.Inject;

import io.reactivex.Observable;
import pe.exceltransport.domain.User;
import pe.exceltransport.domain.executor.PostExecutionThread;
import pe.exceltransport.domain.executor.ThreadExecutor;
import pe.exceltransport.domain.repository.UserRepository;

public class SignIn extends UseCase<User, SignIn.Params> {

    private final UserRepository repository;


    @Inject
    SignIn(UserRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    Observable<User> buildUseCaseObservable(Params params) {
        return null;
    }

    public static final class Params {

        private final String email;

        private final String password;

        public Params(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public static Params getParams(String email, String password) {
            return new Params(email, password);
        }
    }
}
