package food.xinyuan.seller.mvp.ui.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.R;
import food.xinyuan.seller.app.data.bean.response.Goods;
import food.xinyuan.seller.app.utils.ConstantUtil;
import food.xinyuan.seller.app.utils.ImageLoaderUtils;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2017/12/29
 */
public class GoodsAdapter extends BaseQuickAdapter<Goods, BaseViewHolder> {
    AppComponent mAppComponent;

    public GoodsAdapter(int layoutResId, AppComponent appComponent) {
        super(layoutResId);
        mAppComponent = appComponent;
    }

    @Override
    protected void convert(BaseViewHolder helper, Goods item) {
        helper.setText(R.id.tv_name, item.getGoodsName());
        helper.setText(R.id.tv_quantity, "月售" + item.getGoodsSales() + "份");
        helper.setText(R.id.tv_price, "¥" + item.getGoodsPrice());
        helper.setText(R.id.tv_status_goods, item.isPutAway() ? "下架" : "上架");   //是“上架”状态，显示“下架”操作
        ImageLoaderUtils.loadImg(mAppComponent, item.getGoodsImgUrl(), helper.getView(R.id.iv_goods));
        TextView tvEdit = helper.getView(R.id.tv_edit_goods);
        TextView tvStatus = helper.getView(R.id.tv_status_goods);
        TextView tvDel = helper.getView(R.id.tv_del_goods);

        View.OnClickListener clickListener = v -> {
            if (mListener == null) return;
            switch (v.getId()) {
                case R.id.tv_edit_goods:
                    mListener.onEdit(item);
                    break;
                case R.id.tv_status_goods:
                    //是“上架”状态，执行“下架”操作
                    if(item.isPutAway())
                        mListener.onSoldOut(item.getGoodsId());
                    else
                        mListener.onPutAway(item.getGoodsId());
                    break;
                case R.id.tv_del_goods:
                    mListener.onDel(item.getGoodsId());
                    break;
            }
        };

        tvEdit.setOnClickListener(clickListener);
        tvStatus.setOnClickListener(clickListener);
        tvDel.setOnClickListener(clickListener);
    }


    OnGoodsClickListener mListener;

    public void setOnGoodsClickListener(OnGoodsClickListener mListener) {
        this.mListener = mListener;
    }

    public interface OnGoodsClickListener {
        void onEdit(Goods goods);

        void onSoldOut(int goodsId);

        void onPutAway(int goodsId);

        void onDel(int goodsId);
    }
}
