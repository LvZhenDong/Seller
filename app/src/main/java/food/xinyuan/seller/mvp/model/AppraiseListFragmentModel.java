package food.xinyuan.seller.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import food.xinyuan.seller.app.api.service.ShopService;
import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.Appraise;
import food.xinyuan.seller.mvp.contract.AppraiseListFragmentContract;
import io.reactivex.Observable;
import okhttp3.RequestBody;


@ActivityScope
public class AppraiseListFragmentModel extends BaseModel implements AppraiseListFragmentContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public AppraiseListFragmentModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
        super(repositoryManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<HttpResponseData<ListResponse<Appraise>>> getAppraiseList(Boolean reply, Boolean commentsAppraise, int pageId) {
        return mRepositoryManager.obtainRetrofitService(ShopService.class).getAppraiseList(reply, commentsAppraise, pageId);
    }

    @Override
    public Observable<HttpResponseData<Appraise.CommentListBean>> addAppraise(RequestBody json) {
        return mRepositoryManager.obtainRetrofitService(ShopService.class).addAppraise(json);
    }
}