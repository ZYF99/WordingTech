package com.xxx.wordingtech.ui.innershop;

import android.content.Intent;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xxx.wordingtech.R;
import com.xxx.wordingtech.databinding.ItemShopBinding;
import com.xxx.wordingtech.model.shop.Shop;
import com.xxx.wordingtech.ui.base.commonlist.CommonListFragment;
import com.xxx.wordingtech.ui.shopdetail.ShopDetailActivity;

import static com.xxx.wordingtech.ui.shopdetail.ShopDetailActivity.KEY_BRIEF;
import static com.xxx.wordingtech.ui.shopdetail.ShopDetailActivity.KEY_DIRECTOR;
import static com.xxx.wordingtech.ui.shopdetail.ShopDetailActivity.KEY_IMAGE;
import static com.xxx.wordingtech.ui.shopdetail.ShopDetailActivity.KEY_MID;
import static com.xxx.wordingtech.ui.shopdetail.ShopDetailActivity.KEY_NAME;
import static com.xxx.wordingtech.ui.shopdetail.ShopDetailActivity.KEY_PRICE;

public class InnerShopFragment extends CommonListFragment<Shop, InnerShopViewModel, ItemShopBinding> {

    public InnerShopFragment(String classify) {
        super(classify, true, true);
    }

    @Override
    protected Class<InnerShopViewModel> getViewModelClazz() {
        return InnerShopViewModel.class;
    }

    @Override
    public int getItemLayoutRes() {
        return R.layout.item_shop;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(getContext(), 2);
    }

    @Override
    protected void initView() {
        super.initView();
        //商品点击跳转详情
        commonListRecyclerAdapter.setOnCellClickListener((itemShopBinding, shop) -> {
            Intent intent = new Intent(getContext(), ShopDetailActivity.class);
            intent.putExtra(KEY_MID, shop.getMid());
            intent.putExtra(KEY_NAME, shop.getName());
            intent.putExtra(KEY_BRIEF, shop.getIntroduction());
            intent.putExtra(KEY_IMAGE, shop.getImage());
            intent.putExtra(KEY_DIRECTOR, shop.getDirector());
            intent.putExtra(KEY_PRICE, shop.getPrice());
            startActivity(intent);
        });
    }

}
