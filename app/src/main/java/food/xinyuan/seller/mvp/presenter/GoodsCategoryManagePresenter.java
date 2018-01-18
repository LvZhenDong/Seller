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
import food.xinyuan.seller.app.data.bean.response.GoodsCategory;
import food.xinyuan.seller.app.utils.RequestUtils;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import food.xinyuan.seller.mvp.contract.GoodsCategoryManageContract;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


@ActivityScope
public class GoodsCategoryManagePresenter extends BasePresenter<GoodsCategoryManageContract.Model, GoodsCategoryManageContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public GoodsCategoryManagePresenter(GoodsCategoryManageContract.Model model, GoodsCategoryManageContract.View rootView
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

    public void getGoodsCategory() {
        mModel.getGoodsCategory()
                .compose(TransFactory.commonTrans(mRootView))
                .subscribe(new ErrorHandleSubscriber<List<GoodsCategory>>(mErrorHandler) {
                    @Override
                    public void onNext(List<GoodsCategory> data) {
                        mRootView.getGoodsCategorySuc(data);
                    }
                });
    }

    public void addGoodsCategory(String name){
        if(TextUtils.isEmpty(name)){
            ArmsUtils.makeText(mApplication,"请输入分类名称");
            return;
        }

        mModel.addGoodsCategory(RequestUtils.getRequestBody(new GoodsCategory(name)))
                .compose(TransFactory.commonTrans(mRootView))
                .subscribe(new ErrorHandleSubscriber<GoodsCategory>(mErrorHandler) {
                    @Override
                    public void onNext(GoodsCategory goodsCategory) {
                        getGoodsCategory();
                        mRootView.addGoodsCategorySuc();
                    }
                });
    }

    public void delGoodsCategory(String id,int pos){
        mModel.delGoodsCategory(id)
                .compose(TransFactory.commonTransNoData(mRootView))
                .subscribe(new ErrorHandleSubscriber<Boolean>(mErrorHandler) {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        mRootView.delGoodsCategorySuc(pos);
                    }
                });
    }

}
