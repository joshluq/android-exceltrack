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

public class AddEventDialog extends BaseDialog implements AddEventView{

    public static AddEventDialog newInstance() {
        return new AddEventDialog();
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
        injectView(this,view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.setView(this);
    }

    @Override
    public void renderTracking(Tracking tracking) {

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
    public void onBtnAdd(){
        presenter.sendBus();
    }
}
