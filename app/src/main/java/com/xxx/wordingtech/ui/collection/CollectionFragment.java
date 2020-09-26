package com.xxx.wordingtech.ui.collection;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import com.xxx.wordingtech.R;
import com.xxx.wordingtech.databinding.FragmentCollectionBinding;
import com.xxx.wordingtech.ui.base.BaseFragment;
import com.xxx.wordingtech.ui.collectionword.CollectionWordFragment;
import com.xxx.wordingtech.ui.find.FindPagerAdapter;
import com.xxx.wordingtech.ui.innersentence.InnerSentenceFragment;
import com.xxx.wordingtech.ui.widget.MyViewPagerBottomSheetBehavior;
import java.util.ArrayList;
import java.util.List;
import biz.laenger.android.vpbs.BottomSheetUtils;
import biz.laenger.android.vpbs.ViewPagerBottomSheetBehavior;

public class CollectionFragment extends Fragment {

    private List<Pair<BaseFragment, String>> fragmentList = new ArrayList<>();
    private MyViewPagerBottomSheetBehavior behavior;

    public void setBehavior(MyViewPagerBottomSheetBehavior behavior) {
        this.behavior = behavior;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentCollectionBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_collection, null, false);

        CollectionWordFragment collectionFragment = new CollectionWordFragment("", false);
        InnerSentenceFragment sentenceFragment = new InnerSentenceFragment("句子", false);
        InnerSentenceFragment pagramFragment = new InnerSentenceFragment("影视片段", false);
        collectionFragment.setBehavior(behavior);
        sentenceFragment.setBehavior(behavior);
        pagramFragment.setBehavior(behavior);
        fragmentList.add(Pair.create(collectionFragment, "单词"));
        fragmentList.add(Pair.create(sentenceFragment, "句子"));
        fragmentList.add(Pair.create(pagramFragment, "影视片段"));

        FindPagerAdapter filmPagerAdapter = new FindPagerAdapter(getChildFragmentManager(), fragmentList);
        binding.tabLayout.setupWithViewPager(binding.vpCollection);
        binding.vpCollection.setAdapter(filmPagerAdapter);
        binding.vpCollection.setOffscreenPageLimit(1);
        BottomSheetUtils.setupViewPager(binding.vpCollection);
        binding.ivExpand.setOnClickListener(v -> {
            //根据状态不同显示隐藏
            if (behavior.getState() == MyViewPagerBottomSheetBehavior.STATE_COLLAPSED) {
                behavior.setState(MyViewPagerBottomSheetBehavior.STATE_EXPANDED);
            } else if (behavior.getState() == MyViewPagerBottomSheetBehavior.STATE_EXPANDED) {
                behavior.setState(MyViewPagerBottomSheetBehavior.STATE_COLLAPSED);
            }
        });
        behavior.setBottomSheetCallback(new MyViewPagerBottomSheetBehavior.BottomSheetCallback() {
            @SuppressLint("WrongConstant")
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == ViewPagerBottomSheetBehavior.STATE_COLLAPSED) {
                    binding.ivExpand.setImageResource(R.drawable.icon_up);
                } else if (behavior.getState() == ViewPagerBottomSheetBehavior.STATE_EXPANDED) {
                    binding.ivExpand.setImageResource(R.drawable.icon_down);
                }
                //behavior.setScrollViewOnTop(false);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        return binding.getRoot();
    }


}
