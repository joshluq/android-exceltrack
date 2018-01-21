package pe.exceltransport.exceltrack.navigator;

import android.support.v4.app.FragmentTransaction;

import javax.inject.Inject;

import pe.exceltransport.exceltrack.view.activity.BaseActivity;
import pe.exceltransport.exceltrack.view.activity.SignInActivity;

public class Navigator {

    private BaseActivity activity;

    @Inject
    public Navigator(BaseActivity activity) {
        this.activity = activity;
    }

    public void navigateToSignInActivity(){
        activity.finish();
        activity.startActivity(SignInActivity.getCallingIntent(activity));
    }

    private void fragmentTransaction(FragmentTransaction transaction){
        if(activity.isStopped()) transaction.commitAllowingStateLoss();
        else transaction.commit();
    }

}
