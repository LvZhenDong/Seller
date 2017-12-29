package food.xinyuan.seller.mvp.presenter;

import android.app.Application;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import food.xinyuan.seller.app.config.applyOptions.factory.TransFactory;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.Goods;
import food.xinyuan.seller.app.data.bean.response.GoodsCategory;
import food.xinyuan.seller.mvp.contract.AllGoodsContract;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


@ActivityScope
public class AllGoodsPresenter extends BasePresenter<AllGoodsContract.Model, AllGoodsContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public AllGoodsPresenter(AllGoodsContract.Model model, AllGoodsContract.View rootView
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

    public void getInitData() {
        mModel.getGoodsCategory()
                .compose(TransFactory.commonTrans(mRootView))
                .subscribe(new ErrorHandleSubscriber<ListResponse<GoodsCategory>>(mErrorHandler) {
                    @Override
                    public void onNext(ListResponse<GoodsCategory> data) {
                        mRootView.getGoodsCategorySuc(data.getList());
                    }
                });
    }

    public void getGoodsList(int categoryId) {
        mModel.getGoodsList(categoryId + "")
                .compose(TransFactory.commonTrans(mRootView))
                .subscribe(new ErrorHandleSubscriber<ListResponse<Goods>>(mErrorHandler) {
                    @Override
                    public void onNext(ListResponse<Goods> data) {
                        mRootView.getGoodsSuc(data.getList());
                    }
                });
    }

}
