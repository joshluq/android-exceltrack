package pe.exceltransport.exceltrack.internal.di.module;

import dagger.Module;
import dagger.Provides;
import pe.exceltransport.exceltrack.navigator.Navigator;
import pe.exceltransport.exceltrack.view.activity.TripDetailActivity;

@Module
public class TripDetailActivityModule {

    @Provides
    Navigator provideNavigator(TripDetailActivity activity) {
        return new Navigator(activity);
    }

}
