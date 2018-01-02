package food.xinyuan.seller.app.api.service;


import java.util.Map;

import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.response.UploadFile;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
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

    @Multipart
    @POST("/commons/upload/{path}")
    Observable<HttpResponseData<UploadFile>> uploadFile(@Path("path")String path,
                                                        @PartMap Map<String, RequestBody> photo);
}
