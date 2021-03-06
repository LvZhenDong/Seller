package food.xinyuan.seller.mvp.contract;

import android.support.annotation.StringRes;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import java.util.Map;

import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.request.AddGoods;
import food.xinyuan.seller.app.data.bean.request.UpdateGoods;
import food.xinyuan.seller.app.data.bean.response.Goods;
import food.xinyuan.seller.app.data.bean.response.GoodsDetail;
import food.xinyuan.seller.app.data.bean.response.UploadFile;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Path;


public interface AddGoodsContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void addGoodsSuc();

        void uploadImgSuc(String imgUrl);

        void getGoodsSuc(GoodsDetail goodsDetail);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<HttpResponseData<UploadFile>> upLoadImg(Map<String, RequestBody> photo);

        Observable<HttpResponseData<AddGoods>> addGoods(AddGoods addGoods);

        Observable<HttpResponseData<GoodsDetail>> getGoods(long goodsId);

        Observable<HttpResponseData<UpdateGoods>> updateGoods(long goodsId,UpdateGoods updateGoods);
    }
}
