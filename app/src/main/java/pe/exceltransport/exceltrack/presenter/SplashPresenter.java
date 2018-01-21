package pe.exceltransport.exceltrack.presenter;

import android.os.Handler;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import pe.exceltransport.exceltrack.view.SplashView;

public class SplashPresenter implements Presenter<SplashView> {

    private static final long SPLASH_DURATION = 3000;

    private SplashView view;

    @Inject
    public SplashPresenter() {

    }

    @Override
    public void setView(@NonNull SplashView view) {
        this.view = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        view = null;
    }

    public void fetchData() {
        new Handler().postDelayed(() -> view.goToSignIn(), SPLASH_DURATION);
    }
}
