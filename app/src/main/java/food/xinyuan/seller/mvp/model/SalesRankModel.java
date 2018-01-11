package food.xinyuan.seller.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import java.util.List;

import javax.inject.Inject;

import food.xinyuan.seller.app.api.service.ShopService;
import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.response.SalesRank;
import food.xinyuan.seller.mvp.contract.SalesRankContract;
import io.reactivex.Observable;


@ActivityScope
public class SalesRankModel extends BaseModel implements SalesRankContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public SalesRankModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<HttpResponseData<List<SalesRank>>> getSalesRank(String queryDate) {
        return mRepositoryManager.obtainRetrofitService(ShopService.class).getSalesRank(queryDate);
    }
}