package pe.exceltransport.data.network;

import java.util.List;

import io.reactivex.Observable;
import pe.exceltransport.data.entity.SessionEntity;
import pe.exceltransport.data.entity.TripEntity;
import pe.exceltransport.data.network.body.SignInBody;

public interface RestApi {

    //user
    Observable<SessionEntity> signIn(SignInBody body);


    //trip
    Observable<List<TripEntity>> getTrips(long userId, int status);

}
