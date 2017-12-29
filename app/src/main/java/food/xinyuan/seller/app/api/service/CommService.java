package food.xinyuan.seller.app.api.service;


import food.xinyuan.seller.app.data.bean.HttpResponseData;
import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by f-x on 2017/12/20  15:56
 * Description
 */

public interface CommService {

    /**
     * 获取验证码
     */
    @POST("/commons/phoneCode/{phone}")
    Observable<HttpResponseData<String>> getCode(@Path("phone") String phone);
}
