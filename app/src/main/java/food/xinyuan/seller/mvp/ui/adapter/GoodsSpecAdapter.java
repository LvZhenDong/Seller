package food.xinyuan.seller.mvp.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import food.xinyuan.seller.R;
import food.xinyuan.seller.app.data.bean.response.GoodsSpec;

/**
 *商品规格adapter
 * 
 * @author LvZhenDong
 *          created on 2018/1/4 20:59
 */
public class GoodsSpecAdapter extends BaseQuickAdapter<GoodsSpec, BaseViewHolder> {
    public GoodsSpecAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsSpec item) {
        int position =  helper.getLayoutPosition();
        helper.setText(R.id.tv_title, "规格" + (position + 1));
        helper.setText(R.id.tv_spec_name, "规格名称：" + item.getGoodsSpecificationName());
        helper.setText(R.id.tv_spec_price, "价格：" + item.getGoodsSpecificationPrice());
        helper.setText(R.id.tv_spec_inventory, "库存：" + (item.isInfiniteInventory()
                ? "无限" : item.getStock()));
        helper.setText(R.id.tv_box_count, "餐盒数量：" + item.getBoxesNumber());
        helper.setText(R.id.tv_box_price, "餐盒价格：" + item.getBoxesMoney());

        helper.addOnClickListener(R.id.tv_del).addOnClickListener(R.id.tv_update);
    }
}
