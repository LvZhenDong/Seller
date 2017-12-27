package food.xinyuan.seller.app.api.service;


import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.UpdateBean;

import io.reactivex.Observable;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by f-x on 2017/12/20  15:56
 * Description
 */

public interface CommService {
    /**
     * 检查版本
     */
    @Headers({"Domain-Name: apk"})
    @POST("/commons/versions")
    Observable<HttpResponseData<UpdateBean>> checkVersion(@Query("serverVersion") String serverVersion, @Query("serverPackage") String serverPackage, @Query("testVersion") boolean testVersion);

    /**
     * 获取Code
     */
    @Headers({"Domain-Name: main"})
    @POST("/commons/phoneCode/{phone}")
    Observable<HttpResponseData<String>> getCode(@Path("phone") String phone);
}
