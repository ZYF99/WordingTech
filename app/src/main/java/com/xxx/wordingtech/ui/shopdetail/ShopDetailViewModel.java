package com.xxx.wordingtech.ui.shopdetail;

import androidx.lifecycle.MutableLiveData;

import com.xxx.wordingtech.model.shop.Shop;
import com.xxx.wordingtech.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShopDetailViewModel extends BaseViewModel {


    public MutableLiveData<String> commentContentLiveData = new MutableLiveData<>("");
    public MutableLiveData<Shop> shopLiveData = new MutableLiveData<>();

    //获取商品详细信息
    public void fetchShopDetailInfo(
            long id,
            String name,
            String brief,
            String director,
            String price,
            String image
    ) {
        shopLiveData.postValue(new Shop(
                id,
                name,
                director,
                "",
                brief,
                image,
                price

        ));
    }

}
