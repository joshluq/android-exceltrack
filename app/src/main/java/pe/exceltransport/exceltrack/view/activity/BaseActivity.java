package pe.exceltransport.exceltrack.view.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import butterknife.ButterKnife;
import pe.exceltransport.exceltrack.R;
import pe.exceltransport.exceltrack.navigator.Navigator;

public class BaseActivity extends AppCompatActivity {

    private boolean stopped;

    @Inject
    Navigator navigator;

    @Override
    protected void onResume() {
        super.onResume();
        stopped = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopped = true;
    }

    protected void injectView(Activity activity) {
        ButterKnife.bind(activity);
    }

    protected void initUI(){

    }

    public boolean isStopped() {
        return stopped;
    }

}
