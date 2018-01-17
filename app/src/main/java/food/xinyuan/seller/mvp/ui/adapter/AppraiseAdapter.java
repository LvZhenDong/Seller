package food.xinyuan.seller.mvp.ui.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RatingBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.R;
import food.xinyuan.seller.app.data.bean.response.Appraise;
import food.xinyuan.seller.app.utils.ImageLoaderUtils;
import food.xinyuan.seller.app.utils.XDateUtils;

/**
 * <p>
 * Description：评价list
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/12
 */
public class AppraiseAdapter extends BaseQuickAdapter<Appraise, BaseViewHolder> {
    AppComponent mAppComponent;
    public AppraiseAdapter(int layoutResId, AppComponent appComponent) {
        super(layoutResId);
        mAppComponent = appComponent;
    }

    @Override
    protected void convert(BaseViewHolder helper, Appraise item) {
        helper.setText(R.id.tv_user_name, item.getUserName())
                .setText(R.id.tv_time, XDateUtils.millis2String(item.getAppraiseTime(), "yyyy-MM-dd"))
                .setText(R.id.tv_shop_content, item.getContentShopAppraise())
                .setText(R.id.tv_order_name, item.getOrderName());

        helper.addOnClickListener(R.id.tv_reply);


        RatingBar ratingBar = helper.getView(R.id.rb_appraise);
        ratingBar.setRating(item.getShopAppraise());
        ImageLoaderUtils.loadCirImg(mAppComponent, item.getAvatorUrl(), helper.getView(R.id.iv_head));
        showImg(helper, item);
        showComment(helper,item);
        showGoodsAppraise(helper, item);

    }

    /**
     * 评论图片
     * @param helper
     * @param item
     */
    private void showImg(BaseViewHolder helper,Appraise item){
        RecyclerView rv=helper.getView(R.id.rv_img);
        rv.setLayoutManager(new GridLayoutManager(mContext,4));
        BaseQuickAdapter<String,BaseViewHolder> adapter=new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_appraise_img) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                ImageLoaderUtils.loadImg(mAppComponent, item, helper.getView(R.id.iv_img));
            }
        };
        rv.setAdapter(adapter);
        adapter.setNewData(item.getShopAppraiseImageUrlList());
        //TODO 点击图片查看
    }

    /**
     * 商家的评论
     * @param helper
     * @param item
     */
    private void showComment(BaseViewHolder helper,Appraise item){
        RecyclerView rvComment=helper.getView(R.id.rv_comment);
        rvComment.setLayoutManager(new LinearLayoutManager(mContext));
        BaseQuickAdapter<Appraise.CommentListBean,BaseViewHolder> adapter=
                new BaseQuickAdapter<Appraise.CommentListBean, BaseViewHolder>(R.layout.item_appraise_comment) {
                    @Override
                    protected void convert(BaseViewHolder helper, Appraise.CommentListBean item) {
                        helper.setText(R.id.tv_comment,item.getCommentContent());
                    }
                };
        rvComment.setAdapter(adapter);
        adapter.setNewData(item.getCommentList());
    }

    /**
     * 买家的具体商品评论
     * @param helper
     * @param item
     */
    private void showGoodsAppraise(BaseViewHolder helper,Appraise item){
        RecyclerView rvAppraise=helper.getView(R.id.rv_goods_appraise);
        rvAppraise.setLayoutManager(new LinearLayoutManager(mContext));
        BaseQuickAdapter<Appraise.GoodsAppraiseListBean,BaseViewHolder> adapter=
                new BaseQuickAdapter<Appraise.GoodsAppraiseListBean, BaseViewHolder>(R.layout.item_appraise_goods) {
                    @Override
                    protected void convert(BaseViewHolder helper, Appraise.GoodsAppraiseListBean item) {
                        helper.setText(R.id.tv_goods_name,item.getGoodsName())
                                .setText(R.id.tv_goods_appraise,item.getAppraiseContent());
                        RatingBar ratingBar = helper.getView(R.id.rb_appraise_goods);
                        ratingBar.setRating(item.getAppraiseLevel());
                    }
                };
        rvAppraise.setAdapter(adapter);
        adapter.setNewData(item.getGoodsAppraiseList());
    }
}
