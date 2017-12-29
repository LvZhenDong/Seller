package food.xinyuan.seller.app.config.applyOptions;

import android.content.Context;

import com.jess.arms.di.module.ClientModule;

import food.xinyuan.seller.BuildConfig;
import food.xinyuan.seller.app.config.applyOptions.intercept.HeaderInterceptor;
import food.xinyuan.seller.app.config.applyOptions.intercept.LoggingInterceptor;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by f-x on 2017/12/9  10:31
 * Description Retrofit配置
 */

public class MyRetrofitConfiguration implements ClientModule.RetrofitConfiguration {
    @Override
    public void configRetrofit(Context context, Retrofit.Builder builder) {
        // 配置多BaseUrl支持
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(new LoggingInterceptor());//使用自定义的Log拦截器
        }
        clientBuilder.addInterceptor(new HeaderInterceptor());
        builder.client(RetrofitUrlManager.getInstance().with(clientBuilder)
                .build());
    }
}
