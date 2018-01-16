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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.response.Coupon;
import food.xinyuan.seller.app.data.bean.response.ShopActivity;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.app.utils.DialogUtils;
import food.xinyuan.seller.app.utils.XDateUtils;
import food.xinyuan.seller.di.component.DaggerActivityComplimentaryComponent;
import food.xinyuan.seller.di.module.ActivityComplimentaryModule;
import food.xinyuan.seller.mvp.contract.ActivityComplimentaryContract;
import food.xinyuan.seller.mvp.presenter.ActivityComplimentaryPresenter;

/**
 * 购满就送
 */
public class ActivityComplimentaryFragment extends AbstractMyBaseFragment<ActivityComplimentaryPresenter> implements ActivityComplimentaryContract.View {

    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;

    MaterialDialog mDialog;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.rl_start_time)
    RelativeLayout rlStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.rl_end_time)
    RelativeLayout rlEndTime;
    @BindView(R.id.et_min)
    EditText etMin;
    @BindView(R.id.tv_coupon)
    TextView tvCoupon;
    @BindView(R.id.et_count)
    EditText etCount;
    @BindView(R.id.tv_save)
    TextView tvSave;

    private ShopActivity mShopActivity;
    List<String> mCouponNames = new ArrayList<>();
    List<Coupon> mCouponList = new ArrayList<>();

    public static ActivityComplimentaryFragment newInstance(ShopActivity shopActivity) {
        ActivityComplimentaryFragment fragment = new ActivityComplimentaryFragment();
        fragment.mShopActivity = shopActivity;
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerActivityComplimentaryComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .activityComplimentaryModule(new ActivityComplimentaryModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activity_complimentary, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText("购满就送");
        CommonUtils.setBack(this, ivHeaderLeft);
        mDialog = new MaterialDialog.Builder(getActivity()).content(R.string.waiting).
                progress(true, 0).build();


        if (mShopActivity != null) {
            //如果是修改活动，则填入数据
            tvStartTime.setText(XDateUtils.millis2String(mShopActivity.getBeginTime(), "yyyy-MM-dd"));
            startDate = XDateUtils.millis2Date(mShopActivity.getBeginTime());
            long endTime = mShopActivity.getEndTime();
            tvEndTime.setText(endTime <= 0 ? "无限制" : XDateUtils.millis2String(mShopActivity.getEndTime(), "yyyy-MM-dd"));
            endDate = XDateUtils.millis2Date(mShopActivity.getEndTime());
            if (mShopActivity.getActivityContent() != null) {
                etMin.setText(mShopActivity.getActivityContent().getFull() + "");
                etCount.setText(mShopActivity.getActivityContent().getCouponCount() + "");
            }

            mPresenter.getCouponList();

        } else {
            //如果是添加活动，仅加载红包list
            tvStartTime.setText(XDateUtils.date2String(startDate, "yyyy-MM-dd"));
            mPresenter.getCouponList();
        }

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

    private Date startDate = Calendar.getInstance().getTime();
    private Date endDate;
    //只显示年月日
    boolean[] types = {true, true, true, false, false, false};

    private int mSelectedCoupon = -1;

    @OnClick({R.id.rl_start_time, R.id.rl_end_time, R.id.tv_coupon, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_start_time:
                hideSoftInput();
                showTimerPicker((date, v) -> {

                    if (endDate != null && date.after(endDate)) {
                        ArmsUtils.makeText(getActivity(), "结束时间不能小于开始时间");
                    } else {
                        startDate = date;
                        tvStartTime.setText(XDateUtils
                                .date2String(date, "yyyy-MM-dd"));
                    }
                });
                break;
            case R.id.rl_end_time:
                hideSoftInput();
                showTimerPicker((date, v) -> {
                    if (startDate.after(date)) {
                        ArmsUtils.makeText(getActivity(), "结束时间不能小于开始时间");
                    } else {
                        endDate = date;
                        tvEndTime.setText(XDateUtils
                                .date2String(date, "yyyy-MM-dd"));
                    }
                });
                break;
            case R.id.tv_coupon:
                DialogUtils.singleChoiceDialog(getActivity(), mCouponNames, mSelectedCoupon,
                        (dialog, itemView, which, text) -> {
                            tvCoupon.setText(text);
                            mSelectedCoupon = which;
                            return true;
                        }).show();
                break;
            case R.id.tv_save:

                if (mShopActivity== null){
                    //添加
                    mPresenter.addActivity(tvStartTime.getText().toString().trim(),
                            tvEndTime.getText().toString().trim(),
                            etMin.getText().toString().trim(),
                            etCount.getText().toString().trim(),
                            mSelectedCoupon, mCouponList);
                }else {
                    //修改
                    mPresenter.updateActivity(tvStartTime.getText().toString().trim(),
                            tvEndTime.getText().toString().trim(),
                            etMin.getText().toString().trim(),
                            etCount.getText().toString().trim(),
                            mSelectedCoupon, mCouponList,mShopActivity.getActivityId());
                }

                break;
        }
    }

    private void showTimerPicker(TimePickerView.OnTimeSelectListener listener) {
        Calendar calendar = Calendar.getInstance();
        //如果已经选择了开始时间，则以选定的时间做为开始，否则以当前系统时间开始
        if (startDate != null)
            calendar.setTime(startDate);
        TimePickerView pvTime = new TimePickerView.Builder(getActivity(), listener)
                .setRangDate(calendar, null)
                .setType(types)
                .build();

        pvTime.show();
    }

    @Override
    public void getCouponListSuc(List<Coupon> list) {
        mCouponList = list;
        boolean updateActivity = (mShopActivity != null && mShopActivity.getActivityContent() != null);
        for (Coupon item : list) {
            mCouponNames.add(item.getCouponName() + "  "+item.getEndTimeStr());
            //如果是修改活动的话，还要将已经选择的红包显示出来
            if (updateActivity && item.getCouponId() == mShopActivity.getActivityContent().getCouponId()) {
                tvCoupon.setText(item.getCouponName() + item.getEndTimeStr());
                mSelectedCoupon = list.indexOf(item);
            }
        }
    }

    @Override
    public void addActivitySuc() {
        pop();
    }

    @Override
    public void getActivitySuc(ShopActivity shopActivity) {

    }
}
