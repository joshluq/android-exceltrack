package pe.exceltransport.exceltrack.internal.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import pe.exceltransport.exceltrack.view.fragment.MoreFragment;
import pe.exceltransport.exceltrack.view.fragment.TripListFragment;

@Module
public abstract class MainFragmentProvider {

    @ContributesAndroidInjector(modules = MoreFragmentModule.class)
    abstract MoreFragment provideMoreFragmentFactory();

    @ContributesAndroidInjector()
    abstract TripListFragment provideTripListFragmentFactory();

}