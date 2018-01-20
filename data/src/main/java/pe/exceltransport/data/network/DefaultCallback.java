package pe.exceltransport.data.network;

import io.reactivex.ObservableEmitter;
import pe.exceltransport.data.exception.DefaultException;
import pe.exceltransport.data.network.response.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DefaultCallback<T> implements Callback<T> {

    private final ObservableEmitter emitter;

    public DefaultCallback(ObservableEmitter emitter) {
        this.emitter = emitter;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (!response.isSuccessful()) {
            emitter.onError(new DefaultException(DefaultException.Codes.DEFAULT_ERROR.getCode()));
        }else{
            ResponseBody body = ((ResponseBody) response.body());
            if (body != null && body.getError() != null) {
                emitter.onError(new DefaultException(body.getError().getMessage(),body.getError().getCode()));
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        emitter.onError(new DefaultException(DefaultException.Codes.DEFAULT_ERROR.getCode()));
    }
}
