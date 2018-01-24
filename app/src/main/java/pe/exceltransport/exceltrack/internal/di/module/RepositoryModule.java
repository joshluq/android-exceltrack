package pe.exceltransport.exceltrack.internal.di.module;

import dagger.Module;
import dagger.Provides;
import pe.exceltransport.data.repository.UserDataRepository;
import pe.exceltransport.domain.repository.TripRepository;
import pe.exceltransport.domain.repository.UserRepository;

@Module
public class RepositoryModule {

    @Provides
    UserRepository provideUserRepository(UserDataRepository repository) {
        return repository;
    }

    @Provides
    TripRepository provideTripRepository(TripRepository repository) {
        return repository;
    }
}
