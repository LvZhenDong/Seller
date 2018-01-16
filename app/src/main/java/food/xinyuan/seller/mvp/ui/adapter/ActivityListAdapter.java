package food.xinyuan.seller.mvp.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import food.xinyuan.seller.R;
import food.xinyuan.seller.app.data.bean.response.ShopActivity;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/15
 */
public class ActivityListAdapter extends BaseQuickAdapter<ShopActivity, BaseViewHolder> {

    public ActivityListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopActivity item) {
        helper.setText(R.id.tv_name, item.getActivityName())
                .setText(R.id.tv_icon, item.getIconStr())
                .setText(R.id.tv_type, item.getTypeStr())
                .setText(R.id.tv_time, item.getTimeStr());

        helper.setBackgroundRes(R.id.tv_icon, item.getIconDrawableId());
        helper.addOnClickListener(R.id.tv_del)
                .addOnClickListener(R.id.rl_activity_content);
    }
}
