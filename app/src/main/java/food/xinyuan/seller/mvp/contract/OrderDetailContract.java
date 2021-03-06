package food.xinyuan.seller.mvp.contract;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.response.GeoInfo;
import food.xinyuan.seller.app.data.bean.response.Order;
import food.xinyuan.seller.app.data.bean.response.RiderLocation;
import io.reactivex.Observable;
import retrofit2.http.Path;


public interface OrderDetailContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void getDetailSuc(Order order);

        void getRiderLocSuc(RiderLocation riderLocation);

        void getGeoInfoSuc(GeoInfo geoInfo);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<HttpResponseData<Order>> getOrderDetail(long id);

        Observable<HttpResponseData<RiderLocation>> getRiderLoc(long id);

        Observable<HttpResponseData<GeoInfo>> getGeoInfo(long id);
    }
}
