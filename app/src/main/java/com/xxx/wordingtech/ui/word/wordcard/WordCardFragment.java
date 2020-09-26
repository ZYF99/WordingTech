package com.xxx.wordingtech.ui.word.wordcard;

import com.xxx.wordingtech.R;
import com.xxx.wordingtech.databinding.ItemPagerWordBinding;
import com.xxx.wordingtech.model.word.Word;
import com.xxx.wordingtech.ui.base.BaseFragment;

/*单词卡Fragment*/
public class WordCardFragment extends BaseFragment<ItemPagerWordBinding, WordCardViewModel> {

    //是否是最后一个单词
    private boolean isLast;

    private boolean isTest;

    //单词model
    Word word;
    //点击事件监听接口，给WordFragment用来监听
    OnNextClickListener onNextClickListener;

    public void setOnNextClickListener(OnNextClickListener onNextClickListener) {
        this.onNextClickListener = onNextClickListener;
    }

    public WordCardFragment(Word word, boolean isLast,boolean isTest) {
        this.word = word;
        this.isLast = isLast;
        this.isTest = isTest;
    }

    @Override
    protected Class<WordCardViewModel> getViewModelClazz() {
        return WordCardViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.item_pager_word;
    }

    @Override
    protected void initView() {
        //点击“下一个”按钮，回调到WordFragment
        binding.btnNext.setOnClickListener(v -> {
            if (binding.btnNext.getText().equals("再来一组")) onNextClickListener.onClickNextGroup(); //是最后一个，回调触发开启下一组单词
            else onNextClickListener.onClickNextWord(); //不是最后一个，回调出发下一个单词
        });
        if (isLast) {
            if(!isTest){
                binding.btnNext.setText("");
            }
        }
    }

    @Override
    protected void initData() {
        binding.setWord(word);
    }

    //最下按钮点击事件接口
    public interface OnNextClickListener {
        //下一个单词
        void onClickNextWord();

        //下一个词组
        void onClickNextGroup();
    }

}