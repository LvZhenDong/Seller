package food.xinyuan.seller.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import food.xinyuan.seller.app.api.service.ShopService;
import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.Goods;
import food.xinyuan.seller.app.data.bean.response.GoodsCategory;
import food.xinyuan.seller.mvp.contract.AllGoodsContract;
import io.reactivex.Observable;


public class AllGoodsModel extends BaseModel implements AllGoodsContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public AllGoodsModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<HttpResponseData<ListResponse<GoodsCategory>>> getGoodsCategory() {
        return mRepositoryManager.obtainRetrofitService(ShopService.class).getGoodsCategory();
    }

    @Override
    public Observable<HttpResponseData<ListResponse<Goods>>> getGoodsList(String id) {
        return mRepositoryManager.obtainRetrofitService(ShopService.class).getGoodsList(id);
    }
}