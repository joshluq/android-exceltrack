package pe.exceltransport.data.network;


import io.reactivex.Observable;
import pe.exceltransport.data.entity.SessionEntity;
import pe.exceltransport.data.network.body.SignInBody;

public interface RestApi {

    Observable<SessionEntity> signIn(SignInBody body);

}
