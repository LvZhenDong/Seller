package food.xinyuan.seller.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import food.xinyuan.seller.app.api.service.GoodsService;
import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.Goods;
import food.xinyuan.seller.mvp.contract.ActGoodsManageContract;
import io.reactivex.Observable;
import okhttp3.RequestBody;


@ActivityScope
public class ActGoodsManageModel extends BaseModel implements ActGoodsManageContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public ActGoodsManageModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<HttpResponseData<ListResponse<Goods>>> getAllGoods() {
        return mRepositoryManager.obtainRetrofitService(GoodsService.class).searchGoods(null,Integer.MAX_VALUE);
    }

    @Override
    public Observable<HttpResponseData> setActGoods(long activityId, RequestBody json) {
        return mRepositoryManager.obtainRetrofitService(GoodsService.class).setActGoods(activityId, json);
    }
}