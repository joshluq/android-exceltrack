package pe.exceltransport.exceltrack.internal.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import pe.exceltransport.exceltrack.view.fragment.SignInFragment;
import pe.exceltransport.exceltrack.view.fragment.SplashFragment;

@Module
public abstract class SignInFragmentProvider {

    @ContributesAndroidInjector(modules = SplashFragmentModule.class)
    abstract SplashFragment provideSplashFragmentFactory();

    @ContributesAndroidInjector(modules = SignInFragmentModule.class)
    abstract SignInFragment provideSignInFragmentFactory();

}
