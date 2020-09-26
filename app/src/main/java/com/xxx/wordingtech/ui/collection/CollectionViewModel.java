package com.xxx.wordingtech.ui.collection;

import androidx.lifecycle.MutableLiveData;

import com.xxx.wordingtech.model.shop.Shop;
import com.xxx.wordingtech.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class CollectionViewModel extends BaseViewModel {


    public MutableLiveData<String> commentContentLiveData = new MutableLiveData<>("");
    public MutableLiveData<Shop> shopLiveData = new MutableLiveData<>();



}
