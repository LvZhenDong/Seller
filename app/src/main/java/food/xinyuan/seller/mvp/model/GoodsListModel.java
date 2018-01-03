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
import food.xinyuan.seller.app.data.bean.response.Goods;
import food.xinyuan.seller.mvp.contract.GoodsListContract;
import io.reactivex.Observable;
import okhttp3.RequestBody;


@ActivityScope
public class GoodsListModel extends BaseModel implements GoodsListContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public GoodsListModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<HttpResponseData<ListResponse<Goods>>> getGoodsList(String id) {
        return mRepositoryManager.obtainRetrofitService(ShopService.class).getGoodsList(id);
    }

    @Override
    public Observable<HttpResponseData> soldOutGoods(RequestBody json) {
        return mRepositoryManager.obtainRetrofitService(ShopService.class).soldOutGoods(json);
    }

    @Override
    public Observable<HttpResponseData>putawayGoods(RequestBody json) {
        return mRepositoryManager.obtainRetrofitService(ShopService.class).putawayGoods(json);
    }

    @Override
    public Observable<HttpResponseData> deleteGoods(String id) {
        return mRepositoryManager.obtainRetrofitService(ShopService.class).deleteGoods(id);
    }
}