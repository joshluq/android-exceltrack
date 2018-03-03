package pe.exceltransport.exceltrack.view;

import pe.exceltransport.domain.Tracking;

public interface AddEventView extends LoadDataView {

    void renderTracking(Tracking tracking);

}
