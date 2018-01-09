package food.xinyuan.seller.app.api.service;

import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.Order;
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

}
