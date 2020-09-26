package com.xxx.wordingtech.ui.base.commonlist;

import androidx.lifecycle.MutableLiveData;
import com.xxx.wordingtech.model.ResultModel;
import com.xxx.wordingtech.model.commonlist.CommonListPageModel;
import com.xxx.wordingtech.ui.base.BaseViewModel;
import com.xxx.wordingtech.util.ApiErrorUtil;
import com.xxx.wordingtech.util.RxUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import io.reactivex.Single;
import io.reactivex.functions.Function3;

public abstract class CommonListViewModel<T> extends BaseViewModel {
    MutableLiveData<CommonListPageModel<T>> commonListPageModelLiveData = new MutableLiveData<>();
    protected MutableLiveData<List<T>> commonListLiveData = new MutableLiveData<>(new ArrayList<>());
    private int currentPageNum = 1;
    MutableLiveData<Boolean> isLoadingMore = new MutableLiveData<>(false);
    MutableLiveData<Boolean> isRefreshing = new MutableLiveData<>(false);

    public abstract Function3<String, Integer, Integer, Single<ResultModel<CommonListPageModel<T>>>> getRefreshFunction();
    public Function3<String, Integer, Integer, Single<ResultModel<CommonListPageModel<T>>>> getSearchFunction(){
        return null;
    }

    //刷新商品列表
    public void refreshList(String classify) {
        try {
            bindLife(
                    getRefreshFunction().apply(classify, 1, 10)
                            .compose(RxUtil.switchThread())
                            .compose(ApiErrorUtil.dealError())
                            .doOnSubscribe(disposable -> isRefreshing.postValue(true))
                            .doOnSuccess(filmList -> {
                                currentPageNum = 1;
                                commonListPageModelLiveData.postValue(filmList.getData());
                                commonListLiveData.postValue(Objects.requireNonNull(commonListPageModelLiveData.getValue()).getDataList());
                            })
                            .doFinally(() -> isRefreshing.postValue(false))
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //搜索商品列表
    public void search(String classify) {
        try {
            bindLife(
                    getSearchFunction().apply(classify, 1, 10)
                            .compose(RxUtil.switchThread())
                            .compose(ApiErrorUtil.dealError())
                            .doOnSubscribe(disposable -> isRefreshing.postValue(true))
                            .doOnSuccess(filmList -> {
                                currentPageNum = 1;
                                commonListPageModelLiveData.postValue(filmList.getData());
                                commonListLiveData.postValue(Objects.requireNonNull(commonListPageModelLiveData.getValue()).getDataList());
                            })
                            .doFinally(() -> isRefreshing.postValue(false))
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //加载更多
    public void loadMoreShopList(String classify) {
        final int targetPageNum = currentPageNum + 1;
        try {
            bindLife(
                    getRefreshFunction().apply(classify, targetPageNum, 10)
                            .compose(RxUtil.switchThread())
                            .compose(ApiErrorUtil.dealError())
                            .compose(this.autoProgressDialog())
                            .doOnSubscribe(disposable -> isLoadingMore.postValue(true))
                            .doOnSuccess(commonListPageModel -> {
                                commonListPageModelLiveData.postValue(commonListPageModel.getData());
                                List<T> newList = commonListLiveData.getValue();
                                assert newList != null;
                                newList.addAll(commonListPageModel.getData().getDataList());
                                commonListLiveData.postValue(newList);
                                currentPageNum = commonListPageModel.getData().getPageNum();
                            })
                            .doFinally(() -> isLoadingMore.postValue(false))
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
