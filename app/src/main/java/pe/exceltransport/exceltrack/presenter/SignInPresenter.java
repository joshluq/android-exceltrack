package pe.exceltransport.exceltrack.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import pe.exceltransport.data.exception.DefaultException;
import pe.exceltransport.domain.User;
import pe.exceltransport.domain.interactor.DefaultObserver;
import pe.exceltransport.domain.interactor.SignIn;
import pe.exceltransport.exceltrack.exception.ErrorMessageFactory;
import pe.exceltransport.exceltrack.view.SignInView;

public class SignInPresenter implements Presenter<SignInView> {

    private SignInView view;

    private final SignIn signIn;
    private final Context context;

    @Inject
    SignInPresenter(Context context, SignIn signIn) {
        this.context = context;
        this.signIn = signIn;
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
    }

    public void signIn() {
        signIn.execute(new signInObserver(), SignIn.Params.buildParams(view.getEmail(), view.getPassword()));
    }

    private final class signInObserver extends DefaultObserver<User> {

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
        public void onNext(User user) {
            super.onNext(user);
        }

        @Override
        public void onError(Throwable exception) {
            super.onError(exception);
            view.hideLoading();
            view.showError(ErrorMessageFactory.create(context,(DefaultException) exception));
        }
    }
}
