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
import android.view.inputmethod.EditorInfo;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import dagger.android.support.AndroidSupportInjection;
import pe.exceltransport.exceltrack.R;
import pe.exceltransport.exceltrack.presenter.SignInPresenter;
import pe.exceltransport.exceltrack.view.SignInView;
import pe.exceltransport.exceltrack.view.activity.SignInActivity;
import pe.exceltransport.exceltrack.view.util.TextInputLayoutAdapter;

public class SignInFragment extends BaseFragment implements SignInView, Validator.ValidationListener, Validator.ViewValidatedAction {

    @Email(messageResId = R.string.text_invalid_field)
    @BindView(R.id.til_email)
    TextInputLayout tilEmail;

    @Password(scheme = Password.Scheme.ALPHA_NUMERIC, messageResId = R.string.text_invalid_field)
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;

    @BindView(R.id.cb_remember)
    AppCompatCheckBox cbRemember;

    @Inject
    SignInPresenter presenter;

    @Inject
    Validator validator;

    private SignInActivity activity;

    public static SignInFragment newInstance() {
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
        injectView(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (SignInActivity) getActivity();
        presenter.setView(this);
        setupValidator();
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
        if (tilEmail.getEditText() != null) tilEmail.getEditText().setText(email);
        cbRemember.setChecked(!email.isEmpty());
    }

    @Override
    public void goToMain() {
        activity.getNavigator().navigateToMainActivity();
    }


    @OnClick(R.id.btn_sign_in)
    public void onBtnSignIn() {
        activity.hideKeyboard();
        validator.validate();
    }

    @OnEditorAction(R.id.et_password)
    protected boolean onEtPasswordActionDone(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            onBtnSignIn();
            return true;
        }
        return false;
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
        activity.getNavigator().showAlertDialog(getString(R.string.text_error), message, getString(R.string.text_got_it));
    }

    @Override
    public void onValidationSucceeded() {
        presenter.signIn();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        errors.get(0).getView().requestFocus();
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(activity);
            if (view instanceof TextInputLayout) {
                ((TextInputLayout) view).setError(message);
                ((TextInputLayout) view).setErrorEnabled(true);
            }
        }
    }

    @Override
    public void onAllRulesPassed(View view) {
        if (view instanceof TextInputLayout) {
            ((TextInputLayout) view).setError("");
            ((TextInputLayout) view).setErrorEnabled(false);
        }
    }

    private void setupValidator() {
        validator.setValidationListener(this);
        validator.registerAdapter(TextInputLayout.class, new TextInputLayoutAdapter());
        validator.setViewValidatedAction(this);
    }

}
