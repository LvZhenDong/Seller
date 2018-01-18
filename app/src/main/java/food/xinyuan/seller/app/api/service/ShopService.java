package food.xinyuan.seller.app.api.service;

import java.util.List;

import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.Appraise;
import food.xinyuan.seller.app.data.bean.response.AppraiseStatistics;
import food.xinyuan.seller.app.data.bean.response.Coupon;
import food.xinyuan.seller.app.data.bean.response.NewCustomer;
import food.xinyuan.seller.app.data.bean.response.Printer;
import food.xinyuan.seller.app.data.bean.response.SalesRank;
import food.xinyuan.seller.app.data.bean.response.ShopDetail;
import food.xinyuan.seller.app.data.bean.response.ShopStatistics;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
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
    @GET("/seller/shop")
    Observable<HttpResponseData<ShopDetail>> getShopDetail();

    /**
     * 歇业
     *
     * @return
     */
    @DELETE("/seller/shop/operatingState")
    Observable<HttpResponseData> delOperating();

    /**
     * 营业
     *
     * @return
     */
    @PUT("/seller/shop/operatingState")
    Observable<HttpResponseData> putOperating();

    /**
     * 更改联系电话
     *
     * @param phone
     * @return
     */
    @PUT("/seller/shop/takeOutPhone/{takeOutPhone}")
    Observable<HttpResponseData> changeShopPhone(@Path("takeOutPhone") String phone);

    /**
     * 更改最低配送价格
     *
     * @param price
     * @return
     */
    @PUT("/seller/shop/updateMinDeliveryPrice/{minDeliveryPrice}")
    Observable<HttpResponseData> changeMinDeliveryPrice(@Path("minDeliveryPrice") String price);

    /**
     * 手动接单
     *
     * @return
     */
    @DELETE("/seller/shop/automaticAcceptOrder")
    Observable<HttpResponseData> delAutoOrder();

    /**
     * 自动接单
     *
     * @return
     */
    @PUT("/seller/shop/automaticAcceptOrder")
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

    /**
     * 更改营业时间
     * @param json
     * @return
     */
    @PUT("/seller/shop/busTime")
    Observable<HttpResponseData> changeBusTime(@Body RequestBody json);

    /**
     * 获取打印机列表
     */
    @GET("/seller/printer")
    Observable<HttpResponseData<ListResponse<Printer>>> getPrinters(@Query("pageSize") int pageSize);

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
     * 获取红包list
     * @param pageSize
     * @return
     */
    @GET("/seller/coupon")
    Observable<HttpResponseData<ListResponse<Coupon>>> getCouponList(@Query("pageSize") int pageSize);

    /**
     * 删除红包
     * @param id
     * @return
     */
    @DELETE("/seller/coupon/{couponId}")
    Observable<HttpResponseData> delCoupon(@Path("couponId") int id);

    /**
     * 添加红包
     * @param json
     * @return
     */
    @PUT("/seller/coupon")
    Observable<HttpResponseData<Coupon>> addCoupon(@Body RequestBody json);

    /**
     * 新客户数据
     * @param days
     * @return
     */
    @GET("/seller/analysis/newCustomer")
    Observable<HttpResponseData<List<NewCustomer>>> getNewCustomer(@Query("days") int days);

    /**
     * 订单量数据
     * @param days
     * @return
     */
    @GET("/seller/analysis/orderQuantity")
    Observable<HttpResponseData<List<NewCustomer>>> getOrderQuantity(@Query("days") int days);

    /**
     * 营业额
     * @param days
     * @return
     */
    @GET("/seller/analysis/turnover")
    Observable<HttpResponseData<List<NewCustomer>>> getTurnover(@Query("days") int days);

    /**
     * 商品销售量
     * @param days
     * @param goodsId
     * @return
     */
    @GET("/seller/analysis/goodsSales")
    Observable<HttpResponseData<List<NewCustomer>>> getGoodsSales(@Query("days") int days,
                                                                  @Query("goodId") Long goodsId);

    /**
     * 商品销售量排行
     * @param queryDate
     * @return
     */
    @GET("/seller/analysis/salesRank")
    Observable<HttpResponseData<List<SalesRank>>> getSalesRank(@Query("queryDate") String queryDate);

    /**
     * 查询店铺评价统计
     * @return
     */
    @GET("/seller/shopAppraise/shopAppriseStatistics")
    Observable<HttpResponseData<AppraiseStatistics>> getAppraiseStatistics();

    /**
     * 获取店铺评价list
     * @return
     */
    @GET("/seller/shopAppraise")
    Observable<HttpResponseData<ListResponse<Appraise>>> getAppraiseList(@Query("reply") Boolean reply,
                                                                         @Query("commentsAppraise") Boolean commentsAppraise,
                                                                         @Query("pageId") int pageId);

    /**
     * 添加评价
     * @param json
     * @return
     */
    @PUT("/seller/reply")
    Observable<HttpResponseData<Appraise.CommentListBean>> addAppraise(@Body RequestBody json);

}
