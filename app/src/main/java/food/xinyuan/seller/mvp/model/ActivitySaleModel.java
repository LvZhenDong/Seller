package food.xinyuan.seller.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import java.util.List;

import javax.inject.Inject;

import food.xinyuan.seller.app.api.service.ActivityService;
import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.response.ActGoods;
import food.xinyuan.seller.app.data.bean.response.ShopActivity;
import food.xinyuan.seller.mvp.contract.ActivitySaleContract;
import io.reactivex.Observable;
import okhttp3.RequestBody;


@ActivityScope
public class ActivitySaleModel extends BaseModel implements ActivitySaleContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public ActivitySaleModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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

    @Override
    public Observable<HttpResponseData<List<ActGoods>>> getActivityGoods(long activityId) {
        return mRepositoryManager.obtainRetrofitService(ActivityService.class).getActivityGoods(activityId);
    }
}