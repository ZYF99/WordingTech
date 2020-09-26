package com.xxx.wordingtech.manager.api.base;

import java.util.LinkedHashMap;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient<S> {
    Retrofit retrofit;
    OkHttpClient okHttpClient;

    public ApiClient(Retrofit retrofit, OkHttpClient okHttpClient) {
        this.retrofit = retrofit;
        this.okHttpClient = okHttpClient;
    }

    public S createService(Class<S> serviceClass){
        return retrofit.create(serviceClass);
    }

    public static class Builder {
        LinkedHashMap<String, Interceptor> apiAuthorizations;
        OkHttpClient.Builder okBuilder;

        public LinkedHashMap<String, Interceptor> getApiAuthorizations() {
            return apiAuthorizations;
        }

        public OkHttpClient.Builder getOkBuilder() {
            return okBuilder;
        }

        public Retrofit.Builder getAdapterBuilder() {
            return adapterBuilder;
        }

        Retrofit.Builder adapterBuilder;

        public Builder() {
            this.apiAuthorizations = new LinkedHashMap<>();
            this.okBuilder = new OkHttpClient.Builder();
            this.adapterBuilder = new Retrofit.Builder();
        }

        public Builder(LinkedHashMap<String, Interceptor> apiAuthorizations, OkHttpClient.Builder okBuilder, Retrofit.Builder adapterBuilder) {
            this.apiAuthorizations = apiAuthorizations;
            this.okBuilder = okBuilder;
            this.adapterBuilder = adapterBuilder;
        }

        public ApiClient build(String baseUrl) {
            adapterBuilder.baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());
            OkHttpClient okClient = okBuilder.build();
            Retrofit retrofit = adapterBuilder.client(okClient).build();

            return new ApiClient(retrofit, okClient);
        }
    }

}
