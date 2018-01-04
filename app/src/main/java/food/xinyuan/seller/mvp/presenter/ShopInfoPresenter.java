package food.xinyuan.seller.mvp.presenter;

import android.app.Application;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import food.xinyuan.seller.app.config.applyOptions.factory.TransFactory;
import food.xinyuan.seller.app.data.bean.response.ShopDetail;
import food.xinyuan.seller.mvp.contract.ShopInfoContract;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


@ActivityScope
public class ShopInfoPresenter extends BasePresenter<ShopInfoContract.Model, ShopInfoContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public ShopInfoPresenter(ShopInfoContract.Model model, ShopInfoContract.View rootView
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

    public void getShopInfo(){
        mModel.getShopDetail()
                .compose(TransFactory.commonTrans(mRootView))
                .subscribe(new ErrorHandleSubscriber<ShopDetail>(mErrorHandler) {
                    @Override
                    public void onNext(ShopDetail shopDetail) {
                        mRootView.getShopInfoSuc(shopDetail);
                    }
                });
    }

    /**
     * 设置为歇业中
     */
    public void delOperating(){
        mModel.delOperating()
                .compose(TransFactory.commonTransNoData(mRootView))
                .subscribe(new ErrorHandleSubscriber<Boolean>(mErrorHandler) {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        mRootView.changeOperatingSuc(false);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        //修改状态失败，将switchButton改为点击之前的样子
                        mRootView.changeOperatingFail(true);
                    }
                });
    }

    /**
     * 设置为营业中
     */
    public void putOperating(){
        mModel.putOperating()
                .compose(TransFactory.commonTransNoData(mRootView))
                .subscribe(new ErrorHandleSubscriber<Boolean>(mErrorHandler) {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        mRootView.changeOperatingSuc(true);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        //修改状态失败，将switchButton改为点击之前的样子
                        mRootView.changeOperatingFail(false);
                    }
                });
    }

    public void delAutoOrder(){
        mModel.delAutoOrder()
                .compose(TransFactory.commonTransNoData(mRootView))
                .subscribe(new ErrorHandleSubscriber<Boolean>(mErrorHandler) {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        mRootView.changeAutoOrderSuc(false);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        //修改状态失败，将switchButton改为点击之前的样子
                        mRootView.changeAutoOrderFail(true);
                    }
                });
    }

    public void putAutoOrder(){
        mModel.putAutoOrder()
                .compose(TransFactory.commonTransNoData(mRootView))
                .subscribe(new ErrorHandleSubscriber<Boolean>(mErrorHandler) {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        mRootView.changeAutoOrderSuc(true);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        //修改状态失败，将switchButton改为点击之前的样子
                        mRootView.changeAutoOrderFail(false);
                    }
                });
    }

    /**
     * 允许开发票
     */
    public void putDrawInvoice(){
        mModel.putDrawInvoice()
                .compose(TransFactory.commonTransNoData(mRootView))
                .subscribe(new ErrorHandleSubscriber<Boolean>(mErrorHandler) {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        mRootView.changeDrawInvoiceSuc(true);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        //修改状态失败，将switchButton改为点击之前的样子
                        mRootView.changeDrawInvoiceFail(false);
                    }
                });
    }

    /**
     * 不允许开发票
     */
    public void delDrawInvoice(){
        mModel.delDrawInvoice()
                .compose(TransFactory.commonTransNoData(mRootView))
                .subscribe(new ErrorHandleSubscriber<Boolean>(mErrorHandler) {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        mRootView.changeDrawInvoiceSuc(false);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        //修改状态失败，将switchButton改为点击之前的样子
                        mRootView.changeDrawInvoiceFail(true);
                    }
                });
    }

    /**
     * 更改联系方式
     * @param phone
     */
    public void changePhone(String phone){
        mModel.changePhone(phone)
                .compose(TransFactory.commonTransNoData(mRootView))
                .subscribe(new ErrorHandleSubscriber<Boolean>(mErrorHandler) {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        mRootView.changePhoneSuc(phone);
                    }
                });
    }

    /**
     * 更改最低配送价格
     * @param price
     */
    public void changeMinDeliveryPrice(String price){
        mModel.changeMinDeliveryPrice(price)
                .compose(TransFactory.commonTransNoData(mRootView))
                .subscribe(new ErrorHandleSubscriber<Boolean>(mErrorHandler) {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        mRootView.changeMinDeliveryPriceSuc(price);
                    }
                });
    }

}
