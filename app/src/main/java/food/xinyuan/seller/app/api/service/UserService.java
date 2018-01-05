package food.xinyuan.seller.app.api.service;

import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.response.LoginResponse;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by f-x on 2017/12/11  19:42
 * Description
 */

public interface UserService {

    /**
     * 登录
     */
    @POST("/seller/seller/loginByCode")
    Observable<HttpResponseData<LoginResponse>> login(@Body RequestBody json);

    /**
     * 刷新token
     * @return
     */
    @GET("/seller/seller/refreshToken")
    Observable<HttpResponseData<LoginResponse>> refreshToken();
}
