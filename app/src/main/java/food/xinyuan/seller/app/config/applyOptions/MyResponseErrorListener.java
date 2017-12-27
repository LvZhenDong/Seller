package food.xinyuan.seller.app.config.applyOptions;

import android.content.Context;

import com.jess.arms.utils.ArmsUtils;
import food.xinyuan.seller.app.utils.ConstantUtil;


import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;
import retrofit2.HttpException;
import timber.log.Timber;

/**
 * Created by f-x on 2017/12/9  10:31
 * Description
 */

public class MyResponseErrorListener implements ResponseErrorListener {
    @Override
    public void handleResponseError(Context context, Throwable t) {
        /* 用来提供处理所有错误的监听
           rxjava必要要使用ErrorHandleSubscriber(默认实现Subscriber的onError方法),此监听才生效 */
        if (t instanceof HttpException) {
            Timber.e("网络错误" + t.getMessage());
        }
        if (t instanceof RuntimeException) {
            Timber.e("运行错误" + t.getMessage());
        }
        ArmsUtils.snackbarText(t.getMessage(), ConstantUtil.SNACK_ERROR);
    }
}
