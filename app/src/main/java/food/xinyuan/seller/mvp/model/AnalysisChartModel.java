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
import food.xinyuan.seller.app.data.bean.response.NewCustomer;
import food.xinyuan.seller.mvp.contract.AnalysisChartContract;
import io.reactivex.Observable;


@ActivityScope
public class AnalysisChartModel extends BaseModel implements AnalysisChartContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public AnalysisChartModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<HttpResponseData<List<NewCustomer>>> getNewCustomer(int days) {
        return mRepositoryManager.obtainRetrofitService(ShopService.class).getNewCustomer(days);
    }

    @Override
    public Observable<HttpResponseData<List<NewCustomer>>> getOrderQuantity(int days) {
        return mRepositoryManager.obtainRetrofitService(ShopService.class).getOrderQuantity(days);
    }

    @Override
    public Observable<HttpResponseData<List<NewCustomer>>> getTurnover(int days) {
        return mRepositoryManager.obtainRetrofitService(ShopService.class).getTurnover(days);
    }

    @Override
    public Observable<HttpResponseData<List<NewCustomer>>> getGoodsSales(int days, long goodsId) {
        return mRepositoryManager.obtainRetrofitService(ShopService.class).getGoodsSales(days,goodsId);
    }
}