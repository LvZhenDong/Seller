package food.xinyuan.seller.app.api.service;

import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.Order;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by f-x on 2017/12/13  19:10
 * Description
 */

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

}
