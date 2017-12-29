package food.xinyuan.seller.app.config.applyOptions.intercept;

import android.text.TextUtils;

import java.io.IOException;

import food.xinyuan.seller.app.utils.MySharePreferencesManager;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author lzd
 * date 2017/6/28 11:34
 * 类描述：添加token
 */

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        String token = MySharePreferencesManager.getInstance().getString("token", "");
        Request.Builder builder = original.newBuilder();
        if (!TextUtils.isEmpty(token)){     //添加token
            builder.addHeader("token",token);
        }
        builder.addHeader("Domain-Name","main");
        return chain.proceed(builder.build());
    }
}
