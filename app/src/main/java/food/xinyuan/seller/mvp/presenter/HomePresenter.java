package food.xinyuan.seller.mvp.presenter;

import android.app.Application;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;

import javax.inject.Inject;

import food.xinyuan.seller.app.config.applyOptions.factory.TransFactory;
import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.response.LoginResponse;
import food.xinyuan.seller.app.data.bean.response.ShopDetail;
import food.xinyuan.seller.app.data.bean.response.ShopStatistics;
import food.xinyuan.seller.app.utils.DataUtils;
import food.xinyuan.seller.app.utils.MySharePreferencesManager;
import food.xinyuan.seller.mvp.contract.HomeContract;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
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

    public void getInitData() {
        //刷新token→请求shop统计数据→请求shop detail
        mModel.refreshToken()
                .compose(TransFactory.commonTrans(mRootView))
                .doOnNext(loginResponse -> {
                    //保存token以及用户信息
                    MySharePreferencesManager.getInstance().putString("token", loginResponse.getJwt());
                    DataUtils.setToken(mApplication.getApplicationContext(), loginResponse.getJwt());
                    DataUtils.setUser(mApplication.getApplicationContext(), loginResponse);
                })
                .observeOn(Schedulers.io())
                .flatMap(loginResponse -> {
                    //请求shop统计数据
                    return mModel.getShopStatistics();
                })
                .compose(TransFactory.transStepTwo())
                .doOnNext(shopStatistics -> {
                    //显示shop统计数据
                    mRootView.getShopStatisticsSuc(shopStatistics);
                })
                .observeOn(Schedulers.io())
                .flatMap(shopStatistics -> {
                    //请求shop detail
                    return mModel.getShopDetail();
                })
                .compose(TransFactory.transStepTwo())
                .subscribe(new ErrorHandleSubscriber<ShopDetail>(mErrorHandler) {
                    @Override
                    public void onNext(ShopDetail data) {
                        //显示shop detail
                        mRootView.getShopDetailSuc(data);
                    }
                });

    }


}
