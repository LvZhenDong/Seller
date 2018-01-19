package food.xinyuan.seller.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import food.xinyuan.seller.app.api.service.ActivityService;
import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.response.ShopActivity;
import food.xinyuan.seller.mvp.contract.ActivityDelgoldContract;
import io.reactivex.Observable;
import okhttp3.RequestBody;


@ActivityScope
public class ActivityDelgoldModel extends BaseModel implements ActivityDelgoldContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public ActivityDelgoldModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<HttpResponseData<ShopActivity>> addActivity(RequestBody json) {
        return mRepositoryManager.obtainRetrofitService(ActivityService.class).addActivity(json);
    }

    @Override
    public Observable<HttpResponseData<ShopActivity>> updateActivity(long activityId, RequestBody json) {
        return mRepositoryManager.obtainRetrofitService(ActivityService.class).updateActivity(activityId, json);
    }
}