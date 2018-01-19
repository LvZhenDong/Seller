package food.xinyuan.seller.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.utils.ArmsUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import food.xinyuan.seller.app.config.applyOptions.factory.TransFactory;
import food.xinyuan.seller.app.data.bean.response.Delgolds;
import food.xinyuan.seller.app.data.bean.response.ShopActivity;
import food.xinyuan.seller.app.data.event.EventConstant;
import food.xinyuan.seller.app.utils.DataUtils;
import food.xinyuan.seller.app.utils.RequestUtils;
import food.xinyuan.seller.app.utils.XDateUtils;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import food.xinyuan.seller.mvp.contract.ActivityDelgoldContract;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


@ActivityScope
public class ActivityDelgoldPresenter extends BasePresenter<ActivityDelgoldContract.Model, ActivityDelgoldContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public ActivityDelgoldPresenter(ActivityDelgoldContract.Model model, ActivityDelgoldContract.View rootView
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

    private ShopActivity createShopActivity(String startTime, String endTime,
                                            List<Delgolds> list){
        if(DataUtils.isEmpty(list)){
            ArmsUtils.makeText(mApplication, "请输入满减金额");
        }else {
            ShopActivity shopActivity=new ShopActivity();
            shopActivity.setDelgoldActivity(XDateUtils.string2Millis(startTime,"yyyy-MM-dd"),
                    XDateUtils.string2Millis(endTime,"yyyy-MM-dd"),
                    list);
            return shopActivity;
        }

        return null;
    }

    public void addActivity(String startTime, String endTime,
                            List<Delgolds> list){
        ShopActivity shopActivity=createShopActivity(startTime, endTime, list);
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

    public void updateActivity(String startTime, String endTime,
                               List<Delgolds> list,long activityId){
        ShopActivity shopActivity=createShopActivity(startTime, endTime, list);
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

}
