package food.xinyuan.seller.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.response.ShopDetail;
import io.reactivex.Observable;


public interface ShopInfoContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void getShopInfoSuc(ShopDetail data);

        void changeOperatingSuc(boolean checked);

        void changeOperatingFail(boolean checked);

        void changeAutoOrderSuc(boolean checked);

        void changeAutoOrderFail(boolean checked);

        void changeDrawInvoiceSuc(boolean checked);

        void changeDrawInvoiceFail(boolean checked);

        void changePhoneSuc(String phone);

        void changeMinDeliveryPriceSuc(String price);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {

        Observable<HttpResponseData<ShopDetail>> getShopDetail();

        Observable<HttpResponseData> delOperating();

        Observable<HttpResponseData> putOperating();

        Observable<HttpResponseData> delAutoOrder();

        Observable<HttpResponseData> putAutoOrder();

        Observable<HttpResponseData> delDrawInvoice();

        Observable<HttpResponseData> putDrawInvoice();

        Observable<HttpResponseData> changePhone(String phone);

        Observable<HttpResponseData> changeMinDeliveryPrice(String price);
    }
}
