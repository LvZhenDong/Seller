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

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.response.ShopActivity;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.app.utils.XDateUtils;
import food.xinyuan.seller.di.component.DaggerActivitySpecificComponent;
import food.xinyuan.seller.di.module.ActivitySpecificModule;
import food.xinyuan.seller.mvp.contract.ActivitySpecificContract;
import food.xinyuan.seller.mvp.presenter.ActivitySpecificPresenter;

/**
 * 添加其他活动
 */
public class ActivitySpecificFragment extends AbstractMyBaseFragment<ActivitySpecificPresenter> implements ActivitySpecificContract.View {

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
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_save)
    TextView tvSave;

    ShopActivity mShopActivity;

    public static ActivitySpecificFragment newInstance(ShopActivity shopActivity) {
        ActivitySpecificFragment fragment = new ActivitySpecificFragment();
        fragment.mShopActivity = shopActivity;
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerActivitySpecificComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .activitySpecificModule(new ActivitySpecificModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activity_specific, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText("其他");
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
            etName.setText(mShopActivity.getActivityName());
        } else {
            //如果是添加活动，仅加载红包list
            tvStartTime.setText(XDateUtils.date2String(startDate, "yyyy-MM-dd"));
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

    @OnClick({R.id.rl_start_time, R.id.rl_end_time, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_start_time:
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
            case R.id.tv_save:
                if(mShopActivity == null){
                    mPresenter.addActivity(tvStartTime.getText().toString().trim(),
                            tvEndTime.getText().toString().trim(),
                            etName.getText().toString().trim());
                }else {
                    mPresenter.updateActivity(tvStartTime.getText().toString().trim(),
                            tvEndTime.getText().toString().trim(),
                            etName.getText().toString().trim(),mShopActivity.getActivityId());
                }

                break;
        }
    }

    @Override
    public void addActivitySuc() {
        pop();
    }
}
