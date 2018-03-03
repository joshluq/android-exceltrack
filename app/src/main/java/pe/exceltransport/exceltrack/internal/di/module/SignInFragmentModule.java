package pe.exceltransport.exceltrack.internal.di.module;

import com.mobsandgeeks.saripaar.Validator;

import dagger.Module;
import dagger.Provides;
import pe.exceltransport.domain.executor.PostExecutionThread;
import pe.exceltransport.domain.executor.ThreadExecutor;
import pe.exceltransport.domain.interactor.GetEmailSaved;
import pe.exceltransport.domain.interactor.SaveEmail;
import pe.exceltransport.domain.interactor.SaveSession;
import pe.exceltransport.domain.interactor.SignIn;
import pe.exceltransport.domain.repository.UserRepository;
import pe.exceltransport.exceltrack.navigator.Navigator;
import pe.exceltransport.exceltrack.view.activity.SignInActivity;
import pe.exceltransport.exceltrack.view.fragment.SignInFragment;

@Module
public class SignInFragmentModule {

    @Provides
    Navigator provideNavigator(SignInActivity activity) {
        return new Navigator(activity);
    }

    @Provides
    Validator provideValidator(SignInFragment fragment) {
        return new Validator(fragment);
    }

    @Provides
    SignIn provideSignIn(UserRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new SignIn(repository, threadExecutor, postExecutionThread);
    }

    @Provides
    SaveSession provideSaveSession(UserRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new SaveSession(repository, threadExecutor, postExecutionThread);
    }

    @Provides
    SaveEmail provideSaveEmail(UserRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new SaveEmail(repository, threadExecutor, postExecutionThread);
    }

    @Provides
    GetEmailSaved provideGetEmailSaved(UserRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new GetEmailSaved(repository, threadExecutor, postExecutionThread);
    }

}
