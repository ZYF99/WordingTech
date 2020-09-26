package com.xxx.wordingtech.ui.listen;

import android.util.Pair;
import com.xxx.wordingtech.R;
import com.xxx.wordingtech.databinding.FragmentListenBinding;
import com.xxx.wordingtech.ui.base.BaseFragment;
import com.xxx.wordingtech.ui.innersentence.InnerSentenceFragment;
import java.util.ArrayList;
import java.util.List;

public class ListenFragment extends BaseFragment<FragmentListenBinding, ListenViewModel> {

    private List<Pair<BaseFragment, String>> fragmentList = new ArrayList<>();

    @Override
    protected Class<ListenViewModel> getViewModelClazz() {
        return ListenViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_listen;
    }

    @Override
    protected void initView() {
        List<String> l = new ArrayList<>();
        l.add("生词本");
        l.add("已学单词");
        l.add("句子");
        l.add("影视片段");
        for (String s:l) {
            fragmentList.add(Pair.create(new InnerSentenceFragment(s), s));
        }
        ListenPagerAdapter listenPagerAdapter = new ListenPagerAdapter(getChildFragmentManager(), fragmentList);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        binding.viewPager.setAdapter(listenPagerAdapter);
        binding.viewPager.setOffscreenPageLimit(1);
    }

    @Override
    protected void initData() {

    }
}