package food.xinyuan.seller.mvp.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.request.AddGoods;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.app.utils.Constant;
import food.xinyuan.seller.app.utils.DataUtils;

/**
 * 商品属性
 */
public class GoodsPropertyFragment extends AbstractMyBaseFragment {


    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.et_property)
    EditText etProperty;
    @BindView(R.id.rv_property)
    RecyclerView rvProperty;
    @BindView(R.id.tv_ensure)
    TextView tvEnsure;

    AddGoods.GoodsPropertysBean mProperty;
    BaseQuickAdapter<String, BaseViewHolder> mAdapter;

    public static GoodsPropertyFragment newInstance() {
        GoodsPropertyFragment fragment = new GoodsPropertyFragment();
        return fragment;
    }

    public static GoodsPropertyFragment newInstance(AddGoods.GoodsPropertysBean bean) {

        GoodsPropertyFragment fragment = new GoodsPropertyFragment();
        fragment.mProperty = bean;
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {

    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_goods_property, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        CommonUtils.setBack(this, ivHeaderLeft);

        mAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_property_lable) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_property, item);
                ImageView ivClose = helper.getView(R.id.iv_close);
                ivClose.setOnClickListener(v -> {
                    mList.remove(item);
                    mAdapter.notifyDataSetChanged();
                });

            }
        };
        ChipsLayoutManager chipsLayoutManager = ChipsLayoutManager.newBuilder(getActivity())
                .setChildGravity(Gravity.TOP)
                .setScrollingEnabled(true)
                .setMaxViewsInRow(4)
                .setGravityResolver(position -> Gravity.CENTER)
                .build();
        rvProperty.setLayoutManager(chipsLayoutManager);
        rvProperty.setAdapter(mAdapter);

        if (mProperty != null) {
            tvHeaderCenter.setText("修改属性");
            setDataIfUpdate();
        } else {
            tvHeaderCenter.setText("添加属性");
        }
    }

    private void setDataIfUpdate(){
        etName.setText(mProperty.getGoodsPropertyName());
        mList=mProperty.getStrings();
        mAdapter.setNewData(mList);
    }

    @Override
    public void setData(Object data) {

    }

    List<String> mList = new ArrayList<>();

    @OnClick({R.id.tv_add, R.id.tv_ensure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_add:
                String property = etProperty.getText().toString().trim();
                if (mList.size() >= 4) {
                    ArmsUtils.snackbarText("属性值最多4项", Constant.SNACK_WARING);
                    return;
                }
                if (mList.contains(property)) {
                    ArmsUtils.snackbarText("该属性值已存在", Constant.SNACK_WARING);
                    return;
                }
                if (!TextUtils.isEmpty(property)) {
                    etProperty.setText("");
                    mList.add(property);
                    mAdapter.setNewData(mList);
                }
                break;
            case R.id.tv_ensure:
                if (TextUtils.isEmpty(etName.getText().toString().trim())) {
                    ArmsUtils.snackbarText("请输入属性名称", Constant.SNACK_WARING);
                }else if (DataUtils.isEmpty(mList)) {
                    ArmsUtils.snackbarText("请输入属性值", Constant.SNACK_WARING);
                }else if(mProperty != null){
                    mProperty.setGoodsPropertyName(etName.getText().toString().trim());
                    mProperty.setGoodsPropertyValueList(mList);
                    EventBus.getDefault().post(mProperty);
                    pop();

                }else{
                    EventBus.getDefault().post(new AddGoods.GoodsPropertysBean(etName.getText()
                            .toString().trim(),
                            mList));
                    pop();
                }
                break;
        }
    }
}
