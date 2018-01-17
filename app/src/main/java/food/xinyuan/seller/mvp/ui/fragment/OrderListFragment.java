package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.response.Order;
import food.xinyuan.seller.app.data.event.EventConstant;
import food.xinyuan.seller.app.data.event.SellerEvent;
import food.xinyuan.seller.app.utils.Constant;
import food.xinyuan.seller.app.utils.DialogUtils;
import food.xinyuan.seller.di.component.DaggerOrderListComponent;
import food.xinyuan.seller.di.module.OrderListModule;
import food.xinyuan.seller.mvp.contract.OrderListContract;
import food.xinyuan.seller.mvp.presenter.OrderListPresenter;
import food.xinyuan.seller.mvp.ui.adapter.OrderAdapter;


public class OrderListFragment extends AbstractMyBaseFragment<OrderListPresenter> implements OrderListContract.View {

    MaterialDialog mDialog;
    @BindView(R.id.rv_order_liset)
    RecyclerView rvOrderLiset;
    @BindView(R.id.srl_order)
    SmartRefreshLayout srlOrder;

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

        initRv();
    }

    private void initRv() {
        srlOrder.setRefreshHeader(new ClassicsHeader(getActivity()));
        srlOrder.setOnRefreshListener(refreshlayout -> mPresenter.refreshOrder(mStatus));
        srlOrder.setOnLoadmoreListener(refreshlayout -> mPresenter.loadMoreOrder(mStatus));

        mAdapter = new OrderAdapter(R.layout.item_order);
        rvOrderLiset.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvOrderLiset.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Order order = mAdapter.getItem(position);
                switch (view.getId()) {
                    //联系客户
                    case R.id.iv_contact_phone:
                        mPresenter.callPhone(order.getOrderContact().getContactPhone());
                        break;
                    //联系骑手
                    case R.id.tv_rider:
                        mPresenter.callPhone(order.getOrderTakeout().getCarrierDriverphone());
                        break;
                    //打印小票
                    case R.id.tv_print:
                        DialogUtils.commonChooseDialog(getActivity(), "确定打印该订单小票？", new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                mPresenter.printOrder(order.getOrderId());
                            }
                        }).show();
                        break;
                    //取消订单
                    case R.id.tv_cancel:
                        EventBus.getDefault().post(new SellerEvent<Order>(EventConstant.START_ORDER_CANCEL_FRAGMENT, order));
                        break;
                    case R.id.tv_receipt:
                        DialogUtils.commonChooseDialog(getActivity(), "确定接单？", new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                mPresenter.receiptOrder(order);
                            }
                        }).show();
                        break;
                }
            }
        });

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Order order = mAdapter.getItem(position);
                EventBus.getDefault().post(new SellerEvent<Order>(EventConstant.START_ORDER_DETAIL_FRAGMENT, order));
            }
        });
    }

    @Override
    public void setData(Object data) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        hasShown = true;
        srlOrder.autoRefresh();
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

    /**
     * 如果当前fragment还没有显示出来过，则不eventBus不用刷新该list
     */
    boolean hasShown;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateList(String event) {
        if (hasShown && (TextUtils.equals(event, mStatus) || TextUtils.isEmpty(mStatus))) {
            srlOrder.autoRefresh();
        }
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

    @Override
    public void getListSuc(List<Order> data) {
        mAdapter.setNewData(data);
    }

    @Override
    public void loadMoreSuc(List<Order> data) {
        mAdapter.addData(data);
    }

    @Override
    public void noMoreData() {
        ArmsUtils.snackbarText("没有更多数据", Constant.SNACK_WARING);
    }

    @Override
    public void noData() {
        ArmsUtils.snackbarText("没有数据", Constant.SNACK_WARING);
    }

    @Override
    public void stopLoading() {
        srlOrder.finishLoadmore();
        srlOrder.finishRefresh();
    }
}
