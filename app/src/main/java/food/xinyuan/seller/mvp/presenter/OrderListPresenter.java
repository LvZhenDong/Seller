package food.xinyuan.seller.mvp.presenter;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.utils.ArmsUtils;

import org.greenrobot.eventbus.EventBus;

import food.xinyuan.seller.app.config.applyOptions.factory.TransFactory;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.Order;
import food.xinyuan.seller.app.utils.ConstantUtil;
import food.xinyuan.seller.app.utils.DataUtils;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import food.xinyuan.seller.mvp.contract.OrderListContract;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


@ActivityScope
public class OrderListPresenter extends BasePresenter<OrderListContract.Model, OrderListContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public OrderListPresenter(OrderListContract.Model model, OrderListContract.View rootView
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

    private int mPageId = 1;

    public void refreshOrder(String status){
        mPageId = 1;
        mModel.getOrderList(mPageId,status)
                .compose(TransFactory.noLoadingTrans(mRootView))
                .doFinally(() -> {
                    mRootView.stopLoading();
                })
                .subscribe(new ErrorHandleSubscriber<ListResponse<Order>>(mErrorHandler) {
                    @Override
                    public void onNext(ListResponse<Order> orderListResponse) {
                        if(DataUtils.isEmpty(orderListResponse.getList())){    //没有数据
                            mRootView.noData();
                        }else {
                            mPageId++; //加载成功后pageNum加1方便下次加载下一页
                            mRootView.getListSuc(orderListResponse.getList());
                        }
                    }
                });
    }

    public void loadMoreOrder(String status){
        mModel.getOrderList(mPageId,status)
                .compose(TransFactory.noLoadingTrans(mRootView))
                .doFinally(() -> {
                    mRootView.stopLoading();
                })
                .subscribe(new ErrorHandleSubscriber<ListResponse<Order>>(mErrorHandler) {
                    @Override
                    public void onNext(ListResponse<Order> orderListResponse) {
                        if(DataUtils.isEmpty(orderListResponse.getList())){    //没有数据
                            mRootView.noMoreData();
                        }else {
                            mPageId++; //加载成功后pageNum加1方便下次加载下一页
                            mRootView.loadMoreSuc(orderListResponse.getList());
                        }
                    }
                });
    }

    public void printOrder(long id){
        mModel.printOrder(id)
                .compose(TransFactory.commonTransNoData(mRootView))
                .subscribe(new ErrorHandleSubscriber<Boolean>(mErrorHandler) {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        ArmsUtils.makeText(mApplication.getApplicationContext(),"打印成功");
                    }
                });
    }

    public void receiptOrder(Order order){
        mModel.receiptOrder(order.getOrderId())
                .compose(TransFactory.commonTrans(mRootView))
                .subscribe(new ErrorHandleSubscriber<Order>(mErrorHandler) {
                    @Override
                    public void onNext(Order data) {
                        ArmsUtils.makeText(mApplication.getApplicationContext(),"接单成功");
                        //刷新已接单，全部订单
                        EventBus.getDefault().post(ConstantUtil.ORDER_STATUS_RECEIPT);
                    }
                });
    }

    public void callPhone(String phone){
        Intent intent = new Intent(Intent.ACTION_DIAL , Uri.parse("tel:" + phone));
        mApplication.startActivity(intent);
    }

}
