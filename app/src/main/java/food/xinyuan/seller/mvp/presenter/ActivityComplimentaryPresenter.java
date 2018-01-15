package food.xinyuan.seller.mvp.presenter;

import android.app.Application;
import android.text.TextUtils;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.utils.ArmsUtils;

import java.util.List;

import food.xinyuan.seller.app.config.applyOptions.factory.TransFactory;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.Coupon;
import food.xinyuan.seller.app.data.bean.response.ShopActivity;
import food.xinyuan.seller.app.utils.ConstantUtil;
import food.xinyuan.seller.app.utils.DataUtils;
import food.xinyuan.seller.app.utils.RequestUtils;
import food.xinyuan.seller.app.utils.XDateUtils;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import food.xinyuan.seller.mvp.contract.ActivityComplimentaryContract;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


@ActivityScope
public class ActivityComplimentaryPresenter extends BasePresenter<ActivityComplimentaryContract.Model, ActivityComplimentaryContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public ActivityComplimentaryPresenter(ActivityComplimentaryContract.Model model, ActivityComplimentaryContract.View rootView
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

    public void getCouponList() {
        mModel.getCouponList(Integer.MAX_VALUE)
                .compose(TransFactory.commonTrans(mRootView))
                .subscribe(new ErrorHandleSubscriber<ListResponse<Coupon>>(mErrorHandler) {
                    @Override
                    public void onNext(ListResponse<Coupon> couponListResponse) {
                        if (DataUtils.isEmpty(couponListResponse.getList())) {
                            ArmsUtils.makeText(mApplication, "该店铺还没有红包，请先去添加红包");
                        }
                        mRootView.getCouponListSuc(couponListResponse.getList());
                    }
                });
    }

    public void addActivity(String startTime, String endTime, String minStr, String countStr,
                            int selectedCoupon, List<Coupon> list) {
        if (TextUtils.isEmpty(minStr)) {
            ArmsUtils.makeText(mApplication, "请输入最低消费金额");
        } else if (selectedCoupon < 0) {
            ArmsUtils.makeText(mApplication, "请选择红包种类");
        } else if (TextUtils.isEmpty(countStr)) {
            ArmsUtils.makeText(mApplication, "请输入赠送红包数量");
        } else {
            double min = new Double(minStr);
            int count = new Integer(countStr);
            int couponId=list.get(selectedCoupon).getCouponId();
            // TODO typeName没有传
            ShopActivity shopActivity=new ShopActivity(ConstantUtil.ACTIVITY_TYPE_COMPLIMENTARY,
                    XDateUtils.string2Millis(startTime,"yyyy-MM-dd"),
                    XDateUtils.string2Millis(endTime,"yyyy-MM-dd"),
                    new ShopActivity.ActivityContentBean(couponId,min,count));

            mModel.addActivity(RequestUtils.getRequestBody(shopActivity))
                    .compose(TransFactory.commonTrans(mRootView))
                    .subscribe(new ErrorHandleSubscriber<ShopActivity>(mErrorHandler) {
                        @Override
                        public void onNext(ShopActivity shopActivity) {
                            ArmsUtils.makeText(mApplication, "添加成功");
                            mRootView.addActivitySuc();
                        }
                    });
        }

    }

}
