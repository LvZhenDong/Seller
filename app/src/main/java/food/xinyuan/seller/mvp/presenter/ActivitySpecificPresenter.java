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
import food.xinyuan.seller.app.data.bean.response.ShopActivity;
import food.xinyuan.seller.app.data.event.EventConstant;
import food.xinyuan.seller.app.utils.RequestUtils;
import food.xinyuan.seller.app.utils.XDateUtils;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import food.xinyuan.seller.mvp.contract.ActivitySpecificContract;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


@ActivityScope
public class ActivitySpecificPresenter extends BasePresenter<ActivitySpecificContract.Model, ActivitySpecificContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public ActivitySpecificPresenter(ActivitySpecificContract.Model model, ActivitySpecificContract.View rootView
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

    public void addActivity(String startTimeStr,String endTimeStr,String name){
        ShopActivity shopActivity=createShopActivity(startTimeStr, endTimeStr, name);
        if(shopActivity != null){
            mModel.addActivity(RequestUtils.getRequestBody(shopActivity))
                    .compose(TransFactory.commonTrans(mRootView))
                    .subscribe(new ErrorHandleSubscriber<ShopActivity>(mErrorHandler) {
                        @Override
                        public void onNext(ShopActivity shopActivity) {
                            ArmsUtils.makeText(mApplication, "添加成功");
                            EventBus.getDefault().post(EventConstant.UPDATE_ACTIVITY_LIST);
                            mRootView.addActivitySuc();
                        }
                    });
        }
    }

    public void updateActivity(String startTimeStr,String endTimeStr,String name,long activityId){
        ShopActivity shopActivity=createShopActivity(startTimeStr, endTimeStr, name);
        if(shopActivity != null){
            mModel.updateActivity(activityId,RequestUtils.getRequestBody(shopActivity))
                    .compose(TransFactory.commonTrans(mRootView))
                    .subscribe(new ErrorHandleSubscriber<ShopActivity>(mErrorHandler) {
                        @Override
                        public void onNext(ShopActivity shopActivity) {
                            ArmsUtils.makeText(mApplication, "修改成功");
                            EventBus.getDefault().post(EventConstant.UPDATE_ACTIVITY_LIST);
                            mRootView.addActivitySuc();
                        }
                    });
        }
    }

    private ShopActivity createShopActivity(String startTimeStr,String endTimeStr,String name){
        if(TextUtils.isEmpty(name)){
            ArmsUtils.makeText(mApplication,"请输入活动名称");
        }else {
            ShopActivity shopActivity=new ShopActivity();
            shopActivity.setSepcificActivity(XDateUtils.string2Millis(startTimeStr,"yyyy-MM-dd"),
                    XDateUtils.string2Millis(endTimeStr,"yyyy-MM-dd"),
                    name);

            return shopActivity;
        }

        return null;
    }

}
