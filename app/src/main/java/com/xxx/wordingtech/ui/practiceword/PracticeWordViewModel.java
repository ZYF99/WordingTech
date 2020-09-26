package com.xxx.wordingtech.ui.practiceword;

import androidx.lifecycle.MutableLiveData;
import com.orhanobut.hawk.Hawk;
import com.xxx.wordingtech.Constants;
import com.xxx.wordingtech.model.word.Word;
import com.xxx.wordingtech.ui.base.BaseViewModel;
import com.xxx.wordingtech.util.ApiErrorUtil;
import com.xxx.wordingtech.util.RxUtil;
import java.util.ArrayList;
import java.util.List;

public class PracticeWordViewModel extends BaseViewModel {

    //训练单词列表
    MutableLiveData<List<Word>> wordListLiveData = new MutableLiveData();

    //拉取训练单词列表
    public void fetchPracticeWordList(){
        int count = Hawk.<Integer>get(Constants.KEY_WORD_COUNT_OF_GROUP,10);
        bindLife(
                apiService.fetchPracticeWordList(count)
                        .compose(RxUtil.switchThread())
                        .compose(ApiErrorUtil.dealError())
                        .doOnSubscribe(disposable -> {
                            List<Word> l = new ArrayList<>();
                            l.add(new Word("symbol", "音标", "[ˈsɪmbl]", "n."));
                            l.add(new Word("symbol", "音标", "[ˈsɪmbl]", "n."));
                            l.add(new Word("symbol", "音标", "[ˈsɪmbl]", "n."));
                            l.add(new Word("symbol", "音标", "[ˈsɪmbl]", "n."));
                            l.add(new Word("symbol", "音标", "[ˈsɪmbl]", "n."));
                            l.add(new Word("symbol", "音标", "[ˈsɪmbl]", "n."));
                            l.add(new Word("symbol", "音标", "[ˈsɪmbl]", "n."));
                            l.add(new Word("symbol", "音标", "[ˈsɪmbl]", "n."));
                            l.add(new Word("symbol", "音标", "[ˈsɪmbl]", "n."));
                            l.add(new Word("symbol", "音标", "[ˈsɪmbl]", "n."));
                            wordListLiveData.postValue(l);
                        })
                //.doOnSuccess((Consumer<ResultModel<List<Word>>>) resultModel -> wordListLiveData.postValue(resultModel.getData()))
        );

    }

}
