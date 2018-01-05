package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.di.component.DaggerAddCouponComponent;
import food.xinyuan.seller.di.module.AddCouponModule;
import food.xinyuan.seller.mvp.contract.AddCouponContract;
import food.xinyuan.seller.mvp.presenter.AddCouponPresenter;


public class AddCouponFragment extends AbstractMyBaseFragment<AddCouponPresenter> implements AddCouponContract.View {


    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;
    @BindView(R.id.tv_save)
    TextView tvSave;

    public static AddCouponFragment newInstance() {
        AddCouponFragment fragment = new AddCouponFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerAddCouponComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .addCouponModule(new AddCouponModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_coupon, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText("红包设置");
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


    @OnClick({ R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_save:
                break;
        }
    }
}
