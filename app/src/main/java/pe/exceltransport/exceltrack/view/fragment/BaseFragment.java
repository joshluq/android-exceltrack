package pe.exceltransport.exceltrack.view.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    protected void injectView(Fragment fragment,View view) {
        ButterKnife.bind(fragment, view);
    }

    protected void initUI(){}

}
