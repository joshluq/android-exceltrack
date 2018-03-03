package pe.exceltransport.exceltrack.internal.di.module;

import dagger.Module;
import dagger.Provides;
import pe.exceltransport.domain.executor.PostExecutionThread;
import pe.exceltransport.domain.executor.ThreadExecutor;
import pe.exceltransport.domain.interactor.AddEvent;
import pe.exceltransport.domain.interactor.GetTracking;
import pe.exceltransport.domain.repository.TrackingRepository;
import pe.exceltransport.exceltrack.navigator.Navigator;
import pe.exceltransport.exceltrack.view.activity.TripDetailActivity;
import pe.exceltransport.exceltrack.view.util.LocationProvider;

@Module
public class TripDetailActivityModule {

    @Provides
    Navigator provideNavigator(TripDetailActivity activity) {
        return new Navigator(activity);
    }

    @Provides
    GetTracking provideGetTracking(TrackingRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new GetTracking(repository,threadExecutor,postExecutionThread);
    }

    @Provides
    AddEvent provideAddEvent(TrackingRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new AddEvent(repository,threadExecutor,postExecutionThread);
    }

}
