package pe.exceltransport.exceltrack.presenter;

import android.support.annotation.NonNull;

import pe.exceltransport.exceltrack.view.SplashView;

public class SplashPresenter implements Presenter<SplashView> {

    private SplashView view;

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

    }
}
