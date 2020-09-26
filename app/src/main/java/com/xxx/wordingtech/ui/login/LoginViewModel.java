package com.xxx.wordingtech.ui.login;

import androidx.lifecycle.MutableLiveData;
import com.orhanobut.hawk.Hawk;
import com.xxx.wordingtech.model.login.LoginRequestModel;
import com.xxx.wordingtech.ui.base.BaseViewModel;
import com.xxx.wordingtech.util.ApiErrorUtil;
import com.xxx.wordingtech.util.RxUtil;
import io.reactivex.functions.Action;
import static com.xxx.wordingtech.Constants.KEY_ACCOUNT;
import static com.xxx.wordingtech.Constants.KEY_PASSWORD;
import static com.xxx.wordingtech.Constants.KEY_TOKEN;
import static com.xxx.wordingtech.Constants.KEY_UID;

public class LoginViewModel extends BaseViewModel {

    //MutableLiveData<Boolean> isLoginSuccess = new MutableLiveData<>(false);
    public MutableLiveData<String> account = new MutableLiveData<>(Hawk.get(KEY_ACCOUNT));
    public MutableLiveData<String> password = new MutableLiveData<>(Hawk.get(KEY_PASSWORD));

    void login(Action action) {
        bindLife(
                apiService.login(new LoginRequestModel(account.getValue(), password.getValue()))
                        .compose(RxUtil.switchThread())
                        .compose(ApiErrorUtil.dealError())
                        .compose(this.autoProgressDialog())
                        .doOnSuccess(loginResultModel -> {
                            //isLoginSuccess.postValue(true);
                            Hawk.put(KEY_TOKEN, loginResultModel.getData().getToken());
                            Hawk.put(KEY_UID, loginResultModel.getData().getUid());
                            Hawk.put(KEY_ACCOUNT, account.getValue());
                            Hawk.put(KEY_PASSWORD, password.getValue());
                            action.run();
                        })
        );
    }

}
