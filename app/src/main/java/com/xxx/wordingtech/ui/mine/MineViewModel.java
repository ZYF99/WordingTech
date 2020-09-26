package com.xxx.wordingtech.ui.mine;

import androidx.lifecycle.MutableLiveData;

import com.xxx.wordingtech.model.feedback.FeedbackRequestModel;
import com.xxx.wordingtech.model.mine.UserProfile;
import com.xxx.wordingtech.ui.base.BaseViewModel;
import com.xxx.wordingtech.util.ApiErrorUtil;
import com.xxx.wordingtech.util.RxUtil;

public class MineViewModel extends BaseViewModel {

    public MutableLiveData<UserProfile> userProfileLiveData = new MutableLiveData(new UserProfile(
            "","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1600495633056&di=168b132b9e70fc1f7e6517d74c0ba87f&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fw%3D580%2Fsign%3D192ab888f01f4134e0370576151e95c1%2F51da81cb39dbb6fded52c04d0424ab18972b374b.jpg","https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1600485526&di=22bf3dc4b1839ee7bb48aab77ad861c3&src=http://ku.90sjimg.com/back_pic/03/72/08/1557b88ef37a19b.jpg",
            1600485456L,1600485456L,"M","哈哈哈","user","我就是我，颜色不一样的烟火",1,888888,1600485456L
    ));

    //拉取我的个人信息
    public void fetchUserProfile() {
        bindLife(
                apiService.getUserProfile()
                        .compose(RxUtil.switchThread())
                        .compose(ApiErrorUtil.dealError())
                        .doOnSuccess(userProfile -> userProfileLiveData.postValue(userProfile.getData()))
        );
    }

    //提交意见
    public void uploadAdvance(String s) {
        bindLife(
                apiService.uploadAdvance(new FeedbackRequestModel(s))
                        .compose(RxUtil.switchThread())
                        .compose(ApiErrorUtil.dealError())
        );
    }

}
