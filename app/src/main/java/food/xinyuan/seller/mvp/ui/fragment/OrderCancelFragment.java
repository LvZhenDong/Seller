package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.di.component.DaggerOrderCancelComponent;
import food.xinyuan.seller.di.module.OrderCancelModule;
import food.xinyuan.seller.mvp.contract.OrderCancelContract;
import food.xinyuan.seller.mvp.presenter.OrderCancelPresenter;


public class OrderCancelFragment extends AbstractMyBaseFragment<OrderCancelPresenter> implements OrderCancelContract.View {
    MaterialDialog mDialog;
    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;

    long mId;

    public static OrderCancelFragment newInstance(long id) {
        OrderCancelFragment fragment = new OrderCancelFragment();
        fragment.mId=id;
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerOrderCancelComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .orderCancelModule(new OrderCancelModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_cancel, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText("取消订单");
        CommonUtils.setBack(this, ivHeaderLeft);

        mDialog = new MaterialDialog.Builder(getActivity()).content(R.string.waiting).
                progress(true, 0).build();
    }

    @Override
    public void setData(Object data) {

    }


    @Override
    public void showLoading() {
        if (mDialog != null)
            mDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mDialog != null)
            mDialog.dismiss();
    }

    @OnClick(R.id.tv_cancel)
    public void onViewClicked() {
        mPresenter.cancelOrder(etContent.getText().toString().trim(),mId);
    }

    @Override
    public void cancelOrderSuc() {
        pop();
    }
}
