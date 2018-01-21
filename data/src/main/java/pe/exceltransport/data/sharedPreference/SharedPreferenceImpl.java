package pe.exceltransport.data.sharedPreference;


import com.pddstudio.preferences.encrypted.EncryptedPreferences;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.exceltransport.data.BuildConfig;

public class SharedPreferenceImpl implements SharedPreference{

    public static final String KEY = BuildConfig.PREFERENCE_KEY;

    private final static String KEY_USERNAME = "key_email";

    private final EncryptedPreferences encryptedPreferences;

    @Inject
    public SharedPreferenceImpl(EncryptedPreferences encryptedPreferences) {
        this.encryptedPreferences = encryptedPreferences;
    }

    @Override
    public Observable<Void> saveEmail(String email) {
        return Observable.create(emitter -> {
            encryptedPreferences.edit()
                    .putString(encryptedPreferences.getUtils().encryptStringValue(KEY_USERNAME), email)
                    .apply();
            emitter.onComplete();
        });
    }

    @Override
    public Observable<String> getEmailSaved() {
        return Observable.create(emitter -> {
            String email = encryptedPreferences.getString(encryptedPreferences.getUtils().encryptStringValue(KEY_USERNAME), "");
            emitter.onNext(email);
            emitter.onComplete();
        });
    }
}
