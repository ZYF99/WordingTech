package com.xxx.wordingtech.manager.api;

import com.xxx.wordingtech.model.ResultModel;
import com.xxx.wordingtech.model.UploadImageResultModel;
import com.xxx.wordingtech.model.commonlist.CommonListPageModel;
import com.xxx.wordingtech.model.feedback.FeedbackRequestModel;
import com.xxx.wordingtech.model.grammer.Grammer;
import com.xxx.wordingtech.model.listen.Sentence;
import com.xxx.wordingtech.model.login.LoginRequestModel;
import com.xxx.wordingtech.model.login.LoginResultModel;
import com.xxx.wordingtech.model.mine.UpdateUserProfileRequestModel;
import com.xxx.wordingtech.model.mine.UserProfile;
import com.xxx.wordingtech.model.register.RegisterRequestModel;
import com.xxx.wordingtech.model.register.RegisterResultModel;
import com.xxx.wordingtech.model.shop.Shop;
import com.xxx.wordingtech.model.word.Word;

import java.util.List;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {
    //账户相关------------------------------------------------------------------------------------------------------------------------------------
    //登录
    @POST("account/sign/in")
    Single<ResultModel<LoginResultModel>> login(@Body LoginRequestModel loginRequestModel);

    //注册
    @POST("account/sign/up")
    Single<ResultModel<RegisterResultModel>> register(@Body RegisterRequestModel registerRequestModel);

    //获取用户个人信息
    @GET("account/profile")
    Single<ResultModel<UserProfile>> getUserProfile();

    //用户反馈
    @POST("feedback")
    Single<ResponseBody> uploadAdvance(@Body FeedbackRequestModel feedbackRequestModel);

    //更新用户个人信息
    @PUT("account/profile")
    Single<ResponseBody> updateUserProfile(@Body UpdateUserProfileRequestModel updateUserProfileRequestModel);

    //图片相关--------------------------------------------------------------------------------------------------------------------------------------
    //上传头像
    @Multipart
    @POST("tools/upload")
    Single<ResultModel<UploadImageResultModel>> upLoadImage(@Part MultipartBody.Part file);


    //周边相关------------------------------------------------------------------------------------------------------------------------------------
    //按分类检索周边商品列表
    @GET("shop/classify")
    Single<ResultModel<CommonListPageModel<Shop>>> fetchShopListByClassify(
            @Query("classify") String classify, //类型，动态添加的
            @Query("pageNo") int pageNo,
            @Query("pageSize") int pageSize
    );

    //单词相关------------------------------------------------------------------------------------------------------------------------------------
    //按学过/未学过 查询单词列表
    @GET("word/classify")
    Single<ResultModel<CommonListPageModel<Word>>> fetchWordListByType(
            @Query("classify") String classify, // 学过/未学
            @Query("pageNo") int pageNo,
            @Query("pageSize") int pageSize
    );

    //查询收藏的单词列表
    @GET("word/classify")
    Single<ResultModel<CommonListPageModel<Word>>> fetchCollectionWordListByType(
            @Query("classify") String classify, // 这里给""空字符串，收藏的单词不分学习或未学习
            @Query("pageNo") int pageNo,
            @Query("pageSize") int pageSize
    );

    //查询单词训练列表
    @GET("word/practice")
    Single<ResultModel<List<Word>>> fetchPracticeWordList(@Query("pageSize") int pageSize);

    //收藏单词
    @GET("word/collection")
    Single<ResultModel<String>> collectionWord(@Query("id") int wordId);


    //发现相关------------------------------------------------------------------------------------------------------------------------------------
    //按分类检索语法干货列表
    @GET("grammer/classify")
    Single<ResultModel<CommonListPageModel<Grammer>>> fetchGrammerListByClassify(
            @Query("classify") String classify, // 阅读短文/语法干货
            @Query("pageNo") int pageNo,
            @Query("pageSize") int pageSize
    );


    //听力相关------------------------------------------------------------------------------------------------------------------------------------
    //检索 句子/影视片段 列表
    @GET("sentence")
    Single<ResultModel<CommonListPageModel<Sentence>>> fetchSentenceListByClassify(
            @Query("classify") String classify, // 句子/影视片段
            @Query("pageNo") int pageNo,
            @Query("pageSize") int pageSize
    );

    //检索收藏的 句子/影视片段 列表
    @GET("sentence")
    Single<ResultModel<CommonListPageModel<Sentence>>> fetchCollectionSentenceListByClassify(
            @Query("classify") String classify, // 句子/影视片段
            @Query("pageNo") int pageNo,
            @Query("pageSize") int pageSize
    );

    //收藏听力材料
    @GET("listen/collection")
    Single<ResultModel<String>> collectionListen(@Query("id") int listenId);

}