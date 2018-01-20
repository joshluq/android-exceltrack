package pe.exceltransport.exceltrack.internal.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import pe.exceltransport.data.executor.JobExecutor;
import pe.exceltransport.domain.executor.PostExecutionThread;
import pe.exceltransport.domain.executor.ThreadExecutor;
import pe.exceltransport.exceltrack.AndroidApplication;
import pe.exceltransport.exceltrack.UIThread;

@Module
public class ApplicationModule {

    @Provides
    AndroidApplication provideApplication(AndroidApplication application) {
        return application;
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

}
