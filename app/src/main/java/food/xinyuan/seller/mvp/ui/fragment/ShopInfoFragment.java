package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.kyleduo.switchbutton.SwitchButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.request.ChangeBusTime;
import food.xinyuan.seller.app.data.bean.response.ShopDetail;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.app.utils.ConstantUtil;
import food.xinyuan.seller.app.utils.DialogUtils;
import food.xinyuan.seller.di.component.DaggerShopInfoComponent;
import food.xinyuan.seller.di.module.ShopInfoModule;
import food.xinyuan.seller.mvp.contract.ShopInfoContract;
import food.xinyuan.seller.mvp.presenter.ShopInfoPresenter;


public class ShopInfoFragment extends AbstractMyBaseFragment<ShopInfoPresenter> implements
        ShopInfoContract.View {

    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;
    @BindView(R.id.tv_operating)
    TextView tvOperating;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.tv_shop_address)
    TextView tvShopAddress;
    @BindView(R.id.tv_shop_bus_time)
    TextView tvShopBusTime;
    @BindView(R.id.rl_shop_bus_time)
    RelativeLayout rlShopBusTime;
    @BindView(R.id.tv_shop_bus_phone)
    TextView tvShopBusPhone;
    @BindView(R.id.rl_shop_bus_phone)
    RelativeLayout rlShopBusPhone;
    @BindView(R.id.sb_operating)
    SwitchButton sbOperating;
    @BindView(R.id.tv_accept_order_type)
    TextView tvAcceptOrderType;
    @BindView(R.id.sb_accept_order_type)
    SwitchButton sbAcceptOrderType;
    @BindView(R.id.tv_min_delivery_price)
    TextView tvMinDeliveryPrice;
    @BindView(R.id.tv_draw_invoice)
    TextView tvDrawInvoice;
    @BindView(R.id.sb_draw_invoice)
    SwitchButton sbDrawInvoice;
    @BindView(R.id.rl_min_delivery_price)
    RelativeLayout rlMinDeliveryPrice;

    MaterialDialog mDialog;
    ShopDetail mShopDetail;

    public static ShopInfoFragment newInstance() {
        ShopInfoFragment fragment = new ShopInfoFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerShopInfoComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .shopInfoModule(new ShopInfoModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shop_info, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText("门店信息");
        CommonUtils.setBack(this, ivHeaderLeft);
        mDialog = new MaterialDialog.Builder(getActivity()).content(R.string.waiting).
                progress(true, 0).build();

        mPresenter.getShopInfo();
    }

    @Override
    public void setData(Object data) {

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
    public void getShopInfoSuc(ShopDetail data) {
        mShopDetail=data;
        tvOperating.setText(data.isOperatingState() ? "营业中" : "歇业中");
        sbOperating.setChecked(data.isOperatingState());
        tvShopName.setText(data.getShopName());
        tvShopAddress.setText(data.getAddress());
        tvShopBusTime.setText(data.getBusBeginTime() + "-" + data.getBusEndTime());
        tvShopBusPhone.setText(data.getTakeOutPhone());
        tvAcceptOrderType.setText(data.isAutomaticAcceptOrder() ? "自动" : "手动");
        sbAcceptOrderType.setChecked(data.isAutomaticAcceptOrder());
        tvMinDeliveryPrice.setText(data.getMinDeliveryPrice() + "");
        tvDrawInvoice.setText(data.isCanDrawInvoice() ? "是" : "否");
        sbDrawInvoice.setChecked(data.isCanDrawInvoice());
    }

    @Override
    public void changeOperatingSuc(boolean checked) {
        if (checked)
            tvOperating.setText("营业中");
        else
            tvOperating.setText("歇业中");
    }

    @Override
    public void changeOperatingFail(boolean checked) {
        sbOperating.setChecked(checked);
    }

    @Override
    public void changeAutoOrderSuc(boolean checked) {
        if (checked)
            tvAcceptOrderType.setText("自动");
        else
            tvAcceptOrderType.setText("手动");
    }

    @Override
    public void changeAutoOrderFail(boolean checked) {
        sbAcceptOrderType.setChecked(checked);
    }

    @Override
    public void changeDrawInvoiceSuc(boolean checked) {
        if (checked)
            tvDrawInvoice.setText("是");
        else
            tvDrawInvoice.setText("否");
    }

    @Override
    public void changeDrawInvoiceFail(boolean checked) {
        sbDrawInvoice.setChecked(checked);
    }

    @Override
    public void changePhoneSuc(String phone) {
        tvShopBusPhone.setText(phone);
    }

    @Override
    public void changeMinDeliveryPriceSuc(String price) {
        tvMinDeliveryPrice.setText(price);
    }

    /**
     * 修改时间成功
     * @param data
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAddSpec(ChangeBusTime data) {
        tvShopBusTime.setText(data.getBusBeginTime()+"-"+data.getBusEndTime());
    }


    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.rl_shop_bus_time, R.id.rl_shop_bus_phone, R.id.sb_operating, R.id
            .sb_accept_order_type,
            R.id.sb_draw_invoice, R.id.rl_min_delivery_price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_shop_bus_time:
                if(mShopDetail == null)return;
                start(BusTimeFragment.newInstance(mShopDetail.getBusBeginTime(),mShopDetail.getBusEndTime()));
                break;
            case R.id.rl_shop_bus_phone:    //更改联系方式
                DialogUtils.inputDialog(getActivity(), "联系电话", "请输入联系电话", InputType
                                .TYPE_CLASS_PHONE,
                        (dialog, input) -> {
                            if (TextUtils.isEmpty(input)) {
                                ArmsUtils.snackbarText("请输入联系电话", ConstantUtil.SNACK_WARING);
                            } else {
                                mPresenter.changePhone(input + "");
                            }
                        }).show();
                break;
            case R.id.sb_operating:     //切换营业状态
                if (sbOperating.isChecked())    //设置为营业中
                    mPresenter.putOperating();
                else                          //设置为歇业中
                    mPresenter.delOperating();

                break;
            case R.id.sb_accept_order_type: //切换接单方式
                if (sbAcceptOrderType.isChecked())    //设置自动
                    mPresenter.putAutoOrder();
                else                          //设置手动
                    mPresenter.delAutoOrder();
                break;
            case R.id.rl_min_delivery_price:    //更改最低配送金额
                DialogUtils.inputDialog(getActivity(), "最低配送金额", "请输入最低配送金额",
                        InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL,
                        (dialog, input) -> {
                            if (TextUtils.isEmpty(input)) {
                                ArmsUtils.snackbarText("请输入最低配送金额", ConstantUtil.SNACK_WARING);
                            } else {
                                mPresenter.changeMinDeliveryPrice(input + "");
                            }
                        }).show();
                break;
            case R.id.sb_draw_invoice:  //切换是否允许开发票
                if (sbDrawInvoice.isChecked())
                    mPresenter.putDrawInvoice();
                else
                    mPresenter.delDrawInvoice();
                break;
        }
    }
}
