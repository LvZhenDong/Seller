package food.xinyuan.seller.mvp.contract;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import java.util.List;

import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.Goods;
import food.xinyuan.seller.app.data.bean.response.NewCustomer;
import io.reactivex.Observable;
import retrofit2.http.Query;


public interface AnalysisChartContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void getNewCustomerSuc(List<NewCustomer> data);

        void searchGoodsSuc(List<Goods> result);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<HttpResponseData<List<NewCustomer>>> getNewCustomer(int days);

        Observable<HttpResponseData<List<NewCustomer>>> getOrderQuantity(int days);

        Observable<HttpResponseData<List<NewCustomer>>> getTurnover(int days);

        Observable<HttpResponseData<List<NewCustomer>>> getGoodsSales(int days, Long goodsId);

        Observable<HttpResponseData<ListResponse<Goods>>> searchGoods(String name, int pageSize);
    }
}
