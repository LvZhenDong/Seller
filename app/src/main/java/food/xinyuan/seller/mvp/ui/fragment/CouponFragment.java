package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.component.AppComponent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.response.Coupon;
import food.xinyuan.seller.app.data.event.EventConstant;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.app.utils.DialogUtils;
import food.xinyuan.seller.app.utils.XDateUtils;
import food.xinyuan.seller.di.component.DaggerCouponComponent;
import food.xinyuan.seller.di.module.CouponModule;
import food.xinyuan.seller.mvp.contract.CouponContract;
import food.xinyuan.seller.mvp.presenter.CouponPresenter;
import food.xinyuan.seller.mvp.ui.widgets.NormalDecoration;
import food.xinyuan.seller.mvp.ui.widgets.SurroundDecoration;


public class CouponFragment extends AbstractMyBaseFragment<CouponPresenter> implements CouponContract.View {


    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;
    @BindView(R.id.rv_coupon)
    RecyclerView rvCoupon;
    @BindView(R.id.tv_header_right)
    TextView tvHeaderRight;

    MaterialDialog mDialog;
    BaseQuickAdapter<Coupon, BaseViewHolder> mAdapter;

    public static CouponFragment newInstance() {
        CouponFragment fragment = new CouponFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerCouponComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .couponModule(new CouponModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coupon, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText("红包管理");
        CommonUtils.setBack(this, ivHeaderLeft);
        tvHeaderRight.setVisibility(View.VISIBLE);
        tvHeaderRight.setTextSize(26);
        tvHeaderRight.setText("+");

        mDialog = new MaterialDialog.Builder(getActivity()).content(R.string.waiting).
                progress(true, 0).build();

        initRv();

        mPresenter.getCouponList();
    }

    private void initRv() {
        mAdapter = new BaseQuickAdapter<Coupon, BaseViewHolder>(R.layout.item_coupon) {
            @Override
            protected void convert(BaseViewHolder helper, Coupon item) {
                helper.setText(R.id.tv_name, item.getCouponName());
                helper.setText(R.id.tv_time, item.getEndTimeStr());
                helper.setText(R.id.tv_money, item.getMoneyStr());
                helper.setText(R.id.tv_time, item.getEndTimeStr());
                helper.setText(R.id.tv_min, item.getMinStr());

                helper.addOnClickListener(R.id.tv_del);
            }
        };
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.tv_del:
                    DialogUtils.commonChooseDialog(getActivity(), "确定删除该红包吗？", new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            mPresenter.delCoupon(mAdapter.getItem(position).getCouponId(), position);
                        }
                    }).show();

                    break;
            }
        });

        rvCoupon.addItemDecoration(new SurroundDecoration(20));
        rvCoupon.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCoupon.setAdapter(mAdapter);

    }

    @Override
    public void setData(Object data) {

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 刷新list
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAddSpec(String msg) {
        if (TextUtils.equals(msg, EventConstant.UPDATA_COUPON_LIST))
            mPresenter.getCouponList();
    }

    @OnClick(R.id.tv_header_right)
    public void onViewClicked() {
        start(AddCouponFragment.newInstance());
    }


    @Override
    public void showLoading() {
        if (mDialog != null) mDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mDialog != null) mDialog.dismiss();
    }

    @Override
    public void getCouponListSuc(List<Coupon> data) {
        mAdapter.setNewData(data);
    }

    @Override
    public void delCouponSuc(int pos) {
        mAdapter.remove(pos);
    }
}
