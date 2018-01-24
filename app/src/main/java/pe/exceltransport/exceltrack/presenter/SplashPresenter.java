package pe.exceltransport.exceltrack.presenter;

import android.os.Handler;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import pe.exceltransport.domain.Session;
import pe.exceltransport.domain.interactor.DefaultObserver;
import pe.exceltransport.domain.interactor.GetSessionSaved;
import pe.exceltransport.exceltrack.view.SplashView;

public class SplashPresenter implements Presenter<SplashView> {

    private static final long SPLASH_DURATION = 1000;

    private SplashView view;

    private final GetSessionSaved getSessionSaved;

    @Inject
    public SplashPresenter(GetSessionSaved getSessionSaved) {
        this.getSessionSaved = getSessionSaved;
    }

    @Override
    public void setView(@NonNull SplashView view) {
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
        getSessionSaved.dispose();
    }

    public void fetchData() {
        new Handler().postDelayed(this::getSession, SPLASH_DURATION);
    }

    private void getSession(){
        getSessionSaved.execute(new GetSessionSavedObserver(),null);
    }

    private final class GetSessionSavedObserver extends DefaultObserver<Session> {

        @Override
        public void onNext(Session session) {
            super.onNext(session);
                view.goToMain();
        }

        @Override
        public void onError(Throwable exception) {
            super.onError(exception);
            view.goToSignIn();
        }
    }
}
