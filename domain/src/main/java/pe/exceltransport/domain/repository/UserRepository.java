package pe.exceltransport.domain.repository;

import io.reactivex.Observable;
import pe.exceltransport.domain.Session;

public interface UserRepository {

    Observable<Session> signIn(final String email, final String password, String firebaseToken);

    Observable<Void> saveSession(Session session);

    Observable<Void> saveEmail(final String email);

    Observable<String> getEmailSaved();

    Observable<Session> getSessionSaved();

}
