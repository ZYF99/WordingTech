package com.xxx.wordingtech.ui.innergrammer;

import com.xxx.wordingtech.model.ResultModel;
import com.xxx.wordingtech.model.commonlist.CommonListPageModel;
import com.xxx.wordingtech.model.grammer.Grammer;
import com.xxx.wordingtech.model.listen.Sentence;
import com.xxx.wordingtech.model.shop.Shop;
import com.xxx.wordingtech.ui.base.commonlist.CommonListViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Function3;

public class InnerGrammerViewModel extends CommonListViewModel<Grammer> {

    public String classify;

    @Override
    public Function3<String, Integer, Integer, Single<ResultModel<CommonListPageModel<Grammer>>>> getRefreshFunction() {
        return (s, integer, integer2) -> apiService.fetchGrammerListByClassify("", 1, 1).doOnSubscribe(disposable -> {
            List<Grammer> l = new ArrayList<>();
            Grammer grammer;
            if (classify.equals("语法干货")) {
                grammer = new Grammer("主语+系动词+主语补足语（表语）", "作用：无具体动作，仅起连接作用 \u200B \u200B\n" +
                        "\n" +
                        "后面所接成分：说明主语特点性质特征 \u200B \u200B\n" +
                        "\n" +
                        "种类：be动词（am, is, are） \u200B \u200B look, sound, smell, taste, feel, seem, appear, become, turn \u200B \u200B 表语：名词 or 形容词");
            } else {
                grammer = new Grammer("One morning a fox saw a cock.He thought,\"This is my breakfast.''   He came up to the cock and said,\"I know you can sing very well.Can you sing for me?''The cock was glad.He closes his eyes and began to sing.The fox saw that and caught him in his mouth and carried him away.   The people in the field saw the fox.They cried,\"Look,look!The fox is carrying the cock away.''   The cock said to the fox,\"Mr Fox,do you understand?The people say you are carrying their cock away.Tell them it is yours.Not theirs.''   The fox opened his mouth and said,\"The cock is mine,not yours.''Just then the cock ran away from the fox and fled into the tree. ", "一天早上，一只狐狸看到了一只公鸡。他想：这是我的早餐。   他朝公鸡走来，对他说：“我知道，你能唱得非常好听，你能唱给我听么？”公鸡很高兴。他闭上眼睛开始唱歌。狐狸看到这些抓住它放到自己的嘴里走了。   在田地里的人们看到了狐狸。大喊大叫：“看，看！狐狸抓住公鸡逃走了。”公鸡对狐狸说：“狐狸先生，你能理解么？人们认为你叼走了公鸡。告诉他们这是你的，不是他们的。”   狐狸张开她的嘴说：“公鸡是我的，不是你们的。”就在那时，公鸡逃离了狐狸的嘴巴，跑到了树底下。");
            }
            l.add(grammer);
            l.add(grammer);
            l.add(grammer);
            l.add(grammer);
            l.add(grammer);
            l.add(grammer);
            l.add(grammer);
            l.add(grammer);
            l.add(grammer);
            l.add(grammer);
            commonListLiveData.postValue(l);
        });
    }
}
