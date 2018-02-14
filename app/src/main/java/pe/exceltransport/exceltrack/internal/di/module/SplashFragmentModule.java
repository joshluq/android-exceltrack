package pe.exceltransport.exceltrack.internal.di.module;

import dagger.Module;
import dagger.Provides;
import pe.exceltransport.domain.executor.PostExecutionThread;
import pe.exceltransport.domain.executor.ThreadExecutor;
import pe.exceltransport.domain.interactor.GetSessionSaved;
import pe.exceltransport.domain.repository.UserRepository;
import pe.exceltransport.exceltrack.navigator.Navigator;
import pe.exceltransport.exceltrack.view.activity.SignInActivity;

@Module
public class SplashFragmentModule {

    @Provides
    Navigator provideNavigator(SignInActivity activity) {
        return new Navigator(activity);
    }

    @Provides
    GetSessionSaved provideGetSessionSaved(UserRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread){
        return new GetSessionSaved(repository,threadExecutor,postExecutionThread);
    }

}
