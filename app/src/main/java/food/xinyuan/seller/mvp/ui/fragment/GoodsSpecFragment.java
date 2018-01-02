package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.kyleduo.switchbutton.SwitchButton;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.request.AddGoods;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.app.utils.ConstantUtil;


public class GoodsSpecFragment extends AbstractMyBaseFragment {


    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.tv_inventory_hint)
    TextView tvInventoryHint;
    @BindView(R.id.sw_inventory)
    SwitchButton swInventory;
    @BindView(R.id.et_inventory)
    EditText etInventory;
    @BindView(R.id.rl_inventory)
    RelativeLayout rlInventory;
    @BindView(R.id.et_box_count)
    EditText etBoxCount;
    @BindView(R.id.et_box_price)
    EditText etBoxPrice;
    @BindView(R.id.tv_ensure)
    TextView tvEnsure;

    AddGoods.AddSpecsBean mSpec;

    public static GoodsSpecFragment newInstance() {
        GoodsSpecFragment fragment = new GoodsSpecFragment();
        return fragment;
    }

    public static GoodsSpecFragment newInstance(AddGoods.AddSpecsBean bean) {
        GoodsSpecFragment fragment = new GoodsSpecFragment();
        fragment.mSpec = bean;
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {

    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_goods_spec, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        if (mSpec != null) {
            tvHeaderCenter.setText("修改规格");
            setDataIfUpdate();
        } else {
            tvHeaderCenter.setText("添加规格");
        }

        CommonUtils.setBack(this, ivHeaderLeft);

        swInventory.setOnCheckedChangeListener((buttonView, isChecked) -> {
            tvInventoryHint.setText(isChecked ? "无限" : "有限");
            rlInventory.setVisibility(isChecked ? View.GONE : View.VISIBLE);
        });


    }

    private void setDataIfUpdate() {
        etName.setText(mSpec.getGoodsSpecificationName());
        etPrice.setText(mSpec.getGoodsSpecificationPrice() + "");
        swInventory.setChecked(mSpec.isInfiniteInventory());
        etBoxCount.setText(mSpec.getBoxesNumber() + "");
        etBoxPrice.setText(mSpec.getBoxesMoney() + "");
    }

    @Override
    public void setData(Object data) {

    }

    @OnClick(R.id.tv_ensure)
    public void onViewClicked() {
        String name = etName.getText().toString().trim();
        String price = etPrice.getText().toString().trim();
        String inventory = etInventory.getText().toString().trim();
        String boxCount = etBoxCount.getText().toString().trim();
        String boxPrice = etBoxPrice.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            ArmsUtils.snackbarText("请输入规格名称", ConstantUtil.SNACK_WARING);
        } else if (TextUtils.isEmpty(price)) {
            ArmsUtils.snackbarText("请输入规格价格", ConstantUtil.SNACK_WARING);
        } else if (!swInventory.isChecked() && TextUtils.isEmpty(inventory)) {
            ArmsUtils.snackbarText("请输入库存数量", ConstantUtil.SNACK_WARING);
        } else if (TextUtils.isEmpty(boxCount)) {
            ArmsUtils.snackbarText("请输入餐盒数量", ConstantUtil.SNACK_WARING);
        } else if (TextUtils.isEmpty(boxPrice)) {
            ArmsUtils.snackbarText("请输入餐盒价格", ConstantUtil.SNACK_WARING);
        } else if (mSpec != null) {
            //修改
            mSpec.setGoodsSpecificationName(name);
            mSpec.setGoodsSpecificationPrice(new Double(price));
            mSpec.setInfiniteInventory(swInventory.isChecked());
            mSpec.setStock(swInventory.isChecked() ? 0 : new Integer(inventory));
            mSpec.setBoxesNumber(new Integer(boxCount));
            mSpec.setBoxesMoney(new Double(boxPrice));
            EventBus.getDefault().post(mSpec);
            pop();
        } else {
            //添加
            EventBus.getDefault().post(new AddGoods.AddSpecsBean(swInventory.isChecked(),
                    new Double(boxPrice),
                    new Integer(boxCount), name, new Double(price), swInventory.isChecked() ?
                    0 :
                    new Integer(inventory)));
            pop();
        }


    }
}
