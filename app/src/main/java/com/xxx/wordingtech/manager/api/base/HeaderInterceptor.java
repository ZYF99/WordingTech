package com.xxx.wordingtech.manager.api.base;

import com.orhanobut.hawk.Hawk;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import static com.xxx.wordingtech.Constants.KEY_TOKEN;

public class HeaderInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        String token = "";
        if (Hawk.<String>get(KEY_TOKEN) != null) {
            token = Hawk.<String>get(KEY_TOKEN);
        }
        Request request = chain.request().newBuilder()
                .addHeader(KEY_TOKEN, token)
                /*.addHeader("Connection", "close")*/
                .build();
        return chain.proceed(request);
    }
}
