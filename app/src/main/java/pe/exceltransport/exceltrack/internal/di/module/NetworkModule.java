package pe.exceltransport.exceltrack.internal.di.module;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pe.exceltransport.data.network.RestApi;
import pe.exceltransport.data.network.RestApiImpl;
import pe.exceltransport.data.network.RestService;
import pe.exceltransport.data.network.Url;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(logging).build();
    }

    @Provides
    Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(Url.ROOT.getValue())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    RestService provideRestService(Retrofit retrofit) {
        return retrofit.create(RestService.class);
    }

    @Provides
    RestApi provideRestApi(Context context, RestService restService){
        return new RestApiImpl(context, restService);
    }

}
