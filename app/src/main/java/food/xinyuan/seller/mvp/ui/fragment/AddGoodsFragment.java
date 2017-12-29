package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.di.component.DaggerAddGoodsComponent;
import food.xinyuan.seller.di.module.AddGoodsModule;
import food.xinyuan.seller.mvp.contract.AddGoodsContract;
import food.xinyuan.seller.mvp.presenter.AddGoodsPresenter;


public class AddGoodsFragment extends AbstractMyBaseFragment<AddGoodsPresenter> implements AddGoodsContract.View {


    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;
    @BindView(R.id.tv_goods_category)
    RelativeLayout tvGoodsCategory;
    @BindView(R.id.tv_goods_spec)
    RelativeLayout tvGoodsSpec;

    public static AddGoodsFragment newInstance() {
        AddGoodsFragment fragment = new AddGoodsFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerAddGoodsComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .addGoodsModule(new AddGoodsModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_goods, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText(R.string.add_goods);
        CommonUtils.setBack(this, ivHeaderLeft);
    }

    @Override
    public void setData(Object data) {

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @OnClick({R.id.tv_goods_category, R.id.tv_goods_spec})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_goods_category:
                start(GoodsCategoryFragment.newInstance());
                break;
            case R.id.tv_goods_spec:
                break;
        }
    }
}
