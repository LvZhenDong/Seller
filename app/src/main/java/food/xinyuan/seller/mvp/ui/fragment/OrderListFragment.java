package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jess.arms.di.component.AppComponent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.response.Order;
import food.xinyuan.seller.di.component.DaggerOrderListComponent;
import food.xinyuan.seller.di.module.OrderListModule;
import food.xinyuan.seller.mvp.contract.OrderListContract;
import food.xinyuan.seller.mvp.presenter.OrderListPresenter;
import food.xinyuan.seller.mvp.ui.adapter.OrderAdapter;


public class OrderListFragment extends AbstractMyBaseFragment<OrderListPresenter> implements OrderListContract.View {

    MaterialDialog mDialog;
    @BindView(R.id.rv_order_liset)
    RecyclerView rvOrderLiset;

    OrderAdapter mAdapter;

    String mStatus;

    public static OrderListFragment newInstance(String status) {
        OrderListFragment fragment = new OrderListFragment();
        fragment.mStatus = status;
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerOrderListComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .orderListModule(new OrderListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_list, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mDialog = new MaterialDialog.Builder(getActivity()).content(R.string.waiting).
                progress(true, 0).build();

        mAdapter = new OrderAdapter(R.layout.item_order);
        rvOrderLiset.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvOrderLiset.setAdapter(mAdapter);
    }

    @Override
    public void setData(Object data) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.getOrderList(mStatus);
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

    @Override
    public void getListSuc(List<Order> data) {
        mAdapter.setNewData(data);
    }
}
