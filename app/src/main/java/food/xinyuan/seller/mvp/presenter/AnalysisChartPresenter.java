package food.xinyuan.seller.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import java.util.Collections;
import java.util.List;

import food.xinyuan.seller.app.api.service.ShopService;
import food.xinyuan.seller.app.config.applyOptions.factory.TransFactory;
import food.xinyuan.seller.app.data.bean.response.NewCustomer;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import food.xinyuan.seller.mvp.contract.AnalysisChartContract;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


@ActivityScope
public class AnalysisChartPresenter extends BasePresenter<AnalysisChartContract.Model, AnalysisChartContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public AnalysisChartPresenter(AnalysisChartContract.Model model, AnalysisChartContract.View rootView
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

    public void getNewCustomer(){
        mModel.getNewCustomer(7)
                .compose(TransFactory.commonTrans(mRootView))
                .subscribe(new ErrorHandleSubscriber<List<NewCustomer>>(mErrorHandler) {
                    @Override
                    public void onNext(List<NewCustomer> newCustomers) {
                        Collections.reverse(newCustomers);
                        mRootView.getNewCustomerSuc(newCustomers);
                    }
                });
    }

    public void getOrderQuantity(){
        mModel.getOrderQuantity(7)
                .compose(TransFactory.commonTrans(mRootView))
                .subscribe(new ErrorHandleSubscriber<List<NewCustomer>>(mErrorHandler) {
                    @Override
                    public void onNext(List<NewCustomer> newCustomers) {
                        Collections.reverse(newCustomers);
                        mRootView.getNewCustomerSuc(newCustomers);
                    }
                });
    }

    public void getTurnover(){
        mModel.getTurnover(7)
                .compose(TransFactory.commonTrans(mRootView))
                .subscribe(new ErrorHandleSubscriber<List<NewCustomer>>(mErrorHandler) {
                    @Override
                    public void onNext(List<NewCustomer> newCustomers) {
                        Collections.reverse(newCustomers);
                        mRootView.getNewCustomerSuc(newCustomers);
                    }
                });
    }

    public void getGoodsSales(long goodsId){
        mModel.getGoodsSales(7,goodsId)
                .compose(TransFactory.commonTrans(mRootView))
                .subscribe(new ErrorHandleSubscriber<List<NewCustomer>>(mErrorHandler) {
                    @Override
                    public void onNext(List<NewCustomer> newCustomers) {
                        Collections.reverse(newCustomers);
                        mRootView.getNewCustomerSuc(newCustomers);
                    }
                });
    }


}
