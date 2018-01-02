package food.xinyuan.seller.app.config.applyOptions.factory;

import com.jess.arms.mvp.IView;
import com.jess.arms.utils.RxLifecycleUtils;

import food.xinyuan.seller.app.data.bean.HttpResponseData;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2017/12/29
 */
public class TransFactory {

    /**
     * 适用于普通单个请求
     * @param rootView
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<HttpResponseData<T>, T> commonTrans(IView rootView) {
        return (ObservableTransformer<HttpResponseData<T>, T>) upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(3, 2))
                .compose(RxLifecycleUtils.bindToLifecycle(rootView))
                .doOnSubscribe(disposable -> {
                    rootView.showLoading();
                })
                .doFinally(() -> {
                    rootView.hideLoading();
                })
                .map(responseData -> {
                    if (responseData.isStatus()) {
                        return responseData.getData();
                    } else {
                        throw new RuntimeException(responseData.getMessage());
                    }
                });

    }
}