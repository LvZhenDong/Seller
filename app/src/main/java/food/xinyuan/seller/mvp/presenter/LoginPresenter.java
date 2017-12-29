package food.xinyuan.seller.mvp.presenter;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;

import javax.inject.Inject;

import food.xinyuan.seller.R;
import food.xinyuan.seller.app.config.applyOptions.factory.TransFactory;
import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.request.LoginRequest;
import food.xinyuan.seller.app.data.bean.response.LoginResponse;
import food.xinyuan.seller.app.utils.ConstantUtil;
import food.xinyuan.seller.app.utils.DataUtils;
import food.xinyuan.seller.app.utils.MySharePreferencesManager;
import food.xinyuan.seller.mvp.contract.LoginContract;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;


@ActivityScope
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public LoginPresenter(LoginContract.Model model, LoginContract.View rootView
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


    public void sendVerCode(String phone) {
        if (DataUtils.isEmpty(phone)) {
            mRootView.showSnackbarMsg(R.string.emptyPhone, ConstantUtil.SNACK_WARING);
        } else if (!DataUtils.checkMobile(phone)) {
            mRootView.showSnackbarMsg(R.string.errorPhone, ConstantUtil.SNACK_WARING);
        } else {
            mModel.getCode(phone)
                    .subscribeOn(Schedulers.io())
                    .retryWhen(new RetryWithDelay(3, 2))
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                    .doOnSubscribe(disposable -> {
                        mRootView.hideKeyboard();
                        mRootView.showLoading();
                    })
                    .doFinally(() -> {
                        mRootView.hideLoading();
                    })
                    .subscribe(new ErrorHandleSubscriber<HttpResponseData<String>>(mErrorHandler) {
                        @Override
                        public void onNext(HttpResponseData<String> stringHttpResponseData) {
                            mRootView.sendVerCodeSuc();
                            mRootView.showSnackbarMsg(R.string.send_ver_code_success, ConstantUtil.SNACK_NORMAL);
                        }
                    });
        }

    }

    public void login(String phone, String verCode) {
        if (DataUtils.isEmpty(phone)) {
            mRootView.showSnackbarMsg(R.string.emptyPhone, ConstantUtil.SNACK_WARING);
        } else if (!DataUtils.checkMobile(phone)) {
            mRootView.showSnackbarMsg(R.string.errorPhone, ConstantUtil.SNACK_WARING);
        } else {
            mRootView.hideKeyboard();
            LoginRequest loginRequest = new LoginRequest(phone, verCode);
            mModel.login(new Gson().toJson(loginRequest))
                    .compose(TransFactory.commonTrans(mRootView))
                    .subscribe(new ErrorHandleSubscriber<LoginResponse>(mErrorHandler) {
                        @Override
                        public void onNext(LoginResponse data) {
                            //TODO user以及token处理
                            MySharePreferencesManager.getInstance().putString("token", data.getJwt());
                            DataUtils.setToken(mApplication.getApplicationContext(), data.getJwt());
                            DataUtils.setUser(mApplication.getApplicationContext(),data);
                            mRootView.showSnackbarMsg("登录成功", ConstantUtil.SNACK_NORMAL);
                            mRootView.loginSuc();
                        }
                    });
        }
    }

}
