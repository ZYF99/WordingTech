package com.xxx.wordingtech.ui.word;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.orhanobut.hawk.Hawk;
import com.xxx.wordingtech.Constants;
import com.xxx.wordingtech.R;
import com.xxx.wordingtech.databinding.DialogChangeWordCountBinding;
import com.xxx.wordingtech.databinding.FragmentWordBinding;
import com.xxx.wordingtech.ui.base.BaseFragment;
import com.xxx.wordingtech.ui.practiceword.PracticeWordActivity;
import com.xxx.wordingtech.ui.word.wordcard.WordCardFragment;

import java.util.ArrayList;
import java.util.List;

public class WordFragment extends BaseFragment<FragmentWordBinding, WordViewModel> {

    private List<WordCardFragment> fragmentList = new ArrayList<>();

    private Boolean isTest = false;

    public WordFragment(Boolean isTest) {
        super();
        this.isTest = isTest;
    }

    @Override
    protected Class<WordViewModel> getViewModelClazz() {
        return WordViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_word;
    }

    @Override
    protected void initView() {

        if (isTest) {
            binding.btnPractice.setVisibility(View.GONE);
            binding.llTitle.setVisibility(View.GONE);
        }

        //监听单词卡片列表，更新viewpager
        viewModel.wordListLiveData.observe(this, words -> {
            //清空卡片列表，重置数据
            fragmentList.clear();
            //遍历获得的单词列表
            for (int i = 0; i < words.size(); i++) {
                //以单词model创建单词卡Fragment
                WordCardFragment wordCardFragment = new WordCardFragment(words.get(i), i == words.size() - 1,isTest);
                //设置点击下一个：点击翻页
                wordCardFragment.setOnNextClickListener(new WordCardFragment.OnNextClickListener() {
                    @Override
                    public void onClickNextWord() {
                        nextWord();
                    }

                    @Override
                    public void onClickNextGroup() {
                        nextGroup();
                    }
                });
                //将创建的CardFragment添加在fragment列表中
                fragmentList.add(wordCardFragment);
            }

            //翻页后，更改标题题号
            binding.vpWordCard.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    binding.tvNum.setText(position + 1 + "/" + words.size());
                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            //为单词卡ViewPager设置adapter适配器
            WordPagerAdapter pagerAdapter = new WordPagerAdapter(getChildFragmentManager(), fragmentList);
            binding.vpWordCard.setAdapter(pagerAdapter);
            binding.vpWordCard.setOffscreenPageLimit(words.size() - 1);
            binding.vpWordCard.setCurrentItem(0);
        });

        //当前学习的词组状态
        viewModel.studyStateLiveData.observe(this, integer -> {
            if (integer == 0) viewModel.type.setValue("未学");
            if (integer == 1) viewModel.type.setValue("已学");
            viewModel.fetchWordCardList();
        });

        //切换词组状态
        binding.ivChangeState.setOnClickListener(v -> changeWordState());

        //点击设置
        binding.ivSetting.setOnClickListener(v -> showSettingDialog());

        //点击进入单词训练
        binding.btnPractice.setOnClickListener(v -> jumpToWordPractice());

    }

    @Override
    protected void initData() {
        viewModel.fetchWordCardList();
    }

    //下一个单词
    private void nextWord() {
        //不是最后一页
        if (binding.vpWordCard.getCurrentItem() < fragmentList.size() - 1) {
            //翻页
            binding.vpWordCard.setCurrentItem(binding.vpWordCard.getCurrentItem() + 1);
        }
    }

    //下一组单词
    private void nextGroup() {
        viewModel.fetchWordCardList();
    }

    //切换词组状态
    private void changeWordState() {
        if (viewModel.studyStateLiveData.getValue() == 0) viewModel.studyStateLiveData.postValue(1);
        else viewModel.studyStateLiveData.postValue(0);
    }

    //跳转至单词训练界面
    private void jumpToWordPractice() {
        Intent intent = new Intent(getContext(), PracticeWordActivity.class);
        startActivity(intent);
    }

    //弹出设置弹窗
    private void showSettingDialog() {
        DialogChangeWordCountBinding dialogBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_change_word_count, null, false);
        dialogBinding.setCount(Hawk.<Integer>get(Constants.KEY_WORD_COUNT_OF_GROUP, 10).toString());
        new AlertDialog.Builder(getContext())
                .setView(dialogBinding.getRoot())
                .setPositiveButton("确定", (dialog, which) -> {
                    Hawk.put(Constants.KEY_WORD_COUNT_OF_GROUP, Integer.parseInt(dialogBinding.getCount()));
                    viewModel.fetchWordCardList();
                })
                .setNegativeButton("取消", null)
                .create()
                .show();
    }

}