package food.xinyuan.seller.app.utils;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/2
 */
public class RequestUtils {

    public static Map<String, RequestBody> getPhoto(String path) {
        Map<String, RequestBody> images = new HashMap<>();
        if (TextUtils.isEmpty(path))
            return null;
        File file = new File(path);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
        images.put("file\";filename=\"" + file.getName() + "\"", requestFile);

        return images;
    }

    public static RequestBody getRequestBody(String json){
        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
    }

    public static RequestBody getRequestBody(Object object){
        return getRequestBody(new Gson().toJson(object));
    }
}
