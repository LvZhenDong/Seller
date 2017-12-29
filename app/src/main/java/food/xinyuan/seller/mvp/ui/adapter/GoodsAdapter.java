package food.xinyuan.seller.mvp.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.R;
import food.xinyuan.seller.app.data.bean.response.Goods;
import food.xinyuan.seller.app.utils.ImageLoaderUtils;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2017/12/29
 */
public class GoodsAdapter extends BaseQuickAdapter<Goods,BaseViewHolder> {
    AppComponent mAppComponent;

    public GoodsAdapter(int layoutResId,AppComponent appComponent) {
        super(layoutResId);
        mAppComponent=appComponent;
    }

    @Override
    protected void convert(BaseViewHolder helper, Goods item) {
        helper.setText(R.id.tv_name,item.getGoodsName());
        helper.setText(R.id.tv_quantity,"月售"+item.getGoodsSales()+"份");
        helper.setText(R.id.tv_price,"¥"+item.getGoodsPrice());
        ImageLoaderUtils.loadImg(mAppComponent,item.getGoodsImgUrl(),helper.getView(R.id.iv_goods));
    }
}
