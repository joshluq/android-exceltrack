package pe.exceltransport.data.network;

import pe.exceltransport.data.BuildConfig;
import pe.exceltransport.data.network.body.SignInBody;
import pe.exceltransport.data.network.response.BodyResponse;
import pe.exceltransport.data.network.response.SignInResponse;
import pe.exceltransport.data.network.response.TripsResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestService {

    String API_URL = BuildConfig.API_URL + BuildConfig.API_VERSION;

    @POST("users/signIn")
    Call<BodyResponse<SignInResponse>> signIn(@Body SignInBody bodyLogin);

    @GET("users/{userId}/trips")
    Call<BodyResponse<TripsResponse>> getTrips(@Path("customerId") long customerId, @Query("status") int status);
}
