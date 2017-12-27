package food.xinyuan.seller.mvp.contract;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.response.LoginResponse;
import io.reactivex.Observable;
import io.reactivex.Observer;


public interface LoginContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void showSnackbarMsg(String msg,int type);

        void showSnackbarMsg(@StringRes int msgId,int type);

        void hideKeyboard();

        void sendVerCodeSuc();

        void loginSuc();
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<HttpResponseData<String>> getCode(String phone);

        Observable<HttpResponseData<LoginResponse>> login(String loginRequest);
    }
}
