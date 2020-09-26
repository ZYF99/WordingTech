package com.xxx.wordingtech.ui.innersentence;

import com.xxx.wordingtech.model.ResultModel;
import com.xxx.wordingtech.model.commonlist.CommonListPageModel;
import com.xxx.wordingtech.model.listen.Sentence;
import com.xxx.wordingtech.ui.base.commonlist.CommonListViewModel;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Single;
import io.reactivex.functions.Function3;

public class InnerSentenceViewModel extends CommonListViewModel<Sentence> {

    public String classify;

    @Override
    public Function3<String, Integer, Integer, Single<ResultModel<CommonListPageModel<Sentence>>>> getRefreshFunction() {
        return (s, integer, integer2) -> apiService.fetchSentenceListByClassify("", 1, 1).doOnSubscribe(disposable -> {
            List<Sentence> l = new ArrayList<>();

            if(classify.equals("生词本")){
                l.add(new Sentence("抛弃；遗弃", "abandon"));
                l.add(new Sentence("抛弃；遗弃", "abandon"));
                l.add(new Sentence("抛弃；遗弃", "abandon"));
                l.add(new Sentence("抛弃；遗弃", "abandon"));
                l.add(new Sentence("抛弃；遗弃", "abandon"));
                l.add(new Sentence("抛弃；遗弃", "abandon"));
            }else if(classify.equals("已学单词")){
                l.add(new Sentence("你好", "hello"));
                l.add(new Sentence("你好", "hello"));
                l.add(new Sentence("你好", "hello"));
                l.add(new Sentence("你好", "hello"));
                l.add(new Sentence("你好", "hello"));
                l.add(new Sentence("你好", "hello"));
                l.add(new Sentence("你好", "hello"));
            } else {
                String a = "";
                if (classify.equals("句子")) {
                    a = "中国很棒--这是个句子";
                } else if(classify.equals("影视片段")) {
                    a = "上帝既已安排我们相识，怎能不让我们相守！\n" +
                            "当他们问我最爱bai的是什么，我会告诉他们。。。就是你！\n" +
                            "我宁可呼吸到她飘散在空气中的发香，\n" +
                            "轻吻她双唇，抚摸她双手，而放弃永生。\n" +
                            "我老觉得，有种巨大的力量，让你我都显得好渺小！\n" +
                            "我整日都盼着能与你有多一分钟的相聚！";
                }
                String as = "don't understand the God who'd let us meet if we could never be together.When they ask me what I liked the best,I'll tell them It was you. I would rather have had one breath of her hair,one kiss of her mouth,one touch of her hand than an eternity without it.I have a feeling there's something bigger out there.Bigger than we and bigger than you.I wait all day just hoping for one more minute with you.";
                l.add(new Sentence(a, as));
                l.add(new Sentence(a, as));
                l.add(new Sentence(a, as));
                l.add(new Sentence(a, as));
                l.add(new Sentence(a, as));
                l.add(new Sentence(a, as));
                l.add(new Sentence(a, as));
                l.add(new Sentence(a, as));
                l.add(new Sentence(a, as));
                l.add(new Sentence(a, as));
                l.add(new Sentence(a, as));
                l.add(new Sentence(a, as));
            }
            commonListLiveData.postValue(l);
        });
    }

}
