package pe.exceltransport.exceltrack.internal.di.module;

import dagger.Module;
import dagger.Provides;
import pe.exceltransport.domain.executor.PostExecutionThread;
import pe.exceltransport.domain.executor.ThreadExecutor;
import pe.exceltransport.domain.interactor.GetSessionSaved;
import pe.exceltransport.domain.interactor.GetTrips;
import pe.exceltransport.domain.repository.TripRepository;
import pe.exceltransport.domain.repository.UserRepository;

@Module
public class TripListFragmentModule {

    @Provides
    GetTrips provideGetTrips(TripRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new GetTrips(repository, threadExecutor, postExecutionThread);
    }


    @Provides
    GetSessionSaved provideGetSessionSaved(UserRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread){
        return new GetSessionSaved(repository,threadExecutor,postExecutionThread);
    }
}
