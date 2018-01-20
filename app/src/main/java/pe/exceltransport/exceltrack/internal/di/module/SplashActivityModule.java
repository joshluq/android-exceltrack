package pe.exceltransport.exceltrack.internal.di.module;

import dagger.Module;
import dagger.Provides;
import pe.exceltransport.exceltrack.navigator.Navigator;
import pe.exceltransport.exceltrack.view.activity.SplashActivity;

@Module
public class SplashActivityModule {

    @Provides
    Navigator provideNavigator(SplashActivity activity) {
        return new Navigator(activity);
    }

}
