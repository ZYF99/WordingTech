package com.xxx.wordingtech.ui.practiceword;

import android.view.View;
import android.widget.Toast;
import com.xxx.wordingtech.R;
import com.xxx.wordingtech.databinding.ItemPagerPracticeWordBinding;
import com.xxx.wordingtech.model.word.Word;
import com.xxx.wordingtech.ui.base.BaseFragment;

/*单词卡Fragment*/
public class PracticeWordCardFragment extends BaseFragment<ItemPagerPracticeWordBinding, PracticeCardViewModel> {

    //是否是最后一个单词
    private boolean isLast;

    //单词model
    Word word;
    //点击事件监听接口，给WordFragment用来监听
    OnNextClickListener onNextClickListener;

    public void setOnNextClickListener(OnNextClickListener onNextClickListener) {
        this.onNextClickListener = onNextClickListener;
    }

    public PracticeWordCardFragment(Word word, boolean isLast) {
        this.word = word;
        this.isLast = isLast;
    }

    @Override
    protected Class<PracticeCardViewModel> getViewModelClazz() {
        return PracticeCardViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.item_pager_practice_word;
    }

    @Override
    protected void initView() {

        //不是最后一个，隐藏按钮
        if (!isLast) binding.btnNext.setVisibility(View.GONE);

        //点击“确定”，对比答案
        binding.btnConfirm.setOnClickListener(v -> {
            if (binding.etEnglish.getText().toString().equals(word.getEnglish())) {
                onNextClickListener.onNextWord();
            } else {
                Toast.makeText(getContext(), "答案错误", Toast.LENGTH_SHORT).show();
                binding.tvTipAnswer.setVisibility(View.VISIBLE);
                binding.tvAnswer.setVisibility(View.VISIBLE);
                binding.tvTipAnswer.setVisibility(View.VISIBLE);
                binding.btnPass.setVisibility(View.VISIBLE);
            }
        });

        //点击“过”按钮，下一个词
        binding.btnPass.setOnClickListener(v -> {
            onNextClickListener.onNextWord();
        });

        //点击“下一个”按钮，回调到PracticeWordFragment
        binding.btnNext.setOnClickListener(v -> {
            if (isLast) onNextClickListener.onClickNextGroup(); //是最后一个，回调触发开启下一组单词
        });

        //聚焦输入框，直接弹出软键盘
        binding.etEnglish.requestFocus();

    }

    @Override
    protected void initData() {
        binding.setWord(word);
    }

    //最下按钮点击事件接口
    public interface OnNextClickListener {
        //下一个词组
        void onClickNextGroup();

        //下一个单词
        void onNextWord();
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.etEnglish.setText("");
    }
}