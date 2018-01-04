package food.xinyuan.seller.app.api.service;

import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.Notice;
import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/4
 */
public interface NoticeService {

    /**
     * 获取消息list
     * @param pageId
     * @return
     */
    @GET("/seller/notice")
    Observable<HttpResponseData<ListResponse<Notice>>> getNotice(@Query("pageId") int pageId);

    /**
     * 删除消息
     * @param id
     * @return
     */
    @DELETE("/seller/notice/{noticeId}")
    Observable<HttpResponseData> delNotice(@Path("noticeId") long id);
}
