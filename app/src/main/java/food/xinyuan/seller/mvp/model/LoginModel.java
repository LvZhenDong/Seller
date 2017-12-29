package food.xinyuan.seller.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import food.xinyuan.seller.app.api.service.CommService;
import food.xinyuan.seller.app.api.service.UserService;
import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.response.LoginResponse;
import food.xinyuan.seller.mvp.contract.LoginContract;
import io.reactivex.Observable;
import okhttp3.RequestBody;


@ActivityScope
public class LoginModel extends BaseModel implements LoginContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public LoginModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<HttpResponseData<String>> getCode(String phone) {
        return mRepositoryManager.obtainRetrofitService(CommService.class).getCode(phone);
    }

    @Override
    public Observable<HttpResponseData<LoginResponse>> login(String json) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        return mRepositoryManager.obtainRetrofitService(UserService.class).login(body);
    }
}