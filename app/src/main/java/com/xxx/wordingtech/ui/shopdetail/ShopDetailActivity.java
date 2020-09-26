package com.xxx.wordingtech.ui.shopdetail;

import com.xxx.wordingtech.R;
import com.xxx.wordingtech.databinding.ActivityShopDetailBinding;
import com.xxx.wordingtech.ui.base.BaseActivity;

public class ShopDetailActivity extends BaseActivity<ActivityShopDetailBinding, ShopDetailViewModel> {

    public static final String KEY_MID = "key_mid";
    public static final String KEY_NAME = "key_name";
    public static final String KEY_BRIEF = "key_brief";
    public static final String KEY_DIRECTOR = "key_director";
    public static final String KEY_PRICE = "key_price";
    public static final String KEY_IMAGE = "key_image";

    long mId;
    String name;
    String brief;
    String director;
    String price;
    String image;


    @Override
    protected Class<ShopDetailViewModel> getViewModelClazz() {
        return ShopDetailViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_shop_detail;
    }

    @Override
    protected void initView() {
        //得到传进来的mid
        mId = getIntent().getLongExtra(KEY_MID,0);
        name = getIntent().getStringExtra(KEY_NAME);
        brief = getIntent().getStringExtra(KEY_BRIEF);
        director = getIntent().getStringExtra(KEY_DIRECTOR);
        price = getIntent().getStringExtra(KEY_PRICE);
        image = getIntent().getStringExtra(KEY_IMAGE);
        binding.btnSubmit.setOnClickListener(v -> {

        });
    }

    @Override
    protected void initData() {
        //获取电影详情信息
        viewModel.fetchShopDetailInfo(
                mId,name,brief,director,price,image
        );
    }
}