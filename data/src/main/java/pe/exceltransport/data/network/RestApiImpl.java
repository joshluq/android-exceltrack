package pe.exceltransport.data.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Emitter;
import io.reactivex.Observable;
import pe.exceltransport.data.entity.SessionEntity;
import pe.exceltransport.data.entity.TripEntity;
import pe.exceltransport.data.exception.DefaultException;
import pe.exceltransport.data.network.body.SignInBody;
import pe.exceltransport.data.network.response.BodyResponse;
import pe.exceltransport.data.network.response.SignInResponse;
import pe.exceltransport.data.network.response.TripsResponse;
import retrofit2.Call;
import retrofit2.Response;

public class RestApiImpl implements RestApi {

    private final Context context;
    private final RestService restService;

    @Inject
    public RestApiImpl(Context context, RestService restService) {
        this.context = context;
        this.restService = restService;
    }

    @Override
    public Observable<SessionEntity> signIn(SignInBody body) {
        return Observable.create(emitter -> {
            if (isThereNetworkConnection(emitter)) {
                restService.signIn(body).enqueue(new DefaultCallback<BodyResponse<SignInResponse>>(emitter) {
                    @Override
                    public void onResponse(@NonNull Call<BodyResponse<SignInResponse>> call, @NonNull Response<BodyResponse<SignInResponse>> response) {
                        super.onResponse(call, response);
                        BodyResponse<SignInResponse> bodyResponse = response.body();
                        if (bodyResponse != null && bodyResponse.getBody() != null) {
                            emitter.onNext(bodyResponse.getBody().getSessionEntity());
                            emitter.onComplete();
                        }
                    }
                });
            }
        });
    }

    @Override
    public Observable<List<TripEntity>> getTrips(long userId, int status) {
        return Observable.create(emitter -> {
            if (isThereNetworkConnection(emitter)) {
                restService.getTrips(userId, status).enqueue(new DefaultCallback<BodyResponse<TripsResponse>>(emitter) {
                    @Override
                    public void onResponse(@NonNull Call<BodyResponse<TripsResponse>> call, @NonNull Response<BodyResponse<TripsResponse>> response) {
                        super.onResponse(call, response);
                        BodyResponse<TripsResponse> bodyResponse = response.body();
                        if (bodyResponse != null && bodyResponse.getBody() != null) {
                            emitter.onNext(bodyResponse.getBody().getTrips());
                            emitter.onComplete();
                        }
                    }
                });
            }
        });
    }


    private boolean isThereNetworkConnection(Emitter emitter) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) return true;
        emitter.onError(new DefaultException(DefaultException.Codes.NO_INTERNET.getCode()));
        return false;
    }
}
