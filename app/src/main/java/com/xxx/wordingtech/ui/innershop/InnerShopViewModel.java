package com.xxx.wordingtech.ui.innershop;

import com.xxx.wordingtech.model.ResultModel;
import com.xxx.wordingtech.model.commonlist.CommonListPageModel;
import com.xxx.wordingtech.model.listen.Sentence;
import com.xxx.wordingtech.model.shop.Shop;
import com.xxx.wordingtech.ui.base.commonlist.CommonListViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;

public class InnerShopViewModel extends CommonListViewModel<Shop> {

    @Override
    public Function3<String, Integer, Integer, Single<ResultModel<CommonListPageModel<Shop>>>> getRefreshFunction() {
        return (s, no, pageSize) -> apiService.fetchShopListByClassify(s, no,pageSize)
                .doOnSuccess(new Consumer<ResultModel<CommonListPageModel<Shop>>>() {
                    @Override
                    public void accept(ResultModel<CommonListPageModel<Shop>> commonListPageModelResultModel) throws Exception {
                        commonListLiveData.postValue(commonListPageModelResultModel.getData().getDataList());
                    }
                });
    }


    @Override
    public Function3<String, Integer, Integer, Single<ResultModel<CommonListPageModel<Shop>>>> getSearchFunction() {
        return (s, no, pageSize) -> apiService.fetchShopListByClassify(s, no,pageSize)
                .doOnSuccess(new Consumer<ResultModel<CommonListPageModel<Shop>>>() {
                    @Override
                    public void accept(ResultModel<CommonListPageModel<Shop>> commonListPageModelResultModel) throws Exception {
                        commonListLiveData.postValue(commonListPageModelResultModel.getData().getDataList());
                    }
                });
    }
}


