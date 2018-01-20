package pe.exceltransport.exceltrack.internal.di.module;

import dagger.Module;
import dagger.Provides;
import pe.exceltransport.data.repository.UserDataRepository;
import pe.exceltransport.domain.repository.UserRepository;

@Module
public class RepositoryModule {

    @Provides
    UserRepository providePlanetRepository(UserDataRepository repository) {
        return repository;
    }
}
