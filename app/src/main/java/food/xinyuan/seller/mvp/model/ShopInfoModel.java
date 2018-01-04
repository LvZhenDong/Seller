package food.xinyuan.seller.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import food.xinyuan.seller.app.api.service.ShopService;
import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.response.ShopDetail;
import food.xinyuan.seller.mvp.contract.ShopInfoContract;
import io.reactivex.Observable;


@ActivityScope
public class ShopInfoModel extends BaseModel implements ShopInfoContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public ShopInfoModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<HttpResponseData<ShopDetail>> getShopDetail() {
        return mRepositoryManager.obtainRetrofitService(ShopService.class).getShopDetail();
    }

    @Override
    public Observable<HttpResponseData> delOperating() {
        return mRepositoryManager.obtainRetrofitService(ShopService.class).delOperating();
    }

    @Override
    public Observable<HttpResponseData> putOperating() {
        return mRepositoryManager.obtainRetrofitService(ShopService.class).putOperating();
    }

    @Override
    public Observable<HttpResponseData> delAutoOrder() {
        return mRepositoryManager.obtainRetrofitService(ShopService.class).delAutoOrder();
    }

    @Override
    public Observable<HttpResponseData> putAutoOrder() {
        return mRepositoryManager.obtainRetrofitService(ShopService.class).putAutoOrder();
    }

    @Override
    public Observable<HttpResponseData> delDrawInvoice() {
        return mRepositoryManager.obtainRetrofitService(ShopService.class).delDrawInvoice();
    }

    @Override
    public Observable<HttpResponseData> putDrawInvoice() {
        return mRepositoryManager.obtainRetrofitService(ShopService.class).putDrawInvoice();
    }

    @Override
    public Observable<HttpResponseData> changePhone(String phone) {
        return mRepositoryManager.obtainRetrofitService(ShopService.class).changeShopPhone(phone);
    }

    @Override
    public Observable<HttpResponseData> changeMinDeliveryPrice(String price) {
        return mRepositoryManager.obtainRetrofitService(ShopService.class).changeMinDeliveryPrice(price);
    }

}