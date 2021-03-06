package food.xinyuan.seller.app.api.service;

import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.GeoInfo;
import food.xinyuan.seller.app.data.bean.response.Order;
import food.xinyuan.seller.app.data.bean.response.RiderLocation;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OrderService {

    /**
     * 获取订单list
     * @param pageId
     * @param status
     * @return
     */
    @GET("/seller/order")
    Observable<HttpResponseData<ListResponse<Order>>> getOrderList(@Query("pageId") int pageId,
                                                                   @Query("orderStatus") String status);

    /**
     * 取消订单
     * @param json
     * @return
     */
    @PUT("/seller/order/orderCancel")
    Observable<HttpResponseData<Order>> cancelOrder(@Body RequestBody json);

    /**
     * 接单
     * @param orderId
     * @return
     */
    @PUT("/seller/order/confirmReceipt/{orderId}")
    Observable<HttpResponseData<Order>> receiptOrder(@Path("orderId") long orderId);

    /**
     * 打印订单
     * @param id
     * @return
     */
    @POST("/seller/order/print/{orderId}")
    Observable<HttpResponseData> printOrder(@Path("orderId") long id);

    /**
     * 订单详情
     * @param id
     * @return
     */
    @GET("/seller/order/{orderId}")
    Observable<HttpResponseData<Order>> getOrderDetail(@Path("orderId") long id);

    /**
     * 骑手位置
     * @param id
     * @return
     */
    @GET("/seller/order/carrier/{orderId}")
    Observable<HttpResponseData<RiderLocation>> getRiderLoc(@Path("orderId") long id);

    /**
     * 经纬度信息
     * @param id
     * @return
     */
    @GET("/seller/order/geoInfo/{orderId}")
    Observable<HttpResponseData<GeoInfo>> getGeoInfo(@Path("orderId") long id);

}
