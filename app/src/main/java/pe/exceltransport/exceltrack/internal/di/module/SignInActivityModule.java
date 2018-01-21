package pe.exceltransport.exceltrack.internal.di.module;


import dagger.Module;
import dagger.Provides;
import pe.exceltransport.domain.executor.PostExecutionThread;
import pe.exceltransport.domain.executor.ThreadExecutor;
import pe.exceltransport.domain.interactor.SignIn;
import pe.exceltransport.domain.repository.UserRepository;
import pe.exceltransport.exceltrack.navigator.Navigator;
import pe.exceltransport.exceltrack.view.activity.SignInActivity;

@Module
public class SignInActivityModule {

    @Provides
    Navigator provideNavigator(SignInActivity activity) {
        return new Navigator(activity);
    }

    @Provides
    SignIn provideSignIn(UserRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new SignIn(repository, threadExecutor, postExecutionThread);
    }

}
