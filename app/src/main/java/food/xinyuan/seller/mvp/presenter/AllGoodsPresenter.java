package food.xinyuan.seller.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.utils.RxLifecycleUtils;

import java.util.List;

import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.Goods;
import food.xinyuan.seller.app.data.bean.response.GoodsCategory;
import food.xinyuan.seller.app.data.bean.response.Printer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import food.xinyuan.seller.mvp.contract.AllGoodsContract;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;


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
    
    public void getInitData(){
        mModel.getGoodsCategory()
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .doOnSubscribe(disposable -> {
                    mRootView.showLoading();
                })
                .doFinally(() -> {
                    mRootView.hideLoading();
                })
                .map(new Function<HttpResponseData<ListResponse<GoodsCategory>>, ListResponse<GoodsCategory>>() {
                    @Override
                    public ListResponse<GoodsCategory> apply(HttpResponseData<ListResponse<GoodsCategory>> responseData) throws Exception {
                        if (responseData.isStatus()) {
                            return responseData.getData();
                        } else {
                            throw new RuntimeException(responseData.getMessage());
                        }
                    }
                })
                .subscribe(new ErrorHandleSubscriber<ListResponse<GoodsCategory>>(mErrorHandler) {
                    @Override
                    public void onNext(ListResponse<GoodsCategory> data) {
                        mRootView.getGoodsCategorySuc(data.getList());
                    }
                });
    }

    public void getGoodsList(int categoryId){
        mModel.getGoodsList(categoryId+"")
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .doOnSubscribe(disposable -> {
                    mRootView.showLoading();
                })
                .doFinally(() -> {
                    mRootView.hideLoading();
                })
                .map(new Function<HttpResponseData<ListResponse<Goods>>, ListResponse<Goods>>() {
                    @Override
                    public ListResponse<Goods> apply(HttpResponseData<ListResponse<Goods>> responseData) throws Exception {
                        if (responseData.isStatus()) {
                            return responseData.getData();
                        } else {
                            throw new RuntimeException(responseData.getMessage());
                        }
                    }
                })
                .subscribe(new ErrorHandleSubscriber<ListResponse<Goods>>(mErrorHandler) {
                    @Override
                    public void onNext(ListResponse<Goods> data) {
                        mRootView.getGoodsSuc(data.getList());
                    }
                });
    }

}
