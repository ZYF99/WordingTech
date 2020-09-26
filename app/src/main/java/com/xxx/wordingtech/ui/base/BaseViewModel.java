package com.xxx.wordingtech.ui.base;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.xxx.wordingtech.MainApplication;
import com.xxx.wordingtech.manager.api.ApiService;
import javax.inject.Inject;
import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import io.reactivex.functions.Action;

public class BaseViewModel extends ViewModel implements BindLife, LifecycleObserver {

    @Inject
    public ApiService apiService;

    protected MutableLiveData<Boolean> isShowLoadingProgress = new MutableLiveData(false);

    public BaseViewModel() {
        MainApplication.getApplication().appComponent.inject(this);
    }

    protected <T> void bindLife(Single<T> single) {
        compositeDisposable.add(
                single.subscribe(t -> {

                }, throwable -> {
                    //Log.e("RxThrowable", throwable.getMessage());
                })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    public void onDestroy() {
        compositeDisposable.clear();
    }

    protected <T> SingleTransformer<T, T> autoProgressDialog() {
        return upstream -> upstream.doOnSubscribe(disposable -> isShowLoadingProgress.postValue(true)).doFinally((Action) () -> isShowLoadingProgress.postValue(false));
    }
}
