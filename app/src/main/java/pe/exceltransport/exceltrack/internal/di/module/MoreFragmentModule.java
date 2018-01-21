package pe.exceltransport.exceltrack.internal.di.module;

import dagger.Module;
import dagger.Provides;
import pe.exceltransport.domain.executor.PostExecutionThread;
import pe.exceltransport.domain.executor.ThreadExecutor;
import pe.exceltransport.domain.interactor.DeleteSessionSaved;
import pe.exceltransport.domain.repository.UserRepository;

@Module
public class MoreFragmentModule {

    @Provides
    DeleteSessionSaved provideSaveSession(UserRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new DeleteSessionSaved(repository, threadExecutor, postExecutionThread);
    }

}
