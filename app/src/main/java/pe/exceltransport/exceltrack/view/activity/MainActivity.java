package pe.exceltransport.exceltrack.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import pe.exceltransport.exceltrack.R;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener, HasSupportFragmentInjector {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

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


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
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
