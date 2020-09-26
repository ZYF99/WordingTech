package com.xxx.wordingtech.ui.practiceword;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.viewpager.widget.ViewPager;
import com.xxx.wordingtech.R;
import com.xxx.wordingtech.databinding.ActivityPracticeWordBinding;
import com.xxx.wordingtech.ui.base.BaseActivity;
import java.util.ArrayList;
import java.util.List;

public class PracticeWordActivity extends BaseActivity<ActivityPracticeWordBinding, PracticeWordViewModel> {

    private List<PracticeWordCardFragment> fragmentList = new ArrayList<>();

    private boolean isTest;

    @Override
    protected Class<PracticeWordViewModel> getViewModelClazz() {
        return PracticeWordViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_practice_word;
    }

    @Override
    protected void initView() {
        isTest = getIntent().getBooleanExtra("isTest",false);
        //返回按钮
        binding.ivReturn.setOnClickListener(v -> finish());
        //监听单词卡片列表，更新viewpager
        viewModel.wordListLiveData.observe(this, words -> {
            //清空卡片列表，重置数据
            fragmentList.clear();
            //遍历获得的单词列表
            for (int i = 0; i < words.size(); i++) {
                //以单词model创建单词卡Fragment
                PracticeWordCardFragment practiceWordCardFragment = new PracticeWordCardFragment(words.get(i), i == words.size() - 1);
                //设置点击下一个：点击翻页
                practiceWordCardFragment.setOnNextClickListener(new PracticeWordCardFragment.OnNextClickListener() {

                    //再来一组
                    @Override
                    public void onClickNextGroup() {
                        if(isTest){
                            if (isTest) {
                                new AlertDialog.Builder(PracticeWordActivity.this)
                                        .setTitle("您的词汇量为")
                                        .setMessage("0.8")
                                        .setPositiveButton("退出", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                finish();
                                            }
                                        })
                                        .setCancelable(false)
                                        .create()
                                        .show();
                            }else {
                                nextGroup();
                            }
                        }
                    }

                    //下个单词
                    @Override
                    public void onNextWord() {
                        int currentPosition = binding.vpPracticeWordCard.getCurrentItem();
                        if (currentPosition != words.size() - 1) //不是最后一页，翻页
                            binding.vpPracticeWordCard.setCurrentItem(binding.vpPracticeWordCard.getCurrentItem() + 1);
                    }
                });
                //将创建的CardFragment添加在fragment列表中
                fragmentList.add(practiceWordCardFragment);
            }

            //翻页后，更改标题题号
            binding.vpPracticeWordCard.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
            PracticeWordPagerAdapter pagerAdapter = new PracticeWordPagerAdapter(getSupportFragmentManager(), fragmentList);
            binding.vpPracticeWordCard.setAdapter(pagerAdapter);
            binding.vpPracticeWordCard.setOffscreenPageLimit(words.size() - 1);
            binding.vpPracticeWordCard.setCurrentItem(0);
        });

    }

    @Override
    protected void initData() {
        viewModel.fetchPracticeWordList();
    }

    //再来一组单词
    private void nextGroup() {
        viewModel.fetchPracticeWordList();
    }

}