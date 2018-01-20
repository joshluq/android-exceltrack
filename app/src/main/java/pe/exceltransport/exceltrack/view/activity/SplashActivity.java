package pe.exceltransport.exceltrack.view.activity;

import android.os.Bundle;

import dagger.android.AndroidInjection;
import pe.exceltransport.exceltrack.R;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_splash);
        injectView(this);
    }


}
