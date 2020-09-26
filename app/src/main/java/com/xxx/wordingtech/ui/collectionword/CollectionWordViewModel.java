package com.xxx.wordingtech.ui.collectionword;

import com.xxx.wordingtech.model.ResultModel;
import com.xxx.wordingtech.model.commonlist.CommonListPageModel;
import com.xxx.wordingtech.model.word.Word;
import com.xxx.wordingtech.ui.base.commonlist.CommonListViewModel;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Single;
import io.reactivex.functions.Function3;

public class CollectionWordViewModel extends CommonListViewModel<Word> {

    @Override
    public Function3<String, Integer, Integer, Single<ResultModel<CommonListPageModel<Word>>>> getRefreshFunction() {
        return (s, integer, integer2) -> apiService.fetchCollectionWordListByType("", 1, 1).doOnSubscribe(disposable -> {
            List<Word> l = new ArrayList<>();
            l.add(new Word("symbol", "音标", "[ˈsɪmbl]", "n."));
            l.add(new Word("symbol", "音标", "[ˈsɪmbl]", "n."));
            l.add(new Word("symbol", "音标", "[ˈsɪmbl]", "n."));
            l.add(new Word("symbol", "音标", "[ˈsɪmbl]", "n."));
            l.add(new Word("symbol", "音标", "[ˈsɪmbl]", "n."));
            commonListLiveData.postValue(l);
        });
    }

}
