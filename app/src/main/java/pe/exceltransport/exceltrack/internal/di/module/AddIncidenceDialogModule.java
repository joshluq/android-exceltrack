package pe.exceltransport.exceltrack.internal.di.module;

import com.mobsandgeeks.saripaar.Validator;

import dagger.Module;
import dagger.Provides;
import pe.exceltransport.exceltrack.view.dialog.AddIncidentDialog;

@Module
public class AddIncidenceDialogModule {

    @Provides
    Validator provideValidator(AddIncidentDialog dialog) {
        return new Validator(dialog);
    }

}
