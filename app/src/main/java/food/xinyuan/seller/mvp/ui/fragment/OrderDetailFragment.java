package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.component.AppComponent;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import butterknife.BindView;
import butterknife.OnClick;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.response.Order;
import food.xinyuan.seller.app.data.bean.response.OrderActivitys;
import food.xinyuan.seller.app.data.bean.response.OrderGoods;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.app.utils.ConstantUtil;
import food.xinyuan.seller.app.utils.XDateUtils;
import food.xinyuan.seller.di.component.DaggerOrderDetailComponent;
import food.xinyuan.seller.di.module.OrderDetailModule;
import food.xinyuan.seller.mvp.contract.OrderDetailContract;
import food.xinyuan.seller.mvp.presenter.OrderDetailPresenter;


public class OrderDetailFragment extends AbstractMyBaseFragment<OrderDetailPresenter> implements OrderDetailContract.View {


    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;
    @BindView(R.id.tv_order_short_num)
    TextView tvOrderShortNum;
    @BindView(R.id.tv_order_num)
    TextView tvOrderNum;
    @BindView(R.id.tv_cancel_type)
    TextView tvCancelType;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.srl_order_detail)
    SmartRefreshLayout srlOrderDetail;
    @BindView(R.id.rl1)
    RelativeLayout rlOne;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_contact_phone)
    TextView tvContactPhone;
    @BindView(R.id.tv_delivery_time)
    TextView tvDeliveryTime;
    @BindView(R.id.tv_driver)
    TextView tvDriver;
    @BindView(R.id.tv_meal_price)
    TextView tvMealPrice;
    @BindView(R.id.tv_shipping_price)
    TextView tvShippingPrice;
    @BindView(R.id.rv_goods)
    RecyclerView rvGoods;
    @BindView(R.id.rv_activity)
    RecyclerView rvActivity;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.rl_rider)
    RelativeLayout rlRider;

    long mId;
    Order mOrder;
    BaseQuickAdapter<OrderGoods, BaseViewHolder> mOrderGoodsAdapter;
    BaseQuickAdapter<OrderActivitys, BaseViewHolder> mOrderActivityAdapter;

    public static OrderDetailFragment newInstance(long id) {
        OrderDetailFragment fragment = new OrderDetailFragment();
        fragment.mId = id;
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerOrderDetailComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .orderDetailModule(new OrderDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_detail, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText("订单详情");
        CommonUtils.setBack(this, ivHeaderLeft);

        initRv();

        srlOrderDetail.setRefreshHeader(new ClassicsHeader(getActivity()));
        srlOrderDetail.setOnRefreshListener(refreshlayout -> mPresenter.getDetail(mId));

        srlOrderDetail.autoRefresh();
    }

    private void initRv() {
        mOrderGoodsAdapter = new BaseQuickAdapter<OrderGoods, BaseViewHolder>(R.layout.item_order_goods) {
            @Override
            protected void convert(BaseViewHolder helper, OrderGoods item) {
                helper.setText(R.id.tv_goods_name, item.getGoodsName())
                        .setText(R.id.tv_goods_price, "¥" + item.getGoodsPrice())
                        .setText(R.id.tv_goods_num, "x" + item.getGoodsCount());
            }
        };
        rvGoods.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvGoods.setAdapter(mOrderGoodsAdapter);

        mOrderActivityAdapter = new BaseQuickAdapter<OrderActivitys, BaseViewHolder>(R.layout.item_order_activity) {
            @Override
            protected void convert(BaseViewHolder helper, OrderActivitys item) {
                helper.setText(R.id.tv_activity_content, item.getActivityContent())
                        .setText(R.id.tv_activity_reduce, "¥ -" + item.getActivityReduced());
            }
        };
        rvActivity.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvActivity.setAdapter(mOrderActivityAdapter);
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

    @Override
    public void getDetailSuc(Order order) {
        mOrder = order;
        if(!TextUtils.isEmpty(order.getOrderTakeout().getCarrierDriverphone()))
            rlRider.setVisibility(View.VISIBLE);
        llContent.setVisibility(View.VISIBLE);
        srlOrderDetail.finishRefresh();

        //第一栏
        tvOrderShortNum.setText(order.getShortOrderNum());
        tvOrderNum.setText(order.getOrderNum());
        tvCancelType.setText(order.getOrderCancel().getCancelTypeStr());
        tvStatus.setText(order.getOrderStatusStr());
        tvTime.setText(order.getAddTimeStr());
        tvPrice.setText("¥" + order.getOrderPrice());
        tvContent.setText(order.getOrderContent());
        if (TextUtils.equals(order.getOrderStatus(), ConstantUtil.ORDER_STATUS_CANCELED)) {   //已取消的显示取消原因

            tvCancelType.setVisibility(View.VISIBLE);
            tvStatus.setTextColor(getResources().getColor(R.color.tv_red));
        } else {
            tvCancelType.setVisibility(View.GONE);
            tvStatus.setTextColor(getResources().getColor(R.color.colorPrimary));
        }

        //第二栏
        tvAddress.setText(order.getOrderContact().getAddress());
        tvContactPhone.setText(order.getOrderContact().getContactNameStr() + order.getOrderContact().getContactPhone());
        //FIXME 这里尽快送达好像是需要根据distributionType来判断的
        tvDeliveryTime.setText("尽快送达[" + XDateUtils.millis2String(order.getOrderTakeout().getDeliveryTime(), "HH:mm:ss") + "]");
        tvDriver.setText(order.getOrderTakeout().getCarrierDriverName() + order.getOrderTakeout().getCarrierDriverphone());

        //第三栏
        tvMealPrice.setText("¥" + order.getOrderTakeout().getMealFee());
        tvShippingPrice.setText("¥" + order.getOrderTakeout().getShippingFee());
        mOrderGoodsAdapter.setNewData(order.getOrderGoods());
        mOrderActivityAdapter.setNewData(order.getOrderActivitys());

    }

    @OnClick(R.id.tv_driver)
    public void onViewClicked() {
        mPresenter.callPhone(mOrder.getOrderTakeout().getCarrierDriverphone());
    }
}