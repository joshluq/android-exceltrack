package pe.exceltransport.domain.repository;

import io.reactivex.Observable;
import pe.exceltransport.domain.User;

public interface UserRepository {

    Observable<User> signIn(final String email, final String password, String firebaseToken);

    Observable<Void> saveEmail(final String email);

    Observable<String> getEmailSaved();

}
