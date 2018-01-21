package pe.exceltransport.data.repository;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.exceltransport.data.entity.mapper.UserEntityDataMapper;
import pe.exceltransport.data.network.RestApi;
import pe.exceltransport.data.network.body.SignInBody;
import pe.exceltransport.data.sharedPreference.SharedPreference;
import pe.exceltransport.domain.User;
import pe.exceltransport.domain.repository.UserRepository;

public class UserDataRepository implements UserRepository {

    private final RestApi restApi;
    private final SharedPreference sharedPreference;

    @Inject
    UserDataRepository(RestApi restApi, SharedPreference sharedPreference) {
        this.restApi = restApi;
        this.sharedPreference = sharedPreference;
    }

    @Override
    public Observable<User> signIn(String email, String password, String firebaseToken) {
        SignInBody body = new SignInBody();
        body.setEmail(email);
        body.setPassword(password);
        body.setFirebaseToken(firebaseToken);
        return restApi.signIn(body).map(UserEntityDataMapper::transform);
    }

    @Override
    public Observable<Void> saveEmail(String email) {
        return sharedPreference.saveEmail(email);
    }

    @Override
    public Observable<String> getEmailSaved() {
        return sharedPreference.getEmailSaved();
    }
}
