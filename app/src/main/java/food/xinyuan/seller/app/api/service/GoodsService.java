package food.xinyuan.seller.app.api.service;

import java.util.List;

import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.request.AddGoods;
import food.xinyuan.seller.app.data.bean.request.UpdateGoods;
import food.xinyuan.seller.app.data.bean.response.Goods;
import food.xinyuan.seller.app.data.bean.response.GoodsCategory;
import food.xinyuan.seller.app.data.bean.response.GoodsDetail;
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
 * @CreateDate 2018/1/18
 */
public interface GoodsService {
    /**
     * 获取商品类型
     */
    @GET("/seller/goodsCategory")
    Observable<HttpResponseData<List<GoodsCategory>>> getGoodsCategory(@Query("pageSize") int pageSize);

    /**
     * 添加商品类型
     * @param json
     * @return
     */
    @PUT("/seller/goodsCategory")
    Observable<HttpResponseData<GoodsCategory>> addGoodsCategory(@Body RequestBody json);

    /**
     * 删除商品类型
     * @param id
     * @return
     */
    @DELETE("/seller/goodsCategory/{goodsCategoryId}")
    Observable<HttpResponseData> delGoodsCategory(@Path("goodsCategoryId") String id);

    /**
     * 获取根据分类商品列表
     */
    @GET("/seller/goods")
    Observable<HttpResponseData<ListResponse<Goods>>> getGoodsList(@Query("goodsClassId") String id,
                                                                   @Query("pageId") int pageId);

    /**
     * 搜索商品
     */
    @GET("/seller/goods")
    Observable<HttpResponseData<ListResponse<Goods>>> searchGoods(@Query("goodsNameLike") String name,
                                                                  @Query("pageSize") int pageSize);

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
     * @param goodsId
     * @return
     */
    @GET("/seller/goods/{goodsId}")
    Observable<HttpResponseData<GoodsDetail>> getGoods(@Path("goodsId") long goodsId);

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

    /**
     * 修改商品
     * @param goodsId
     * @return
     */
    @POST("/seller/goods/{goodsId}")
    Observable<HttpResponseData<UpdateGoods>> updateGoods(@Path("goodsId") long goodsId,
                                                          @Body RequestBody json);

    /**
     * 设置活动商品
     * @param activityId
     * @param json
     * @return
     */
    @PUT("/seller/activity/goods/{activityId}")
    Observable<HttpResponseData> setActGoods(@Path("activityId") long activityId,@Body RequestBody json);
}
