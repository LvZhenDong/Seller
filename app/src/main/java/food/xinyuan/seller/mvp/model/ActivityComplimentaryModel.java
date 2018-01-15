package food.xinyuan.seller.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import food.xinyuan.seller.app.api.service.ActivityService;
import food.xinyuan.seller.app.api.service.ShopService;
import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.Coupon;
import food.xinyuan.seller.app.data.bean.response.ShopActivity;
import food.xinyuan.seller.mvp.contract.ActivityComplimentaryContract;
import io.reactivex.Observable;
import okhttp3.RequestBody;


@ActivityScope
public class ActivityComplimentaryModel extends BaseModel implements ActivityComplimentaryContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public ActivityComplimentaryModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<HttpResponseData<ListResponse<Coupon>>> getCouponList(int pageSize) {
        return mRepositoryManager.obtainRetrofitService(ShopService.class).getCouponList(pageSize);
    }

    @Override
    public Observable<HttpResponseData<ShopActivity>> addActivity(RequestBody json) {
        return mRepositoryManager.obtainRetrofitService(ActivityService.class).addActivity(json);
    }
}