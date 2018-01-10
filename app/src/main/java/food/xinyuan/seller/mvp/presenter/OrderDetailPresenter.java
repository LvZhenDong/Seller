package food.xinyuan.seller.mvp.presenter;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import java.util.concurrent.TimeUnit;

import food.xinyuan.seller.app.config.applyOptions.factory.TransFactory;
import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.response.GeoInfo;
import food.xinyuan.seller.app.data.bean.response.Order;
import food.xinyuan.seller.app.data.bean.response.RiderLocation;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import food.xinyuan.seller.mvp.contract.OrderDetailContract;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


@ActivityScope
public class OrderDetailPresenter extends BasePresenter<OrderDetailContract.Model, OrderDetailContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public OrderDetailPresenter(OrderDetailContract.Model model, OrderDetailContract.View rootView
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

    public void getDetail(long id){
        mModel.getOrderDetail(id)
                .compose(TransFactory.noLoadingTrans(mRootView))
                .doOnNext(order -> mRootView.getDetailSuc(order))
                .observeOn(Schedulers.io())
                .flatMap(getInfo -> mModel.getGeoInfo(id))
                .compose(TransFactory.transStepTwo())
                .doFinally(() -> mRootView.hideLoading())
                .subscribe(new ErrorHandleSubscriber<GeoInfo>(mErrorHandler) {
                    @Override
                    public void onNext(GeoInfo geoInfo) {
                        mRootView.getGeoInfoSuc(geoInfo);
                    }
                });
    }

    public void callPhone(String phone){
        Intent intent = new Intent(Intent.ACTION_DIAL , Uri.parse("tel:" + phone));
        mApplication.startActivity(intent);
    }

    public void getRiderLoc(long id){
        mModel.getRiderLoc(id)
                .compose(TransFactory.noLoadingTrans(mRootView))
                .repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                        return Observable.interval(30, TimeUnit.SECONDS);
                    }
                })
                .subscribe(new ErrorHandleSubscriber<RiderLocation>(mErrorHandler) {
                    @Override
                    public void onNext(RiderLocation riderLocation) {
                        mRootView.getRiderLocSuc(riderLocation);
                    }
                });
    }

}
