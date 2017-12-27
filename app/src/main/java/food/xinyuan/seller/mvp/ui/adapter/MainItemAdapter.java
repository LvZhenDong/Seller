package food.xinyuan.seller.mvp.ui.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import food.xinyuan.seller.R;
import food.xinyuan.seller.app.data.bean.MainItem;
import food.xinyuan.seller.app.data.bean.response.LoginResponse;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2017/12/27
 */
public class MainItemAdapter extends BaseQuickAdapter<MainItem,BaseViewHolder> {
    List<MainItem> mList;

    public MainItemAdapter(@Nullable List<MainItem> data) {
        super(R.layout.item_main,data);
        mList=data;
    }

    @Override
    protected void convert(BaseViewHolder helper, MainItem item) {
        helper.setText(R.id.tv_title,item.getTitle());
        TextView tv=helper.getView(R.id.tv_title);
        Drawable top = ContextCompat.getDrawable(mContext, item.getImgId());
        tv.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
    }
}
