package food.xinyuan.seller.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import food.xinyuan.seller.app.api.service.ActivityService;
import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.ShopActivity;
import food.xinyuan.seller.mvp.contract.ActivityManageContract;
import io.reactivex.Observable;


@ActivityScope
public class ActivityManageModel extends BaseModel implements ActivityManageContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public ActivityManageModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<HttpResponseData<ListResponse<ShopActivity>>> getActivityList(int pageSize) {
        return mRepositoryManager.obtainRetrofitService(ActivityService.class).getActivityList(pageSize);
    }

    @Override
    public Observable<HttpResponseData> delActivity(long id) {
        return mRepositoryManager.obtainRetrofitService(ActivityService.class).delActivity(id);
    }
}