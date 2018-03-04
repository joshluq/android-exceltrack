package pe.exceltransport.exceltrack.view;

import pe.exceltransport.domain.Tracking;

public interface AddIncidentView extends LoadDataView {

    long getTrackingId();

    String getIncidentDetail();

    void renderTracking(Tracking tracking);

}
