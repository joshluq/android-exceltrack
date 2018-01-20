package pe.exceltransport.data.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;

import io.reactivex.Emitter;
import io.reactivex.Observable;
import pe.exceltransport.data.entity.UserEntity;
import pe.exceltransport.data.exception.DefaultException;
import pe.exceltransport.data.network.body.SignInBody;
import pe.exceltransport.data.network.response.BodyResponse;
import pe.exceltransport.data.network.response.SignInResponse;
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
    public Observable<UserEntity> signIn(SignInBody body) {
        return Observable.create(emitter -> {
            if (isThereNetworkConnection(emitter)) {
                restService.signIn(body).enqueue(new DefaultCallback<BodyResponse<SignInResponse>>(emitter) {
                    @Override
                    public void onResponse(Call<BodyResponse<SignInResponse>> call, Response<BodyResponse<SignInResponse>> response) {
                        super.onResponse(call, response);
                        BodyResponse<SignInResponse> body = response.body();
                        if (body != null && body.getBody() != null) {
                            emitter.onNext(body.getBody().getUserEntity());
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
