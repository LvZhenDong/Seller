package food.xinyuan.seller.app.config.applyOptions.intercept;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import food.xinyuan.seller.app.ARouterPaths;
import food.xinyuan.seller.app.utils.ConstantUtil;
import food.xinyuan.seller.app.utils.DataUtils;


/**
 * Created by f-x on 2017/12/12  16:24
 * Description
 */
@Interceptor(priority = 7)
public class ARouterInterceptor implements IInterceptor {
    Context mContext;


    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        switch (postcard.getExtra()) {
            case ConstantUtil.SHOULD_LOGIN:
                if (DataUtils.checkToken(mContext)) {
                    callback.onContinue(postcard);
                } else {
                    callback.onInterrupt(new RuntimeException("未登陆"));
                    ARouter.getInstance().build(ARouterPaths.LOGIN).navigation();
                }
                break;
            default:
                callback.onContinue(postcard);
                break;
        }


    }

    @Override
    public void init(Context context) {
        mContext = context;
    }
}
