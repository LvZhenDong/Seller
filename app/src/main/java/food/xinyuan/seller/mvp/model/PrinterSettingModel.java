package food.xinyuan.seller.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import food.xinyuan.seller.app.api.service.ShopService;
import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.Printer;
import food.xinyuan.seller.mvp.contract.PrinterSettingContract;
import io.reactivex.Observable;


@ActivityScope
public class PrinterSettingModel extends BaseModel implements PrinterSettingContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public PrinterSettingModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<HttpResponseData<ListResponse<Printer>>> getPrinters() {
        //由于打印机数量一般较少，所以这里一次加载所有的设备，不做分页
        return mRepositoryManager.obtainRetrofitService(ShopService.class).getPrinters(Integer.MAX_VALUE);
    }

    @Override
    public Observable<HttpResponseData> delPrinter(String printerId) {
        return mRepositoryManager.obtainRetrofitService(ShopService.class).delPrinter(printerId);
    }
}