package pe.exceltransport.exceltrack.navigator;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;

import javax.inject.Inject;

import pe.exceltransport.exceltrack.view.activity.BaseActivity;
import pe.exceltransport.exceltrack.view.activity.MainActivity;
import pe.exceltransport.exceltrack.view.activity.SignInActivity;

public class Navigator {

    private BaseActivity activity;

    @Inject
    public Navigator(BaseActivity activity) {
        this.activity = activity;
    }

    public void navigateToSignInActivity() {
        activity.finish();
        activity.startActivity(SignInActivity.getCallingIntent(activity));
    }

    public void navigateToMainActivity() {
        activity.finish();
        activity.startActivity(MainActivity.getCallingIntent(activity));
    }

    public void showAlertDialog(String title, String message, String positiveButtonText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(positiveButtonText, (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }

    private void fragmentTransaction(FragmentTransaction transaction) {
        if (activity.isStopped()) transaction.commitAllowingStateLoss();
        else transaction.commit();
    }

}
