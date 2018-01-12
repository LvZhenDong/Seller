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
import food.xinyuan.seller.app.data.bean.response.AppraiseStatistics;
import food.xinyuan.seller.mvp.contract.AppraiseManageContract;
import io.reactivex.Observable;


@ActivityScope
public class AppraiseManageModel extends BaseModel implements AppraiseManageContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public AppraiseManageModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<HttpResponseData<AppraiseStatistics>> getAppraiseStatistics() {
        return mRepositoryManager.obtainRetrofitService(ShopService.class).getAppraiseStatistics();
    }

}