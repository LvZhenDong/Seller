package food.xinyuan.seller.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.utils.ArmsUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import food.xinyuan.seller.app.config.applyOptions.factory.TransFactory;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.ActGoods;
import food.xinyuan.seller.app.data.bean.response.Goods;
import food.xinyuan.seller.app.data.event.EventConstant;
import food.xinyuan.seller.app.utils.RequestUtils;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import food.xinyuan.seller.mvp.contract.ActGoodsManageContract;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


@ActivityScope
public class ActGoodsManagePresenter extends BasePresenter<ActGoodsManageContract.Model, ActGoodsManageContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public ActGoodsManagePresenter(ActGoodsManageContract.Model model, ActGoodsManageContract.View rootView
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

    public void getGoods(){
        mModel.getAllGoods()
                .compose(TransFactory.commonTrans(mRootView))
                .subscribe(new ErrorHandleSubscriber<ListResponse<Goods>>(mErrorHandler) {
                    @Override
                    public void onNext(ListResponse<Goods> goodsListResponse) {
                        mRootView.getGoodsSuc(goodsListResponse.getList());
                    }
                });
    }

    public void setActGoods(long activityId, Map<String,ActGoods> map){
        mModel.setActGoods(activityId, RequestUtils.getRequestBody(map))
                .compose(TransFactory.commonTransNoData(mRootView))
                .subscribe(new ErrorHandleSubscriber<Boolean>(mErrorHandler) {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        ArmsUtils.makeText(mApplication,"修改成功");
                        EventBus.getDefault().post(EventConstant.UPDATE_ACT_GOODS);
                        mRootView.setActGoodsSuc();
                    }
                });
    }

}
