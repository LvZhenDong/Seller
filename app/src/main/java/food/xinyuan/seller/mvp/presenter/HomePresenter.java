package food.xinyuan.seller.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.utils.RxLifecycleUtils;

import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.response.ShopDetail;
import food.xinyuan.seller.app.data.bean.response.ShopStatistics;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import food.xinyuan.seller.mvp.contract.HomeContract;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;


@ActivityScope
public class HomePresenter extends BasePresenter<HomeContract.Model, HomeContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public HomePresenter(HomeContract.Model model, HomeContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void getInitData(){
        //TODO 网络请求优化
        mModel.getShopStatistics()
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .doOnSubscribe(disposable -> {
                    mRootView.showLoading();
                })
                .doFinally(() -> {
                    mRootView.hideLoading();
                })
                .map(new Function<HttpResponseData<ShopStatistics>, ShopStatistics>() {
                    @Override
                    public ShopStatistics apply(HttpResponseData<ShopStatistics> responseData) throws Exception {
                        if (responseData.isStatus()) {
                            return responseData.getData();
                        } else {
                            throw new RuntimeException(responseData.getMessage());
                        }
                    }
                })
                .doOnNext(new Consumer<ShopStatistics>() {
                    @Override
                    public void accept(ShopStatistics shopStatistics) throws Exception {
                        mRootView.getShopStatisticsSuc(shopStatistics);
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<ShopStatistics, ObservableSource<HttpResponseData<ShopDetail>>>() {
                    @Override
                    public ObservableSource<HttpResponseData<ShopDetail>> apply(ShopStatistics shopStatistics) throws Exception {
                        return mModel.getShopDetail();
                    }
                })
                .map(new Function<HttpResponseData<ShopDetail>, ShopDetail>() {
                    @Override
                    public ShopDetail apply(HttpResponseData<ShopDetail> responseData) throws Exception {
                        if (responseData.isStatus()) {
                            return responseData.getData();
                        } else {
                            throw new RuntimeException(responseData.getMessage());
                        }
                    }
                })
                .subscribe(new ErrorHandleSubscriber<ShopDetail>(mErrorHandler) {
                    @Override
                    public void onNext(ShopDetail data) {
                        mRootView.getShopDetailSuc(data);
                    }
                });
    }

}
