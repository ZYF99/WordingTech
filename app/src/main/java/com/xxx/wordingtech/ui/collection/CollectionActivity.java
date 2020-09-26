package com.xxx.wordingtech.ui.collection;

import android.util.Pair;
import com.xxx.wordingtech.R;
import com.xxx.wordingtech.databinding.FragmentCollectionBinding;
import com.xxx.wordingtech.ui.base.BaseActivity;
import com.xxx.wordingtech.ui.base.BaseFragment;
import com.xxx.wordingtech.ui.collectionword.CollectionWordFragment;
import com.xxx.wordingtech.ui.find.FindPagerAdapter;
import com.xxx.wordingtech.ui.innersentence.InnerSentenceFragment;

import java.util.ArrayList;
import java.util.List;

public class CollectionActivity extends BaseActivity<FragmentCollectionBinding, CollectionViewModel> {

    private List<Pair<BaseFragment, String>> fragmentList = new ArrayList<>();

    @Override
    protected Class<CollectionViewModel> getViewModelClazz() {
        return CollectionViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_collection;
    }

    @Override
    protected void initView() {
        CollectionWordFragment collectionFragment = new CollectionWordFragment("");
        InnerSentenceFragment sentenceFragment = new InnerSentenceFragment("句子");
        InnerSentenceFragment pagramFragment = new InnerSentenceFragment("影视片段");
        fragmentList.add(Pair.create(collectionFragment, "单词"));
        fragmentList.add(Pair.create(sentenceFragment, "句子"));
        fragmentList.add(Pair.create(pagramFragment, "影视片段"));
        FindPagerAdapter filmPagerAdapter = new FindPagerAdapter(getSupportFragmentManager(), fragmentList);
        binding.tabLayout.setupWithViewPager(binding.vpCollection);
        binding.vpCollection.setAdapter(filmPagerAdapter);
        binding.vpCollection.setOffscreenPageLimit(1);
    }

    @Override
    protected void initData() {

    }
}