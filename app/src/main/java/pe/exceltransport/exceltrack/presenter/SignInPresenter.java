package pe.exceltransport.exceltrack.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import pe.exceltransport.data.exception.DefaultException;
import pe.exceltransport.domain.Session;
import pe.exceltransport.domain.User;
import pe.exceltransport.domain.interactor.DefaultObserver;
import pe.exceltransport.domain.interactor.GetEmailSaved;
import pe.exceltransport.domain.interactor.SaveEmail;
import pe.exceltransport.domain.interactor.SaveSession;
import pe.exceltransport.domain.interactor.SignIn;
import pe.exceltransport.exceltrack.exception.ErrorMessageFactory;
import pe.exceltransport.exceltrack.view.SignInView;

public class SignInPresenter implements Presenter<SignInView> {

    private SignInView view;

    private final SignIn signIn;
    private final SaveEmail saveEmail;
    private final GetEmailSaved getEmailSaved;
    private final SaveSession saveSession;
    private final Context context;

    @Inject
    SignInPresenter(Context context, SignIn signIn, SaveEmail saveEmail, GetEmailSaved getEmailSaved, SaveSession saveSession) {
        this.context = context;
        this.signIn = signIn;
        this.saveSession = saveSession;
        this.saveEmail = saveEmail;
        this.getEmailSaved = getEmailSaved;
    }

    @Override
    public void setView(@NonNull SignInView view) {
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
        signIn.dispose();
        saveSession.dispose();
        saveEmail.dispose();
        getEmailSaved.dispose();
    }

    public void GetEmailSaved() {
        getEmailSaved.execute(new GetEmailSavedObserver(),null);
    }

    public void signIn() {
        signIn.execute(new SignInObserver(), SignIn.Params.buildParams(view.getEmail(), view.getPassword()));
    }

    private void saveSession(Session session) {
        saveSession.execute(new SaveSessionObserver(), SaveSession.Params.buildParams(session));
    }

    private void saveEmail() {
        saveEmail.execute(new SaveEmailObserver(), SaveEmail.Params.buildParams(view.getEmail()));
    }

    private void deleteEmail() {
        saveEmail.execute(new SaveEmailObserver(), SaveEmail.Params.buildParams(""));
    }

    private final class GetEmailSavedObserver extends DefaultObserver<String> {

        @Override
        public void onNext(String email) {
            super.onNext(email);
            view.setEmail(email);
        }
    }

    private final class SignInObserver extends DefaultObserver<Session> {

        @Override
        protected void onStart() {
            super.onStart();
            view.showLoading();
        }

        @Override
        public void onComplete() {
            super.onComplete();
            view.hideLoading();
        }

        @Override
        public void onNext(Session session) {
            super.onNext(session);
            saveSession(session);
        }

        @Override
        public void onError(Throwable exception) {
            super.onError(exception);
            view.hideLoading();
            view.showError(ErrorMessageFactory.create(context, (DefaultException) exception));
        }
    }

    private final class SaveSessionObserver extends DefaultObserver<Void> {

        @Override
        public void onComplete() {
            super.onComplete();
            if (view.isCheckedRemember()) {
                saveEmail();
            } else {
                deleteEmail();
            }
        }
    }

    private final class SaveEmailObserver extends DefaultObserver<Void> {

        @Override
        public void onComplete() {
            super.onComplete();
            view.goToMainActivity();
        }
    }


}
