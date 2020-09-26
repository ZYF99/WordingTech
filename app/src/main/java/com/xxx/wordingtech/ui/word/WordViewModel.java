package com.xxx.wordingtech.ui.word;

import androidx.lifecycle.MutableLiveData;
import com.orhanobut.hawk.Hawk;
import com.xxx.wordingtech.Constants;
import com.xxx.wordingtech.model.word.Word;
import com.xxx.wordingtech.ui.base.BaseViewModel;
import com.xxx.wordingtech.util.ApiErrorUtil;
import com.xxx.wordingtech.util.RxUtil;
import java.util.ArrayList;
import java.util.List;

public class WordViewModel extends BaseViewModel {

    //单词列表
    MutableLiveData<List<Word>> wordListLiveData = new MutableLiveData();

    //当前选择的词组 0：未学 1：已学
    MutableLiveData<Integer> studyStateLiveData = new MutableLiveData(0);

    //当前单词状态
    public MutableLiveData<String> type = new MutableLiveData<>("未学");

    //拉取单词列表
    public void fetchWordCardList() {
        //每组数量
        int count = Hawk.<Integer>get(Constants.KEY_WORD_COUNT_OF_GROUP,10);
        bindLife(
                apiService.fetchWordListByType(type.getValue(),1,count)
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
