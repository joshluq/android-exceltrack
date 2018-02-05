package pe.exceltransport.exceltrack.internal.di.component;

import android.app.Application;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import pe.exceltransport.exceltrack.AndroidApplication;
import pe.exceltransport.exceltrack.internal.di.module.ActivityBuilder;
import pe.exceltransport.exceltrack.internal.di.module.ApplicationModule;
import pe.exceltransport.exceltrack.internal.di.module.NetworkModule;
import pe.exceltransport.exceltrack.internal.di.module.RepositoryModule;

@Component(modules = {AndroidInjectionModule.class, ApplicationModule.class, NetworkModule.class,RepositoryModule.class, ActivityBuilder.class})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }

    void inject(AndroidApplication application);

}
