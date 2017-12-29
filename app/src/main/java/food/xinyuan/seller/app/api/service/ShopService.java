package food.xinyuan.seller.app.api.service;

import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.Goods;
import food.xinyuan.seller.app.data.bean.response.GoodsCategory;
import food.xinyuan.seller.app.data.bean.response.Printer;
import food.xinyuan.seller.app.data.bean.response.ShopDetail;
import food.xinyuan.seller.app.data.bean.response.ShopStatistics;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2017/12/27
 */
public interface ShopService {

    /**
     * 查询统计数据
     */
    @GET("/seller/realtimestatistics")
    Observable<HttpResponseData<ShopStatistics>> getShopStatistics();


    /**
     * 查询店铺详情
     */
    @GET("/seller/shopDetail")
    Observable<HttpResponseData<ShopDetail>> getShopDetail();

    /**
     * 获取打印机列表
     */
    @GET("/seller/printer")
    Observable<HttpResponseData<ListResponse<Printer>>> getPrinters();

    /**
     * 获取商品类型
     */
    @GET("/seller/goodsCategory")
    Observable<HttpResponseData<ListResponse<GoodsCategory>>> getGoodsCategory();


    /**
     * 获取商品列表
     */
    @GET("/seller/goods")
    Observable<HttpResponseData<ListResponse<Goods>>> getGoodsList(@Query("goodsClassId") String id);
}
