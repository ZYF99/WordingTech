package com.xxx.wordingtech.ui.shop;

import android.util.Pair;
import com.xxx.wordingtech.R;
import com.xxx.wordingtech.databinding.FragmentShopBinding;
import com.xxx.wordingtech.ui.base.BaseFragment;
import com.xxx.wordingtech.ui.innershop.InnerShopFragment;
import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends BaseFragment<FragmentShopBinding, ShopViewModel> {

    private List<Pair<InnerShopFragment, String>> fragmentList = new ArrayList<>();

    @Override
    protected Class<ShopViewModel> getViewModelClazz() {
        return ShopViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_shop;
    }

    @Override
    protected void initView() {

        //监听分类列表，更新tab
        viewModel.classifyListLiveData.observe(this, strings -> {
            for (String s:strings) {
                fragmentList.add(Pair.create(new InnerShopFragment(s), s));
            }
            ShopPagerAdapter filmPagerAdapter = new ShopPagerAdapter(getChildFragmentManager(), fragmentList);
            binding.tabLayout.setupWithViewPager(binding.viewPager);
            binding.viewPager.setAdapter(filmPagerAdapter);
            binding.viewPager.setOffscreenPageLimit(strings.size() - 1);
        });

    }

    @Override
    protected void initData() {
        viewModel.fetchClassifyList();
    }
}