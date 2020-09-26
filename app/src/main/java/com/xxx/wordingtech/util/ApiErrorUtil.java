package com.xxx.wordingtech.util;

import android.util.Log;
import com.xxx.wordingtech.MainApplication;
import com.xxx.wordingtech.manager.api.base.ApiException;
import com.xxx.wordingtech.manager.api.base.ServerException;
import io.reactivex.SingleTransformer;

public class ApiErrorUtil {

    public static <T> SingleTransformer<T, T> dealError() {
        return upstream -> upstream.doOnError(throwable -> {
            if (throwable instanceof ApiException) {
                MainApplication.getApplication().showToast(((ApiException) throwable).getErrorMsg());
            } else if (throwable instanceof ServerException) {
                MainApplication.getApplication().showToast(((ServerException) throwable).getErrorMsg());
            } else {
                Log.d("RxThrowable", throwable.getMessage());
            }
        });
    }
}
