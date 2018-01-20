package pe.exceltransport.data.network;


import io.reactivex.Observable;
import pe.exceltransport.data.entity.UserEntity;
import pe.exceltransport.data.network.body.SignInBody;

public interface RestApi {

    Observable<UserEntity> signIn(SignInBody body);

}
