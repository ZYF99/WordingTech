package com.xxx.wordingtech.manager.di;

import com.google.gson.Gson;
import com.xxx.wordingtech.BuildConfig;
import com.xxx.wordingtech.manager.api.ApiService;
import com.xxx.wordingtech.manager.api.base.ApiClient;
import com.xxx.wordingtech.manager.api.base.HeaderInterceptor;
import com.xxx.wordingtech.manager.api.base.NetErrorInterceptor;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class AppModule {

    @Singleton
    @Provides
    public ApiService provideApiService() {
        return provideApiClient().createService(ApiService.class);
    }

/*    @Singleton
    @Provides
    public DialogUtil provideDialogUtil() {
        return new DialogUtil();
    }*/

    @Provides
    public Gson provideGson() {
        return new Gson();
    }

    public ApiClient<ApiService> provideApiClient() {
        ApiClient.Builder apiClient = new ApiClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        apiClient.getOkBuilder().
                addInterceptor(new HeaderInterceptor())
                .addInterceptor(new NetErrorInterceptor())
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor);
        return apiClient.build(BuildConfig.BASE_URL);
    }

}
