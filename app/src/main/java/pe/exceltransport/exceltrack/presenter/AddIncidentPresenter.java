package pe.exceltransport.exceltrack.presenter;

import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import pe.exceltransport.data.entity.mapper.LocationEntityDataMapper;
import pe.exceltransport.data.exception.DefaultException;
import pe.exceltransport.domain.Event;
import pe.exceltransport.domain.Tracking;
import pe.exceltransport.domain.interactor.AddEvent;
import pe.exceltransport.domain.interactor.DefaultObserver;
import pe.exceltransport.exceltrack.exception.ErrorMessageFactory;
import pe.exceltransport.exceltrack.internal.bus.EventBus;
import pe.exceltransport.exceltrack.internal.bus.RxBus;
import pe.exceltransport.exceltrack.view.AddIncidentView;
import pe.exceltransport.exceltrack.view.util.LocationProvider;

public class AddIncidentPresenter implements Presenter<AddIncidentView>, LocationProvider.LocationListener {

    private AddIncidentView view;
    private final Context context;
    private final AddEvent addEvent;
    private final LocationProvider locationProvider;
    private final RxBus rxBus;

    @Inject
    AddIncidentPresenter(Context context, AddEvent addEvent, LocationProvider locationProvider, RxBus rxBus) {
        this.context = context;
        this.addEvent = addEvent;
        this.locationProvider = locationProvider;
        this.rxBus = rxBus;
    }

    @Override
    public void setView(@NonNull AddIncidentView view) {
        this.view = view;
    }

    @Override
    public void resume() {
        //default implementation
    }

    @Override
    public void pause() {
        //default implementation
    }

    @Override
    public void destroy() {
        view = null;
        addEvent.dispose();
    }

    public void sendBus(Tracking tracking) {
        rxBus.send(new EventBus.UpdateTrackingEvent(tracking));
    }

    public void getCurrentLocation() {
        locationProvider.setListener(this);
        locationProvider.connect();
        locationProvider.startLocationUpdates();
    }

    @Override
    public void onNewLocation(Location location) {
        addIncidenceEvent(location);
    }

    @Override
    public void onLocationNotAvailable() {
        //default implementation
    }

    private void addIncidenceEvent(Location location) {
        Event event = new Event();
        event.setType(Event.Type.INCIDENCE);
        event.setDetail(view.getIncidentDetail());
        event.setLocation(LocationEntityDataMapper.transform(location));
        addEvent.execute(new AddEventObserver(), AddEvent.Params.buildParams(view.getTrackingId(), event));
    }

    private class AddEventObserver extends DefaultObserver<Tracking> {

        @Override
        protected void onStart() {
            super.onStart();
            view.showLoading();
        }

        @Override
        public void onComplete() {
            super.onComplete();
            view.hideLoading();
        }

        @Override
        public void onNext(Tracking tracking) {
            super.onNext(tracking);
            view.renderTracking(tracking);
        }

        @Override
        public void onError(Throwable exception) {
            super.onError(exception);
            view.hideLoading();
            view.showError(ErrorMessageFactory.create(context, (DefaultException) exception));
        }
    }
}