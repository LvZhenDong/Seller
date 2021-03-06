package food.xinyuan.seller.app.api.service;

import java.util.List;

import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.ActGoods;
import food.xinyuan.seller.app.data.bean.response.Goods;
import food.xinyuan.seller.app.data.bean.response.Order;
import food.xinyuan.seller.app.data.bean.response.ShopActivity;
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
 * Description：活动
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/15
 */
public interface ActivityService {

    /**
     * 活动list
     *
     * @param pageSize
     * @return
     */
    @GET("/seller/activity")
    Observable<HttpResponseData<ListResponse<ShopActivity>>> getActivityList(@Query("pageSize") int pageSize);

    /**
     * 删除活动
     *
     * @param id
     * @return
     */
    @DELETE("/seller/activity/{activityId}")
    Observable<HttpResponseData> delActivity(@Path("activityId") long id);

    /**
     * 添加活动
     *
     * @param json
     * @return
     */
    @PUT("/seller/activity")
    Observable<HttpResponseData<ShopActivity>> addActivity(@Body RequestBody json);

    /**
     * 修改活动
     *
     * @param json
     * @return
     */
    @POST("/seller/activity/{activityId}")
    Observable<HttpResponseData<ShopActivity>> updateActivity(@Path("activityId") long activityId,
                                                              @Body RequestBody json);

    /**
     * 获取活动详情
     *
     * @param activityId
     * @return
     */
    @GET("/seller/activity/{activityId}")
    Observable<HttpResponseData<ShopActivity>> getActivity(@Path("activityId") long activityId);

    /**
     * 获取活动商品list
     * @param activityId
     * @return
     */
    @GET("/seller/activityGoods/{activityId}")
    Observable<HttpResponseData<List<ActGoods>>> getActivityGoods(@Path("activityId") long activityId);
}
