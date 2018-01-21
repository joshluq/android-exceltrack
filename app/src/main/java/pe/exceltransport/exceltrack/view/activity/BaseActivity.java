package pe.exceltransport.exceltrack.view.activity;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.exceltransport.exceltrack.R;
import pe.exceltransport.exceltrack.navigator.Navigator;

public abstract class BaseActivity extends AppCompatActivity {

    private boolean stopped;

    @Nullable
    @BindView(R.id.v_loading)
    View vLoading;

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

    public void showLoading(){
        if(vLoading != null){
            vLoading.setVisibility(View.VISIBLE);
        }
    }

    public void hideLoading(){
        if(vLoading != null){
            vLoading.setVisibility(View.GONE);
        }
    }

}
