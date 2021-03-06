package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bigkoo.pickerview.TimePickerView;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.kyleduo.switchbutton.SwitchButton;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.event.EventConstant;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.app.utils.TimePickerUtils;
import food.xinyuan.seller.app.utils.XDateUtils;
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
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_pick_up_hint)
    TextView tvPickUpHint;
    @BindView(R.id.sw_pick_up)
    SwitchButton swPickUp;
    @BindView(R.id.tv_random_hint)
    TextView tvRandomHint;
    @BindView(R.id.sw_fixed)
    SwitchButton swFixed;
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.rl_start_time)
    RelativeLayout rlStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.rl_end_time)
    RelativeLayout rlEndTime;
    @BindView(R.id.et_inventory)
    EditText etInventory;
    @BindView(R.id.rl_inventory)
    RelativeLayout rlInventory;
    @BindView(R.id.et_max_pick_number)
    EditText etMaxPickNumber;
    @BindView(R.id.et_min)
    EditText etMin;
    @BindView(R.id.rl_money_range)
    RelativeLayout rlRange;
    @BindView(R.id.et_max_money)
    EditText etMaxMoney;
    @BindView(R.id.et_min_money)
    EditText etMinMoney;
    @BindView(R.id.rl_money)
    RelativeLayout rlMoney;

    MaterialDialog mDialog;
    TimePickerUtils mTimePickerUtils;

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
        mTimePickerUtils=new TimePickerUtils();
        tvHeaderCenter.setText("红包设置");
        CommonUtils.setBack(this, ivHeaderLeft);

        mDialog = new MaterialDialog.Builder(getActivity()).content(R.string.waiting).
                progress(true, 0).build();

        tvStartTime.setText(XDateUtils.date2String(Calendar.getInstance().getTime(),
                "yyyy-MM-dd"));

    }

    @Override
    public void showLoading() {
        if (mDialog != null) mDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mDialog != null) mDialog.dismiss();
    }

    TimePickerUtils.TimeCallBack callBack=new TimePickerUtils.TimeCallBack() {
        @Override
        public void onStartTimeSelect(Date startDate) {
            tvStartTime.setText(XDateUtils
                    .date2String(startDate, "yyyy-MM-dd"));
        }

        @Override
        public void onEndTimeSelect(Date endDate) {
            tvEndTime.setText(XDateUtils
                    .date2String(endDate, "yyyy-MM-dd"));
        }
    };

    @OnClick({R.id.sw_pick_up, R.id.sw_fixed, R.id.rl_start_time, R.id.rl_end_time, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sw_pick_up:
                tvPickUpHint.setText(swPickUp.isChecked() ? "允许" : "不允许");
                break;
            case R.id.sw_fixed:
                if(swFixed.isChecked()){
                    tvRandomHint.setText("固定");
                    rlRange.setVisibility(View.GONE);
                    rlMoney.setVisibility(View.VISIBLE);
                }else {
                    tvRandomHint.setText("随机");
                    rlRange.setVisibility(View.VISIBLE);
                    rlMoney.setVisibility(View.GONE);
                }
                break;
            case R.id.rl_start_time:
                hideSoftInput();
                mTimePickerUtils.showStart(getActivity(),callBack);
                break;
            case R.id.rl_end_time:
                hideSoftInput();
                mTimePickerUtils.showEnd(getActivity(),callBack);
                break;
            case R.id.tv_save:
                //添加固定金额红包
                if(swFixed.isChecked()){
                    mPresenter.addFixedCoupon(etName.getText().toString().trim(), swPickUp.isChecked(), swFixed.isChecked(),
                            etMoney.getText().toString().trim(), tvStartTime.getText().toString().trim(),
                            tvEndTime.getText().toString(), etMaxPickNumber.getText().toString().trim(),
                            etMin.getText().toString().trim());
                }else { //添加随机金额红包
                    mPresenter.addRandomCoupon(etName.getText().toString().trim(), swPickUp.isChecked(), swFixed.isChecked(),
                            etMinMoney.getText().toString().trim(), etMaxMoney.getText().toString().trim(), tvStartTime.getText().toString().trim(),
                            tvEndTime.getText().toString(), etMaxPickNumber.getText().toString().trim(),
                            etMin.getText().toString().trim());
                }

                break;
        }
    }

    @Override
    public void addCouponSuc() {
        pop();
    }
}
