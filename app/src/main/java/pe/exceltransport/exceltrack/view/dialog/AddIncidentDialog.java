package pe.exceltransport.exceltrack.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;
import pe.exceltransport.domain.Tracking;
import pe.exceltransport.exceltrack.R;
import pe.exceltransport.exceltrack.presenter.AddIncidentPresenter;
import pe.exceltransport.exceltrack.view.AddIncidentView;
import pe.exceltransport.exceltrack.view.activity.TripDetailActivity;
import pe.exceltransport.exceltrack.view.util.Extra;
import pe.exceltransport.exceltrack.view.util.TextInputLayoutAdapter;

public class AddIncidentDialog extends BaseDialog implements AddIncidentView, Validator.ValidationListener, Validator.ViewValidatedAction  {

    @NotEmpty(messageResId = R.string.text_invalid_field)
    @BindView(R.id.til_incident_detail)
    TextInputLayout tilIncidentDetail;

    @BindView(R.id.btn_add)
    Button btnAdd;

    @BindView(R.id.v_loading)
    View vLoading;

    @Inject
    AddIncidentPresenter presenter;

    @Inject
    Validator validator;

    private TripDetailActivity activity;

    private long trackingId;

    public static AddIncidentDialog newInstance(long trackingId) {
        AddIncidentDialog dialog = new AddIncidentDialog();
        Bundle arg = new Bundle();
        arg.putLong(Extra.TRACKING_ID.getValue(), trackingId);
        dialog.setArguments(arg);
        return dialog;
    }

    public AddIncidentDialog() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add_incident, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().setTitle(R.string.text_incident_dialog_title);
        injectView(this, view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (TripDetailActivity) getActivity();
        presenter.setView(this);
        getExtras();
        setupValidator();
        initUI();
    }

    @Override
    public long getTrackingId() {
        return trackingId;
    }

    @Override
    public String getIncidentDetail() {
        return tilIncidentDetail.getEditText() != null ? tilIncidentDetail.getEditText().getText().toString().trim() : "";
    }

    @Override
    public void renderTracking(Tracking tracking) {
        presenter.sendBus(tracking);
        dismiss();
    }

    @Override
    public void showLoading() {
        viewVisibility(View.INVISIBLE);
        vLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        viewVisibility(View.VISIBLE);
        vLoading.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        //default implementation
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @OnClick(R.id.btn_add)
    public void onBtnAdd() {
        activity.hideKeyboard();
        validator.validate();
    }

    @Override
    public void onValidationSucceeded() {
        presenter.getCurrentLocation();
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

    private void getExtras() {
        if (getArguments() != null)
            trackingId = getArguments().getLong(Extra.TRACKING_ID.getValue());
    }

    private void viewVisibility(int status){
        btnAdd.setVisibility(status);
        tilIncidentDetail.setVisibility(status);
    }
}
