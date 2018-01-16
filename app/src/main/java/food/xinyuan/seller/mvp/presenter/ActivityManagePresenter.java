package food.xinyuan.seller.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import food.xinyuan.seller.R;
import food.xinyuan.seller.app.config.applyOptions.factory.TransFactory;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.ShopActivity;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import food.xinyuan.seller.mvp.contract.ActivityManageContract;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


@ActivityScope
public class ActivityManagePresenter extends BasePresenter<ActivityManageContract.Model, ActivityManageContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public ActivityManagePresenter(ActivityManageContract.Model model, ActivityManageContract.View rootView
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

    public void getList(){
        mModel.getActivityList(Integer.MAX_VALUE)
                .compose(TransFactory.commonTrans(mRootView))
                .subscribe(new ErrorHandleSubscriber<ListResponse<ShopActivity>>(mErrorHandler) {
                    @Override
                    public void onNext(ListResponse<ShopActivity> shopActivityListResponse) {
                        mRootView.getListSuc(shopActivityListResponse.getList());
                    }
                });
    }

    /**
     * 刷新，但是不显示dialog
     */
    public void refresh(){
        mModel.getActivityList(Integer.MAX_VALUE)
                .compose(TransFactory.noLoadingTrans(mRootView))
                .subscribe(new ErrorHandleSubscriber<ListResponse<ShopActivity>>(mErrorHandler) {
                    @Override
                    public void onNext(ListResponse<ShopActivity> shopActivityListResponse) {
                        mRootView.getListSuc(shopActivityListResponse.getList());
                    }
                });
    }

    public void delActivity(long id,int pos){
        mModel.delActivity(id)
                .compose(TransFactory.commonTransNoData(mRootView))
                .subscribe(new ErrorHandleSubscriber<Boolean>(mErrorHandler) {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        mRootView.delActivitySuc(pos);
                    }
                });
    }

}
