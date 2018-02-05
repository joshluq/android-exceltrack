package pe.exceltransport.exceltrack.internal.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import pe.exceltransport.exceltrack.view.activity.MainActivity;
import pe.exceltransport.exceltrack.view.activity.SignInActivity;
import pe.exceltransport.exceltrack.view.activity.SplashActivity;
import pe.exceltransport.exceltrack.view.activity.TripDetailActivity;


@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector(modules = SignInActivityModule.class)
    abstract SignInActivity bindSignInActivity();

    @ContributesAndroidInjector(modules = {MainActivityModule.class, MainFragmentProvider.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = TripDetailActivityModule.class)
    abstract TripDetailActivity bindTripDetailActivity();

}
