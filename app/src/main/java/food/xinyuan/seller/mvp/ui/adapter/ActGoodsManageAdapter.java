package food.xinyuan.seller.mvp.ui.adapter;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import food.xinyuan.seller.R;
import food.xinyuan.seller.app.data.bean.response.ActGoods;
import food.xinyuan.seller.app.data.bean.response.ActGoodsManage;
import food.xinyuan.seller.app.data.bean.response.Goods;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/19
 */
public class ActGoodsManageAdapter extends BaseQuickAdapter<ActGoodsManage, BaseViewHolder> {
    long activityId;

    public ActGoodsManageAdapter(int layoutResId, long activityId) {
        super(layoutResId);
        this.activityId = activityId;
    }

    @Override
    protected void convert(BaseViewHolder helper, ActGoodsManage item) {
        Goods goods = item.getGoods();
        ActGoods actGoods = item.getActGoods();
        helper.setText(R.id.tv_name, goods.getGoodsName())
                .setText(R.id.tv_price, "¥ " + goods.getGoodsPrice());

        CheckBox checkBox = helper.getView(R.id.cb_act_goods);
        checkBox.setChecked(item.isChecked());

        helper.setText(R.id.et_discount, "" + actGoods.getDiscountStr())
                .setText(R.id.et_num, "" + actGoods.getLimitedQuantityStr());

        EditText etDiscount = helper.getView(R.id.et_discount);
        EditText etQuantity = helper.getView(R.id.et_num);

        etDiscount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString()))
                    actGoods.setDiscount(new Double(s.toString()));
            }
        });

        etQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString()))
                    actGoods.setLimitedQuantity(new Integer(s.toString()));
            }
        });

        checkBox.setOnClickListener(v -> {
            item.setChecked(checkBox.isChecked());
            actGoods.setGoodsId(goods.getGoodsId());
            actGoods.setActivityId(activityId);
        });

    }
}
