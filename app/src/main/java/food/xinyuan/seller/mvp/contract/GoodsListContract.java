package food.xinyuan.seller.mvp.contract;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import java.util.List;

import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.Goods;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;


public interface GoodsListContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void getGoodsSuc(List<Goods> data);

        void loadMoreSuc(List<Goods> data);

        void noMoreData();

        void noData();

        void stopLoading();

        void soldOutGoodsSuc(int pos);

        void delSuc(int pos);

        void putAwaySuc(int pos);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<HttpResponseData<ListResponse<Goods>>> getGoodsList(String id,int pageId);

        Observable<HttpResponseData> soldOutGoods(@Body RequestBody json);

        Observable<HttpResponseData> putawayGoods(@Body RequestBody json);

        Observable<HttpResponseData> deleteGoods(String id);
    }
}
