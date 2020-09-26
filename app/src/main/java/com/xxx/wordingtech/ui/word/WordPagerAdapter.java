package com.xxx.wordingtech.ui.word;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.xxx.wordingtech.ui.word.wordcard.WordCardFragment;
import java.util.List;

public class WordPagerAdapter extends FragmentPagerAdapter {

    private List<WordCardFragment> fragmentList;

    public WordPagerAdapter(@NonNull FragmentManager fm, List<WordCardFragment>  fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }


}