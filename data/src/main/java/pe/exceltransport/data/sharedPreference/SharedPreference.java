package pe.exceltransport.data.sharedPreference;

import io.reactivex.Observable;

public interface SharedPreference {

    Observable<Void> saveEmail(String email);

    Observable<String> getEmailSaved();

}
