package food.xinyuan.seller.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import food.xinyuan.seller.app.api.service.ShopService;
import food.xinyuan.seller.app.api.service.UserService;
import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.response.LoginResponse;
import food.xinyuan.seller.app.data.bean.response.ShopDetail;
import food.xinyuan.seller.app.data.bean.response.ShopStatistics;
import food.xinyuan.seller.mvp.contract.HomeContract;
import io.reactivex.Observable;


@ActivityScope
public class HomeModel extends BaseModel implements HomeContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public HomeModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<HttpResponseData<ShopStatistics>> getShopStatistics() {
        return mRepositoryManager.obtainRetrofitService(ShopService.class).getShopStatistics();
    }

    @Override
    public Observable<HttpResponseData<ShopDetail>> getShopDetail() {
        return mRepositoryManager.obtainRetrofitService(ShopService.class).getShopDetail();
    }

    @Override
    public Observable<HttpResponseData<LoginResponse>> refreshToken() {
        return mRepositoryManager.obtainRetrofitService(UserService.class).refreshToken();
    }
}