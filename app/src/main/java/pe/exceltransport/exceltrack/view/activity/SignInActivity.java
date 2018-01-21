package pe.exceltransport.exceltrack.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import pe.exceltransport.exceltrack.R;
import pe.exceltransport.exceltrack.presenter.SignInPresenter;
import pe.exceltransport.exceltrack.view.SignInView;

public class SignInActivity extends BaseActivity implements SignInView{

    @BindView(R.id.til_email)
    TextInputLayout tilEmail;

    @BindView(R.id.til_password)
    TextInputLayout tilPassword;

    @Inject
    SignInPresenter presenter;

    public static Intent getCallingIntent(BaseActivity activity) {
        return new Intent(activity, SignInActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        injectView(this);
        presenter.setView(this);
        initUI();
    }

    @Override
    protected void initUI() {

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
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @OnClick(R.id.btn_sign_in)
    public void onBtnSignIn(){
        presenter.signIn();
    }

    @Override
    public void showError(String message) {

    }
}
