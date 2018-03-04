package pe.exceltransport.exceltrack.navigator;

import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;

import javax.inject.Inject;

import pe.exceltransport.domain.Trip;
import pe.exceltransport.exceltrack.R;
import pe.exceltransport.exceltrack.view.activity.BaseActivity;
import pe.exceltransport.exceltrack.view.activity.MainActivity;
import pe.exceltransport.exceltrack.view.activity.SignInActivity;
import pe.exceltransport.exceltrack.view.activity.TripDetailActivity;
import pe.exceltransport.exceltrack.view.dialog.AddIncidentDialog;
import pe.exceltransport.exceltrack.view.fragment.MoreFragment;
import pe.exceltransport.exceltrack.view.fragment.SignInFragment;
import pe.exceltransport.exceltrack.view.fragment.SplashFragment;
import pe.exceltransport.exceltrack.view.fragment.TripListFragment;

public class Navigator {

    private BaseActivity activity;

    @Inject
    public Navigator(BaseActivity activity) {
        this.activity = activity;
    }

    //navigate to activities
    public void navigateToSignInActivity() {
        activity.finish();
        activity.startActivity(SignInActivity.getCallingIntent(activity));
    }

    public void navigateToMainActivity() {
        activity.finish();
        activity.startActivity(MainActivity.getCallingIntent(activity));
    }

    public void navigateToTripDetailActivity(Trip trip) {
        activity.startActivity(TripDetailActivity.getCallingIntent(activity, trip));
    }

    //navigate to fragments
    public void navigateToSplashFragment() {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, SplashFragment.newInstance(), SplashFragment.class.getSimpleName());
        fragmentTransaction(transaction);
    }

    public void navigateToSignInFragment() {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, SignInFragment.newInstance(), SignInFragment.class.getSimpleName());
        fragmentTransaction(transaction);
    }


    public void navigateToTripListFragment() {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, TripListFragment.newInstance(), TripListFragment.class.getSimpleName());
        fragmentTransaction(transaction);
    }

    public void navigateToMoreFragment() {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, MoreFragment.newInstance(), MoreFragment.class.getSimpleName());
        fragmentTransaction(transaction);
    }


    //show dialogs
    public void showAlertDialog(String title, String message, String positiveButtonText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(positiveButtonText, (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }

    public void showAlertDialog(String title, String message, String positiveButtonText, DialogInterface.OnClickListener positiveAction) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton(R.string.text_negative_button, (dialog, which) -> dialog.dismiss())
                .setPositiveButton(positiveButtonText, positiveAction);
        builder.create().show();
    }

    public void showAddIncidentDialog(long trackingId){
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        AddIncidentDialog.newInstance(trackingId).show(fragmentManager, AddIncidentDialog.class.getSimpleName());
    }

    private void fragmentTransaction(FragmentTransaction transaction) {
        if (activity.isStopped()) transaction.commitAllowingStateLoss();
        else transaction.commit();
    }

}
