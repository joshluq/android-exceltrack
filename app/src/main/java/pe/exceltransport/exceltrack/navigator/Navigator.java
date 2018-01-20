package pe.exceltransport.exceltrack.navigator;

import android.support.v4.app.FragmentTransaction;

import javax.inject.Inject;

import pe.exceltransport.exceltrack.view.activity.BaseActivity;

public class Navigator {

    private BaseActivity activity;

    @Inject
    public Navigator(BaseActivity activity) {
        this.activity = activity;
    }

    private void fragmentTransaction(FragmentTransaction transaction){
        if(activity.isStopped()) transaction.commitAllowingStateLoss();
        else transaction.commit();
    }

}
