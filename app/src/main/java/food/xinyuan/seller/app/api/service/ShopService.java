package food.xinyuan.seller.app.api.service;

import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.Goods;
import food.xinyuan.seller.app.data.bean.response.GoodsCategory;
import food.xinyuan.seller.app.data.bean.response.LoginResponse;
import food.xinyuan.seller.app.data.bean.response.Printer;
import food.xinyuan.seller.app.data.bean.response.ShopDetail;
import food.xinyuan.seller.app.data.bean.response.ShopStatistics;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author hq
 * @CreateDate 2017/12/27
 */
public interface ShopService {

    /**
     * 查询统计数据
     */
    @Headers({"Domain-Name: main"})
    @GET("/seller/realtimestatistics")
    Observable<HttpResponseData<ShopStatistics>> getShopStatistics();


    /**
     * 查询店铺详情
     */
    @Headers({"Domain-Name: main"})
    @GET("/seller/shopDetail")
    Observable<HttpResponseData<ShopDetail>> getShopDetail();

    /**
     * 获取打印机列表
     */
    @Headers({"Domain-Name: main"})
    @GET("/seller/printer")
    Observable<HttpResponseData<ListResponse<Printer>>> getPrinters();

    /**
     * 获取商品类型
     */
    @Headers({"Domain-Name: main"})
    @GET("/seller/goodsCategory")
    Observable<HttpResponseData<ListResponse<GoodsCategory>>> getGoodsCategory();


    /**
     * 获取商品列表
     */
    @Headers({"Domain-Name: main"})
    @GET("/seller/goods")
    Observable<HttpResponseData<ListResponse<Goods>>> getGoodsList(@Query("goodsClassId") String id);
}
