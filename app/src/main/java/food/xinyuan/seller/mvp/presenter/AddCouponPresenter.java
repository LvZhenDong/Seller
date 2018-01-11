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
import food.xinyuan.seller.app.data.bean.response.Coupon;
import food.xinyuan.seller.app.data.event.EventConstant;
import food.xinyuan.seller.app.utils.XDateUtils;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import food.xinyuan.seller.mvp.contract.AddCouponContract;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


@ActivityScope
public class AddCouponPresenter extends BasePresenter<AddCouponContract.Model, AddCouponContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public AddCouponPresenter(AddCouponContract.Model model, AddCouponContract.View rootView
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

    /**
     * 添加固定金额的红包
     * @param name
     * @param isPickUpAuto
     * @param isFixed
     * @param money 红包金额
     * @param startTimeStr
     * @param endTimeStr
     * @param maxPickUpNumStr
     * @param minStr 最低消费额度
     */
    public void addFixedCoupon(String name, boolean isPickUpAuto, boolean isFixed, String money,
                               String startTimeStr, String endTimeStr, String maxPickUpNumStr, String minStr) {
        if (TextUtils.isEmpty(name)) {
            ArmsUtils.makeText(mApplication.getApplicationContext(), "请输入红包名称");
        } else if (TextUtils.isEmpty(money)) {
            ArmsUtils.makeText(mApplication.getApplicationContext(), "请输入红包金额");
        } else {
            long startTime = XDateUtils.string2Millis(startTimeStr, "yyyy-MM-dd");
            long endTime = XDateUtils.string2Millis(endTimeStr, "yyyy-MM-dd");

            int maxPickUpNum = 0;
            double min = 0;
            if (!TextUtils.isEmpty(maxPickUpNumStr)) maxPickUpNum = new Integer(maxPickUpNumStr);
            if (!TextUtils.isEmpty(minStr)) min = new Double(minStr);
            Coupon coupon = new Coupon(name, isPickUpAuto, isFixed,
                    new Double(money), startTime, endTime, maxPickUpNum, min);

            addCoupon(coupon);
        }

    }

    /**
     * 添加随机金额的红包
     * @param name
     * @param isPickUpAuto
     * @param isFixed
     * @param minMoney 最小随机金额
     * @param maxMoney 最大随机金额
     * @param startTimeStr
     * @param endTimeStr
     * @param maxPickUpNumStr
     * @param minStr 最低消费额度
     */
    public void addRandomCoupon(String name, boolean isPickUpAuto, boolean isFixed, String minMoney, String maxMoney,
                                String startTimeStr, String endTimeStr, String maxPickUpNumStr, String minStr) {
        if (TextUtils.isEmpty(name)) {
            ArmsUtils.makeText(mApplication.getApplicationContext(), "请输入红包名称");
        } else if (TextUtils.isEmpty(minMoney)) {
            ArmsUtils.makeText(mApplication.getApplicationContext(), "请输入最小随机金额");
        } else if (TextUtils.isEmpty(maxMoney)) {
            ArmsUtils.makeText(mApplication.getApplicationContext(), "请输入最大随机金额");
        } else {
            long startTime = XDateUtils.string2Millis(startTimeStr, "yyyy-MM-dd");
            long endTime = XDateUtils.string2Millis(endTimeStr, "yyyy-MM-dd");

            int maxPickUpNum = 0;
            double min = 0;
            if (!TextUtils.isEmpty(maxPickUpNumStr)) maxPickUpNum = new Integer(maxPickUpNumStr);
            if (!TextUtils.isEmpty(minStr)) min = new Double(minStr);
            Coupon coupon = new Coupon(name, isPickUpAuto, isFixed,
                    new Double(minMoney), new Double(maxMoney), startTime, endTime, maxPickUpNum, min);

            addCoupon(coupon);
        }
    }

    private void addCoupon(Coupon coupon){
        mModel.addCoupon(coupon)
                .compose(TransFactory.commonTrans(mRootView))
                .subscribe(new ErrorHandleSubscriber<Coupon>(mErrorHandler) {
                    @Override
                    public void onNext(Coupon coupon) {
                        ArmsUtils.makeText(mApplication.getApplicationContext(), "添加成功");
                        EventBus.getDefault().post(EventConstant.UPDATE_COUPON_LIST);
                        mRootView.addCouponSuc();
                    }
                });
    }

}
