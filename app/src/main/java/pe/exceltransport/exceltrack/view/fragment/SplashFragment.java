package pe.exceltransport.exceltrack.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import pe.exceltransport.exceltrack.R;
import pe.exceltransport.exceltrack.presenter.SplashPresenter;
import pe.exceltransport.exceltrack.view.SplashView;
import pe.exceltransport.exceltrack.view.activity.SignInActivity;
import pe.exceltransport.exceltrack.view.util.PermissionUtil;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

public class SplashFragment extends BaseFragment implements SplashView{

    @Inject
    SplashPresenter presenter;

    private SignInActivity activity;

    public static SplashFragment newInstance(){
        return new SplashFragment();
    }

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        injectView(this,view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (SignInActivity) getActivity();
        presenter.setView(this);
        if(activity.isGooglePlayServicesAvailable()){
            requiresPermission();
        }

    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    protected void initUI() {
        presenter.fetchData();
    }

    @Override
    public void goToSignIn() {
        activity.getNavigator().navigateToSignInFragment();
    }

    @Override
    public void goToMain() {
        activity.getNavigator().navigateToMainActivity();
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(PermissionUtil.LOCATION)
    private void requiresPermission() {
        String[] perms = {PermissionUtil.Permission.LOCATION.getPerm()};
        if (EasyPermissions.hasPermissions(activity, perms)) {
            initUI();
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(
                    new PermissionRequest.Builder(this, PermissionUtil.Permission.LOCATION.getCode(), perms)
                            .setRationale(R.string.location_rationale)
                            .setPositiveButtonText(R.string.rationale_ask_ok)
                            .build());
        }
    }

}
