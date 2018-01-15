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
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.app.utils.XDateUtils;
import food.xinyuan.seller.di.component.DaggerActivityFirstComponent;
import food.xinyuan.seller.di.module.ActivityFirstModule;
import food.xinyuan.seller.mvp.contract.ActivityFirstContract;
import food.xinyuan.seller.mvp.presenter.ActivityFirstPresenter;


public class ActivityFirstFragment extends AbstractMyBaseFragment<ActivityFirstPresenter> implements ActivityFirstContract.View {

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
    @BindView(R.id.et_reduce)
    EditText etReduce;
    @BindView(R.id.tv_save)
    TextView tvSave;

    public static ActivityFirstFragment newInstance() {
        ActivityFirstFragment fragment = new ActivityFirstFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerActivityFirstComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .activityFirstModule(new ActivityFirstModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activity_first, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText("首单立减");
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

    private Date startDate = Calendar.getInstance().getTime();
    private Date endDate;
    //只显示年月日
    boolean[] types = {true, true, true, false, false, false};

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
}
