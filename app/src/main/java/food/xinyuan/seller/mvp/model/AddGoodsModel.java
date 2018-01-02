package food.xinyuan.seller.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import java.util.Map;

import javax.inject.Inject;

import food.xinyuan.seller.app.api.service.CommService;
import food.xinyuan.seller.app.api.service.ShopService;
import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.request.AddGoods;
import food.xinyuan.seller.app.data.bean.response.UploadFile;
import food.xinyuan.seller.app.utils.RequestUtils;
import food.xinyuan.seller.mvp.contract.AddGoodsContract;
import io.reactivex.Observable;
import okhttp3.RequestBody;


@ActivityScope
public class AddGoodsModel extends BaseModel implements AddGoodsContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public AddGoodsModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<HttpResponseData<UploadFile>> upLoadImg(Map<String, RequestBody> photo) {
        return mRepositoryManager.obtainRetrofitService(CommService.class).uploadFile("goods", photo);
    }

    @Override
    public Observable<HttpResponseData<AddGoods>> addGoods(AddGoods addGoods) {
        return mRepositoryManager.obtainRetrofitService(ShopService.class)
                .addGoods(RequestUtils.getRequestBody(addGoods));
    }
}