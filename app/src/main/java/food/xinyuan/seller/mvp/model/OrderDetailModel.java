package food.xinyuan.seller.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import food.xinyuan.seller.app.api.service.OrderService;
import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.response.Order;
import food.xinyuan.seller.mvp.contract.OrderDetailContract;
import io.reactivex.Observable;


@ActivityScope
public class OrderDetailModel extends BaseModel implements OrderDetailContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public OrderDetailModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<HttpResponseData<Order>> getOrderDetail(long id) {
        return mRepositoryManager.obtainRetrofitService(OrderService.class).getOrderDetail(id);
    }
}