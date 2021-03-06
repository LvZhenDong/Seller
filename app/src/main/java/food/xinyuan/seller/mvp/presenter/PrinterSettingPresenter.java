package food.xinyuan.seller.mvp.presenter;

import android.app.Application;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import food.xinyuan.seller.app.config.applyOptions.factory.TransFactory;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.Printer;
import food.xinyuan.seller.mvp.contract.PrinterSettingContract;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


@ActivityScope
public class PrinterSettingPresenter extends BasePresenter<PrinterSettingContract.Model, PrinterSettingContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public PrinterSettingPresenter(PrinterSettingContract.Model model, PrinterSettingContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void getPrinters(){
        mModel.getPrinters()
                .compose(TransFactory.commonTrans(mRootView))
                .subscribe(new ErrorHandleSubscriber<ListResponse<Printer>>(mErrorHandler) {
                    @Override
                    public void onNext(ListResponse<Printer> data) {
                        mRootView.getPrintersSuc(data.getList());
                    }
                });
    }

    public void refreshPrinters(){
        mModel.getPrinters()
                .compose(TransFactory.noLoadingTrans(mRootView))
                .subscribe(new ErrorHandleSubscriber<ListResponse<Printer>>(mErrorHandler) {
                    @Override
                    public void onNext(ListResponse<Printer> data) {
                        mRootView.getPrintersSuc(data.getList());
                    }
                });
    }

    public void delPrinter(String printerId,int pos){
        mModel.delPrinter(printerId)
                .compose(TransFactory.commonTransNoData(mRootView))
                .subscribe(new ErrorHandleSubscriber<Boolean>(mErrorHandler) {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        mRootView.delPrinterSuc(pos);
                    }
                });
    }
}
