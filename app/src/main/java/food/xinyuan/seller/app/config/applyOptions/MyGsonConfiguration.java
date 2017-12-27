package food.xinyuan.seller.app.config.applyOptions;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.jess.arms.di.module.AppModule;

/**
 * Created by f-x on 2017/12/9  10:30
 * Description Gson配置
 */

public class MyGsonConfiguration implements AppModule.GsonConfiguration {
    @Override
    public void configGson(Context context, GsonBuilder builder) {
        builder.serializeNulls()//支持序列化null的参数
                .enableComplexMapKeySerialization();//支持将序列化key为object的map,默认只能序列化key为string的map
    }
}
