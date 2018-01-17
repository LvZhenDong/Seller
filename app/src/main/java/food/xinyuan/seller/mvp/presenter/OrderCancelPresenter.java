package food.xinyuan.seller.mvp.presenter;

import android.app.Application;
import android.text.TextUtils;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.utils.ArmsUtils;

import org.greenrobot.eventbus.EventBus;

import food.xinyuan.seller.app.config.applyOptions.factory.TransFactory;
import food.xinyuan.seller.app.data.bean.request.CancelOrder;
import food.xinyuan.seller.app.data.bean.response.Order;
import food.xinyuan.seller.app.utils.Constant;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import food.xinyuan.seller.mvp.contract.OrderCancelContract;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


@ActivityScope
public class OrderCancelPresenter extends BasePresenter<OrderCancelContract.Model, OrderCancelContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public OrderCancelPresenter(OrderCancelContract.Model model, OrderCancelContract.View rootView
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

    public void cancelOrder(String content,long id){
        if(TextUtils.isEmpty(content)){
            ArmsUtils.makeText(mApplication.getApplicationContext(),"请输入取消理由");
        }else {
            CancelOrder cancelOrder=new CancelOrder(content,id);
            mModel.cancelOrder(cancelOrder)
                    .compose(TransFactory.commonTrans(mRootView))
                    .subscribe(new ErrorHandleSubscriber<Order>(mErrorHandler) {
                        @Override
                        public void onNext(Order order) {
                            ArmsUtils.makeText(mApplication.getApplicationContext(),"取消成功");
                            //刷新已接单,已取消，全部list
                            EventBus.getDefault().post(Constant.ORDER_STATUS_RECEIPT);
                            EventBus.getDefault().post(Constant.ORDER_STATUS_CANCELED);
                            mRootView.cancelOrderSuc();
                        }
                    });
        }
    }

}
