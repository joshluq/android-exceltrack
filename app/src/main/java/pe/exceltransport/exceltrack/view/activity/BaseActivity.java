package pe.exceltransport.exceltrack.view.activity;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.exceltransport.exceltrack.R;
import pe.exceltransport.exceltrack.navigator.Navigator;

public abstract class BaseActivity extends AppCompatActivity {

    private boolean stopped;

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;

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

    protected void initUI() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null)
                getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    public boolean isStopped() {
        return stopped;
    }

    public void showLoading() {
        if (vLoading != null) {
            vLoading.setVisibility(View.VISIBLE);
        }
    }

    public void hideLoading() {
        if (vLoading != null) {
            vLoading.setVisibility(View.GONE);
        }
    }

    public void setToolbarTitle(String title) {
        if (toolbar != null) {
            ((TextView) toolbar.findViewById(R.id.toolbar_title)).setText(title);
        }
    }

    public Navigator getNavigator() {
        return navigator;
    }
}
