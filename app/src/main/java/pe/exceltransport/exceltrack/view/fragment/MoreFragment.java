package pe.exceltransport.exceltrack.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;
import pe.exceltransport.exceltrack.R;
import pe.exceltransport.exceltrack.presenter.MorePresenter;
import pe.exceltransport.exceltrack.view.MoreView;
import pe.exceltransport.exceltrack.view.activity.MainActivity;

public class MoreFragment extends BaseFragment implements MoreView {

    @Inject
    MorePresenter presenter;

    private MainActivity activity;

    public static MoreFragment newInstance(){
        return new MoreFragment();
    }

    public MoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_more, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        injectView(this,view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (MainActivity) getActivity();
        presenter.setView(this);
        initUI();
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    protected void initUI() {
        setupToolbar();
    }

    @OnClick(R.id.v_sign_out)
    public void onSignOut(){
        presenter.signOut();
    }

    @Override
    public void goToSignIn() {
        activity.getNavigator().navigateToSignInActivity();
    }

    private void setupToolbar(){
        activity.setToolbarTitle("Más opciones");
    }
}
