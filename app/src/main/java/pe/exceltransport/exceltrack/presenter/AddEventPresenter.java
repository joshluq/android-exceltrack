package pe.exceltransport.exceltrack.presenter;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import pe.exceltransport.exceltrack.internal.bus.EventBus;
import pe.exceltransport.exceltrack.internal.bus.RxBus;
import pe.exceltransport.exceltrack.view.AddEventView;

public class AddEventPresenter implements Presenter<AddEventView>{

    private AddEventView view;
    private final RxBus rxBus;

    @Inject
    AddEventPresenter(RxBus rxBus) {
        this.rxBus = rxBus;
    }

    @Override
    public void setView(@NonNull AddEventView view) {
        this.view = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    public void sendBus(){
        rxBus.send(new EventBus.UpdateTrackinEvent());
    }
}
