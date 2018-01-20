package pe.exceltransport.data.repository;

import io.reactivex.Observable;
import pe.exceltransport.domain.User;
import pe.exceltransport.domain.repository.UserRepository;

public class UserDataRepository implements UserRepository {

    @Override
    public Observable<User> signIn(String email, String password) {
        return null;
    }
}
