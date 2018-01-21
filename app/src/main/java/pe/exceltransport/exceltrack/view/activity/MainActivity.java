package pe.exceltransport.exceltrack.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import butterknife.BindView;
import dagger.android.AndroidInjection;
import pe.exceltransport.exceltrack.R;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;

    private int currentMenuItemId;

    public static Intent getCallingIntent(BaseActivity activity) {
        return new Intent(activity, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        injectView(this);
        initUI();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initUI() {
        bottomNavigation.setOnNavigationItemSelectedListener(this);
        bottomNavigation.setSelectedItemId(R.id.action_trip_list);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(currentMenuItemId == item.getItemId()){
            return false;
        }
        currentMenuItemId = item.getItemId();
        return navigateToFragments(item.getItemId());
    }

    private boolean navigateToFragments(int itemId){
        switch (itemId) {
            case R.id.action_trip_list:
                navigator.navigateToTripListFragment();
                return true;
            case R.id.action_more:
                navigator.navigateToMoreFragment();
                return true;
            default:
                return false;
        }
    }
}
