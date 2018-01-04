package food.xinyuan.seller.app.api.service;

import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.request.AddGoods;
import food.xinyuan.seller.app.data.bean.response.Goods;
import food.xinyuan.seller.app.data.bean.response.GoodsCategory;
import food.xinyuan.seller.app.data.bean.response.Printer;
import food.xinyuan.seller.app.data.bean.response.ShopDetail;
import food.xinyuan.seller.app.data.bean.response.ShopStatistics;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
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
     * 歇业
     *
     * @return
     */
    @DELETE("/seller/shopDetail/operatingState")
    Observable<HttpResponseData> delOperating();

    /**
     * 营业
     *
     * @return
     */
    @PUT("/seller/shopDetail/operatingState")
    Observable<HttpResponseData> putOperating();

    /**
     * 更改联系电话
     *
     * @param phone
     * @return
     */
    @PUT("/seller/shopDetail/takeOutPhone/{takeOutPhone}")
    Observable<HttpResponseData> changeShopPhone(@Path("takeOutPhone") String phone);

    /**
     * 更改最低配送价格
     *
     * @param price
     * @return
     */
    @PUT("/seller/shopDetail/updateMinDeliveryPrice/{minDeliveryPrice}")
    Observable<HttpResponseData> changeMinDeliveryPrice(@Path("minDeliveryPrice") String price);

    /**
     * 手动接单
     *
     * @return
     */
    @DELETE("/seller/shopDetail/automaticAcceptOrder")
    Observable<HttpResponseData> delAutoOrder();

    /**
     * 自动接单
     *
     * @return
     */
    @PUT("/seller/shopDetail/automaticAcceptOrder")
    Observable<HttpResponseData> putAutoOrder();

    /**
     * 不允许开发票
     *
     * @return
     */
    @DELETE("/seller/shop/drawInvoice")
    Observable<HttpResponseData> delDrawInvoice();

    /**
     * 允许开发票
     *
     * @return
     */
    @PUT("/seller/shop/drawInvoice")
    Observable<HttpResponseData> putDrawInvoice();

    @PUT("/seller/shopDetail/busTime")
    Observable<HttpResponseData> changeBusTime(@Body RequestBody json);

    /**
     * 获取打印机列表
     */
    @GET("/seller/printer")
    Observable<HttpResponseData<ListResponse<Printer>>> getPrinters();

    /**
     * 删除打印机
     * @param printerId
     * @return
     */
    @DELETE("/seller/printer/{printerId}")
    Observable<HttpResponseData> delPrinter(@Path("printerId") String printerId);

    /**
     * 添加打印机
     * @param json
     * @return
     */
    @PUT("/seller/printer")
    Observable<HttpResponseData<Printer>> addPrinter(@Body RequestBody json);
    /**
     * 获取商品类型
     */
    @GET("/seller/goodsCategory")
    Observable<HttpResponseData<ListResponse<GoodsCategory>>> getGoodsCategory();

    /**
     * 获取商品列表
     */
    @GET("/seller/goods")
    Observable<HttpResponseData<ListResponse<Goods>>> getGoodsList(@Query("goodsClassId") String
                                                                           id);

    /**
     * 上传商品
     *
     * @param json
     * @return
     */
    @PUT("/seller/goods")
    Observable<HttpResponseData<AddGoods>> addGoods(@Body RequestBody json);

    /**
     * 获取商品详情
     *
     * @param json
     * @return
     */
    @GET("/seller/goods")
    Observable<HttpResponseData<Goods>> getGoods(@Body RequestBody json);

    /**
     * 下架商品
     *
     * @param json
     * @return
     */
    @POST("/seller/goods/soldOut")
    Observable<HttpResponseData> soldOutGoods(@Body RequestBody json);

    /**
     * 上架商品
     *
     * @param json
     * @return
     */
    @POST("/seller/goods/putaway")
    Observable<HttpResponseData> putawayGoods(@Body RequestBody json);

    /**
     * 删除商品
     *
     * @return
     */
    @DELETE("/seller/goods/{goodsId}")
    Observable<HttpResponseData> deleteGoods(@Path("goodsId") String goodsId);


}
