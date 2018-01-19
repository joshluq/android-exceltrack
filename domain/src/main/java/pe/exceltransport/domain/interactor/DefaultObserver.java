package pe.exceltransport.domain.interactor;

import io.reactivex.observers.DisposableObserver;

public class DefaultObserver<T> extends DisposableObserver<T> {

    @Override
    public void onNext(T t) {
        // default implementation
    }

    @Override
    public void onComplete() {
        // default implementation
    }

    @Override
    public void onError(Throwable exception) {
        // default implementation
    }

}
