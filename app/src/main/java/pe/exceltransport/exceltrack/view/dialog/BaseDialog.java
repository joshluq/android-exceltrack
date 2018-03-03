package pe.exceltransport.exceltrack.view.dialog;


import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;

public abstract class BaseDialog extends DialogFragment {

    protected void injectView(Fragment fragment, View view) {
        ButterKnife.bind(fragment, view);
    }

    protected void initUI(){}

}
