package pe.exceltransport.exceltrack.presenter;


import android.support.annotation.NonNull;

import javax.inject.Inject;

import pe.exceltransport.domain.interactor.DefaultObserver;
import pe.exceltransport.domain.interactor.DeleteSessionSaved;
import pe.exceltransport.exceltrack.view.MoreView;

public class MorePresenter implements Presenter<MoreView> {

    private MoreView view;

    private final DeleteSessionSaved deleteSessionSaved;

    @Inject
    public MorePresenter(DeleteSessionSaved deleteSessionSaved) {
        this.deleteSessionSaved = deleteSessionSaved;
    }

    @Override
    public void setView(@NonNull MoreView view) {
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
        view = null;
        deleteSessionSaved.dispose();
    }

    public void signOut(){
        deleteSessionSaved.execute(new SaveSessionObserver(),null);
    }

    private final class SaveSessionObserver extends DefaultObserver<Void> {

        @Override
        public void onComplete() {
            super.onComplete();
            view.goToSignIn();
        }
    }
}
