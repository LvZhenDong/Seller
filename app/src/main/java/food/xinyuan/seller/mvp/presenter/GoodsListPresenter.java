package food.xinyuan.seller.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import food.xinyuan.seller.R;
import food.xinyuan.seller.app.config.applyOptions.factory.TransFactory;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.Goods;
import food.xinyuan.seller.app.utils.DataUtils;
import food.xinyuan.seller.app.utils.RequestUtils;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import food.xinyuan.seller.mvp.contract.GoodsListContract;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


@ActivityScope
public class GoodsListPresenter extends BasePresenter<GoodsListContract.Model, GoodsListContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public GoodsListPresenter(GoodsListContract.Model model, GoodsListContract.View rootView
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

    long groupId;
    private int mPageId = 1;

    public void refreshGoods(long categoryId){
        groupId=categoryId;
        mPageId=1;
        mModel.getGoodsList(categoryId + "",mPageId)
                .compose(TransFactory.noLoadingTrans(mRootView))
                .doFinally(() -> {
                    mRootView.stopLoading();
                })
                .subscribe(new ErrorHandleSubscriber<ListResponse<Goods>>(mErrorHandler) {
                    @Override
                    public void onNext(ListResponse<Goods> data) {
                        //没有数据
                        if(DataUtils.isEmpty(data.getList())){
                            mRootView.noData();
                        }else {
                            mPageId++; //加载成功后pageNum加1方便下次加载下一页
                            mRootView.getGoodsSuc(data.getList());
                        }
                    }
                });
    }

    public void loadMoreGoods(long categoryId){
        mModel.getGoodsList(categoryId+"",mPageId)
                .compose(TransFactory.noLoadingTrans(mRootView))
                .doFinally(() -> {
                    mRootView.stopLoading();
                })
                .subscribe(new ErrorHandleSubscriber<ListResponse<Goods>>(mErrorHandler) {
                    @Override
                    public void onNext(ListResponse<Goods> data) {
                        //没有更多数据了
                        if(DataUtils.isEmpty(data.getList())){
                            mRootView.noMoreData();
                        }else {
                            mPageId++; //加载成功后pageNum加1方便下次加载下一页
                            mRootView.loadMoreSuc(data.getList());
                        }
                    }
                });

    }

    public void soldOutGoods(Goods item,int pos){
        Long[] array={item.getGoodsId()};
        mModel.soldOutGoods(RequestUtils.getRequestBody(array))
                .compose(TransFactory.commonTransNoData(mRootView))
                .subscribe(new ErrorHandleSubscriber<Boolean>(mErrorHandler) {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        item.setPutAway(false);
                        mRootView.soldOutGoodsSuc(pos);
                    }
                });
    }

    public void putawayGoods(Goods item,int pos){
        Long[] array={item.getGoodsId()};
        mModel.putawayGoods(RequestUtils.getRequestBody(array))
                .compose(TransFactory.commonTransNoData(mRootView))
                .subscribe(new ErrorHandleSubscriber<Boolean>(mErrorHandler) {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        item.setPutAway(true);
                        mRootView.putAwaySuc(pos);
                    }
                });
    }

    public void delGoods(Long goodsId,int pos){
        mModel.deleteGoods(goodsId+"")
                .compose(TransFactory.commonTransNoData(mRootView))
                .subscribe(new ErrorHandleSubscriber<Boolean>(mErrorHandler) {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        mRootView.delSuc(pos);
                    }
                });
    }
}
