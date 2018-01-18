package food.xinyuan.seller.mvp.contract;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import java.util.List;

import food.xinyuan.seller.app.data.bean.HttpResponseData;
import food.xinyuan.seller.app.data.bean.common.ListResponse;
import food.xinyuan.seller.app.data.bean.response.Appraise;
import food.xinyuan.seller.app.data.bean.response.Notice;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Query;


public interface AppraiseListContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void getListSuc(List<Appraise> list);

        void loadMoreSuc(List<Appraise> data);

        void noMoreData();

        void noData();

        void stopLoading();

        void addAppraiseSuc(int pos);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<HttpResponseData<ListResponse<Appraise>>> getAppraiseList(Boolean reply,
                                                                             Boolean commentsAppraise,
                                                                             int pageId);

        Observable<HttpResponseData<Appraise.CommentListBean>> addAppraise(RequestBody json);
    }
}
