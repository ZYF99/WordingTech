package com.xxx.wordingtech.ui.register;

import androidx.lifecycle.MutableLiveData;
import com.orhanobut.hawk.Hawk;
import com.xxx.wordingtech.model.register.RegisterRequestModel;
import com.xxx.wordingtech.ui.base.BaseViewModel;
import com.xxx.wordingtech.util.ApiErrorUtil;
import com.xxx.wordingtech.util.RxUtil;
import io.reactivex.functions.Action;
import static com.xxx.wordingtech.Constants.KEY_ACCOUNT;
import static com.xxx.wordingtech.Constants.KEY_PASSWORD;
import static com.xxx.wordingtech.Constants.KEY_TOKEN;
import static com.xxx.wordingtech.Constants.KEY_UID;

public class RegisterViewModel extends BaseViewModel {

    //MutableLiveData<Boolean> isRegisterSuccess = new MutableLiveData<>(false);
    public MutableLiveData<String> nickName = new MutableLiveData<>("");
    public MutableLiveData<String> account = new MutableLiveData<>("");
    public MutableLiveData<String> password = new MutableLiveData<>("");
    public MutableLiveData<String> sex = new MutableLiveData<>("M");

    void registerAndLogin(Action action) {
        bindLife(
                apiService.register(new RegisterRequestModel(
                        account.getValue(),
                        "https://w.wallhaven.cc/full/39/wallhaven-39pw6v.jpg",
                        sex.getValue(),
                        nickName.getValue(),
                        password.getValue(),
                        "User"
                )).compose(RxUtil.switchThread())
                        .compose(ApiErrorUtil.dealError())
                        .compose(autoProgressDialog())
                        .doOnSuccess(registerResultModelResultModel -> {
                            Hawk.put(KEY_TOKEN, registerResultModelResultModel.getData().getToken());
                            Hawk.put(KEY_UID, registerResultModelResultModel.getData().getUid());
                            Hawk.put(KEY_ACCOUNT, account.getValue());
                            Hawk.put(KEY_PASSWORD, password.getValue());
                            action.run();
                        })
        );
    }

}
