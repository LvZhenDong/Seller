package food.xinyuan.seller.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import java.util.List;

import javax.inject.Inject;

import food.xinyuan.seller.app.api.service.GoodsService;
import food.xinyuan.seller.app.api.service.ShopService;
import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.GoodsCategory;
import food.xinyuan.seller.mvp.contract.GoodsCategoryContract;
import io.reactivex.Observable;


@ActivityScope
public class GoodsCategoryModel extends BaseModel implements GoodsCategoryContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public GoodsCategoryModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<HttpResponseData<List<GoodsCategory>>> getGoodsCategory() {
        //由于商品类型数量一般较少，所以这里一次加载所有的类型，不做分页
        return mRepositoryManager.obtainRetrofitService(GoodsService.class).getGoodsCategory(Integer.MAX_VALUE);
    }
}