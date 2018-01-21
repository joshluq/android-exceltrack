package pe.exceltransport.exceltrack.internal.di.module;

import android.app.Application;
import android.content.Context;

import com.pddstudio.preferences.encrypted.EncryptedPreferences;

import dagger.Module;
import dagger.Provides;
import pe.exceltransport.data.executor.JobExecutor;
import pe.exceltransport.data.sharedPreference.SharedPreference;
import pe.exceltransport.data.sharedPreference.SharedPreferenceImpl;
import pe.exceltransport.domain.executor.PostExecutionThread;
import pe.exceltransport.domain.executor.ThreadExecutor;
import pe.exceltransport.exceltrack.AndroidApplication;
import pe.exceltransport.exceltrack.UIThread;

@Module
public class ApplicationModule {

    @Provides
    AndroidApplication provideApplication(Application application) {
        return (AndroidApplication) application;
    }

    @Provides
    Context provideContext(AndroidApplication application) {
        return application.getApplicationContext();
    }

    @Provides
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    SharedPreference provideSharedPreference(Context context) {
        return new SharedPreferenceImpl(new EncryptedPreferences.Builder(context)
                .withEncryptionPassword(SharedPreferenceImpl.KEY)
                .build());
    }

}
