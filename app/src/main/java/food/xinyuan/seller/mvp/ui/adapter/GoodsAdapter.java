package food.xinyuan.seller.mvp.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import food.xinyuan.seller.R;
import food.xinyuan.seller.app.data.bean.response.Goods;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2017/12/29
 */
public class GoodsAdapter extends BaseQuickAdapter<Goods,BaseViewHolder> {
    public GoodsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Goods item) {
        helper.setText(R.id.tv_name,item.getGoodsName());
        helper.setText(R.id.tv_quantity,"月售"+item.getGoodsSales()+"份");
        helper.setText(R.id.tv_price,"¥"+item.getGoodsPrice());
    }
}
