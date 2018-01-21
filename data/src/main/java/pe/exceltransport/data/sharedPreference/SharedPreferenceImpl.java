package pe.exceltransport.data.sharedPreference;


import com.google.gson.Gson;
import com.pddstudio.preferences.encrypted.EncryptedPreferences;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.exceltransport.data.BuildConfig;
import pe.exceltransport.data.entity.SessionEntity;
import pe.exceltransport.data.exception.DefaultException;

public class SharedPreferenceImpl implements SharedPreference {

    public static final String KEY = BuildConfig.PREFERENCE_KEY;

    private final static String KEY_EMAIL = "key_email";
    private final static String KEY_SESSION = "key_session";

    private final EncryptedPreferences encryptedPreferences;

    @Inject
    public SharedPreferenceImpl(EncryptedPreferences encryptedPreferences) {
        this.encryptedPreferences = encryptedPreferences;
    }

    @Override
    public Observable<Void> saveSession(String session) {
        return Observable.create(emitter -> {
            encryptedPreferences.edit()
                    .putString(encryptedPreferences.getUtils().encryptStringValue(KEY_SESSION), session)
                    .apply();
            emitter.onComplete();
        });
    }

    @Override
    public Observable<Void> saveEmail(String email) {
        return Observable.create(emitter -> {
            encryptedPreferences.edit()
                    .putString(encryptedPreferences.getUtils().encryptStringValue(KEY_EMAIL), email)
                    .apply();
            emitter.onComplete();
        });
    }

    @Override
    public Observable<String> getEmailSaved() {
        return Observable.create(emitter -> {
            String email = encryptedPreferences.getString(encryptedPreferences.getUtils().encryptStringValue(KEY_EMAIL), "");
            emitter.onNext(email);
            emitter.onComplete();
        });
    }

    @Override
    public Observable<SessionEntity> getSessionSaved() {
        return Observable.create(emitter -> {
            String session = encryptedPreferences.getString(encryptedPreferences.getUtils().encryptStringValue(KEY_SESSION), "");
            if(!session.isEmpty() ){
                emitter.onNext(new Gson().fromJson(session, SessionEntity.class));
                emitter.onComplete();
            }else{
                emitter.onError(new DefaultException(DefaultException.Codes.NO_SESSION.getCode()));
            }
        });
    }

    @Override
    public Observable<Void> deleteSession() {
        return null;
    }
}
