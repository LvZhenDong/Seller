package food.xinyuan.seller.mvp.ui.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import food.xinyuan.seller.R;
import food.xinyuan.seller.app.data.bean.response.Delgolds;
import food.xinyuan.seller.app.data.bean.response.ShopActivity;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/19
 */
public class DelgoldAdapter extends BaseQuickAdapter<Delgolds,BaseViewHolder> {
    public DelgoldAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Delgolds item) {
        helper.addOnClickListener(R.id.tv_add)
                .addOnClickListener(R.id.tv_del);

        helper.setText(R.id.et_full,item.getFull())
                .setText(R.id.et_reduce,item.getSubtract());

        EditText etFull=helper.getView(R.id.et_full);
        etFull.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                item.setFull(s.toString().trim());
            }
        });

        EditText etReduce=helper.getView(R.id.et_reduce);
        etReduce.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                item.setSubtract(s.toString().trim());
            }
        });

    }
}
