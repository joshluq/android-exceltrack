package pe.exceltransport.exceltrack.presenter;


import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import pe.exceltransport.data.exception.DefaultException;
import pe.exceltransport.domain.Session;
import pe.exceltransport.domain.Trip;
import pe.exceltransport.domain.interactor.DefaultObserver;
import pe.exceltransport.domain.interactor.GetSessionSaved;
import pe.exceltransport.domain.interactor.GetTrips;
import pe.exceltransport.exceltrack.exception.ErrorMessageFactory;
import pe.exceltransport.exceltrack.view.TripListView;

public class TripListPresenter implements Presenter<TripListView> {

    private TripListView view;
    private final Context context;
    private final GetTrips getTrips;
    private final GetSessionSaved getSessionSaved;

    @Inject
    TripListPresenter(Context context, GetSessionSaved getSessionSaved, GetTrips getTrips) {
        this.context = context;
        this.getTrips = getTrips;
        this.getSessionSaved = getSessionSaved;
    }

    @Override
    public void setView(@NonNull TripListView view) {
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
    }

    private void getPendingTrips() {
        getSessionSaved.execute(new GetSessionSavedObserver(), null);
    }

    private final class GetSessionSavedObserver extends DefaultObserver<Session> {

        @Override
        public void onNext(Session session) {
            super.onNext(session);
            getTrips.execute(new GetTripListObserver(), GetTrips.Params.buildParams(session.getUser().getId(), Trip.Status.PENDING));
        }

        @Override
        public void onError(Throwable exception) {
            super.onError(exception);
            view.goToSignIn();
        }
    }

    private final class GetTripListObserver extends DefaultObserver<List<Trip>> {

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
        public void onNext(List<Trip> trips) {
            super.onNext(trips);
            view.renderTrips(trips);
        }

        @Override
        public void onError(Throwable exception) {
            super.onError(exception);
            view.hideLoading();
            view.showError(ErrorMessageFactory.create(context, (DefaultException) exception));
        }
    }
}
