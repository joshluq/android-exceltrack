package pe.exceltransport.exceltrack.internal.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import pe.exceltransport.exceltrack.view.dialog.AddIncidentDialog;

@Module
public abstract class TripDetailFragmentProvider {

    @ContributesAndroidInjector(modules = AddEventDialogModule.class)
    abstract AddIncidentDialog provideAddEventDialog();

}
