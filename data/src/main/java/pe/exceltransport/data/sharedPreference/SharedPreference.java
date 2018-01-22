package pe.exceltransport.data.sharedPreference;

import io.reactivex.Observable;
import pe.exceltransport.data.entity.SessionEntity;

public interface SharedPreference {

    Observable<Void> saveSession(String session);

    Observable<Void> saveEmail(String email);

    Observable<String> getEmailSaved();

    Observable<SessionEntity> getSessionSaved();

}