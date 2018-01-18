package food.xinyuan.seller.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;

import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import food.xinyuan.seller.R;
import food.xinyuan.seller.app.data.bean.request.AddGoods;
import food.xinyuan.seller.app.data.bean.response.GoodsProperty;

/**
 *商品属性adapter
 *
 * @author LvZhenDong
 *          created on 2018/1/4 20:50
 */
public class GoodsPropertyAdapter extends BaseQuickAdapter<GoodsProperty, BaseViewHolder> {


    public GoodsPropertyAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsProperty item) {
        int pos = helper.getLayoutPosition();
        helper.setText(R.id.tv_title, "属性" + (pos + 1));
        helper.setText(R.id.tv_property_name,"属性名称："+item.getGoodsPropertyName());
        //删除
        helper.addOnClickListener(R.id.tv_del).addOnClickListener(R.id.tv_update);

        RecyclerView rvLabel=helper.getView(R.id.rv_property);
        BaseQuickAdapter<String,BaseViewHolder> adapter=new BaseQuickAdapter<String,
                BaseViewHolder>(R.layout.item_property_lable) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_property,item);
                helper.setVisible(R.id.iv_close,false);
            }
        };
        ChipsLayoutManager chipsLayoutManager = ChipsLayoutManager.newBuilder(mContext)
                .setChildGravity(Gravity.TOP)
                .setScrollingEnabled(true)
                .setMaxViewsInRow(4)
                .setGravityResolver(position -> Gravity.CENTER)
                .build();
        adapter.setNewData(item.getStrings());
        rvLabel.setLayoutManager(chipsLayoutManager);
        rvLabel.setAdapter(adapter);
    }
}
