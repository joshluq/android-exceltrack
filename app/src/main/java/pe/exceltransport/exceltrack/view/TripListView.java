package pe.exceltransport.exceltrack.view;


import java.util.List;

import pe.exceltransport.domain.Trip;

public interface TripListView extends LoadDataView{

    void goToSignIn();

    void renderTrips(List<Trip> trips);

}
