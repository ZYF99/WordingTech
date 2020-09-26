package com.xxx.wordingtech.ui.find;

import android.util.Pair;
import com.xxx.wordingtech.R;
import com.xxx.wordingtech.databinding.FragmentFindBinding;
import com.xxx.wordingtech.ui.base.BaseFragment;
import com.xxx.wordingtech.ui.innergrammer.InnerGrammerFragment;
import java.util.ArrayList;
import java.util.List;

public class FindFragment extends BaseFragment<FragmentFindBinding, FindViewModel> {

    private List<Pair<BaseFragment, String>> fragmentList = new ArrayList<>();

    @Override
    protected Class<FindViewModel> getViewModelClazz() {
        return FindViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initView() {
        List<String> l = new ArrayList<>();
        l.add("阅读短文");
        l.add("语法干货");
        for (String s:l) {
            fragmentList.add(Pair.create(new InnerGrammerFragment(s), s));
        }
        FindPagerAdapter filmPagerAdapter = new FindPagerAdapter(getChildFragmentManager(), fragmentList);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        binding.viewPager.setAdapter(filmPagerAdapter);
        binding.viewPager.setOffscreenPageLimit(1);
    }

    @Override
    protected void initData() {

    }
}