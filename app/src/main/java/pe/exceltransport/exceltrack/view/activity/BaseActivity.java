package pe.exceltransport.exceltrack.view.activity;

import android.app.Activity;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.exceltransport.exceltrack.R;
import pe.exceltransport.exceltrack.navigator.Navigator;
import pe.exceltransport.exceltrack.view.util.KeyboardUtil;

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

    private long lastClickTime = 0;


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

    public void hideKeyboard(){
        KeyboardUtil.hideKeyboard(this);
    }

    public boolean isActionEnable() {
        if (SystemClock.elapsedRealtime() - lastClickTime < 500){
            return false;
        }
        lastClickTime = SystemClock.elapsedRealtime();
        return true;
    }

    public boolean isGooglePlayServicesAvailable() {
        int resultCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        return ConnectionResult.SUCCESS == resultCode;
    }

    public boolean isGpsEnabled() {
        final int locationMode;
        try {
            locationMode = Settings.Secure.getInt(getContentResolver(),
                    Settings.Secure.LOCATION_MODE);
        } catch (Settings.SettingNotFoundException e) {
            return false;
        }
        switch (locationMode) {
            case Settings.Secure.LOCATION_MODE_HIGH_ACCURACY:
            case Settings.Secure.LOCATION_MODE_SENSORS_ONLY:
                return true;
            case Settings.Secure.LOCATION_MODE_BATTERY_SAVING:
            case Settings.Secure.LOCATION_MODE_OFF:
            default:
                return false;
        }
    }
}
