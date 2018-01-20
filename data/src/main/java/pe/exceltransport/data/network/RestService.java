package pe.exceltransport.data.network;

import pe.exceltransport.data.BuildConfig;
import pe.exceltransport.data.network.body.SignInBody;
import pe.exceltransport.data.network.response.ResponseBody;
import pe.exceltransport.data.network.response.SignInResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RestService {

    String API_URL = BuildConfig.API_URL + BuildConfig.API_VERSION;

    @POST("users/signIn")
    Call<ResponseBody<SignInResponse>> signIn(@Body SignInBody bodyLogin);
}
