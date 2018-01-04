package food.xinyuan.seller.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import food.xinyuan.seller.app.api.service.NoticeService;
import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.Notice;
import food.xinyuan.seller.mvp.contract.NoticeContract;
import io.reactivex.Observable;
import retrofit2.http.Query;


@ActivityScope
public class NoticeModel extends BaseModel implements NoticeContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public NoticeModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<HttpResponseData<ListResponse<Notice>>> getNotice(int pageId) {
        return mRepositoryManager.obtainRetrofitService(NoticeService.class).getNotice(pageId);
    }

    @Override
    public Observable<HttpResponseData> delNotice(long id) {
        return mRepositoryManager.obtainRetrofitService(NoticeService.class).delNotice(id);
    }
}