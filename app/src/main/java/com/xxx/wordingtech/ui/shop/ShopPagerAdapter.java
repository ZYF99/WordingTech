package com.xxx.wordingtech.ui.shop;

import android.os.Bundle;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.xxx.wordingtech.ui.innershop.InnerShopFragment;
import java.util.List;
import static com.xxx.wordingtech.ui.innershop.InnerShopFragment.KEY_CLASSIFY;

public class ShopPagerAdapter extends FragmentPagerAdapter {

    private List<Pair<InnerShopFragment, String>> fragmentList;

    public ShopPagerAdapter(@NonNull FragmentManager fm, List<Pair<InnerShopFragment, String>> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_CLASSIFY,fragmentList.get(position).second);
        fragmentList.get(position).first.setArguments(bundle);
        return fragmentList.get(position).first;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentList.get(position).second;
    }
}