package com.xxx.wordingtech.ui.shop;

import androidx.lifecycle.MutableLiveData;

import com.xxx.wordingtech.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShopViewModel extends BaseViewModel {

    MutableLiveData<List<String>> classifyListLiveData = new MutableLiveData();

    //拉取商品分类列表
    public void fetchClassifyList() {
        List<String> l = new ArrayList<>();
        l.add("单词书");
        l.add("教材");
        l.add("阅读材料");
        classifyListLiveData.postValue(l);
/*        bindLife(
                apiService.fetchFilmClassifyList()
                        .compose(RxUtil.<ResultModel<ClassifyListModel>>switchThread())
                        .compose(ApiErrorUtil.<ResultModel<ClassifyListModel>>dealError())
                        .doOnSuccess(new Consumer<ResultModel<ClassifyListModel>>() {
                            @Override
                            public void accept(ResultModel<ClassifyListModel> resultModel) {
                                classifyListLiveData.postValue(resultModel.getData().getClassifyList());
                            }
                        })
        );*/
    }

}
