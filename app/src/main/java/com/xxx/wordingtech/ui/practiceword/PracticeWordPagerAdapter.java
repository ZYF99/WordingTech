package com.xxx.wordingtech.ui.practiceword;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.List;

public class PracticeWordPagerAdapter extends FragmentPagerAdapter {

    private List<PracticeWordCardFragment> fragmentList;

    public PracticeWordPagerAdapter(@NonNull FragmentManager fm, List<PracticeWordCardFragment>  fragmentList) {
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