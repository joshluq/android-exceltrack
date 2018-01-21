package pe.exceltransport.exceltrack.internal.di.module;

import dagger.Module;
import dagger.Provides;
import pe.exceltransport.exceltrack.navigator.Navigator;
import pe.exceltransport.exceltrack.view.activity.MainActivity;

@Module
public class MainActivityModule {

    @Provides
    Navigator provideNavigator(MainActivity activity) {
        return new Navigator(activity);
    }

}
