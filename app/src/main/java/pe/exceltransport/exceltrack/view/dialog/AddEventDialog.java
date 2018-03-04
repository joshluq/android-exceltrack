package pe.exceltransport.exceltrack.view.dialog;

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
import pe.exceltransport.domain.Tracking;
import pe.exceltransport.exceltrack.R;
import pe.exceltransport.exceltrack.presenter.AddEventPresenter;
import pe.exceltransport.exceltrack.view.AddEventView;
import pe.exceltransport.exceltrack.view.util.Extra;

public class AddEventDialog extends BaseDialog implements AddEventView {

    private long trackingId;

    public static AddEventDialog newInstance(long trackingId) {
        AddEventDialog dialog = new AddEventDialog();
        Bundle arg = new Bundle();
        arg.putLong(Extra.TRACKING_ID.getValue(), trackingId);
        dialog.setArguments(arg);
        return dialog;
    }

    @Inject
    AddEventPresenter presenter;

    public AddEventDialog() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add_event, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        injectView(this, view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.setView(this);
        getExtras();
    }

    @Override
    public long getTrackingId() {
        return trackingId;
    }

    @Override
    public String getIncidentDetail() {
        return null;
    }

    @Override
    public void renderTracking(Tracking tracking) {
        presenter.sendBus(tracking);
        dismiss();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @OnClick(R.id.btn_add)
    public void onBtnAdd() {
        presenter.getCurrentLocation();
    }

    private void getExtras() {
        if (getArguments() != null)
            trackingId = getArguments().getLong(Extra.TRACKING_ID.getValue());
    }
}
