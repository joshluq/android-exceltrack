package pe.exceltransport.exceltrack.view.activity;

import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import pe.exceltransport.exceltrack.R;
import pe.exceltransport.exceltrack.presenter.SplashPresenter;
import pe.exceltransport.exceltrack.view.SplashView;

public class SplashActivity extends BaseActivity implements SplashView {

    @Inject
    SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        injectView(this);
        presenter.setView(this);
        initUI();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    protected void initUI() {
        presenter.fetchData();
    }

    @Override
    public void showLoading() {
        //default implementation
    }

    @Override
    public void hideLoading() {
        //default implementation
    }

    @Override
    public void showError(String message) {
        //default implementation
    }

    @Override
    public void goToSignIn() {
        navigator.navigateToSignInActivity();
    }

    @Override
    public void goToMain() {
        navigator.navigateToMainActivity();
    }
}