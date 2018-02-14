package pe.exceltransport.exceltrack.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;
import pe.exceltransport.exceltrack.R;
import pe.exceltransport.exceltrack.presenter.SignInPresenter;
import pe.exceltransport.exceltrack.view.SignInView;
import pe.exceltransport.exceltrack.view.activity.SignInActivity;

public class SignInFragment extends BaseFragment implements SignInView{

    @BindView(R.id.til_email)
    TextInputLayout tilEmail;

    @BindView(R.id.til_password)
    TextInputLayout tilPassword;

    @BindView(R.id.cb_remember)
    AppCompatCheckBox cbRemember;

    @Inject
    SignInPresenter presenter;

    private SignInActivity activity;

    public static SignInFragment newInstance(){
        return new SignInFragment();
    }

    public SignInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
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
        initUI();
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    protected void initUI() {
        presenter.GetEmailSaved();
    }

    @Override
    public String getEmail() {
        return tilEmail.getEditText() != null ? tilEmail.getEditText().getText().toString().trim() : "";
    }

    @Override
    public String getPassword() {
        return tilPassword.getEditText() != null ? tilPassword.getEditText().getText().toString().trim() : "";
    }

    @Override
    public boolean isCheckedRemember() {
        return cbRemember.isChecked();
    }

    @Override
    public void setEmail(String email) {
        if(tilEmail.getEditText() != null) tilEmail.getEditText().setText(email);
        cbRemember.setChecked(!email.isEmpty());
    }

    @Override
    public void goToMain() {
        activity.getNavigator().navigateToMainActivity();
    }


    @OnClick(R.id.btn_sign_in)
    public void onBtnSignIn(){
        presenter.signIn();
    }

    @Override
    public void showLoading() {
        activity.showLoading();
    }

    @Override
    public void hideLoading() {
        activity.hideLoading();
    }

    @Override
    public void showError(String message) {
        activity.getNavigator().showAlertDialog(getString(R.string.text_error),message,getString(R.string.text_got_it));
    }
}
