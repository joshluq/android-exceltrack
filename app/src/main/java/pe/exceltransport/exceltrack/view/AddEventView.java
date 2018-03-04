package pe.exceltransport.exceltrack.view;

import pe.exceltransport.domain.Tracking;

public interface AddEventView extends LoadDataView {

    long getTrackingId();

    String getIncidentDetail();

    void renderTracking(Tracking tracking);

}
