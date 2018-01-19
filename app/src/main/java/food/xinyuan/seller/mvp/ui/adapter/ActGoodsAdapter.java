package food.xinyuan.seller.mvp.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import food.xinyuan.seller.R;
import food.xinyuan.seller.app.data.bean.response.ActGoods;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/19
 */
public class ActGoodsAdapter extends BaseQuickAdapter<ActGoods,BaseViewHolder> {
    public ActGoodsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    public void setNewData(@Nullable List<ActGoods> data) {
        data.add(0,new ActGoods()); //添加个title
        super.setNewData(data);
    }

    @NonNull
    @Override
    public List<ActGoods> getData() {
        List<ActGoods> result=super.getData();
        result.remove(0);
        return result;
    }

    @Override
    protected void convert(BaseViewHolder helper, ActGoods item) {
        if(helper.getAdapterPosition() == 0){
            helper.setText(R.id.tv_name,"商品名称")
                    .setText(R.id.tv_price,"原价")
                    .setText(R.id.tv_discount,"折扣");
        }else {
            helper.setText(R.id.tv_name,item.getGoodsName())
                    .setText(R.id.tv_price,"¥ "+item.getGoodsPrice())
                    .setText(R.id.tv_discount,item.getDiscount()+" 折");
        }

    }
}
