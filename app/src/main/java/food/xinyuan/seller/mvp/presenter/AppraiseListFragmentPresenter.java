package food.xinyuan.seller.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import food.xinyuan.seller.app.config.applyOptions.factory.TransFactory;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.Appraise;
import food.xinyuan.seller.app.data.event.EventConstant;
import food.xinyuan.seller.app.data.event.SellerEvent;
import food.xinyuan.seller.app.utils.DataUtils;
import food.xinyuan.seller.app.utils.RequestUtils;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import food.xinyuan.seller.mvp.contract.AppraiseListFragmentContract;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


@ActivityScope
public class AppraiseListFragmentPresenter extends BasePresenter<AppraiseListFragmentContract.Model, AppraiseListFragmentContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public AppraiseListFragmentPresenter(AppraiseListFragmentContract.Model model, AppraiseListFragmentContract.View rootView
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

    int mType;
    private int mPageId = 1;
    Boolean reply = null;
    /**
     * 是否仅显示有回复内容的list
     */
    Boolean commentsAppraise = null;

    /**
     * @param type 0：全部；1：已回复；2：未回复
     */
    public void refireshList(int type) {
        mPageId = 1;
        mType = type;
        switch (type) {
            case 0:
                reply = null;
                break;
            case 1:
                reply = true;
                break;
            case 2:
                reply = false;
                break;
        }
        mModel.getAppraiseList(reply, commentsAppraise, mPageId)
                .compose(TransFactory.noLoadingTrans(mRootView))
                .doFinally(() -> {
                    mRootView.stopLoading();
                })
                .subscribe(new ErrorHandleSubscriber<ListResponse<Appraise>>(mErrorHandler) {
                    @Override
                    public void onNext(ListResponse<Appraise> appraiseListResponse) {
                        if (DataUtils.isEmpty(appraiseListResponse.getList())) {    //没有更多数据了
                            mRootView.noData();
                        } else {
                            mPageId++; //加载成功后pageNum加1方便下次加载下一页
                            mRootView.getListSuc(appraiseListResponse.getList());
                        }
                    }
                });

    }

    public void loadMore() {
        mModel.getAppraiseList(reply, commentsAppraise, mPageId)
                .compose(TransFactory.noLoadingTrans(mRootView))
                .doFinally(() -> mRootView.stopLoading())
                .subscribe(new ErrorHandleSubscriber<ListResponse<Appraise>>(mErrorHandler) {
                    @Override
                    public void onNext(ListResponse<Appraise> appraiseListResponse) {
                        if (DataUtils.isEmpty(appraiseListResponse.getList())) {    //没有数据
                            mRootView.noMoreData();
                        } else {
                            mPageId++; //加载成功后pageNum加1方便下次加载下一页
                            mRootView.loadMoreSuc(appraiseListResponse.getList());
                        }
                    }
                });
    }

    public void addAppraise(String content, Appraise appraise, int position) {
        mModel.addAppraise(RequestUtils.getRequestBody(new Appraise.CommentListBean(content, appraise.getShopAppraiseId())))
                .compose(TransFactory.commonTrans(mRootView))
                .subscribe(new ErrorHandleSubscriber<Appraise.CommentListBean>(mErrorHandler) {
                    @Override
                    public void onNext(Appraise.CommentListBean data) {
                        appraise.getCommentList().add(data);
                        //当前list直接更新本地数据
                        mRootView.addAppraiseSuc(position);
                        //其他2个list通过接口更新数据
                        EventBus.getDefault().post(new SellerEvent<Integer>(EventConstant.UPDATE_APPRAISE_LIST, mType));
                    }
                });
    }

}
