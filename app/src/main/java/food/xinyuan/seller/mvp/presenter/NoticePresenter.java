package food.xinyuan.seller.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import food.xinyuan.seller.app.config.applyOptions.factory.TransFactory;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.Notice;
import food.xinyuan.seller.app.utils.DataUtils;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import food.xinyuan.seller.mvp.contract.NoticeContract;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


@ActivityScope
public class NoticePresenter extends BasePresenter<NoticeContract.Model, NoticeContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public NoticePresenter(NoticeContract.Model model, NoticeContract.View rootView
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

    private int mPageId = 1;

    public void loadMoreNotice() {
        mModel.getNotice(mPageId)
                .compose(TransFactory.noLoadingTrans(mRootView))
                .doFinally(() -> {
                    mRootView.stopLoading();
                })
                .subscribe(new ErrorHandleSubscriber<ListResponse<Notice>>(mErrorHandler) {
                    @Override
                    public void onNext(ListResponse<Notice> noticeListResponse) {
                        if(DataUtils.isEmpty(noticeListResponse.getList())){    //没有更多数据了
                            mRootView.noMoreData();
                        }else {
                            mPageId++; //加载成功后pageNum加1方便下次加载下一页
                            mRootView.loadMoreSuc(noticeListResponse.getList());
                        }

                    }
                });
    }

    public void refreshNotice() {
        mPageId = 1;
        mModel.getNotice(mPageId)
                .compose(TransFactory.noLoadingTrans(mRootView))
                .doFinally(() -> {
                    mRootView.stopLoading();
                })
                .subscribe(new ErrorHandleSubscriber<ListResponse<Notice>>(mErrorHandler) {
                    @Override
                    public void onNext(ListResponse<Notice> noticeListResponse) {
                        if(DataUtils.isEmpty(noticeListResponse.getList())){    //没有更多数据了
                            mRootView.noData();
                        }else {
                            mPageId++; //加载成功后pageNum加1方便下次加载下一页
                            mRootView.getNoticesSuc(noticeListResponse.getList());
                        }

                    }
                });
    }

    public void delNotice(long id,int pos){
        mModel.delNotice(id)
                .compose(TransFactory.commonTransNoData(mRootView))
                .subscribe(new ErrorHandleSubscriber<Boolean>(mErrorHandler) {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        mRootView.delNoticeSuc(pos);
                    }
                });
    }

}
