package food.xinyuan.seller.app;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.di.module.GlobalConfigModule;
import com.jess.arms.integration.ConfigModule;
import com.jess.arms.utils.DataHelper;
import food.xinyuan.seller.app.config.MyActivityLifecycle;
import food.xinyuan.seller.app.config.MyApplicationLifecycles;
import food.xinyuan.seller.app.config.applyOptions.MyGlobalHttpHandler;
import food.xinyuan.seller.app.config.applyOptions.MyGsonConfiguration;
import food.xinyuan.seller.app.config.applyOptions.MyResponseErrorListener;
import food.xinyuan.seller.app.config.applyOptions.MyRetrofitConfiguration;

import java.io.File;
import java.util.List;


/**
 * Created by f-x on 2017/12/9  10:18
 * Description 全局配置
 */

public class GlobalConfiguration implements ConfigModule {
    @Override
    public void applyOptions(Context context, GlobalConfigModule.Builder builder) {
        builder
                .retrofitConfiguration(new MyRetrofitConfiguration())
                .rxCacheConfiguration((context1, rxCacheBuilder) -> {
                    rxCacheBuilder.useExpiredDataIfLoaderNotAvailable(true);
                    return null;
                })
                .globalHttpHandler(new MyGlobalHttpHandler())
                .responseErrorListener(new MyResponseErrorListener())
                .cacheFile(new File(DataHelper.getCacheFile(context), "rxCache"))
                .gsonConfiguration(new MyGsonConfiguration());
    }

    @Override
    public void injectAppLifecycle(Context context, List<AppLifecycles> lifecycles) {
        lifecycles.add(new MyApplicationLifecycles());
    }

    @Override
    public void injectActivityLifecycle(Context context, List<Application.ActivityLifecycleCallbacks> lifecycles) {
        lifecycles.add(new MyActivityLifecycle());

    }

    @Override
    public void injectFragmentLifecycle(Context context, List<FragmentManager.FragmentLifecycleCallbacks> lifecycles) {

    }
}
