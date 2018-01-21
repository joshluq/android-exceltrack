package pe.exceltransport.exceltrack.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatCheckBox;

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

    @BindView(R.id.cb_remember)
    AppCompatCheckBox cbRemember;

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
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
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
    }

    @Override
    public void goToMainActivity() {
        navigator.navigateToMainActivity();
    }

    @Override
    public void showLoading() {
        super.showLoading();
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
    }

    @OnClick(R.id.btn_sign_in)
    public void onBtnSignIn(){
        presenter.signIn();
    }

    @Override
    public void showError(String message) {
        navigator.showAlertDialog(getString(R.string.text_error),message,getString(R.string.text_got_it));
    }

}
